package main.scrabble.view;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Lenovo on 14/11/2016.
 */
public class UIMessageBox extends UIObject {
    private String message;

    public UIMessageBox(int x, int y, int width, int height) {
        super(x, y);

        w = width;
        h = height;

        message = "Welcome to Scrabble!";
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public void draw(Graphics2D g2, JFrame context) {

        float hsb[] = new float[3];
        float color[];

        BasicStroke backShadow = new BasicStroke(4f);
        g2.setStroke(backShadow);
        color = Color.RGBtoHSB(190, 218, 212,hsb);
        g2.setColor(Color.getHSBColor(color[0], color[1], color[2]));
        //g2.setColor(Color.GRAY);
        g2.drawRoundRect(x, y, w, h, 10, 10);

        color = Color.RGBtoHSB(61, 160, 178, hsb);  //190, 218, 212,hsb);
        g2.setColor(Color.getHSBColor(color[0], color[1], color[2]));
        g2.fillRoundRect(x, y, w, h, 10, 10);

        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Monaco",Font.PLAIN, 20));
        g2.drawString(message, x + 30, y + 55);
    }
}
