package scrabble.view;

/**
 * Created by enrique on 27/09/16.
 */
public class UICell extends UIObject {
    public UICell(int x, int y) {
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
