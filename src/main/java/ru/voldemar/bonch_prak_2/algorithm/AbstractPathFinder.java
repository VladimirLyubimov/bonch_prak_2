package ru.voldemar.bonch_prak_2.algorithm;

import ru.voldemar.bonch_prak_2.model.Cell;
import ru.voldemar.bonch_prak_2.model.Maze;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AbstractPathFinder implements IPathFindingAlgorithm {

    @Override
    public List<Cell> findPath(Maze<Cell> maze) {
        Cell end = doFindPath(maze);
        List<Cell> path = new ArrayList<>();
        while (end != null) {
            end.setOnPath(true);
            path.add(new Cell(end));
            end = end.getPrevOnPath();
        }
        Collections.reverse(path);
        return path;
    }

    protected abstract Cell doFindPath(Maze<Cell> maze);
}
