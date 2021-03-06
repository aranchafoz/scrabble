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

    public char getLetter() {
        return Character.toLowerCase(letter);
    }

    public int getScore() {
        return score;
    }
}
