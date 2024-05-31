package me.gamercoder215.socketmc.fabric.screen;

import me.gamercoder215.socketmc.fabric.FabricSocketMC;
import me.gamercoder215.socketmc.fabric.FabricUtil;
import me.gamercoder215.socketmc.fabric.machines.FabricMachineFinder;
import me.gamercoder215.socketmc.instruction.Instruction;
import me.gamercoder215.socketmc.screen.Positionable;
import me.gamercoder215.socketmc.screen.ui.*;
import me.gamercoder215.socketmc.screen.ui.ImageButton;
import me.gamercoder215.socketmc.screen.ui.ImageWidget;
import me.gamercoder215.socketmc.screen.util.Tooltip;
import net.minecraft.client.gui.components.*;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;

import static me.gamercoder215.socketmc.fabric.FabricSocketMC.minecraft;

public final class FabricScreenUtil {

    private FabricScreenUtil() {}

    private static final Button.OnPress NO_PRESS = b -> {};

    public static AbstractWidget convert(@NotNull Positionable renderable) {
        int x = renderable.getX();
        int y = renderable.getY();
        int width = renderable.getWidth();
        int height = renderable.getHeight();
        Tooltip tooltip = renderable.getTooltip();

        Component message = renderable instanceof AbstractTextWidget text ? FabricUtil.fromJson(text.getMessageJSON()) : null;

        return switch (renderable) {
            // Widgets
            case TextWidget widget -> {
                if (width == -1 || height == -1)
                    yield new StringWidget(x, y, message, minecraft.font);
                else
                    yield new StringWidget(x, y, width, height, message, minecraft.font);
            }
            case FocusedTextWidget widget -> {
                FocusableTextWidget w = new FocusableTextWidget(width, message, minecraft.font, widget.isAlwaysShowBorder(), widget.getPadding());
                w.setPosition(widget.getX(), widget.getY());

                yield w;
            }
            case ImageWidget widget -> {
                net.minecraft.client.gui.components.ImageWidget w = switch (widget.getType()) {
                    case TEXTURE -> net.minecraft.client.gui.components.ImageWidget.texture(width, height, FabricUtil.toMinecraft(widget.getLocation()), width, height);
                    case SPRITE -> net.minecraft.client.gui.components.ImageWidget.sprite(width, height, FabricUtil.toMinecraft(widget.getLocation()));
                };
                w.setPosition(x, y);

                yield w;
            }
            case EditTextWidget widget -> new EditBox(minecraft.font, x, y, width, height, message);
            // Buttons
            case TextButton button -> Button.builder(message, NO_PRESS).bounds(x, y, width, height).build();
            case ImageButton button ->
                    new net.minecraft.client.gui.components.ImageButton(x, y, width, height, FabricUtil.toMinecraft(button.getSprite()), NO_PRESS);
            case CheckboxButton button -> Checkbox.builder(message, minecraft.font).pos(x, y).build();
            case SendInstructionButton button -> Button.builder(message, b -> {
                Instruction i = button.getInstruction();

                try {
                    FabricMachineFinder.getMachine(i.getId()).onInstruction(i);
                } catch (Exception e) {
                    FabricSocketMC.print(e);
                }
            }).bounds(x, y, width, height).build();

            case null, default -> throw new AssertionError("Unexpected value: " + renderable);
        };
    }

}
