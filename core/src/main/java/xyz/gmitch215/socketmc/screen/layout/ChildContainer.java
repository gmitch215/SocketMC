package xyz.gmitch215.socketmc.screen.layout;

import org.jetbrains.annotations.NotNull;

import java.io.Serial;

/**
 * The default Container for all children in a layout.
 */
public final class ChildContainer extends AbstractLayout.AbstractChildContainer {

    @Serial
    private static final long serialVersionUID = -3175885020423687229L;

    /**
     * Constructs a new ChildContainer with the specified element and settings.
     * @param element  the element
     * @param settings the settings
     */
    public ChildContainer(@NotNull LayoutElement element, @NotNull LayoutSettings settings) {
        super(element, settings);
    }

}
