package cz.strancice.ttkacik.productbundlemanagementservice.infrastructure.service.idempotency;

import cz.strancice.ttkacik.productbundlemanagementservice.infrastructure.adapter.out.persistence.ProcessedMessageEntity;
import cz.strancice.ttkacik.productbundlemanagementservice.infrastructure.persistence.ProcessedMessagesRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class IdempotentConsumerAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(IdempotentConsumerAspect.class);

    private final ProcessedMessagesRepository processedMessagesRepository;

    @Pointcut("@annotation(IdempotentConsumer) && @annotation(org.springframework.transaction.annotation.Transactional)")
    public void idempotentAndTransactionalMethods() {}

    @Around("@annotation(idempotentConsumer)")
    public Object aroundIdempotentConsumerMethods(ProceedingJoinPoint joinPoint, IdempotentConsumer idempotentConsumer) throws Throwable {
        String messageIdParamName = idempotentConsumer.messageParamName();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String messageId = getMessageId(joinPoint, methodSignature, messageIdParamName);

        LOGGER.info("Checking message ID {} idempotency ...", messageId);
        if (processedMessagesRepository.existsByMessageId(messageId)) {
            LOGGER.info("Processing of message with ID {} was skipped because it has been already processed.", messageId);
            return null;
        } else {
            try {
                var processedMessageEntity = new ProcessedMessageEntity();
                processedMessageEntity.setMessageId(messageId);
                processedMessagesRepository.save(processedMessageEntity);
            } catch (DataIntegrityViolationException e) {
                LOGGER.info("Processing of message with ID {} was terminated due to a duplicate message.", messageId);
                return null;
            }
        }
        LOGGER.info("Message ID {} has not been processed yet. Processing ...", messageId);

        return joinPoint.proceed();
    }

    private static String getMessageId(ProceedingJoinPoint joinPoint, MethodSignature methodSignature, String messageIdParamName) {
        String[] parameterNames = methodSignature.getParameterNames();
        Object[] args = joinPoint.getArgs();
        String messageId = null;

        for (int i = 0; i < parameterNames.length; i++) {
            if (parameterNames[i].equals(messageIdParamName)) {
                messageId = (String) args[i];
                break;
            }
        }

        if (messageId == null) {
            throw new IllegalArgumentException("Message ID parameter '" + messageIdParamName + "' not found in method: " + methodSignature.getMethod());
        }
        return messageId;
    }
}
