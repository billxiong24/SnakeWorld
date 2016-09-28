import javafx.animation.FadeTransition;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * Purpose: Use with Snake class to create a laser on the screen.
 * Dependencies: none
 * Assumptions: coordinates will be within the bounds, Group p in fire should not be null
 * Dependencies: none
 * Example: Laser l = new Laser(10, 20, 14, 23); l.fire(group);
 * Defines a Laser object that is added to a Scene object.
 * Represents a firing laser.
 * @author William Xiong
 */
public class Laser{

	static final int LASER_LENGTH = 200;
	private Rectangle laser;
	private double x, y;

    /**
     * Initializes instance variables (laser, x, y)
     * @param x starting x location
     * @param y starting y location
     * @param w starting width
     * @param h starting height
     * @param c color
     */
	public Laser(double x, double y, double w, double h, Color c){
		laser = new Rectangle(w, h, c);
		this.x = x;
		this.y = y;
	}

	public Rectangle getLaser(){
		return laser;
	}

    /**
     * sets the location of the laser, and adds it to a Group, then fades.
     * @param g the Group to display the laser on
     */
	public void fire(Group g){
		laser.setTranslateX(x);
		laser.setTranslateY(y);
		g.getChildren().add(laser);
		fade();
	}
	//laser will fade from view.
	private void fade(){
		FadeTransition ft = new FadeTransition(Duration.millis(300), laser);
    	ft.setFromValue(1.0);
    	ft.setToValue(0.0);
    	ft.play();
	}
}
