FROM gradle:6.5-jdk8
COPY . .
RUN gradle build 
WORKDIR ./build/libs
CMD ["java", "-jar", "com.edu.product-service-0.0.1.jar"]

