package me.gamercoder215.socketmc.screen;

import me.gamercoder215.socketmc.util.render.text.Text;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a custom screen to be displayed on the client's screen.
 */
public final class CustomScreen extends AbstractScreen {
    @Serial
    private static final long serialVersionUID = 2958020867211288488L;

    private String titleJSON;
    private String narrationMessageJSON;
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

    @Override
    public String getNarrationMessageJSON() {
        if (narrationMessageJSON == null) return titleJSON;
        return narrationMessageJSON;
    }

    /**
     * Sets the narration message of this screen.
     * @param narrationMessageJSON the narration message in JSON format
     */
    public void setNarrationMessageJSON(@Nullable String narrationMessageJSON) {
        this.narrationMessageJSON = narrationMessageJSON;
    }

    /**
     * Sets the narration message of this screen.
     * @param narrationMessage the narration message
     */
    public void setNarrationMessage(@Nullable Text narrationMessage) {
        if (narrationMessage == null)
            this.narrationMessageJSON = null;
        else
            this.narrationMessageJSON = narrationMessage.toJSON();
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

    @Override
    public String toString() {
        return "CustomScreen{" +
                "title='" + titleJSON + '\'' +
                ", narrationMessage='" + narrationMessageJSON + '\'' +
                ", children=" + children +
                '}';
    }
}
