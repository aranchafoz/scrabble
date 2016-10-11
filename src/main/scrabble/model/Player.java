package main.scrabble.model;


import main.scrabble.exceptions.NoPiecesInBagException;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

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

    public ArrayList<Piece> getPieces() {
        return pieces;
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

    public void mixPieces() {
        Collections.shuffle(pieces, new Random(System.nanoTime()));
    }

    public void fillRack(Bag bag) throws NoPiecesInBagException {
        while (!rackFilled())
            addPiece(bag.takePiece());
    }
}
