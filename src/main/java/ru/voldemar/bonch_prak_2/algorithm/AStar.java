package ru.voldemar.bonch_prak_2.algorithm;

import ru.voldemar.bonch_prak_2.model.Cell;
import ru.voldemar.bonch_prak_2.model.CellType;
import ru.voldemar.bonch_prak_2.model.Maze;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class AStar implements IPathFindingAlgorithm {

    private final PriorityQueue<Cell> cellToVisit = new PriorityQueue<>(new CellComparator());
    private final Set<Cell> visitedCells = new HashSet<>();

    @Override
    public void findPath(Maze maze) {
        final Cell start = maze.getFirstCellOfType(CellType.START);
        final Cell finish = maze.getFirstCellOfType(CellType.FINISH);
        Cell cur = start;
        while (cur != null && cur != finish) {
            System.out.println(maze);
            int curCost = cur.getCost();
            var neighbours = maze.getNotBlockedNeighbours(cur);
            for (var neighbour : neighbours) {
                if (visitedCells.contains(neighbour)) {
                    continue;
                }
                neighbour.setCost(curCost + 1);
                setHeuristics(neighbour, finish);
                cellToVisit.add(neighbour);
            }
            visitedCells.add(cur);
            cur = cellToVisit.poll();
        }
    }

    private void setHeuristics(Cell cell, Cell finish) {
         cell.setHeuristicsEstimate(
                (int) (cell.getCost() + Math.hypot(
                        Math.abs(cell.getX() - finish.getX()),
                        Math.abs(cell.getY() - finish.getY()))
                )
        );
    }

    private static final class CellComparator implements Comparator<Cell> {

        @Override
        public int compare(Cell o1, Cell o2) {
            return Integer.compare(o1.getHeuristicsEstimate() + o1.getCost(), o2.getHeuristicsEstimate() + o2.getCost());
        }
    }
}
