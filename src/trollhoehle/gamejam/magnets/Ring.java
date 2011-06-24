package trollhoehle.gamejam.magnets;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;

/**
 * This class is a rotating ring used as boundary for the game.
 * 
 * @author Cakemix
 * 
 */
public class Ring extends Entity {

    public Ring(float posX, float posY, Shape shape, Image img) {

	// TODO: directly create shape and image here (maybe)
	super(posX, posY, shape, img);
    }

    public Obstacle[] update(float timePerFrame, float toCenterX, float toCenterY, float attract) {
	// TODO: ROTAAAAAAATEEEEEEEEEEE! :O
	return null;
    }

}
