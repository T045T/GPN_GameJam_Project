package trollhoehle.gamejam.magnets;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Image;


public class PowerupSpeedup extends Powerup {

	public PowerupSpeedup(float posX, float posY, int hp, float startSpeed, Image img) throws SlickException {
		super(posX, posY, hp, startSpeed,img);
		// TODO Auto-generated constructor stub
	}
	public PowerupSpeedup(float posX, float posY, int hp, float startSpeed) throws SlickException {
		super(posX, posY, hp, startSpeed,new Image("res/images/fuckyea.png"));
		// TODO Auto-generated constructor stub
	}
	
	public Obstacle[] collision(Entity collider) {
		if (collider instanceof Player || collider instanceof Ring) {
			if(collider instanceof Player) {
				//((Player) collider).setCollisionTimer(1500);
				collider.setSpeedMultiplier(collider.getSpeedMultiplier()+collider.getSpeedMultiplier()*0.5f);
				GlobalSettings.getPa_speedup().playAsSoundEffect(1.0f, 1.0f, false);
			}
		    super.collision(collider);
		}
		return null;
	    }
	
}
