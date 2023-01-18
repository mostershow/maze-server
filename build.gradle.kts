import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.5.2"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("org.asciidoctor.convert") version "1.5.8"
    kotlin("jvm") version "1.5.20"
    kotlin("plugin.spring") version "1.5.20"
    kotlin("plugin.serialization") version "1.5.20"
}



group = "com.ck567.netty"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
    maven {
        name = "publicRepo"
        url = uri("https://repository.icesimba.cn/repository/maven-public/")
    }

}
tasks.getByName<Jar>("jar") {
    enabled = false
}
sourceSets.main {
    java.srcDirs("src/main/kotlin")
}
tasks.register("showRepos") {
    doLast {
        println("All repos:")
        //TODO:kotlin-dsl remove filter once we're no longer on a kotlin eap
        println(repositories.map { it.name }.filter { it != "maven" })
    }
}
configurations.all {
    // 排除spring boot中的logging。使用log子项目。
    exclude(group = "org.springframework.boot", module = "spring-boot-starter-logging")
    // check for updates every build
    resolutionStrategy.cacheChangingModulesFor(0, TimeUnit.SECONDS)
}
dependencies {
    val log4j2Version = "2.17.0"
    implementation("org.springframework.boot:spring-boot-starter-web") {
        exclude(group = "org.springframework.boot", module = "spring-boot-starter-logging")
    }

    val sprintJwtVersion = "1.0.10.RELEASE"
//    implementation("org.springframework.security:spring-security-jwt:$sprintJwtVersion")
    //jwt supports
//    implementation("org.springframework.boot:spring-boot-starter-webflux"){
//        exclude(group = "org.springframework.boot", module = "spring-boot-starter-logging")
//    }
//    implementation("io.jsonwebtoken:jjwt:0.9.1")
    implementation("com.icesimba.simba:exception:0.0.1-SNAPSHOT")
//    implementation("com.icesimba.simba:log:0.0.1-SNAPSHOT")
//    implementation("com.icesimba.simba:security:0.0.2-SNAPSHOT")
//    implementation("com.icesimba.simba:mybatis-utils:0.0.1-SNAPSHOT")
    implementation("com.icesimba.simba:common-utils:0.0.1-SNAPSHOT")
    implementation("com.icesimba.simba:scheduler:0.0.1-SNAPSHOT")
    implementation("com.icesimba.simba:enumeration:0.0.1-SNAPSHOT")
//    implementation("org.springframework.boot:spring-boot-starter-security")
//    implementation("org.springframework.kafka:spring-kafka")
    implementation("org.springframework.boot:spring-boot-starter-rsocket")
//    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
//    testImplementation("io.projectreactor:reactor-test")
    implementation("io.netty:netty-all:4.1.63.Final")
    implementation("com.google.code.gson:gson:2.8.6")
    implementation("com.google.guava:guava:30.1.1-jre")

    implementation("com.google.guava:guava:31.0.1-jre")
    implementation("io.netty:netty-all")
    implementation("org.springframework.boot:spring-boot-configuration-processor")
//    implementation("org.mybatis.dynamic-sql:mybatis-dynamic-sql:1.2.0")
//    implementation("com.baomidou:mybatis-plus-boot-starter:$mybatisplusVersion")
//    testImplementation("com.baomidou:mybatis-plus-generator:$mybatisplusVersion")
    // protobuf
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-protobuf:1.3.0")
    implementation("com.google.protobuf:protobuf-kotlin-lite:3.19.1")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.20")
    implementation("com.google.protobuf:protobuf-java:3.19.1")
//    implementation("com.google.protobuf:protoc:3.19.1")
//    implementation("org.jetbrains.kotlin:kotlin-serialization:1.5.20")
//    implementation("com.google.protobuf:protobuf-gradle-plugin:0.8.8")
    implementation("org.apache.logging.log4j:log4j-slf4j-impl:$log4j2Version")
    implementation("org.apache.logging.log4j:log4j-core:$log4j2Version") {
        //exclusions for avoiding conflict.
        exclude(group = "ch.qos.logback", module = "logback-classic")
        exclude(group = "ch.qos.logback", module = "log4j-to-slf4j")
    }
    implementation("org.apache.logging.log4j:log4j-api:$log4j2Version")
    implementation("org.apache.logging.log4j:log4j-jul:$log4j2Version")
    implementation("org.slf4j:jul-to-slf4j:1.7.25")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
