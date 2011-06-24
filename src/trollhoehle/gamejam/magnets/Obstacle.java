package trollhoehle.gamejam.magnets;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;

/**
 * The class {@link Obstacle} is a PhysicalEntity which is, obviously, not a
 * Player.
 * 
 * @author Cakemix
 * 
 */
public class Obstacle extends PhysicalEntity {

    public Obstacle(float posX, float posY, Shape shape, Image img, int hp, float startSpeed) {
	super(posX, posY, shape, img, hp, startSpeed);
    }
}
