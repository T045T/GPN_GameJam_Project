package trollhoehle.gamejam.magnets;

import java.util.Random;

import org.newdawn.slick.Color;
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

    /**
     * ms-value
     */
    private static final float TIME_BETWEEN_COLLISIONS = 1500f;
    private float collisionTimer;
    private String name;
    private int button;
    private Color color = new Color(255,255,255);

    public Player(float posX, float posY, Shape shape, Image img, int hp, float startSpeed, String name, int button, float speedMultiplier) {
	super(posX, posY, shape, img, hp, startSpeed, speedMultiplier);
	this.collisionTimer = 0;
	this.name = name;
	this.button = button;
	Random rand = new Random();

	float r = rand.nextFloat();
	float g = rand.nextFloat();
	float b = rand.nextFloat();

	this.color = new Color(r, g, b);
	this.color.brighter(1000);
    }
    public Player(float posX, float posY, Shape shape, Image img, int hp, float startSpeed, String name, int button, Color color, float speedMultiplier) {
        this( posX,  posY,  shape,  img,  hp,  startSpeed,  name,  button, speedMultiplier);
        this.color = color;

    }
    
    public Color getColor() {
    	return color;
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
	    this.collisionTimer -= timePerFrame;
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
