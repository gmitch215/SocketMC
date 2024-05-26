package me.gamercoder215.socketmc.screen;

import me.gamercoder215.socketmc.instruction.util.Text;
import me.gamercoder215.socketmc.screen.ui.Positionable;
import org.jetbrains.annotations.NotNull;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a custom screen to be displayed on the client's screen.
 */
public final class CustomScreen extends Screen {
    @Serial
    private static final long serialVersionUID = 2958020867211288488L;

    private String titleJSON;
    private final List<Positionable> children = new ArrayList<>();

    /**
     * Constructs a new screen with the given title.
     * @param title the title
     */
    public CustomScreen(@NotNull Text title) {
        if (title == null) throw new IllegalArgumentException("Title cannot be null");
        this.titleJSON = title.toJSON();
    }

    @Override
    public String getTitleJSON() {
        return titleJSON;
    }

    /**
     * Sets the title of this screen.
     * @param titleJSON the title in JSON format
     */
    public void setTitleJSON(@NotNull String titleJSON) {
        if (titleJSON == null) throw new IllegalArgumentException("Title cannot be null");
        this.titleJSON = titleJSON;
    }

    /**
     * Sets the title of this screen.
     * @param title the title
     */
    public void setTitle(@NotNull Text title) {
        if (title == null) throw new IllegalArgumentException("Title cannot be null");
        this.titleJSON = title.toJSON();
    }

    /**
     * Adds a child to this screen.
     * @param child the child element
     */
    public void addChild(@NotNull Positionable child) {
        if (child == null) throw new IllegalArgumentException("Child cannot be null");
        children.add(child);
    }

    /**
     * Removes a child from this screen.
     * @param child the child element
     */
    public void removeChild(@NotNull Positionable child) {
        if (child == null) throw new IllegalArgumentException("Child cannot be null");
        children.remove(child);
    }

    /**
     * Removes all children from this screen.
     */
    public void clearChildren() {
        children.clear();
    }

    /**
     * Gets an immutable copy of the children in this screen.
     * @return List of Screen Elements
     */
    @NotNull
    public List<Positionable> getChildren() {
        return List.copyOf(children);
    }
}
