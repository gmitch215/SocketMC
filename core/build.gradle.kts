description = "SocketMC Core Library"

tasks {
    javadoc {
        enabled = true

        val s = File.separator
        val packages = sourceSets["main"].allJava.map { it.path }
            .map { it
                .substringAfter("src${s}main${s}java${s}")
                .substringBeforeLast(s)
            }
            .toSet()
            .filter { it.isNotEmpty() }
        for (pkg in packages) {
            val packageInfo = file("src/main/javadoc/$pkg/package-info.java")
            if (!packageInfo.exists())
                throw IllegalStateException("Package ${pkg.replace(s, ".")} does not have a package-info.java file")
        }

        sourceSets["main"].allJava.srcDir("src/main/javadoc")

        options {
            require(this is StandardJavadocDocletOptions)
            showFromProtected()

            links("https://javadoc.io/doc/org.jetbrains/annotations-java5/23.0.0/")
        }
    }
}

java {
    withJavadocJar()
    withSourcesJar()
}

// Bundled with Minecraft
dependencies {
    compileOnly("org.joml:joml:1.10.6")
}