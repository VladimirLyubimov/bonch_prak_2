package ru.voldemar.bonch_prak_2.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import ru.voldemar.bonch_prak_2.gui.MazePainter;

import java.awt.*;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class DijkstraCell extends Cell {

    private int cost = -1;

    public DijkstraCell(Cell cell) {
        super(cell.getType(), cell.getX(), cell.getY());
    }

    @Override
    public void printText(Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("Ariel", Font.PLAIN, MazePainter.cellSize / 3));

        if(getCost() != -1){
            g.drawString("%d".formatted(getCost()), getX() * MazePainter.cellSize, getY() * MazePainter.cellSize + MazePainter.cellSize / 2);
        } else {
            g.drawString("∞", getX() * MazePainter.cellSize, getY() * MazePainter.cellSize + MazePainter.cellSize / 2);
        }

    }

    @Override
    public String toString() {
        return String.valueOf(cost);
    }
}
