package xyz.gmitch215.socketmc.screen.layout;

import org.jetbrains.annotations.NotNull;
import xyz.gmitch215.socketmc.util.math.Divisor;
import xyz.gmitch215.socketmc.util.math.MathUtil;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Represents a layout with elements in a grid format. The Grid Layout starts with a size of 0, and is expanded as elements are added.
 */
public final class GridLayout extends AbstractLayout {

    @Serial
    private static final long serialVersionUID = -820924554520321304L;

    private final List<LayoutElement> children = new ArrayList<>();
    private final List<Cell> cells = new ArrayList<>();
    private int rowSpacing;
    private int columnSpacing;

    /**
     * Creates a new GridLayout at the origin.
     */
    public GridLayout() {
        this(0, 0);
    }

    /**
     * Creates a new GridLayout with the specified position.
     * @param x The X position
     * @param y The Y position
     */
    public GridLayout(int x, int y) {
        super(x, y, 0, 0);
    }

    /**
     * Gets an immutable copy of the children in the GridLayout.
     * @return GridLayout Children
     */
    @NotNull
    public List<LayoutElement> getChildren() {
        return List.copyOf(children);
    }

    /**
     * Gets an immutable copy of the cells in the GridLayout.
     * @return GridLayout Cells
     */
    @NotNull
    public List<Cell> getCells() {
        return List.copyOf(cells);
    }

    /**
     * Gets the row spacing between cells.
     * @return Row Spacing
     */
    public int getRowSpacing() {
        return rowSpacing;
    }

    /**
     * Sets the row spacing between cells.
     * @param rowSpacing Row Spacing
     * @return this class, for chaining
     */
    @NotNull
    public GridLayout setRowSpacing(int rowSpacing) {
        this.rowSpacing = rowSpacing;
        return this;
    }

    /**
     * Gets the column spacing between cells.
     * @return Column Spacing
     */
    public int getColumnSpacing() {
        return columnSpacing;
    }

    /**
     * Sets the column spacing between cells.
     * @param columnSpacing Column Spacing
     * @return this class, for chaining
     */
    @NotNull
    public GridLayout setColumnSpacing(int columnSpacing) {
        this.columnSpacing = columnSpacing;
        return this;
    }

    /**
     * Sets the row and column spacing between cells.
     * @param spacing The spacing
     * @return this class, for chaining
     */
    @NotNull
    public GridLayout setSpacing(int spacing) {
        this.rowSpacing = spacing;
        this.columnSpacing = spacing;
        return this;
    }

    /**
     * Sets the row and column spacing between cells.
     * @param row The row spacing
     * @param column The column spacing
     * @return this class, for chaining
     */
    @NotNull
    public GridLayout setSpacing(int row, int column) {
        this.rowSpacing = row;
        this.columnSpacing = column;
        return this;
    }

    /**
     * Adds a cell to the GridLayout using the default layout settings.
     * @param element The element to add
     * @param row The row of the cell
     * @param column The column of the cell
     * @return The element that was added
     * @param <T> The type of the element
     * @throws IllegalArgumentException if the element or size is null or the row/column coordinates are negative
     */
    @NotNull
    public <T extends LayoutElement> T addCell(@NotNull T element, int row, int column) throws IllegalArgumentException {
        return addCell(element, row, column, LayoutSettings.create());
    }

    /**
     * Adds a cell to the GridLayout using the default layout settings.
     * @param element The element to add
     * @param row The row of the cell
     * @param column The column of the cell
     * @param occupiedRows The number of rows the cell occupies
     * @param occupiedColumns The number of columns the cell occupies
     * @return The element that was added
     * @param <T> The type of the element
     * @throws IllegalArgumentException if the element or size is null, the row/column coordinates are negative, or the occupied size is less than 1
     */
    @NotNull
    public <T extends LayoutElement> T addCell(@NotNull T element, int row, int column, int occupiedRows, int occupiedColumns) throws IllegalArgumentException {
        return addCell(element, row, column, occupiedRows, occupiedColumns, LayoutSettings.create());
    }

    /**
     * Adds a cell to the GridLayout.
     * @param element The element to add
     * @param row The row of the cell
     * @param column The column of the cell
     * @param settings The layout settings for the cell
     * @return The element that was added
     * @param <T> The type of the element
     * @throws IllegalArgumentException if the element or size is null or the row/column coordinates are negative
     */
    @NotNull
    public <T extends LayoutElement> T addCell(@NotNull T element, int row, int column, @NotNull LayoutSettings settings) throws IllegalArgumentException {
        return addCell(element, row, column, 1, 1, settings);
    }

