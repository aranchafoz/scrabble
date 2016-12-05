package main.scrabble.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

/**
 * Created by enrique on 27/09/16.
 */

public class Dictionary {
    private static final String FILENAME = "eng_dic.txt";
    private static ArrayList<String> words;

    private static Dictionary dic = new Dictionary();

    private Dictionary() {
        words = new ArrayList<>();
        try {
            Scanner input = new Scanner(getClass().getResourceAsStream("/eng_dic.txt"));
            while (input.hasNext())
                words.add(input.next());
        } catch (Exception e) {
            System.exit(90);
        }
    }

    public static boolean existWord(String word) {
        if (word.contains("_")) {
            boolean exists = false;

            for (char i = 'a'; i <= 'z'; i++) {
                if (existWord(word.replaceFirst("_", String.valueOf(i))))
                    exists = true;
            }
            return exists;
        }
        return words.contains(word.toLowerCase());
    }

    public static ArrayList<String> getWords(int size) {
        ArrayList<String> wrds = new ArrayList<>();
        for (String w : dic.words)
            if (w.length() == size)
                wrds.add(w);
        return wrds;
    }

    public static boolean wordFits(String letters, String word) {
        ArrayList<Character> l = new ArrayList<Character>();
        ArrayList<Character> w = new ArrayList<Character>();

        for (int i = 0; i < letters.length(); i++)
            l.add(letters.charAt(i));
        for (int i = 0; i < word.length(); i++)
            w.add(word.charAt(i));

        return wordFits(l, w);
    }

    private static boolean wordFits(ArrayList<Character> letters, ArrayList<Character> word) {
        ArrayList<Character> l = new ArrayList<>(letters);
        for (char c : word) {
            if (l.contains(c))
                l.remove(l.indexOf(c));
            else
                return false;
        }
        return true;
    }

    public static ArrayList<Word> findWords(ArrayList<Cell> cells, ArrayList<Piece> pieces, Direction dir) {
        ArrayList<Word> words = new ArrayList<>();
        String ins = "";

        for (Cell c : cells)
            if (c.getPiece() != null)
                ins += c.getPiece().getLetter();
            else
                ins += "_";

        ArrayList<String> insertedWords = new ArrayList<>(Arrays.asList(ins.split("_")));
        if (insertedWords.size() == 0)
            return words;

        ArrayList<Character> p = new ArrayList<>();

        for (Piece piece : pieces)
            p.add(piece.getLetter());

        for (String inserted : insertedWords) {
            if (!inserted.isEmpty())
                for (String str : getWordsContaining(p, inserted)) {
                    for (int i = 0; i < cells.size(); i++) {
                        Cell c = cells.get(i);
                        if (c.getPiece() != null) {
                            if (c.getPiece().getLetter() == inserted.charAt(0)) {
                                int index = i - str.indexOf(inserted);
                                if (index >= 0) {
                                    Cell origin = cells.get(index);
                                    String letters = str.replace(inserted, "");
                                    ArrayList<Piece> wordPieces = new ArrayList<>();
                                    for (Piece piece : pieces) {
                                        if (letters.contains("" + piece.getLetter())) {
                                            wordPieces.add(piece);
                                            letters = letters.replace("" + piece.getLetter(), "");
                                        }
                                    }
                                    words.add(new Word(origin, dir, wordPieces));
                                }
                            }
                        }
                    }
                }
        }

        return words;
    }

    public static ArrayList<String> getWordsWith(ArrayList<Character> letters) {
        ArrayList<String> validWords = new ArrayList<>();
        for (String w : dic.words) {
            ArrayList<Character> word = new ArrayList<>();
            for (int i = 0; i < w.length(); i++)
                word.add(w.charAt(i));
            if (wordFits(letters, word))
                validWords.add(w);
        }
        return validWords;
    }


    public static ArrayList<Word> getWordsForMiddle(ArrayList<Piece> pieces) {
        ArrayList<Word> words = new ArrayList<>();

        ArrayList<Character> letters = new ArrayList<>();
        for (Piece p : pieces)
            letters.add(p.getLetter());

        for (String str : getWordsWith(letters)) {
            ArrayList<Piece> p = new ArrayList<>();
            ArrayList<Piece> wordPieces = new ArrayList<>();
            p.addAll(pieces);

            for (char c : str.toCharArray()) {
                for (Piece piece : p) {
                    //if (piece.getLetter() == c.)
                }
            }
        }

        return words;
    }

    public static ArrayList<String> getWordsContaining(ArrayList<Character> letters, String substring) {
        ArrayList<String> words = new ArrayList<>();

        for (String word : dic.words) {
            String w = word;
            if (w.contains(substring.toLowerCase()) && (word.length() != substring.length())) {
                w = w.replace(substring.toLowerCase(), "");
                ArrayList<Character> l = new ArrayList<>(letters);
                boolean insert = true;
                for (int i = 0; i < w.length() && insert; i++) {
                    if (l.contains(w.charAt(i)))
                        l.remove((Object) w.charAt(i));
                    else
                        insert = false;
                }
                if (insert) words.add(word);
            }
        }

        return words;
    }

}
