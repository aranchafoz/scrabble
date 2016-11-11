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

    private int sideWidth = 60;

    public UIButton(int x, int y, String text, String image) {
        super(x, y);
        w = sideWidth;
        h = sideWidth;

        this.text = text;
        setImage(image);
    }

    public boolean isPressed(Point p) throws OccupiedCellException {
        if (receiveInput(p))
            return true;
        else
            return false;
    }

    public void draw(Graphics2D g2, JFrame context) {
        super.draw(g2, context);


    }
}
