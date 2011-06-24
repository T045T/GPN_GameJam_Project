package trollhoehle.gamejam.magnets;

import org.newdawn.slick.SlickException;

public class PowerupInvincible extends Powerup {

	public PowerupInvincible(float posX, float posY, int hp, float startSpeed) throws SlickException {
		super(posX, posY, hp, startSpeed);
		// TODO Auto-generated constructor stub
	}
	
	public Obstacle[] collision(Entity collider) {
		if (collider instanceof Player || collider instanceof Ring) {
			if(collider instanceof Player) {
				((Player) collider).setCollisionTimer(1500);
			}
		    super.collision(collider);
		}
		return null;
	    }
	
}
