package ru.voldemar.bonch_prak_2.gui;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.voldemar.bonch_prak_2.model.CellType;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@RequiredArgsConstructor
@Setter
@Getter
public class CellAdder extends MouseAdapter {

    private final MazePainter mazePainter;
    private CellType cellType = CellType.BLOCK;

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX() / MazePainter.CELL_SIZE;
        int y = e.getY() / MazePainter.CELL_SIZE;
        if (e.getButton() == MouseEvent.BUTTON1) {
            mazePainter.getMaze().getCells().get(y).get(x).setType(cellType);
        } else {
            mazePainter.getMaze().getCells().get(y).get(x).setType(CellType.FIELD);
        }
        cellType = CellType.BLOCK;
        mazePainter.repaint();
    }
}
