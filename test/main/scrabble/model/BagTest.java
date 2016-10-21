package main.scrabble.model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by enrique on 18/10/16.
 */
public class BagTest {
    @Test
    public void isEmpty() throws Exception {
        Bag bag = new Bag();
        assertFalse(bag.isEmpty());
        int n = bag.getRemaining();
        for (int i = 0; i < n; i++) {
            assertFalse(bag.isEmpty());
            bag.takePiece();
        }
        assertTrue(bag.isEmpty());
    }

    @Test
    public void takePiece() throws Exception {
        Bag bag = new Bag();
        int nPiecesBefore = bag.getRemaining();
        bag.takePiece();
        int nPiecesAfter = bag.getRemaining();
        assertNotEquals(nPiecesBefore, nPiecesAfter);
    }

    @Test
    public void changePiece() throws Exception {
        Bag bag = new Bag();
        Piece p = bag.takePiece();
        int nPieces = bag.getRemaining();
        Piece p2 = bag.changePiece(p);
        assertNotEquals(p, p2);
        assertEquals(nPieces, bag.getRemaining());
    }

}