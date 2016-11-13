package main.scrabble.controller;

import main.scrabble.exceptions.NoPiecesInBagException;
import main.scrabble.exceptions.OccupiedCellException;
import main.scrabble.exceptions.WrongCoordinateException;
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
import java.util.ArrayList;

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
    private ArrayList<UIPiece> playedPieces;
    private ArrayList<UIPiece> tempPieces; // Pieces in the board but not yet played
    private UIBoard board; // The board already includes all cells
    private UIRack rack; // The rack already includes the player's pieces
    private ArrayList<UIPlayer> players;

//    private JButton playButton;
    //private JButton test;

    private UIButton pass;
    private UIButton mix;
    private UIButton exchange;
    private UIButton undo;
/*
    private TextField player1textField;
    private TextField player2textField;
    private TextField player3textField;
    private TextField player4textField;
*/
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


        buffer = new BufferedImage(WINDOW_WIDTH, WINDOW_HEIGHT, BufferedImage.TYPE_INT_RGB);

        inputHandler = new InputHandler();
        this.addMouseListener(inputHandler);

        // Initialize game status
        state = GameState.IN_GAME;

        // Background & Board
        background = new UIBackground(WINDOW_WIDTH, WINDOW_HEIGHT);
        Board b = new Board();
        board = new UIBoard(50, 75, 883, b); //game.getBoard); // 675 for 15 cells of 45 + 28 for 14 lines of 2

        // Rack
        rack = new UIRack(1000 + 20, 500, 700, 126);//170);

        game = new Game(players);

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
        /*
        playButton = new JButton("START");
        playButton.setBounds(155,500,200,100);
        */
        pass = new UIButton(1000 + 530 + 40, 750, "Pass","assets/buttons/Play-90.png");
        mix = new UIButton(1000 + 370 + 40, 750, "Shuffle","assets/buttons/Shuffle-90.png");
        exchange = new UIButton(1000 + 210 + 40, 750, "Exchange","assets/buttons/Replace-90.png");
        undo = new UIButton(1000 + 50 + 40, 750, "Undo","assets/buttons/Undo-90.png");

        // Text fields
        /*
        player1textField = new TextField();
        player1textField.setFont(new Font("Monaco",Font.PLAIN, 15));
        player1textField.setBounds(50,150,400,35);

        player2textField = new TextField();
        player2textField.setFont(new Font("Monaco",Font.PLAIN, 15));
        player2textField.setBounds(50,240,400,35);

        player3textField = new TextField();
        player3textField.setFont(new Font("Monaco",Font.PLAIN, 15));
        player3textField.setBounds(50,330,400,35);

        player4textField = new TextField();
        player4textField.setFont(new Font("Monaco",Font.PLAIN, 15));
        player4textField.setBounds(50,420,400,35);
        */

        playedPieces = new ArrayList<>();
        tempPieces = new ArrayList<>();
    }

    public static void main(String[] args) throws WrongCoordinateException {
        Player p1 = new Player("asdf","assets/avatar/ivorra_player.png");
        Player p2 = new Player("asdf","assets/avatar/enrique_avatar.png");
        Player p3 = new Player("asdf","assets/avatar/arancha_avatar.png");
        Player p4 = new Player("asdf","assets/avatar/jon_nieve.jpg");

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
                rack.setPieces(plPieces);
                break;
            }
        }

        float hsb[] = new float[3];
        float color[];

        color = Color.RGBtoHSB(190, 218, 212, hsb); // 192,128,64
        bg.setColor(Color.getHSBColor(color[0], color[1], color[2]));
        bg.fillRoundRect(currentPlayerUI.getX() - 20, currentPlayerUI.getY() - 20, currentPlayerUI.getW() + 240, currentPlayerUI.getH() + 40, 20, 20);

        rack.draw(bg, this);

        for (UIPiece piece : playedPieces)
            piece.draw(bg, this);
        for (UIPiece piece : tempPieces)
            piece.draw(bg, this);
        for (UIPlayer player : players) {
            player.draw(bg, this);
        }

        if (selectedPiece != null)
            bg.drawImage(
                    selectedPiece.getImage(),
                    selectedPiece.getX() + 3,
                    selectedPiece.getY() + 3,
                    selectedPiece.getW() - 6,
                    selectedPiece.getH() - 6,
                    this
            );


        // Draw best player
        Player bestp = game.getBestPlayer();
        for (UIPlayer player : players) {
            if (player.getPlayer() == bestp) {
                player.drawCrown(bg, this);
            }
        }

        pass.draw(bg, this);
        mix.draw(bg, this);
        exchange.draw(bg, this);
        undo.draw(bg, this);

        g2.drawImage(buffer, 0, 0, this);
    }
