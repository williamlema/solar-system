FROM alpine/git as clone
WORKDIR /app
RUN git clone https://github.com/williamlema/solar-system.git

FROM maven:3.5-jdk-8-alpine as build
WORKDIR /app
COPY --from=clone /app/solar-system /app
RUN mvn install

FROM openjdk:8-jre-alpine
WORKDIR /app
COPY --from=build /app/target/solar-system-0.0.1-SNAPSHOT.jar /app
EXPOSE 9081
CMD ["java -jar solar-system-0.0.1-SNAPSHOT.jar"]
