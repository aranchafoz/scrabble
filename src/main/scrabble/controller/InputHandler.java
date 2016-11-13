package main.scrabble.controller;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by enrique on 26/09/16.
 */
public class InputHandler implements MouseListener {
    private Point lastClick;

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Click");
        lastClick = new Point(e.getPoint());
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("Pressed");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("Released");
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    Point getInputs() {
        if (lastClick != null) {
            Point p = lastClick;
            lastClick = null;
            return p;
        }
        return null;
    }
}
