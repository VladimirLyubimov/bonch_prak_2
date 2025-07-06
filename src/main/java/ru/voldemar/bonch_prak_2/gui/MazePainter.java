package ru.voldemar.bonch_prak_2.gui;

import lombok.Getter;
import lombok.Setter;
import lombok.Synchronized;
import ru.voldemar.bonch_prak_2.model.Cell;
import ru.voldemar.bonch_prak_2.model.Maze;

import javax.swing.*;
import java.awt.*;

@Setter
@Getter
public class MazePainter extends JComponent {

    private transient Maze<? extends Cell> maze;

    @Synchronized
    public void setMaze(Maze<? extends Cell> maze) {
        this.maze = maze;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());
        maze.print(g);
    }
}
