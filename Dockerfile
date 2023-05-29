FROM openjdk:17-jdk
EXPOSE 8080
ADD target/devops-crud-k8s-0.0.1-SNAPSHOT.jar devops-crud-k8s-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/devops-crud-k8s-0.0.1-SNAPSHOT.jar"]