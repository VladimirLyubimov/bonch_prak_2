package ru.voldemar.bonch_prak_2.algorithm;

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
public class AStarPathFinder implements IPathFindingAlgorithm {

    private final PriorityQueue<AStarCell> cellToVisit = new PriorityQueue<>(new CellComparator());
    private final Set<AStarCell> visitedCells = new HashSet<>();

    @Override
    public void findPath(Maze<Cell> maze) {
        Maze<AStarCell> astarMaze = new Maze<>(maze, AStarCell::new);
        final AStarCell start = astarMaze.getFirstCellOfType(CellType.START);
        final AStarCell end = astarMaze.getFirstCellOfType(CellType.END);
        AStarCell cur = start;
        while (cur != null && cur != end) {
            int curCost = cur.getCost();
            var neighbours = astarMaze.getNotBlockedNeighbours(cur);
            for (var neighbour : neighbours) {
                if (visitedCells.contains(neighbour)) {
                    continue;
                }
                neighbour.setCost(curCost + 1);
                neighbour.setHeuristicsEstimate(end);
                cellToVisit.add(neighbour);
            }
            visitedCells.add(cur);
            cur = cellToVisit.poll();
        }
        if (cur == end) {
            System.out.println("End has been found");
        } else {
            System.out.println("End of the maze is unreachable");
        }
        System.out.println(astarMaze);
    }
    private static final class CellComparator implements Comparator<AStarCell> {

        @Override
        public int compare(AStarCell o1, AStarCell o2) {
            return Double.compare(o1.getHeuristicsEstimate(), o2.getHeuristicsEstimate());
        }
    }

}
