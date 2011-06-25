package trollhoehle.gamejam.magnets;

import java.util.ArrayList;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * This class is used to spawn Obstacles. It is a PhysicalEntity since it move
 * in circular motions around the core.
 * 
 * @author Cakemix
 */
public class ObstacleSpawner extends PhysicalEntity {

    /*
     * ms-value.
     */
    protected float timeBetweenSpawns;
    protected float timeFromLastSpawn;
    protected float timeToLive;

    public ObstacleSpawner(float posX, float posY, float startSpeed, float timeBetweenSpawns, float timeToLive)
	    throws SlickException {
	super(posX, posY, new Rectangle(posX, posY, 10, 5), new Image("res/images/gun.png"), -100, startSpeed);
	this.timeBetweenSpawns = timeBetweenSpawns;
	this.timeToLive = timeToLive;
    }

    protected void calculateCircularMovement(float timePerFrame, float toCenterX, float toCenterY, float attract) {
	super.calculateCircularMovement(timePerFrame, toCenterX, toCenterY, this.speedMultiplier);
    }

    public void setRadius(float radius) {
    	// Hardcoded for now...
    	this.setCenterX((float) (this.getCenterX() - 360 + Math.cos(this.getPolarPhi()) * radius));
    	this.setCenterY((float) (this.getCenterY() - 360 - Math.sin(this.getPolarPhi()) * radius));
    }
    
    public ArrayList<Obstacle> update(float timePerFrame, float toCenterX, float toCenterY, float attract) {
	ArrayList<Obstacle> spawnedObstacles = super.update(timePerFrame, toCenterX, toCenterY, attract);

	this.getImg().setRotation(-(float) (180 * this.getPolarPhi() / Math.PI));

	this.timeFromLastSpawn += timePerFrame;
	if (timeFromLastSpawn >= timeBetweenSpawns) {
	    timeFromLastSpawn = 0;
	    try {
		if (Math.random() < 0.2) {
		    double random = Math.random();
		    if (random < 0.5) {
			spawnedObstacles.add(new PowerupInvincible(this.getCenterX(), this.getCenterY(), 1,
				GlobalSettings.getGlobalSpeed()));
		    } else if (random >= 0.5) {
			spawnedObstacles.add(new PowerupHealth(this.getCenterX(), this.getCenterY(), 1, GlobalSettings
				.getGlobalSpeed()));
		    }
		} else {
		    spawnedObstacles.add(new Obstacle(this.getCenterX(), this.getCenterY(), 1, this.getSpeed()));
		}
	    } catch (SlickException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}

	this.timeToLive -= timePerFrame;

	if (timeToLive <= 0) {
	    this.setHp(0);
	}

	return spawnedObstacles;
    }
}
