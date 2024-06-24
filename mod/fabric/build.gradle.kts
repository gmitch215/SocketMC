@file:Suppress("UnstableApiUsage")

import net.fabricmc.loom.api.LoomGradleExtensionAPI

plugins {
    id("fabric-loom")
    id("com.modrinth.minotaur")
}

description = "Fabric Mod for SocketMC Client-side Implementation"

val minecraft = project.ext["minecraft_version"].toString()
val parchment = project.ext["parchment"].toString()

val fabric = "0.100.2"

dependencies {
    api(project(":socketmc-core"))
    api(project(":socketmc-shared"))

    minecraft("com.mojang:minecraft:$minecraft")
    mappings(loom.layered {
        officialMojangMappings()
        parchment("org.parchmentmc.data:parchment-$minecraft:$parchment@zip")
    })

    modImplementation("net.fabricmc:fabric-loader:0.15.6")

    setOf(
        "fabric-api-base",
        "fabric-networking-api-v1",
        "fabric-rendering-v1",
        "fabric-lifecycle-events-v1",
        "fabric-resource-loader-v0",
        "fabric-screen-api-v1",
        "fabric-key-binding-api-v1"
    ).forEach {
        modImplementation(fabricApi.module(it, "$fabric+$minecraft"))
    }

    annotationProcessor("org.spongepowered:mixin:0.8.6:processor")

    // API Hooks
    modImplementation("com.terraformersmc:modmenu:11.0.0")
}

loom {
    accessWidenerPath = file("src/main/resources/socketmc.accesswidener")

    runs {
        named("client") {
            client()
            configName = "Fabric Client"
            ideConfigGenerated(true)
            runDir = "run"
        }
    }
}

tasks {
    jar {
        from(project(":socketmc-core").sourceSets["main"].output)
        from(project(":socketmc-shared").sourceSets["main"].output)
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    }

    processResources {
        filesMatching("**/*.json") {
            expand(project.properties)
        }
    }

    configureLaunch {
        copy {
            from(project(":socketmc-shared").sourceSets["main"].resources)
            into(layout.buildDirectory.dir("resources/main"))
            duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        }
    }

    named("modrinth") {
        dependsOn("remapJar")
    }
}

modrinth {
    token.set(System.getenv("MODRINTH_TOKEN"))
    projectId.set(project.ext["id"].toString())

    versionName.set("SocketMC v$version")
    versionNumber.set(version.toString())
    versionType.set(project.ext["version_type"].toString())

    uploadFile.set(tasks.remapJar)
    gameVersions.add(project.ext["minecraft_version"].toString())
    changelog.set(project.ext["changelog"].toString())

    loaders.addAll(listOf("fabric", "quilt"))
    dependencies {
        required.project("fabric-api")
    }

    syncBodyFrom.set(rootProject.file("README.md").bufferedReader().use { it.readText() })
}