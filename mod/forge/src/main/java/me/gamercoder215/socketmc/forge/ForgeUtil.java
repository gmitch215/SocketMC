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
        return Component.Serializer.fromJson(json, minecraft.level.registryAccess());
    }

    public static ResourceLocation toMinecraft(Identifier identifier) {
        return new ResourceLocation(identifier.getNamespace(), identifier.getPath());
    }

    public static WidgetSprites toMinecraft(Sprite sprite) {
        return new WidgetSprites(
                toMinecraft(sprite.getEnabled()),
                toMinecraft(sprite.getEnabledHovered()),
                toMinecraft(sprite.getDisabled()),
                toMinecraft(sprite.getDisabledHovered())
        );
    }

    public static net.minecraft.client.gui.components.Tooltip toMinecraft(Tooltip tooltip) {
        return net.minecraft.client.gui.components.Tooltip.create(
                fromJson(tooltip.getTooltipJSON()),
                fromJson(tooltip.getNarrationMessageJSON())
        );
    }

}