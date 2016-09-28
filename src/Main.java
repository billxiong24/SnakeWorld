import javafx.stage.Stage;
import javafx.application.Application;

/**
 * Purpose: Driver class that runs the program.
 * Dependencies: none
 * Assumptions: none
 * Example: Click the green arrow to play the GAAAAYME
 * Defines global constants such as width and height of window.
 *
 * @author William Xiong
 */
public class Main extends Application{
	
	public static final int WIDTH = 800;
	public static final int HEIGHT = 550;
	public static final int BUTTON_SCALE = 2;

    /**
     * sets the stage to the menu scene, and shows the stage.
     * @param primaryStage the stage to display the scene
     */
    @Override
	public void start(Stage primaryStage){

		SceneControl.setStage(primaryStage);
		SceneControl.switchToMenu();
		primaryStage.show();
	}
	
    public static void main(String[] args) {
    	launch(args);
    }
}
