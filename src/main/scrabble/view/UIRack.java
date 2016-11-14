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

    private static final int backHmargin = 16;
    private static final int backHpadding = 9;
    private static final int pieceSeparator = 8;
    private static final int frontHeight = 25;
    private static final int backTopPadding = 13;

    public UIRack(int x, int y, int width, int height) {
        super(x, y);
        this.x = x;
        this.y = y;

        w = width;
        h = height;
        pieces = new ArrayList<>();
    }

    public void setPieces(ArrayList<Piece> pieces) {
        int i = 0;
        // Check if pieces size equals to 7, else ERROR
        this.pieces = new ArrayList<>();
        for (Piece p : pieces) {
            this.pieces.add(new UIPiece(x + backHmargin + backHpadding + ((UIPiece.sideWidthRack + pieceSeparator) * i), y + backTopPadding, p, true));
            i++;
        }
    }

    public void removePiece(UIPiece piece) {
        int i = pieces.indexOf(piece);
        pieces.set(i, null);
    }

    @Override
    public void draw(Graphics2D g2, JFrame context) {

        float hsb[] = new float[3];
        float color[];

        BasicStroke backShadow = new BasicStroke(4f);
        g2.setStroke(backShadow);
        color = Color.RGBtoHSB(192,128,64,hsb);//204,153,102,hsb); // 192,128,64
        g2.setColor(Color.getHSBColor(color[0], color[1], color[2]));
        g2.drawRoundRect(x + backHmargin, y, w - (backHmargin * 2), h, 20, 20);

        color = Color.RGBtoHSB(250,223,173,hsb); // 192,128,64
        g2.setColor(Color.getHSBColor(color[0], color[1], color[2]));
        g2.fillRoundRect(x + backHmargin, y, w - (backHmargin * 2), h, 20, 20);

        BasicStroke frontShadow = new BasicStroke(3f);
        g2.setStroke(frontShadow);
        color = Color.RGBtoHSB(192,128,64,hsb); // 192,128,64
        g2.setColor(Color.getHSBColor(color[0], color[1], color[2]));
        g2.drawRoundRect(x, y + h - frontHeight, w, frontHeight, 15, 15);

        color = Color.RGBtoHSB(204,153,102,hsb); // 192,128,64
        g2.setColor(Color.getHSBColor(color[0], color[1], color[2]));
        g2.fillRoundRect(x, y + h - frontHeight, w, frontHeight, 15, 15);

        for (UIPiece piece : pieces)
            if (piece != null)
                piece.draw(g2, context);
    }

    public UIPiece getSelectedPiece(Point point) throws OccupiedCellException {
        for (UIPiece piece : pieces)
           if (piece != null && piece.receiveInput(point))
               return piece;
        return null;
    }
}
