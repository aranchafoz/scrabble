package main.scrabble.model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
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
        ArrayList<Character> l = new ArrayList<>();
        ArrayList<Character> w = new ArrayList<>();

        for (char c : word.toCharArray()) w.add(c);
        for (char c : letters.toCharArray()) l.add(c);

        return wordFits(letters, word);
    }

    private boolean wordFits(ArrayList<Character> letters, ArrayList<Character> word) {
        if (letters.size() != word.size())
            return false;
        letters = new ArrayList<>(letters);
        for (char c : word) {
            if (letters.contains(c))
                letters.remove(c);
            else
                return false;
        }
        return letters.isEmpty(); // If it's not empty (error) it returns false
    }

    public ArrayList<String> getWords(ArrayList<Character> letters) {
        ArrayList<String> words = getWords(letters.size());
        for (String w : words) {
            ArrayList<Character> word = new ArrayList<>(); // We convert to
            for (char c : w.toCharArray()) word.add(c);
            if (!wordFits(letters, word))
                words.remove(w);
        }
        return words;
    }
}
