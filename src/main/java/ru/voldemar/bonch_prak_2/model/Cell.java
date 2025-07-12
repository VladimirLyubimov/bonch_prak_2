package ru.voldemar.bonch_prak_2.model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.voldemar.bonch_prak_2.gui.MazePainter;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cell implements IPrintableCell {

    // TODO Make final
    private CellType type;

    @EqualsAndHashCode.Include
    private final int x;

    @EqualsAndHashCode.Include
    private final int y;

    private Cell prevOnPath;

    private boolean isOnPath;

    private boolean visited;

    private boolean cur;

    private boolean queued;

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

    public void markAsVisited() {
        this.visited = true;
        this.cur = false;
        this.queued = false;
    }

    public void markAsCur() {
        this.cur = true;
        this.queued = false;
        this.visited = false;
    }

    public void markedAsQueued() {
        this.queued = true;
        this.visited = false;
        this.cur = false;
    }

    @Override
    public void print(Graphics g) {
        if (type == CellType.START || type == CellType.END) {
            g.setColor(getType().getColor());
        } else {
            if (isCur()) {
                g.setColor(Color.RED);
            } else if (isVisited()) {
                g.setColor(Color.YELLOW);
            } else if (isQueued()) {
                g.setColor(Color.CYAN);
            } else {
                g.setColor(getType().getColor());
            }
        }
        g.fillRect(getX() *  MazePainter.cellSize, getY() *  MazePainter.cellSize,  MazePainter.cellSize,  MazePainter.cellSize);

        g.setColor(Color.WHITE);
        int textCoordX = getX() * MazePainter.cellSize + 10;
        int textCoordY = getY() * MazePainter.cellSize + 90;
        if (type == CellType.START) {
            g.drawString("START", textCoordX, textCoordY);
        } else if (type == CellType.END) {
            g.drawString("END", textCoordX, textCoordY);
        }
    }

    @Override
    public void printPath(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        if (getPrevOnPath() != null) {
            g.setColor(Color.GREEN);
            g2.setStroke(new BasicStroke(10));
            g2.draw(new Line2D.Double(getXCenter(), getYCenter(), getPrevOnPath().getXCenter(), getPrevOnPath().getYCenter()));
            getPrevOnPath().printPath(g);
        }
    }

    protected double getXCenter() {
        return getX() *  MazePainter.cellSize + (double)  MazePainter.cellSize / 2;
    }

    protected double getYCenter() {
        return getY() *  MazePainter.cellSize + (double)  MazePainter.cellSize / 2;
    }
}
