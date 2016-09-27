package main.scrabble.model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by enrique on 27/09/16.
 */
public class Dictionary {
    private final String FILENAME = "english_dic";
    private ArrayList<String> words;

    public Dictionary() {
        words = new ArrayList<>();
        try {
            Scanner input = new Scanner(new FileReader(FILENAME));
            while (input.hasNext())
                words.add(input.next());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean exist(String word) {
        return words.contains(word);
    }
}
