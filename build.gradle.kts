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
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
    implementation("org.springframework:spring-core:6.0.7")
    implementation("org.springframework.boot:spring-boot-starter-web:3.0.5")
    testImplementation("org.springframework.boot:spring-boot-starter-test:3.0.5")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}