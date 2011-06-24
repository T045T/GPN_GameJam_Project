package trollhoehle.gamejam.magnets;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;

/**
 * The core is the .. core! .. in the middle of the game. This class has extra
 * private methods for awesome special effects like a pulsing-effect or the
 * spawning of Obstacles.
 * 
 * @author Cakemix
 * 
 */
public class Core extends Entity {

    private static final float OBSTACLE_SPEED = 0.1f;
    /**
     * ms-value.
     */
    private float timeBetweenSpawns;
    private float timeFromLastSpawn;
    private float spawnAngle;

    public Core(float screenWidth, float screenHeight) throws SlickException {
	super(screenWidth / 2, screenHeight / 2, new Circle(screenWidth / 2 - 25, screenHeight / 2 - 25, 50),
		new Image("res/images/core.png"));
	this.timeBetweenSpawns = 3000f;
	this.timeFromLastSpawn = 0f;
	this.spawnAngle = 0;
    }

    @Override
    public Obstacle[] update(float timePerFrame, float toCenterX, float toCenterY, float attract) {
	Obstacle spawnedObstacles[];

	this.pulse(timePerFrame);

	this.spawnAngle += 0.001f * timePerFrame;
	if (this.spawnAngle >= 2 * Math.PI) {
	    this.spawnAngle = 0;
	}

	spawnedObstacles = this.spawn(timePerFrame);

	return spawnedObstacles;
    }

    private Obstacle[] spawn(float timePerFrame) {
	Obstacle spawnedObstacles[] = null;
	if (this.timeFromLastSpawn >= this.timeBetweenSpawns) {
	    spawnedObstacles = new Obstacle[3];
	    try {
		spawnedObstacles[0] = new Obstacle((float) (this.getShape().getCenterX() + 80 + this.getRadius()
			* Math.cos(this.spawnAngle + Math.PI * 2 / 3)),
			(float) (this.getShape().getCenterY() + 80 + this.getRadius()
				* Math.sin(this.spawnAngle + Math.PI * 2 / 3)), 1, OBSTACLE_SPEED);
		spawnedObstacles[1] = new Obstacle((float) (this.getShape().getCenterX() + 80 + this.getRadius()
			* Math.cos(this.spawnAngle + Math.PI * 4 / 3)),
			(float) (this.getShape().getCenterY() + 80 + this.getRadius()
				* Math.sin(this.spawnAngle + Math.PI * 4 / 3)), 1, OBSTACLE_SPEED);
		spawnedObstacles[2] = new Obstacle((float) (this.getShape().getCenterX() + 80 + this.getRadius()
			* Math.cos(this.spawnAngle)), (float) (this.getShape().getCenterY() + 80 + this.getRadius()
			* Math.sin(this.spawnAngle)), 1, OBSTACLE_SPEED);
	    } catch (SlickException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	    this.timeFromLastSpawn = 0f;
	} else {
	    this.timeFromLastSpawn += timePerFrame;
	}
	return spawnedObstacles;
    }

    private void pulse(float timePerFrame) {
	// TODO maaaaake iiiiit puuuuuuuulse! :O

    }

    @Override
    public Obstacle[] collision(Entity collider) {
	// TODO Auto-generated method stub
	return null;
    }

}
