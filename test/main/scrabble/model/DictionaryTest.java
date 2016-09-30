package main.scrabble.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by enrique on 27/09/16.
 */
public class DictionaryTest {
    private Dictionary dic;

    @Before
    public void initialize() {
        dic = new Dictionary();
    }

    @Test
    public void existWord() throws Exception {
        assertTrue(dic.existWord("house"));
        assertTrue(dic.existWord("coffee"));
        assertFalse(dic.existWord("alsjkdnf"));
    }

}