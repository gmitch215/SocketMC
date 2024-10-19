package xyz.gmitch215.socketmc.screen;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Unmodifiable;
import xyz.gmitch215.socketmc.screen.layout.Layout;
import xyz.gmitch215.socketmc.util.render.text.Text;

import java.io.Serial;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a custom screen to be displayed on the client's screen.
 */
public final class CustomScreen extends AbstractScreen {
    @Serial
    private static final long serialVersionUID = 2958020867211288488L;

    private String titleJSON;
    private String narrationMessageJSON;
    private ScreenBackground background = ScreenBackground.DEFAULT;
    private Layout layout;
    private boolean closeableOnEscape = true;

    private final List<Positionable> children = new ArrayList<>();
    private final Map<String, Object> attributes = new HashMap<>();

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
     * @throws IllegalArgumentException if the child is null or if an element already exists at the same position
     */
    public void addChild(@NotNull Positionable child) throws IllegalArgumentException {
        if (child == null) throw new IllegalArgumentException("Child cannot be null");
        if (children.stream().anyMatch(c -> c.inSamePosition(child))) throw new IllegalArgumentException("An element already exists at this position (" + child.getX() + ", " + child.getY() + ")");

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

    /**
     * Gets the background of this screen.
     * @return Screen Background
     */
    @NotNull
    public ScreenBackground getBackground() {
        return background;
    }

    /**
     * Sets the background of this screen.
     * @param background Screen Background
     * @throws IllegalArgumentException if the background is null
     */
    public void setBackground(@NotNull ScreenBackground background) throws IllegalArgumentException {
        if (background == null) throw new IllegalArgumentException("Background cannot be null");
        this.background = background;
    }

    /**
     * Gets the layout of this screen.
     * @return Layout
     */
    @Nullable
    public Layout getLayout() {
        return layout;
    }

    /**
     * Sets the layout of this screen. Setting it to null will not remove the elements from the layout.
     * @param layout Layout
     */
    public void setLayout(@Nullable Layout layout) {
        this.layout = layout;
        if (layout != null) {
            layout.visitWidgets(this::addChild);
            layout.arrangeElements();
        }
    }

    /**
     * Gets whether this screen can be closed with the escape key.
     * @return true if screen can be closed with escape
     */
    public boolean isCloseableOnEscape() {
        return closeableOnEscape;
    }

    /**
     * Sets whether this screen can be closed with the escape key.
     * @param closeableOnEscape true if screen can be closed with escape
     */
    public void setCloseableOnEscape(boolean closeableOnEscape) {
        this.closeableOnEscape = closeableOnEscape;
    }

    /**
     * Gets an immutable copy of the attributes of this screen.
     * @return Map of Attributes
     */
    @Unmodifiable
    @NotNull
    public Map<String, Object> getAttributes() {
        return Map.copyOf(attributes);
    }

    /**
     * Gets an attribute from this screen.
     * @param key the key of the attribute
     * @return the attribute value, or null if the attribute does not exist
     * @throws IllegalArgumentException if the key is null
     */
    @Nullable
    public Object getAttribute(@NotNull String key) throws IllegalArgumentException {
        if (key == null) throw new IllegalArgumentException("Key cannot be null");
        return attributes.get(key);
    }

    /**
     * Gets an attribute from this screen.
     * @param key the key of the attribute
     * @param def the default value if the attribute does not exist
     * @return the attribute value, or the default value if the attribute does not exist
     * @throws IllegalArgumentException if the key is null
     */
    public Object getAttribute(@NotNull String key, @Nullable Object def) throws IllegalArgumentException {
        if (key == null) throw new IllegalArgumentException("Key cannot be null");
        return attributes.getOrDefault(key, def);
    }

    /**
     * Gets an attribute from this screen.
     * @param key the key of the attribute
     * @param type the type of the attribute
     * @return the attribute value, or null if the attribute does not exist
     * @param <T> the type of the attribute
     */
    @Nullable
    public <T> T getAttribute(@NotNull String key, @NotNull Class<T> type) {
        if (key == null) throw new IllegalArgumentException("Key cannot be null");
        if (type == null) throw new IllegalArgumentException("Type cannot be null");

        return type.cast(attributes.get(key));
    }

    /**
     * Gets an attribute from this screen.
     * @param key the key of the attribute
     * @param type the type of the attribute
     * @param def the default value if the attribute does not exist
     * @return the attribute value, or the default value if the attribute does not exist
     * @param <T> the type of the attribute
     */
    @Nullable
    public <T> T getAttribute(@NotNull String key, @NotNull Class<T> type, @Nullable T def) {
        if (key == null) throw new IllegalArgumentException("Key cannot be null");
        if (type == null) throw new IllegalArgumentException("Type cannot be null");

        return type.cast(attributes.getOrDefault(key, def));
    }

    /**
     * Sets an attribute for this screen.
     * @param key the key of the attribute
     * @param value the value of the attribute
     * @throws IllegalArgumentException if the key is null
     */
    public void setAttribute(@NotNull String key, @Nullable Object value) throws IllegalArgumentException {
        if (key == null) throw new IllegalArgumentException("Key cannot be null");

        if (value == null) attributes.remove(key);
        else attributes.put(key, value);
    }

    @Override
    @NotNull
    public String toString() {
        return "CustomScreen{" +
                "title='" + titleJSON + '\'' +
                ", narrationMessage='" + narrationMessageJSON + '\'' +
                ", children=" + children +
                ", background=" + background +
                ", layout=" + layout +
                ", closeableOnEscape=" + closeableOnEscape +
                ", attributes=" + attributes +
                '}';
    }
}
