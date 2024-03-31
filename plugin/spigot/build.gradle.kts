import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import io.github.patrick.gradle.remapper.RemapTask

plugins {
    id("io.github.patrick.remapper") version "1.4.0"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

val minecraft = project.ext["minecraft_version"].toString()

dependencies {
    compileOnly("org.spigotmc:spigot:$minecraft-R0.1-SNAPSHOT:remapped-mojang")

    // API
    api(project(":socketmc-core"))
}

java {
    withSourcesJar()
    withJavadocJar()
}

tasks {
    assemble {
        dependsOn("remap")
    }

    jar.configure {
        enabled = false
    }

    remap {
        dependsOn("shadowJar")

        inputTask.set(getByName<ShadowJar>("shadowJar"))
        version.set(minecraft)
        action.set(RemapTask.Action.MOJANG_TO_SPIGOT)
        archiveName.set("${project.name}-${project.version}.jar")
    }
}