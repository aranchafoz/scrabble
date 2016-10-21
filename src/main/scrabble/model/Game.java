package main.scrabble.model;

import javax.print.attribute.standard.DateTimeAtCompleted;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Random;

import main.scrabble.exceptions.*;


public class Game {
    private int round;
    private int turn;

    private ArrayList<Player> players;
    private Board board;
    private Bag bag;

    public Game(ArrayList<Player> players) throws WrongCoordinateException {
        this.players = players;

        board = new Board();
        bag = new Bag();

        round = 0;
        turn = (new Random()).nextInt(players.size());
        // Borrar después, solo pruebas
    }

    public void fillPlayerRack() throws NoPiecesInBagException {
        players.get(turn).fillRack(bag);
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
    public Piece playTurn(Piece piece) throws NoPiecesInBagException {
        return bag.changePiece(piece);
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Player getBestPlayer() {
        Player bestPlayer = players.get(0);

        for (Player player: players) {
            if (player.getPunctuation() > bestPlayer.getPunctuation())
                bestPlayer = player;
        }

        return bestPlayer;
    }
}