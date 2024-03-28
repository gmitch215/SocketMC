val versions = listOf(
    "1_20_R1",
    "1_20_R2",
    "1_20_R3"
)

dependencies {
    compileOnly("org.spigotmc:spigot-api") {
        version {
            strictly("1.20.2-R0.1-SNAPSHOT")
        }
    }

    // API
    api(project(":socketmc-core"))
    versions.forEach {
        api(project(":socketmc-nms-$it"))
    }
}

java {
    withSourcesJar()
    withJavadocJar()
}