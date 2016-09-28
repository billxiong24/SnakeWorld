
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Purpose: Defines a Mouse object which the Snake object will "eat".
 * The Mouse object randomly relocates upon being "eaten".
 *
 * Assumptions: Mouse object should be only created in House.java class
 * methods should be called from snake class, since it interacts with snake
 * Dependencies: none
 * Example: Mouse = new Mouse(3, 4, 10, 10, Color.BLACK);
 *
 * @author William Xiong
 */
public class Mouse{

	private Rectangle mouse;

    /**
     * Intializes the Rectangle instance variable "mouse", giving
     * it width, height, color, and location.
     * @param w width of rectangle
     * @param h height of rectangle
     * @param x x location of rectangle
     * @param y y location of rectangle
     * @param color color of rectangle
     */
	public Mouse(int w, int h, double x, double y, Color color){
			
		this.mouse = new Rectangle(w, h, color);
		this.mouse.setTranslateX(x);
		this.mouse.setTranslateY(y);
	}

    /**
     * Randomly changes coordinates of mouse
     */
	public void randCoord(){
		setCoord(Item.getRandX(), Item.getRandY());
	}
	
	public Rectangle getMouse(){
		return mouse;
	}
	
	private void setCoord(double x, double y){
		mouse.setTranslateX(x);
		mouse.setTranslateY(y);
	}
}
