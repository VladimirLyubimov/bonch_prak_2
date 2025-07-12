package ru.voldemar.bonch_prak_2.model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import ru.voldemar.bonch_prak_2.gui.MazePainter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class AStarCell extends Cell {

    private int cost;

    private double heuristicsEstimate = -1;

    public AStarCell(Cell cell) {
        super(cell.getType(), cell.getX(), cell.getY());
    }

    /**
     * Устанавливаем ячейке эвристическую оценку.
     *
     * @param destination Ячейка, до которой рассчитывается оценка.
     */
    public void setHeuristicsEstimate(AStarCell destination) {
        heuristicsEstimate = cost + Math.hypot(
                Math.abs(getX() - destination.getX()),
                Math.abs(getY() - destination.getY())
        );
    }

    @Override
    public void printText(Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("Ariel", Font.PLAIN, MazePainter.cellSize / 9));
        g.drawString("Estimate is %.2f".formatted(heuristicsEstimate), getX() * MazePainter.cellSize + 2, getY() * MazePainter.cellSize + 14);
    }

    @Override
    public String toString() {
        return String.valueOf(cost);
    }
}
