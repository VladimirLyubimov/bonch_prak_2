package ru.voldemar.bonch_prak_2.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cell {

    // TODO make final
    private CellType type;

    private int cost;

    @EqualsAndHashCode.Include
    private final int x;

    @EqualsAndHashCode.Include
    private final int y;

    private boolean waitingProcessing;

    private boolean selected;

    private int heuristicsEstimate = -1;

    public Cell(CellType type, int x, int y) {
        this.type = type;
        this.x = x;
        this.y = y;
    }
}
