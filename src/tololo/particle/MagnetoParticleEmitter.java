package tololo.particle;

import org.newdawn.slick.Image;
import org.newdawn.slick.particles.Particle;
import org.newdawn.slick.particles.ParticleEmitter;
import org.newdawn.slick.particles.ParticleSystem;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Circle;

public class MagnetoParticleEmitter implements ParticleEmitter {
	private int x;
	private int y;
	private int particleRate = 30;
	private int timer;
	private float size = 6;
	private float speed = 0.01f;
	private Circle source;
	private Circle target;

	public MagnetoParticleEmitter(Circle source, Circle target) {
		this.x = (int) (source.getX() + source.getRadius());
		this.y = (int) (source.getY() + source.getRadius());
		this.source = source;
		this.target = target;
		System.out.println("Created MagnetoEmitter with source " + source);
	}


	public boolean completed() {
		// TODO Auto-generated method stub
		return false;
	}


	public Image getImage() {
		// TODO Auto-generated method stub
		return null;
	}


	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}


	public boolean isOriented() {
		// TODO Auto-generated method stub
		return true;
	}


	public void resetState() {
		// TODO Auto-generated method stub

	}


	public void setEnabled(boolean arg0) {
		// TODO Auto-generated method stub

	}


	public void update(ParticleSystem system, int delta) {
		this.x = (int) (source.getCenterX());
		this.y = (int) (source.getCenterY());
		timer -= delta;
		if (timer <= 0) {
			timer = particleRate;
			Particle p = system.getNewParticle(this, 1500);
			p.setColor(1, 1, 1, 1);
			//Spawn particles at random location inside source circle
			p.setPosition((float) (x + (Math.random() - 0.5) * 2 * source.getRadius()), (float) (y + (Math.random() - 0.5) * 2 * source.getRadius()));
			
			p.setSize(size);


			p.setOriented(true);
			//p.setVelocity(target.getCenterX() - this.x, target.getCenterY() - this.y ,delta * speed);
		}
	}


	private float[] getDirection(Particle p, int delta) {
		float[] targetCenter = target.getCenter();
		float targetDistance = getTargetDistance(p.getX(), p.getY());
		float vx = (targetCenter[0] - this.x) / targetDistance * speed;
		float vy = (targetCenter[1] - this.y) / targetDistance * speed;
		return new float[] {vx, vy};
	}


	private float getTargetDistance(float x, float y) {
		//return (float) Math.sqrt((target.getCenterX() - x) * (target.getCenterX() - x)) + (target.getCenterY() - y) * (target.getCenterY() - y);
		return getDistance(x, y, target.getCenterX(), target.getCenterY());
	}
	
	private float getDistance(float x1, float y1, float x2, float y2) {
		return (float) Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
	}


	public void updateParticle(Particle p, int delta) {
		if (getTargetDistance(p.getX(), p.getY()) <= target.getRadius() || getTargetDistance(x, y) < getDistance(x, y, p.getX(), p.getY())) {
			p.kill();
			return;
		}
		/*
		float[] dir = getDirection(p, delta);
		float vx = (float) (dir[0] + (Math.random() - 0.5) * 0.1f);
		float vy = (float) (dir[1] + (Math.random() - 0.5) * 0.1f);

		p.setVelocity(dir[0], dir[1], delta * speed);
		//p.setVelocity(vx,vy, delta * speed);
		 */

		p.setVelocity(target.getCenterX() - p.getX(), target.getCenterY() - p.getY() ,delta * (speed ));//+ 1 / getTargetDistance(p.getX(), p.getY())));
	}


	public boolean useAdditive() {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean usePoints(ParticleSystem arg0) {
		// TODO Auto-generated method stub
		return false;
	}


	public void wrapUp() {
		// TODO Auto-generated method stub

	}

}
