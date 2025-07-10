package ru.voldemar.bonch_prak_2.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Map;
import java.util.function.Function;

import javax.swing.JComponent;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.Synchronized;
import ru.voldemar.bonch_prak_2.algorithm.AStarPathFinder;
import ru.voldemar.bonch_prak_2.algorithm.AlgoType;
import ru.voldemar.bonch_prak_2.algorithm.BFSPathFinder;
import ru.voldemar.bonch_prak_2.algorithm.IPathFindingAlgorithm;
import ru.voldemar.bonch_prak_2.model.Cell;
import ru.voldemar.bonch_prak_2.model.CellType;
import ru.voldemar.bonch_prak_2.model.Maze;

@Setter
@Getter
public class MazePainter extends JComponent {

    public static int cellSize = 100;

    private static final Map<AlgoType, Function<MazePainter, IPathFindingAlgorithm>> PATH_FINDER_FACTORY = Map.of(
            AlgoType.A_STAR, AStarPathFinder::new,
            AlgoType.BFS, BFSPathFinder::new
    );

    private int stepDelay;
    private transient IPathFindingAlgorithm pathFinder;
    private transient Maze<? extends Cell> maze;
    private CellType addType;

    @Synchronized
    public void reset() {
        maze = new Maze<>(7, 10, (x, y) -> new Cell(CellType.FIELD, x, y));
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
            g.setColor(Color.white);
            g.fillRect(0, 0, getWidth(), getHeight());
            maze.print(g);
        }
    }

    public void setCellSize(int size) {
        cellSize = size;
    }
    public int getCellSize() {
        return cellSize;
    }
}
