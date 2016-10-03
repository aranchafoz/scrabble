package main.scrabble.model;

import main.scrabble.exceptions.*;

/**
 * Created by Ivorra on 30/09/16.
 */
public class Board {

    private static final int DIM = 15;
    private Cell matrix[][];

    public Board() throws WrongCoordinateException {
        matrix = new Cell[DIM][DIM];
        fillBoard();
    }

    private void fillBoard() throws WrongCoordinateException {
        for(int i = 0; i < DIM; i++) {
            for(int j = 0; j < DIM; j++) {
                if(fillTW(i,j)) {
                    matrix[i][j] = new Cell(i, j , CellType.tw);
                } else if (fillTL(i,j)) {
                    matrix[i][j] = new Cell(i, j , CellType.tl);
                } else if(fillDW(i,j)) {
                    matrix[i][j] = new Cell(i, j , CellType.dw);
                } else if(fillDL(i,j)) {
                    matrix[i][j] = new Cell(i, j , CellType.dl);
                } else {
                    matrix[i][j] = new Cell(i, j , CellType.plain);
                }
            }
        }
    }

    private boolean fillTW(int i, int j) throws WrongCoordinateException {
        boolean fill = false;

        if((i == 7) && (j == 0 || j == 14)) {
            fill = true;
        } else if((i == 0 || i == 14) && (j == 0 || j == 7 || j == 14 )) {
            fill = true;
        }

        return fill;
    }

    private boolean fillTL(int i, int j) throws WrongCoordinateException {
        boolean fill = false;

        if ((i == 5 || i == 9) && (j == 1|| j == 5 || j == 9 || j == 13)) {
            fill = true;
        } else if ((i == 1 || i == 13) && (j == 5 || j == 9)) {
            fill = true;
        }

        return fill;
    }

    private boolean fillDW(int i, int j) throws WrongCoordinateException {
        boolean fill = false;

         if ((i == 1 || i == 13) && (j == 1|| j == 13)) {
             fill = true;
        } else if ((i == 2 || i == 12) && (j == 2|| j == 12)) {
             fill = true;
        } else if ((i == 3 || i == 11) && (j == 3|| j == 11)) {
             fill = true;
        } else if ((i == 4 || i == 10) && (j == 4|| j == 10)) {
             fill = true;
        }  else if (i == 7 && j == 7) {
             fill = true;
        }

        return fill;
    }

    private boolean fillDL(int i, int j) throws WrongCoordinateException {
        boolean fill = false;

        if ((i == 1 || i == 14) && (j == 3|| j == 11)) {
            fill = true;
        } else if ((i == 2 || i == 12) && (j == 6|| j == 8)) {
            fill = true;
        } else if ((i == 3 || i == 11) && (j == 0|| j == 7 || j == 14)) {
            fill = true;
        } else if ((i == 6 || i == 8) && (j == 2|| j == 6 || j == 8 || j == 12)) {
            fill = true;
        } else if ((i == 7) && (j == 3|| j == 11)) {
            fill = true;
        }

        return fill;
    }

    public Cell getCell(int x, int y) {
        Cell cell = matrix[x-1][y-1];
        return cell;
    }

}



















