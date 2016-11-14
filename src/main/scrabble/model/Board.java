package main.scrabble.model;

import main.scrabble.exceptions.*;
import java.util.ArrayList;

public class Board {

    private static final int DIM = 15;
    private Cell cells[][];

    public Board() throws WrongCoordinateException {
        cells = new Cell[DIM][DIM];
        fillBoard();
    }

    private int checkOppositeDirection(Piece piece, Cell cell, Direction direction) throws WrongWordException {
        int score = 0;
        int multiplier = 1;
        ArrayList<Character> newWord = new ArrayList<>();

        newWord.add(piece.getLetter());

        Cell currentCell = cell;
        do {
            if (!currentCell.isEmpty()) {
                score += currentCell.getPiece().getScore();
                newWord.add(0, currentCell.getPiece().getLetter());
            }
            try {
                currentCell = getPreviousCell(currentCell, direction);
            } catch (OutOfBoundsException e) {
                break;
            }
        } while (!currentCell.isEmpty());

        try {
            currentCell = getNextCell(cell, direction);
        } catch (OutOfBoundsException e) {

        }

        do {
            if (!currentCell.isEmpty()) {
                score += currentCell.getPiece().getScore();
                newWord.add(currentCell.getPiece().getLetter());
            } else
                break;
            try {
                currentCell = getNextCell(currentCell, direction);
            } catch (OutOfBoundsException e) {
                break;
            }
        } while (!currentCell.isEmpty());

        String nw = "";
        for (char c : newWord)
            nw += c;

        if (!Dictionary.existWord(nw) && nw.length() > 1)
            throw new WrongWordException(nw);

        if (score > 0)
            System.out.println("Opposite word: " + nw + "\tScore: " + Integer.toString(score * multiplier));

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
            if (word.getPieces().indexOf(p) != word.getPieces().size() - 1)
                currentCell = getNextCell(currentCell, word.getDirection());
        }
    }

    public int playWord(Word word) throws OccupiedCellException, WrongWordException, NoPieceInCenterException, OutOfBoundsException, AloneWordException {
        int score = checkInsertion(word);
        insertWord(word);

        return score;
    }

    public int checkInsertion(Word word) throws AloneWordException, WrongWordException, OutOfBoundsException, NoPieceInCenterException {
        boolean validPosition = false;

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
                } else if (word.getOrigin().getX() > 7 ||
                        word.getOrigin().getX() + word.getPieces().size() < 7) {
                    throw new NoPieceInCenterException();
                }
            }
            validPosition = true;
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
                    validPosition = true;
                }
            } catch (OutOfBoundsException e) {
                break;
            }
        } while (!currentCell.isEmpty());

        currentCell = word.getOrigin();

        for (Piece p : word.getPieces()) {
            // If the piece was already inserted we don't want to
            // compute the score of its opposite direction
            while (!currentCell.isEmpty()) {
                newWord.add(currentCell.getPiece().getLetter());
                wordScore += currentCell.getPiece().getScore();
                currentCell = getNextCell(currentCell, word.getDirection());
                validPosition = true;
            }
            newWord.add(p.getLetter());
            wordScore += p.getScore() * currentCell.getLetterMultiplier();
            wordMultiplier *= currentCell.getWordMultiplier();
            extraWordsScore += checkOppositeDirection(p, currentCell, oppositeDir);

            if (word.getPieces().indexOf(p) != word.getPieces().size() - 1)
                currentCell = getNextCell(currentCell, word.getDirection());
        }

        try {
            do {
                currentCell = getNextCell(currentCell, word.getDirection());
                if (!currentCell.isEmpty()) {
                    newWord.add(currentCell.getPiece().getLetter());
                    wordScore += currentCell.getPiece().getScore();
                    validPosition = true;
                }
            } while (!currentCell.isEmpty());
        } catch (OutOfBoundsException e) { }

        String nw = "";
        for (char c : newWord) nw += c;

        if (!Dictionary.existWord(nw))
            throw new WrongWordException(nw);

        if (extraWordsScore > 0) validPosition = true;

        if (!validPosition) {
            throw (new AloneWordException());
        }

        int totalScore = wordScore * wordMultiplier + extraWordsScore;

        System.out.println("Word: " + nw + "\tBase score: " + Integer.toString(wordScore * wordMultiplier) + "\tTotal Score: " + Integer.toString(totalScore));

        return totalScore;
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