package ru.voldemar.bonch_prak_2.algorithm;

import ru.voldemar.bonch_prak_2.gui.MazePainter;
import ru.voldemar.bonch_prak_2.model.Cell;
import ru.voldemar.bonch_prak_2.model.CellType;
import ru.voldemar.bonch_prak_2.model.Maze;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * Реализация {@link IPathFindingAlgorithm}, использующая поиск в ширину для поиска пути.
 */
public class BFSPathFinder extends AbstractPathFinder {

    public BFSPathFinder(MazePainter mazePainter) {
        super(mazePainter);
    }

    @Override
    protected Cell doFindPath(Maze<? extends Cell> maze) {
        Cell start = maze.getFirstCellOfType(CellType.START);
        Cell end = maze.getFirstCellOfType(CellType.END);

        Queue<Cell> queue = new LinkedList<>();
        Set<Cell> visited = new HashSet<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            Cell current = queue.poll();
            current.markAsCur();
            visited.add(current);

            if (current.equals(end)) {
                System.out.println("End has been found");
                return current;
            }

            for (Cell neighbor : maze.getNotBlockedNeighbours(current)) {
                if (!visited.contains(neighbor) && neighbor.getPrevOnPath() == null) {
                    queue.add(neighbor);
                    neighbor.markedAsQueued();
                    neighbor.setPrevOnPath(current);
                }
            }
            doPaint();
            current.markAsVisited();
        }

        System.out.println("End of the maze is unreachable");
        return null;
    }
}
