package main.scrabble.view;

import main.scrabble.model.Cell;
import main.scrabble.exceptions.*;
import main.scrabble.model.CellType;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static com.sun.javafx.tk.Toolkit.getToolkit;

/**
 * Created by enrique on 27/09/16.
 */

public class UICell extends UIObject {
    private Cell cell;

    public static final int sideWidth = 57;

    public UICell(int x, int y, Cell cell) {
        super(x, y);
        w = sideWidth;
        h = sideWidth;
        this.cell = cell;

        setImageforType(this.cell.getType());
    }

    public Cell getCell() {
        return cell;
    }

    @Override
    public boolean receiveInput(Point p) throws OccupiedCellException {
        if (super.receiveInput(p)) {
            /*
            if (!cell.isEmpty()) {
                throw new OccupiedCellException(cell);
            }*/
            return true;
        } else
            return false;
    }
    /*
    @Override
    public void draw(Graphics g, JFrame context) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.getHSBColor(50, 68, 70));
        g2.fillRect(x, y, w, h);
        /*String fileName = "";
        File file = new File(fileName);
        try {
            BufferedImage img = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        JLabel label = new JLabel(new ImageIcon(image));
        context.getContentPane().add(label);
    }*/

    @Override
    public void draw(Graphics2D g2, JFrame context) {
        if (cell.getPiece() != null) {
            UIPiece piece = new UIPiece(x, y, cell.getPiece(), false);
            piece.draw(g2, context);
        } else {
            g2.drawImage(image, x, y, w, h, context);
        }
    }

    public void setImageforType(CellType type) {

        switch (type) {
            case PLAIN:
                setImage(getClass().getResource("/cell/plain.png"));
                break;
            case DOUBLE_LETTER:
                setImage(getClass().getResource("/cell/dl.png"));
                break;
            case TRIPLE_LETTER:
                setImage(getClass().getResource("/cell/tl.png"));
                break;
            case DOUBLE_WORD:
                setImage(getClass().getResource("/cell/dw.png"));
                break;
            case TRIPLE_WORD:
                setImage(getClass().getResource("/cell/tw.png"));
                break;
            case CENTRAL_CELL:
                setImage(getClass().getResource("/cell/startCell.png"));
                break;
        }
    }
}
