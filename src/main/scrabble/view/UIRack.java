package main.scrabble.view;

import main.scrabble.exceptions.OccupiedCellException;
import main.scrabble.model.Piece;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by enrique on 27/09/16.
 */
public class UIRack extends UIObject {
    private ArrayList<UIPiece> pieces;

    private int paddingBottom = 5;
    private int paddingTop = 5;
    private int paddingRight = 5;
    private int paddingLeft = 5;

    private final int PIECE_WIDTH = 20;

    public UIRack(int x, int y, int width, int height) {
        super(x, y);
        /*
        x += paddingLeft;
        w -= paddingLeft + paddingRight;
        y += paddingTop;
        h -= paddingTop + paddingBottom;
        */
        this.x = x;
        this.y = y;

        w = width;
        h = height;

        pieces = new ArrayList<>();
    }

    public void setPieces(ArrayList<Piece> pieces) {
        int i = 0;
        for (Piece p : pieces) {
            this.pieces.add(new UIPiece(x + i * PIECE_WIDTH, y, p));
        }
    }

    @Override
    public void draw(Graphics2D g2, JFrame context) {

        float hsb[] = new float[3];
        float color[];

        BasicStroke backShadow = new BasicStroke(4f);
        g2.setStroke(backShadow);
        color = Color.RGBtoHSB(192,128,64,hsb);//204,153,102,hsb); // 192,128,64
        g2.setColor(Color.getHSBColor(color[0], color[1], color[2]));
        g2.drawRoundRect(x + 20,y,w - 40,h,20,20);

        color = Color.RGBtoHSB(250,223,173,hsb); // 192,128,64
        g2.setColor(Color.getHSBColor(color[0], color[1], color[2]));
        g2.fillRoundRect(x + 20,y,w - 40,h,20,20);

        BasicStroke frontShadow = new BasicStroke(3f);
        g2.setStroke(frontShadow);
        color = Color.RGBtoHSB(192,128,64,hsb); // 192,128,64
        g2.setColor(Color.getHSBColor(color[0], color[1], color[2]));
        g2.drawRoundRect(x,y + h - 47,w,45,15,15);

        color = Color.RGBtoHSB(204,153,102,hsb); // 192,128,64
        g2.setColor(Color.getHSBColor(color[0], color[1], color[2]));
        g2.fillRoundRect(x,y + h - 47,w,45,15,15);

        for (UIPiece piece : pieces)
            piece.draw(g2, context);
    }

    public UIPiece getSelectedPiece(Point point) throws OccupiedCellException {
        for (UIPiece piece : pieces)
           if (piece.receiveInput(point))
               return piece;
        return null;
    }
}
