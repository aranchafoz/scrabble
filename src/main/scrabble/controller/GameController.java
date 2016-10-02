package main.scrabble.controller;

import main.scrabble.exceptions.WrongCoordinateException;
import main.scrabble.model.Board;
import main.scrabble.model.Game;
import main.scrabble.model.Piece;
import main.scrabble.view.*;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by enrique on 26/09/16.
 */
public class GameController extends JFrame {
    private final int FPS = 60;
    private final String WINDOW_TITLE = "Scrabble";
    private final int WINDOW_HEIGHT = 1000;
    private final int WINDOW_WIDTH = 1000;
    private Game game;
    private boolean isRunning = true;

    private BufferedImage buffer;

    private GameState state;

    private InputHandler inputHandler;

    private UIPiece selectedPiece;

    private UIBackground background;
    private ArrayList<UIPiece> playedPieces;
    private ArrayList<UIPiece> tempPieces; // Pieces in the board but not yet played
    private UIBoard board; // The board already includes all cells
    private UIRack rack; // The rack already includes the player's pieces
    private ArrayList<UIPlayer> players;

    private UIButton play;
    private UIButton mix;

    public GameController() throws WrongCoordinateException {
        setTitle(WINDOW_TITLE);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        buffer = new BufferedImage(WINDOW_WIDTH, WINDOW_HEIGHT, BufferedImage.TYPE_INT_RGB);

        inputHandler = new InputHandler();
        this.addMouseListener(inputHandler);

        state = GameState.menu;

        //game = new Game();
        background = new UIBackground(WINDOW_WIDTH, WINDOW_HEIGHT);
        Board b = new Board();
        board = new UIBoard(50, 100, 703, b); //game.getBoard); // 675 for 15 cells of 45 + 28 for 14 lines of 2


        playedPieces = new ArrayList<>();
        tempPieces = new ArrayList<>();
    }

    public static void main(String[] args) throws WrongCoordinateException {
        GameController g = new GameController();
        g.run();
    }

    private void run() {
        while (isRunning) {
            long time = System.currentTimeMillis();

            update();
            draw();

            time = (1000 / FPS) - (System.currentTimeMillis() - time);
            if (time > 0) {
                try {
                    Thread.sleep(time);
                } catch (InterruptedException e) {

                }
            }
        }
        setVisible(false);
    }

    private void update() {
        Point mouseClick = inputHandler.getInputs();
        if (mouseClick != null) {

            //rack.receiveInput();
            //board.receiveInput(mouseClick);

            //x = mouseClick.getX();
            //y = mouseClick.getY();


        }
    }

    private void draw() {
        Graphics g = getGraphics();
        Graphics bg = buffer.getGraphics();

        // Draw the background
        /*
        bg.setColor(Color.RED);
        bg.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

        bg.setColor(Color.BLACK);
        bg.fillOval((int)x - 10, (int)y - 10, 20, 20);
        */
        background.draw(bg, this);
        board.draw(bg, this);
//        rack.draw(bg, this);
/*
        for (UIPiece piece : playedPieces)
            piece.draw(bg, this);
        for (UIPiece piece : tempPieces)
            piece.draw(bg, this);
        for (UIPlayer player : players) {
            player.draw(bg, this);
        }
*/
        g.drawImage(buffer, 0, 0, this);
    }
}
