plugins {
    id("java")
}

group = "pl.zabicki.billing"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")

    implementation("org.apache.commons:commons-lang3:3.12.0")
    implementation("org.apache.commons:commons-csv:1.10.0")

    implementation("org.projectlombok:lombok:1.18.22")
    annotationProcessor("org.projectlombok:lombok:1.18.22")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}