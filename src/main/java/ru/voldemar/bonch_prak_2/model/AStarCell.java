package ru.voldemar.bonch_prak_2.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

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
    public String toString() {
        return String.valueOf(cost);
    }
}
