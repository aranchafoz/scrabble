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
}
