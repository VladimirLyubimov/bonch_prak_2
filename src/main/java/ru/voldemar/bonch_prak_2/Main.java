package ru.voldemar.bonch_prak_2;

import ru.voldemar.bonch_prak_2.algorithm.AStarPathFinder;
import ru.voldemar.bonch_prak_2.gui.MazePainter;
import ru.voldemar.bonch_prak_2.model.Cell;
import ru.voldemar.bonch_prak_2.model.CellType;
import ru.voldemar.bonch_prak_2.model.Maze;
import ru.voldemar.bonch_prak_2.utils.MazeUtils;

import javax.swing.*;
import java.io.File;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;

public class Main {

    public static void main(String... args) {
        JFrame frame = new JFrame("Pathfinder");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setSize(1000, 1000);
        frame.setVisible(true);
        Maze<Cell> maze = MazeUtils.loadFromFile(new File("C:\\Users\\VoLdEmAr\\IdeaProjects\\bonch_prak_2\\src\\main\\resources\\mazes\\square_maze_with_blocks.txt"));
        MazePainter mazePainter = new MazePainter();
        mazePainter.setMaze(maze);
        frame.add(mazePainter);
        frame.revalidate();
        CompletableFuture.runAsync(() -> {
                    var pathFinder = new AStarPathFinder(mazePainter);
                    var path = pathFinder.findPath(maze);
//        maze.getCells()
//                .stream()
//                .flatMap(Collection::stream)
//                .filter(cell -> cell.getType() == CellType.FIELD)
//                .filter(path::contains)
//                .forEach(cell -> cell.setOnPath(true));
                    System.out.println(maze);
                    System.out.println(path);
                }
        );
    }
}
