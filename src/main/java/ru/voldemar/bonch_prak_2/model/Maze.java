package ru.voldemar.bonch_prak_2.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

@Data
public class Maze<T extends Cell> {

    private final List<List<T>> cells;
    private final int rows;
    private final int columns;

    public Maze(T[][] cells) {
        rows = cells.length;
        columns = cells[0].length;
        this.cells = new ArrayList<>(rows);
        for (int i = 0; i < rows; i++) {
            this.cells.add(Arrays.asList(cells[i]));
        }
    }

    public Maze(Maze<Cell> maze, Function<Cell, T> cellConstructor) {
        rows = maze.getRows();
        columns = maze.getColumns();
        cells = new ArrayList<>(rows);
        for (int i = 0; i < rows; i++) {
            List<T> row = new ArrayList<>(columns);
            for (int j = 0; j < columns; j++) {
                row.add(cellConstructor.apply(maze.getCells().get(i).get(j)));
            }
            cells.add(row);
        }
    }

    public T getFirstCellOfType(CellType type) {
        return cells.stream()
                .flatMap(List::stream)
                .filter(cell -> cell.getType() == type)
                .findFirst()
                .orElseThrow();
    }

    public List<T> getNotBlockedNeighbours(T cell) {
        int x = cell.getX();
        int y = cell.getY();
        List<T> res = new ArrayList<>();
        if (x != 0 && x != columns - 1) {
            addToListIfNotBlock(cells.get(y).get(x - 1), res);
            addToListIfNotBlock(cells.get(y).get(x + 1), res);
        }
        if (x == 0 && x != columns - 1) {
            addToListIfNotBlock(cells.get(y).get(x + 1), res);
        }
        if (x == columns - 1 && x != 0) {
            addToListIfNotBlock(cells.get(y).get(x - 1), res);
        }
        if (y != 0 && y != rows - 1) {
            addToListIfNotBlock(cells.get(y - 1).get(x), res);
            addToListIfNotBlock(cells.get(y + 1).get(x), res);
        }
        if (y == 0 && y != rows - 1) {
            addToListIfNotBlock(cells.get(y + 1).get(x), res);
        }
        if (y == rows - 1 && y != 0) {
            addToListIfNotBlock(cells.get(y - 1).get(x), res);
        }
        return res;
    }

    private void addToListIfNotBlock(T cell, List<T> cells) {
        if (cell.getType() != CellType.BLOCK) {
            cells.add(cell);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (var row : cells) {
            for (var cell : row) {
                sb.append(cell);
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}
