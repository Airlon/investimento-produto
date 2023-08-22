FROM openjdk
WORKDIR /app

COPY target/investimento.jar /app/investimento.jar
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "investimento.jar"]