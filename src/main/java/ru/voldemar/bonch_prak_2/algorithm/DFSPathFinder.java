package ru.voldemar.bonch_prak_2.algorithm;

import ru.voldemar.bonch_prak_2.gui.MazePainter;
import ru.voldemar.bonch_prak_2.model.Cell;
import ru.voldemar.bonch_prak_2.model.CellType;
import ru.voldemar.bonch_prak_2.model.Maze;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Реализация {@link IPathFindingAlgorithm}, использующая алгоритм DFS для поиска пути.
 */
public class DFSPathFinder extends AbstractPathFinder implements IPathFindingAlgorithm {

    public DFSPathFinder(MazePainter mazePainter) {
        super(mazePainter);
    }

    @Override
    protected Cell doFindPath(Maze<? extends Cell> maze) {
        Maze<Cell> dfsMaze = new Maze<>(maze, Cell::new);
        mazePainter.setMaze(dfsMaze);
        final Cell start = dfsMaze.getFirstCellOfType(CellType.START);
        final Cell end = dfsMaze.getFirstCellOfType(CellType.END);
        Deque<Cell> stack = new LinkedList<>();
        stack.push(start);

        while (!stack.isEmpty()) {
            Cell currentCell = stack.poll();
            if(currentCell.isVisited()){
                continue;
            }
            currentCell.markAsCur();
            if (currentCell == end) {
                System.out.println("End has been found");
                currentCell.markAsCur();
                return currentCell;
            }
            var neighbours = dfsMaze.getNotBlockedNeighbours(currentCell);
            for (var neighbour : neighbours) {
                if (!neighbour.isVisited()) {
                    stack.push(neighbour);
                    neighbour.markedAsQueued();
                    neighbour.setPrevOnPath(currentCell);
                }
            }
            doPaint();
            currentCell.markAsVisited();
        }

        System.out.println("End of the maze is unreachable");
        return null;
    }
}