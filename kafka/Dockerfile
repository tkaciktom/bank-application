FROM alpine:latest

RUN apk add --no-cache dos2unix curl jq bash openjdk11-jre-headless wget


COPY register-schemas-topics.sh /register-schemas-topics.sh
COPY schemas /schemas

RUN dos2unix /register-schemas-topics.sh
RUN chmod +x /register-schemas-topics.sh

# Stažení a instalace Kafka
ARG KAFKA_VERSION="2.8.0"
ARG SCALA_VERSION="2.13"
RUN wget https://archive.apache.org/dist/kafka/${KAFKA_VERSION}/kafka_${SCALA_VERSION}-${KAFKA_VERSION}.tgz -O /tmp/kafka_${SCALA_VERSION}-${KAFKA_VERSION}.tgz \
    && tar -xzf /tmp/kafka_${SCALA_VERSION}-${KAFKA_VERSION}.tgz -C /opt \
    && rm /tmp/kafka_${SCALA_VERSION}-${KAFKA_VERSION}.tgz

# Nastavení prostředí pro snadný přístup k Kafka CLI nástrojům
ENV PATH="/opt/kafka_${SCALA_VERSION}-${KAFKA_VERSION}/bin:${PATH}"

CMD ["/register-schemas-topics.sh"]
