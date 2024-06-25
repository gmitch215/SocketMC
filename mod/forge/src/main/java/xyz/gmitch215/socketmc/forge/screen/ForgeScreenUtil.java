package xyz.gmitch215.socketmc.forge.screen;

import xyz.gmitch215.socketmc.forge.ForgeSocketMC;
import xyz.gmitch215.socketmc.forge.ForgeUtil;
import xyz.gmitch215.socketmc.screen.AbstractScreen;
import xyz.gmitch215.socketmc.screen.DefaultScreen;
import xyz.gmitch215.socketmc.screen.Positionable;
import xyz.gmitch215.socketmc.screen.ScreenWidget;
import xyz.gmitch215.socketmc.screen.ui.CycleButton;
import xyz.gmitch215.socketmc.screen.ui.ImageButton;
import xyz.gmitch215.socketmc.screen.ui.ImageWidget;
import xyz.gmitch215.socketmc.screen.util.Tooltip;
import xyz.gmitch215.socketmc.util.render.text.JsonText;
import xyz.gmitch215.socketmc.util.render.text.Text;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.*;
import net.minecraft.client.gui.screens.*;
import net.minecraft.client.gui.screens.achievement.StatsScreen;
import net.minecraft.client.gui.screens.advancements.AdvancementsScreen;
import net.minecraft.client.gui.screens.options.OptionsScreen;
import net.minecraft.network.chat.Component;

import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

import static xyz.gmitch215.socketmc.forge.ForgeSocketMC.minecraft;

@SuppressWarnings({"unchecked", "rawtypes"})
public final class ForgeScreenUtil {

    private ForgeScreenUtil() {}

