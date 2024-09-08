plugins {
    id("net.neoforged.gradle.userdev")
    id("net.neoforged.gradle.mixin")
    id("com.modrinth.minotaur")
}

description = "NeoForge Mod for SocketMC Client-side Implementation"

val minecraft = project.ext["minecraft_version"].toString()
val parchmentV = project.ext["parchment"].toString()

val neoforge = "21.0.167"

dependencies {
    api(project(":socketmc-core"))
    api(project(":socketmc-shared"))

    implementation("net.neoforged:neoforge:$neoforge")
}

minecraft {
    accessTransformers {
        file("src/main/resources/META-INF/accesstransformer.cfg")
    }
}

subsystems {
    parchment {
        minecraftVersion.set(minecraft)
        mappingsVersion.set(parchmentV)
    }
}

mixin {
    config("socketmc-neoforge.mixins.json")
}

runs {
    named("client") {
        workingDirectory("run")
        modSources(sourceSets["main"])

        dependencies {
            runtime(configurations.runtimeClasspath.get())
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

    loaders.add("neoforge")

    syncBodyFrom.set(rootProject.file("README.md").bufferedReader().use { it.readText() })
}