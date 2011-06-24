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
	private int particleRate = 100;
	private int timer;
	private float size = 15;
	private float speed = 0.008f;
	private Circle source;
	private Circle target;
	
	public MagnetoParticleEmitter(Circle source, Circle target) {
		this.x = (int) (source.getX() + source.getRadius());
		this.y = (int) (source.getY() + source.getRadius());
		this.source = source;
		this.target = target;
		System.out.println("Created MagnetoEmitter with source " + source);
	}
	
	@Override
	public boolean completed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isOriented() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void resetState() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setEnabled(boolean arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(ParticleSystem system, int delta) {
		this.x = (int) (source.getX() + source.getRadius());
		this.y = (int) (source.getY() + source.getRadius());
		timer -= delta;
		if (timer <= 0) {
			timer = particleRate;
			Particle p = system.getNewParticle(this, 1500);
			p.setColor(1, 1, 1, 0.8f);
			//Spawn particles at random location inside source circle
			p.setPosition((float) (x + (Math.random() - 0.5) * 2 * source.getRadius()), (float) (y + (Math.random() - 0.5) * 2 * source.getRadius()));
			p.setSize(size);
			/*
			float[] dir = getDirection(p);
			float vx = (float) (dir[0] + (Math.random() - 0.5) * 0.1f);
			float vy = (float) (dir[1] + (Math.random() - 0.5) * 0.1f);
			*/
			p.setOriented(true);
			p.setVelocity(target.getX() + target.getRadius() - this.x,target.getY() + target.getRadius() - this.y ,speed);
		}
	}

	/*
	private float[] getDirection(Particle p) {
		float[] targetMax = target.getCenter();
		float targetDistance = getTargetDistance(p.getX(), p.getY());
		float vx = (targetMax[0] - this.x) / targetDistance * speed;
		float vy = (targetMax[1] - this.y) / getTargetDistance(p.getX(), p.getY()) * speed;
		return new float[] {vx, vy};
	}
	*/
	
	private float getTargetDistance(float x, float y) {
		return (float) Math.sqrt((target.getX() + target.getRadius() - x) * (target.getX() + target.getRadius() - x)) + (target.getY() + target.getRadius() - y) * (target.getY() + target.getRadius() - y);
	}
	
	@Override
	public void updateParticle(Particle p, int delta) {
		if (getTargetDistance(p.getX(), p.getY()) <= target.getRadius()) {
			p.kill();
			return;
		}
		/*
		float[] dir = getDirection(p);
		float vx = (float) (dir[0] + (Math.random() - 0.5) * 0.1f);
		float vy = (float) (dir[1] + (Math.random() - 0.5) * 0.1f);
		p.setVelocity(vx,vy,1.1f);
		*/
		p.setVelocity(target.getX() + target.getRadius() - p.getX(), target.getY() + target.getRadius() - p.getY() ,speed + 1 / getTargetDistance(p.getX(), p.getY()));
	}

	@Override
	public boolean useAdditive() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean usePoints(ParticleSystem arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void wrapUp() {
		// TODO Auto-generated method stub
		
	}

}
