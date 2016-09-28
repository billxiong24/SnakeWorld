import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * Purpose: Displays scene when the user wins the game
 * Assumptions: none
 * Dependencies: none
 * Example: In SceneControl class, create method, create Win object,
 * call displayScene, use SceneControl stage variable to switch to this scene.
 *
 * @author William Xiong
 */
public class Win implements SceneMaker{
	private Scene win;

	public Scene getScene(){
		return win;
	}

    /**
     * Add label and menu button to scene.
     */
	public void displayScene(){
		Group g = new Group();
		win = new Scene(g, Main.WIDTH, Main.HEIGHT, Color.DEEPSKYBLUE);
		g.getChildren().add(makeLabel());
		g.getChildren().add(menu());
	}
	
	private Label makeLabel(){
		Label l = new Label("You Win!!!");
		l.setFont(new Font("Verdana", 40));
		l.setTranslateX(Main.WIDTH/2 - 110);
		l.setTranslateY(Main.HEIGHT/2 - 60);
		return l;
	}
	private Button menu(){
		Button back = new Button("Menu");
		
		back.setScaleX(Main.BUTTON_SCALE);
		back.setScaleY(Main.BUTTON_SCALE);

		back.setLayoutX(Main.WIDTH/2 - 30);
		back.setLayoutY(Main.HEIGHT/2 + 90);
		
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
