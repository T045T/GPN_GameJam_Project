package trollhoehle.gamejam.magnets;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;

/**
 * A {@link Player} is a {@link PhysicalEntity} with a String as name and an
 * integer-value defining the one button which controls this {@link Player}.
 * 
 * @author Cakemix
 * 
 */
public class Player extends PhysicalEntity {

    private String name;
    private int button;

    public Player(float posX, float posY, Shape shape, Image img, int hp, float startSpeed, String name, int button) {
	super(posX, posY, shape, img, hp, startSpeed);
	this.name = name;
	this.button = button;
    }

    public String getName() {
	return this.name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public int getButton() {
	return this.button;
    }

}
