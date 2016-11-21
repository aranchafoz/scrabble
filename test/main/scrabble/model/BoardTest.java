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

    ArrayList<Piece> labiodentalPieces = new ArrayList<>();
    ArrayList<Piece> letPieces = new ArrayList<>();
    ArrayList<Piece> crimePieces = new ArrayList<>();
    ArrayList<Piece> requiemPieces = new ArrayList<>();
    ArrayList<Piece> paramedicPieces = new ArrayList<>();
    ArrayList<Piece> liberatePieces = new ArrayList<>();
    ArrayList<Piece> primePieces = new ArrayList<>();
    ArrayList<Piece> matePieces = new ArrayList<>();
    ArrayList<Piece> promotionsPieces = new ArrayList<>();

    ArrayList<Piece> oPieces = new ArrayList<>();
    ArrayList<Piece> iPieces = new ArrayList<>();




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

        labiodentalPieces.add(new Piece('l', 1));
        labiodentalPieces.add(new Piece('a', 1));
        labiodentalPieces.add(new Piece('b', 1));
        labiodentalPieces.add(new Piece('i', 1));
        labiodentalPieces.add(new Piece('o', 1));
        labiodentalPieces.add(new Piece('d', 1));
        labiodentalPieces.add(new Piece('e', 1));
        labiodentalPieces.add(new Piece('n', 1));
        labiodentalPieces.add(new Piece('t', 1));
        labiodentalPieces.add(new Piece('a', 1));
        labiodentalPieces.add(new Piece('l', 1));

        letPieces.add(new Piece('e', 2));
        letPieces.add(new Piece('t', 2));

        crimePieces.add(new Piece('c', 3));
        crimePieces.add(new Piece('r', 3));
        crimePieces.add(new Piece('i', 3));
        crimePieces.add(new Piece('m', 3));

        requiemPieces.add(new Piece('e', 4));
        requiemPieces.add(new Piece('q', 4));
        requiemPieces.add(new Piece('u', 4));
        requiemPieces.add(new Piece('i', 4));
        requiemPieces.add(new Piece('e', 4));
        requiemPieces.add(new Piece('m', 4));

        paramedicPieces.add(new Piece('p', 5));
        paramedicPieces.add(new Piece('a', 5));
        paramedicPieces.add(new Piece('r', 5));
        paramedicPieces.add(new Piece('a', 5));
        paramedicPieces.add(new Piece('m', 5));
        paramedicPieces.add(new Piece('e', 5));
        paramedicPieces.add(new Piece('d', 5));
        paramedicPieces.add(new Piece('i', 5));

        liberatePieces.add(new Piece('i', 6));
        liberatePieces.add(new Piece('b', 6));
        liberatePieces.add(new Piece('e', 6));
        liberatePieces.add(new Piece('r', 6));
        liberatePieces.add(new Piece('a', 6));
        liberatePieces.add(new Piece('t', 6));
        liberatePieces.add(new Piece('e', 6));

        primePieces.add(new Piece('p', 7));
        primePieces.add(new Piece('r', 7));
        primePieces.add(new Piece('i', 7));
        primePieces.add(new Piece('m', 7));

        matePieces.add(new Piece('m', 8));
        matePieces.add(new Piece('a', 8));
        matePieces.add(new Piece('t', 8));

        promotionsPieces.add(new Piece('p', 9));
        promotionsPieces.add(new Piece('r', 9));
        promotionsPieces.add(new Piece('m', 9));
        promotionsPieces.add(new Piece('o', 9));
        promotionsPieces.add(new Piece('t', 9));
        promotionsPieces.add(new Piece('i', 9));
        promotionsPieces.add(new Piece('o', 9));
        promotionsPieces.add(new Piece('n', 9));
        promotionsPieces.add(new Piece('s', 9));

        oPieces.add(new Piece('o', 1));
        iPieces.add(new Piece('i', 1));

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
    public void insertSeveralWords() throws Exception{
        Board board = new Board();
        Word word = new Word(board.getCell(7, 4), Direction.VERTICAL, labiodentalPieces);
        int score = board.playWord(word);

        assertEquals('l', board.getCell(7, 4).getPiece().getLetter());
        assertEquals('a', board.getCell(7, 5).getPiece().getLetter());
        assertEquals('b', board.getCell(7, 6).getPiece().getLetter());
        assertEquals('i', board.getCell(7, 7).getPiece().getLetter());
        assertEquals('o', board.getCell(7, 8).getPiece().getLetter());
        assertEquals('d', board.getCell(7, 9).getPiece().getLetter());
        assertEquals('e', board.getCell(7, 10).getPiece().getLetter());
        assertEquals('n', board.getCell(7, 11).getPiece().getLetter());
        assertEquals('t', board.getCell(7, 12).getPiece().getLetter());
        assertEquals('a', board.getCell(7, 13).getPiece().getLetter());
        assertEquals('l', board.getCell(7, 14).getPiece().getLetter());

        assertEquals(72, score); //(11*1+1)*6 = 72

        word = new Word(board.getCell(7, 14), Direction.HORIZONTAL, liberatePieces);
        score = board.playWord(word);
        assertEquals('l', board.getCell(7, 14).getPiece().getLetter());
        assertEquals('i', board.getCell(8, 14).getPiece().getLetter());
        assertEquals('b', board.getCell(9, 14).getPiece().getLetter());
        assertEquals('e', board.getCell(10, 14).getPiece().getLetter());
        assertEquals('r', board.getCell(11, 14).getPiece().getLetter());
        assertEquals('a', board.getCell(12, 14).getPiece().getLetter());
        assertEquals('t', board.getCell(13, 14).getPiece().getLetter());
        assertEquals('e', board.getCell(14, 14).getPiece().getLetter());

        assertEquals(147, score); //(7*6+6)*3+1 = 145

        word = new Word(board.getCell(7, 4), Direction.HORIZONTAL, letPieces);
        score = board.playWord(word);
        assertEquals('l', board.getCell(7, 4).getPiece().getLetter());
        assertEquals('e', board.getCell(8, 4).getPiece().getLetter());
        assertEquals('t', board.getCell(9, 4).getPiece().getLetter());

        assertEquals(5, score); //(2*2+0)*1+1 = 5

        word = new Word(board.getCell(8, 0), Direction.VERTICAL, crimePieces);
        score = board.playWord(word);
        assertEquals('c', board.getCell(8, 0).getPiece().getLetter());
        assertEquals('r', board.getCell(8, 1).getPiece().getLetter());
        assertEquals('i', board.getCell(8, 2).getPiece().getLetter());
        assertEquals('m', board.getCell(8, 3).getPiece().getLetter());
        assertEquals('e', board.getCell(8, 4).getPiece().getLetter());

        assertEquals(17, score); //(4*3+3)*1+2 = 17

        word = new Word(board.getCell(8, 1), Direction.HORIZONTAL, requiemPieces);
        score = board.playWord(word);
        assertEquals('r', board.getCell(8, 1).getPiece().getLetter());
        assertEquals('e', board.getCell(9, 1).getPiece().getLetter());
        assertEquals('q', board.getCell(10, 1).getPiece().getLetter());
        assertEquals('u', board.getCell(11, 1).getPiece().getLetter());
        assertEquals('i', board.getCell(12, 1).getPiece().getLetter());
        assertEquals('e', board.getCell(13, 1).getPiece().getLetter());
        assertEquals('m', board.getCell(14, 1).getPiece().getLetter());

        assertEquals(70, score); //(6*4+4+4)*2+3 = 67

        word = new Word(board.getCell(0, 0), Direction.HORIZONTAL, paramedicPieces);
        score = board.playWord(word);
        assertEquals('p', board.getCell(0, 0).getPiece().getLetter());
        assertEquals('a', board.getCell(1, 0).getPiece().getLetter());
        assertEquals('r', board.getCell(2, 0).getPiece().getLetter());
        assertEquals('a', board.getCell(3, 0).getPiece().getLetter());
        assertEquals('m', board.getCell(4, 0).getPiece().getLetter());
        assertEquals('e', board.getCell(5, 0).getPiece().getLetter());
        assertEquals('d', board.getCell(6, 0).getPiece().getLetter());
        assertEquals('i', board.getCell(7, 0).getPiece().getLetter());
        assertEquals('c', board.getCell(8, 0).getPiece().getLetter());

        assertEquals(432, score); //(8*5+5)*9+4 = 409

        word = new Word(board.getCell(14, 10), Direction.VERTICAL, primePieces);
        score = board.playWord(word);
        assertEquals('p', board.getCell(14, 10).getPiece().getLetter());
        assertEquals('r', board.getCell(14, 11).getPiece().getLetter());
        assertEquals('i', board.getCell(14, 12).getPiece().getLetter());
        assertEquals('m', board.getCell(14, 13).getPiece().getLetter());
        assertEquals('e', board.getCell(14, 14).getPiece().getLetter());

        assertEquals(41, score); //(4*7+7)*1+6 = 41

        word = new Word(board.getCell(4, 10), Direction.HORIZONTAL, matePieces);
        score = board.playWord(word);
        assertEquals('m', board.getCell(4, 10).getPiece().getLetter());
        assertEquals('a', board.getCell(5, 10).getPiece().getLetter());
        assertEquals('t', board.getCell(6, 10).getPiece().getLetter());
        assertEquals('e', board.getCell(7, 10).getPiece().getLetter());

        assertEquals(50, score); //(3*8+0)*2+1 = 49

        word = new Word(board.getCell(5, 8), Direction.HORIZONTAL, promotionsPieces);
        score = board.playWord(word);
        assertEquals('p', board.getCell(5, 8).getPiece().getLetter());
        assertEquals('r', board.getCell(6, 8).getPiece().getLetter());
        assertEquals('o', board.getCell(7, 8).getPiece().getLetter());
        assertEquals('m', board.getCell(8, 8).getPiece().getLetter());
        assertEquals('o', board.getCell(9, 8).getPiece().getLetter());
        assertEquals('t', board.getCell(10, 8).getPiece().getLetter());
        assertEquals('i', board.getCell(11, 8).getPiece().getLetter());
        assertEquals('o', board.getCell(12, 8).getPiece().getLetter());
        assertEquals('n', board.getCell(13, 8).getPiece().getLetter());
        assertEquals('s', board.getCell(14, 8).getPiece().getLetter());


        assertEquals(109, score); //(9*9+9*3)*1+1 = 109
    }

    @Test
    public void directionBug() throws Exception {
        Board board = new Board();
        Word word = new Word(board.getCell(7, 7), Direction.HORIZONTAL, oPieces);
        int score = board.playWord(word);
        assertEquals('o', board.getCell(7, 7).getPiece().getLetter());

        assertEquals(2, score); //(1*1+1)*1 = 2

        word = new Word(board.getCell(6, 7), Direction.HORIZONTAL, iPieces);
        score = board.playWord(word);
        assertEquals('i', board.getCell(6, 7).getPiece().getLetter());

        assertEquals(2, score); //(1*1+0)*1+1 = 2

    }
}