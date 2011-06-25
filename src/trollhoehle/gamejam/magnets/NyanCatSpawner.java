package trollhoehle.gamejam.magnets;

import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class NyanCatSpawner extends ObstacleSpawner {
    public NyanCatSpawner(float posX, float posY, float startSpeed, float timeBetweenSpawns,float speedMultiplier) throws SlickException {
	super(posX, posY, startSpeed, timeBetweenSpawns, 0,speedMultiplier);
    }

    protected void calculateCircularMovement(float timePerFrame, float toCenterX, float toCenterY, float attract) {
	// from Cartesian to Radial
	float phi = this.getPolarPhi();
	float radius = this.getPolarRadius();

	// calculate new values
	phi += this.getSpeed() * timePerFrame / radius;

	// back from Radial to Cartesian and save

	this.setCenterX((float) (toCenterX + Math.cos(phi) * radius));
	this.setCenterY((float) (toCenterY - Math.sin(phi) * radius));
    }

    public ArrayList<Obstacle> update(float timePerFrame, float toCenterX, float toCenterY, float attract) {
	super.update(timePerFrame, toCenterX, toCenterY, attract);

	ArrayList<Obstacle> spawnedObstacles = new ArrayList<Obstacle>();

	this.getImg().setRotation((float) (180 * this.getPolarPhi() / Math.PI));

	this.timeFromLastSpawn += timePerFrame;
	if (timeFromLastSpawn >= timeBetweenSpawns) {
	    timeFromLastSpawn = 0;
	    try {
		spawnedObstacles.add(new PowerupHealth(this.getCenterX(), this.getCenterY(), 1, OBSTACLE_SPEED,
			new Image("res/images/lol.png")));
		System.out.println("health-powerup spawned");

	    } catch (SlickException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}
	return spawnedObstacles;
    }
}
