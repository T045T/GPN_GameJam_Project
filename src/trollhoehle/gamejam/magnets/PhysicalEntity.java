package trollhoehle.gamejam.magnets;

import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;

/**
 * Still pretty basic class, extending the {@link Entity}. New:
 * {@link PhysicalEntity}s have a specified amount of hitpoints and a
 * speed-value with which they can move through the game.
 * 
 * @author Cakemix
 * 
 */
public abstract class PhysicalEntity extends Entity {

	private int hp;
	protected float speed;
	protected float radius;

	public PhysicalEntity(float posX, float posY, Shape shape, Image img, int hp, float startSpeed,
			float speedMultiplier) {
		super(posX, posY, shape, img, speedMultiplier);
		this.hp = hp;
		this.speed = startSpeed;
		this.radius = this.getPolarRadius();
	}

	/**
	 * Returns the amount of health this PhysicalEntity has.
	 * 
	 * @return
	 */
	public int getHp() {
		return this.hp;
	}

	public float getSpeed() {
		return this.speed;
	}

	public float getPolarPhi() {
		float transX = this.getCenterX() - 360; // HARDCODE
		float transY = 360 - this.getCenterY(); // HARDCODE
		return (float) Math.atan2(transY, transX);
	}

	public float getPolarRadius() {
		float transX = this.getCenterX() - 360; // HARDCODE
		float transY = 360 - this.getCenterY(); // HARDCODE
		return (float) Math.sqrt(Math.pow(transX, 2) + Math.pow(transY, 2));
	}

	/**
	 * Sets the health of this PhysicalEntity to a given value.
	 * 
	 * @param hp
	 */
	public void setHp(int hp) {
		this.hp = hp;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public ArrayList<Obstacle> update(float timePerFrame, float toCenterX, float toCenterY, float attract) {
		// calculate new position
		this.calculateCircularMovement(timePerFrame, toCenterX, toCenterY, attract);

		return new ArrayList<Obstacle>();
	}

	/**
	 * Updates the this PhysicalEntitie's position assuming a circular movement.
	 * 
	 * 
	 * @param timePerFrame
	 *            The time between the next and the last frame.
	 * @param toCenterX
	 *            The absolute distance to the center of the screen on the
	 *            x-axis.
	 * @param toCenterY
	 *            The absolute distance to the center of the screen on the
	 *            y-axis.
	 * @param attract
	 *            The strength with which the core attracts objects.
	 */
	protected void calculateCircularMovement(float timePerFrame, float toCenterX, float toCenterY, float attract) {

		System.out.println(radius + " | " + this.getPolarRadius());
		float radiusChangeRatio = (float) Math.pow(radius / this.getPolarRadius(), 2);
		System.out.println(radiusChangeRatio);
		speed *= radiusChangeRatio;
		// from Cartesian to Radial
		float phi = this.getPolarPhi();
		radius = this.getPolarRadius();

		// calculate new values
		phi += this.getSpeed() * timePerFrame / radius;

		/*	Moved this into Obstacle - PhysicalEntity should not need to rely on knowledge of subclasses
		 * 
		if (this instanceof Obstacle) {
	    	phi -= speed * timePerFrame / radius;
		} else {
	    	phi += speed * timePerFrame / radius;
		}
		 */

		float myRadius = radius;
		// only if NOT an ObstacleSpawner
		//if (!(this instanceof ObstacleSpawner)) {
			// constantly increase radius, except attract != 0
			myRadius += (this.speedMultiplier - attract) * timePerFrame;
		//}

		// back from Radial to Cartesian and save

		this.setCenterX((float) (toCenterX + Math.cos(phi) * myRadius));
		this.setCenterY((float) (toCenterY - Math.sin(phi) * myRadius));
	}

	/**
	 * Every PhysicalEntity will lose 1 hp when colliding with another Entity.
	 */
	public Obstacle[] collision(Entity collider) {
		this.setHp(this.getHp() - 1);

		return null;
	}
}
