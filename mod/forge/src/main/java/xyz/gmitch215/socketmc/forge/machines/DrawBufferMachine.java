package xyz.gmitch215.socketmc.forge.machines;

import com.mojang.blaze3d.vertex.*;
import xyz.gmitch215.socketmc.instruction.Instruction;
import xyz.gmitch215.socketmc.instruction.InstructionId;
import xyz.gmitch215.socketmc.instruction.Machine;
import xyz.gmitch215.socketmc.util.LifecycleMap;
import xyz.gmitch215.socketmc.util.render.RenderBuffer;
import xyz.gmitch215.socketmc.util.render.Vertex;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphics;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;

import java.util.function.Consumer;

@InstructionId(Instruction.DRAW_BUFFER)
public final class DrawBufferMachine implements Machine {

    public static final DrawBufferMachine MACHINE = new DrawBufferMachine();

    private DrawBufferMachine() {}

    public static final LifecycleMap<Consumer<GuiGraphics>> lifecycle = new LifecycleMap<>();

    public static void frameTick(GuiGraphics graphics, DeltaTracker delta) {
        lifecycle.run();
        lifecycle.forEach(c -> c.accept(graphics));
    }

    @Override
    public void onInstruction(@NotNull Instruction instruction) {
        RenderBuffer buffer = instruction.parameter(0, RenderBuffer.class);
        long millis = instruction.lastLongParameter();

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

            BufferBuilder builder = tesselator.begin(mode, DefaultVertexFormat.POSITION_COLOR);

            for (Vertex vertex : buffer.getVertices()) {
                int color = buffer.getColor(vertex);

                builder.addVertex(matrix, vertex.getX(), vertex.getY(), vertex.getZ())
                        .setColor((color >> 16) & 0xFF, (color >> 8) & 0xFF, color & 0xFF, (color >> 24) & 0xFF);
            }

            BufferUploader.drawWithShader(builder.buildOrThrow());
        }, millis);
    }
}
