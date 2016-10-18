package main.scrabble.model;

/**
 * Created by enrique on 30/09/16.
 */
public class Piece {
    private char letter;
    private int score;

    public Piece(char c, int score) {
        this.letter = c;
        this.score = score;
    }
    public int getCoordinateX(){
        return 0;
    }

    public int getCoordinateY(){
        return 0;
    }

    public char getLetter() {
        return letter;
    }

    public int getScore() {
        return score;
    }
}