    public static AbstractScreen fromMinecraft(Screen screen) {
        if (screen == null) return null;

        AbstractScreen s0 = findDefault(screen);
        if (s0 == null) {
            if (screen instanceof ForgeScreen forge) s0 = forge.handle;
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
                JsonText title = ForgeUtil.toText(s.getTitle());
                JsonText message = ForgeUtil.toText(s.messageText);
                JsonText button = ForgeUtil.toText(s.okButton);

                yield DefaultScreen.alert(title, message);
            }
            case DisconnectedScreen s -> {
                JsonText title = ForgeUtil.toText(s.getTitle());
                JsonText reason = ForgeUtil.toText(s.details.reason());
                JsonText button = ForgeUtil.toText(s.buttonText);

                yield DefaultScreen.disconnected(title, reason);
            }
            case GenericMessageScreen s -> {
                JsonText message = ForgeUtil.toText(s.getTitle());

                yield DefaultScreen.message(message);
            }
            case DeathScreen s -> {
                JsonText cause = ForgeUtil.toText(s.causeOfDeath);
                boolean hardcore = s.hardcore;

                yield DefaultScreen.death(cause, hardcore);
            }
            case ErrorScreen s -> {
                JsonText title = ForgeUtil.toText(s.getTitle());
                JsonText message = ForgeUtil.toText(s.message);

                yield DefaultScreen.error(title, message);
            }
            default -> null;
        };
    }

    static final Button.OnPress BUTTON_PRESS_EVENT = b -> {
        AbstractScreen s0 = fromMinecraft(minecraft.screen);
        if (s0 == null) return;

        ForgeSocketMC.sendEvent(5, Map.of("screen", s0, "button", fromMinecraft(b)));
    };

    static final Checkbox.OnValueChange CHECKBOX_CHANGE_EVENT = (b, state) -> {
        AbstractScreen s0 = fromMinecraft(minecraft.screen);
        if (s0 == null) return;

        ForgeSocketMC.sendEvent(6, Map.of("screen", s0, "button", fromMinecraft(b), "state", state));
    };

    static final BiConsumer<EditBox, String> EDIT_BOX_EVENT = (w, s) -> {
        AbstractScreen s0 = fromMinecraft(minecraft.screen);
        if (s0 == null) return;

        ForgeSocketMC.sendEvent(7, Map.of("screen", s0, "widget", fromMinecraft(w), "text", s));
    };

    static final net.minecraft.client.gui.components.CycleButton.OnValueChange<Object> CYCLE_BUTTON_EVENT = (b, o) -> {
        AbstractScreen s0 = fromMinecraft(minecraft.screen);
        if (s0 == null) return;

        ForgeSocketMC.sendEvent(8, Map.of("screen", s0, "button", fromMinecraft(b), "value", o));
    };

    public static AbstractWidget toMinecraft(Positionable renderable) {
        if (renderable == null) return null;

        int x = renderable.getX();
        int y = renderable.getY();
        int width = renderable.getWidth();
        int height = renderable.getHeight();
        Tooltip tooltip = renderable.getTooltip();

        Component message = renderable instanceof xyz.gmitch215.socketmc.screen.ui.AbstractTextWidget text ? ForgeUtil.fromJson(text.getMessageJSON()) : null;

        AbstractWidget w0 = switch (renderable) {
            // Widgets
            case xyz.gmitch215.socketmc.screen.ui.TextWidget widget -> {
                if (width == -1 || height == -1)
                    yield new StringWidget(x, y, message, minecraft.font);
                else
                    yield new StringWidget(x, y, width, height, message, minecraft.font);
            }
            case xyz.gmitch215.socketmc.screen.ui.FocusedTextWidget widget -> {
                FocusableTextWidget w = new FocusableTextWidget(width, message, minecraft.font, widget.isAlwaysShowBorder(), widget.getPadding());
                w.setPosition(widget.getX(), widget.getY());

                yield w;
            }
            case ImageWidget widget -> {
                net.minecraft.client.gui.components.ImageWidget w = switch (widget.getType()) {
                    case TEXTURE -> net.minecraft.client.gui.components.ImageWidget.texture(width, height, ForgeUtil.toMinecraft(widget.getLocation()), width, height);
                    case SPRITE -> net.minecraft.client.gui.components.ImageWidget.sprite(width, height, ForgeUtil.toMinecraft(widget.getLocation()));
                };
                w.setPosition(x, y);

                yield w;
            }
            case xyz.gmitch215.socketmc.screen.ui.EditTextWidget widget -> {
                EditBox w = new EditBox(minecraft.font, x, y, width, height, message);
                w.setResponder(s -> EDIT_BOX_EVENT.accept(w, s));

                yield w;
            }

            // Buttons
            case xyz.gmitch215.socketmc.screen.ui.TextButton button -> Button.builder(message, BUTTON_PRESS_EVENT).bounds(x, y, width, height).build();
            case ImageButton button ->
                    new net.minecraft.client.gui.components.ImageButton(x, y, width, height, ForgeUtil.toMinecraft(button.getSprite()), BUTTON_PRESS_EVENT);
            case xyz.gmitch215.socketmc.screen.ui.CheckboxButton button -> Checkbox.builder(message, minecraft.font).onValueChange(CHECKBOX_CHANGE_EVENT).pos(x, y).build();
            case xyz.gmitch215.socketmc.screen.ui.SendInstructionButton button -> new ForgeSendInstructionButton(x, y, width, height, message, button.getInstruction());
            case xyz.gmitch215.socketmc.screen.ui.LockButton button -> new LockIconButton(x, y, BUTTON_PRESS_EVENT);
            case CycleButton button -> {
                Function<Object, Component> stringifier = o -> ForgeUtil.fromJson(((Text) button.getStringifier().apply(o)).toJSON());

                yield net.minecraft.client.gui.components.CycleButton
                        .builder(stringifier)
                        .withValues(button.getValues())
                        .create(x, y, width, height, message, CYCLE_BUTTON_EVENT);
            }

            case null, default -> null;
        };
        if (w0 == null) return null;

        w0.setTooltip(ForgeUtil.toMinecraft(tooltip));

        ScreenWidget f0 = (ScreenWidget) w0;
        f0.socketMC$addClickListeners(renderable.getListeners());

        return w0;
    }

    public static Positionable fromMinecraft(AbstractWidget renderable) {
        if (renderable == null) return null;

        int x = renderable.getX();
        int y = renderable.getY();
        int width = renderable.getWidth();
        int height = renderable.getHeight();
        Tooltip tooltip = ForgeUtil.fromMinecraft(renderable.getTooltip());

        Positionable w0 = switch (renderable) {
            // Widgets
            case StringWidget widget -> new xyz.gmitch215.socketmc.screen.ui.TextWidget(x, y, width, height, ForgeUtil.toJson(widget.getMessage()));
            case FocusableTextWidget widget -> new xyz.gmitch215.socketmc.screen.ui.FocusedTextWidget(x, y, width, height, ForgeUtil.toJson(widget.getMessage()), widget.alwaysShowBorder, widget.padding);
            case net.minecraft.client.gui.components.ImageWidget.Sprite widget -> new ImageWidget(x, y, width, height, ImageWidget.Type.SPRITE, ForgeUtil.fromMinecraft(widget.sprite));
            case net.minecraft.client.gui.components.ImageWidget.Texture widget -> new ImageWidget(x, y, width, height, ImageWidget.Type.TEXTURE, ForgeUtil.fromMinecraft(widget.texture));
            case EditBox widget -> new xyz.gmitch215.socketmc.screen.ui.EditTextWidget(x, y, width, height, ForgeUtil.toJson(widget.getMessage()));

            // Buttons
            case net.minecraft.client.gui.components.ImageButton button -> new ImageButton(x, y, width, height, ForgeUtil.fromMinecraft(button.sprites));
            case ForgeSendInstructionButton button -> new xyz.gmitch215.socketmc.screen.ui.SendInstructionButton(x, y, width, height, ForgeUtil.toJson(button.getMessage()), button.instruction);
            case LockIconButton button -> new xyz.gmitch215.socketmc.screen.ui.LockButton(x, y);
            case Button button -> new xyz.gmitch215.socketmc.screen.ui.TextButton(x, y, width, height, ForgeUtil.toJson(button.getMessage()));
            case Checkbox button -> new xyz.gmitch215.socketmc.screen.ui.CheckboxButton(x, y, ForgeUtil.toJson(button.getMessage()));
            case net.minecraft.client.gui.components.CycleButton button -> {
                Function<Object, Text> stringifier = o -> JsonText.raw(ForgeUtil.toJson((Component) button.valueStringifier.apply(o)));
                yield new CycleButton(x, y, width, height, JsonText.raw(ForgeUtil.toJson(button.getMessage())), stringifier, button.values.getDefaultList());
            }

            case null, default -> null;
        };
        if (w0 == null) return null;

        w0.setTooltip(tooltip);

        ScreenWidget f0 = (ScreenWidget) renderable;
        f0.socketMC$getClickListeners().forEach(w0::onClick);

        return w0;
    }

}
