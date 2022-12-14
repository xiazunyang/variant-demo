import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    kotlin("jvm")
    kotlin("plugin.spring")
}

group = "cn.numeron"
version = "0.0.1-SNAPSHOT"

java.sourceCompatibility = JavaVersion.VERSION_11

sourceSets {
    create("actuator") {
        java {
            srcDirs("src/actuator/kotlin")
        }
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    // 注册actuator渠道
    registerFeature("actuator") {
        usingSourceSet(sourceSets["actuator"])
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    // actuator
    "actuatorImplementation"(project(":app"))
    "actuatorImplementation"("org.springframework.boot:spring-boot-starter-actuator")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
