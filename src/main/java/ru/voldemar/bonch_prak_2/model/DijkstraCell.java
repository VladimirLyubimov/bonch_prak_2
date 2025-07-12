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
        g.setFont(new Font("Ariel", Font.PLAIN, MazePainter.cellSize / 9));

        if(getCost() != -1){
            g.drawString("Cost is %d".formatted(getCost()), getX() * MazePainter.cellSize + 2, getY() * MazePainter.cellSize + 14);
        } else {
            g.drawString("Cost is unknown", getX() * MazePainter.cellSize + 2, getY() * MazePainter.cellSize + 14);
        }

    }

    @Override
    public String toString() {
        return String.valueOf(cost);
    }
}
