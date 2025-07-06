package ru.voldemar.bonch_prak_2.algorithm;

import ru.voldemar.bonch_prak_2.model.Cell;
import ru.voldemar.bonch_prak_2.model.Maze;

public interface IPathFindingAlgorithm {

    void findPath(Maze<Cell> maze);
}
