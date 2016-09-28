import javafx.scene.Scene;

/**
 * Purpose: Defines an interface for constructing a Scene with necessary components.
 * This standarizes the classes which represent different screens of the game.
 * There are 2 crucial methods which must be implemented. They will give information
 * on how the scene is to be constructed.
 *
 * Assumptions: none
 * Dependencies: none
 * Example: when making a "screen" class, implement this interface. getScene is like a getter method
 * to return the scene. displayScene should add all components to the scene.
 * @author William Xiong
 */
public interface SceneMaker {
    /**
     * @return a Scene object containing all elements to be displayed on the Stage.
     */
	Scene getScene();

    /**
     * adds all the elements to the scene that are to be displayed on the screen
     */
	void displayScene();
}
