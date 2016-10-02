package main.scrabble.view;

import javax.swing.*;
import java.awt.*;

/**
 * Created by enrique on 30/09/16.
 */
public class UIBackground extends UIObject {
    public UIBackground(int width, int height) {
        super(0, 0);
        w = width;
        h = height;
    }

    public void draw(Graphics g,JFrame context) {
        g.setColor(Color.getHSBColor(40, 68, 75)); // marrón clarito RGB(227,220,186)
        g.fillRect(0, 0, w, h);
    }
}
