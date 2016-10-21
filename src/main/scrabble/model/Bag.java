package main.scrabble.model;

import main.scrabble.exceptions.NoPiecesInBagException;

import java.util.*;

/**
 * Created by enrique on 11/10/16.
 */
public class Bag {

    private ArrayList<Piece> pieces;
    private HashMap letters;
    private HashMap punctuations;

    public Bag() {
        pieces = new ArrayList<>();
        letters = new HashMap();
        letters.put('a', new Integer[]{9, 1});
        letters.put('b', new Integer[]{2, 3});
        letters.put('c', new Integer[]{2, 3});
        letters.put('d', new Integer[]{4, 2});
        letters.put('e', new Integer[]{12, 1});
        letters.put('f', new Integer[]{2, 4});
        letters.put('g', new Integer[]{3, 2});
        letters.put('h', new Integer[]{2, 4});
        letters.put('i', new Integer[]{9, 1});
        letters.put('j', new Integer[]{1, 8});
        letters.put('k', new Integer[]{1, 5});
        letters.put('l', new Integer[]{4, 1});
        letters.put('m', new Integer[]{2, 3});
        letters.put('n', new Integer[]{6, 1});
        letters.put('o', new Integer[]{8, 1});
        letters.put('p', new Integer[]{2, 3});
        letters.put('q', new Integer[]{1, 10});
        letters.put('r', new Integer[]{6, 1});
        letters.put('s', new Integer[]{4, 1});
        letters.put('t', new Integer[]{6, 1});
        letters.put('u', new Integer[]{4, 1});
        letters.put('v', new Integer[]{2, 4});
        letters.put('w', new Integer[]{2, 4});
        letters.put('x', new Integer[]{2, 8});
        letters.put('y', new Integer[]{1, 4});
        letters.put('z', new Integer[]{1, 10});
        letters.put(' ', new Integer[]{2, 0});

        Iterator i = letters.entrySet().iterator();
        while(i.hasNext()) {
            Map.Entry me = (Map.Entry)i.next();
            for (int j = 0; j < ((Integer[])me.getValue())[1]; j++)
                pieces.add(new Piece((char)me.getKey(), ((Integer[])me.getValue())[0]));
        }

        mix();
    }

    public void mix() {
        Collections.shuffle(pieces, new Random(System.nanoTime()));
    }

    public Piece takePiece() throws NoPiecesInBagException {
        if (pieces.size() <= 0) throw new NoPiecesInBagException();
        Piece p = pieces.get(0);
        pieces.remove(0);
        return p;
    }

    public int getRemaining() {
        return pieces.size();
    }

    public boolean isEmpty() {
        return pieces.size() == 0;
    }

    public Piece changePiece(Piece old) throws NoPiecesInBagException {
        pieces.add(old);
        mix();
        return takePiece();
    }
}
