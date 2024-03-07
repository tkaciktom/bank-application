#!/bin/bash
# register-schemas-topics.sh

# Wait for the Schema Registry to be available
echo "Waiting for Schema Registry to start..."
while ! curl -s http://schema-registry:8081/subjects > /dev/null; do
  sleep 1
done
echo "Schema Registry is up!"

# Register each schema
for schema in /schemas/*.avsc; do
  echo "Registering schema: $schema"
  curl -X POST -H "Content-Type: application/vnd.schemaregistry.v1+json" \
       --data "{\"schema\": $(jq -Rs . < $schema)}" \
       http://schema-registry:8081/subjects/$(basename "$schema" .avsc)/versions
  echo ""
done

# Add Kafka topics initialization
echo "Initializing Kafka topics..."

# Define topics
topics=("workflow-commands" "account-management-commands" "card-management-commands" "account-events" "card-events" "notification-commands")

# Wait for Kafka to be available
echo "Waiting for Kafka to start..."
while ! nc -z kafka 9092; do   
  sleep 1
done
echo "Kafka is up!"

# Create topics
for topic in "${topics[@]}"; do
  echo "Creating topic: $topic"
  kafka-topics.sh --create --if-not-exists --zookeeper zookeeper:2181 --replication-factor 1 --partitions 1 --topic "$topic"
done

echo "Kafka topics initialized!"
