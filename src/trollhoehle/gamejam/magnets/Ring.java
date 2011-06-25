package trollhoehle.gamejam.magnets;

import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;

/**
 * This class is a rotating ring used as boundary for the game.
 * 
 * @author Cakemix
 * 
 */
public class Ring extends Entity {

    public Ring(float posX, float posY, float radius) throws SlickException {
	super(posX, posY, new Circle(posX, posY, radius - 85), new Image("res/images/testRing.png"));
    }
    
    public void draw(){
	this.getImg().draw(0, 0,(float) ((float) 720 / this.getImg().getHeight()));
	this.getImg().setCenterOfRotation(720 / 2, 720 / 2);
    }

    public ArrayList<Obstacle> update(float timePerFrame, float toCenterX, float toCenterY, float attract) {

	this.getImg().rotate(-0.02f * timePerFrame - this.speedMultiplier);
	return new ArrayList<Obstacle>();
    }

    @Override
    public Obstacle[] collision(Entity collider) {
	// TODO Auto-generated method stub
	return null;
    }

}
