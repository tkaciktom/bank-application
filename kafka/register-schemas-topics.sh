#!/bin/bash
# register-schemas-topics.sh

echo "Waiting for Schema Registry to start..."
while ! curl -s http://schema-registry:8081/subjects > /dev/null; do
  sleep 1
done
echo "Schema Registry is up!"

for schema in /schemas/*.avsc; do
  echo "Registering schema: $schema"
  curl -X POST -H "Content-Type: application/vnd.schemaregistry.v1+json" \
       --data "{\"schema\": $(jq -Rs . < $schema)}" \
       http://schema-registry:8081/subjects/$(basename "$schema" .avsc)/versions
  echo ""
done

echo "Initializing Kafka topics..."

topics=("bundle-management-commands" "account-management-commands" "card-management-commands" "notification-commands" "account-events" "card-events" "bundle-events")

echo "Waiting for Kafka to start..."
while ! nc -z kafka 9092; do   
  sleep 1
done
echo "Kafka is up!"

for topic in "${topics[@]}"; do
  echo "Creating topic: $topic"
  kafka-topics.sh --create --if-not-exists --zookeeper zookeeper:2181 --replication-factor 1 --partitions 1 --topic "$topic"
done

echo "Kafka topics initialized!"
