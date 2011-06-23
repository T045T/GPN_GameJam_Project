public class Player extends PhysicalEntity {
    private String name;

    /**
     * Creates a new {@link Player} with given start-values. All given values
     * are considered to be radial-values.
     * 
     * @param name
     *            A String defining the name of this player.
     * @param distanceRadius
     *            troll
     * @param sizeRadius
     *            ol
     * @param phi
     *            ranging from 0 to 2*pi
     * @param speed
     *            ololol
     */
    public Player(String name, float distanceRadius, float sizeRadius, float phi, float speed) {
	super(distanceRadius, sizeRadius, phi, speed);
	this.name = name;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }
}
