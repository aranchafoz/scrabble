package main.scrabble.view;

import main.scrabble.exceptions.OccupiedCellException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by enrique on 30/09/16.
 */
public class UIButton extends UIObject {
    private String text;

    private int sideWidth = 90;
    private int sideWidthPlay = 200;
    private int sideHeightPlay = 75;

    public UIButton(int x, int y) {
        super(x, y);
        w = sideWidthPlay;
        h = sideHeightPlay;

        this.text = "";
    }

    public UIButton(int x, int y, String text, String image) {
        super(x, y);
        w = sideWidth;
        h = sideWidth;

        this.text = text;
        setImage(getClass().getResource(image));
    }

    public boolean isPressed(Point p) throws OccupiedCellException {
        if (receiveInput(p))
            return true;
        else
            return false;
    }

    public void draw(Graphics2D g2, JFrame context) {

        if (image != null) {
            super.draw(g2, context);

            g2.setColor(Color.BLACK);
            g2.setFont(new Font("Monaco",Font.BOLD, 17));

            switch (text) {
                case "Undo":
                    g2.drawString(text, x + 25, y + 120);
                    break;
                case "Exchange":
                    g2.drawString(text, x + 9, y + 120);
                    break;
                case "Shuffle":
                    g2.drawString(text, x + 18, y + 120);
                    break;
                case "Pass":
                    g2.drawString(text, x + 30, y + 120);
                    break;
            }
        } else {

            float hsb[] = new float[3];
            float color[];

            color = Color.RGBtoHSB(58,144,163,hsb);

            g2.setColor(Color.getHSBColor(color[0], color[1], color[2]));
            g2.fillRoundRect(x, y, sideWidthPlay, sideHeightPlay, 20, 20);

            g2.setColor(Color.white);
            g2.setFont(new Font("Monaco",Font.BOLD, 30));
            g2.drawString("PLAY", 220, 550);

        }
    }
}
