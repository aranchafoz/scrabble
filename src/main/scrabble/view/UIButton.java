package main.scrabble.view;

/**
 * Created by enrique on 30/09/16.
 */
public class UIButton extends UIObject {
    private String text;

    public UIButton(int x, int y, String text) {
        super(x, y);
        this.text = text;
    }

}
