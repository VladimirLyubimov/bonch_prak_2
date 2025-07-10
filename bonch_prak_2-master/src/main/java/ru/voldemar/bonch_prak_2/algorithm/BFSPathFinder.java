package ru.voldemar.bonch_prak_2.algorithm;

import ru.voldemar.bonch_prak_2.model.Cell;
import ru.voldemar.bonch_prak_2.model.CellType;
import ru.voldemar.bonch_prak_2.model.Maze;

import java.util.*;

public class BFSPathFinder implements IPathFindingAlgorithm {

    @Override
    public void findPath(Maze<Cell> maze) {
        Cell start = maze.getFirstCellOfType(CellType.START);
        Cell end = maze.getFirstCellOfType(CellType.END);

        Queue<Cell> queue = new LinkedList<>();
        Map<Cell, Cell> cameFrom = new HashMap<>();
        Set<Cell> visited = new HashSet<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Cell current = queue.poll();

            if (current.equals(end)) {
                System.out.println("End has been found");
                markPath(start, end, cameFrom);
                System.out.println(maze);
                return;
            }

            for (Cell neighbor : maze.getNotBlockedNeighbours(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                    cameFrom.put(neighbor, current);
                }
            }
        }

        System.out.println("End of the maze is unreachable");
    }

    private void markPath(Cell start, Cell end, Map<Cell, Cell> cameFrom) {
        Cell current = end;
        while (current != null && !current.equals(start)) {
            if (current.getType() == CellType.FIELD) {
                current.setType(CellType.PATH);
            }
            current = cameFrom.get(current);
        }
    }
}
