import javafx.stage.Stage;
/**
 * Purpose: Defines methods for switching between scenes. Contains a copy of the Stage object,
 * whose state is to be preserved.
 * Assumptions: none
 * Dependencies: none
 * Example: SceneControl.switchToMap(); to switch to map scene;
 *
 * The methods in this class are static since it is necessary
 * to preserve the state of the Stage throughout the life of the program.
 * The reasons for preserving the state of the Stage are obvious- there can
 * only be a single stage, which is passed in as a parameter in the start
 * function in the Main class.
 * By making the methods static, it is not necessary to pass a reference
 * to a SceneControl object in every single class that deals with changing
 * scenes. By making the methods static, it ensures that the stage's global
 * state is preserved, which is necessary.
 *
 * @author William Xiong
 */

public class SceneControl{

    /**
     * This Stage variable is static because there can only be one Stage variable for this game.
     * Thus, the global state of the stage should be constant throughout the life of the program.
     * There should only ever be one copy of the Stage variable, since there is only 1 game.
     * Another alternative to making this Stage variable static is to
     * pass a reference to a single SceneControl object (or Stage object)
     * to every class that interacts with the stage, which is
     * cumbersome and unecessary for the purpose of this game.
     */
    private static Stage primaryStage;

    /**
     * initializes primaryStage
     * @param s Stage variable to assign to primaryStage
     */
	public static void setStage(Stage s){
		primaryStage = s;
	}

    /**
     * switches the scene of the stage to map
     */
	public static void switchToMap(){
	    Map map = new Map();
		map.displayScene();
		primaryStage.setScene(map.getScene());
	}

    /**
     * switches the scene of the stage to main menu
     */
	public static void switchToMenu(){
	    Menu menu = new Menu();
		menu.displayScene();
		primaryStage.setScene(menu.getScene());
	}

    /**
     * Switches the scene of the stage to the actual game
     * @param level level to start off
     * @param diff difficulty of the game
     */
	public static void switchToHouse(int level, int diff){
	    House house = new House(level, diff);
		Snake s = new Snake(house);
        s.getHouse().displayScene(s);
		primaryStage.setScene(s.getHouse().getScene());
        primaryStage.show();
	}

    /**
     * switch to game over scene
     */
	public static void endGame(){
	    GameOver gameOver = new GameOver();
		gameOver.displayScene();
		primaryStage.setScene(gameOver.getScene());
	}

    /**
     * Switch to win game scene if boss is defeated
     */
	public static void winGame(){
		Win win = new Win();
	    win.displayScene();
		primaryStage.setScene(win.getScene());
	}

    /**
     * switch scene of stage to practice mode
     */
	public static void switchToPractice(){
        House house = new House(0, 0);
        Snake s = new Snake(house);
        s.getHouse().displayScene(s);
        primaryStage.setScene(s.getHouse().getScene());
        primaryStage.show();
    }
}
