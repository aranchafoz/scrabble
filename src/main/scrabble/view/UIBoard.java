package main.scrabble.view;

import main.scrabble.exceptions.OccupiedCellException;
import main.scrabble.model.Board;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by enrique on 27/09/16.
 */
public class UIBoard extends UIObject {
    private Board board;
    private ArrayList<UICell> cells;

    public UIBoard(int x, int y, int sideWidth, Board b) {
        super(x, y);
        w = sideWidth;
        h = sideWidth;
        // Receive board and create the UICell array from it
    }

    @Override
    public void draw(Graphics g, JFrame context) {
        //super.draw(g, context);

        // draw the board (super.draw) and all its cells
        // draw Rectangle2D.Double
        Graphics2D g2 = (Graphics2D) g;
        BasicStroke in = new BasicStroke(6f);
        g2.setStroke(in);
        g2.setColor(Color.white);
        g2.drawRect(x-3,y-3,w+6,h+6);
        g2.setColor(Color.GREEN);
        g2.fillRect(x,y,w,h);
        BasicStroke mid = new BasicStroke(2f);
        g2.setStroke(mid);
        g2.setColor(Color.black);
        g2.drawRect(x-7,y-7,w+14,h+14);
        BasicStroke out = new BasicStroke(8f);
        g2.setStroke(out);
        g2.setColor(Color.white);
        g2.drawRect(x-12,y-12,w+24,h+24);

       /* for (UICell cell : cells)
            cell.draw(g, context); */
    }

    @Override
    public boolean receiveInput(Point p) throws OccupiedCellException {
        if (super.receiveInput(p)) {

            return true;
        } else
            return false;
    }

}
