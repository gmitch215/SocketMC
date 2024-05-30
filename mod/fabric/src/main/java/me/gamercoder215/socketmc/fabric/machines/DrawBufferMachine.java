package me.gamercoder215.socketmc.fabric.machines;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import me.gamercoder215.socketmc.instruction.Instruction;
import me.gamercoder215.socketmc.instruction.InstructionId;
import me.gamercoder215.socketmc.instruction.Machine;
import me.gamercoder215.socketmc.util.render.RenderBuffer;
import me.gamercoder215.socketmc.util.render.Vertex;
import me.gamercoder215.socketmc.util.LifecycleMap;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;

import java.util.function.Consumer;

@InstructionId(Instruction.DRAW_BUFFER)
public final class DrawBufferMachine implements Machine {

    public static final DrawBufferMachine MACHINE = new DrawBufferMachine();

    private DrawBufferMachine() {}

    private static final LifecycleMap<Consumer<GuiGraphics>> lifecycle = new LifecycleMap<>();

    public static void frameTick(GuiGraphics graphics, float delta) {
        lifecycle.run();
        lifecycle.forEach(c -> c.accept(graphics));
    }

    @Override
    public void onInstruction(@NotNull Instruction instruction) {
        RenderBuffer buffer = instruction.parameter(0, RenderBuffer.class);
        long millis = instruction.lastParameter(Long.class);

        VertexFormat.Mode mode = switch (buffer.getMode()) {
            case LINES -> VertexFormat.Mode.LINES;
            case LINE_STRIP -> VertexFormat.Mode.LINE_STRIP;
            case TRIANGLES -> VertexFormat.Mode.TRIANGLES;
            case TRIANGLE_STRIP -> VertexFormat.Mode.TRIANGLE_STRIP;
            case TRIANGLE_FAN -> VertexFormat.Mode.TRIANGLE_FAN;
            case QUADRILATERALS -> VertexFormat.Mode.QUADS;
        };

        lifecycle.store(graphics -> {
            Tesselator tesselator = Tesselator.getInstance();
            Matrix4f matrix = graphics.pose().last().pose();

            BufferBuilder builder = tesselator.getBuilder();
            builder.begin(mode, DefaultVertexFormat.POSITION_COLOR);

            for (Vertex vertex : buffer.getVertices()) {
                int color = buffer.getColor(vertex);

                builder.vertex(matrix, vertex.getX(), vertex.getY(), vertex.getZ())
                        .color((color >> 16) & 0xFF, (color >> 8) & 0xFF, color & 0xFF, (color >> 24) & 0xFF)
                        .endVertex();
            }

            RenderSystem.setShader(GameRenderer::getPositionColorShader);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

            tesselator.end();
        }, millis);
    }
}
