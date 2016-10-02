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
    private WrongCoordinateException exc;

    @Before
    public void initialize() throws WrongCoordinateException{
        cell = new Cell(1, 2, CellType.plain);
        exc = new WrongCoordinateException(9,4);
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
    @Test
    public void testIsEmpty() throws Exception {
        assertTrue(cell.isEmpty());
        cell.setPiece(new Piece());
        assertFalse(cell.isEmpty());
        cell.setPiece(null);
    }
    @Test
    public void testToString() throws Exception {
        assertEquals("B3",cell.toString());
    }
    @Test
    public void testWrongCoordinateException() throws Exception {
        String message = "Error, the coordinate J5 are incorrect";
        assertEquals(message,exc.getMessage());
    }
}
