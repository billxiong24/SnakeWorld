import java.util.HashMap;
import java.util.HashSet;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.*;

/**
 * Purpose: The main snake class that the user controls.
 * This class contains the main game loop, since the only thing moving
 * is the Snake object, and any other subclasses that extend the Snake class.
 * This class also contains an EventHandler instance variable for key inputs as well,
 * since the only thing being controlled is the Snake object. All interactions with Item
 * objects are also contained in this class, since the Snake object is the only
 * object that interacts with said Item objects.
 *
 * Assumptions: the main game loop is in this class. Every time the scene is switched,
 * the animation must be stopped.
 *
 * Dependencies: none
 *
 * Example: Snake s = new Snake(); to create a new Snake object.
 * s.getAnimator().start(); will make the snake move.
 *
 *
 * @author William Xiong
 */
public class Snake{

	private static final double STEP = 3.0;
	private final int miceGoal = 3;
	private final int maxLives = 50;

	protected Rectangle snake;
    protected Laser laser;
	private Mouse mouse;

	private int mouseCount;

	private int numLives = maxLives;
    private boolean paused;

	protected House house;
    private Color snakeColor;
	
	private HashSet<Rectangle> rects;

	protected boolean right, left, up, down, space;
	protected double locX, locY;

    /**
     * Calling animate.start() will cause the function "handle" to
     * loop continously. This is essentially the game loop.
     */
    private AnimationTimer animate=new AnimationTimer(){
        @Override
        public void handle(long arg0){
            checkDeaths();
            updateInfo();
            move(STEP);
            if(house.currLevel() > 1 && Math.random() < 0.005)
                mouse.randCoord();

            if(snake.getBoundsInParent().intersects(mouse.getMouse().getBoundsInParent()))
                checkMouse();
        }
    };

    /**
     * To check for key inputs, and act accordingly.
     */
    private EventHandler<KeyEvent> keyListener = new EventHandler<KeyEvent>() {
        public void handle(KeyEvent e){
            cheat(e);
            setDirections(e.getCode());
            if(e.getCode() == KeyCode.SPACE)
                space = true;
        }
    };

    /**
     * This default constructor exists so it can
     * be called from sub class, so that the subclass
     * does not unecessarily pass in a House object.
     */
    public Snake(){}

    /**
     * This constructor calls the init method to initialize instance variables.
     * This specific class will instantiate a Rectangle object in the same location every time.
     * The starting x and y locations are constant and are never used elsewhere in this class.
     * The width, height and color of the rectangle are constant and are never used elsewhere in this class.
     *
     * @param h House object that the Snake resides in. Passing in a House object is necessary
     *          because the Snake resides inside the House's Scene instance variable, and as the
     *          Snake interacts with other Node objects, elements in the House's Scene instance variable
     *          must be updated.
     */
	public Snake(House h){
	    house = h;
		init(10, 40, 10, 10, Color.BLACK);
	}

	public int getMaxLives(){
        return maxLives;
    }
    public Rectangle getSnake(){
        return snake;
    }
    public double getX(){
        return locX;
    }
    public double getY(){
        return locY;
    }
    public Rectangle mouse(){
        return mouse.getMouse();
    }

    public EventHandler<KeyEvent> getControls(){
        return keyListener;
    }
    public AnimationTimer getAnimator(){
        return animate;
    }
    public int getMiceGoal(){
        return miceGoal;
    }
    public House getHouse(){
        return house;
    }

    /**
     * Initializes all instance variables.
     * @param x starting x location
     * @param y starting y location
     * @param w width of rectangle
     * @param h height of rectangle
     * @param color color of rectangle
     */
    protected void init(int x, int y, int w, int h, Color color){
		snake = new Rectangle(w, h, color);
		mouse = new Mouse(30, 20, Item.getRandX(), Item.getRandY(), Color.GREEN);
		mouseCount = 0;
		snakeColor = Color.BLACK;
		locX = x;
		locY = y;
		right = left = up = space = false;
		down = true;
		rects = new HashSet<>();
	}

    /**
     * changes the location of snake variable in the appropriate direction.
     * @param distance the distance to move
     */
    protected void move(double distance){
        if(right)
            moveHorizontal(distance, 60);
        if(left)
            moveHorizontal(-distance, -250);
        if(up)
            moveVertical(-distance, -250);
        if(down)
            moveVertical(distance, 60);
    }

    /**
     * relocate snake horizontally
     * @param moveDistance distance to move
     * @param laserLoc distance from snake to place laser
     */
    protected void moveHorizontal(double moveDistance, int laserLoc){
        locX+=moveDistance;
        shootLaser(locX + laserLoc, locY);
        house.getPane().getChildren().add(makeTail(locX -moveDistance, locY));
        snake.setTranslateX(locX);
    }

    /**
     * relocation snake vertically
     * @param moveDistance distance to translate
     * @param laserLoc distance from snake to place laser
     */
    protected void moveVertical(double moveDistance, int laserLoc){
        locY+=moveDistance;
        shootLaser(locX, locY + laserLoc);
        house.getPane().getChildren().add(makeTail(locX, locY - moveDistance));
        snake.setTranslateY(locY);
    }

