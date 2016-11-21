package main.scrabble.model;

import main.scrabble.exceptions.WrongCoordinateException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by enrique on 27/09/16.
 */
public class DictionaryTest {

    @Test
    public void existWord() throws Exception {
        assertTrue(Dictionary.existWord("house"));
        assertTrue(Dictionary.existWord("coffee"));
        assertFalse(Dictionary.existWord("alsjkdnf"));
    }

    @Test
    public void getWordsWith() throws Exception {
        ArrayList<Character> chars = new ArrayList<>();
        chars.add('h');
        chars.add('e');
        chars.add('l');
        chars.add('l');
        chars.add('o');
        ArrayList<String> words = Dictionary.getWordsWith(chars);

        assertTrue(words.contains("hello"));
        assertTrue(words.contains("hell"));
        assertFalse(words.contains("alskdjf"));

        chars = new ArrayList<>();
        chars.add('f');
        chars.add('i');
        chars.add('n');
        chars.add('d');
        chars.add('e');
        chars.add('r');
        words = Dictionary.getWordsWith(chars);

        assertTrue(words.contains("friend"));
        assertTrue(words.contains("finder"));
        assertTrue(words.contains("fired"));
    }

    @Test
    public void existWithWildcard() throws Exception {
        assertTrue(Dictionary.existWord("ho_se"));
        assertTrue(Dictionary.existWord("h__se"));
        assertFalse(Dictionary.existWord("_xya_"));
    }

    @Test
    public void findWords() throws Exception {
        ArrayList<Cell> cells = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            cells.add(new Cell(0, i, CellType.PLAIN));
        }
        cells.get(5).setPiece(new Piece('M', 3));
        cells.get(6).setPiece(new Piece('E', 3));

        ArrayList<Piece> pieces = new ArrayList<>();
        Piece h = new Piece('H', 3);
        Piece o = new Piece('O', 3);

        pieces.add(h);
        pieces.add(o);

        Word word = new Word(cells.get(3), Direction.HORIZONTAL, pieces);

        boolean correct = false;

        for (Word w : Dictionary.findWords(cells, pieces, Direction.HORIZONTAL)) {
            if (w.toString().equals(word.toString()))
                correct = true;
        }

        assertTrue(correct);
    }
}