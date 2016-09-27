package scrabble.exceptions;

import scrabble.model.Cell;

/**
 * Created by enrique on 27/09/16.
 */
public class OccupiedCellException extends Exception {
    private Cell cell;

    public OccupiedCellException(Cell cell) {
        this.cell = cell;
    }

    @Override
    public String getMessage() {
        return "Error, the cell " + cell.toString() + " is occupied by " + cell.getPiece().toString();
    }
}
