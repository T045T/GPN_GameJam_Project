package trollhoehle.gamejam.magnets;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Image;


public class PowerupHealth extends Powerup {

	public PowerupHealth(float posX, float posY, int hp, float startSpeed, Image img) throws SlickException {
		super(posX, posY, hp, startSpeed,img);
		// TODO Auto-generated constructor stub
	}
	
	public PowerupHealth(float posX, float posY, int hp, float startSpeed) throws SlickException {
		super(posX, posY, hp, startSpeed,new Image("res/images/lol.png"));
		// TODO Auto-generated constructor stub
	}
	
	public Obstacle[] collision(Entity collider) {
		if (collider instanceof Player || collider instanceof Ring) {
			if(collider instanceof Player) {
				((Player) collider).setHp(((Player) collider).getHp()+1);
				GlobalSettings.getPa_health().playAsSoundEffect(1.0f, 1.0f, false);
			}
		    super.collision(collider);
		}
		return null;
	    }
	
}
