plugins {
    java
    id("org.springframework.boot") version "4.0.0"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "com.sogeor"
version = "1.0.0-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

repositories {
    maven("https://nexus.sogeor.com/repository/maven-public/") {
        credentials {
            username = findProperty("SOGEOR_NEXUS_USERNAME")?.toString() ?: System.getenv("NEXUS_USERNAME")
            password = findProperty("SOGEOR_NEXUS_PASSWORD")?.toString() ?: System.getenv("NEXUS_PASSWORD")
        }
    }
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.cloud:spring-cloud-config-server")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    testImplementation("io.projectreactor:reactor-test")

    compileOnly("org.jetbrains:annotations:${findProperty("org.jetbrains.annotations.version")}")
    implementation("io.micrometer:micrometer-registry-prometheus")
    implementation("org.springdoc:springdoc-openapi-starter-webflux-api:${
        findProperty("org.springdoc.springdoc-openapi-starter-webflux-api.version")
    }")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

dependencyManagement {
    imports {
        mavenBom(
            "org.springframework.cloud:spring-cloud-dependencies:${findProperty("org.springframework.cloud.version")}")
    }
}

tasks.wrapper {
    gradleVersion = "9.2.1"
}

tasks.withType<Test> {
    useJUnitPlatform()
}
