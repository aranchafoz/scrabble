package main.scrabble.model;

import javax.print.attribute.standard.DateTimeAtCompleted;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Random;

public class Game {
    private int round;
    private int turn;

    private ArrayList<Player> players;
    private Board board;
    //private Bag bag;

    public Game(ArrayList<Player> players) {
        this.players = players;

        board = new Board();
        //bag = new Bag();

        round = 0;
        turn = (new Random()).nextInt(players.size());
    }

    public Player nextTurn() {
        Player player = players.get(turn++);
        if (turn >= players.size()) {
            turn = 0;
        }
        return player;
    }
/*
    public int playTurn(Word word) {
        return board.insertWord(word);
    }
*/
    public Piece playTurn(Piece piece) {

        return new Piece();
    }
/*
    public ArrayList<Players> getPlayers() {
        return players;
    }*/
}