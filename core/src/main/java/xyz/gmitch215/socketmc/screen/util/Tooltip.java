package xyz.gmitch215.socketmc.screen.util;

import xyz.gmitch215.socketmc.screen.Narratable;
import xyz.gmitch215.socketmc.util.render.text.Text;
import org.jetbrains.annotations.NotNull;

import java.io.Serial;

/**
 * Represents a Tooltip message.
 */
public final class Tooltip implements Narratable {

    @Serial
    private static final long serialVersionUID = -5588112512618273311L;

    private String tooltipJSON;
    private String narrationMessageJSON;

    /**
     * Constructs a new, empty Tooltip.
     */
    public Tooltip() {}

    /**
     * Constructs a new Tooltip.
     * @param tooltip Tooltip Message
     * @throws IllegalArgumentException if tooltip is null
     */
    public Tooltip(@NotNull Text tooltip) throws IllegalArgumentException {
        this(tooltip, tooltip);
    }

    /**
     * Constructs a new Tooltip.
     * @param tooltip Tooltip Message
     * @param narratableMessage Narration Message
     * @throws IllegalArgumentException if tooltip or narratableMessage is null
     */
    public Tooltip(@NotNull Text tooltip, @NotNull Text narratableMessage) throws IllegalArgumentException {
        if (tooltip == null) throw new IllegalArgumentException("tooltip cannot be null");
        if (narratableMessage == null) throw new IllegalArgumentException("narratableMessage cannot be null");

        this.tooltipJSON = tooltip.toJSON();
        this.narrationMessageJSON = narratableMessage.toJSON();
    }

    /**
     * Constructs a new Tooltip.
     * @param tooltipJSON Tooltip Message JSON
     * @param narrationMessageJSON Narration Message JSON
     * @throws IllegalArgumentException if tooltipJSON or narrationMessageJSON is null
     */
    public Tooltip(@NotNull String tooltipJSON, @NotNull String narrationMessageJSON) throws IllegalArgumentException {
        if (tooltipJSON == null) throw new IllegalArgumentException("tooltipJSON cannot be null");
        if (narrationMessageJSON == null) throw new IllegalArgumentException("narrationMessageJSON cannot be null");

        this.tooltipJSON = tooltipJSON;
        this.narrationMessageJSON = narrationMessageJSON;
    }

    @NotNull
    public String getTooltipJSON() {
        return tooltipJSON;
    }

    /**
     * Sets the tooltip message for this Tooltip.
     * @param tooltip Tooltip Message
     */
    public void setTooltip(@NotNull Text tooltip) {
        if (tooltip == null) throw new IllegalArgumentException("tooltip cannot be null");
        this.tooltipJSON = tooltip.toJSON();
    }

    /**
     * Sets the tooltip message JSON for this Tooltip.
     * @param tooltipJSON Tooltip Message JSON
     * @throws IllegalArgumentException if tooltipJSON is null
     */
    public void setTooltipJSON(@NotNull String tooltipJSON) throws IllegalArgumentException {
        if (tooltipJSON == null) throw new IllegalArgumentException("tooltipJSON cannot be null");
        this.tooltipJSON = tooltipJSON;
    }

    @Override
    public String getNarrationMessageJSON() {
        return narrationMessageJSON;
    }

    /**
     * Sets the Narration Message for this Tooltip.
     * @param narrationMessage Narration Message
     * @throws IllegalArgumentException if narrationMessage is null
     */
    public void setNarrationMessage(@NotNull Text narrationMessage) throws IllegalArgumentException {
        if (narrationMessage == null) throw new IllegalArgumentException("narrationMessage cannot be null");
        this.narrationMessageJSON = narrationMessage.toJSON();
    }

    /**
     * Sets the Narration Message JSON for this Tooltip.
     * @param narrationMessageJSON Narration Message JSON
     * @throws IllegalArgumentException if narrationMessageJSON is null
     */
    public void setNarrationMessageJSON(@NotNull String narrationMessageJSON) throws IllegalArgumentException {
        if (narrationMessageJSON == null) throw new IllegalArgumentException("narrationMessageJSON cannot be null");
        this.narrationMessageJSON = narrationMessageJSON;
    }
}
