package xyz.gmitch215.socketmc.util;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.nbt.TagParser;
import org.bukkit.craftbukkit.v1_21_R1.CraftRegistry;
import org.bukkit.craftbukkit.v1_21_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * Utility class to handle {@link NBTTag} for Bukkit.
 */
public final class BukkitNBTTag {

    private BukkitNBTTag() { throw new UnsupportedOperationException("This class cannot be instantiated."); }

    /**
     * Converts a {@link NBTTag} to a Bukkit {@link ItemStack}.
     * @param item The item to convert.
     * @return The converted item.
     */
    @NotNull
    public static NBTTag fromBukkit(@NotNull ItemStack item) {
        String tag = item.getItemMeta().getAsString();
        return NBTTag.fromTag(tag);
    }

    /**
     * Converts a {@link NBTTag} to a Bukkit {@link ItemStack}.
     * @param tag The tag to convert.
     * @return The converted item.
     */
    @NotNull
    public static ItemStack toBukkit(@NotNull NBTTag tag) {
        String nbt = tag.toTag();

        try {
            return CraftItemStack.asBukkitCopy(
                    net.minecraft.world.item.ItemStack.parse(CraftRegistry.getMinecraftRegistry(), TagParser.parseTag(nbt))
                    .orElse(null)
            );
        } catch (CommandSyntaxException e) {
            throw new IllegalArgumentException("Failed to parse NBT", e);
        }
    }

}
