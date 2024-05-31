package me.gamercoder215.socketmc.fabric.machines;

import me.gamercoder215.socketmc.fabric.screen.FabricScreen;
import me.gamercoder215.socketmc.instruction.Instruction;
import me.gamercoder215.socketmc.instruction.InstructionId;
import me.gamercoder215.socketmc.instruction.Machine;
import me.gamercoder215.socketmc.screen.AbstractScreen;
import me.gamercoder215.socketmc.screen.CustomScreen;
import me.gamercoder215.socketmc.screen.DefaultScreen;
import net.minecraft.client.gui.screens.*;
import net.minecraft.client.gui.screens.achievement.StatsScreen;
import net.minecraft.client.gui.screens.advancements.AdvancementsScreen;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static me.gamercoder215.socketmc.fabric.FabricSocketMC.minecraft;

@InstructionId(Instruction.OPEN_SCREEN)
public final class OpenScreenMachine implements Machine {

    private static Screen defaultScreen(@NotNull DefaultScreen screen, @Nullable Screen lastScreen) {
        return switch (screen.getIdentifier()) {
            case "title" -> new TitleScreen();
            case "pause" -> new PauseScreen(true);
            case "options" -> new OptionsScreen(lastScreen, minecraft.options);
            case "share_to_lan" -> new ShareToLanScreen(lastScreen);
            case "advancements" -> new AdvancementsScreen(minecraft.getConnection().getAdvancements(), lastScreen);
            case "stats" -> new StatsScreen(lastScreen, minecraft.player.getStats());

            default -> throw new AssertionError("Unexpected value: " + screen.getIdentifier());
        };
    }

    @Override
    public void onInstruction(@NotNull Instruction instruction) throws Exception {
        AbstractScreen screen = instruction.parameter(0, AbstractScreen.class);

        if (screen instanceof DefaultScreen s) {
            Screen screen0 = defaultScreen(s, minecraft.screen);
            minecraft.execute(() -> minecraft.setScreen(screen0));
        }

        if (screen instanceof CustomScreen s) {
            Screen screen0 = new FabricScreen(s, minecraft.screen);
            minecraft.execute(() -> minecraft.setScreen(screen0));
        }
    }

}
