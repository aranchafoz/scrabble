package main.scrabble.view;

import main.scrabble.model.Board;
import main.scrabble.model.Cell;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by enrique on 27/09/16.
 */
public class UIBoard extends UIObject {
    private Board board;
    private ArrayList<UICell> cells;

    private static final int sideSizeCells = 15;
    private static final int cellSeparator = 2;

    public UIBoard(int x, int y, int sideWidth, Board b) {
        super(x, y);
        w = sideWidth;
        h = sideWidth;
        // Receive board and create the UICell array from it
        board = b;

        cells = new ArrayList<>();

        for(int i = 0; i < sideSizeCells; i++) {
            for(int j = 0; j < sideSizeCells; j++) {
                UICell cell = new UICell(x + i * (UICell.sideWidth + cellSeparator), y + j * (UICell.sideWidth + cellSeparator), b.getCell(i+1,j+1));
                cells.add(cell);
            }
        }
    }

    @Override
    public void draw(Graphics2D g2, JFrame context) {

        // Draw Board
        BasicStroke in = new BasicStroke(6f);
        g2.setStroke(in);
        g2.setColor(Color.white);
        g2.drawRect(x-3,y-3,w+6,h+6);
        g2.setColor(Color.white);
        g2.fillRect(x,y,w,h);
        BasicStroke mid = new BasicStroke(2f);
        g2.setStroke(mid);
        g2.setColor(Color.black);
        g2.drawRect(x-7,y-7,w+14,h+14);
        BasicStroke out = new BasicStroke(8f);
        g2.setStroke(out);
        g2.setColor(Color.white);
        g2.drawRect(x-12,y-12,w+24,h+24);

        // Draw Cells
        for (UICell cell : cells)
            cell.draw(g2, context);
    }

    public Cell getSelectedCell(Point p) {
        int h = (int) ((p.x - x) / (w / 15f));
        int v = (int) ((p.y - y) / (h / 15f));

        return cells.get(h + v * 15).getCell();
    }
}
