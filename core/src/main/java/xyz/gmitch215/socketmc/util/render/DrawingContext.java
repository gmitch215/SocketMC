package xyz.gmitch215.socketmc.util.render;

import org.jetbrains.annotations.NotNull;
import xyz.gmitch215.socketmc.util.Identifier;
import xyz.gmitch215.socketmc.util.Paramaterized;
import xyz.gmitch215.socketmc.util.render.text.Text;

import java.awt.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.*;

/**
 * Represents the raw graphics context used to display graphics on the screen.
 */
public final class DrawingContext implements Serializable, Iterable<DrawingContext.Command> {

    @Serial
    private static final long serialVersionUID = 6151208407260227313L;

    /**
     * Horizontal Line
     */
    public static final int H_LINE = 0;

    /**
     * Vertical Line
     */
    public static final int V_LINE = 1;

    /**
     * Enable Scissor
     */
    public static final int ENABLE_SCISSOR = 2;

    /**
     * Disable Scissor
     */
    public static final int DISABLE_SCISSOR = 3;

    /**
     * Fill
     */
    public static final int FILL = 4;

    /**
     * Fill Gradient
     */
    public static final int FILL_GRADIENT = 5;

    /**
     * Draw Centered String
     */
    public static final int DRAW_CENTERED_STRING = 6;

    /**
     * Draw String
     */
    public static final int DRAW_STRING = 7;

    /**
     * Draw Word Wrap
     */
    public static final int DRAW_WORD_WRAP = 8;

    /**
     * Outline
     */
    public static final int OUTLINE = 9;

    /**
     * Blit
     */
    public static final int BLIT = 10;

    private final List<Command> commands = new ArrayList<>();

    private DrawingContext(Collection<Command> commands) {
        this.commands.addAll(commands);
    }

    /**
     * Gets an immutable copy of the commands in this DrawingContext.
     * @return DrawingContext Commands
     */
    @NotNull
    public List<Command> getCommands() {
        return List.copyOf(commands);
    }

    /**
     * Adds a command to the DrawingContext.
     * @param command the command to add
     * @throws IllegalArgumentException if the command is null
     */
    public void addCommand(@NotNull Command command) throws IllegalArgumentException {
        if (command == null) throw new IllegalArgumentException("Command cannot be null");
        commands.add(command);
    }

    /**
     * Adds a command to the DrawingContext at the specified index.
     * @param index the index to add the command at
     * @param command the command to add
     * @throws IllegalArgumentException if the command is null
     */
    public void addCommand(int index, @NotNull Command command) throws IllegalArgumentException {
        if (command == null) throw new IllegalArgumentException("Command cannot be null");
        commands.add(index, command);
    }

    /**
     * Removes a command from the DrawingContext.
     * @param command the command to remove
     * @throws IllegalArgumentException if the command is null
     */
    public void removeCommand(@NotNull Command command) throws IllegalArgumentException {
        if (command == null) throw new IllegalArgumentException("Command cannot be null");
        commands.remove(command);
    }

    /**
     * Removes a command from the DrawingContext at the specified index.
     * @param index the index to remove the command at
     */
    public void removeCommand(int index) {
        commands.remove(index);
    }

    /**
     * Clears all commands from the DrawingContext.
     */
    public void clearCommands() {
        commands.clear();
    }

