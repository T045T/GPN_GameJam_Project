package trollhoehle.gamejam.magnets;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;

public class Obstacle extends PhysicalEntity {

    public Obstacle(float posX, float posY, Shape shape, Image img, int hp, float startSpeed) {
	super(posX, posY, shape, img, hp, startSpeed);
    }

    @Override
    public Obstacle[] update(float timePerFrame, float toCenterX, float toCenterY, float attract) {
	// calculate new position
	this.calculateCircularMovement(timePerFrame, toCenterX, toCenterY, attract);

	// draw image to a new position
	this.getImg().draw(this.getX(), this.getY());

	return null;
    }

}
