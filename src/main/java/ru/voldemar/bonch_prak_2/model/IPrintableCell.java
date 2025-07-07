package ru.voldemar.bonch_prak_2.model;

import java.awt.*;

public interface IPrintableCell extends IPrintable {

    void printPath(Graphics g);

    default void printText(Graphics g) {
    }
}