    /**
     * Adds a cell to the GridLayout.
     * @param element The element to add
     * @param row The row of the cell
     * @param column The column of the cell
     * @param occupiedRows The number of rows the cell occupies
     * @param occupiedColumns The number of columns the cell occupies
     * @param settings The layout settings for the cell
     * @return The element that was added
     * @param <T> The type of the element
     * @throws IllegalArgumentException if the element or size is null, the row/column coordinates are negative, or the occupied size is less than 1
     */
    @NotNull
    public <T extends LayoutElement> T addCell(@NotNull T element, int row, int column, int occupiedRows, int occupiedColumns, @NotNull LayoutSettings settings) throws IllegalArgumentException {
        if (element == null) throw new IllegalArgumentException("Element cannot be null");
        if (row < 0) throw new IllegalArgumentException("Row must be at least 0");
        if (column < 0) throw new IllegalArgumentException("Column must be at least 0");
        if (occupiedRows < 1) throw new IllegalArgumentException("Occupied rows must be at least 1");
        if (occupiedColumns < 1) throw new IllegalArgumentException("Occupied columns must be at least 1");
        if (settings == null) throw new IllegalArgumentException("Settings cannot be null");

        cells.add(new Cell(element, row, column, occupiedRows, occupiedColumns, settings));
        children.add(element);
        return element;
    }

    /**
     * Adds a cell to the GridLayout.
     * @param element The element to add
     * @param row The row of the cell
     * @param column The column of the cell
     * @param settings The generator for the cell settings
     * @return The element that was added
     * @param <T> The type of the element
     * @throws IllegalArgumentException if the element or size is null or the row/column coordinates are negative
     */
    @NotNull
    public <T extends LayoutElement> T addCell(@NotNull T element, int row, int column, @NotNull Supplier<LayoutSettings> settings) throws IllegalArgumentException {
        return addCell(element, row, column, settings.get());
    }

    /**
     * Adds a cell to the GridLayout.
     * @param element The element to add
     * @param row The row of the cell
     * @param column The column of the cell
     * @param occupiedRows The number of rows the cell occupies
     * @param occupiedColumns The number of columns the cell occupies
     * @param settings The generator for the cell settings
     * @return The element that was added
     * @param <T> The type of the element
     * @throws IllegalArgumentException if the element or size is null, the row/column coordinates are negative, or the occupied size is less than 1
     */
    @NotNull
    public <T extends LayoutElement> T addCell(@NotNull T element, int row, int column, int occupiedRows, int occupiedColumns, @NotNull Supplier<LayoutSettings> settings) throws IllegalArgumentException {
        return addCell(element, row, column, occupiedRows, occupiedColumns, settings.get());
    }

    /**
     * Adds a cell to the GridLayout.
     * @param element The element to add
     * @param row The row of the cell
     * @param column The column of the cell
     * @param settings The function to apply to {@link #createDefaultSettings()}
     * @return The element that was added
     * @param <T> The type of the element
     * @throws IllegalArgumentException if the element or size is null or the row/column coordinates are negative
     */
    @NotNull
    public <T extends LayoutElement> T addCell(@NotNull T element, int row, int column, @NotNull Consumer<LayoutSettings> settings) throws IllegalArgumentException {
        LayoutSettings settings0 = createDefaultSettings();
        settings.accept(settings0);
        return addCell(element, row, column, settings0);
    }

    /**
     * Adds a cell to the GridLayout.
     * @param element The element to add
     * @param row The row of the cell
     * @param column The column of the cell
     * @param occupiedRows The number of rows the cell occupies
     * @param occupiedColumns The number of columns the cell occupies
     * @param settings The function to apply to {@link #createDefaultSettings()}
     * @return The element that was added
     * @param <T> The type of the element
     * @throws IllegalArgumentException if the element or size is null, the row/column coordinates are negative, or the occupied size is less than 1
     */
    @NotNull
    public <T extends LayoutElement> T addCell(@NotNull T element, int row, int column, int occupiedRows, int occupiedColumns, @NotNull Consumer<LayoutSettings> settings) throws IllegalArgumentException {
        LayoutSettings settings0 = createDefaultSettings();
        settings.accept(settings0);
        return addCell(element, row, column, occupiedRows, occupiedColumns, settings0);
    }

    @Override
    @NotNull
    public LayoutSettings createDefaultSettings() {
        return LayoutSettings.create();
    }

    @Override
    public void visitChildren(@NotNull Consumer<LayoutElement> visitor) {
        children.forEach(visitor);
    }
    
