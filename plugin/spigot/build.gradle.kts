import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import io.github.patrick.gradle.remapper.RemapTask

plugins {
    id("io.github.patrick.remapper") version "1.4.1"
    id("io.github.goooler.shadow") version "8.1.7"
}

val minecraft = project.ext["minecraft_version"].toString()

dependencies {
    compileOnly("org.spigotmc:spigot:$minecraft-R0.1-SNAPSHOT:remapped-mojang")
    testCompileOnly("org.spigotmc:spigot:$minecraft-R0.1-SNAPSHOT:remapped-mojang")

    // API
    api(project(":socketmc-core"))
}

java {
    withSourcesJar()
    withJavadocJar()
}

tasks {
    assemble {
        dependsOn(remap)
    }

    javadoc {
        enabled = true

        sourceSets["main"].allJava.srcDir("src/main/javadoc")

        options {
            require(this is StandardJavadocDocletOptions)

            links("https://hub.spigotmc.org/javadocs/spigot/")
            links("https://javadoc.io/doc/org.jetbrains/annotations-java5/23.0.0/")
        }
    }

    jar.configure {
        enabled = false
    }

    shadowJar {
        manifest {
            attributes(
                "Implementation-Title" to project.name,
                "Implementation-Version" to project.version,
                "Implementation-Vendor" to project.ext["author"],
            )
        }

        archiveFileName.set("${project.name}-${project.version}.jar")
        archiveClassifier.set("")
    }

    remap {
        dependsOn("shadowJar")

        inputTask.set(getByName<ShadowJar>("shadowJar"))
        version.set(minecraft)
        action.set(RemapTask.Action.MOJANG_TO_SPIGOT)
        archiveName.set("${project.name}-${project.version}.jar")
    }
}

configurations {
    listOf(apiElements, runtimeElements).map { it.get() }.forEach {
        it.outgoing.artifacts.removeIf { true }
        it.outgoing.artifact(tasks.getByName("shadowJar"))
    }
}