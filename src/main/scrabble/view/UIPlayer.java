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

    private static final int imgRightMargin = 15;
    private static final int playerNameTopMargin = 45;
    private static final int playerPunctTopMargin = 100;
    private static final int crownHfit = 35;
    private static final int crownVfit = -50;

    public UIPlayer(int x, int y,int sideWidth, Player p) {
        super(x, y);
        w = sideWidth;
        h = sideWidth;
        player = p;

        setImage(player.getAvatarPath());
    }

    @Override
    public void draw(Graphics2D g2, JFrame context) {
        super.draw(g2,context);

        // Draw name
        //Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.BLACK);
        g2.setFont(new Font("Monaco",Font.BOLD, 25));
        g2.drawString(player.getName(), x + w + imgRightMargin, y + playerNameTopMargin);

        // Draw punctuation
        g2.setColor(Color.darkGray);
        g2.setFont(new Font("Monaco",Font.ITALIC, 20));
        g2.drawString(String.valueOf(player.getScore()) + " points", x + w + imgRightMargin, y + playerPunctTopMargin);
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void drawCrown(Graphics2D g2, JFrame context) {

        try {
            Image crown = ImageIO.read(new File("assets/Queen UK-80.png"));
            g2.drawImage(crown, x + crownHfit, y + crownVfit, context);
        } catch (IOException e) {

        }

    }
}
