package main.scrabble.model;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by enrique on 8/11/16.
 */
public class PlayerTest {
    @Test
    public void increasePunctuation() throws Exception {
        Player p = new Player("Name", "path");
        p.increaseScore(5);
        assertEquals(5, p.getScore());
    }

    @Test
    public void getPieces() throws Exception {
        Player p = new Player("Name", "path");
        Piece pA = new Piece('A', 4);
        Piece pB = new Piece('B', 5);
        p.addPiece(pA);
        assertEquals(pA, p.getPieces().get(0));
        p.addPiece(pB);
        assertEquals(pB, p.getPieces().get(1));
    }

    @Test
    public void rackFilled() throws Exception {
        Player p = new Player("Name", "path");
        Piece pA = new Piece('A', 4);
        for (int i = 0; i < 7; i++) {
            assertFalse(p.rackFilled());
            p.addPiece(pA);
        }
        assertTrue(p.rackFilled());
    }

    @Test
    public void addPiece() throws Exception {
        Player p = new Player("Name", "path");
        Piece pA = new Piece('A', 4);
        Piece pB = new Piece('B', 5);
        assertEquals(0, p.getPieces().size());
        p.addPiece(pA);
        assertEquals(1, p.getPieces().size());
        p.addPiece(pB);
        assertEquals(2, p.getPieces().size());
    }

    @Test
    public void removePiece() throws Exception {
        Player p = new Player("Name", "path");
        Piece pA = new Piece('A', 4);
        p.addPiece(pA);
        assertEquals(1, p.getPieces().size());
        p.removePiece(pA);
        assertEquals(0, p.getPieces().size());
    }

    @Test
    public void mixPieces() throws Exception {
        Player p = new Player("Name", "path");
        ArrayList<Piece> pieces = new ArrayList<>();
        pieces.add(new Piece('A', 4));
        pieces.add(new Piece('B', 5));
        pieces.add(new Piece('C', 3));
        pieces.add(new Piece('D', 5));
        pieces.add(new Piece('E', 6));
        pieces.add(new Piece('F', 7));
        pieces.add(new Piece('G', 8));

        for (Piece piece : pieces)
            p.addPiece(piece);

        assertArrayEquals(pieces.toArray(), p.getPieces().toArray());

        p.mixPieces();

        // Does not exist a method for assert not equal array elements
        boolean equal = true;
        for (int i = 0; i < p.getPieces().size(); i++)
            if (p.getPieces().get(i) != pieces.get(i))
                equal = false;

        assertFalse(equal);
    }
}