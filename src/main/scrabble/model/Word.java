package main.scrabble.model;

import java.util.ArrayList;

/**
 * Created by enrique on 11/10/16.
 */
public class Word {
    private Cell start;
    private Direction dir;
    private ArrayList<Piece> pieces;

    public Word(Cell start, Direction dir, ArrayList<Piece> pieces) {
        this.dir = dir;
        this.start = start;
        this.pieces = pieces;
    }

    public Direction getDirection(){
        return dir;
    }

    public ArrayList<Piece> getPieces() {
        return pieces;
    }

    public int getOriginX(){
        return start.getX();
    }

    public int getOriginY(){
        return start.getY();
    }

    public Cell getOrigin(){
        return start;
    }

    public String toString() {
        String str = "Origin: " + start.getX() + ", " + start.getY() + " Word: ";
        for (Piece p : pieces) {
            str += p.getLetter();
        }
        str += " Orientation: " + dir.toString();

        return str;
    }

}
