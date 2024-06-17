package me.gamercoder215.socketmc;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import me.gamercoder215.socketmc.config.ModPermission;
import me.gamercoder215.socketmc.spigot.SocketPlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

public interface SocketMC {

    Logger LOGGER = LoggerFactory.getLogger("SocketMC");

    AtomicReference<File> GAME_DIRECTORY = new AtomicReference<>(null);

    static void print(Throwable t) {
        LOGGER.error("[SocketMC] {}", t.getClass().getSimpleName());
        LOGGER.error("-----------");
        LOGGER.error(t.getMessage());
        for (StackTraceElement element : t.getStackTrace()) LOGGER.error("  {}", element.toString());

        if (t.getCause() != null) {
            LOGGER.error("Caused by:");
            print(t.getCause());
        }
    }

    //<editor-fold desc="Gson Configuration" defaultstate="collapsed">

    Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    String CONFIG_PATH = "config/socketmc.json";

    static JsonObject config() {
        try {
            File config = new File(GAME_DIRECTORY.get(), CONFIG_PATH);

            if (!config.exists()) {
                config.getParentFile().mkdirs();
                config.createNewFile();
                return new JsonObject();
            }

            FileReader reader = new FileReader(config);
            return GSON.fromJson(reader, JsonObject.class);
        } catch (IOException e) {
            print(e);
            return new JsonObject();
        }
    }

    static <T> T config(String path, Class<T> type) {
        JsonObject object = config();
        if (!object.has(path)) return null;

        return GSON.fromJson(object.get(path), type);
    }

    static void writeConfig(Consumer<JsonObject> consumer) {
        JsonObject object = config();

        consumer.accept(object);
        String out = GSON.toJson(object);
    }

    static void writeConfig(String out) {
        try {
            File config = new File(GAME_DIRECTORY.get(), CONFIG_PATH);

            if (!config.exists()) {
                config.getParentFile().mkdirs();
                config.createNewFile();
            }

            Files.writeString(config.toPath(), out, StandardCharsets.UTF_8);
        } catch (IOException e) {
            print(e);
        }
    }

    //</editor-fold>

    // Configuration Keys
    String PLUGINS = "plugins";

    static void addPlugin(SocketPlugin plugin) {
        writeConfig(config -> {
            JsonObject plugins = config.getAsJsonObject(PLUGINS);
            if (plugins == null) plugins = new JsonObject();

            JsonObject data = GSON.toJsonTree(plugin).getAsJsonObject();

            plugins.add(plugin.getMainClass(), data);
            config.add(PLUGINS, plugins);
        });
    }

    static void removePlugin(SocketPlugin plugin) {
        writeConfig(config -> {
            JsonObject plugins = config.getAsJsonObject(PLUGINS);
            if (plugins == null) return;

            plugins.remove(plugin.getMainClass());
            config.add(PLUGINS, plugins);
        });
    }

    static List<SocketPlugin> plugins() {
        JsonObject object = config();
        if (!object.has(PLUGINS)) return List.of();

        List<SocketPlugin> plugins = new ArrayList<>();
        JsonObject plugins0 = object.getAsJsonObject(PLUGINS);
        for (String key : plugins0.keySet())
            plugins.add(GSON.fromJson(plugins0.get(key), SocketPlugin.class));

        return plugins;
    }

    static boolean isPermissionEnabled(ModPermission permission) {
        return config("permissions." + permission.name().toLowerCase(), Boolean.class);
    }

    static boolean isPermissionEnabled(SocketPlugin plugin, ModPermission permission) {
        JsonObject config = config();

        JsonObject plugins = config.getAsJsonObject(PLUGINS);
        if (plugins == null) return false;

        JsonObject plugin0 = plugins.getAsJsonObject(plugin.getMainClass());
        if (plugin0 == null) return false;
        if (!plugin0.has("permissions")) return false;

        return plugin0.has("permissions." + permission.name().toLowerCase());
    }

    static void setPermissionEnabled(ModPermission permission, boolean value) {
        String key = "permissions." + permission.name().toLowerCase();
        writeConfig(config -> {
            if (value) config.addProperty(key, value); else config.remove(key);
        });
    }

    static void setPermissionEnabled(SocketPlugin plugin, ModPermission permission, boolean value) {
        String key = "permissions." + permission.name().toLowerCase();
        writeConfig(config -> {
            JsonObject plugins = config.getAsJsonObject(PLUGINS);
            if (plugins == null) plugins = new JsonObject();

            JsonObject plugin0 = plugins.getAsJsonObject(plugin.getMainClass());
            if (plugin0 == null) plugin0 = new JsonObject();

            if (value) plugin0.addProperty(key, value); else plugin0.remove(key);

            plugins.add(plugin.getMainClass(), plugin0);
            config.add(PLUGINS, plugins);
        });
    }


}
