import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Purpose: Represents a "trap" in the game. Extends and overrides
 * methods in the Item class to provide unique functionality.
 * Upon hitting a MouseTrap object, the user will lose 1 life,
 * and the MouseTrap object will disappear.
 *
 * Assumptions: Should only be created in House.java and methods should be
 * called from snake class.
 *
 * Dependencies: none
 * Example: MouseTrap m = new MouseTrap();
 *  m.putTrap(Group); to put it on the scene.
 * @author William Xiong
 */

public class MouseTrap extends Item{

	private Circle trap;

    /**
     *  Intializes Circle instance variable "trap"
     */
    public MouseTrap(){
		super();
        trap = new Circle(Item.getRandX(), Item.getRandY(), 30, Color.BURLYWOOD);
	}
	public Circle getTrap(){
	    return trap;
    }

    /**
     * Adds MouseTrap (Circle object) to a Group object
     * @param g Group to put Circle object in
     */
    @Override
	public void putTrap(Group g){
	    g.getChildren().add(trap);
    }
}
