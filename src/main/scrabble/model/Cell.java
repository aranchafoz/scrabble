package main.scrabble.model;

/**
 * Created by enrique on 27/09/16.
 */

/**
 * TODO:
 * - Override toString() method. (string format: "XY", being X the
 * LETTER corresponding to the X axis and Y the NUMBER of the Y axis)
 */
public class Cell {

    private Piece piece;
    private int x;
    private int y;
    private CellType type;

  public Cell() {
      piece = new Piece();
      x = 0;
      y = 0;
      type = CellType.plain
  }

 public Cell(Piece piece, int x, int y, CellType type) throws WrongCoordinateException {
     if(checkCoordinates(x,y)) {
         this.piece = piece;
         this.x = x;
         this.y = y;
         this.type = type;
     } else {
         throws new WrongCoordinateException(x,y);
     }
 }

 private boolean checkCoordinates(int xCoord, int yCoord) {
     boolean correct = true;
     if(xCoord < 0 || yCoord < 0){
         if(xCoord > 14) || yCoord > 14) {
             correct = false;
         }
     }
     return coorrect;
 }
}