rootProject.name = "SocketMC"

include(":socketmc-core")
project(":socketmc-core").projectDir = rootDir.resolve("core")

listOf("fabric", "forge").forEach {
    include(":socketmc-$it")
    project(":socketmc-$it").projectDir = rootDir.resolve("mod/$it")
}

listOf("paper", "spigot").forEach {
    include(":socketmc-$it")
    project(":socketmc-$it").projectDir = rootDir.resolve("plugin/$it")
}

listOf(
    "1_20_R1",
    "1_20_R2",
    "1_20_R3"
).forEach {
    include(":socketmc-nms-$it")
    project(":socketmc-nms-$it").projectDir = rootDir.resolve("plugin/nms/$it")
}