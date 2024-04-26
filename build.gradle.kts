import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("org.springframework.boot") version Libs.Versions.SPRING_BOOT
    id("io.spring.dependency-management") version Libs.Versions.SPRING_DEPENDENCY_MANAGEMENT
    kotlin("jvm") version Libs.Versions.KOTLIN
    kotlin("plugin.spring") version Libs.Versions.KOTLIN
    kotlin("plugin.jpa") version Libs.Versions.KOTLIN
    id("org.jlleitschuh.gradle.ktlint") version Libs.Versions.KTLINT
    id("org.jetbrains.kotlinx.kover") version Libs.Versions.KOVER
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.kotest:kotest-runner-junit5:${Libs.Versions.KOTEST}")
    testImplementation("io.kotest:kotest-assertions-core:${Libs.Versions.KOTEST}")
    testImplementation("io.kotest:kotest-property:${Libs.Versions.KOTEST}")
    testImplementation("io.mockk:mockk:${Libs.Versions.MOCKK}")
    testRuntimeOnly("com.h2database:h2")
}

kotlin {
    compilerOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget.set(JvmTarget.JVM_17)
    }
}

tasks {
    test {
        useJUnitPlatform()
    }
}
