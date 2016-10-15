package main.scrabble.controller;

import main.scrabble.exceptions.NoPiecesInBagException;
import main.scrabble.exceptions.OccupiedCellException;
import main.scrabble.exceptions.WrongCoordinateException;
import main.scrabble.model.Board;
import main.scrabble.model.Game;
import main.scrabble.model.Player;
import main.scrabble.view.*;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by enrique on 26/09/16.
 */
public class GameController extends JFrame {
    private Game game;
    private Player currentPlayer;

    private final int FPS = 60;

    private final String WINDOW_TITLE = "Scrabble";
    private final int WINDOW_HEIGHT = 1000;
    private final int WINDOW_WIDTH = 1800;

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
    private UIButton change;
    private UIButton cancel;

    private UIButton test;

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

        // Random Players for UI pattern design
        Player p1 = new Player("WikiIvorra","assets/ivorra_player.png");
        Player p2 = new Player("#PeloGuay","assets/ivorra_player.png");
        Player p3 = new Player("Aranchunfli","assets/ivorra_player.png");
        Player p4 = new Player("Photohannes","assets/ivorra_player.png");
        ArrayList<Player> player = new ArrayList<Player>();
        player.add(p1);    player.add(p2);    player.add(p3);    player.add(p4);

        UIPlayer uip1 = new UIPlayer(1000,75,150,p1);
        UIPlayer uip2 = new UIPlayer(1400,75,150,p2);
        UIPlayer uip3 = new UIPlayer(1000,225 + 50,150,p3);
        UIPlayer uip4 = new UIPlayer(1400,225 + 50,150,p4);

        players = new ArrayList<UIPlayer>();
        players.add(uip1);    players.add(uip2);    players.add(uip3);    players.add(uip4);

        game = new Game(player);

        // Background & Board
        background = new UIBackground(WINDOW_WIDTH, WINDOW_HEIGHT);
        Board b = new Board();
        board = new UIBoard(50, 75, 883, b); //game.getBoard); // 675 for 15 cells of 45 + 28 for 14 lines of 2

        // image test
        test = new UIButton(20, 20, "test");

        playedPieces = new ArrayList<>();
        tempPieces = new ArrayList<>();
    }

    public static void main(String[] args) throws WrongCoordinateException {
        GameController g = new GameController();
        g.run();
    }

    private void run() {
        newTurn();
        while (isRunning) {
            long time = System.currentTimeMillis();

            try {
                update();
            } catch (OccupiedCellException e) {
                e.printStackTrace();
            } catch (NoPiecesInBagException e) {
                e.printStackTrace();
            }
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

    private void update() throws OccupiedCellException, NoPiecesInBagException {

        Point mouseClick = inputHandler.getInputs();
        if (mouseClick != null) {


            if (rack.receiveInput(mouseClick)){
                selectedPiece = rack.getSelectedPiece(mouseClick);
            } else {
                if (board.receiveInput(mouseClick)) {

                    /**
                     * TODO
                     * if (selectedPiece)
                     *  take the piece from the rake and put it in the
                     */

                } else if (play.isPressed(mouseClick)) {
                    /**
                     * TODO
                     * - Take temp pieces and check if can be played
                     * - Try to insert in board
                     * - Take them out from the player
                     */

                    game.fillPlayerRack();
                    newTurn();
                } else if (mix.isPressed(mouseClick)) {
                    currentPlayer.mixPieces();
                    rack.setPieces(currentPlayer.getPieces());
                } else if (change.isPressed(mouseClick)) {
                    currentPlayer.addPiece(game.playTurn(selectedPiece.getPiece()));
                    newTurn();
                } else if (cancel.isPressed(mouseClick)) {
                    revokeTempPieces();
                }

                selectedPiece = null; // We nullify this if anything but piece is clicked
            }
        }
    }

    private void draw() {
        Graphics g = getGraphics();
        Graphics bg = buffer.getGraphics();

        // Draw the background
        background.draw(bg, this);

        board.draw(bg, this);

        //rack.draw(bg, this);

        for (UIPiece piece : playedPieces)
            piece.draw(bg, this);
        for (UIPiece piece : tempPieces)
            piece.draw(bg, this);
        for (UIPlayer player : players) {
            player.draw(bg, this);
        }

        // Draw best player
        Player bestp = game.getBestPlayer();
        for (UIPlayer player : players) {
            if (player.getPlayer() == bestp) {
                player.drawCrown(bg,this);

            }
        }

        g.drawImage(buffer, 0, 0, this);
    }

    public void revokeTempPieces() {

    }

    public void newTurn() {
//       currentPlayer = game.nextTurn();
//        rack.setPieces(currentPlayer.getPieces());
    }
}
