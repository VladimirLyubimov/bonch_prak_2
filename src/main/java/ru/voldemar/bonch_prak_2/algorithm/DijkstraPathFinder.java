package ru.voldemar.bonch_prak_2.algorithm;

import ru.voldemar.bonch_prak_2.gui.MazePainter;
import ru.voldemar.bonch_prak_2.model.DijkstraCell;
import ru.voldemar.bonch_prak_2.model.Cell;
import ru.voldemar.bonch_prak_2.model.CellType;
import ru.voldemar.bonch_prak_2.model.Maze;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Реализация {@link IPathFindingAlgorithm}, использующая алгоритм Дейкстры для поиска пути.
 */
public class DijkstraPathFinder extends AbstractPathFinder implements IPathFindingAlgorithm {

    public DijkstraPathFinder(MazePainter mazePainter) {
        super(mazePainter);
    }

    @Override
    protected Cell doFindPath(Maze<? extends Cell> maze) {
        Maze<DijkstraCell> dijkstraMaze = new Maze<>(maze, DijkstraCell::new);
        mazePainter.setMaze(dijkstraMaze);
        final DijkstraCell start = dijkstraMaze.getFirstCellOfType(CellType.START);
        final DijkstraCell end = dijkstraMaze.getFirstCellOfType(CellType.END);
        Queue<DijkstraCell> queue = new LinkedList<>();

        DijkstraCell currentCell = start;
        currentCell.setCost(0);

        while (currentCell != null && currentCell != end) {
            currentCell.markAsCur();
            var neighbours = dijkstraMaze.getNotBlockedNeighbours(currentCell);
            for (var neighbour : neighbours) {
                if( (neighbour.getCost() == -1) || ( neighbour.getCost() > (currentCell.getCost() + 1)) ){
                    neighbour.setCost(currentCell.getCost() + 1);
                    queue.add(neighbour);
                    neighbour.markedAsQueued();
                    neighbour.setPrevOnPath(currentCell);
                }
            }
            doPaint();
            currentCell.markAsVisited();
            currentCell = queue.poll();
        }

        if (currentCell == end) {
            System.out.println("End has been found");
            if (currentCell != null) {
                currentCell.markAsCur();
            }
            return currentCell;
        } else {
            System.out.println("End of the maze is unreachable");
            return null;
        }
    }
}