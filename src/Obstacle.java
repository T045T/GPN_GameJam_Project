public class Obstacle extends PhysicalEntity {

    /**
     * Creates a new {@link Obstacle} with given start-values. All given values
     * are considered to be radial-values.
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
    public Obstacle(float distanceRadius, float sizeRadius, float phi, float speed) {
	super(distanceRadius, sizeRadius, phi, speed);
    }
}
