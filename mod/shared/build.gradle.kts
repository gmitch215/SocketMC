description = "Shared Mod Sources for SocketMC"

dependencies {
    api(project(":socketmc-core"))

    // Minecraft Client
    compileOnly("org.slf4j:slf4j-api:2.0.9")
    compileOnly("com.google.code.gson:gson:2.10.1")
    compileOnly("com.mojang:text2speech:1.17.9")

    compileOnly("org.lwjgl:lwjgl-tinyfd:3.3.3")
    compileOnly("org.lwjgl:lwjgl-glfw:3.3.3")
}

tasks {
    jar {
        from(project(":socketmc-core").sourceSets["main"].output)
    }
}