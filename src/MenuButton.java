import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.control.Label;

/**
 * Purpose: collection of static methods for creating buttons and labels
 * Assumptions: none
 * Dependencies: none
 * Example: MenuButton.makeButton(String, double, double, double);
 *
 * Class that defines button and label creation, to be
 * used by other classes. Does not have any instance
 * variables or state, so all methods are static.
 *
 * @author Wiliam Xiong
 */

public class MenuButton {

    /**
     * @param text the text on the button
     * @param x the x location of button
     * @param y the y location of button
     * @param minWidth the minimum width of the button
     * @return a button containing the above attributes
     */
    public static Button makeButton(String text, double x, double y, double minWidth){
        Button b = new Button(text);
        b.setMinWidth(minWidth);
        b.setScaleX(Main.BUTTON_SCALE);
        b.setScaleY(Main.BUTTON_SCALE);
        b.setLayoutX(x);
        b.setLayoutY(y);
        return b;
    }

    /**
     * @param text text for the label
     * @param x x location of label
     * @param y y location of label
     * @return a label containing the above attributes
     */
    public static Label makeLabel(String text, double x, double y){
        Label l = new Label(text);
        l.setLayoutY(y);
        l.setLayoutX(x);
        return l;
    }

    /**
     * @param text text for label
     * @param x x location of label
     * @param y y location of label
     * @param f font of label
     * @return a label containing above attributes
     */
    public static Label makeLabel(String text, double x, double y, Font f){
        Label l = new Label(text);
        l.setLayoutX(x);
        l.setLayoutY(y);
        l.setFont(f);

        return l;
    }
}
