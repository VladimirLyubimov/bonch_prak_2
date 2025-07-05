package ru.voldemar.bonch_prak_2;

import ru.voldemar.bonch_prak_2.algorithm.AStar;
import ru.voldemar.bonch_prak_2.model.Cell;
import ru.voldemar.bonch_prak_2.model.CellType;
import ru.voldemar.bonch_prak_2.model.Maze;

public class Main {

    public static void main(String... args) {
        Cell[][] cells = new Cell[5][5];
        for(int i = 0; i < 5; i++){
            for (int j = 0; j < 5; j++) {
                cells[i][j] = new Cell(CellType.SPACE, j, i);
            }
        }
        cells[0][0].setType(CellType.START);
        cells[4][0].setType(CellType.FINISH);
        Maze maze = new Maze(cells);
        var pathFinder = new AStar();
        pathFinder.findPath(maze);
        System.out.println(maze);
    }
}
