package ru.voldemar.bonch_prak_2.gui;

import ru.voldemar.bonch_prak_2.algorithm.AlgoType;
import ru.voldemar.bonch_prak_2.model.CellType;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    private static final int DEFAULT_STEP_DELAY = 500;

    public Window(String title) {
        setTitle(title);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initComponents();
        pack();
        setVisible(true);
    }

    private void initComponents() {
        Box box = Box.createVerticalBox();
        MazePainter mazeGUI = new MazePainter();
        mazeGUI.setStepDelay(DEFAULT_STEP_DELAY);
        CellAdder cellAdder = new CellAdder(mazeGUI);
        mazeGUI.addMouseListener(cellAdder);
        JList<AlgoType> algoSelector = new JList<>(AlgoType.values());
        algoSelector.addListSelectionListener(
                e -> mazeGUI.setAlgo(algoSelector.getSelectedValue())
        );

        JButton startButton = new JButton("Start!");
        startButton.addActionListener(e -> mazeGUI.findPath());

        JLabel speedLabel = new JLabel("Speed:", SwingConstants.CENTER);
        speedLabel.setFont(new Font("Arial", Font.BOLD, 12));

        JSlider algoStepDelaySlider = new JSlider(JSlider.HORIZONTAL, 0, 1000, DEFAULT_STEP_DELAY);
        algoStepDelaySlider.setMajorTickSpacing(500);
        algoStepDelaySlider.setMinorTickSpacing(50);
        algoStepDelaySlider.setPaintTicks(true);
        algoStepDelaySlider.setPaintLabels(true);

        algoStepDelaySlider.addChangeListener(e -> {
            mazeGUI.setStepDelay(algoStepDelaySlider.getValue());
        });

        JPanel mazeBuildingButtons = getMazeBuildingButtons(mazeGUI, cellAdder);
        JPanel algoSettings = new JPanel(new FlowLayout());
        algoSettings.add(algoSelector);
        algoSettings.add(speedLabel);
        algoSettings.add(algoStepDelaySlider);

        box.add(mazeGUI);
        box.add(mazeBuildingButtons);
        box.add(algoSettings);
        box.add(startButton);
        add(box);
        revalidate();
    }

    private JPanel getMazeBuildingButtons(MazePainter mazeGUI, CellAdder cellAdder) {
        JPanel mazeBuildingButtons = new JPanel(new FlowLayout());
        mazeBuildingButtons.setPreferredSize(new Dimension(mazeGUI.getWidth(), 30));

        JButton setStartButton = new JButton("Set start");
        setStartButton.addActionListener(e -> cellAdder.setCellType(CellType.START));

        JButton setEndButton = new JButton("Set end");
        setEndButton.addActionListener(e -> cellAdder.setCellType(CellType.END));

        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> mazeGUI.reset());

        mazeBuildingButtons.add(setStartButton);
        mazeBuildingButtons.add(setEndButton);
        mazeBuildingButtons.add(resetButton);
        return mazeBuildingButtons;
    }

}
