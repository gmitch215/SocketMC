import groovy.json.JsonSlurper
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.net.*

plugins {
    kotlin("jvm") version "2.0.20"

    java
    jacoco
    `maven-publish`

    // Mod
    id("fabric-loom") version "1.8.8" apply false
    id("net.minecraftforge.gradle") version "6.0.29" apply false
    id("net.neoforged.gradle.mixin") version "7.0.165" apply false
    id("net.neoforged.gradle.userdev") version "7.0.165" apply false
    id("org.parchmentmc.librarian.forgegradle") version "1.+" apply false
    id("org.spongepowered.mixin") version "0.7.+" apply false
    id("com.modrinth.minotaur") version "2.+" apply false
}

val docs = listOf(
    ":socketmc-core",
    ":socketmc-spigot",
    ":socketmc-paper"
)

tasks {
    register("allJavadoc", Javadoc::class.java) {
        docs.forEach { dependsOn(project(it).tasks["javadoc"]) }

        enabled = true
        title = "SocketMC $version API"

        source = files(docs.map { project(it).sourceSets["main"].allJava }).asFileTree
        classpath = files(docs.map { project(it).sourceSets["main"].compileClasspath })

        options {
            require(this is StandardJavadocDocletOptions)
            showFromProtected()

            overview = "core/src/main/javadoc/overview.html"

            links(
                "https://hub.spigotmc.org/javadocs/spigot/",
                "https://netty.io/4.0/api/",
                "https://javadoc.io/doc/org.jetbrains/annotations-java5/23.0.0/",
                "https://jd.advntr.dev/api/4.17.0/",
                "https://javadoc.scijava.org/JOML/"
            )
        }
    }
}

allprojects {
    val mc = "1.21"
    val pr = "0.3.0"

    project.ext["minecraft_version"] = mc
    project.ext["project_version"] = pr
    project.ext["parchment"] = "2024.07.28"

    group = "xyz.gmitch215.socketmc"
    description = "Direct Minecraft Server-to-Client Communication"

    val v = "$mc-$pr"
    val suffix = project.findProperty("suffix") ?: ""
    version = if (project.hasProperty("suffix")) "$v-$suffix" else v

    project.ext["id"] = "socketmc"
    project.ext["name"] = "SocketMC"
    project.ext["author"] = "gmitch215"
    project.ext["license"] = "GPL-3.0"
    project.ext["github"] = "https://github.com/gmitch215/SocketMC"

    project.ext["version_type"] = when {
        version.toString().contains("SNAPSHOT") -> "alpha"
        version.toString().split("-")[1].startsWith("0") -> "beta"
        else -> "release"
    }
    project.ext["changelog"] = createChangelog()

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
        maven("https://maven.terraformersmc.com/")
        maven("https://libraries.minecraft.net/")
        maven("https://maven.neoforged.net/releases/")
    }

    publishing {
        publications {
            create<MavenPublication>("maven") {
                artifactId = project.name.lowercase()

                pom {
                    name = project.name
                    description.set(project.description)
                    licenses {
                        license {
                            name.set("GPL-3.0")
                            url.set("${project.ext["github"]}/blob/master/LICENSE")
                        }
                    }
                    scm {
                        connection.set("scm:git:git://gmitch215/SocketMC.git")
                        developerConnection.set("scm:git:ssh://gmitch215/SocketMC.git")
                        url.set(project.ext["github"].toString())
                    }
                }

                from(components["java"])
            }
        }

        repositories {
            maven {
                credentials {
                    username = project.findProperty("username")?.toString() ?: System.getenv("JENKINS_USERNAME")
                    password = project.findProperty("password")?.toString() ?: System.getenv("JENKINS_PASSWORD")
                }

                val releases = "https://repo.codemc.io/repository/maven-releases/"
                val snapshots = "https://repo.codemc.io/repository/maven-snapshots/"
                url = uri(
                    project.findProperty("url") ?:
                    if (version.toString().endsWith("SNAPSHOT")) snapshots else releases
                )
            }
        }
    }
}

val jdk = JavaVersion.VERSION_21

subprojects {
    apply<JacocoPlugin>()
    apply(plugin = "org.jetbrains.kotlin.jvm")

    tasks {
        clean {
            delete("logs")
        }

        compileJava {
            options.encoding = "UTF-8"
            options.isDeprecation = false
            options.isWarnings = false
            options.compilerArgs.addAll(listOf("-Xlint:all", "-Xlint:-processing"))
        }

        compileKotlin {
            compilerOptions {
                jvmTarget.set(JvmTarget.fromTarget(jdk.toString()))
            }
        }

        javadoc {
            options {
                showFromProtected()
            }
        }

        jacocoTestReport {
            dependsOn(test)

            reports {
                csv.required.set(false)

                xml.required.set(true)
                xml.outputLocation.set(layout.buildDirectory.file("jacoco.xml"))

                html.required.set(true)
                html.outputLocation.set(layout.buildDirectory.dir("jacocoHtml"))
            }
        }

        test {
            useJUnitPlatform()
            testLogging {
                events("passed", "skipped", "failed")
            }
            finalizedBy(jacocoTestReport)
        }
    }

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(jdk.toString()))
        }
    }

    dependencies {
        compileOnly(kotlin("stdlib"))
        compileOnly("org.jetbrains:annotations:26.0.0")

        testImplementation("org.junit.jupiter:junit-jupiter:5.11.2")
    }

}

fun createChangelog(): String {
    val url = "https://api.github.com/repos/gmitch215/SocketMC/releases/tags/$version"
    return with(URI.create(url).toURL().openConnection() as HttpURLConnection) {
        requestMethod = "GET"

        if (responseCode != 200)
            return@with "No changelog available for $version"

        val response = inputStream.bufferedReader().readText()
        val json = JsonSlurper().parseText(response) as Map<*, *>

        json["body"].toString()
    }
}