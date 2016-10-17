package main.scrabble.model;

import main.scrabble.exceptions.*;

import java.util.ArrayList;
import java.util.List;


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

    public int checkOpositeDirection(Word word) {
        int score = 0;
        Direction direction;

        if (word.getDirection() == Direction.HORIZONTAL) {
            direction = Direction.VERTICAL;
        } else {
            direction = Direction.HORIZONTAL;
        }

        ArrayList<Piece> pieces = word.getPieces();
        ArrayList<Piece> oppositePieces = new ArrayList<>();
        int cx = 0;
        int cy = 0;

        for (int i = 0; i < pieces.size(); i++) {
              cx = pieces.get(i).getCoordinateX();
              cy = pieces.get(i).getCoordinateY();

            while (matrix[cx][cy].getType() != CellType.PLAIN) {
                oppositePieces.add(0, matrix[cx][cy].getPiece());
                updateCoordinate(cx,cy,direction,"-");
            }

            cx = pieces.get(i).getCoordinateX();
            cy = pieces.get(i).getCoordinateY();

            updateCoordinate(cx,cy,direction,"+");
            while (matrix[cx][cy].getType() != CellType.PLAIN) {
                oppositePieces.add(matrix[cx][cy].getPiece());
                updateCoordinate(cx,cy,direction,"+");
            }

            String s = createWordFromPieces(oppositePieces);
            Dictionary.existWord(s);
            //PONER LO DE PUNTUACIONES
            oppositePieces.clear();
        }
        return score;
    }

    private void updateCoordinate(int cx,int cy, Direction dir, String op){
        if(op == "-") {
            if (dir == Direction.HORIZONTAL) {
                cx--;
            } else {
                cy--;
            }
        } else if(op == "+") {
            if (dir == Direction.HORIZONTAL) {
                cx++;
            } else {
                cy++;
            }
        }
    }

    public String createWordFromPieces(ArrayList<Piece> p){
        int size = p.size();
        String s = "";
        for(int i = 0; i < size; i++ ){
            s = s + p.get(i).getLetter();
        }
        return s;
    }















    public Cell getCell(int x, int y) {
        Cell cell = matrix[x-1][y-1];
        return cell;
    }

    private void fillBoard() throws WrongCoordinateException {
        for(int i = 0; i < DIM; i++) {
            for(int j = 0; j < DIM; j++) {
                if(fillTW(i,j)) {
                    matrix[i][j] = new Cell(i, j , CellType.TRIPLE_WORD);
                } else if (fillTL(i,j)) {
                    matrix[i][j] = new Cell(i, j , CellType.TRIPLE_LETTER);
                } else if(fillDW(i,j)) {
                    matrix[i][j] = new Cell(i, j , CellType.DOUBLE_WORD);
                } else if(fillDL(i,j)) {
                    matrix[i][j] = new Cell(i, j , CellType.DOUBLE_LETTER);
                } else if(i == 7 && j == 7) {
                    matrix[i][j] = new Cell(i, j , CellType.CENTRAL_CELL);
                } else {
                    matrix[i][j] = new Cell(i, j , CellType.PLAIN);
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
        }  /*else if (i == 7 && j == 7) {
             fill = true;
        }*/

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

}



















