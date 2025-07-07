package ru.voldemar.bonch_prak_2.algorithm;

import lombok.RequiredArgsConstructor;
import ru.voldemar.bonch_prak_2.gui.MazePainter;
import ru.voldemar.bonch_prak_2.model.Cell;
import ru.voldemar.bonch_prak_2.model.Maze;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
public abstract class AbstractPathFinder implements IPathFindingAlgorithm {

    protected final MazePainter mazePainter;

    @Override
    public List<Cell> findPath(Maze<? extends Cell> maze) {
        Cell end = doFindPath(maze);
        doPaint();
        List<Cell> path = new ArrayList<>();
        while (end != null) {
            end.setOnPath(true);
            path.add(new Cell(end));
            end = end.getPrevOnPath();
        }
        Collections.reverse(path);
        return path;
    }

    protected abstract Cell doFindPath(Maze<? extends Cell> maze);

    protected void doPaint() {
        mazePainter.paint();
    }
}
