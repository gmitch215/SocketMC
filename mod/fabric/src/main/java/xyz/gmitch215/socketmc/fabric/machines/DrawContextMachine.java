package xyz.gmitch215.socketmc.fabric.machines;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.RenderType;
import org.jetbrains.annotations.NotNull;
import org.joml.Quaternionf;
import xyz.gmitch215.socketmc.fabric.FabricUtil;
import xyz.gmitch215.socketmc.fabric.screen.FabricGraphicsContext;
import xyz.gmitch215.socketmc.instruction.Instruction;
import xyz.gmitch215.socketmc.instruction.InstructionId;
import xyz.gmitch215.socketmc.instruction.Machine;
import xyz.gmitch215.socketmc.util.Identifier;
import xyz.gmitch215.socketmc.util.LifecycleMap;
import xyz.gmitch215.socketmc.util.render.DrawingContext;
import xyz.gmitch215.socketmc.util.render.GraphicsContext;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import static xyz.gmitch215.socketmc.fabric.FabricSocketMC.minecraft;

@InstructionId(Instruction.DRAW_CONTEXT)
public final class DrawContextMachine implements Machine {

    public static final DrawContextMachine MACHINE = new DrawContextMachine();

    private DrawContextMachine() {}

    private static final LifecycleMap<Consumer<GuiGraphics>> lifecycle = new LifecycleMap<>();

    public static void frameTick(GuiGraphics graphics, DeltaTracker delta) {
        lifecycle.run();
        lifecycle.forEach(c -> c.accept(graphics));
    }

    @Override
    public void onInstruction(@NotNull Instruction instruction) throws Exception {
        DrawingContext context = instruction.firstParameter(DrawingContext.class);
        long millis = instruction.lastLongParameter();

        lifecycle.store(graphics -> draw(graphics, context), millis);
    }

