package trollhoehle.gamejam.magnets;

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
    private float speed;

    public PhysicalEntity(float posX, float posY, Shape shape, Image img, int hp, float startSpeed) {
	super(posX, posY, shape, img);
	this.hp = hp;
	this.speed = startSpeed;
    }

    /**
     * Sets the health of this PhysicalEntity to a given value.
     * 
     * @param hp
     */
    public void setHp(int hp) {
	this.hp = hp;
    }

    /**
     * Returns the amount of health this PhysicalEntity has.
     * 
     * @return
     */
    public int getHp() {
	return this.hp;
    }

    public Obstacle[] update(float timePerFrame, float toCenterX, float toCenterY, float attract) {
	// calculate new position
	this.calculateCircularMovement(timePerFrame, toCenterX, toCenterY, attract);

	// draw image to a new position
	this.getImg().draw(this.getX(), this.getY());

	return null;
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
    private void calculateCircularMovement(float timePerFrame, float toCenterX, float toCenterY, float attract) {
	// from Cartesian to Radial
	float transX = this.getX() - toCenterX;
	float transY = toCenterY - this.getY();
	float phi = (float) Math.atan2(transY, transX);
	float radius = (float) Math.sqrt(Math.pow(transX, 2) + Math.pow(transY, 2));

	// calculate new values
	phi += speed * timePerFrame / radius;

	// TODO: Just demo-code!
	if (radius > 300)
	    radius = 50;
	// TODO: ---till here
	if (attract == 0)
	    radius += 0.1 * timePerFrame;
	else
	    radius -= attract * timePerFrame;

	// back from Radial to Cartesian and save

	this.setX((float) (toCenterX + Math.cos(phi) * radius));
	this.setY((float) (toCenterY - Math.sin(phi) * radius));
    }
}
