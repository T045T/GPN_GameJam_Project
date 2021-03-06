package trollhoehle.gamejam.magnets;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;

/**
 * The class {@link Obstacle} is a PhysicalEntity which is, obviously, not a
 * Player.
 * 
 * @author Cakemix
 * 
 */
public class Obstacle extends PhysicalEntity {

    public Obstacle(float posX, float posY, int hp, float startSpeed) throws SlickException {
	super(posX, posY, new Circle(posX, posY, 20), new Image("res/images/trollface.png"), hp, startSpeed);
	this.speed = -startSpeed;
    }

    public Obstacle(float posX, float posY, int hp, float startSpeed, Image img) throws SlickException {
	super(posX, posY, new Circle(posX, posY, 20), img, hp, startSpeed);
	this.speed = -startSpeed;
    }

    public Obstacle[] collision(Entity collider) {
	if (collider instanceof Player || collider instanceof Ring) {
	    super.collision(collider);
	}
	return null;
    }

}
