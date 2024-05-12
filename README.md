# 🔌 SocketMC

> Minecraft Server-to-Client Direct Communication Library

[Notice](./NOTICE.md) | [License](./LICENSE)

## Background

"Client-side only" is a term widely used when advancing in plugin development, where you're limited in interacting with player's own client. The goal of this library
is to solve this issue: create a mod that can communicate with your plugin. That is exactly what SocketMC does! Send your own instructions to clients using our
simple and efficient API.

## ❓ Why?

- **Extensive**: SocketMC provides thorough documentation and examples to help you get started.
- **Robust**: SocketMC is built with performance and reliability in mind, especially since it is version-dependent.
- **Transparent**: SocketMC is open-source, meaning you can see how it works and contribute to its development.

# 🚚 Features

- Client Instructions
  - Draw Shapes
  - Draw Text
  - Play Audio
  - And More!
- Client Events
  - Player Type and Click Events

## 📥 Installation

[![GitHub branch checks state](https://github.com/gmitch215/SocketMC/actions/workflows/build.yml/badge.svg)](https://github.com/gmitch215/SocketMC/actions/workflows/build.yml)
![GitHub](https://img.shields.io/github/license/gmitch215/SocketMC)
![GitHub issues](https://img.shields.io/github/issues/gmitch215/SocketMC)

### Prerequisites

All players on your server must have the SocketMC mod installed. 
You can download them from the following locations:

- [Modrinth](https://modrinth.com/mod/socketmc)
- [Jenkins CI](https://ci.codemc.io/job/gmitch215/job/SocketMC/)
- [GitHub Releases](https://github.com/gmitch215/SocketMC/releases/latest)

### In your Plugin

![GitHub release (latest by date)](https://img.shields.io/github/v/release/gmitch215/SocketMC)


<details>
    <summary>Maven</summary>

```xml
<project>
    
    <!-- Import CodeMC Repo -->
    
    <repositories>
        <repository>
            <id>codemc-snapshots</id>
            <url>https://repo.codemc.io/repository/maven-snapshots/</url>
        </repository>
    </repositories>
    
    <dependencies>
        <dependency>
            <groupId>me.gamercoder215.socketmc</groupId>
            <artifactId>socketmc-spigot</artifactId>
            <version>[VERSION]</version>
        </dependency>
        
        <!-- Alternatively, use the Paper Build -->
        <dependency>
            <groupId>me.gamercoder215.socketmc</groupId>
            <artifactId>socketmc-paper</artifactId>
            <version>[VERSION]</version>
        </dependency>
    </dependencies>
    
</project>
```
</details>

<details>
    <summary>Gradle (Groovy)</summary>

```gradle
repositories {
    maven { url 'https://repo.codemc.io/repository/maven-snapshots/' }
}

dependencies {
    implementation 'me.gamercoder215.socketmc:socketmc-spigot:[VERSION]'
    
    // Alternatively, use the Paper Build
    implementation 'me.gamercoder215.socketmc:socketmc-paper:[VERSION]'
}
```
</details>

<details>
    <summary>Gradle (Kotlin DSL)</summary>

```kotlin
repositories {
    maven(url = "https://repo.codemc.io/repository/maven-snapshots/")
}

dependencies {
    implementation("me.gamercoder215.socketmc:socketmc-spigot:[VERSION]")
    
    // Alternatively, use the Paper Build
    implementation("me.gamercoder215.socketmc:socketmc-paper:[VERSION]")
}
```
</details>

## 📺 Example

```java
Player player = Bukkit.getPlayer("gmitch215");
SocketPlayer sp = new SocketPlayer(player);

// Specify X and Y, Text, and Duration
sp.sendInstruction(Instruction.drawText(100, 100, "Hello World", 5, TimeUnit.SECONDS));
```

Output:

![Example](https://i.imgur.com/VZmqWLC.gif)