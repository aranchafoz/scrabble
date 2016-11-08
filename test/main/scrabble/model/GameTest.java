package main.scrabble.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by enrique on 8/11/16.
 */
public class GameTest {
    ArrayList<Player> players;

    @Before
    public void setUp() {
        players = new ArrayList<>();
        players.add(new Player("Player 1", "path"));
        players.add(new Player("Player 2", "path"));
        players.add(new Player("Player 3", "path"));
        players.add(new Player("Player 4", "path"));
    }

    @Test
    public void fillPlayerRack() throws Exception {
        Game game = new Game(players);
        game.fillPlayerRack();

        int turn = game.getTurn();
        assertTrue(game.getPlayers().get(turn).rackFilled());
    }

    @Test
    public void nextTurn() throws Exception {
        Game game = new Game(players);
        for (int i = 0; i < 4; i++) {
            int previousTurn = game.getTurn();
            game.nextTurn();
            int currentTurn = game.getTurn();
            if (previousTurn >= 3)
                assertEquals(0, currentTurn);
            else
                assertEquals(previousTurn + 1, currentTurn);
        }
    }

    @Test
    public void getBestPlayer() throws Exception {
        Game game = new Game(players);
        // Scores: 0 0 0 0

        game.getPlayers().get(3).increaseScore(10);
        // Scores: 0 0 0 10

        assertEquals(game.getPlayers().get(3), game.getBestPlayer());

        game.getPlayers().get(2).increaseScore(15);
        // Scores: 0 0 15 10

        assertEquals(game.getPlayers().get(2), game.getBestPlayer());
    }
}