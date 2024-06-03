package me.gamercoder215.socketmc.fabric.screen;

import me.gamercoder215.socketmc.fabric.FabricSocketMC;
import me.gamercoder215.socketmc.fabric.FabricUtil;
import me.gamercoder215.socketmc.screen.AbstractScreen;
import me.gamercoder215.socketmc.screen.DefaultScreen;
import me.gamercoder215.socketmc.screen.Positionable;
import me.gamercoder215.socketmc.screen.ui.ImageButton;
import me.gamercoder215.socketmc.screen.ui.ImageWidget;
import me.gamercoder215.socketmc.screen.ui.*;
import me.gamercoder215.socketmc.screen.util.Tooltip;
import me.gamercoder215.socketmc.util.render.text.JsonText;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.*;
import net.minecraft.client.gui.screens.*;
import net.minecraft.client.gui.screens.achievement.StatsScreen;
import net.minecraft.client.gui.screens.advancements.AdvancementsScreen;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.function.BiConsumer;

import static me.gamercoder215.socketmc.fabric.FabricSocketMC.minecraft;

public final class FabricScreenUtil {

    private FabricScreenUtil() {}

    public static AbstractScreen fromMinecraft(Screen screen) {
        if (screen == null) return null;

        AbstractScreen s0 = findDefault(screen);
        if (s0 == null) {
            if (screen instanceof FabricScreen fabric) s0 = fabric.handle;
        }

        return s0;
    }

    public static DefaultScreen findDefault(Screen screen) {
        if (screen == null) return null;

        return switch (screen) {
            case TitleScreen ignored -> DefaultScreen.TITLE;
            case PauseScreen ignored -> DefaultScreen.PAUSE;
            case OptionsScreen ignored -> DefaultScreen.OPTIONS;
            case ShareToLanScreen ignored -> DefaultScreen.SHARE_TO_LAN;
            case AdvancementsScreen ignored -> DefaultScreen.ADVANCEMENTS;
            case StatsScreen ignored -> DefaultScreen.STATS;

            case AlertScreen s -> {
                JsonText title = FabricUtil.toText(s.getTitle());
                JsonText message = FabricUtil.toText(s.messageText);
                JsonText button = FabricUtil.toText(s.okButton);

                yield DefaultScreen.alert(title, message);
            }
            case DisconnectedScreen s -> {
                JsonText title = FabricUtil.toText(s.getTitle());
                JsonText reason = FabricUtil.toText(s.reason);
                JsonText button = FabricUtil.toText(s.buttonText);

                yield DefaultScreen.disconnected(title, reason);
            }
            case GenericMessageScreen s -> {
                JsonText message = FabricUtil.toText(s.getTitle());

                yield DefaultScreen.message(message);
            }
            case DeathScreen s -> {
                JsonText cause = FabricUtil.toText(s.causeOfDeath);
                boolean hardcore = s.hardcore;

                yield DefaultScreen.death(cause, hardcore);
            }
            case ErrorScreen s -> {
                JsonText title = FabricUtil.toText(s.getTitle());
                JsonText message = FabricUtil.toText(s.message);

                yield DefaultScreen.error(title, message);
            }
            default -> null;
        };
    }

    static final Button.OnPress BUTTON_PRESS_EVENT = b -> {
        AbstractScreen s0 = fromMinecraft(minecraft.screen);
        if (s0 == null) return;

        FabricSocketMC.sendEvent(5, Map.of("screen", s0, "button", fromMinecraft(b)));
    };

    static final Checkbox.OnValueChange CHECKBOX_CHANGE_EVENT = (b, state) -> {
        AbstractScreen s0 = fromMinecraft(minecraft.screen);
        if (s0 == null) return;

        FabricSocketMC.sendEvent(6, Map.of("screen", s0, "button", fromMinecraft(b), "state", state));
    };

    static final BiConsumer<EditBox, String> EDIT_BOX_EVENT = (w, s) -> {
        AbstractScreen s0 = fromMinecraft(minecraft.screen);
        if (s0 == null) return;

        FabricSocketMC.sendEvent(7, Map.of("screen", s0, "widget", fromMinecraft(w), "text", s));
    };

    public static AbstractWidget toMinecraft(Positionable renderable) {
        if (renderable == null) return null;

        int x = renderable.getX();
        int y = renderable.getY();
        int width = renderable.getWidth();
        int height = renderable.getHeight();
        Tooltip tooltip = renderable.getTooltip();

        Component message = renderable instanceof AbstractTextWidget text ? FabricUtil.fromJson(text.getMessageJSON()) : null;

        AbstractWidget w0 = switch (renderable) {
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
            case EditTextWidget widget -> {
                EditBox w = new EditBox(minecraft.font, x, y, width, height, message);
                w.setResponder(s -> EDIT_BOX_EVENT.accept(w, s));

                yield w;
            }

            // Buttons
            case TextButton button -> Button.builder(message, BUTTON_PRESS_EVENT).bounds(x, y, width, height).build();
            case ImageButton button ->
                    new net.minecraft.client.gui.components.ImageButton(x, y, width, height, FabricUtil.toMinecraft(button.getSprite()), BUTTON_PRESS_EVENT);
            case CheckboxButton button -> Checkbox.builder(message, minecraft.font).onValueChange(CHECKBOX_CHANGE_EVENT).pos(x, y).build();
            case SendInstructionButton button -> new FabricSendInstructionButton(x, y, width, height, message, button.getInstruction());
            case LockButton button -> new LockIconButton(x, y, BUTTON_PRESS_EVENT);

            case null, default -> throw new AssertionError("Unexpected value: " + renderable);
        };

        w0.setTooltip(FabricUtil.toMinecraft(tooltip));
        return w0;
    }

    public static Positionable fromMinecraft(AbstractWidget renderable) {
        if (renderable == null) return null;

        int x = renderable.getX();
        int y = renderable.getY();
        int width = renderable.getWidth();
        int height = renderable.getHeight();
        Tooltip tooltip = FabricUtil.fromMinecraft(renderable.getTooltip());

        Positionable w0 = switch (renderable) {
            // Widgets
            case StringWidget widget -> new TextWidget(x, y, width, height, FabricUtil.toJson(widget.getMessage()));
            case FocusableTextWidget widget -> new FocusedTextWidget(x, y, width, height, FabricUtil.toJson(widget.getMessage()), widget.alwaysShowBorder, widget.padding);
            case net.minecraft.client.gui.components.ImageWidget.Sprite widget -> new ImageWidget(x, y, width, height, ImageWidget.Type.SPRITE, FabricUtil.fromMinecraft(widget.sprite));
            case net.minecraft.client.gui.components.ImageWidget.Texture widget -> new ImageWidget(x, y, width, height, ImageWidget.Type.TEXTURE, FabricUtil.fromMinecraft(widget.texture));
            case EditBox widget -> new EditTextWidget(x, y, width, height, FabricUtil.toJson(widget.getMessage()));

            // Buttons
            case net.minecraft.client.gui.components.ImageButton button -> new ImageButton(x, y, width, height, FabricUtil.fromMinecraft(button.sprites));
            case FabricSendInstructionButton button -> new SendInstructionButton(x, y, width, height, FabricUtil.toJson(button.getMessage()), button.instruction);
            case LockIconButton button -> new LockButton(x, y);
            case Button button -> new TextButton(x, y, width, height, FabricUtil.toJson(button.getMessage()));
            case Checkbox button -> new CheckboxButton(x, y, FabricUtil.toJson(button.getMessage()));

            case null, default -> throw new AssertionError("Unexpected value: " + renderable);
        };

        w0.setTooltip(tooltip);
        return w0;
    }

}
