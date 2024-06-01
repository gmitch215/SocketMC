package me.gamercoder215.socketmc.screen;

import org.jetbrains.annotations.NotNull;

import java.io.Serial;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents default screens built-in to the client.
 */
public final class DefaultScreen extends AbstractScreen {
    @Serial
    private static final long serialVersionUID = 8336967742008601990L;

    /**
     * Represents the title screen.
     */
    public static final DefaultScreen TITLE = new DefaultScreen("title");

    /**
     * Represents the pause menu.
     */
    public static final DefaultScreen PAUSE = new DefaultScreen("pause");

    /**
     * Represents the options menu.
     */
    public static final DefaultScreen OPTIONS = new DefaultScreen("options");

    /**
     * Represents the share to Local-Access-Network (LAN) menu.
     */
    public static final DefaultScreen SHARE_TO_LAN = new DefaultScreen("share_to_lan");

    /**
     * Represents the advancements screen.
     */
    public static final DefaultScreen ADVANCEMENTS = new DefaultScreen("advancements");

    /**
     * Represents the player statistics screen.
     */
    public static final DefaultScreen STATS = new DefaultScreen("stats");

    private final String identifier;

    private DefaultScreen(String identifier) {
        this.identifier = identifier;
    }

    /**
     * Gets the identifier for this default screen.
     * @return Screen Identifier
     */
    @NotNull
    public String getIdentifier() {
        return identifier;
    }

    @Override
    @NotNull
    public String getTitleJSON() {
        throw new UnsupportedOperationException("Default screens do not have titles accessible here");
    }

    /**
     * Gets all the default screens.
     * @return Default Screens
     */
    @NotNull
    public static Set<DefaultScreen> getAllScreens() {
        Set<DefaultScreen> screens = new HashSet<>();

        try {
            for (Field f : DefaultScreen.class.getDeclaredFields()) {
                if (!Modifier.isStatic(f.getModifiers())) continue;
                if (!Modifier.isFinal(f.getModifiers())) continue;
                if (!DefaultScreen.class.isAssignableFrom(f.getType())) continue;

                screens.add((DefaultScreen) f.get(null));
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        return screens;
    }

    @Override
    public String toString() {
        return "DefaultScreen{'" + identifier + "'}";
    }
}
