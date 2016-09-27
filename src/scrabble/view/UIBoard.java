package scrabble.view;

import javafx.scene.control.Cell;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by enrique on 27/09/16.
 */
public class UIBoard extends UIObject {
    private Board board;
    private ArrayList<UICell> cells;

    public UIBoard(int x, int y, Board b) {
        super(x, y);

        // Receive board and create the UICell array from it
    }

    @Override
    public void draw(Graphics g, JFrame context) {
        super.draw(g, context);

        // draw the board (super.draw) and all its cells
    }

    @Override
    public boolean receiveInput() {
        return false;
    }

    @Override
    public void update() {

    }
}
