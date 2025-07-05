package ru.voldemar.bonch_prak_2;

import ru.voldemar.bonch_prak_2.algorithm.AStarPathFinder;
import ru.voldemar.bonch_prak_2.model.Cell;
import ru.voldemar.bonch_prak_2.model.Maze;
import ru.voldemar.bonch_prak_2.utils.MazeUtils;

import java.io.File;

public class Main {

    public static void main(String... args) {
        Maze<Cell> maze = MazeUtils.loadFromFile(new File("C:\\Users\\VoLdEmAr\\IdeaProjects\\bonch_prak_2\\src\\main\\resources\\mazes\\square_no_block_maze.txt"));
        var pathFinder = new AStarPathFinder();
        pathFinder.findPath(maze);
        System.out.println(maze);
    }
}
