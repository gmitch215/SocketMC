plugins {
    id("io.github.patrick.remapper") version "1.4.0"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

val minecraft = project.ext["minecraft_version"].toString()

java {
    withSourcesJar()
    withJavadocJar()
}

dependencies {
    compileOnly("io.papermc.paper:paper-api") {
        version {
            strictly("$minecraft-R0.1-SNAPSHOT")
        }
    }

    // API
    api(project(":socketmc-core"))
    api(project(":socketmc-spigot"))
}

tasks {
    javadoc {
        enabled = true

        options {
            require(this is StandardJavadocDocletOptions)

            links("https://hub.spigotmc.org/javadocs/spigot/")
            links("https://javadoc.io/doc/org.jetbrains/annotations-java5/23.0.0/")
        }
    }
}