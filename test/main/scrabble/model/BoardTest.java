package main.scrabble.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by enrique on 19/10/16.
 */
public class BoardTest {
    ArrayList<Piece> helloPieces;
    ArrayList<Piece> heroPieces;
    ArrayList<Piece> eraPieces;
    ArrayList<Piece> incorrectWordPieces;

    @Before
    public void setUp() {
        helloPieces = new ArrayList<>();
        helloPieces.add(new Piece('h', 1));
        helloPieces.add(new Piece('e', 1));
        helloPieces.add(new Piece('l', 1));
        helloPieces.add(new Piece('l', 1));
        helloPieces.add(new Piece('o', 1));

        heroPieces = new ArrayList<>();
        heroPieces.add(new Piece('h', 10));
        heroPieces.add(new Piece('r', 3));
        heroPieces.add(new Piece('o', 1));

        eraPieces = new ArrayList<>();
        eraPieces.add(new Piece('e', 2));
        eraPieces.add(new Piece('a', 8));

        incorrectWordPieces = new ArrayList<>();
        incorrectWordPieces.add(new Piece('a', 2));
        incorrectWordPieces.add(new Piece('b', 2));
        incorrectWordPieces.add(new Piece('z', 2));
    }

    @Test
    public void insertWord() throws Exception {
        Board board = new Board();
        Word word = new Word(board.getCell(7, 7), Direction.VERTICAL, helloPieces);
        int score = board.playWord(word);
        assertEquals('h', board.getCell(7, 7).getPiece().getLetter());
        assertEquals('e', board.getCell(7, 8).getPiece().getLetter());
        assertEquals('l', board.getCell(7, 9).getPiece().getLetter());
        assertEquals('l', board.getCell(7, 10).getPiece().getLetter());
        assertEquals('o', board.getCell(7, 11).getPiece().getLetter());
        assertEquals(12, score);

        word = new Word(board.getCell(5, 3), Direction.VERTICAL, incorrectWordPieces);
        score = 0;
        try {
            score = board.playWord(word);
        } catch (Exception e) {

        }
        assertEquals(0, score);

        word = new Word(board.getCell(6, 8), Direction.HORIZONTAL, heroPieces);
        score = board.playWord(word);
        assertEquals('h', board.getCell(6, 8).getPiece().getLetter());
        assertEquals('e', board.getCell(7, 8).getPiece().getLetter());
        assertEquals('r', board.getCell(8, 8).getPiece().getLetter());
        assertEquals('o', board.getCell(9, 8).getPiece().getLetter());
        assertEquals(28, score);

        word = new Word(board.getCell(8, 7), Direction.VERTICAL, eraPieces);
        score = board.playWord(word);
        assertEquals('e', board.getCell(8, 7).getPiece().getLetter());
        assertEquals('r', board.getCell(8, 8).getPiece().getLetter());
        assertEquals('a', board.getCell(8, 9).getPiece().getLetter());
        assertEquals(15, score); //We expect to get 15 = H(1) + L(1) + E(2) + R(3) + A(8)
                                 //We get 17

    }

    @Test
    public void noPiecesInMiddle() throws Exception {
        Board board = new Board();
        Word word = new Word(board.getCell(2, 10), Direction.VERTICAL, helloPieces);
        int score = 0;
        try {
            score = board.playWord(word);
        } catch (Exception e) {

        }
        assertEquals(0, score);

        word = new Word(board.getCell(7, 8), Direction.HORIZONTAL, helloPieces);
        score = 0;
        try {
            score = board.playWord(word);
        } catch (Exception e) {

        }
        assertEquals(0, score);
    }

    @Test
    public void insertStartedWord() throws Exception {
        ArrayList<Piece> hello2Pieces = new ArrayList<>(helloPieces);
        hello2Pieces.remove(0);
        Board board = new Board();
        Word word = new Word(board.getCell(7, 7), Direction.HORIZONTAL, helloPieces);
        board.playWord(word);
        word = new Word(board.getCell(7, 8), Direction.VERTICAL, hello2Pieces);
        int score = board.playWord(word);
        assertEquals(6, score);
    }

    @Test
    public void checkOpposite() throws Exception {

    }

}