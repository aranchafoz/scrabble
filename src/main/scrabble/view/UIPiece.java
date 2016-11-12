package main.scrabble.view;


import main.scrabble.model.Piece;

import javax.swing.*;
import java.awt.*;

/**
 * Created by enrique on 27/09/16.
 */
public class UIPiece extends UIObject {
    private Piece piece;

    public static final int sideWidthBoard = 57;
    public static final int sideWidthRack = 86;

    public UIPiece(int x, int y, Piece piece) {
        super(x, y);
        this.piece = piece;

        w = sideWidthBoard;
        h = sideWidthBoard;

        setImage("assets/alphabet/" + piece.getLetter() +".png");
    }

    public UIPiece(int x, int y, Piece piece, boolean drawInRack) {
        super(x, y);
        this.piece = piece;
        if (drawInRack) {
            w = sideWidthRack;
            h = sideWidthRack;
        } else {
            w = -1;
            h = -1;
        }
        setImage("assets/alphabet/" + piece.getLetter() +".png");
    }

    public Piece getPiece() {
        return piece;
    }

    public int getSideWidthBoard() { return sideWidthBoard; }

    public int getSideWidthRack() { return sideWidthRack; }
}
