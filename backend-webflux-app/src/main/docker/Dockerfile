FROM java:8-jre-alpine
LABEL maintainer="before30 before30@gmail.com"

ARG JAR_FILE
ENV JAR_FILE=${JAR_FILE}

COPY ${JAR_FILE} .

CMD ["/bin/sh", "-c", "exec java ${JåAVA_OPTS} -jar ${JAR_FILE}"]