import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * Purpose: Display the game over when player loses game.
 * Assumptions: none
 * Dependencies: none
 * Example: In the SceneControl class, create method as follows:
 * GameOver g = new GameOver();
 * g.displayScene();
 * stage.setScene(g.getScene()); to switch to game over scene.
 *
 * Class containing game over scene.
 * Contains a "Game Over" message, and a button to
 * go back to the main menu.
 * @author William Xiong
 */
public class GameOver implements SceneMaker{
	
	private Scene over;
    private Group root;
	public Scene getScene(){
		return over;
	}

    /**
     * Initializes scene and group.
     */
	public GameOver(){
	    root = new Group();
	    over = new Scene(root, Main.WIDTH, Main.HEIGHT, Color.TURQUOISE);
    }

    @Override
	public void displayScene(){
		root.getChildren().add(text());
		root.getChildren().add(menu());
	}

	
	private Label text(){
		return MenuButton.makeLabel("GAME OVER", Main.WIDTH/2 - 154, Main.HEIGHT/2 - 100, new Font("Verdana", 50));
	}
	
	private Button menu(){
	    Button back = MenuButton.makeButton("Menu", Main.WIDTH/2 - 40, Main.HEIGHT/2, 80);
		back.setOnKeyPressed(new EventHandler<KeyEvent>(){
			@Override
			public void handle(KeyEvent event){
				if(event.getCode() == KeyCode.ENTER){
                   SceneControl.switchToMenu();
				}
			}
		});
		return back;
	}
}
