package ru.voldemar.bonch_prak_2.model;

import lombok.Data;
import ru.voldemar.bonch_prak_2.gui.MazePainter;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

@Data
public class Maze<T extends Cell> implements IPrintable {

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

    public Maze(int rows, int columns, BiFunction<Integer, Integer, T> cellCreator) {
        this.rows = rows;
        this.columns = columns;
        cells = new ArrayList<>(rows);
        for (int i = 0; i < rows; i++) {
            List<T> row = new ArrayList<>(columns);
            for (int j = 0; j < columns; j++) {
                row.add(cellCreator.apply(j, i));
            }
            cells.add(row);
        }
    }

    public Maze(Maze<? extends Cell> maze, Function<Cell, T> cellConstructor) {
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

    public void print(Graphics g) {
        Cell cur = null;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Cell toPrint = cells.get(i).get(j);
                toPrint.print(g);
                if (cur == null && toPrint.isCur()) {
                    cur = toPrint;
                }
            }
        }
        if (cur != null) {
            cur.printPath(g);
        }

        g.setColor(Color.BLACK);
        ((Graphics2D) g).setStroke(new BasicStroke(1));
        for (int i = 0; i <= rows; i++) {
            g.drawLine(0, i * MazePainter.cellSize, columns * MazePainter.cellSize, i * MazePainter.cellSize);
        }
        for (int i = 0; i <= columns; i++) {
            g.drawLine(i * MazePainter.cellSize, 0, i * MazePainter.cellSize, rows * MazePainter.cellSize);
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                cells.get(i).get(j).printText(g);
            }
        }
    }

    public T getFirstCellOfType(CellType type) {
        return cells.stream()
                .flatMap(List::stream)
                .filter(cell -> cell.getType() == type)
                .findFirst()
                .orElseThrow();
    }

    public List<T> getNotBlockedNeighbours(Cell cell) {
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
