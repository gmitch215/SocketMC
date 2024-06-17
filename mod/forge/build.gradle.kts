plugins {
    id("net.minecraftforge.gradle")
    id("org.parchmentmc.librarian.forgegradle")
    id("org.spongepowered.mixin")
    id("com.modrinth.minotaur")
}

description = "Forge Mod for SocketMC Client-side Implementation"

val minecraft = project.ext["minecraft_version"].toString()
val parchment = project.ext["parchment"].toString()

dependencies {
    api(project(":socketmc-core"))
    api(project(":socketmc-shared"))

    minecraft("net.minecraftforge:forge:$minecraft-50.0.13")

    annotationProcessor("org.spongepowered:mixin:0.8.6:processor")
}

minecraft {
    mappings("parchment", "$parchment-$minecraft")

    accessTransformer("src/main/resources/META-INF/accesstransformer.cfg")

    runs {
        create("client") {
            workingDirectory = "run"
            taskName = "runForgeClient"

            mods {
                create("modClientRun") {
                    source(sourceSets["main"])
                }
            }
        }
    }
}

mixin {
    add(sourceSets["main"], "socketmc-forge.mixins.refmap.json")
    config("socketmc-forge.mixins.json")

    disableEclipseAddon = true
}

tasks {
    jar {
        from(project(":socketmc-core").sourceSets["main"].output)
        from(project(":socketmc-shared").sourceSets["main"].output)
    }

    processResources {
        filesMatching("**/*.toml") {
            expand(project.properties)
        }
    }

    named("modrinth") {
        dependsOn("reobfJar")
    }
}

modrinth {
    token.set(System.getenv("MODRINTH_TOKEN"))
    projectId.set(project.ext["id"].toString())

    versionName.set("SocketMC v$version")
    versionNumber.set(version.toString())
    versionType.set(project.ext["version_type"].toString())

    uploadFile.set(tasks.jar.get().archiveFile.get().asFile)
    gameVersions.add(project.ext["minecraft_version"].toString())
    changelog.set(project.ext["changelog"].toString())

    loaders.add("forge")

    syncBodyFrom.set(rootProject.file("README.md").bufferedReader().use { it.readText() })
}