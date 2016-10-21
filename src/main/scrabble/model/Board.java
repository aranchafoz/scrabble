package main.scrabble.model;

import main.scrabble.exceptions.*;
import java.util.ArrayList;


//getCell why -1?? Use in other methods!!??
/**
class Coordinate {
    public int x;
    public int y;

    public Coordinate(int cx, int cy){
        x = cx;
        y = cy;
    }

    public Coordinate(Coordinate c){
        x = c.x;
        y = c.y;
    }

    public void updateCoordinate(Direction dir, String op){
        if(op == "-") {
            if (dir == Direction.HORIZONTAL) {
                x--;
            } else {
                y--;
            }
        } else if(op == "+") {
            if (dir == Direction.HORIZONTAL) {
                x++;
            } else {
                y++;
            }
        }
    }
}
*/

public class Board {

    private static final int DIM = 15;
    private Cell cells[][];

    public Board() throws WrongCoordinateException {
        cells = new Cell[DIM][DIM];
        fillBoard();
    }
/**
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
        Coordinate c = new Coordinate(0,0);

        for (int i = 0; i < pieces.size(); i++) {
              c.x = pieces.get(i).getCoordinateX();
              c.y = pieces.get(i).getCoordinateY();

            while (cells[c.x][c.y].getType() != CellType.PLAIN) {
                oppositePieces.add(0, cells[c.x][c.y].getPiece());
                c.updateCoordinate(direction,"-");
            }

            c.x = pieces.get(i).getCoordinateX();
            c.y = pieces.get(i).getCoordinateY();
            c.updateCoordinate(direction,"+");

            while (cells[c.x][c.y].getType() != CellType.PLAIN) {
                oppositePieces.add(cells[c.x][c.y].getPiece());
                c.updateCoordinate(direction,"+");
            }

            //PONER LO DE PUNTUACIONES
            String s = createWordFromPieces(oppositePieces);
            Dictionary.existWord(s);
            //PONER LO DE PUNTUACIONES
            oppositePieces.clear();
        }
        return score;
    }
    */

    private int checkOppositeDirection(Piece piece, Cell cell, Direction direction) throws WrongWordException, OutOfBoundsException {
        int score = 0;
        int multiplier = 1;
        ArrayList<Character> newWord = new ArrayList<>();

        newWord.add(piece.getLetter());

        Cell currentCell = cell;
        do {
            if (!currentCell.isEmpty())
                score += currentCell.getPiece().getScore();
            currentCell = getPreviousCell(currentCell, direction);
        } while (!currentCell.isEmpty());

        currentCell = getNextCell(currentCell, direction);

        do {
            if (!currentCell.isEmpty())
                score += currentCell.getPiece().getScore();
            currentCell = getNextCell(currentCell, direction);
        } while (!currentCell.isEmpty());

        String nw = "";
        for (char c : newWord)
            nw += c;

        if (!Dictionary.existWord(nw) && nw.length() > 1)
            throw new WrongWordException(nw);

        return score * multiplier;
    }

    private Cell getNextCell(Cell currentCell, Direction dir) throws OutOfBoundsException {
        try {
            if (dir == Direction.HORIZONTAL)
                return cells[currentCell.getX() + 1][currentCell.getY()];
            else
                return cells[currentCell.getX()][currentCell.getY() + 1];
        } catch (Exception e) {
            throw new OutOfBoundsException();
        }
    }

    private Cell getPreviousCell(Cell currentCell, Direction dir) throws OutOfBoundsException {
        try {
            if (dir == Direction.HORIZONTAL)
                return cells[currentCell.getX() - 1][currentCell.getY()];
            else
                return cells[currentCell.getX()][currentCell.getY() - 1];
        } catch (Exception e) {
            throw new OutOfBoundsException();
        }
    }

    private void insertWord(Word word) throws OutOfBoundsException {
        Cell currentCell = word.getOrigin();
        for (Piece p : word.getPieces()) {
            while (!currentCell.isEmpty())
                currentCell = getNextCell(currentCell, word.getDirection());
            cells[currentCell.getX()][currentCell.getY()].setPiece(p);
            currentCell = getNextCell(currentCell, word.getDirection());
        }
    }

