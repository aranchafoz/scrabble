package main.scrabble.view;

import main.scrabble.exceptions.OccupiedCellException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by enrique on 30/09/16.
 */
public class UIButton extends UIObject {
    private String text;

    public UIButton(int x, int y, String text) {
        super(x, y);
        this.text = text;
        //setImage(text);
    }

    public boolean isPressed(Point p) throws OccupiedCellException {
        if (receiveInput(p))
            return true;
        else
            return false;
    }
}
