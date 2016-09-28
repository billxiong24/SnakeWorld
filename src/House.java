// This entire file is part of my masterpiece.
// William Xiong
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import java.util.HashMap;

/**
 * This code is my code masterpiece. It is a good design because it extracts all duplicated
 * code, does not contain private static variables, and uses good naming convention.
 *
 * Purpose: Displays all gameplay elements such as snake, items, enemies, and game info.
 * Assumptions: Snake object passed in is not null.
 * Dependencies: none
 * Example: In SceneControl class, create object, call displayScene, use getScene to switch
 * scenes.
 *
 * This class displays all elements of the actual gameplay.
 * Contains Snake object, Item objects, Boss objects, and game info.
 * @author William Xiong
 */

public class House {

	public static final int bossLevel = 4;
	private Scene house;
    private Group pane;

	private Label lives;
	private Label miceLeft;
	private int levelNumber;
    private int difficulty;

    private int bossCount;
    private HashMap<Item, Circle> traps;
    private HashMap<Boss, Rectangle> bosses;

    /**
     * Initializes instance variables.
     * @param level the level of this particular scene
     * @param diff the difficulty of this scene
     */
	public House(int level, int diff){
	    pane = new Group();
	    levelNumber = level;
        difficulty = diff;
        traps = new HashMap<>();
        bosses = new HashMap<>();
        bossCount = (diff == Map.INSANE) ? 2 : 1;
	}

	public Group getPane(){
	    return pane;
    }
	public Label getMiceLeft(){
		return miceLeft;
	}
	public Label getLives(){
		return lives;
	}
	public Scene getScene(){
		return house;
	}
	public void resetLevel(){
		levelNumber = 0;
	}
	public int currLevel(){
		return levelNumber;
	}
    public int getDifficulty(){
        return difficulty;
    }
    public void killBoss(){
        --bossCount;
    }
    public int getNumBosses(){
        return bossCount;
    }

    public HashMap<Item, Circle> getMap(){
        return traps;
    }
    public HashMap<Boss, Rectangle> getBosses(){
        return bosses;
    }

    /**
     * Adds all the relevant elements to the scene, such as the snake, items, and game information.
     * @param s Snake object to add to the scene
     */
	public void displayScene(Snake s){
		house = new Scene(pane, Main.WIDTH, Main.HEIGHT, Color.CRIMSON);
		setSnake(s);
		setItems(s, difficulty * levelNumber, difficulty * levelNumber * 2);
		displayLives(pane, s.getMaxLives());
		displayMiceLeft(pane, s.getMiceGoal());
		displayLevel(pane);
		
		house.setOnKeyPressed(s.getControls());
		s.getAnimator().start();
	}

    /**
     * updates the level information
     * @param pane the pane in which to add the label
     */
	public void displayLevel(Group pane){

	    if(levelNumber == bossLevel)
	        showInfo("BOSS", 0, pane);
        else
            showInfo("Level: " + Integer.toString(levelNumber), 0, pane);
	}
	private void displayMiceLeft(Group pane, int l){
	    if(levelNumber != bossLevel)
            miceLeft = showInfo("Mice Left: " + Integer.toString(l), Main.WIDTH/2 - 80, pane);
	    else
	        miceLeft = showInfo("Catch the gold mouse!", Main.WIDTH/2 - 180, pane);
	}
	
	private void displayLives(Group pane, int l){
        lives = showInfo("Health: " + Integer.toString(l), Main.WIDTH-180, pane);
	}
	
	private Label showInfo(String text, int offsetX, Group pane){
	    Label l = new Label(text);
        l.setFont(new Font("Verdana", 30));
        l.setTranslateX(offsetX);
        pane.getChildren().add(l);
		return l;
	}

	private void setItems(Snake s, int numPoison, int numMouseTrap){
        setPoison(numPoison);
        setMouseTrap(numMouseTrap);

		if(levelNumber != bossLevel)
			pane.getChildren().add(s.mouse());
        else{
            setBoss();
        }
	}
	private void setPoison(int numPoison){
        for(int i = 0; i < numPoison; i++){
            Poison p = new Poison();
            p.putTrap(pane);
            traps.put(p, p.getPois());
        }
    }
	private void setMouseTrap(int numMouseTrap){
        for(int j = 0; j < numMouseTrap; j++){
            MouseTrap m = new MouseTrap();
            m.putTrap(pane);
            traps.put(m, m.getTrap());
        }
    }
	private void setBoss(){

        for(int i = 0; i < bossCount; i++) {
            Boss boss = new Boss();
            bosses.put(boss, boss.getSnake());
            pane.getChildren().add(boss.getSnake());
            boss.getAnimator().start();
        }
    }
	private void setSnake(Snake s){

		Rectangle r = s.getSnake();

		pane.getChildren().add(s.getSnake());

		r.setTranslateX(s.getX());
		r.setTranslateY(s.getY());
	}
}
