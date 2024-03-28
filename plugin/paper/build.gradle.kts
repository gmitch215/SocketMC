dependencies {
    compileOnly("io.papermc.paper:paper-api") {
        version {
            strictly("1.20.2-R0.1-SNAPSHOT")
        }
    }

    // API
    api(project(":socketmc-core"))
    api(project(":socketmc-spigot"))
}