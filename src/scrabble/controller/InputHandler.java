package scrabble.controller;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by enrique on 26/09/16.
 */
class InputHandler implements MouseListener {
    private Point lastClick;

    @Override
    public void mouseClicked(MouseEvent e) {
        lastClick = new Point(e.getPoint());
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

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
