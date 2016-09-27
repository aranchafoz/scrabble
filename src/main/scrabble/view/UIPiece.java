package main.scrabble.view;

/**
 * Created by enrique on 27/09/16.
 */
public class UIPiece extends UIObject {
    public UIPiece(int x, int y) {
        super(x, y);
    }

    @Override
    public void update() {

    }

    @Override
    public boolean receiveInput() {
        return false;
    }
}
