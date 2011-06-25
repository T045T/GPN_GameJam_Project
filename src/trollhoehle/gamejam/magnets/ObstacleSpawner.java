package trollhoehle.gamejam.magnets;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * This class is used to spawn Obstacles. It is a PhysicalEntity since it move in circular motions around the core.
 * 
 * @author Cakemix
 */
public class ObstacleSpawner extends PhysicalEntity {

	protected static final float OBSTACLE_SPEED = 0.1f;
	/**
	 * ms-value.
	 */
	protected float timeBetweenSpawns;
	protected float timeFromLastSpawn;
	protected float timeToLive;

	public ObstacleSpawner(float posX, float posY, float startSpeed, float timeBetweenSpawns, float timeToLive)
			throws SlickException {
		super(posX, posY, new Rectangle(posX, posY, 10, 5), new Image("res/images/gun.png"), -100, startSpeed, 0.08f);
		this.timeBetweenSpawns = timeBetweenSpawns;
		this.timeToLive = timeToLive;
	}

	protected void calculateCircularMovement(float timePerFrame, float toCenterX, float toCenterY, float attract) {
		super.calculateCircularMovement(timePerFrame, toCenterX, toCenterY, this.speedMultiplier);
	}

	public Obstacle[] update(float timePerFrame, float toCenterX, float toCenterY, float attract) {
		super.update(timePerFrame, toCenterX, toCenterY, attract);

		Obstacle[] spawnedObstacles = null;

		this.getImg().setRotation(-(float) (180 * this.getPolarPhi() / Math.PI));

		this.timeFromLastSpawn += timePerFrame;
		if (timeFromLastSpawn >= timeBetweenSpawns) {
			timeFromLastSpawn = 0;
			spawnedObstacles = new Obstacle[1];
			try {
				if (Math.random() < 0.1) {
					if (Math.random() < 0.5) {
						System.out.println("invincible-powerup spawned");
						spawnedObstacles[0] = new PowerupInvincible(this.getCenterX(), this.getCenterY(), 1,
								OBSTACLE_SPEED);
					} else {
						System.out.println("health-powerup spawned");
						spawnedObstacles[0] = new PowerupHealth(this.getCenterX(), this.getCenterY(), 1,
								OBSTACLE_SPEED);
					}
				} else {
					System.out.println("obstacle spawned");
					spawnedObstacles[0] = new Obstacle(this.getCenterX(), this.getCenterY(), 1, OBSTACLE_SPEED, 0.08f);
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
