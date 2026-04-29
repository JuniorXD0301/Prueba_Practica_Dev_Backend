FROM eclipse-temurin:17-jdk-jammy AS build

WORKDIR /app

COPY mvnw pom.xml ./
COPY .mvn .mvn

# descargar las dependiencias sin compilar el proyecto, por eso -B
RUN chmod +x mvnw && ./mvnw dependency:go-offline -B 

COPY src src
RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
