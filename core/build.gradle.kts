description = "SocketMC Core Library"

tasks {
    javadoc {
        enabled = true

        sourceSets["main"].allJava.srcDir("src/main/javadoc")

        options {
            require(this is StandardJavadocDocletOptions)

            links("https://hub.spigotmc.org/javadocs/spigot/")
            links("https://javadoc.io/doc/org.jetbrains/annotations-java5/23.0.0/")
        }
    }
}

java {
    withJavadocJar()
    withSourcesJar()
}