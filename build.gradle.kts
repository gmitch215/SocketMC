plugins {
    kotlin("jvm") version "1.9.23"

    java
    `maven-publish`
}

allprojects {
    group = "me.gamercoder215.socketmc"
    version = "0.1.0"
    description = "Direct Minecraft Server-to-Client Communication"

    project.ext["id"] = "socketmc"
    project.ext["name"] = "SocketMC"
    project.ext["author"] = "gmitch215"
    project.ext["license"] = "GPL-3.0"
    project.ext["github"] = "https://github.com/gmitch215/SocketMC"

    apply(plugin = "maven-publish")
    apply<JavaPlugin>()
    apply<JavaLibraryPlugin>()

    repositories {
        mavenCentral()
        mavenLocal()

        maven("https://repo.codemc.org/repository/nms/")
        maven("https://repo.papermc.io/repository/maven-public/")
        maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
        maven("https://maven.fabricmc.net/")
        maven("https://maven.minecraftforge.net/")
        maven("https://maven.parchmentmc.org")
    }

    project.ext["minecraft_version"] = "1.20.4"
    project.ext["parchment"] = "2024.02.25"
}

subprojects {
    apply<JacocoPlugin>()
    apply(plugin = "org.jetbrains.kotlin.jvm")

    tasks {
        compileJava {
            options.encoding = "UTF-8"
        }
    }

    java {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    dependencies {
        compileOnly(kotlin("stdlib"))
        compileOnly("org.jetbrains:annotations:24.1.0")

        testImplementation("org.junit.jupiter:junit-jupiter:5.10.2")
    }

}