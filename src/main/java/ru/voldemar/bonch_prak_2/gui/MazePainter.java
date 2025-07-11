package ru.voldemar.bonch_prak_2.gui;

import lombok.Getter;
import lombok.Setter;
import lombok.Synchronized;
import ru.voldemar.bonch_prak_2.algorithm.*;
import ru.voldemar.bonch_prak_2.model.Cell;
import ru.voldemar.bonch_prak_2.model.CellType;
import ru.voldemar.bonch_prak_2.model.Maze;

import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.function.Function;

@Setter
@Getter
public class MazePainter extends JComponent {

    private static final int DEFAULT_ROW_COUNT = 7;
    private static final int DEFAULT_COLUMN_COUNT = 10;

    public static int cellSize = 100;

    private static final Map<AlgoType, Function<MazePainter, IPathFindingAlgorithm>> PATH_FINDER_FACTORY = Map.of(
            AlgoType.A_STAR, AStarPathFinder::new,
            AlgoType.BFS, BFSPathFinder::new,
            AlgoType.DIJKSTRA, DijkstraPathFinder::new,
            AlgoType.DFS, DFSPathFinder::new
    );

    private int stepDelay;
    private transient IPathFindingAlgorithm pathFinder;
    private transient Maze<? extends Cell> maze;
    private CellType addType;

    public MazePainter() {
        super();
        setPreferredSize(new Dimension(cellSize * DEFAULT_COLUMN_COUNT, cellSize * DEFAULT_ROW_COUNT));
        reset();
    }

    @Synchronized
    public void reset() {
        maze = new Maze<>(DEFAULT_ROW_COUNT, DEFAULT_COLUMN_COUNT, (x, y) -> new Cell(CellType.FIELD, x, y));
        pathFinder = null;
        repaint();
    }

    @Synchronized
    public void setAlgo(AlgoType algoType) {
        pathFinder = PATH_FINDER_FACTORY.get(algoType).apply(this);
    }

    public void findPath() {
        if (pathFinder == null) {
            throw new IllegalStateException("No pathfinder selected");
        }
        pathFinder.findPath(maze);
    }

    public void paint() {
        paintImmediately(0, 0, getWidth(), getHeight());
        try {
            Thread.sleep(stepDelay);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (maze != null) {
            g.setColor(Color.gray);
            g.fillRect(0, 0, getWidth(), getHeight());
            maze.print(g);
        }
    }

    public void setCellSize(int size) {
        cellSize = size;
        repaint();
    }

    public int getCellSize() {
        return cellSize;
    }
}
