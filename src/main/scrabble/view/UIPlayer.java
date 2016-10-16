package main.scrabble.view;

import main.scrabble.model.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by enrique on 27/09/16.
 */
public class UIPlayer extends UIObject {
    private Player player;
    public UIPlayer(int x, int y,int sideWidth, Player p) {
        super(x, y);
        w = sideWidth;
        h = sideWidth;
        player = p;

        setImage(player.getAvatarPath());
    }

    @Override
    public void draw(Graphics g, JFrame context) {
        super.draw(g,context);

        // Draw name
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.BLACK);
        g2.setFont(new Font("Monaco",Font.BOLD, 25));
        g2.drawString(player.getName(), x + 165, y + 45);

        // Draw punctuation
        g2.setColor(Color.darkGray);
        g2.setFont(new Font("Monaco",Font.ITALIC, 20));
        g2.drawString(String.valueOf(player.getPunctuation()) + " points", x + 165, y + 100);
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void drawCrown(Graphics g, JFrame context) {

        try {
            Image crown = ImageIO.read(new File("assets/Queen UK-80.png"));
            g.drawImage(crown, x + 35, y - 50, context);
        } catch (IOException e) {

        }

    }
}
