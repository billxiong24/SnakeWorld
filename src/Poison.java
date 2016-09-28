import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Purpose: Represents a "trap" in the game. Extends Item class and
 * overrides methods to provide unique functionality.
 * User loses 10 lives upon hitting a Poison object. Poison
 * object disappears upon contact.
 *
 * Assumptions: object instantiated in House.java, methods called
 * from snake class.
 * Dependencies: none
 * Example: Poison p = new Poison();
 * p.putTrap(Group); to put the trap on a scene.
 *
 * @author William Xiong
 */
public class Poison extends Item{

    private Circle pois;

    /**
     * Intializes Circle instance variable "pois" with a random location
     *
     */
    public Poison() {
        super();
        pois = new Circle(Item.getRandX(), Item.getRandY(), 10, this.color);
    }
    public Circle getPois(){
        return pois;
    }

    /**
     * Add Poison (Circle object) to Group object
     * @param g Group to put Circle object in
     */
    @Override
	public void putTrap(Group g){
        g.getChildren().add(pois);
    }
}