/*
    private void drawInitial() {

        setSize(500, 600);

        Container container = this.getContentPane();
        setLayout(new BorderLayout());

        // Info
        JLabel info = new JLabel("Input players");
        info.setBounds(150, 70, 400, 100);
        info.setFont(new Font("Monaco", Font.BOLD, 25));
        info.setForeground(Color.BLACK);
        info.setHorizontalAlignment(SwingConstants.CENTER);

        container.add(info, BorderLayout.PAGE_START);


        // Players
        Panel panelDisplay = new Panel(new GridLayout(8, 1, 50, 30));

        for (int i = 0; i < 4; i++) {
            JLabel player = new JLabel("Player " + (i + 1));
            player.setBounds(50, 110 + (i * 90), 400, 30);
            player.setFont(new Font("Monaco", Font.ITALIC, 20));
            player.setForeground(Color.darkGray);
            player.setHorizontalAlignment(SwingConstants.LEFT);

            container.add(player);

            // Input Player names
            switch (i) {
                case 0:
                    container.add(player1textField);
                    break;
                case 1:
                    container.add(player2textField);
                    break;
                case 2:
                    container.add(player3textField);
                    break;
                case 3:
                    container.add(player4textField);
                    break;
            }
        }

        container.add(panelDisplay, BorderLayout.CENTER);

        // Play Button
        container.add(playButton, BorderLayout.PAGE_END);

        playButton.addActionListener(this);


        // Background
        float hsb[] = new float[3]; float color[];

        color = Color.RGBtoHSB(188, 180,139,hsb);
        container.setBackground(Color.getHSBColor(color[0], color[1], color[2]));

        this.setContentPane(container);
    }
*/
    public void revokeTempPieces() {

    }

    public void newTurn() {
//       currentPlayer = game.nextTurn();
//        rack.setPieces(currentPlayer.getPieces());
    }
/*
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == playButton) {
            //for (int i = 0; i < 4; i++) {
                // Random Players for UI pattern design
                Player p1 = new Player(player1textField.getText(),"assets/avatar/ivorra_player.png");
                Player p2 = new Player(player2textField.getText(),"assets/avatar/enrique_avatar.png");
                Player p3 = new Player(player3textField.getText(),"assets/avatar/arancha_avatar.png");
                Player p4 = new Player(player4textField.getText(),"assets/avatar/jon_nieve.jpg");
                ArrayList<Player> player = new ArrayList<Player>();
                player.add(p1);    player.add(p2);    player.add(p3);    player.add(p4);
                try {
                    player.get(0).fillRack(game.getBag());
                    player.get(1).fillRack(game.getBag());
                    player.get(2).fillRack(game.getBag());
                    player.get(3).fillRack(game.getBag());
                } catch (NoPiecesInBagException e1) {
                    e1.printStackTrace();
                }

                UIPlayer uip1 = new UIPlayer(1000,75,150,p1);
                UIPlayer uip2 = new UIPlayer(1400,75,150,p2);
                UIPlayer uip3 = new UIPlayer(1000,225 + 50,150,p3);
                UIPlayer uip4 = new UIPlayer(1400,225 + 50,150,p4);

                players = new ArrayList<UIPlayer>();
                players.add(uip1);    players.add(uip2);    players.add(uip3);    players.add(uip4);

                game.setPlayers(player);
                currentPlayer = game.getPlayers().get(game.getTurn());
            //}

            // Delete all components
            removeAll();
            // Set next GameState
            state = GameState.IN_GAME;

            run();
        } /*else if (e.getSource() == test) {
            System.out.print("kdksmkckmc");
        }* /
    }
*/
/*
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Click");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("Pressed");

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("Released");

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }*/
}
