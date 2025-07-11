package ru.voldemar.bonch_prak_2.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ru.voldemar.bonch_prak_2.algorithm.AlgoType;
import ru.voldemar.bonch_prak_2.model.CellType;
import ru.voldemar.bonch_prak_2.utils.Utils;

public class Window extends JFrame {

    private static final int DEFAULT_STEP_DELAY = 500;
    // private static final double WINDOW_ASPECT_RATIO = 1.2;

    public Window(String title) {
        setTitle(title);
        //setLocationRelativeTo(null);
        setResizable(false);
        // setMaximumSize(new Dimension(1100, 900)); // И он всё равно меняется...
        // setMinimumSize(new Dimension(600, 400));
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

        JTextField algoStepDelayInput = new JTextField("Input delay between algorithm steps in milliseconds (default is 500)");
        algoStepDelayInput.addActionListener(
                e -> mazeGUI.setStepDelay(Utils.parseIntOrDefault(algoStepDelayInput.getText(), DEFAULT_STEP_DELAY))
        );

        JPanel mazeBuildingButtons = getMazeBuildingButtons(mazeGUI, cellAdder);
        JPanel algoSettings = new JPanel(new FlowLayout());
        algoSettings.add(algoSelector);
        algoSettings.add(algoStepDelayInput);

        // addComponentListener(new ComponentAdapter() {
        //         @Override
        //         public void componentResized(ComponentEvent e) {
        //                 // int newWidth = getWidth();
        //                 // int newHeight = (int) (newWidth / WINDOW_ASPECT_RATIO);
        //                 int newWidth = getWidth();
        //                 int newHeight = getHeight();
        //                 //setSize(newWidth, newHeight);
        //                 // int cellSize = (Math.min(newWidth, newHeight) - 100) / 5;
        //                 mazeGUI.setCellSize(cellSize);
        //                 revalidate();
        //                 repaint();
        //                 System.err.println(mazeGUI.getCellSize());
        //                 System.err.println(e.getComponent().getSize());
        //         }
        // });

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

        // JButton addCellSize = new JButton("Increase cell display size");
        // addCellSize.addActionListener(e -> mazeGUI.setCellSize(mazeGUI.getCellSize() + 5));

        // JButton subCellSize = new JButton("Decrease cell display size");
        // subCellSize.addActionListener(e -> mazeGUI.setCellSize(mazeGUI.getCellSize() - 5));

        JButton setStartButton = new JButton("Set start");
        setStartButton.addActionListener(e -> cellAdder.setCellType(CellType.START));

        JButton setEndButton = new JButton("Set end");
        setEndButton.addActionListener(e -> cellAdder.setCellType(CellType.END));

        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> mazeGUI.reset());

        // mazeBuildingButtons.add(addCellSize);
        // mazeBuildingButtons.add(subCellSize);
        mazeBuildingButtons.add(setStartButton);
        mazeBuildingButtons.add(setEndButton);
        mazeBuildingButtons.add(resetButton);
        return mazeBuildingButtons;
    }

}
