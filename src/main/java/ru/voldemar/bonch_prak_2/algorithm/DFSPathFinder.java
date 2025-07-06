package ru.voldemar.bonch_prak_2.algorithm;

import ru.voldemar.bonch_prak_2.model.Cell;
import ru.voldemar.bonch_prak_2.model.Maze;

import java.util.List;

/**
 * Реализация {@link IPathFindingAlgorithm}, использующая поиск в глубину для поиска пути.
 */
public class DFSPathFinder implements IPathFindingAlgorithm {

    @Override
    public List<Cell> findPath(Maze<Cell> maze) {
        return List.of();
        // TODO реализовать поиск пути поиском в глубину (https://ru.wikipedia.org/wiki/%D0%9F%D0%BE%D0%B8%D1%81%D0%BA_%D0%B2_%D0%B3%D0%BB%D1%83%D0%B1%D0%B8%D0%BD%D1%83)
    }
}
