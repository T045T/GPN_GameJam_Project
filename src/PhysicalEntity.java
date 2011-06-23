import org.newdawn.slick.geom.Circle;

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
    public void calculateNewPos(float timePerFrame) {
	// from Cartesian to Radial
	float phi = (float) Math.atan(x / y);
	float radius = (float) (Math.cos(phi) / this.getCenterX());

	// calculate new values
	phi += speed * timePerFrame;
	if (phi < 0) {
	    phi = (float) (2 * Math.PI + phi);
	} else if (phi > Math.PI) {
	    phi = (float) (Math.PI - phi);
	}

	// back from Radial to Cartesian and save
	this.setCenterX((float) (Math.cos(phi) * radius));
	this.setCenterY((float) (Math.sin(phi) * radius));
    }

}
