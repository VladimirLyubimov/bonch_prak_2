package ru.voldemar.bonch_prak_2.gui;

import ru.voldemar.bonch_prak_2.algorithm.AlgoType;
import ru.voldemar.bonch_prak_2.CellAdder;
import ru.voldemar.bonch_prak_2.MazePainter;
import ru.voldemar.bonch_prak_2.model.CellType;

import javax.swing.*;
import java.awt.*;

import java.net.URISyntaxException;

public class Window extends JFrame {

    public Window(String title) {
        setTitle(title);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }

    private void initComponents() {
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

//        gridLayout.addLayoutComponent("algo_selector", algoSelector);
//        gridLayout.addLayoutComponent("maze", mazeGUI);
        setLayout(gridLayout);
        add(mazeGUI, BorderLayout.NORTH);
        add(algoSelector, BorderLayout.SOUTH);
        add(startButton, BorderLayout.WEST);
        add(setStartButton, BorderLayout.CENTER);
        add(setEndButton, BorderLayout.EAST);
        add(resetButton, BorderLayout.PAGE_END);
        revalidate();
    }

}
