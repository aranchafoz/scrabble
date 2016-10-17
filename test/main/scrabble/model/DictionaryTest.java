package main.scrabble.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by enrique on 27/09/16.
 */
public class DictionaryTest {

    @Before
    public void initialize() {

    }

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
        assertFalse(words.contains("hell"));
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
        assertFalse(words.contains("fired"));
    }

}