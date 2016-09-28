import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.*;
import java.util.Map;

/**
 * Purpose: Superclass for creating items to put in the game.
 * Assumptions: subclasses will override putTrap method.
 * Dependencies: none
 * Example: create a class, extend Item class, override putTrap method and
 * actionOnHit if necessary.
 *
 * Defines an Item super class for placing items on the screen.
 * @author William Xiong
 */
public class Item {

	protected Color color;

    /**
     * Sets default color to purple
     */
	public Item(){
		//default color
		color = Color.PURPLE;
	}

    /**
     * Performs an action when another Node intersects with a Circle object
     * Default action: remove Circle from Group.
     * @param pane the pane on which to perform an action
     * @param c the circle on which to perform an action
     */
	public void actionOnHit(Group pane, Circle c){
        pane.getChildren().remove(c);
    }

    //override in subclass
	protected void putTrap(Group g){

    }

    /**
     * Checks for intersections between a Rectangle object and Items.
     * If intersection is found, act on the corresponding Circle object (stored in map).
     * @param map map to iterate through and check for intersections
     * @param snake Rectangle object to check intersections with
     * @param pane pane to be modified upon intersection
     * @return the item that was intersected with snake. If no intersections, return null.
     */
	public Item hit(HashMap<Item, Circle> map, Rectangle snake, Group pane){

		for(Node n : pane.getChildren()){
		    boolean test = n.getBoundsInParent().intersects(snake.getBoundsInParent());
            for(Item a : map.keySet()){
                if(map.get(a) == n && test){
                    a.actionOnHit(pane, (Circle) n);
                    return a;
                }
            }
        }
		return null;
	}

    /**
     * This method is static since it does not depend on any state of an object.
     * It generates and returns a number.
     * @return a random x coordinate in which to put an object
     */
	public static double getRandX(){
		//to avoid collision right from the start
		return 40 + (int)(Math.random() * ((Main.WIDTH - 60) + 1));
	}

    /**
     * This method is static since it does not depend on any state of an object.
     * It generates and returns a number.
     * @return a random y coordinate in which to an object
     */
	public static double getRandY(){
		return 60 + (int)(Math.random() * ((Main.HEIGHT - 70) + 1));
	}
	
}
