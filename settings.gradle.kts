rootProject.name = "SocketMC"

include(":socketmc-core")
project(":socketmc-core").projectDir = rootDir.resolve("core")

listOf("shared", "fabric", "forge", "neoforge").forEach {
    include(":socketmc-$it")
    project(":socketmc-$it").projectDir = rootDir.resolve("mod/$it")
}

listOf("paper", "spigot").forEach {
    include(":socketmc-$it")
    project(":socketmc-$it").projectDir = rootDir.resolve("plugin/$it")
}

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()

        maven("https://maven.fabricmc.net/")
        maven("https://maven.minecraftforge.net/")
        maven("https://maven.parchmentmc.org")
        maven("https://maven.neoforged.net/releases/")
    }
}