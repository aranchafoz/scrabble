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

        setImage("assets/bck2.png");
    }

}
