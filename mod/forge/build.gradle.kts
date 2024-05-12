plugins {
    id("net.minecraftforge.gradle") version "6.0.25"
    id("org.parchmentmc.librarian.forgegradle") version "1.+"
    id("org.spongepowered.mixin") version "0.7.+"
}

description = "Forge Mod for SocketMC Client-side Implementation"

val minecraft = project.ext["minecraft_version"].toString()
val parchment = project.ext["parchment"].toString()

dependencies {
    api(project(":socketmc-core"))

    minecraft("net.minecraftforge:forge:$minecraft-50.0.13")

    annotationProcessor("org.spongepowered:mixin:0.8.5:processor")
}

minecraft {
    mappings("parchment", "$parchment-$minecraft")
}

mixin {
    add(sourceSets["main"], "socketmc-forge.mixins.refmap.json")
    config("socketmc-forge.mixins.json")

    disableEclipseAddon = true
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