package trollhoehle.gamejam.magnets;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;

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
    }
    
    
}
