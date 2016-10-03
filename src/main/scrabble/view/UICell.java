package main.scrabble.view;

import main.scrabble.model.Cell;
import main.scrabble.exceptions.*;
import main.scrabble.model.CellType;

import javax.swing.*;
import java.awt.*;

/**
 * Created by enrique on 27/09/16.
 */
public class UICell extends UIObject {
    private Cell cell;

    public UICell(int x, int y, int sideWidth, Cell cell) {
        super(x, y);
        w =  sideWidth;
        h = sideWidth;
        this.cell = cell;

        setImage(this.cell.getType());
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

    @Override
    public void draw(Graphics g, JFrame context) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.blue);
        g2.fillRect(x,y,w,h);
    }

    public void setImage(CellType type) {

        switch (type) {
            case plain:
                //this.image = image;
                break;
            case dl:
                //this.image = image;
                break;
            case tl:
                //this.image = image;
                break;
            case dw:
                //this.image = image;
                break;
            case tw:
                //this.image = image;
                break;
        }
    }
}
