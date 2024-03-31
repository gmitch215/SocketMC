description = "Joint jar between Fabric and Forge Mod Implementation"

tasks {
    jar {
        listOf(
            project(":socketmc-core"),
            project(":socketmc-fabric"),
            project(":socketmc-forge"),
        ).forEach {
            dependsOn(it.tasks.named("jar"))

            from(it.sourceSets["main"].output)
        }

        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    }
}