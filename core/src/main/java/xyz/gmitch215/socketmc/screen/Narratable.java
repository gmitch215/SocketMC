package xyz.gmitch215.socketmc.screen;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

/**
 * Represents a screen element that can be spoken by the narrator.
 */
public interface Narratable extends Serializable {

    /**
     * Gets the JSON message that represents the narration of this element. By default, this is the title of the element.
     * @return the JSON message
     */
    @NotNull
    String getNarrationMessageJSON();

}
