package main.scrabble.view;

import main.scrabble.model.Cell;
import main.scrabble.exceptions.*;

import java.awt.*;

/**
 * Created by enrique on 27/09/16.
 */
public class UICell extends UIObject {
    private Cell cell;

    public UICell(int x, int y, Cell cell) {
        super(x, y);
        this.cell = cell;
    }

    @Override
    public boolean receiveInput(Point p) throws OccupiedCellException {
        if (super.receiveInput(p)) {
            if (!cell.isEmpty()) {
                throw new OccupiedCellException(cell);
            }
            return true;
        } else
            return false;
    }

}
