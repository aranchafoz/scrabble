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

    private int paddingBot = 5;
    private int paddingTop = 5;
    private int paddingRight = 5;
    private int paddingLeft = 5;

    private final int PIECE_WIDTH = 20;

    public UIRack(int x, int y) {
        super(x, y);
        x += paddingLeft;
        w -= paddingLeft + paddingRight;
        y += paddingTop;
        h -= paddingTop + paddingBot;
    }

    public void setPieces(ArrayList<Piece> pieces) {
        int i = 0;
        for (Piece p : pieces) {
            this.pieces.add(new UIPiece(x + i * PIECE_WIDTH, y, p));
        }
    }

    @Override
    public void draw(Graphics g, JFrame context) {
        g.drawImage(image, x - paddingLeft, y - paddingRight, context);
        for (UIPiece piece : pieces)
            piece.draw(g, context);
    }

    public UIPiece getSelectedPiece(Point point) throws OccupiedCellException {
        for (UIPiece piece : pieces)
           if (piece.receiveInput(point))
               return piece;
        return null;
    }
}