    public static void draw(GuiGraphics graphics, DrawingContext context) {
        for (Function<GraphicsContext, DrawingContext.Command> func : context) {
            DrawingContext.Command cmd = func.apply(FabricGraphicsContext.INSTANCE);

            RenderType type = switch (cmd.getType()) {
                case DEFAULT -> RenderType.gui();
                case OVERLAY -> RenderType.guiOverlay();
                case TEXT_HIGHLIGHT -> RenderType.guiTextHighlight();
                case GHOST_RECIPE_OVERLAY -> RenderType.guiGhostRecipeOverlay();
            };

            List<DrawingContext.Modifier> modifiers = cmd.getModifiers();
            boolean modding = !modifiers.isEmpty();

            if (modding) {
                graphics.pose().pushPose();

                for (DrawingContext.Modifier mod : modifiers)
                    applyModifier(mod, graphics.pose());
            }

            switch (cmd.getId()) {
                case DrawingContext.H_LINE -> {
                    int minX = cmd.firstIntParameter();
                    int maxX = cmd.intParameter(1);
                    int y = cmd.intParameter(2);
                    int rgb = cmd.lastIntParameter();

                    graphics.hLine(type, minX, maxX, y, rgb);
                }
                case DrawingContext.V_LINE -> {
                    int x = cmd.firstIntParameter();
                    int minY = cmd.intParameter(1);
                    int maxY = cmd.intParameter(2);
                    int rgb = cmd.lastIntParameter();

                    graphics.vLine(type, x, minY, maxY, rgb);
                }
                case DrawingContext.ENABLE_SCISSOR -> {
                    int minX = cmd.firstIntParameter();
                    int minY = cmd.intParameter(1);
                    int maxX = cmd.intParameter(2);
                    int maxY = cmd.lastIntParameter();

                    graphics.enableScissor(minX, minY, maxX, maxY);
                }
                case DrawingContext.DISABLE_SCISSOR -> graphics.disableScissor();
                case DrawingContext.FILL -> {
                    int minX = cmd.firstIntParameter();
                    int minY = cmd.intParameter(1);
                    int maxX = cmd.intParameter(2);
                    int maxY = cmd.intParameter(3);
                    int z = cmd.intParameter(4);
                    int rgb = cmd.lastIntParameter();

                    graphics.fill(type, minX, minY, maxX, maxY, z, rgb);
                }
                case DrawingContext.FILL_GRADIENT -> {
                    int minX = cmd.firstIntParameter();
                    int minY = cmd.intParameter(1);
                    int maxX = cmd.intParameter(2);
                    int maxY = cmd.intParameter(3);
                    int z = cmd.intParameter(4);
                    int from = cmd.intParameter(5);
                    int to = cmd.lastIntParameter();

                    graphics.fillGradient(type, minX, minY, maxX, maxY, z, from, to);
                }
                case DrawingContext.DRAW_CENTERED_STRING -> {
                    int x = cmd.firstIntParameter();
                    int y = cmd.intParameter(1);
                    String text = cmd.stringParameter(2);
                    int rgb = cmd.lastIntParameter();

                    graphics.drawCenteredString(minecraft.font, FabricUtil.fromJson(text), x, y, rgb);
                }
                case DrawingContext.DRAW_STRING -> {
                    int x = cmd.firstIntParameter();
                    int y = cmd.intParameter(1);
                    String text = cmd.stringParameter(2);
                    int rgb = cmd.intParameter(3);
                    boolean dropShadow = cmd.lastBooleanParameter();

                    graphics.drawString(minecraft.font, FabricUtil.fromJson(text), x, y, rgb, dropShadow);
                }
                case DrawingContext.DRAW_WORD_WRAP -> {
                    int x = cmd.firstIntParameter();
                    int y = cmd.intParameter(1);
                    int width = cmd.intParameter(2);
                    String text = cmd.stringParameter(3);
                    int rgb = cmd.intParameter(4);

                    graphics.drawWordWrap(minecraft.font, FabricUtil.fromJson(text), x, y, width, rgb);
                }
                case DrawingContext.OUTLINE -> {
                    int minX = cmd.firstIntParameter();
                    int minY = cmd.intParameter(1);
                    int maxX = cmd.intParameter(2);
                    int maxY = cmd.intParameter(3);
                    int rgb = cmd.lastIntParameter();

                    graphics.renderOutline(minX, minY, maxX, maxY, rgb);
                }
                case DrawingContext.BLIT -> {
                    Identifier texture = cmd.firstParameter(Identifier.class);
                    int x = cmd.intParameter(1);
                    int y = cmd.intParameter(2);
                    float u = cmd.intParameter(3);
                    float v = cmd.intParameter(4);
                    int width = cmd.intParameter(5);
                    int height = cmd.intParameter(6);
                    int textureWidth = cmd.intParameter(7);
                    int textureHeight = cmd.lastIntParameter();

                    graphics.blit(FabricUtil.toMinecraft(texture), x, y, u, v, width, height, textureWidth, textureHeight);
                }
            }

            if (modding)
                graphics.pose().popPose();
        }
    }

    public static void applyModifier(DrawingContext.Modifier mod, PoseStack pose) {
        switch (mod.getId()) {
            case DrawingContext.MODIFIER_SCALE -> {
                float x = mod.firstFloatParameter();
                float y = mod.floatParameter(1);
                float z = mod.lastFloatParameter();

                pose.scale(x, y, z);
            }
            case DrawingContext.MODIFIER_TRANSLATE -> {
                float x = mod.firstFloatParameter();
                float y = mod.floatParameter(1);
                float z = mod.lastFloatParameter();

                pose.translate(x, y, z);
            }
            case DrawingContext.MODIFIER_ROTATE -> {
                Quaternionf quaternion = mod.firstParameter(Quaternionf.class);

                pose.mulPose(quaternion);
            }
            case DrawingContext.MODIFIER_ROTATE_AROUND -> {
                Quaternionf quaternion = mod.firstParameter(Quaternionf.class);
                float x = mod.floatParameter(1);
                float y = mod.floatParameter(2);
                float z = mod.lastFloatParameter();

                pose.rotateAround(quaternion, x, y, z);
            }
        }
    }
}
