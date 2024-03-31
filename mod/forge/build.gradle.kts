plugins {
    id("net.minecraftforge.gradle") version "6.0.21"
    id("org.parchmentmc.librarian.forgegradle") version "1.+"
}

description = "Forge Mod for SocketMC Client-side Implementation"

val minecraft = project.ext["minecraft_version"].toString()
val parchment = project.ext["parchment"].toString()

dependencies {
    api(project(":socketmc-core"))

    minecraft("net.minecraftforge:forge:$minecraft-49.0.30")

    annotationProcessor("org.spongepowered:mixin:0.8.5:processor")
}

minecraft {
    mappings("parchment", "$parchment-$minecraft")
}

tasks {
    jar {
        from(project(":socketmc-core").sourceSets["main"].output)
    }

    processResources {
        filesMatching("**/*.toml") {
            expand(project.properties)
        }
    }
}