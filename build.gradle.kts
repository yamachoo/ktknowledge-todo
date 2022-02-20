import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.6.3"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("io.gitlab.arturbosch.detekt").version("1.19.0")
    id("org.flywaydb.flyway") version "8.0.1"
    id("nu.studer.jooq") version "7.1.1"
    kotlin("jvm") version "1.6.10"
    kotlin("plugin.spring") version "1.6.10"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

val graphqlKotlinVersion = "5.3.2"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-jooq")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    implementation("com.expediagroup:graphql-kotlin-spring-server:$graphqlKotlinVersion")
    implementation("com.expediagroup:graphql-kotlin-hooks-provider:$graphqlKotlinVersion")
    implementation("com.graphql-java:graphql-java-extended-scalars:17.0")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.19.0")
    jooqGenerator("mysql:mysql-connector-java")
    jooqGenerator("jakarta.xml.bind:jakarta.xml.bind-api:3.0.1")
    runtimeOnly("mysql:mysql-connector-java")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.projectreactor:reactor-test")
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

detekt {
    source = files(".")
    autoCorrect = true
}

flyway {
    url = System.getenv("MYSQL_URL")
    user = System.getenv("MYSQL_USER")
    password = System.getenv("MYSQL_PASSWORD")
}

jooq {
    configurations {
        create("main") {
            jooqConfiguration.apply {
                jdbc.apply {
                    driver = "com.mysql.cj.jdbc.Driver"
                    url = System.getenv("MYSQL_URL")
                    user = System.getenv("MYSQL_USER")
                    password = System.getenv("MYSQL_PASSWORD")
                }
                generator.apply {
                    name = "org.jooq.codegen.KotlinGenerator"
                    database.apply {
                        name = "org.jooq.meta.mysql.MySQLDatabase"
                        inputSchema = System.getenv("MYSQL_DB_NAME")
                        excludes = "flyway_schema_history"
                    }
                    generate.apply {
                        isDeprecated = false
                        isTables = true
                    }
                    target.apply {
                        packageName = "com.example.ktknowledgeTodo.infra.jooq"
                        directory = "$buildDir/generated/source/jooq/main"
                    }
                }
            }
        }
    }
}

tasks.named("generateJooq") {
    dependsOn(tasks.named("flywayMigrate"))
}
