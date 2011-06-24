public class Obstacle extends PhysicalEntity {

    /**
     * Creates a new {@link Player} with given start-values. All given values
     * are considered to be radial-values.
     * 
     * @param x
     *            The Cartesian x-position of this Obstacle.
     * @param y
     *            The Cartesian y-position of this Obstacle.
     * @param radius
     *            The radius of this Obstacle.
     * @param speed
     *            The start-speed of this Obstacle.
     */
    public Obstacle(float x, float y, float radius, float speed) {
	super(x, y, radius, speed);
    }
}
