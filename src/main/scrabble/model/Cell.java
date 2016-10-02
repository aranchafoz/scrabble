package main.scrabble.model;

import main.scrabble.exceptions.*;

/**
 * Created by enrique on 27/09/16.
 */

public class Cell {

    private Piece piece;
    private int x;
    private int y;
    private CellType type;

    public Cell(int x, int y, CellType type) throws WrongCoordinateException {
        if (checkCoordinates(x, y)) {
            this.x = x;
            this.y = y;
            this.type = type;
        } else {
            throw new WrongCoordinateException(x, y);
        }
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
        return x;
    }

    public int getY() {
        return y;
    }

    public CellType getType() {
        return type;
    }

    public boolean isEmpty() {
        boolean empty = false;
        if(piece == null){
            empty = true;
        }
        return empty;
    }
    @Override
    public String toString() {
        return Integer.toString(x + 'A') + Integer.toString(y + 1);
    }

    public boolean checkCoordinates(int xCoord, int yCoord) {
        boolean correct = true;
        if(xCoord < 0 || yCoord < 0 || xCoord > 14 || yCoord > 14) {
                correct = false;
            }

        return correct;
    }

}



















