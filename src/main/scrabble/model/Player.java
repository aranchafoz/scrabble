package main.scrabble.model;


import java.awt.*;
import java.util.ArrayList;

public class Player {
    private final int RACK_SIZE = 7;
    private int punctuation;

    private String name;
    private Image avatar;
    private ArrayList<Piece> pieces;

    public Player(String name, Image avatar) {
        this.name = name;
        this.avatar = avatar;

    }

    public int increasePunctuarion(int value) {
        punctuation += value;
        return punctuation;
    }

    public boolean rackFilled() {
        return pieces.size() >= RACK_SIZE;
    }

    public void addPiece(Piece piece) {
        pieces.add(piece);
    }

    public void removePiece(Piece piece) {
        pieces.remove(piece);
    }
}
