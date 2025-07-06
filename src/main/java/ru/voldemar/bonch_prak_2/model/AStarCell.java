package ru.voldemar.bonch_prak_2.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class AStarCell extends Cell {

    private int cost;

    private double heuristicsEstimate = -1;

    private boolean visited;

    private boolean cur;

    private boolean queued;

    public AStarCell(Cell cell) {
        super(cell.getType(), cell.getX(), cell.getY());
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

    /**
     * Устанавливаем ячейке
     *
     * @param destination
     */
    public void setHeuristicsEstimate(AStarCell destination) {
        heuristicsEstimate = cost + Math.hypot(
                Math.abs(getX() - destination.getX()),
                Math.abs(getY() - destination.getY())
        );
    }

    @Override
    public void print(Graphics g) {
//        if (getType() == CellType.FIELD) {
            if (cur) {
                g.setColor(Color.RED);
            } else if (visited) {
                g.setColor(Color.YELLOW);
            } else if (queued) {
                g.setColor(Color.CYAN);
            } else {
//        } else {
            g.setColor(getType().getColor());
        }
        g.fillRect(getX() * CELL_SIZE, getY() * CELL_SIZE, CELL_SIZE, CELL_SIZE);
        g.setColor(Color.BLACK);
        g.drawString("Estimate is %.2f".formatted(heuristicsEstimate), getX() * CELL_SIZE, getY() * CELL_SIZE + 10);
    }

    @Override
    public String toString() {
        return String.valueOf(cost);
    }
}
