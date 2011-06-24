package trollhoehle.gamejam.magnets;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;

public abstract class Entity {

    private Image img;
    private Shape shape;

    public Entity(float posX, float posY, Shape shape, Image img) {
	this.shape.setCenterX(posX);
	this.shape.setCenterY(posY);
	this.shape = shape;
	this.img = img;
    }

    public Image getImg() {
	return img;
    }

    public Shape getShape() {
	return shape;
    }

    public float getX() {
	return this.shape.getCenterX();
    }

    public float getY() {
	return this.shape.getCenterY();
    }

    public void setImg(Image img) {
	this.img = img;
    }

    public void setShape(Shape shape) {
	this.shape = shape;
    }

    /**
     * Sets this Entitie's x-position to a given value. Also updates this
     * Entitie's Shape's position.
     * 
     * @param x
     *            The given value.
     */
    public void setX(float x) {
	this.shape.setX(x);
    }

    /**
     * Sets this Entitie's y-position to a given value. Also updates this
     * Entitie's Shape's position.
     * 
     * @param y
     *            The given value.
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
