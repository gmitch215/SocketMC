plugins {
    id("io.github.patrick.remapper") version "1.4.1"
    id("io.github.goooler.shadow") version "8.1.7"
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
    compileJava {
        dependsOn(project(":socketmc-spigot").tasks["assemble"])
    }

    javadoc {
        enabled = true

        options {
            require(this is StandardJavadocDocletOptions)

            links("https://hub.spigotmc.org/javadocs/spigot/")
            links("https://javadoc.io/doc/org.jetbrains/annotations-java5/23.0.0/")
        }
    }
}