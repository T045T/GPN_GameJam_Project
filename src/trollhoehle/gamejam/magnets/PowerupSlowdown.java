package trollhoehle.gamejam.magnets;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Image;


public class PowerupSlowdown extends Powerup {

	public PowerupSlowdown(float posX, float posY, int hp, float startSpeed, Image img) throws SlickException {
		super(posX, posY, hp, startSpeed,img);
		// TODO Auto-generated constructor stub
	}
	public PowerupSlowdown(float posX, float posY, int hp, float startSpeed) throws SlickException {
		super(posX, posY, hp, startSpeed,new Image("res/images/yuno.png"));
		// TODO Auto-generated constructor stub
	}
	
	public Obstacle[] collision(Entity collider) {
		if (collider instanceof Player || collider instanceof Ring) {
			if(collider instanceof Player) {
				collider.setSpeedMultiplier(collider.getSpeedMultiplier()-collider.getSpeedMultiplier()*0.5f);
			}
		    super.collision(collider);
		}
		return null;
	    }
	
}