    public int playWord(Word word) throws OccupiedCellException, WrongWordException, OutOfBoundsException, NoPieceInCenterException {
        /*
        int score = 0;
        Direction direction = word.getDirection();
        Coordinate origin = new Coordinate(word.getOriginX(),word.getOriginY());

        if(!cells[origin.x][origin.y].isEmpty()){
            throw new OccupiedCellException(word.getOrigin());
        }

        Coordinate c = new Coordinate(origin);
        ArrayList<Piece> completedWord = new ArrayList<>();


        while(!cells[c.x][c.y].isEmpty()){
            completedWord.add(0, cells[c.x][c.y].getPiece());
            c.updateCoordinate(direction,"-");
        }
        c = new Coordinate(origin);
        c.updateCoordinate(direction,"+");

        ArrayList<Piece> auxWord = new ArrayList<>();
        ArrayList<Piece> pieces = word.getPieces();

        for(Piece p : pieces) {
            auxWord.add(p);
        }
        auxWord.remove(0); //Inserted before

        while(!auxWord.isEmpty()){
            if(!cells[c.x][c.y].isEmpty()){
                completedWord.add(cells[c.x][c.y].getPiece());
            } else {
                completedWord.add(auxWord.get(0));
                auxWord.remove(0);
            }
            c.updateCoordinate(direction,"+");

        }

        for(Piece p : pieces) {
            auxWord.add(p);
        }
        auxWord.remove(0); //Inserted before

        while(!auxWord.isEmpty()){
            if(cells[c.x][c.y].isEmpty()){
                cells[c.x][c.y].setPiece(auxWord.get(0));
                auxWord.remove(0);
            }
            c.updateCoordinate(direction,"+");

        }

        //PONER LO DE PUNTUACIONES
        String s = createWordFromPieces(completedWord);
        // if(!Dictionary.existWord(s)){throw new  WrongWordException(s);}
        //PONER LO DE PUNTUACIONES

        //Insertarla al final!!! (iterar igual que el bucle de antes, donde haya vacio poner la pieza)


        return score;
        */

        if (cells[7][7].isEmpty()) {
            if (word.getDirection() == Direction.VERTICAL) {
                if (word.getOrigin().getX() != 7) {
                    throw new NoPieceInCenterException();
                } else if (word.getOrigin().getY() > 7 ||
                        word.getOrigin().getY() + word.getPieces().size() < 7) {
                    throw new NoPieceInCenterException();
                }
            } else {
                if (word.getOrigin().getY() != 7) {
                    throw new NoPieceInCenterException();
                } else if (word.getOrigin().getX() > 7 &&
                        word.getOrigin().getX() + word.getPieces().size() < 7) {
                    throw new NoPieceInCenterException();
                }
            }
        }

        int wordScore = 0;
        int extraWordsScore = 0;
        int wordMultiplier = 1;
        Direction oppositeDir;
        ArrayList<Character> newWord = new ArrayList<>();

        if (word.getDirection() == Direction.HORIZONTAL)
            oppositeDir = Direction.VERTICAL;
        else
            oppositeDir = Direction.HORIZONTAL;

        Cell currentCell = word.getOrigin();

        do {
            try {
                currentCell = getPreviousCell(currentCell, word.getDirection());
                if (!currentCell.isEmpty()) {
                    newWord.add(0, currentCell.getPiece().getLetter());
                    wordScore += currentCell.getPiece().getScore();
                }
            } catch (OutOfBoundsException e) {
                break;
            }
        } while (!currentCell.isEmpty());

        currentCell = word.getOrigin();

        for (Piece p : word.getPieces()) {
            /** If the piece was already inserted we don't want to
             * compute the score of its opposite direction
             */
            while (!currentCell.isEmpty()) {
                newWord.add(currentCell.getPiece().getLetter());
                wordScore += currentCell.getPiece().getScore();
                currentCell = getNextCell(currentCell, word.getDirection());
            }
            newWord.add(p.getLetter());
            wordScore += p.getScore() * currentCell.getLetterMultiplier();
            wordMultiplier *= currentCell.getWordMultiplier();
            extraWordsScore += checkOppositeDirection(p, currentCell, oppositeDir);

            currentCell = getNextCell(currentCell, word.getDirection());
        }
        try {
            do {
                currentCell = getNextCell(currentCell, word.getDirection());
                if (!currentCell.isEmpty()) {
                    newWord.add(currentCell.getPiece().getLetter());
                    wordScore += currentCell.getPiece().getScore();
                }
            } while (!currentCell.isEmpty());
        } catch (OutOfBoundsException e) { }

        String nw = "";
        for (char c : newWord) nw += c;

        if (!Dictionary.existWord(nw))
            throw new WrongWordException(nw);

        insertWord(word);

        return wordScore * wordMultiplier + extraWordsScore;
    }

    public Cell getCell(int x, int y) {
        return cells[x][y];
    }

    private void fillBoard() throws WrongCoordinateException {
        for(int i = 0; i < DIM; i++) {
            for(int j = 0; j < DIM; j++) {
                if(fillTW(i,j)) {
                    cells[i][j] = new Cell(i, j , CellType.TRIPLE_WORD);
                } else if (fillTL(i,j)) {
                    cells[i][j] = new Cell(i, j , CellType.TRIPLE_LETTER);
                } else if(fillDW(i,j)) {
                    cells[i][j] = new Cell(i, j , CellType.DOUBLE_WORD);
                } else if(fillDL(i,j)) {
                    cells[i][j] = new Cell(i, j , CellType.DOUBLE_LETTER);
                } else if(i == 7 && j == 7) {
                    cells[i][j] = new Cell(i, j , CellType.CENTRAL_CELL);
                } else {
                    cells[i][j] = new Cell(i, j , CellType.PLAIN);
                }
            }
        }
    }

    private boolean fillTW(int i, int j) {
        boolean fill = false;

        if((i == 7) && (j == 0 || j == 14)) {
            fill = true;
        } else if((i == 0 || i == 14) && (j == 0 || j == 7 || j == 14 )) {
            fill = true;
        }

        return fill;
    }

    private boolean fillTL(int i, int j) {
        boolean fill = false;

        if ((i == 5 || i == 9) && (j == 1|| j == 5 || j == 9 || j == 13)) {
            fill = true;
        } else if ((i == 1 || i == 13) && (j == 5 || j == 9)) {
            fill = true;
        }

        return fill;
    }

    private boolean fillDW(int i, int j) {
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

    private boolean fillDL(int i, int j) {
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