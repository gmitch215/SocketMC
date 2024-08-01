package xyz.gmitch215.socketmc.fabric;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.nbt.TagParser;
import net.minecraft.world.item.ItemStack;
import xyz.gmitch215.socketmc.screen.util.Sprite;
import xyz.gmitch215.socketmc.screen.util.Tooltip;
import xyz.gmitch215.socketmc.util.Identifier;
import xyz.gmitch215.socketmc.util.NBTTag;
import xyz.gmitch215.socketmc.util.render.text.JsonText;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import static xyz.gmitch215.socketmc.fabric.FabricSocketMC.minecraft;

public final class FabricUtil {

    private FabricUtil() {}

    public static Component fromJson(String json) {
        if (json == null) return Component.empty();
        return Component.Serializer.fromJson(json, minecraft.level.registryAccess());
    }

    public static String toJson(Component component) {
        if (component == null) return "";
        return Component.Serializer.toJson(component, minecraft.level.registryAccess());
    }

    public static JsonText toText(Component component) {
        if (component == null) return JsonText.empty();
        return JsonText.raw(toJson(component));
    }

    public static Identifier fromMinecraft(ResourceLocation resourceLocation) {
        if (resourceLocation == null) return null;
        return new Identifier(resourceLocation.getNamespace(), resourceLocation.getPath());
    }

    public static ResourceLocation toMinecraft(Identifier identifier) {
        if (identifier == null) return null;
        return ResourceLocation.fromNamespaceAndPath(identifier.getNamespace(), identifier.getPath());
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

    public static ItemStack toItem(NBTTag tag) {
        try {
            return ItemStack.parse(minecraft.level.registryAccess(), TagParser.parseTag(tag.toTag())).orElse(ItemStack.EMPTY);
        } catch (CommandSyntaxException e) {
            return ItemStack.EMPTY;
        }
    }

}