    @NotNull
    @Override
    public Iterator<Command> iterator() {
        return commands.iterator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DrawingContext ctx)) return false;
        return Objects.equals(commands, ctx.commands);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(commands);
    }

    @Override
    public String toString() {
        return "DrawingContext{" + commands + '}';
    }

    /**
     * Represents the type of drawing command to use.
     */
    public enum Type {

        /**
         * Represents the default drawing command.
         */
        DEFAULT,

        /**
         * Represents the drawing command for rendering an overlay.
         */
        OVERLAY,

        /**
         * Represents the drawing command for rendering highlighted text.
         */
        TEXT_HIGHLIGHT,

        /**
         * Represents the drawing command for rendering a "ghost recipe" overlay, which is used to display a recipe
         * that is not currently craftable.
         */
        GHOST_RECIPE_OVERLAY
    }

    /**
     * Represents a command in a DrawingContext.
     */
    public static final class Command implements Serializable, Paramaterized {

        @Serial
        private static final long serialVersionUID = 1202929033750152256L;

        // GuiGraphics

        /**
         * Draws a horizontal line.
         * @param minX the minimum x-coordinate
         * @param maxX the maximum x-coordinate
         * @param y the y-coordinate
         * @return A DrawingContext Command
         * @throws IllegalArgumentException if the type is null, coordinates are invalid, or color is null
         */
        @NotNull
        public static Command hLine(int minX, int maxX, int y) throws IllegalArgumentException {
            return hLine(minX, maxX, y, Color.WHITE);
        }

        /**
         * Draws a horizontal line.
         * @param minX the minimum x-coordinate
         * @param maxX the maximum x-coordinate
         * @param y the y-coordinate
         * @param color the color of the line
         * @return A DrawingContext Command
         * @throws IllegalArgumentException if the type is null, coordinates are invalid, or color is null
         */
        @NotNull
        public static Command hLine(int minX, int maxX, int y, @NotNull Color color) throws IllegalArgumentException {
            return hLine(Type.DEFAULT, minX, maxX, y, color);
        }

        /**
         * Draws a horizontal line.
         * @param type the type of drawing command to use
         * @param minX the minimum x-coordinate
         * @param maxX the maximum x-coordinate
         * @param y the y-coordinate
         * @param color the color of the line
         * @return A DrawingContext Command
         * @throws IllegalArgumentException if the type is null, coordinates are invalid, or color is null
         */
        @NotNull
        public static Command hLine(@NotNull Type type, int minX, int maxX, int y, @NotNull Color color) throws IllegalArgumentException {
            if (type == null) throw new IllegalArgumentException("Type cannot be null");
            if (minX < 0 || maxX < 0) throw new IllegalArgumentException("Coordinates cannot be negative");
            if (minX > maxX) throw new IllegalArgumentException("minX cannot be greater than maxX");
            if (y < 0) throw new IllegalArgumentException("y cannot be negative");
            if (color == null) throw new IllegalArgumentException("Color cannot be null");

            return new Command(H_LINE, type, List.of(minX, maxX, y, color.getRGB()));
        }

        /**
         * Draws a vertical line.
         * @param x the x-coordinate
         * @param minY the minimum y-coordinate
         * @param maxY the maximum y-coordinate
         * @return A DrawingContext Command
         * @throws IllegalArgumentException if the type is null, coordinates are invalid, or color is null
         */
        @NotNull
        public static Command vLine(int x, int minY, int maxY) throws IllegalArgumentException {
            return vLine(x, minY, maxY, Color.WHITE);
        }

        /**
         * Draws a vertical line.
         * @param x the x-coordinate
         * @param minY the minimum y-coordinate
         * @param maxY the maximum y-coordinate
         * @param color the color of the line
         * @return A DrawingContext Command
         * @throws IllegalArgumentException if the type is null, coordinates are invalid, or color is null
         */
        @NotNull
        public static Command vLine(int x, int minY, int maxY, @NotNull Color color) throws IllegalArgumentException {
            return vLine(Type.DEFAULT, x, minY, maxY, color);
        }

        /**
         * Draws a vertical line.
         * @param type the type of drawing command to use
         * @param x the x-coordinate
         * @param minY the minimum y-coordinate
         * @param maxY the maximum y-coordinate
         * @param color the color of the line
         * @return A DrawingContext Command
         * @throws IllegalArgumentException if the type is null, coordinates are invalid, or color is null
         */
        @NotNull
        public static Command vLine(@NotNull Type type, int x, int minY, int maxY, @NotNull Color color) throws IllegalArgumentException {
            if (type == null) throw new IllegalArgumentException("Type cannot be null");
            if (x < 0) throw new IllegalArgumentException("x cannot be negative");
            if (minY < 0 || maxY < 0) throw new IllegalArgumentException("Coordinates cannot be negative");
            if (minY > maxY) throw new IllegalArgumentException("minY cannot be greater than maxY");
            if (color == null) throw new IllegalArgumentException("Color cannot be null");

            return new Command(V_LINE, type, List.of(x, minY, maxY, color.getRGB()));
        }

        /**
         * Enables scissoring over the specified area.
         * @param minX the minimum x-coordinate
         * @param minY the minimum y-coordinate
         * @param maxX the maximum x-coordinate
         * @param maxY the maximum y-coordinate
         * @return A DrawingContext Command
         * @throws IllegalArgumentException if coordinates are invalid
         * @see <a href="https://developer.mozilla.org/en-US/docs/Web/API/WebGL_API/By_example/Basic_scissoring">Basic Scissoring</a>
         */
        @NotNull
        public static Command enableScissor(int minX, int minY, int maxX, int maxY) throws IllegalArgumentException {
            if (minX < 0 || minY < 0 || maxX < 0 || maxY < 0) throw new IllegalArgumentException("Coordinates cannot be negative");
            if (minX > maxX) throw new IllegalArgumentException("minX cannot be greater than maxX");
            if (minY > maxY) throw new IllegalArgumentException("minY cannot be greater than maxY");

            return new Command(ENABLE_SCISSOR, Type.DEFAULT, List.of(minX, minY, maxX, maxY));
        }

        /**
         * Disables scissoring.
         * @return A DrawingContext Command
         */
        @NotNull
        public static Command disableScissor() {
            return new Command(DISABLE_SCISSOR, Type.DEFAULT, List.of());
        }

        /**
         * Fills a rectangle with the specified color.
         * @param type the type of drawing command to use
         * @param minX the minimum x-coordinate
         * @param minY the minimum y-coordinate
         * @param maxX the maximum x-coordinate
         * @param maxY the maximum y-coordinate
         * @return A DrawingContext Command
         * @throws IllegalArgumentException if the coordinates are invalid
         */
        @NotNull
        public static Command fill(int minX, int minY, int maxX, int maxY) throws IllegalArgumentException {
            return fill(Type.DEFAULT, minX, minY, maxX, maxY, Color.WHITE);
        }

        /**
         * Fills a rectangle with the specified color.
         * @param type the type of drawing command to use
         * @param minX the minimum x-coordinate
         * @param minY the minimum y-coordinate
         * @param maxX the maximum x-coordinate
         * @param maxY the maximum y-coordinate
         * @return A DrawingContext Command
         * @throws IllegalArgumentException if the type is null or coordinates are invalid
         */
        @NotNull
        public static Command fill(@NotNull Type type, int minX, int minY, int maxX, int maxY) throws IllegalArgumentException {
            return fill(type, minX, minY, maxX, maxY, Color.WHITE);
        }

        /**
         * Fills a rectangle with the specified color.
         * @param minX the minimum x-coordinate
         * @param minY the minimum y-coordinate
         * @param maxX the maximum x-coordinate
         * @param maxY the maximum y-coordinate
         * @param color the color of the rectangle
         * @return A DrawingContext Command
         * @throws IllegalArgumentException if the coordinates are invalid or color is null
         */
        @NotNull
        public static Command fill(int minX, int minY, int maxX, int maxY, @NotNull Color color) throws IllegalArgumentException {
            return fill(Type.DEFAULT, minX, minY, maxX, maxY, color);
        }

        /**
         * Fills a rectangle with the specified color.
         * @param type the type of drawing command to use
         * @param minX the minimum x-coordinate
         * @param minY the minimum y-coordinate
         * @param maxX the maximum x-coordinate
         * @param maxY the maximum y-coordinate
         * @param color the color of the rectangle
         * @return A DrawingContext Command
         * @throws IllegalArgumentException if the type is null, coordinates are invalid, or color is null
         */
        @NotNull
        public static Command fill(@NotNull Type type, int minX, int minY, int maxX, int maxY, @NotNull Color color) throws IllegalArgumentException {
            return fill(type, minX, minY, maxX, maxY, 0, color);
        }

        /**
         * Fills a rectangle with the specified color.
         * @param minX the minimum x-coordinate
         * @param minY the minimum y-coordinate
         * @param maxX the maximum x-coordinate
         * @param maxY the maximum y-coordinate
         * @param z the z-index of the rectangle
         * @param color the color of the rectangle
         * @return A DrawingContext Command
         * @throws IllegalArgumentException if the coordinates are invalid or color is null
         */
        @NotNull
        public static Command fill(int minX, int minY, int maxX, int maxY, int z, @NotNull Color color) throws IllegalArgumentException {
            return fill(Type.DEFAULT, minX, minY, maxX, maxY, z, color);
        }

        /**
         * Fills a rectangle with the specified color.
         * @param type the type of drawing command to use
         * @param minX the minimum x-coordinate
         * @param minY the minimum y-coordinate
         * @param maxX the maximum x-coordinate
         * @param maxY the maximum y-coordinate
         * @param z the z-index of the rectangle
         * @param color the color of the rectangle
         * @return A DrawingContext Command
         * @throws IllegalArgumentException if the type is null, coordinates are invalid, or color is null
         */
        @NotNull
        public static Command fill(@NotNull Type type, int minX, int minY, int maxX, int maxY, int z, @NotNull Color color) throws IllegalArgumentException {
            if (type == null) throw new IllegalArgumentException("Type cannot be null");
            if (minX < 0 || minY < 0 || maxX < 0 || maxY < 0) throw new IllegalArgumentException("Coordinates cannot be negative");
            if (minX > maxX) throw new IllegalArgumentException("minX cannot be greater than maxX");
            if (minY > maxY) throw new IllegalArgumentException("minY cannot be greater than maxY");
            if (color == null) throw new IllegalArgumentException("Color cannot be null");

            return new Command(FILL, type, List.of(minX, minY, maxX, maxY, z, color.getRGB()));
        }

        /**
         * Fills a rectangle with a gradient.
         * @param minX the minimum x-coordinate
         * @param minY the minimum y-coordinate
         * @param maxX the maximum x-coordinate
         * @param maxY the maximum y-coordinate
         * @param from the starting color of the gradient
         * @param to the ending color of the gradient
         * @return A DrawingContext Command
         * @throws IllegalArgumentException if the coordinates are invalid or colors are null
         */
        @NotNull
        public static Command fillGradient(int minX, int minY, int maxX, int maxY, @NotNull Color from, @NotNull Color to) throws IllegalArgumentException {
            return fillGradient(Type.DEFAULT, minX, minY, maxX, maxY, from, to);
        }

        /**
         * Fills a rectangle with a gradient.
         * @param type the type of drawing command to use
         * @param minX the minimum x-coordinate
         * @param minY the minimum y-coordinate
         * @param maxX the maximum x-coordinate
         * @param maxY the maximum y-coordinate
         * @param from the starting color of the gradient
         * @param to the ending color of the gradient
         * @return A DrawingContext Command
         * @throws IllegalArgumentException if the type is null, coordinates are invalid, or colors are null
         */
        @NotNull
        public static Command fillGradient(@NotNull Type type, int minX, int minY, int maxX, int maxY, @NotNull Color from, @NotNull Color to) throws IllegalArgumentException {
            return fillGradient(type, minX, minY, maxX, maxY, 0, from, to);
        }

        /**
         * Fills a rectangle with a gradient.
         * @param minX the minimum x-coordinate
         * @param minY the minimum y-coordinate
         * @param maxX the maximum x-coordinate
         * @param maxY the maximum y-coordinate
         * @param z the z-index of the rectangle
         * @param from the starting color of the gradient
         * @param to the ending color of the gradient
         * @return A DrawingContext Command
         * @throws IllegalArgumentException if the coordinates are invalid or colors are null
         */
        @NotNull
        public static Command fillGradient(int minX, int minY, int maxX, int maxY, int z, @NotNull Color from, @NotNull Color to) throws IllegalArgumentException {
            return fillGradient(Type.DEFAULT, minX, minY, maxX, maxY, z, from, to);
        }

        /**
         * Fills a rectangle with a gradient.
         * @param type the type of drawing command to use
         * @param minX the minimum x-coordinate
         * @param minY the minimum y-coordinate
         * @param maxX the maximum x-coordinate
         * @param maxY the maximum y-coordinate
         * @param z the z-index of the rectangle
         * @param from the starting color of the gradient
         * @param to the ending color of the gradient
         * @return A DrawingContext Command
         * @throws IllegalArgumentException if the type is null, coordinates are invalid, or colors are null
         */
        @NotNull
        public static Command fillGradient(@NotNull Type type, int minX, int minY, int maxX, int maxY, int z, @NotNull Color from, @NotNull Color to) throws IllegalArgumentException {
            if (type == null) throw new IllegalArgumentException("Type cannot be null");
            if (minX < 0 || minY < 0 || maxX < 0 || maxY < 0) throw new IllegalArgumentException("Coordinates cannot be negative");
            if (minX > maxX) throw new IllegalArgumentException("minX cannot be greater than maxX");
            if (minY > maxY) throw new IllegalArgumentException("minY cannot be greater than maxY");
            if (from == null || to == null) throw new IllegalArgumentException("Colors cannot be null");

            return new Command(FILL_GRADIENT, type, List.of(minX, minY, maxX, maxY, z, from.getRGB(), to.getRGB()));
        }

        /**
         * Draws a centered string.
         * @param x the x-coordinate
         * @param y the y-coordinate
         * @param text the text to draw
         * @return A DrawingContext Command
         * @throws IllegalArgumentException if the text is null, or coordinates are invalid
         */
        @NotNull
        public static Command drawCenteredString(int x, int y, @NotNull Text text) throws IllegalArgumentException {
            return drawCenteredString(x, y, text, Color.WHITE);
        }

        /**
         * Draws a centered string.
         * @param x the x-coordinate
         * @param y the y-coordinate
         * @param text the text to draw
         * @param color the color of the text
         * @return A DrawingContext Command
         * @throws IllegalArgumentException if the text or color are null, or coordinates are invalid
         */
        @NotNull
        public static Command drawCenteredString(int x, int y, @NotNull Text text, @NotNull Color color) throws IllegalArgumentException {
            if (text == null) throw new IllegalArgumentException("Text cannot be null");
            if (color == null) throw new IllegalArgumentException("Color cannot be null");
            if (x < 0 || y < 0) throw new IllegalArgumentException("Coordinates cannot be negative");

            return new Command(DRAW_CENTERED_STRING, Type.DEFAULT, List.of(x, y, text.toJSON(), color.getRGB()));
        }

        /**
         * Draws a string.
         * @param x the x-coordinate
         * @param y the y-coordinate
         * @param text the text to draw
         * @return A DrawingContext Command
         * @throws IllegalArgumentException if the text or color are null, or coordinates are invalid
         */
        @NotNull
        public static Command drawString(int x, int y, @NotNull Text text) throws IllegalArgumentException {
            return drawString(x, y, text, Color.WHITE);
        }

        /**
         * Draws a string.
         * @param x the x-coordinate
         * @param y the y-coordinate
         * @param text the text to draw
         * @param color the color of the text
         * @return A DrawingContext Command
         * @throws IllegalArgumentException if the text or color are null, or coordinates are invalid
         */
        @NotNull
        public static Command drawString(int x, int y, @NotNull Text text, @NotNull Color color) throws IllegalArgumentException {
            return drawString(x, y, text, color, true);
        }

        /**
         * Draws a string.
         * @param x the x-coordinate
         * @param y the y-coordinate
         * @param text the text to draw
         * @param color the color of the text
         * @param dropShadow whether to draw a drop shadow
         * @return A DrawingContext Command
         * @throws IllegalArgumentException if the text or color are null, or coordinates are invalid
         */
        public static Command drawString(int x, int y, @NotNull Text text, @NotNull Color color, boolean dropShadow) throws IllegalArgumentException {
            if (text == null) throw new IllegalArgumentException("Text cannot be null");
            if (color == null) throw new IllegalArgumentException("Color cannot be null");
            if (x < 0 || y < 0) throw new IllegalArgumentException("Coordinates cannot be negative");

            return new Command(DRAW_STRING, Type.DEFAULT, List.of(x, y, text.toJSON(), color.getRGB(), dropShadow));
        }

        /**
         * Draws a word-wrapped string.
         * @param x the x-coordinate
         * @param y the y-coordinate
         * @param width the width of each line
         * @param text the text to draw
         * @return A DrawingContext Command
         * @throws IllegalArgumentException if the text is null, or coordinates/width are invalid
         */
        @NotNull
        public static Command drawWordWrap(int x, int y, int width, @NotNull Text text) throws IllegalArgumentException {
            return drawWordWrap(x, y, width, text, Color.WHITE);
        }

        /**
         * Draws a word-wrapped string.
         * @param x the x-coordinate
         * @param y the y-coordinate
         * @param width the width of each line
         * @param text the text to draw
         * @param color the color of the text
         * @return A DrawingContext Command
         * @throws IllegalArgumentException if the text or color are null, or coordinates/width are invalid
         */
        @NotNull
        public static Command drawWordWrap(int x, int y, int width, @NotNull Text text, @NotNull Color color) throws IllegalArgumentException {
            if (text == null) throw new IllegalArgumentException("Text cannot be null");
            if (color == null) throw new IllegalArgumentException("Color cannot be null");
            if (x < 0 || y < 0) throw new IllegalArgumentException("Coordinates cannot be negative");
            if (width < 0) throw new IllegalArgumentException("Width cannot be negative");

            return new Command(DRAW_WORD_WRAP, Type.DEFAULT, List.of(x, y, width, text.toJSON(), color.getRGB()));
        }

        /**
         * Draws a rectangular outline.
         * @param minX the minimum x-coordinate
         * @param minY the minimum y-coordinate
         * @param maxX the maximum x-coordinate
         * @param maxY the maximum y-coordinate
         * @return A DrawingContext Command
         * @throws IllegalArgumentException if the coordinates are invalid
         */
        @NotNull
        public static Command outline(int minX, int minY, int maxX, int maxY) throws IllegalArgumentException {
            return outline(minX, minY, maxX, maxY, Color.WHITE);
        }

        /**
         * Draws a rectangular outline.
         * @param minX the minimum x-coordinate
         * @param minY the minimum y-coordinate
         * @param maxX the maximum x-coordinate
         * @param maxY the maximum y-coordinate
         * @param color the color of the outline
         * @return A DrawingContext Command
         * @throws IllegalArgumentException if the coordinates are invalid or color is null
         */
        @NotNull
        public static Command outline(int minX, int minY, int maxX, int maxY, @NotNull Color color) throws IllegalArgumentException {
            if (minX < 0 || minY < 0 || maxX < 0 || maxY < 0) throw new IllegalArgumentException("Coordinates cannot be negative");
            if (minX > maxX) throw new IllegalArgumentException("minX cannot be greater than maxX");
            if (minY > maxY) throw new IllegalArgumentException("minY cannot be greater than maxY");
            if (color == null) throw new IllegalArgumentException("Color cannot be null");

            return new Command(OUTLINE, Type.DEFAULT, List.of(minX, minY, maxX, maxY, color.getRGB()));
        }

        /**
         * Draws a texture at the specified coordinates, using the vanilla 16x16 texture parameters.
         * @param texture the identifier to the texture to draw
         * @param x the x-coordinate
         * @param y the y-coordinate
         * @return A DrawingContext Command
         */
        @NotNull
        public static Command blit(@NotNull Identifier texture, int x, int y) {
            return blit(texture, x, y,16, 16);
        }

        /**
         * Draws a texture at the specified coordinates.
         * @param texture the identifier to the texture to draw
         * @param x the x-coordinate
         * @param y the y-coordinate
         * @param width the width of the blitted area
         * @param height the height of the blitted area
         * @return A DrawingContext Command
         */
        @NotNull
        public static Command blit(@NotNull Identifier texture, int x, int y, int width, int height) {
            return blit(texture, x, y, 0, 0, width, height);
        }

        /**
         * Draws a texture at the specified coordinates.
         * @param texture the identifier to the texture to draw
         * @param x the x-coordinate
         * @param y the y-coordinate
         * @param xOffset the offset of the starting x-coordinate to blit
         * @param yOffset the offset of the starting y-coordinate to blit
         * @param width the width of the blitted area
         * @param height the height of the blitted area
         * @return A DrawingContext Command
         */
        @NotNull
        public static Command blit(@NotNull Identifier texture, int x, int y, float xOffset, float yOffset, int width, int height) {
            return blit(texture, x, y, xOffset, yOffset, width, height, 256, 256);
        }

        /**
         * Draws a texture at the specified coordinates.
         * @param texture the identifier to the texture to draw
         * @param x the x-coordinate
         * @param y the y-coordinate
         * @param xOffset the offset of the starting x-coordinate to blit
         * @param yOffset the offset of the starting y-coordinate to blit
         * @param width the width of the blitted area
         * @param height the height of the blitted area
         * @param textureWidth the width of the texture file (usually 256)
         * @param textureHeight the height of the texture file (usually 256)
         * @return A DrawingContext Command
         * @see <a href="https://docs.fabricmc.net/develop/rendering/draw-context#drawing-an-entire-texture">Drawing Textures - Fabric</a>
         */
        @NotNull
        public static Command blit(@NotNull Identifier texture, int x, int y, float xOffset, float yOffset, int width, int height, int textureWidth, int textureHeight) {
            if (texture == null) throw new IllegalArgumentException("Texture cannot be null");
            if (x < 0 || y < 0) throw new IllegalArgumentException("Coordinates cannot be negative");
            if (width < 0 || height < 0) throw new IllegalArgumentException("Width and height cannot be negative");
            if (textureWidth < 0 || textureHeight < 0) throw new IllegalArgumentException("Texture width and height cannot be negative");

            return new Command(BLIT, Type.DEFAULT, List.of(texture, x, y, xOffset, yOffset, width, height, textureWidth, textureHeight));
        }

        //<editor-fold desc="Implementation">

        private final int id;
        private final Type type;
        private final List<Object> parameters;

        private Command(int id, Type type, List<Object> parameters) {
            this.id = id;
            this.type = type;
            this.parameters = parameters;
        }

        /**
         * Gets the internal identifier for the command.
         * @return Command ID
         */
        public int getId() {
            return id;
        }

        /**
         * Gets the type of drawing command to use.
         * @return DrawingContext Type
         */
        @NotNull
        public Type getType() {
            return type;
        }

        @Override
        public List<Object> getParameters() {
            return List.copyOf(parameters);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Command command)) return false;
            return id == command.id && type == command.type && Objects.equals(parameters, command.parameters);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, type, parameters);
        }

        @Override
        public String toString() {
            return "Command{" +
                    "id=" + id +
                    ", type=" + type +
                    ", parameters=" + parameters +
                    '}';
        }

        //</editor-fold>
    }
}
