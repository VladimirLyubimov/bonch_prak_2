package ru.voldemar.bonch_prak_2;

import ru.voldemar.bonch_prak_2.algorithm.AlgoType;
import ru.voldemar.bonch_prak_2.gui.CellAdder;
import ru.voldemar.bonch_prak_2.gui.MazePainter;
import ru.voldemar.bonch_prak_2.model.CellType;

import javax.swing.*;
import java.awt.*;
import java.net.URISyntaxException;

public class Main {

    public static void main(String... args) throws URISyntaxException {
        JFrame frame = new JFrame("Pathfinder");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setSize(1000, 1000);
        frame.setVisible(true);
        BorderLayout gridLayout = new BorderLayout();
        MazePainter mazeGUI = new MazePainter(500);
        mazeGUI.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        mazeGUI.setPreferredSize(new Dimension(700, 700));
        mazeGUI.reset();
        CellAdder cellAdder = new CellAdder(mazeGUI);
        mazeGUI.addMouseListener(cellAdder);
        JList<AlgoType> algoSelector = new JList<>(AlgoType.values());
        algoSelector.addListSelectionListener(
                e -> mazeGUI.setAlgo(AlgoType.values()[e.getFirstIndex()])
        );
        algoSelector.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        algoSelector.setPreferredSize(new Dimension(100, 100));

        JButton startButton = new JButton("Start!");
        startButton.setPreferredSize(new Dimension(100, 100));
        startButton.addActionListener(e -> mazeGUI.findPath());
        startButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JButton setStartButton = new JButton("Set start");
        setStartButton.setPreferredSize(new Dimension(100, 100));
        setStartButton.addActionListener(e -> cellAdder.setCellType(CellType.START));
        setStartButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JButton setEndButton = new JButton("Set end");
        setEndButton.setPreferredSize(new Dimension(100, 100));
        setEndButton.addActionListener(e -> cellAdder.setCellType(CellType.END));
        setEndButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JButton resetButton = new JButton("Reset");
        resetButton.setPreferredSize(new Dimension(100, 100));
        resetButton.addActionListener(e -> mazeGUI.reset());
        resetButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

//        gridLayout.addLayoutComponent("maze", mazeGUI);
//        gridLayout.addLayoutComponent("algo_selector", algoSelector);
        frame.setLayout(gridLayout);
        frame.add(mazeGUI, BorderLayout.NORTH);
        frame.add(algoSelector, BorderLayout.SOUTH);
        frame.add(startButton, BorderLayout.WEST);
        frame.add(setStartButton, BorderLayout.CENTER);
        frame.add(setEndButton, BorderLayout.EAST);
        frame.add(resetButton, BorderLayout.PAGE_END);
        frame.revalidate();
//        CompletableFuture.runAsync(() -> {
//                    var pathFinder = new AStarPathFinder(mazePainter);
//                    var path = pathFinder.findPath(maze);
//                    maze.getCells()
//                            .stream()
//                            .flatMap(Collection::stream)
//                            .filter(cell -> cell.getType() == CellType.FIELD)
//                            .filter(path::contains)
//                            .forEach(cell -> cell.setOnPath(true));
//                    System.out.println(maze);
//                    System.out.println(path);
////                    mazePainter.setMaze(maze);
//                }
//        ).thenRun(frame::repaint);
    }
}
