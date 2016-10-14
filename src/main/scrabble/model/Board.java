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
        List<Piece> oppositePieces = new ArrayList<Piece>();
        Coordinate c = new Coordinate();
        int size = pieces.size();
        Dictionary dic = new Dictionary(); //Better static?



        for (int i = 0; i < size; i++) {

              c = pieces.get(i).getCoordinates();

            while (matrix[c.x][c.y].getType() != CellType.PLAIN) {
                oppositePieces.add(0, matrix[c.x][c.y].getPiece());
                updateCoordinate(c,direction,"-");
            }

            c = pieces.get(i).getCoordinates();

            updateCoordinate(c,direction,"+");
            while (matrix[c.x][c.y].getType() != CellType.PLAIN) {
                oppositePieces.add(matrix[c.x][c.y].getPiece());
                updateCoordinate(c,direction,"+");
            }

            String s = createWordFromPieces((ArrayList<Piece>)oppositePieces);
            dic.existWord(s);
            //PONER LO DE PUNTUACIONES
            oppositePieces.clear();

        }
        return score;

    }

    private void updateCoordinate(Coordinate c, Direction dir, String op){
        if(op == "-") {
            if (dir == Direction.HORIZONTAL) {
                c.x--;
            } else {
                c.y--;
            }
        } else if(op == "+") {
            if (dir == Direction.HORIZONTAL) {
                c.x++;
            } else {
                c.y++;
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



















