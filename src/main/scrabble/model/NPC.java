package main.scrabble.model;

import main.scrabble.exceptions.AloneWordException;
import main.scrabble.exceptions.NoPieceInCenterException;
import main.scrabble.exceptions.OutOfBoundsException;
import main.scrabble.exceptions.WrongWordException;

import java.util.ArrayList;

/**
 * Created by enrique on 14/11/16.
 */

public class NPC extends Player {
    public NPC(String name, String avatarPath) {
        this.name = name;
        this.avatarPath = avatarPath;
        pieces = new ArrayList<>();
        score = 0;
    }

    public Word playTurn(Board board) {
        ArrayList<Word> playableWords = new ArrayList<>();

        if (board.getCell(7, 7).getPiece() == null) {
            playableWords.addAll(Dictionary.getWordsForMiddle(pieces));
        } else {
            for (int i = 0; i < 15; i++) {
                ArrayList<Cell> horizontal = new ArrayList<>();
                ArrayList<Cell> vertical = new ArrayList<>();

                for (int j = 0; j < 15; j++) {
                    horizontal.add(board.getCell(i, j));
                    vertical.add(board.getCell(j, i));
                }

                playableWords.addAll(Dictionary.findWords(horizontal, pieces, Direction.HORIZONTAL));
                playableWords.addAll(Dictionary.findWords(vertical, pieces, Direction.HORIZONTAL));
            }
        }

        Word bestWord = null;
        int bestScore = 0;
        for (Word word : playableWords) {
            try {
                int score = board.checkInsertion(word);
                if (score > bestScore) {
                    bestScore = score;
                    bestWord = word;
                }
            } catch (AloneWordException e) {

            } catch (WrongWordException e) {

            } catch (OutOfBoundsException e) {

            } catch (NoPieceInCenterException e) {

            }
        }

        return bestWord;
    }
}
