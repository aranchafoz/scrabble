package main.scrabble.model;

import main.scrabble.exceptions.*;

/**
 * Created by enrique on 27/09/16.
 */

public class Cell {

    private Piece piece;
    private int cx;
    private int cy;

    private CellType type;

    public Cell(int x, int y, CellType type) throws WrongCoordinateException {
        if (checkCoordinates(x, y)) {
            cx = x;
            cy = y;
            this.type = type;
        } else {
            throw new WrongCoordinateException(x, y);
        }
    }

    public Cell(Cell c) {
        setPiece(c.getPiece());
        cx = c.getX();
        cy = c.getY();
        type = c.getType();
    }

    //Getters
    public Piece getPiece() {
        return piece;
    }

    //Setters
    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public int getX() {
        return cx;
    }

    public int getY() {
        return cy;
    }

    public CellType getType() {
        return type;
    }

    public boolean isEmpty() {
        return piece == null;
    }
    @Override
    public String toString() {
        return (char) (cx + 'A') + Integer.toString(cy + 1);
    }

    public boolean checkCoordinates(int xCoord, int yCoord) {
        boolean correct = true;
        if(xCoord < 0 || yCoord < 0 || xCoord > 14 || yCoord > 14) {
                correct = false;
            }

        return correct;
    }

    public int getWordMultiplier() {
        if (type == CellType.DOUBLE_WORD || type == CellType.CENTRAL_CELL) return 2;
        if (type == CellType.TRIPLE_WORD) return 3;
        return 1;
    }

    public int getLetterMultiplier() {
        if (type == CellType.DOUBLE_LETTER) return 2;
        if (type == CellType.TRIPLE_LETTER) return 3;
        return 1;
    }
}



















