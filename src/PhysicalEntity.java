import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
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
    private Image img;

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
    public PhysicalEntity(float x, float y, float radius, float speed, Image img) {
	super(x, y, radius);
	this.speed = speed;
	this.img = img;
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
    public void update(float timePerFrame, float toCenterX, float toCenterY, float attract) {
	// from Cartesian to Radial
	float transX = getCenterX() - toCenterX;
	float transY = toCenterY - getCenterY();
	float phi = (float) Math.atan2(transY, transX);
	float radius = (float) Math.sqrt(Math.pow(transX, 2) + Math.pow(transY, 2));
	
	// calculate new values
	phi += speed * timePerFrame / radius;
	if (radius > 300)
		radius = 50;
	if (attract == 0)
		radius += 0.1 * timePerFrame; 
	else
		radius -= attract *timePerFrame;
	//phi += 0.07;
	/*
	if (phi < -Math.PI) {
	    phi = (float) (Math.PI + phi);
	} else if (phi > Math.PI) {
	    phi = (float) (Math.PI - phi);
	}*/

	// back from Radial to Cartesian and save
	
	this.setCenterX((float)( toCenterX + Math.cos(phi) * radius));
	this.setCenterY((float)( toCenterY - Math.sin(phi) * radius));
    }

}
