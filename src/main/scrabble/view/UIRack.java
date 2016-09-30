package main.scrabble.view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by enrique on 27/09/16.
 */
public class UIRack extends UIObject {
    ArrayList<UIPiece> pieces;

    public UIRack(int x, int y) {
        super(x, y);
    }
/*
    public void setPieces(ArrayList<Piece> pieces) {
        for (Piece p : pieces) {
            pieces.add(new UIPiece(p));
        }
    }
*/
    @Override
    public void draw(Graphics g, JFrame context) {
        super.draw(g, context);

    }

    @Override
    public void update() {

    }

}
