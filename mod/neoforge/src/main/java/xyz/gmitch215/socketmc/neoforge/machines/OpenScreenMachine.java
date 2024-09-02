package xyz.gmitch215.socketmc.neoforge.machines;

import net.minecraft.client.gui.screens.*;
import net.minecraft.client.gui.screens.achievement.StatsScreen;
import net.minecraft.client.gui.screens.advancements.AdvancementsScreen;
import net.minecraft.client.gui.screens.options.OptionsScreen;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.gmitch215.socketmc.instruction.Instruction;
import xyz.gmitch215.socketmc.instruction.InstructionId;
import xyz.gmitch215.socketmc.instruction.Machine;
import xyz.gmitch215.socketmc.neoforge.NeoForgeUtil;
import xyz.gmitch215.socketmc.neoforge.screen.NeoForgeScreen;
import xyz.gmitch215.socketmc.screen.AbstractScreen;
import xyz.gmitch215.socketmc.screen.CustomScreen;
import xyz.gmitch215.socketmc.screen.DefaultScreen;

import static xyz.gmitch215.socketmc.neoforge.NeoForgeSocketMC.minecraft;

@InstructionId(Instruction.OPEN_SCREEN)
public final class OpenScreenMachine implements Machine {

    public static final OpenScreenMachine MACHINE = new OpenScreenMachine();

    private OpenScreenMachine() {};

     static Screen defaultScreen(@NotNull DefaultScreen screen, @Nullable Screen lastScreen) {
        return switch (screen.getIdentifier()) {
            case "title" -> new TitleScreen();
            case "pause" -> new PauseScreen(true);
            case "options" -> new OptionsScreen(lastScreen, minecraft.options);
            case "share_to_lan" -> new ShareToLanScreen(lastScreen);
            case "advancements" -> new AdvancementsScreen(minecraft.getConnection().getAdvancements(), lastScreen);
            case "stats" -> new StatsScreen(lastScreen, minecraft.player.getStats());
            case "alert" -> {
                Component title = NeoForgeUtil.fromJson(screen.data("title", String.class));
                Component message = NeoForgeUtil.fromJson(screen.data("message", String.class));
                String button = screen.data("button", String.class);

                if (button == null)
                    yield new AlertScreen(() -> {}, title, message);
                else
                    yield new AlertScreen(() -> {}, title, message, NeoForgeUtil.fromJson(button), true);
            }
            case "disconnected" -> {
                Component title = NeoForgeUtil.fromJson(screen.data("title", String.class));
                Component reason = NeoForgeUtil.fromJson(screen.data("reason", String.class));
                String button = screen.data("button", String.class);

                if (button == null)
                    yield new DisconnectedScreen(lastScreen, title, reason);
                else
                    yield new DisconnectedScreen(lastScreen, title, reason, NeoForgeUtil.fromJson(button));
            }
            case "message" -> {
                Component message = NeoForgeUtil.fromJson(screen.data("message", String.class));
                yield new GenericMessageScreen(message);
            }
            case "death" -> {
                String cause = screen.data("cause", String.class);
                boolean hardcore = screen.data("hardcore", Boolean.class);

                if (cause == null)
                    yield new DeathScreen(null, hardcore);
                else
                    yield new DeathScreen(NeoForgeUtil.fromJson(cause), hardcore);
            }
            case "error" -> {
                Component title = NeoForgeUtil.fromJson(screen.data("title", String.class));
                Component message = NeoForgeUtil.fromJson(screen.data("message", String.class));

                yield new ErrorScreen(title, message);
            }

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
            Screen screen0 = new NeoForgeScreen(s, minecraft.screen);
            minecraft.execute(() -> minecraft.setScreen(screen0));
        }
    }

}
