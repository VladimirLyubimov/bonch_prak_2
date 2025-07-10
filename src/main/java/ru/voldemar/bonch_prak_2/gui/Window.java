package ru.voldemar.bonch_prak_2.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import ru.voldemar.bonch_prak_2.algorithm.AlgoType;
import ru.voldemar.bonch_prak_2.model.CellType;

public class Window extends JFrame {

    public Window(String title) {
        setTitle(title);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initComponents();
        pack();
        setVisible(true);
    }

    private void initComponents() {
        BoxLayout gridLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        Box box = Box.createVerticalBox();
        MazePainter mazeGUI = new MazePainter(500);
        mazeGUI.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        mazeGUI.setPreferredSize(new Dimension(1000, 700));
        mazeGUI.reset();
        CellAdder cellAdder = new CellAdder(mazeGUI);
        mazeGUI.addMouseListener(cellAdder);
        JList<AlgoType> algoSelector = new JList<>(AlgoType.values());
        algoSelector.addListSelectionListener(
                e -> mazeGUI.setAlgo(AlgoType.values()[e.getFirstIndex()])
        );
//        algoSelector.setPreferredSize(new Dimension(100, 100));

        JButton startButton = new JButton("Start!");
//        startButton.setPreferredSize(new Dimension(100, 100));
        startButton.addActionListener(e -> mazeGUI.findPath());

        JPanel mazeBuildingButtons = new JPanel(new FlowLayout());
        mazeBuildingButtons.setPreferredSize(new Dimension(mazeGUI.getWidth(), 30));

        JButton setStartButton = new JButton("Set start");
//        setStartButton.setPreferredSize(new Dimension(mazeBuildingButtons.getWidth() / 3, 100));
        setStartButton.addActionListener(e -> cellAdder.setCellType(CellType.START));
//        setStartButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JButton setEndButton = new JButton("Set end");
//        setEndButton.setPreferredSize(new Dimension(mazeBuildingButtons.getWidth() / 3, 100));
        setEndButton.addActionListener(e -> cellAdder.setCellType(CellType.END));
//        setEndButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JButton resetButton = new JButton("Reset");
//        resetButton.setPreferredSize(new Dimension(mazeBuildingButtons.getWidth() / 3, 100));
        resetButton.addActionListener(e -> mazeGUI.reset());
//        resetButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JButton addCellSize = new JButton("+");
        addCellSize.addActionListener(e -> mazeGUI.setCellSize(mazeGUI.getCellSize() + 1));

        JLabel cellSize = new JLabel(String.valueOf(mazeGUI.getCellSize()));

        JButton subCellSize = new JButton("-");
        addCellSize.addActionListener(e -> {mazeGUI.setCellSize(mazeGUI.getCellSize() - 1);});


        mazeBuildingButtons.add(setStartButton);
        mazeBuildingButtons.add(setEndButton);
        mazeBuildingButtons.add(resetButton);

        box.add(mazeGUI);
        box.add(mazeBuildingButtons);
        box.add(algoSelector);
        box.add(startButton);
        box.add(addCellSize);
        box.add(cellSize);
        box.add(subCellSize);
        add(box);
        revalidate();
    }

}
