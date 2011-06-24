import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.util.FastTrig;

/**
 * Works completely with radial stuff
 * 
 * @author Cakemix
 * 
 */
public class PhysicalEntity extends Circle {

    // "omega", the anglespeed
    private float speed;

    /**
     * Creates a new {@link PhysicalEntity} with given start-values. All given
     * values are considered to be radial-values.
     * 
     * @param distanceRadius
     *            troll
     * @param sizeRadius
     *            ol
     * @param phi
     *            ranging from 0 to 2*pi
     * @param speed
     *            ololol
     */
    public PhysicalEntity(float x, float y, float radius, float speed) {
	super(x, y, radius);
	this.speed = speed;
    }

    public float getSpeed() {
	return speed;
    }

    public void setSpeed(float speed) {
	this.speed = speed;
    }

    /**
     * Calculates the new position of this {@link PhysicalEntity} in relation to
     * the time this frame took.
     * 
     * @param timePerFrame
     *            The given time per frame.
     */
    public void calculateNewPos(float timePerFrame, float toCenterX, float toCenterY) {
	// from Cartesian to Radial
	float transX = x - toCenterX;
	float transY = toCenterY - y;
	float phi = (float) Math.atan2(transY, transX);
	float radius = (float) Math.sqrt(Math.pow(transX, 2) + Math.pow(transY, 2));

	// calculate new values
	// phi += speed * timePerFrame
	phi += 0.1;
	/*
	if (phi < -Math.PI) {
	    phi = (float) (Math.PI + phi);
	} else if (phi > Math.PI) {
	    phi = (float) (Math.PI - phi);
	}*/

	// back from Radial to Cartesian and save
	this.setCenterX((float) (Math.cos(phi) * radius) + toCenterX);
	this.setCenterY((float) (-Math.sin(phi) * radius) + toCenterY);
    }

}
