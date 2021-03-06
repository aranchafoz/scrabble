package main.scrabble.model;

import main.scrabble.exceptions.NoPiecesInBagException;

import java.util.*;

/**
 * Created by enrique on 11/10/16.
 */
public class Bag {

    private ArrayList<Piece> pieces;
    private HashMap letters;

    public Bag() {
        pieces = new ArrayList<>();
        letters = new HashMap();
        letters.put('A', new Integer[]{9, 1});
        letters.put('B', new Integer[]{2, 3});
        letters.put('C', new Integer[]{2, 3});
        letters.put('D', new Integer[]{4, 2});
        letters.put('E', new Integer[]{12, 1});
        letters.put('F', new Integer[]{2, 4});
        letters.put('G', new Integer[]{3, 2});
        letters.put('H', new Integer[]{2, 4});
        letters.put('I', new Integer[]{9, 1});
        letters.put('J', new Integer[]{1, 8});
        letters.put('K', new Integer[]{1, 5});
        letters.put('L', new Integer[]{4, 1});
        letters.put('M', new Integer[]{2, 3});
        letters.put('N', new Integer[]{6, 1});
        letters.put('O', new Integer[]{8, 1});
        letters.put('P', new Integer[]{2, 3});
        letters.put('Q', new Integer[]{1, 10});
        letters.put('R', new Integer[]{6, 1});
        letters.put('S', new Integer[]{4, 1});
        letters.put('T', new Integer[]{6, 1});
        letters.put('U', new Integer[]{4, 1});
        letters.put('V', new Integer[]{2, 4});
        letters.put('W', new Integer[]{2, 4});
        letters.put('X', new Integer[]{2, 8});
        letters.put('Y', new Integer[]{1, 4});
        letters.put('Z', new Integer[]{1, 10});
        letters.put('_', new Integer[]{2, 0});

        Iterator i = letters.entrySet().iterator();
        while(i.hasNext()) {
            Map.Entry me = (Map.Entry)i.next();
            for (int j = 0; j < ((Integer[])me.getValue())[0]; j++)
                pieces.add(new Piece((char)me.getKey(), ((Integer[])me.getValue())[1]));
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
        System.out.println(pieces.size() + " pieces remaining in bag.");
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
