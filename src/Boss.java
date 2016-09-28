import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;

/**
 * Purpose: This is the boss class which is responsible for designating
 * the size, movement and location of the Boss object. This is used in the boss level of the game.
 * Assumptions: The user will start the AnimationTimer object automatically in the House.java class.
 * Obviously, if Boss is null the game will break down.
 * Dependencies: none
 * Example: Boss b = new Boss(); b.getAnimator().start(); to create and animate boss.
 *
 * Extends snake class. Movements randomly in different directions.
 * Random numbers are generated using Math.random().
 * If goes out of bounds, it will bounce back.
 *
 * @author William Xiong
 */
public class Boss extends Snake{

    private enum Direction{
        LEFT, RIGHT, UP, DOWN
    }

	private static final double BOSS_STEP = 3.9;

    /**
     * Calls super's init method to initialize variables.
     * The width and height will always be constant, and are never used elsewhere
     * in this class. The x and y coordinates are random every time.
     * The color is constant and is never used elsewhere in this class.
     */
	public Boss(){
	    super();
		super.init((int) Item.getRandX(), (int) Item.getRandY(), 10, 10, Color.GOLD);
	}

	@Override
    public AnimationTimer getAnimator(){
        return animate;
    }

    private AnimationTimer animate = new AnimationTimer(){
    	@Override
    	public void handle(long arg0){
    		checkBounds();
            if(Math.random() < 0.003){
                snake.setTranslateY(Item.getRandX());
                snake.setTranslateX(Item.getRandY());
            }
    		if(Math.random() < 0.16){
    			double a = Math.random();
                if(a < 0.25){
                    setDirections(Direction.DOWN);
                }
                else if(a > 0.25 && a < 0.5){
                    setDirections(Direction.UP);
                }
                else if(a > 0.5 && a < 0.75){
                    setDirections(Direction.LEFT);
                }
                else{
                    setDirections(Direction.RIGHT);
                }
    		}

            move(BOSS_STEP);
        }
    };

    /**
     * Sets the boolean directions (right, left, up, down) based on
     * the Direction input.
     * @param d the direction to change to
     */
    protected void setDirections(Direction d){
        if(d.equals(Direction.RIGHT)) {
            this.right = true;
            left = up = down = false;
        }
        if(d.equals(Direction.LEFT)) {
            this.left = true;
            right = up = down = false;
        }
        if(d.equals(Direction.UP)) {
            this.up = true;
            down = right = left = false;
        }
        if(d.equals(Direction.DOWN)) {
            this.down = true;
            left = right = up = false;
        }
    }

    /**
     * move rectangle horizontally. Does not fire laser.
     * @param moveDistance distance to move
     * @param laserLoc distance from snake to place laser
     */
    @Override
    protected void moveHorizontal(double moveDistance, int laserLoc){
        locX+=moveDistance;
        snake.setTranslateX(locX);
    }

    /**
     * move rectangle vertically. Does not fire laser
     * @param moveDistance distance to translate
     * @param laserLoc distance from snake to place laser
     */
    @Override
    protected void moveVertical(double moveDistance, int laserLoc){
        locY+=moveDistance;
        snake.setTranslateY(locY);
    }

    /**
     * If rectangle out of bounds, move it in bounds, so it does not disappear
     */
    @Override
    protected void checkBounds(){
    	if(snake.getTranslateX() >= Main.WIDTH)
    		moveHorizontal(-BOSS_STEP, 10);
    	if(snake.getTranslateX() <= 0)
    		moveHorizontal(BOSS_STEP, 10);
    	if(snake.getTranslateY() >= Main.HEIGHT)
    		moveVertical(-BOSS_STEP, 10);
    	if(snake.getTranslateY() <= 0)
    		moveVertical(BOSS_STEP, 10);
    }
}