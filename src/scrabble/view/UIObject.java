package scrabble.view;

import javax.swing.*;
import java.awt.*;

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
    }

    public void draw(Graphics g, JFrame context) {
        g.drawImage(image, x, y, context);
    }

    abstract public void update();
    abstract public boolean receiveInput();
}
