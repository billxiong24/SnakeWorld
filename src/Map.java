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
 * Purpose: Map scene that allows user to choose the gameplay difficulty,
 * as well as a back button to go back to the main menu.
 * Selecting a difficulty will start the game on that difficulty, which is predefined by
 * 3 constants.
 *
 * Assumptions: none
 * Dependencies: none
 * Example: In SceneControl class, create object, call displayScene,
 * use Stage object to switch to the Map's scene, accessible through the getScene method.
 *
 * @author William Xiong
 */
public class Map implements SceneMaker{
	
	private Scene m;
	public static final int EASY = 1;
    public static final int MEDIUM = 5;
    public static final int INSANE = 8;

	public Scene getScene(){
		return m;
	}

    /**
     * Adds 3 difficulty buttons, a back button, and a label.
     */
    @Override
	public void displayScene(){
		Group g = new Group();
		m = new Scene(g, Main.WIDTH, Main.HEIGHT, Color.CRIMSON);
		
		Label l = new Label("MAP");
		l.setFont(new Font("Verdana", 30));
		g.getChildren().add(l);
        Button [] d = difficulty();
        for(int i = 0; i < d.length; i++)
            g.getChildren().add(d[i]);

		g.getChildren().add(backToMenu());
	}

	private Button []difficulty(){

	    Button[] difficulties = new Button[3];
        difficulties[0] = diff("Easy", 60, Main.HEIGHT/2 - 30, EASY);
        difficulties[1] = diff("Medium", Main.WIDTH/2 - 40, Main.HEIGHT/2 - 30, MEDIUM);
        difficulties[2] = diff("INSANE", 650, Main.HEIGHT/2 - 30, INSANE);
        return difficulties;
    }

    private Button diff(String text, int x, int y, int diff){
        Button b = MenuButton.makeButton(text, x, y, 80);
        b.setOnKeyPressed(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent event){
                if(event.getCode() == KeyCode.ENTER){
                    SceneControl.switchToHouse(1, diff);
                }
            }
        });
        return b;
    }

	private Button backToMenu(){
        Button back = MenuButton.makeButton("Back", Main.WIDTH - 110, 20, 60);
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
