package main.scrabble.controller;

import main.scrabble.exceptions.NoPiecesInBagException;
import main.scrabble.exceptions.WrongCoordinateException;
import main.scrabble.model.NPC;
import main.scrabble.model.Player;
import main.scrabble.view.UIPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by enrique on 13/11/16.
 */
public class MenuController extends JFrame implements ActionListener {
    private JButton playButton;

    private TextField player1textField;
    private TextField player2textField;
    private TextField player3textField;
    private TextField player4textField;

    private boolean finished = false;

    private ArrayList<Player> players;

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == playButton) {
            // for (int i = 0; i < 4; i++) {
            // Random Players for UI pattern design
            Player p1 = new Player(player1textField.getText(), "assets/avatar/ivorra_player.png");
            NPC p2 = new NPC(player2textField.getText(), "assets/avatar/enrique_avatar.png");
            Player p3 = new Player(player3textField.getText(), "assets/avatar/arancha_avatar.png");
            Player p4 = new Player(player4textField.getText(), "assets/avatar/jon_nieve.jpg");

            players.add(p1);
            players.add(p2);
            players.add(p3);
            players.add(p4);

            removeAll();
            finished = true;

            // Set next GameState
        } /*else if (e.getSource() == test) {
            System.out.print("kdksmkckmc");
        }*/

    }


    private MenuController() throws WrongCoordinateException {
        // Text fields
        players = new ArrayList<>();

        playButton = new JButton("START");
        playButton.setBounds(155,500,200,100);

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

        setVisible(true);
    }

    public boolean hasFinished() { return finished; }

    private void run() {
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

        color = Color.RGBtoHSB(188, 180, 139, hsb);
        container.setBackground(Color.getHSBColor(color[0], color[1], color[2]));

        this.setContentPane(container);
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public static void main(String[] args) throws WrongCoordinateException, InterruptedException {
        MenuController menu = new MenuController();
        menu.run();

        while (!menu.hasFinished()) {
            Thread.sleep(100);
        }

        GameController g = null;
        try {
            g = new GameController(menu.getPlayers());
        } catch (WrongCoordinateException e1) {
            e1.printStackTrace();
        }
        menu.setVisible(false);
        g.run();
    }
}

