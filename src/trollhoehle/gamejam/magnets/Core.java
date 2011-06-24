package trollhoehle.gamejam.magnets;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;

/**
 * The core is the .. core! .. in the middle of the game. This class has extra
 * private methods for awesome special effects like a pulsing-effect or the
 * spawning of Obstacles.
 * 
 * @author Cakemix
 * 
 */
public class Core extends Entity {

    public Core(float posX, float posY, Shape shape, Image img) {
	// TODO: directly create shape and image here (maybe)
	super(posX, posY, shape, img);
    }

    @Override
    public Obstacle[] update(float timePerFrame, float toCenterX, float toCenterY, float attract) {
	Obstacle spawnedObstacles[];

	this.pulse(timePerFrame);

	spawnedObstacles = this.spawn(timePerFrame);

	super.update(timePerFrame, toCenterX, toCenterY, attract);

	return spawnedObstacles;
    }

    private Obstacle[] spawn(float timePerFrame) {
	Obstacle spawnedObstacles[] = new Obstacle[0];
	// TODO maaaaaake 'em spaaaaaawn!
	return spawnedObstacles;
    }

    private void pulse(float timePerFrame) {
	// TODO maaaaake iiiiit puuuuuuuulse! :O

    }

}
