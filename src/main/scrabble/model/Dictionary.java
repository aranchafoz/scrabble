package main.scrabble.model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

/**
 * Created by enrique on 27/09/16.
 */

public class Dictionary {
    private final String FILENAME = "eng_dic";
    private ArrayList<String> words;

    public Dictionary() {
        words = new ArrayList<>();
        try {
            Scanner input = new Scanner(new FileReader("assets/" + FILENAME));
            while (input.hasNext())
                words.add(input.next());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean existWord(String word) {
        return words.contains(word);
    }

    public ArrayList<String> getWords(int size) {
        ArrayList<String> words = new ArrayList<>();
        for (String w : this.words)
            if (w.length() == size)
                words.add(w);
        return words;
    }

    public boolean wordFits(String letters, String word) {
        ArrayList<Character> l = new ArrayList<Character>();
        ArrayList<Character> w = new ArrayList<Character>();

        for (int i = 0; i < letters.length(); i++)
            l.add(letters.charAt(i));
        for (int i = 0; i < word.length(); i++)
            w.add(word.charAt(i));

        return wordFits(l, w);
    }

    private boolean wordFits(ArrayList<Character> letters, ArrayList<Character> word) {
        if (letters.size() != word.size())
            return false;
        ArrayList<Character> l = new ArrayList<>(letters);
        for (char c : word) {
            if (l.contains(c))
                l.remove(l.indexOf(c));
            else
                return false;
        }
        return l.isEmpty(); // If it's not empty (error) it returns false
    }

    public ArrayList<String> getWordsWith(ArrayList<Character> letters) {
        ArrayList<String> words = getWords(letters.size());
        ArrayList<String> validWords = new ArrayList<>();
        for (String w : words) {
            ArrayList<Character> word = new ArrayList<>();
            for (int i = 0; i < w.length(); i++)
                word.add(w.charAt(i));
            if (wordFits(letters, word))
                validWords.add(w);
        }
        return validWords;
    }
}
