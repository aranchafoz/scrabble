package main.scrabble.view;

import main.scrabble.model.Player;

import javax.swing.*;
import java.awt.*;

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

        // Draw puntuation

    }

}
