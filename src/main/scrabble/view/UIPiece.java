package main.scrabble.view;


import main.scrabble.model.Piece;

/**
 * Created by enrique on 27/09/16.
 */
public class UIPiece extends UIObject {
    private Piece piece;

    public UIPiece(int x, int y, Piece piece) {
        super(x, y);
        this.piece = piece;
    }

    public Piece getPiece() {
        return piece;
    }
}
