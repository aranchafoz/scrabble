package main.scrabble.view;

import main.scrabble.exceptions.OccupiedCellException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Created by enrique on 26/09/16.
 */

/**
 * TODO in UI classes:
 * - Create constructor from its model
 * - Override draw methods in all classes
 * - Implement update method if necessary
 * - Implement receiveInput
 * - Set the images
 */

public abstract class UIObject {
    protected int x;
    protected int y;
    protected int w;
    protected int h;

    protected Image image;

    public UIObject(int x, int y) {
        this.x = x;
        this.y = y;
        image = null;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getW() { return w; }
    public int getH() { return h; }

    public void draw(Graphics2D g2, JFrame context) { g2.drawImage(image, x, y, w, h, context); }

    public boolean receiveInput(Point p) throws OccupiedCellException {
        return p.x > x && p.x < x + w && p.y > y && p.y < y + h;
    }

    public void setImage(String imagePath) {
        try {
            image = ImageIO.read(new File(imagePath));
        } catch (IOException e) {

        }
    }

    public void setImage(URL imagePath) {
        try {
            image = ImageIO.read(imagePath);
        } catch (IOException e) {

        }
    }
}
