package main.scrabble.model;

import main.scrabble.exceptions.WrongCoordinateException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ivorra on 30/09/16.
 */
public class CellTest {
    private Cell cell;

    @Before
    public void initialize() throws WrongCoordinateException{
        cell = new Cell(1, 2/*, CellType.plain*/);
    }

    @Test
    public void testCheckCoordinates() throws Exception {
        assertTrue(cell.checkCoordinates(1, 2));
        assertFalse(cell.checkCoordinates(1, 15));
        assertFalse(cell.checkCoordinates(1, -1));

        assertFalse(cell.checkCoordinates(15, 2));
        assertFalse(cell.checkCoordinates(15, 15));
        assertFalse(cell.checkCoordinates(15, -2));

        assertFalse(cell.checkCoordinates(-1, 2));
        assertFalse(cell.checkCoordinates(-1, 15));
        assertFalse(cell.checkCoordinates(-1, -2));
    }

    public void testIsEmpty() throws Exception {
        assertTrue(cell.isEmpty());
        cell.setPiece(new Piece());
        assertFalse(cell.isEmpty());
        cell.setPiece(null);
    }

    public void testToString() throws Exception {
        assertEquals(cell.toString(),"B3");
    }
}
