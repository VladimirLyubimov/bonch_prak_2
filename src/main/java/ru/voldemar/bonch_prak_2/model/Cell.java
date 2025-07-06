package ru.voldemar.bonch_prak_2.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.awt.*;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cell implements IPrintableCell {

    private final CellType type;

    @EqualsAndHashCode.Include
    private final int x;

    @EqualsAndHashCode.Include
    private final int y;

    private Cell prevOnPath;

    private boolean isOnPath;

    public Cell(CellType type, int x, int y) {
        this.type = type;
        this.x = x;
        this.y = y;
    }

    public Cell(Cell cell) {
        this.type = cell.getType();
        this.x = cell.getX();
        this.y = cell.getY();
        this.prevOnPath = cell.getPrevOnPath();
        this.isOnPath = cell.isOnPath();
    }

    @Override
    public void print(Graphics g) {
        g.setColor(isOnPath && type == CellType.FIELD ? Color.GREEN : type.getColor());
        g.fillRect(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
    }
}
