package main.scrabble.controller;

import com.sun.istack.internal.Nullable;
import main.scrabble.exceptions.*;
import main.scrabble.model.*;
import main.scrabble.view.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.ButtonUI;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by enrique on 26/09/16.
 */

public class GameController extends JFrame /* implements ActionListener */ {
    private Game game;
    private GameState state;
    private Player currentPlayer;

    private final int FPS = 60;

    private final String WINDOW_TITLE = "Scrabble";

    private final int WINDOW_HEIGHT = 1000;
    private final int WINDOW_WIDTH = 1800;

    private boolean isRunning = true;

    private BufferedImage buffer;

    private InputHandler inputHandler;

    private UIPiece selectedPiece = null;

    private UIBackground background;

    private Map<Integer, UIPiece> tempPieces;
    //private ArrayList<UIPiece> tempPieces; // Pieces in the board but not yet played
    private UIBoard board; // The board already includes all cells
    private UIRack rack; // The rack already includes the player's pieces
    private ArrayList<UIPlayer> players;

    private UIButton pass;
    private UIButton mix;
    private UIButton exchange;
    private UIButton undo;

    public GameController(ArrayList<Player> players) throws WrongCoordinateException {
        setTitle(WINDOW_TITLE);

        // Responsive screensize
        //Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        //int width = (int) screenSize.getWidth();
        //int height = (int) screenSize.getHeight();

        //setSize(width, height - 20);
        setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        setResizable(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        // Window Close
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        game = new Game(players);

        buffer = new BufferedImage(WINDOW_WIDTH, WINDOW_HEIGHT, BufferedImage.TYPE_INT_RGB);

        inputHandler = new InputHandler();
        this.addMouseListener(inputHandler);

        // Initialize game status
        state = GameState.IN_GAME;

        // Background & Board
        background = new UIBackground(WINDOW_WIDTH, WINDOW_HEIGHT);
        board = new UIBoard(50, 75, 883, game.getBoard()); //game.getBoard); // 675 for 15 cells of 45 + 28 for 14 lines of 2

        // Rack
        rack = new UIRack(1000 + 20, 500, 700, 126);//170);

        UIPlayer uip1 = new UIPlayer(1000, 75, 150, players.get(0));
        UIPlayer uip2 = new UIPlayer(1400, 75, 150, players.get(1));
        UIPlayer uip3 = new UIPlayer(1000, 225 + 50, 150, players.get(2));
        UIPlayer uip4 = new UIPlayer(1400, 225 + 50, 150, players.get(3));

        this.players = new ArrayList<UIPlayer>();
        this.players.add(uip1);
        this.players.add(uip2);
        this.players.add(uip3);
        this.players.add(uip4);

        currentPlayer = game.getCurrentPlayer();

        // Buttons
        pass = new UIButton(1000 + 530 + 40, 750, "Pass","assets/buttons/Play-90.png");
        mix = new UIButton(1000 + 370 + 40, 750, "Shuffle","assets/buttons/Shuffle-90.png");
        exchange = new UIButton(1000 + 210 + 40, 750, "Exchange","assets/buttons/Replace-90.png");
        undo = new UIButton(1000 + 50 + 40, 750, "Undo","assets/buttons/Undo-90.png");

        //playedPieces = new ArrayList<>();
        tempPieces = new TreeMap<>();
    }

    public static void main(String[] args) throws WrongCoordinateException {
        Player p1 = new Player("asdf", "assets/avatar/ivorra_player.png");
        Player p2 = new Player("asdf", "assets/avatar/enrique_avatar.png");
        Player p3 = new Player("asdf", "assets/avatar/arancha_avatar.png");
        Player p4 = new Player("asdf", "assets/avatar/jon_nieve.jpg");

        ArrayList<Player> p = new ArrayList<>();
        p.add(p1);
        p.add(p2);
        p.add(p3);
        p.add(p4);

        GameController g = new GameController(p);
        g.run();
    }

    public void run() {
        switch (state){
            case START_MENU:
                //drawInitial();
                break;
            case IN_GAME:
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
                break;
            case FINAL:
                break;
        }
    }

    private void update() throws OccupiedCellException, NoPiecesInBagException {

        Point mouseClick = inputHandler.getInputs();
        if (mouseClick != null) {

            if (rack.receiveInput(mouseClick)) {
                selectedPiece = rack.getSelectedPiece(mouseClick);
                if (selectedPiece != null)
                    System.out.println("Selected piece: " + selectedPiece.getPiece().getLetter());
            } else {
                if (board.receiveInput(mouseClick)) {
                    if (selectedPiece != null) {
                        Point cellCoordinates = board.getSelectedCell(mouseClick);

                        int linearCoordinates = cellCoordinates.x + 15 * cellCoordinates.y;

                        if (!tempPieces.containsKey(linearCoordinates)) {
                            rack.removePiece(selectedPiece);

                            // gets the new coordinates of the piece
                            int xPos = board.getCellX(cellCoordinates.x);
                            int yPos = board.getCellY(cellCoordinates.y);

                            tempPieces.put(linearCoordinates, new UIPiece(xPos, yPos, selectedPiece.getPiece(), false));
                            System.out.println("Piece " + selectedPiece.getPiece().getLetter() + " inserted on " + cellCoordinates.getX() + ", " + cellCoordinates.getY());
                            selectedPiece = null;
                        }
                    }

                    /**
                     * TODO
                     * if (selectedPiece)
                     *  take the piece from the rake and put it in the
                     */
                } else if (pass.isPressed(mouseClick)) {
                    /**
                     * TODO
                     * - Take temp pieces and check if can be played
                     * - Try to insert in board
                     * - Take them out from the player
                     */

                    if (!tempPieces.isEmpty()) {
                        Word word = getWord();
                        if (word != null) {
                            try {
                                int score = game.playTurn(word);
                                currentPlayer.increaseScore(score);
                                System.out.println(score + " points to " + currentPlayer.getName());

                                for (Piece p : word.getPieces())
                                    currentPlayer.removePiece(p);

                                game.fillPlayerRack();
                                newTurn();
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                        }
                    } else {
                        newTurn();
                    }
                } else if (mix.isPressed(mouseClick)) {
                    if (tempPieces.isEmpty()) {
                        currentPlayer.mixPieces();
                        rack.setPieces(currentPlayer.getPieces());
                    }
                } else if (exchange.isPressed(mouseClick)) {
                    if (selectedPiece != null) {
                        if (tempPieces.isEmpty()) {
                            System.out.println("-- Changing piece --");
                            System.out.print("Pieces before:");
                            for (Piece p : currentPlayer.getPieces())
                                System.out.print(" " + p.getLetter());
                            currentPlayer.addPiece(game.playTurn(selectedPiece.getPiece()));
                            currentPlayer.removePiece(selectedPiece.getPiece());
                            selectedPiece = null;
                            System.out.print("Pieces after:");
                            for (Piece p : currentPlayer.getPieces())
                                System.out.print(" " + p.getLetter());
                            rack.setPieces(currentPlayer.getPieces());
                            newTurn();
                        }
                    }
                } else if (undo.isPressed(mouseClick)) {
                    revokeTempPieces();
                }

                selectedPiece = null; // We nullify this if anything but piece is clicked
            }

        }
    }

    // returns true if pieces are aligned vertical or horizontally
    private Word getWord() {
        boolean h = true;
        boolean v = true;

        float prevX = -1;
        float prevY = -1;

        Cell origin = null;
        ArrayList<Piece> wordPieces = new ArrayList<>();

        for (Map.Entry<Integer, UIPiece> entry : tempPieces.entrySet()) {
            int x = entry.getKey() % 15;
            int y = entry.getKey() / 15;

            if (origin == null) {
                prevX = x;
                prevY = y;
                origin = game.getBoard().getCell(x, y);
            }

            wordPieces.add(entry.getValue().getPiece());

            if (prevX != x) v = false;
            if (prevY != y) h = false;
        }

        if (!(h || v)) return null;

        Direction dir;

        if (h) dir = Direction.HORIZONTAL;
        else dir = Direction.VERTICAL;

        Word word = new Word(origin, dir, wordPieces);

        System.out.println(word.toString());

        return word;
    }

    private void draw() {
        Graphics g = getGraphics();
        Graphics2D g2= (Graphics2D) g;
        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setRenderingHints(rh);

        Graphics2D bg = (Graphics2D) buffer.getGraphics();
        bg.setRenderingHints(rh);

        // Responsive screensize
        //Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        //int width = (int) screenSize.getWidth();
        //int height = (int) screenSize.getHeight();

        //setSize(width, height - 20);
        setSize(WINDOW_WIDTH,WINDOW_HEIGHT);

        // Draw the background
        background.draw(bg,this);

        board.draw(bg, this);

        // Draw current player
        UIPlayer currentPlayerUI = null;
        for (UIPlayer player : players) {
            if(player.getPlayer() == currentPlayer) {
                currentPlayerUI = player;
                ArrayList<Piece> plPieces = currentPlayer.getPieces();
                for (Piece p : plPieces){
                    //System.out.print(p.getLetter() + "\n");
                }
                //rack.setPieces(plPieces);
                break;
            }
        }

        float hsb[] = new float[3];
        float color[];

        color = Color.RGBtoHSB(190, 218, 212, hsb); // 192,128,64
        bg.setColor(Color.getHSBColor(color[0], color[1], color[2]));
        bg.fillRoundRect(currentPlayerUI.getX() - 20, currentPlayerUI.getY() - 20, currentPlayerUI.getW() + 240, currentPlayerUI.getH() + 40, 20, 20);

        rack.draw(bg, this);
/*
        for (UIPiece piece : playedPieces)
            piece.draw(bg, this);*/
        for (Map.Entry<Integer, UIPiece> entry : tempPieces.entrySet()) {
            UIPiece piece = entry.getValue();
            piece.draw(bg, this);
        }
        for (UIPlayer player : players) {
            player.draw(bg, this);
        }

        // Draw best player
        Player bestp = game.getBestPlayer();
        for (UIPlayer player : players) {
            if (player.getPlayer() == bestp) {
                player.drawCrown(bg, this);
            }
        }

        if (selectedPiece != null)
            bg.drawRect(
                    selectedPiece.getX() - 2,
                    selectedPiece.getY() - 2,
                    selectedPiece.getW() + 4,
                    selectedPiece.getH() + 4
            );

        pass.draw(bg, this);
        mix.draw(bg, this);
        exchange.draw(bg, this);
        undo.draw(bg, this);

        g2.drawImage(buffer, 0, 0, this);
    }

    public void revokeTempPieces() {
        tempPieces.clear();
        rack.setPieces(currentPlayer.getPieces());
    }

    public void newTurn() {
        currentPlayer = game.nextTurn();
        rack.setPieces(currentPlayer.getPieces());
        tempPieces.clear();
    }

}