    @Override
    public void arrangeElements() {
        int n1, n2, n3;
        super.arrangeElements();

        int lastRow = 0;
        int lastColumn = 0;

        for (Cell cell : cells) {
            lastRow = Math.max(cell.getLastOccupiedRow(), lastRow);
            lastColumn = Math.max(cell.getLastOccupiedColumn(), lastColumn);
        }

        int[] columns = new int[lastColumn + 1];
        int[] rows = new int[lastRow + 1];

        for (Cell cell : cells) {
            n3 = cell.getHeight() - (cell.occupiedRows - 1) * rowSpacing;
            Divisor divisor = new Divisor(n3, cell.occupiedRows);

            for (n2 = cell.row; n2 <= cell.getLastOccupiedRow(); n2++)
                rows[n2] = Math.max(rows[n2], divisor.next());

            n2 = cell.getWidth() - (cell.occupiedColumns - 1) * columnSpacing;
            Divisor divisor2 = new Divisor(n2, cell.occupiedColumns);
            for (n1 = cell.column; n1 <= cell.getLastOccupiedColumn(); n1++)
                columns[n1] = Math.max(columns[n1], divisor2.next());
        }

        int[] finalRows = new int[lastRow + 1];
        int[] finalColumns = new int[lastColumn + 1];

        finalColumns[0] = 0;
        for (n3 = 1; n3 <= lastColumn; n3++)
            finalColumns[n3] = finalColumns[n3 - 1] + columns[n3 - 1] + columnSpacing;

        finalRows[0] = 0;
        for (n3 = 1; n3 <= lastRow; n3++)
            finalRows[n3] = finalRows[n3 - 1] + rows[n3 - 1] + rowSpacing;

        for (Cell cell : cells) {
            int n;

            n2 = 0;
            for (n = cell.column; n <= cell.getLastOccupiedColumn(); n++)
                n2 += columns[n];
            cell.setHorizontalBounds(getX() + finalColumns[cell.column], n2 + columnSpacing * (cell.occupiedColumns - 1));

            n = 0;
            for (n1 = cell.row; n1 <= cell.getLastOccupiedRow(); n1++)
                n += rows[n1];

            cell.setVerticalBounds(getY() + finalRows[cell.row], n + rowSpacing * (cell.occupiedRows - 1));
        }

        width = finalColumns[lastColumn] + columns[lastColumn];
        height = finalRows[lastRow] + rows[lastRow];
    }

