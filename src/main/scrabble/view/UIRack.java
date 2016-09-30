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
    private int piecePaddingBot = 5;
    private int piecePaddingTop = 5;
    private int piecePaddingRight = 5;
    private int piecePaddingLeft = 5;

    public UIRack(int x, int y) {
        super(x, y);
        x += piecePaddingLeft;
        w -= piecePaddingLeft + piecePaddingRight;
        y += piecePaddingTop;
        h -= piecePaddingTop + piecePaddingBot;
    }

    public void setPieces(ArrayList<Piece> pieces) {
        for (Piece p : pieces) {
            pieces.add(new UIPiece(p));
        }
    }

    @Override
    public void draw(Graphics g, JFrame context) {
        super.draw(g, context);
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
