package me.gamercoder215.socketmc.forge;

import me.gamercoder215.socketmc.screen.util.Sprite;
import me.gamercoder215.socketmc.screen.util.Tooltip;
import me.gamercoder215.socketmc.util.Identifier;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import static me.gamercoder215.socketmc.forge.ForgeSocketMC.minecraft;

public final class ForgeUtil {

    private ForgeUtil() {}

    public static Component fromJson(String json) {
        if (json == null) return Component.empty();
        return Component.Serializer.fromJson(json, minecraft.level.registryAccess());
    }

    public static String toJson(Component component) {
        if (component == null) return "";
        return Component.Serializer.toJson(component, minecraft.level.registryAccess());
    }

    public static Identifier fromMinecraft(ResourceLocation resourceLocation) {
        if (resourceLocation == null) return null;
        return new Identifier(resourceLocation.getNamespace(), resourceLocation.getPath());
    }

    public static ResourceLocation toMinecraft(Identifier identifier) {
        if (identifier == null) return null;
        return new ResourceLocation(identifier.getNamespace(), identifier.getPath());
    }

    public static Sprite fromMinecraft(WidgetSprites widgetSprites) {
        return new Sprite(
                fromMinecraft(widgetSprites.enabled()),
                fromMinecraft(widgetSprites.enabledFocused()),
                fromMinecraft(widgetSprites.disabled()),
                fromMinecraft(widgetSprites.disabledFocused())
        );
    }

    public static WidgetSprites toMinecraft(Sprite sprite) {
        return new WidgetSprites(
                toMinecraft(sprite.getEnabled()),
                toMinecraft(sprite.getEnabledHovered()),
                toMinecraft(sprite.getDisabled()),
                toMinecraft(sprite.getDisabledHovered())
        );
    }

    public static Tooltip fromMinecraft(net.minecraft.client.gui.components.Tooltip tooltip) {
        if (tooltip == null) return null;

        return new Tooltip(
                toJson(tooltip.message),
                toJson(tooltip.narration)
        );
    }

    public static net.minecraft.client.gui.components.Tooltip toMinecraft(Tooltip tooltip) {
        if (tooltip == null) return null;

        return net.minecraft.client.gui.components.Tooltip.create(
                fromJson(tooltip.getTooltipJSON()),
                fromJson(tooltip.getNarrationMessageJSON())
        );
    }

}
