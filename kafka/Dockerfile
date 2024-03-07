FROM alpine:latest

RUN apk add --no-cache curl jq bash

COPY register-schemas-topics.sh /register-schemas-topics.sh
COPY schemas /schemas

RUN chmod +x /register-schemas-topics.sh

CMD ["/register-schemas-topics.sh"]
