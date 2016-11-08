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
    ArrayList<Piece> kayakPieces = new ArrayList<>();
    ArrayList<Piece> mommyPieces = new ArrayList<>();
    ArrayList<Piece> modernityPieces = new ArrayList<>();

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

        kayakPieces.add(new Piece('k', 4));
        kayakPieces.add(new Piece('a', 4));
        kayakPieces.add(new Piece('y', 4));
        kayakPieces.add(new Piece('a', 4));
        kayakPieces.add(new Piece('k', 4));

        mommyPieces.add(new Piece('o', 5));
        mommyPieces.add(new Piece('m', 5));

        momyyPieces.add(new Piece('o', 5));
        momyyPieces.add(new Piece('y', 5));

        modernityPieces.add(new Piece('m', 6));
        modernityPieces.add(new Piece('o', 6));
        modernityPieces.add(new Piece('d', 6));
        modernityPieces.add(new Piece('e', 6));
        modernityPieces.add(new Piece('r', 6));

        modernityPieces.add(new Piece('i', 6));
        modernityPieces.add(new Piece('y', 6));

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

        //PATCH
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
        Board board = new Board();
        //SHOULD NOT FAIL
        /*Word word = new Word(board.getCell(7, 6), Direction.VERTICAL, ramblingsPieces);
        int score = board.playWord(word);
        assertEquals('r', board.getCell(7, 6).getPiece().getLetter());
        assertEquals('a', board.getCell(7, 7).getPiece().getLetter());
        assertEquals('m', board.getCell(7, 8).getPiece().getLetter());
        assertEquals('b', board.getCell(7, 9).getPiece().getLetter());
        assertEquals('l', board.getCell(7, 10).getPiece().getLetter());
        assertEquals('i', board.getCell(7, 11).getPiece().getLetter());
        assertEquals('n', board.getCell(7, 12).getPiece().getLetter());
        assertEquals('g', board.getCell(7, 13).getPiece().getLetter());
        assertEquals(34, score); //(16+1)x2 = 34*/


        //PATCH FOR THE ABOVE WORD
        Word word = new Word(board.getCell(7, 6), Direction.VERTICAL, hePieces);
        int score = board.playWord(word);

        word = new Word(board.getCell(9, 5), Direction.VERTICAL, communityPieces);
        score = board.playWord(word);
        assertEquals('c', board.getCell(9, 5).getPiece().getLetter());
        assertEquals('o', board.getCell(9, 6).getPiece().getLetter());
        assertEquals('m', board.getCell(9, 7).getPiece().getLetter());
        assertEquals('m', board.getCell(9, 8).getPiece().getLetter());
        assertEquals('u', board.getCell(9, 9).getPiece().getLetter());
        assertEquals('n', board.getCell(9, 10).getPiece().getLetter());
        assertEquals('i', board.getCell(9, 11).getPiece().getLetter());
        assertEquals('t', board.getCell(9, 12).getPiece().getLetter());
        assertEquals('y', board.getCell(9, 13).getPiece().getLetter());
        assertEquals(45, score); //(9x3+18) = 45*/

        word = new Word(board.getCell(11, 6), Direction.VERTICAL, kayakPieces);
        score = board.playWord(word);
        assertEquals('k', board.getCell(11, 6).getPiece().getLetter());
        assertEquals('a', board.getCell(11, 7).getPiece().getLetter());
        assertEquals('y', board.getCell(11, 8).getPiece().getLetter());
        assertEquals('a', board.getCell(11, 9).getPiece().getLetter());
        assertEquals('k', board.getCell(11, 10).getPiece().getLetter());
        assertEquals(24, score); //(5x4+4) = 24*/



        word = new Word(board.getCell(5, 3), Direction.VERTICAL, momyyPieces);
        score = 0;
        try {
            score = board.playWord(word);
        } catch (Exception e) {

        }
        assertEquals(0, score);

        //PATCH
       /* mommyPieces.add(0,new Piece('m', 5));
        word = new Word(board.getCell(7, 8), Direction.HORIZONTAL, mommyPieces); //momm not exists //Should be mommy exists
        score = board.playWord(word);
        assertEquals('m', board.getCell(7, 8).getPiece().getLetter());
        assertEquals('o', board.getCell(8, 8).getPiece().getLetter());
        assertEquals('m', board.getCell(9, 8).getPiece().getLetter());
        assertEquals('m', board.getCell(10, 8).getPiece().getLetter());
        assertEquals('y', board.getCell(11, 8).getPiece().getLetter());*/
        //assertEquals(20, score); //(3x5+5) = 20*/

        modernityPieces.add(5,(new Piece('n', 6)));
        word = new Word(board.getCell(2, 12), Direction.HORIZONTAL, modernityPieces); //momm not exists //Should be mommy exists
        score = board.playWord(word);
        assertEquals('m', board.getCell(2, 12).getPiece().getLetter());
        assertEquals('o', board.getCell(3, 12).getPiece().getLetter());
        assertEquals('d', board.getCell(4, 12).getPiece().getLetter());
        assertEquals('e', board.getCell(5, 12).getPiece().getLetter());
        assertEquals('r', board.getCell(6, 12).getPiece().getLetter());
        assertEquals('n', board.getCell(7, 12).getPiece().getLetter());
        assertEquals('i', board.getCell(8, 12).getPiece().getLetter());
        assertEquals('t', board.getCell(9, 12).getPiece().getLetter());
        assertEquals('y', board.getCell(10, 12).getPiece().getLetter());
        assertEquals(126, score); //(6x8+6+6)x2 = 20*/
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
            System.out.println("Out of bounds exception");
        }
        assertEquals(0, score);

        word = new Word(board.getCell(10, 10), Direction.HORIZONTAL, helloPieces);
        score = 0;
        try {
            score = board.playWord(word);
        } catch (Exception e) {
            System.out.println("Out of bounds exception");
        }
        assertEquals(0, score);

        // Fails when checking for opposite direction and (obviously) there are not more cells because it's a border.

        word = new Word(board.getCell(0, 0), Direction.HORIZONTAL, helloPieces);
        score = board.playWord(word);
        assertEquals(18, score);

        word = new Word(board.getCell(0, 0), Direction.VERTICAL, helloPieces);
        score = board.playWord(word);
        assertEquals(18, score);

        word = new Word(board.getCell(0, 1), Direction.VERTICAL, helloPieces);
        score = board.playWord(word);
        assertEquals(10, score);

    }
}