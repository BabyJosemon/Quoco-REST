FROM openjdk:8-jre-alpine
COPY target/broker-*-SNAPSHOT.jar /broker.jar
CMD ["/usr/bin/java", "-jar", "/broker.jar", "auldfellas:8081,dodgydrivers:8082,girlpower:8083"]
