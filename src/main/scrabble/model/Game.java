package main.scrabble.model;

import java.util.ArrayList;
import java.util.Random;

import main.scrabble.exceptions.*;


public class Game {
    private int round;
    private int turn;

    private ArrayList<Player> players;
    private Board board;
    private Bag bag;

    public Game() throws WrongCoordinateException {
        this.players = new ArrayList<>();

        board = new Board();
        bag = new Bag();

        round = 0;
        //turn = (new Random()).nextInt(players.size());
        // Borrar después, solo pruebas
    }

    public Game(ArrayList<Player> players) throws WrongCoordinateException {
        this.players = players;

        board = new Board();
        bag = new Bag();

        round = 0;
        turn = (new Random()).nextInt(players.size());

        for (Player p : players)
            try {
                p.fillRack(bag);
            } catch (NoPiecesInBagException e) {
                e.printStackTrace();
            }
    }

    public void fillPlayerRack() throws NoPiecesInBagException {
        players.get(turn).fillRack(bag);
    }

    public Player nextTurn() {
        turn++;
        if (turn >= players.size()) {
            turn = 0;
        }
        return players.get(turn);
    }

    public int playTurn(Word word) throws WrongWordException, OutOfBoundsException, OccupiedCellException, NoPieceInCenterException, AloneWordException {
        return board.playWord(word);
    }

    public Piece playTurn(Piece piece) throws NoPiecesInBagException {
        return bag.changePiece(piece);
    }

    public int getTurn() {
        return turn;
    }

    public Board getBoard() { return board; }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;

        turn = (new Random()).nextInt(players.size());
    }

    public Player getCurrentPlayer() {
        return players.get(turn);
    }

    public Player getBestPlayer() {
        Player bestPlayer = players.get(0);

        for (Player player: players) {
            if (player.getScore() > bestPlayer.getScore())
                bestPlayer = player;
        }

        return bestPlayer;
    }

    public Bag getBag() {
        return bag;
    }
}