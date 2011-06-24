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

    public Player(float posX, float posY, Shape shape, Image img, int hp, float startSpeed, String name, int button) {
	super(posX, posY, shape, img, hp, startSpeed);
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
    public Player(float posX, float posY, Shape shape, Image img, int hp, float startSpeed, String name, int button, Color color) {
        this( posX,  posY,  shape,  img,  hp,  startSpeed,  name,  button);
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
	if (this.collisionTimer <= 0) {
	    this.setHp(this.getHp() - 1);
	    this.collisionTimer = TIME_BETWEEN_COLLISIONS;
	}

	return null;
    }

}
