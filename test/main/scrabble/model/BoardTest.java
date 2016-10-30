package main.scrabble.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by enrique on 19/10/16.
 */
public class BoardTest {
    ArrayList<Piece> helloPieces = new ArrayList<>();
    ArrayList<Piece> heroPieces  = new ArrayList<>();
    ArrayList<Piece> hePieces = new ArrayList<>();

    ArrayList<Piece> eraPieces = new ArrayList<>();
    ArrayList<Piece> ramblingsPieces = new ArrayList<>();
    ArrayList<Piece> communityPieces = new ArrayList<>();
    ArrayList<Piece> cayakPieces = new ArrayList<>();
    ArrayList<Piece> mommyPieces = new ArrayList<>();
    ArrayList<Piece> paintingsPieces = new ArrayList<>();

    ArrayList<Piece> incorrectWordPieces = new ArrayList<>();
    ArrayList<Piece> momyyPieces = new ArrayList<>();



    @Before
    public void setUp() {

        helloPieces.add(new Piece('h', 1));
        helloPieces.add(new Piece('e', 1));
        helloPieces.add(new Piece('l', 1));
        helloPieces.add(new Piece('l', 1));
        helloPieces.add(new Piece('o', 1));

        heroPieces.add(new Piece('h', 10));
        heroPieces.add(new Piece('r', 3));
        heroPieces.add(new Piece('o', 1));

        eraPieces.add(new Piece('e', 2));
        eraPieces.add(new Piece('a', 8));

        hePieces.add(new Piece('h', 5));
        hePieces.add(new Piece('e', 1));

        hePieces.add(new Piece('h', 5));

        ramblingsPieces.add(new Piece('r', 2));
        ramblingsPieces.add(new Piece('a', 2));
        ramblingsPieces.add(new Piece('m', 2));
        ramblingsPieces.add(new Piece('b', 2));
        ramblingsPieces.add(new Piece('l', 2));
        ramblingsPieces.add(new Piece('i', 2));
        ramblingsPieces.add(new Piece('n', 2));
        ramblingsPieces.add(new Piece('g', 2));
        ramblingsPieces.add(new Piece('s', 2));

        communityPieces.add(new Piece('c', 3));
        communityPieces.add(new Piece('o', 3));
        communityPieces.add(new Piece('m', 3));
        communityPieces.add(new Piece('m', 3));
        communityPieces.add(new Piece('u', 3));
        communityPieces.add(new Piece('n', 3));
        communityPieces.add(new Piece('i', 3));
        communityPieces.add(new Piece('t', 3));
        communityPieces.add(new Piece('y', 3));

        cayakPieces.add(new Piece('c', 4));
        cayakPieces.add(new Piece('a', 4));
        cayakPieces.add(new Piece('y', 4));
        cayakPieces.add(new Piece('a', 4));
        cayakPieces.add(new Piece('k', 4));

        mommyPieces.add(new Piece('o', 5));
        mommyPieces.add(new Piece('m', 5));

        momyyPieces.add(new Piece('o', 5));
        momyyPieces.add(new Piece('y', 5));


        mommyPieces.add(new Piece('p', 6));
        mommyPieces.add(new Piece('i', 6));
        mommyPieces.add(new Piece('i', 6));
        mommyPieces.add(new Piece('t', 6));
        mommyPieces.add(new Piece('i', 6));
        mommyPieces.add(new Piece('n', 6));
        mommyPieces.add(new Piece('g', 6));

        incorrectWordPieces.add(new Piece('a', 2));
        incorrectWordPieces.add(new Piece('b', 2));
        incorrectWordPieces.add(new Piece('z', 2));
    }

    @Test
    public void insertWordBasic() throws Exception {
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
    public void insertToExistingWordVertical() throws Exception {
        Board board = new Board();
        Word word = new Word(board.getCell(7, 7), Direction.VERTICAL, hePieces);
        int score = board.playWord(word);
        assertEquals('h', board.getCell(7, 7).getPiece().getLetter());
        assertEquals('e', board.getCell(7, 8).getPiece().getLetter());
        assertEquals(12, score); //(5+1)x2 = 6x2 = 12

        heroPieces.remove(0);
        word = new Word(board.getCell(7, 9), Direction.VERTICAL, heroPieces);
        score = board.playWord(word);
        assertEquals('r', board.getCell(7, 9).getPiece().getLetter());
        assertEquals('o', board.getCell(7, 10).getPiece().getLetter());
        assertEquals(10, score); //(3+1) + (5+1) = 4+6 = 10

        heroPieces.add(0,new Piece('h', 10));

    }

    @Test
    public void insertToExistingWordHorizontal() throws Exception {
        Board board = new Board();
        Word word = new Word(board.getCell(7, 7), Direction.HORIZONTAL, hePieces);
        int score = board.playWord(word);
        assertEquals('h', board.getCell(7, 7).getPiece().getLetter());
        assertEquals('e', board.getCell(8, 7).getPiece().getLetter());
        assertEquals(12, score); //(5+1)x2 = 6x2 = 12

        heroPieces.remove(0);
        word = new Word(board.getCell(9, 7), Direction.HORIZONTAL, heroPieces);
        score = board.playWord(word);
        assertEquals('r', board.getCell(9, 7).getPiece().getLetter());
        assertEquals('o', board.getCell(10, 7).getPiece().getLetter());
        assertEquals(10, score); //(3+1) + (5+1) = 4+6 = 10

        heroPieces.add(0,new Piece('h', 10));

    }

    @Test
    public void insertSeveralWords() throws Exception {

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
    public void insertWordOutOufBounds() throws Exception{
        Board board = new Board();
        Word word = new Word(board.getCell(7, 7), Direction.HORIZONTAL, helloPieces);
        board.playWord(word);

        word = new Word(board.getCell(14, 14), Direction.VERTICAL, helloPieces);
        int score = 0;
        try {
            score = board.playWord(word);
        } catch (Exception e) {

        }
        assertEquals(0, score);

        word = new Word(board.getCell(10, 10), Direction.HORIZONTAL, helloPieces);
        score = 0;
        try {
            score = board.playWord(word);
        } catch (Exception e) {

        }
        assertEquals(0, score);

        //Fails when checking for opposite direction and (obviously) there are not more cells because it's a border.

        word = new Word(board.getCell(0, 0), Direction.HORIZONTAL, helloPieces);
        score = 0;
        score = board.playWord(word);
        assertEquals(18, score);

        word = new Word(board.getCell(0, 0), Direction.VERTICAL, helloPieces);
        score = 0;
        score = board.playWord(word);
        assertEquals(18, score);

        word = new Word(board.getCell(0, 1), Direction.VERTICAL, helloPieces);
        score = 0;
        score = board.playWord(word);
        assertEquals(10, score);

    }

    @Test
    public void checkOpposite() throws Exception {

    }

}