    /**
     * @deprecated Use {@link #addCell(LayoutElement, int, int, int, int, LayoutSettings)} instead
     */
    @Deprecated
    @Override
    public <T extends LayoutElement> T addElement(@NotNull T element, @NotNull LayoutSettings settings) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Cannot add elements to a GridLayout directly. Use addCell instead.");
    }

    /**
     * Creates a new RowHelper with one column.
     * @return RowHelper
     */
    @NotNull
    public RowHelper newRow() {
        return new RowHelper(1);
    }

    /**
     * Creates a new RowHelper with the specified number of columns.
     * @param columns The number of columns
     * @return RowHelper
     */
    @NotNull
    public RowHelper newRow(int columns) {
        return new RowHelper(columns);
    }

    /**
     * Utility class for adding elements in rows.
     */
    public final class RowHelper {
        private final int columns;
        private int index;

        private RowHelper(int columns) {
            this.columns = columns;
        }

        /**
         * Adds an element to this row.
         * @param child The element to add
         * @return The element that was added
         * @param <T> The type of the element
         * @throws IllegalArgumentException if the element is null
         */
        @NotNull
        public <T extends LayoutElement> T add(@NotNull T child) throws IllegalArgumentException {
            return add(child, 1);
        }

        /**
         * Adds an element to this row.
         * @param child The element to add
         * @param occupiedColumns The number of columns the element occupies
         * @return The element that was added
         * @param <T> The type of the element
         * @throws IllegalArgumentException if the element is null or the occupied columns is less than 1
         */
        @NotNull
        public <T extends LayoutElement> T add(@NotNull T child, int occupiedColumns) throws IllegalArgumentException {
            return add(child, occupiedColumns, GridLayout.this.createDefaultSettings());
        }

        /**
         * Adds an element to this row.
         * @param child The element to add
         * @param settings The layout settings for the element
         * @return The element that was added
         * @param <T> The type of the element
         * @throws IllegalArgumentException if the element is null
         */
        @NotNull
        public <T extends LayoutElement> T add(@NotNull T child, LayoutSettings settings) throws IllegalArgumentException {
            return add(child, 1, settings);
        }

        /**
         * Adds an element to this row.
         * @param child The element to add
         * @param occupiedColumns The number of columns the element occupies
         * @param settings The layout settings for the element
         * @return The element that was added
         * @param <T> The type of the element
         * @throws IllegalArgumentException if the element or settings are null or the occupied columns is less than 1
         */
        @NotNull
        public <T extends LayoutElement> T add(@NotNull T child, int occupiedColumns, LayoutSettings settings) throws IllegalArgumentException {
            if (child == null) throw new IllegalArgumentException("Child cannot be null");
            if (occupiedColumns < 1) throw new IllegalArgumentException("Occupied columns must be at least 1");
            if (settings == null) throw new IllegalArgumentException("Layout settings cannot be null");
            
            int div = index / columns;
            int mod = index % columns;
            
            if (mod + occupiedColumns > columns) {
                div++;
                mod = 0;
                index = MathUtil.roundToward(index, columns);
            }
            
            index += occupiedColumns;
            return GridLayout.this.addCell(child, div, mod, 1, occupiedColumns, settings);
        }

        /**
         * Adds an element to this row.
         * @param child The element to add
         * @param settings The generator for the layout settings
         * @return The element that was added
         * @param <T> The type of the element
         * @throws IllegalArgumentException if the element is null
         */
        @NotNull
        public <T extends LayoutElement> T add(@NotNull T child, Supplier<LayoutSettings> settings) throws IllegalArgumentException {
            return add(child, settings.get());
        }

        /**
         * Adds an element to this row.
         * @param child The element to add
         * @param occupiedColumns The number of columns the element occupies
         * @param settings The generator for the layout settings
         * @return The element that was added
         * @param <T> The type of the element
         * @throws IllegalArgumentException if the element or settings are null or the occupied columns is less than 1
         */
        @NotNull
        public <T extends LayoutElement> T add(@NotNull T child, int occupiedColumns, Supplier<LayoutSettings> settings) throws IllegalArgumentException {
            return add(child, occupiedColumns, settings.get());
        }

        /**
         * Adds an element to this row.
         * @param child The element to add
         * @param settings The function to apply on {@link GridLayout#createDefaultSettings()}
         * @return The element that was added
         * @param <T> The type of the element
         * @throws IllegalArgumentException if the element is null
         */
        @NotNull
        public <T extends LayoutElement> T add(@NotNull T child, Consumer<LayoutSettings> settings) throws IllegalArgumentException {
            LayoutSettings settings0 = GridLayout.this.createDefaultSettings();
            settings.accept(settings0);
            return add(child, settings0);
        }

        /**
         * Adds an element to this row.
         * @param child The element to add
         * @param occupiedColumns The number of columns the element occupies
         * @param settings The function to apply on {@link GridLayout#createDefaultSettings()}
         * @return The element that was added
         * @param <T> The type of the element
         * @throws IllegalArgumentException if the element or settings are null or the occupied columns is less than 1
         */
        @NotNull
        public <T extends LayoutElement> T add(@NotNull T child, int occupiedColumns, Consumer<LayoutSettings> settings) throws IllegalArgumentException {
            LayoutSettings settings0 = GridLayout.this.createDefaultSettings();
            settings.accept(settings0);
            return add(child, occupiedColumns, settings0);
        }

        /**
         * Gets the GridLayout that the RowHelper is associated with.
         * @return GridLayout
         */
        @NotNull
        public GridLayout getGrid() {
            return GridLayout.this;
        }
    }

    /**
     * A Child Container inside of a GridLayout.
     */
    public static final class Cell extends AbstractChildContainer {

        @Serial
        private static final long serialVersionUID = 7390812570276991443L;

        private final int row;
        private final int column;
        private final int occupiedRows;
        private final int occupiedColumns;

        /**
         * Creates a new Cell.
         * @param child The child element
         * @param row The row of the cell
         * @param column The column of the cell
         * @param occupiedRows The number of rows the cell occupies
         * @param occupiedColumns The number of columns the cell occupies
         * @param layoutSettings Element Layout Settings
         */
        public Cell(@NotNull LayoutElement child, int row, int column, int occupiedRows, int occupiedColumns, LayoutSettings layoutSettings) {
            super(child, layoutSettings);

            this.row = row;
            this.column = column;
            this.occupiedRows = occupiedRows;
            this.occupiedColumns = occupiedColumns;
        }

        /**
         * Gets the row of the cell.
         * @return Cell Row
         */
        public int getRow() {
            return row;
        }

        /**
         * Gets the column of the cell.
         * @return Cell Column
         */
        public int getColumn() {
            return column;
        }

        /**
         * Gets the number of rows the cell occupies.
         * @return Occupied Rows
         */
        public int getOccupiedRows() {
            return occupiedRows;
        }

        /**
         * Gets the number of columns the cell occupies.
         * @return Occupied Columns
         */
        public int getOccupiedColumns() {
            return occupiedColumns;
        }

        /**
         * Gets the last row that the cell occupies.
         * @return The last row that the cell occupies
         */
        public int getLastOccupiedRow() {
            return this.row + this.occupiedRows - 1;
        }

        /**
         * Gets the last column that the cell occupies.
         * @return The last column that the cell occupies
         */
        public int getLastOccupiedColumn() {
            return this.column + this.occupiedColumns - 1;
        }
    }
}
