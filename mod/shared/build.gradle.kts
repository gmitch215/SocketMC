description = "Shared Mod Sources for SocketMC"

dependencies {
    api(project(":socketmc-core"))

    // Minecraft Client
    compileOnly("org.slf4j:slf4j-api:2.0.9")
    compileOnly("com.google.code.gson:gson:2.10.1")
}

tasks {
    jar {
        from(project(":socketmc-core").sourceSets["main"].output)
    }
}