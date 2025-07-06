package ru.voldemar.bonch_prak_2.algorithm;

import lombok.RequiredArgsConstructor;
import ru.voldemar.bonch_prak_2.gui.MazePainter;
import ru.voldemar.bonch_prak_2.model.AStarCell;
import ru.voldemar.bonch_prak_2.model.Cell;
import ru.voldemar.bonch_prak_2.model.CellType;
import ru.voldemar.bonch_prak_2.model.Maze;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Реализация {@link IPathFindingAlgorithm}, использующая алгоритм A* для поиска пути.
 */
@RequiredArgsConstructor
public class AStarPathFinder extends AbstractPathFinder implements IPathFindingAlgorithm {

    private final MazePainter mazePainter;

    @Override
    protected Cell doFindPath(Maze<Cell> maze) {
        final PriorityQueue<AStarCell> cellToVisit = new PriorityQueue<>(new CellComparator());
        final Set<AStarCell> visitedCells = new HashSet<>();
        Maze<AStarCell> astarMaze = new Maze<>(maze, AStarCell::new);
        mazePainter.setMaze(astarMaze);
        final AStarCell start = astarMaze.getFirstCellOfType(CellType.START);
        final AStarCell end = astarMaze.getFirstCellOfType(CellType.END);
        AStarCell cur = start;
        while (cur != null && cur != end) {
            cur.markAsCur();
            int curCost = cur.getCost();
            var neighbours = astarMaze.getNotBlockedNeighbours(cur);
            for (var neighbour : neighbours) {
                if (visitedCells.contains(neighbour) || (neighbour.getCost() > 0 && neighbour.getCost() < curCost + 1)) {
                    continue;
                }
                neighbour.setCost(curCost + 1);
                neighbour.setHeuristicsEstimate(end);
                neighbour.setPrevOnPath(cur);
                neighbour.markedAsQueued();
                cellToVisit.add(neighbour);
            }
            visitedCells.add(cur);
            cur.markAsVisited();
            cur = cellToVisit.poll();
            mazePainter.repaint();
        }
        System.out.println(astarMaze);
        if (cur == end) {
            System.out.println("End has been found");
            return end;
        } else {
            System.out.println("End of the maze is unreachable");
            return null;
        }
    }

    private static final class CellComparator implements Comparator<AStarCell> {

        @Override
        public int compare(AStarCell o1, AStarCell o2) {
            return Double.compare(o1.getHeuristicsEstimate(), o2.getHeuristicsEstimate());
        }
    }

}
