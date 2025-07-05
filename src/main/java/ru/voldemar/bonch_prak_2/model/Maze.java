package ru.voldemar.bonch_prak_2.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class Maze {

    private Cell[][] cells;
    private int rows;
    private int columns;

    public Maze(Cell[][] cells) {
        this.cells = cells;
        rows = cells.length;
        columns = cells[0].length;
    }

    public Cell getFirstCellOfType(CellType type) {
        return Arrays.stream(cells)
                .flatMap(Arrays::stream)
                .filter(cell -> cell.getType() == type)
                .findFirst()
                .orElseThrow();
    }

    // TODO добавить фильтр блоков
    public List<Cell> getNotBlockedNeighbours(Cell cell) {
        int x = cell.getX();
        int y = cell.getY();
        List<Cell> res = new ArrayList<>();
        if (x != 0 && x != columns - 1) {
            res.add(cells[y][x - 1]);
            res.add(cells[y][x + 1]);
        }
        if (x == 0 && x != columns - 1) {
            res.add(cells[y][x + 1]);
        }
        if (x == columns - 1 && x != 0) {
            res.add(cells[y][x - 1]);
        }
        if (y != 0 && y != rows - 1) {
            res.add(cells[y - 1][x]);
            res.add(cells[y + 1][x]);
        }
        if (y == 0 && y != rows - 1) {
            res.add(cells[y + 1][x]);
        }
        if (y == rows - 1 && y != 0) {
            res.add(cells[y - 1][x]);
        }
        return res;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (var row : cells) {
            for (var cell : row) {
                sb.append(cell.getCost());
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}
