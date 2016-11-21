package main.scrabble.model;


import main.scrabble.exceptions.NoPiecesInBagException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Player {
    protected final int RACK_SIZE = 7;
    protected int score;

    protected String name;
    protected String avatarPath;
    protected ArrayList<Piece> pieces;

    public Player() {
        name = "";
        avatarPath = "";
        pieces = new ArrayList<>();
        score = 0;
    }

    public Player(String name, String avatarPath) {
        this.name = name;
        this.avatarPath = avatarPath;
        pieces = new ArrayList<>();
        score = 0;
    }

    public void increaseScore(int value) {
        score += value;
    }

    public ArrayList<Piece> getPieces() {
        return pieces;
    }

    public String getName() { return name; }

    public String getAvatarPath() {
        return avatarPath;
    }

    public int getScore() { return score; }

    public boolean rackFilled() {
        return pieces.size() >= RACK_SIZE;
    }

    public void addPiece(Piece piece) {
        pieces.add(piece);
    }

    public void removePiece(Piece piece) {
        pieces.remove(piece);
    }

    public void mixPieces() {
        Collections.shuffle(pieces, new Random(System.nanoTime()));
    }

    public void fillRack(Bag bag) throws NoPiecesInBagException {
        while (!rackFilled())
            addPiece(bag.takePiece());
    }
}
