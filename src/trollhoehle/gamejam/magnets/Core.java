package trollhoehle.gamejam.magnets;

import java.util.ArrayList;

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

    private static final float OBSTACLE_SPAWNER_SPAWN_PROBABILITY = 20000f;
    private ArrayList<ObstacleSpawner> spawners;

    public Core(float screenWidth, float screenHeight) throws SlickException {
	super(screenWidth / 2, screenHeight / 2, new Circle(screenWidth / 2 - 25, screenHeight / 2 - 25, 50),
		new Image("res/images/core.png"));
	this.spawners = new ArrayList<ObstacleSpawner>();
	this.spawners.add(new ObstacleSpawner(this.getCenterX() + this.getRadius(), this.getCenterY(), 0.05f, 3000f,
		30000f));
    }

    public ArrayList<ObstacleSpawner> getObstacleSpawner() {
	return this.spawners;
    }

    public void setSpeedMultiplier(float speedMultiplier) {
	this.speedMultiplier = speedMultiplier;
	for (ObstacleSpawner os : this.spawners) {
	    os.setSpeedMultiplier(speedMultiplier);
	}
    }

    @Override
    public ArrayList<Obstacle> update(float timePerFrame, float toCenterX, float toCenterY, float attract) {
	this.pulse(timePerFrame);

	ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();

	// CHECK IF A NEW OBSTACLE_SPAWNER NEEDS TO BE SPAWNED
	if (Math.random() <= 0.5 * (timePerFrame / 1000)) {
	    try {
		float rndFireRate = (float) (Math.random() * 10000f);
		this.spawners.add(new ObstacleSpawner(this.getCenterX() + this.getRadius(), this.getCenterY(), 0.05f,
			rndFireRate * 1.3f, rndFireRate * 10));
	    } catch (SlickException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}

	// LET THE SPAWNERS SPAWN
	for (ObstacleSpawner os : this.spawners) {
	    obstacles.addAll(os.update(timePerFrame, toCenterX, toCenterY, attract));
	}

	return obstacles;
    }

    private void pulse(float timePerFrame) {
	// TODO maaaaake iiiiit puuuuuuuulse! :O

    }

    /**
     * Draw this Core's {@link Image} at this Core's position and call the draw
     * methods of all attached {@link ObstacleSpawner}s.
     */
    public void draw() {
	super.draw();
	for (ObstacleSpawner os : this.spawners) {
	    os.draw();
	}
    }

    @Override
    public Obstacle[] collision(Entity collider) {
	// TODO Auto-generated method stub
	return null;
    }

}
