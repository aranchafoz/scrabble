package main.scrabble.model;

import main.scrabble.exceptions.*;

/**
 * Created by Ivorra on 30/09/16.
 */
public class Board {

    private static final int DIM;
    private Cell matrix[][];

    Board() {
        matrix = new Cell[DIM][DIM];
        for(int i = 0; i < DIM; i++) {
            for(int j = 0; j < DIM; j++) {
                Cell[i][j] = new Cell();
            }
        }
    }


}
