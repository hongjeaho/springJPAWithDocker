FROM openjdk:11.0.7-jre-slim

ENV DEMO_ROOT=/root

ADD /build/libs/demo-0.0.1-SNAPSHOT.jar $DEMO_ROOT
WORKDIR ${DEMO_ROOT}

CMD ["java", "-jar", "-Dspring.profiles.active=dev", "demo-0.0.1-SNAPSHOT.jar"]
