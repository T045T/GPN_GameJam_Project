package trollhoehle.gamejam.magnets;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Image;


public class PowerupInvincible extends Powerup {

	public PowerupInvincible(float posX, float posY, int hp, float startSpeed, Image img) throws SlickException {
		super(posX, posY, hp, startSpeed,img);
		// TODO Auto-generated constructor stub
	}
	public PowerupInvincible(float posX, float posY, int hp, float startSpeed) throws SlickException {
		super(posX, posY, hp, startSpeed,new Image("res/images/lulz.png"));
		// TODO Auto-generated constructor stub
	}
	
	public Obstacle[] collision(Entity collider) {
		if (collider instanceof Player || collider instanceof Ring) {
			if(collider instanceof Player) {
				((Player) collider).setCollisionTimer(1500);
				GlobalSettings.getPa_invincible().playAsSoundEffect(1.0f, 1.0f, false);
			}
		    super.collision(collider);
		}
		return null;
	    }
	
}
