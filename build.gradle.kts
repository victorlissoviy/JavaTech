plugins {
  java
  id("org.springframework.boot") version "3.3.5"
  id("io.spring.dependency-management") version "1.1.6"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

var mongoDBVersion = "5.2.1"
val jwtVersion = "0.11.5"

java {
  toolchain {
    languageVersion = JavaLanguageVersion.of(21)
  }
}

repositories {
  mavenCentral()
}

dependencies {
  implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
  implementation("org.springframework.boot:spring-boot-starter-data-redis")
  implementation("org.springframework.boot:spring-boot-starter-web")
  implementation("org.springframework.boot:spring-boot-starter-security")
  implementation("io.jsonwebtoken:jjwt-api:$jwtVersion")

  runtimeOnly("io.jsonwebtoken:jjwt-impl:$jwtVersion")
  runtimeOnly("io.jsonwebtoken:jjwt-jackson:$jwtVersion")

  compileOnly("org.projectlombok:lombok")

  annotationProcessor("org.projectlombok:lombok")

  testImplementation("org.springframework.boot:spring-boot-starter-test")
  testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
  useJUnitPlatform()
}

tasks.wrapper {
  version = "latest"
  distributionType = Wrapper.DistributionType.ALL
}