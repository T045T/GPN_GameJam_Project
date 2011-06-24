public class Player extends PhysicalEntity {
    private String name;

    /**
     * Creates a new {@link Player} with given start-values. All given values
     * are considered to be radial-values.
     * 
     * @param name
     *            A String defining the name of this player.
     * @param x
     *            The Cartesian x-position of this player.
     * @param y
     *            The Cartesian y-position of this player.
     * @param radius
     *            The radius of this Player.
     * @param speed
     *            The start-speed of this Player.
     */
    public Player(String name, float x, float y, float radius, float speed) {
	super(x, y, radius, speed);
	this.name = name;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }
}