    /**
     * Sets the boolean values (right, left, up, down) based on KeyCode input.
     * @param d the KeyCode which was pressed
     */
    protected void setDirections(KeyCode d){
        if(d.equals(KeyCode.RIGHT)) {
            this.right = true;
            left = up = down = false;
        }
        if(d.equals(KeyCode.LEFT)) {
            this.left = true;
            right = up = down = false;
        }
        if(d.equals(KeyCode.UP)) {
            this.up = true;
            down = right = left = false;
        }
        if(d.equals(KeyCode.DOWN)) {
            this.down = true;
            left = right = up = false;
        }
    }

    /**
     * Check if location of snake is in bounds. If not, go to game over scene.
     */
    protected void checkBounds(){
        if(snake.getTranslateX() >= Main.WIDTH || snake.getTranslateX() <= 0)
            end(true);
        if(snake.getTranslateY() >= Main.HEIGHT || snake.getTranslateY() <= 0)
            end(true);
    }

    private void checkDeaths(){
        checkCollisions(snake);
        checkTraps();
    }
    private void shootLaser(double x, double y){
        if(space){
            if(right || left)
                laser = new Laser(x, y, Laser.LASER_LENGTH, snake.getHeight(), Color.RED);
            else
                laser = new Laser(x, y, snake.getWidth(), Laser.LASER_LENGTH, Color.RED);

            laser.fire(house.getPane());
            checkShot();
            space = false;
        }
    }

    private void checkShot(){
        if(intersects(laser.getLaser(), mouse.getMouse(), 20))
            checkMouse();
    }
    private void cheat(KeyEvent e){
        if(e.getCode() == KeyCode.Q)
            quit();
        else if(e.getCode() == KeyCode.P)
            pause();
        //skip to boss level
        else if(e.getCode() == KeyCode.B)
           goToBoss();

        else if(e.getCode() == KeyCode.R)
            numLives += 20;
    }

    private void goToBoss(){
        animate.stop();
        SceneControl.switchToHouse(House.bossLevel, house.getDifficulty());
    }
    private void pause(){
        paused = !paused;
        if(paused)
            changeAnimations(true);
        else
            changeAnimations(false);
    }
    private void changeAnimations(boolean test){
        animate.stop();
        for(Boss b : house.getBosses().keySet()){
            if(test)
                b.getAnimator().stop();
            else
                b.getAnimator().start();
        }
    }
    private void quit(){
        stop();
        SceneControl.switchToMenu();
    }
    
    private void updateInfo(){
		house.getLives().setText("Health: " + Integer.toString(numLives));
		if(house.currLevel() != House.bossLevel)
			house.getMiceLeft().setText("Mice Left: " + Integer.toString(miceGoal - mouseCount));
    }
    
    private Rectangle makeTail(double x, double y){
    	
        Rectangle tail = new Rectangle(10, 10, snakeColor);
        tail.setTranslateX(x);
        tail.setTranslateY(y);
        
        rects.add(tail);
        return tail;
    }
    
    private void checkCollisions(Shape check){
    	for(Shape s : rects){
    		if(intersects(check, s, 2.5))
    			end(true);
    	}
    	//check if boss
        if(house.currLevel() == House.bossLevel)
            checkBoss();
    	checkBounds();
    }
    private void checkBoss(){
        for(Node n : house.getPane().getChildren()){
            HashMap<Boss, Rectangle> bosses = house.getBosses();
            Boss killed = hitBoss(bosses, n);
            if(killed != null) {
                removeBoss(n, killed);
                return;
            }
        }
    }
    private Boss hitBoss(HashMap<Boss, Rectangle> bosses, Node n){
        boolean test = n.getBoundsInParent().intersects(snake.getBoundsInParent());
        for(Boss b : bosses.keySet()) {
            if (bosses.get(b) == n && test) {
                return b;
            }
        }
        return null;
    }
    private void removeBoss(Node n, Boss b){
        b.getAnimator().stop();
        house.killBoss();
        house.getPane().getChildren().remove(n);
        if(house.getNumBosses() == 0)
            end(false);
    }

    private void checkTraps(){
        Item c = new Item().hit(house.getMap(), snake, house.getPane());
    	if(c != null && c instanceof Poison)
            numLives -=10;

        else if(c != null && c instanceof MouseTrap)
    	    --numLives;

        if(numLives <= 0)
            end(true);
    }
    
    private boolean intersects(Shape c, Shape d, double range){
    	return Math.abs(d.getTranslateX() - c.getTranslateX()) <= range &&
    	   Math.abs(d.getTranslateY() - c.getTranslateY()) <= range;
    }

    private void checkMouse(){
    	mouseCount++;
    	if(mouseCount == miceGoal){
    		animate.stop();
    		SceneControl.switchToHouse(house.currLevel() + 1, house.getDifficulty());
    	}
    	mouse.randCoord();
    }
    private void stop(){
        animate.stop();
        house.getPane().getChildren().removeAll();
        house.resetLevel();
    }
    private void end(boolean isDead){
        stop();
    	numLives = maxLives;
    	if(isDead)
    		SceneControl.endGame();
    	else
    		SceneControl.winGame();
    }
}