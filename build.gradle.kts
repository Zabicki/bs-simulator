plugins {
    id("java")
    //id("org.springframework.boot") version "3.0.5"
}

group = "pl.zabicki.billing"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.projectlombok:lombok:1.18.22")
    implementation("jakarta.validation:jakarta.validation-api:3.0.2")
    implementation("org.hibernate.validator:hibernate-validator:8.0.0.Final")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
    implementation("org.springframework:spring-core:6.0.7")

    implementation("org.springframework.boot:spring-boot-starter-web:3.0.5")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.0.5")
    testImplementation("org.springframework.boot:spring-boot-starter-test:3.0.5")

    //implementation("org.springframework.data:spring-data-cassandra:4.0.4")
    //implementation("org.springframework.data:spring-data-redis:3.0.4")
    //implementation("org.springframework.data:spring-data-elasticsearch:5.0.4")
    //implementation("org.springframework.data:spring-data-neo4j:7.0.4")

    implementation("org.liquibase:liquibase-core:4.20.0")
    implementation("org.postgresql:postgresql:42.6.0")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}