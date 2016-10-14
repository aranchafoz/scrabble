package main.scrabble.view;

import main.scrabble.model.Player;

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

}
