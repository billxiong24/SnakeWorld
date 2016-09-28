import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.input.*;
import javafx.scene.control.*;
import javafx.scene.text.Font;

/**
 * Purpose: main screen of the entire game.
 * It is the first screen the user sees when opening the game.
 * has 3 different options: Start game, Practice, and Instructions.
 *
 * Assumptions: none
 * Dependencies: none
 * Example: In a SceneControl class method, create object, call displayScene, call getScene
 * use Stage object to switch to scene
 *
 *
 * @author William Xiong
 */
public class Menu implements SceneMaker{

	private Scene main;
	private Group menu;
	private Group instr;

    /**
     * Constructor- initializes instance variables
     */
	public Menu(){
		menu = new Group();
		main = new Scene(menu, Main.WIDTH, Main.HEIGHT, Color.GREEN);
		instr = new Group();
	}
	
	public Scene getScene(){
		return main;
	}

    /**
     * adds title, message, start, practice, and instructions button.
     */
    @Override
	public void displayScene(){
		menu.getChildren().add(addStart());
		menu.getChildren().add(addPractice());
		menu.getChildren().add(addInstructions());
        menu.getChildren().add(intro());
        menu.getChildren().add(introMessage());
	}
	
	private Button addPractice(){
		Button multi = MenuButton.makeButton("Practice", Main.WIDTH/2 - 40, Main.HEIGHT/2 + 57, 94);
		multi.setOnKeyPressed(new EventHandler<KeyEvent>(){
			@Override
			public void handle(KeyEvent event){
				if(event.getCode() == KeyCode.ENTER){
				    SceneControl.switchToPractice();
				}
			}
		});
		return multi;
	}
	private Button addStart(){
		Button start = MenuButton.makeButton("Start Game", Main.WIDTH/2 - 40, Main.HEIGHT/2, 94);
		start.setOnKeyPressed(new EventHandler<KeyEvent>(){
			@Override
			public void handle(KeyEvent event){
				if(event.getCode() == KeyCode.ENTER){
					SceneControl.switchToMap();
				}		
			}
		});
		return start;
	}
	
	private Button addInstructions(){
	    Button instr = MenuButton.makeButton("Instructions", Main.WIDTH/2 - 40, Main.WIDTH/2 - 10, 94);
		instr.setOnKeyPressed(new EventHandler<KeyEvent>(){
			@Override
			public void handle(KeyEvent event){
				if(event.getCode() == KeyCode.ENTER)
					displayInstructions();
	    	}
		});
		return instr;
	}
	
	private void displayInstructions(){
        instr.getChildren().add(instructions());
        instr.getChildren().add(backButton());
		main.setRoot(instr);
	}
	
	private Button backButton(){
	    Button back = MenuButton.makeButton("Back", Main.WIDTH/2 - 30, Main.HEIGHT/2 + 90, 94);
		back.setOnKeyPressed(new EventHandler<KeyEvent>(){
			@Override
			public void handle(KeyEvent event){
				if(event.getCode() == KeyCode.ENTER){
					main.setRoot(menu);
				}					
			}
		});
		return back;
	}

	private Label intro(){
        return MenuButton.makeLabel("SNAKE WORLD", Main.WIDTH/2 - 180, Main.HEIGHT/2 - 200, new Font("Verdana", 50));
    }
    private Label introMessage(){
        return MenuButton.makeLabel("Use arrow keys and press enter to select an option.",
                Main.WIDTH/2 - 260, Main.HEIGHT/2 - 100, new Font("Verdana", 20));
    }
	private Label instructions(){
		return MenuButton.makeLabel("Use arrow keys to move.\n"
                +"Press space to fire laser.\n"
                +"Press q to quit, p to pause.\n"
                +"Catch the mice (the green rectangle), and avoid mouse traps and poison.\n"
                +"Hitting a mouse trap (large brown circle) decreases your health by 1, while \n"
                +"hitting poison (small purple circle) decreases your health by 10. \n"
                +"To advance to the next level, you must catch 3 mice without letting \n"
                +"your health get to 0. Defeat the golden mouse on the last level to win!",
                Main.WIDTH/2 - 150, Main.HEIGHT/2 - 90);
	}
}
