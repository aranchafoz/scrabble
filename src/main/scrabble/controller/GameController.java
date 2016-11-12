package main.scrabble.controller;

import main.scrabble.exceptions.NoPiecesInBagException;
import main.scrabble.exceptions.OccupiedCellException;
import main.scrabble.exceptions.WrongCoordinateException;
import main.scrabble.model.Board;
import main.scrabble.model.Game;
import main.scrabble.model.GameState;
import main.scrabble.model.Player;
import main.scrabble.view.*;

import javax.swing.*;
import javax.swing.plaf.ButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by enrique on 26/09/16.
 */
public class GameController extends JFrame implements ActionListener {
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

    private UIPiece selectedPiece;

    private UIBackground background;
    private ArrayList<UIPiece> playedPieces;
    private ArrayList<UIPiece> tempPieces; // Pieces in the board but not yet played
    private UIBoard board; // The board already includes all cells
    private UIRack rack; // The rack already includes the player's pieces
    private ArrayList<UIPlayer> players;

    private JButton playButton;

    private UIButton pass;
    private UIButton mix;
    private UIButton exchange;
    private UIButton undo;

    private TextField player1textField;
    private TextField player2textField;
    private TextField player3textField;
    private TextField player4textField;


    public GameController() throws WrongCoordinateException {
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

        buffer = new BufferedImage(WINDOW_WIDTH, WINDOW_HEIGHT, BufferedImage.TYPE_INT_RGB);

        inputHandler = new InputHandler();
        this.addMouseListener(inputHandler);

        // Initialize game status
        state = GameState.START_MENU;

        // Background & Board
        background = new UIBackground(WINDOW_WIDTH, WINDOW_HEIGHT);
        Board b = new Board();
        board = new UIBoard(50, 75, 883, b); //game.getBoard); // 675 for 15 cells of 45 + 28 for 14 lines of 2

        // Rack
        rack = new UIRack(1000 + 20, 500, 700, 126);//170);

        game = new Game();

        // Buttons
        //play =  new UIButton(155, 500);
        pass = new UIButton(1000 + 240, 750, "Pass","assets/buttons/Play-60.png");
        mix = new UIButton(1000 + 170, 750, "Shuffle","assets/buttons/Shuffle-60.png");
        exchange = new UIButton(1000 + 100, 750, "Exchange","assets/buttons/Replace-60.png");
        undo = new UIButton(1000 + 30, 750, "Exchange","assets/buttons/Undo-60.png");

        // Text fields
        player1textField = new TextField();
        player1textField.setBounds(50,150,400,35);

        player2textField = new TextField();
        player2textField.setBounds(50,240,400,35);

        player3textField = new TextField();
        player3textField.setBounds(50,330,400,35);

        player4textField = new TextField();
        player4textField.setBounds(50,420,400,35);


        playedPieces = new ArrayList<>();
        tempPieces = new ArrayList<>();
    }

    public static void main(String[] args) throws WrongCoordinateException {
        GameController g = new GameController();
        g.run();
    }

    private void run() {
        switch (state){
            case START_MENU:
                drawInitial();
                //updateInitial() ??????????????????????????????????????????????????????????????????????????????????????????
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


            if (rack.receiveInput(mouseClick)){
                selectedPiece = rack.getSelectedPiece(mouseClick);
            } else {
                if (board.receiveInput(mouseClick)) {

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

                    game.fillPlayerRack();
                    newTurn();
                } else if (mix.isPressed(mouseClick)) {
                    currentPlayer.mixPieces();
                    rack.setPieces(currentPlayer.getPieces());
                } else if (exchange.isPressed(mouseClick)) {
                    currentPlayer.addPiece(game.playTurn(selectedPiece.getPiece()));
                    newTurn();
                } else if (undo.isPressed(mouseClick)) {
                    revokeTempPieces();
                }

                selectedPiece = null; // We nullify this if anything but piece is clicked
            }
        }
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
        background.draw(bg, this);

        board.draw(bg, this);

        rack.draw(bg, this);

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

        pass.draw(bg,this);
        mix.draw(bg,this);
        exchange.draw(bg,this);
        undo.draw(bg,this);



        g2.drawImage(buffer, 0, 0, this);

    }

    private void drawInitial() {
        setSize(500, 600);

        Graphics g = getGraphics();
        Graphics2D g2= (Graphics2D) g;
        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setRenderingHints(rh);

        Graphics2D bg = (Graphics2D) buffer.getGraphics();
        bg.setRenderingHints(rh);

        // Draw the background
        background.draw(bg, this);

        // Info
        bg.setColor(Color.BLACK);
        bg.setFont(new Font("Monaco",Font.BOLD, 25));
        bg.drawString("Input players", 150, 70);

        // Players
        for(int i = 0; i < 4; i++){
            bg.setColor(Color.darkGray);
            bg.setFont(new Font("Monaco",Font.ITALIC, 20));
            bg.drawString("Player " + (i + 1), 50, 150 + (i * 90));
        }

        // Input Player names
        add(player1textField);
        add(player2textField);
        add(player3textField);
        add(player4textField);


        // Play Button
        //play.draw(bg,this);
        playButton = new JButton("START");
        playButton.setBounds(155,500,200,50);

        add(playButton);

        playButton.addActionListener(this);

        g2.drawImage(buffer, 0, 0, this);

    }

    public void revokeTempPieces() {

    }

    public void newTurn() {
//       currentPlayer = game.nextTurn();
//        rack.setPieces(currentPlayer.getPieces());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //System.out.printf(e.getSource().toString());

        if (e.getSource() == playButton) {
            for (int i = 0; i < 4; i++) {
                // Random Players for UI pattern design
                Player p1 = new Player(player1textField.getText(),"assets/ivorra_player.png");
                Player p2 = new Player(player2textField.getText(),"assets/enrique_avatar.png");
                Player p3 = new Player(player3textField.getText(),"assets/arancha_avatar.png");
                Player p4 = new Player(player4textField.getText(),"assets/ivorra_player.png");
                ArrayList<Player> player = new ArrayList<Player>();
                player.add(p1);    player.add(p2);    player.add(p3);    player.add(p4);

                UIPlayer uip1 = new UIPlayer(1000,75,150,p1);
                UIPlayer uip2 = new UIPlayer(1400,75,150,p2);
                UIPlayer uip3 = new UIPlayer(1000,225 + 50,150,p3);
                UIPlayer uip4 = new UIPlayer(1400,225 + 50,150,p4);

                players = new ArrayList<UIPlayer>();
                players.add(uip1);    players.add(uip2);    players.add(uip3);    players.add(uip4);

                game.setPlayers(player);
            }

            // Delete all components
            removeAll();
            // Set next GameState
            state = GameState.IN_GAME;

            run();
        }

    }
}
