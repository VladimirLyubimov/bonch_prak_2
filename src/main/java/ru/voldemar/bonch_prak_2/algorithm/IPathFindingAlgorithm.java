package ru.voldemar.bonch_prak_2.algorithm;

import ru.voldemar.bonch_prak_2.model.Cell;
import ru.voldemar.bonch_prak_2.model.Maze;

import java.util.List;

public interface IPathFindingAlgorithm {

    List<Cell> findPath(Maze<? extends Cell> maze);
}
