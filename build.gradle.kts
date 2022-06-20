//import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.0" apply false
    //id("com.github.johnrengelman.shadow") version "7.1.2" apply false
    id("org.jetbrains.dokka") version "1.7.0" apply false
    `maven-publish`
}

group = "io.github.nickacpt.behaviours"

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "java-library")
    //apply(plugin = "com.github.johnrengelman.shadow")
    apply(plugin = "maven-publish")
    apply(plugin = "org.jetbrains.dokka")

    group = "io.github.nickacpt.behaviours"

    println("${project.group}:${project.name}")

    repositories {
        mavenCentral()
    }

    extensions.getByType<org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension>().jvmToolchain {
        this.languageVersion.set(JavaLanguageVersion.of(8))
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
        kotlinOptions.javaParameters = true
    }

    tasks.withType<AbstractArchiveTask> {
        isPreserveFileTimestamps = false
    }


    /*tasks.withType<ShadowJar> {
        archiveBaseName.set("${project.name}-behaviour")
        destinationDirectory.set(rootProject.file("build/libs"))
    }*/

    publishing {
        publications {
            create<MavenPublication>("java") {
                from(components["java"])
            }
        }

        repositories {
            val lightCraftRepoDir = project.findProperty("lightcraft.repo.location")
            if (lightCraftRepoDir != null) {
                maven {
                    name = "OrionCraftRepo"
                    url = File(lightCraftRepoDir.toString()).toURI()
                }
            }
        }
    }
}