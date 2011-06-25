package trollhoehle.gamejam.magnets;

import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;

/**
 * Basic class for everything that can be displayed. Has an {@link Image} which
 * will be drawn every time the update()-method is called. Also supports
 * collision with help of this Entities {@link Shape}.
 * 
 * @author Cakemix
 * 
 */
public abstract class Entity {

    private Image img;
    private Shape shape;
    private float scale;
    protected float speedMultiplier;

    /**
     * 
     * @param posX
     *            Upper-left-x-pos
     * @param posY
     *            Upper-left-y-pos
     * @param shape
     * @param img
     */
    public Entity(float posX, float posY, Shape shape, Image img) {
	this.shape = shape;
	this.img = img;
	this.shape.setCenterX(posX);
	this.shape.setCenterY(posY);
	this.speedMultiplier = GlobalSettings.getGlobalSpeed();
	this.scale = 1;
    }

    /**
	 * Scales the associated Shape and Image to the factor provided. BUGGY!!!
	 * @param scale the factor to which you want to scale - 1.0 is the original size
	 */
	public void setScale(float scale) {
		float tempScale = scale / this.scale;
		System.out.println(tempScale);
		float posX = this.getX();
		float posY = this.getY();
		this.shape = this.getShape().transform(Transform.createScaleTransform(tempScale, tempScale));
		this.shape.setX(posX - (this.shape.getWidth() / 2));
		this.shape.setY(posY - (this.shape.getHeight() / 2));
		this.scale = scale;
	}

	/**
	 * Returns the current scale of this Entity, based on the size it was
	 * when it was created.
	 * @return the current scale of this Entity, based on the size it was
	 * when it was created.
	 */
	public float getScale() {
		return this.scale;
	}
	
    /**
     * Draws this Entitie's {@link Image} at this Entitie's position.
     */
    public void draw() {
	this.img.draw(this.getX(), this.getY(), this.shape.getHeight() / (float) this.img.getHeight());
    }

    public Image getImg() {
	return img;
    }

    public Shape getShape() {
	return shape;
    }

    public float getCenterX() {
	return this.shape.getCenterX();
    }

    public float getCenterY() {
	return this.shape.getCenterY();
    }

    public float getX() {
	return this.shape.getX();
    }

    public float getY() {
	return this.shape.getY();
    }

    public float getSpeedMultiplier() {
	return this.speedMultiplier;
    }

    public void setSpeedMultiplier(float speedMultiplier) {
	this.speedMultiplier = speedMultiplier;
    }

    /**
     * Returns the radius of the circle completely encloses this Entity's Shape.
     * 
     * @return
     */
    public float getRadius() {
	return this.shape.getBoundingCircleRadius();
    }

    public void setImg(Image img) {
	this.img = img;
    }

    public void setShape(Shape shape) {
	this.shape = shape;
    }

    /**
     * Sets this Entity's center-x-position.
     * 
     * @param x
     *            The given value.
     */
    public void setCenterX(float x) {
	this.shape.setCenterX(x);
    }

    /**
     * Sets this Entity's center-y-position.
     * 
     * @param y
     *            The given value.
     */
    public void setCenterY(float y) {
	this.shape.setCenterY(y);
    }

    /**
     * Sets this Entity's upper-left-x-position
     * 
     * @param x
     */
    public void setX(float x) {
	this.shape.setX(x);
    }

    /**
     * Sets this Entity's upper-left-y-position
     * 
     * @param y
     */
    public void setY(float y) {
	this.shape.setY(y);
    }

    /**
     * This method calculates everything which needs to be calculated every
     * frame.
     * 
     * @param timePerFrame
     *            The time between the next and the last frame.
     * @param toCenterX
     *            The absolute distance to the center of the screen on the
     *            x-axis.
     * @param toCenterY
     *            The absolute distance to the center of the screen on the
     *            y-axis.
     * @param attract
     *            The strength with which the core attracts objects.
     * @return An array of Obstacles which has been spawned in this Entity's
     *         update-method.
     */
    public abstract ArrayList<Obstacle> update(float timePerFrame, float toCenterX, float toCenterY, float attract);

    /**
     * This method is called when this entity collides with another Entity.
     * 
     * @param collider
     *            The "other" Entity.
     * @return
     */
    public abstract Obstacle[] collision(Entity collider);
}
