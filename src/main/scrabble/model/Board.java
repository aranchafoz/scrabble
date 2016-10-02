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

    private void fillBoard() {
        for(int i = 0; i < DIM; i++) {
            for(int j = 0; j < DIM; j++) {
                if((i == 7) && (j == 0 || j == 14)) {
                    matrix[i][j] = new Cell(i,j , CellType.tw);
                } else if((i == 0 || i == 14) && (j == 0 || j == 7 || j == 14 )) {
                    matrix[i][j] = new Cell(i, j, CellType.tw);
                } else if ((i == 5 || i == 9) && (j == 1|| j == 5 || j == 9 || j == 13)) {
                    matrix[i][j] = new Cell(i,j , CellType.tl);
                } else if ((i == 1 || i == 13) && (j == 1|| j == 5 || j == 9)) {
                    matrix[i][j] = new Cell(i,j , CellType.tl);
                } else if ((i == 5 || i == 9) && (j == 1|| j == 5 || j == 9 | j == 13)) {
                    matrix[i][j] = new Cell(i,j , CellType.dw);
                }
            }
        }
    }
}




















