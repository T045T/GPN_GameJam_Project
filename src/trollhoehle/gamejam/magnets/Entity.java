package trollhoehle.gamejam.magnets;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;

/**
 * Basic class for everything that can be displayed. Has an {@link Image} which
 * will be drawn every time the update()-method is called. Also supports
 * collision with help of this Entitie's {@link Shape}.
 * 
 * @author Cakemix
 * 
 */
public abstract class Entity {

    private Image img;
    private Shape shape;

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

    /**
     * Returns the radius of the circle completely encloses this Entitie's
     * Shape.
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
     * Sets this Entitie's center-x-position.
     * 
     * @param x
     *            The given value.
     */
    public void setCenterX(float x) {
	this.shape.setCenterX(x);
    }

    /**
     * Sets this Entitie's center-y-position.
     * 
     * @param y
     *            The given value.
     */
    public void setCenterY(float y) {
	this.shape.setCenterY(y);
    }

    /**
     * Sets this Entitie's upper-left-x-position
     * 
     * @param x
     */
    public void setX(float x) {
	this.shape.setX(x);
    }

    /**
     * Sets this Entitie's upper-left-y-position
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
     * @return An array of Obstacles which has been spawned in this Entitie's
     *         update-method.
     */
    public abstract Obstacle[] update(float timePerFrame, float toCenterX, float toCenterY, float attract);

}
