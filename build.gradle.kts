plugins {
    kotlin("jvm") version "1.9.23"

    java
    jacoco
    `maven-publish`
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

            overview = "core/src/main/javadoc/overview.html"

            showFromProtected()

            links("https://hub.spigotmc.org/javadocs/spigot/")
            links("https://netty.io/5.0/api/")
            links("https://javadoc.io/doc/org.jetbrains/annotations-java5/23.0.0/")
        }
    }
}

allprojects {
    project.ext["minecraft_version"] = "1.20.4"
    project.ext["parchment"] = "2024.02.25"

    group = "me.gamercoder215.socketmc"
    version = "${project.ext["minecraft_version"]}-0.1.0"
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
}

subprojects {
    apply<JacocoPlugin>()
    apply(plugin = "org.jetbrains.kotlin.jvm")

    tasks {
        compileJava {
            options.encoding = "UTF-8"
            options.isDeprecation = false
            options.isWarnings = false
            options.compilerArgs.addAll(listOf("-Xlint:all", "-Xlint:-processing"))
        }

        compileKotlin {
            kotlinOptions.jvmTarget = "17"
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    dependencies {
        compileOnly(kotlin("stdlib"))
        compileOnly("org.jetbrains:annotations:24.1.0")

        testImplementation("org.junit.jupiter:junit-jupiter:5.10.2")
    }

}