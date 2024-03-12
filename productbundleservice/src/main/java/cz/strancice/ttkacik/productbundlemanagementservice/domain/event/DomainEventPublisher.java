package cz.strancice.ttkacik.productbundlemanagementservice.domain.event;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Component
public class DomainEventPublisher implements ApplicationListener<ContextRefreshedEvent> {

    private final List<Consumer<DomainEvent>> subscribers = new ArrayList<>();
    private static DomainEventPublisher instance;

    public void publish(DomainEvent event) {
        subscribers.forEach(subscriber -> subscriber.accept(event));
    }

    public void subscribe(Consumer<DomainEvent> subscriber) {
        subscribers.add(subscriber);
    }

    public static DomainEventPublisher instance() {
        return instance;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        DomainEventPublisher.instance = event.getApplicationContext().getBean(DomainEventPublisher.class);
    }
}
