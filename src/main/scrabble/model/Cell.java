package main.scrabble.model;

import main.scrabble.exceptions.*;

/**
 * Created by enrique on 27/09/16.
 */

public class Cell {

    private Piece piece;
    private Coordinate coordinate;
    private CellType type;

    public Cell(int x, int y, CellType type) throws WrongCoordinateException {
        if (checkCoordinates(x, y)) {
            coordinate.x = x;
            coordinate.y = y;
            this.type = type;
        } else {
            throw new WrongCoordinateException(x, y);
        }
    }

    public Cell(Cell c) {
        setPiece(c.getPiece());
        coordinate.x = c.getX();
        coordinate.y = c.getY();
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
        return coordinate.x;
    }

    public int getY() {
        return coordinate.y;
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
        return Integer.toString(coordinate.x + 'A') + Integer.toString(coordinate.y + 1);
    }

    public boolean checkCoordinates(int xCoord, int yCoord) {
        boolean correct = true;
        if(xCoord < 0 || yCoord < 0 || xCoord > 14 || yCoord > 14) {
                correct = false;
            }

        return correct;
    }

}



















