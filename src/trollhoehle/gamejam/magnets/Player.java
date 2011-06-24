package trollhoehle.gamejam.magnets;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;

/**
 * A {@link Player} is a {@link PhysicalEntity} with a String as name and an
 * integer-value defining the one button which controls this {@link Player}.
 * 
 * @author Cakemix
 * 
 */
public class Player extends PhysicalEntity {

    private static final float TIME_BETWEEN_COLLISIONS = 3.0f;
    private float collisionTimer;
    private String name;
    private int button;

    public Player(float posX, float posY, Shape shape, Image img, int hp, float startSpeed, String name, int button) {
	super(posX, posY, shape, img, hp, startSpeed);
	this.collisionTimer = 0;
	this.name = name;
	this.button = button;
    }

    public boolean isInvincible() {
	boolean isInvincible = true;

	if (collisionTimer <= 0) {
	    isInvincible = false;
	}

	return isInvincible;
    }

    public String getName() {
	return this.name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public int getButton() {
	return this.button;
    }

    public Obstacle[] update(float timePerFrame, float toCenterX, float toCenterY, float attract) {
	if (this.collisionTimer > 0) {
	    this.collisionTimer -= 1 / timePerFrame;
	}
	return super.update(timePerFrame, toCenterX, toCenterY, attract);
    }

    public Obstacle[] collision(Entity collider) {
	
    	// calculate relative angle between colliding parties
		float phi_relativ = (float) (Math.atan2(this.getCenterY() -collider.getCenterY(), collider.getCenterX() - this.getCenterX()));
		// bounce off into the opposite direction exept when hitting the Ring 
		if(!(collider instanceof Ring))
			phi_relativ += Math.PI;
		// do the bounce!
		this.setCenterX((float) (this.getCenterX() + Math.cos(phi_relativ) * 20));
		this.setCenterY((float) (this.getCenterY() - Math.sin(phi_relativ) * 20));
		
	if (collider instanceof Player) {
	}
	else{
		if (this.collisionTimer <= 0) {
	    this.setHp(this.getHp() - 1);
	    this.collisionTimer = TIME_BETWEEN_COLLISIONS;
		}
	}

	return null;
    }

}
