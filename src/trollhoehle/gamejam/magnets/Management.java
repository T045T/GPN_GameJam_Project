package trollhoehle.gamejam.magnets;

import trollhoehle.gamejam.magnets.Player;

import java.util.ArrayList;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Management extends BasicGame {

    /**
     * All Entities but the Players and the ring
     */
    private ArrayList<Entity> entities;
    private Core core;
    private Ring ring;
    private ArrayList<Player> players;
    private float currentSpeed;

    public Management() {
	super("Fucking magnets - How do they work?");
	this.entities = new ArrayList<Entity>();
	this.players = new ArrayList<Player>();
	this.currentSpeed = 1f;
    }

    /**
     * @param args
     * @throws SlickException
     */
    public static void main(String[] args) throws SlickException {
	AppGameContainer app = new AppGameContainer(new Management());

	app.setDisplayMode(720, 720, false);
	app.setTargetFrameRate(60);
	app.start();

    }

    /**
     * Sets the speed of all objects to a desired value.
     * 
     * @param currentSpeed
     */
    public void setCurrentSpeed(float currentSpeed) {
	this.currentSpeed = currentSpeed;

	this.core.setSpeedMultiplier(this.currentSpeed);
	this.ring.setSpeedMultiplier(this.currentSpeed);

	for (Entity e : this.entities) {
	    e.setSpeedMultiplier(this.currentSpeed);
	}

	for (Player p : this.players) {
	    p.setSpeedMultiplier(this.currentSpeed);
	}
    }

    public void render(GameContainer gc, Graphics arg1) throws SlickException {
	for (Entity e : this.entities) {
	    e.draw();
	}

	ring.draw();

	core.draw();

	for (Player p : this.players) {
	    p.draw(arg1);
	}

    }

    @Override
    public void init(GameContainer gc) throws SlickException {

	this.ring = new Ring(gc.getWidth() / 2, gc.getHeight() / 2, gc.getWidth() / 2, 0.08f);
	this.core = new Core(gc.getWidth(), gc.getHeight());

	gc.getInput().addKeyListener(new MagnetKeyListener(this.players));

	this.setCurrentSpeed(currentSpeed);
    }

    @Override
    public void update(GameContainer gc, int delta) throws SlickException {
	Input input = gc.getInput();

	ArrayList<Obstacle> newObstacles = new ArrayList<Obstacle>();

	// RING
	newObstacles.addAll(this.ring.update(delta, gc.getWidth() / 2, gc.getHeight() / 2, 0.08f));

	// CORE
	newObstacles.addAll(this.core.update(delta, gc.getWidth() / 2, gc.getHeight() / 2, 0.08f));

	// PLAYERS
	for (int i = 0; i < this.players.size(); i++) {
	    float attract = 0;

	    Player p = this.players.get(i);

	    // KEY STROKES:
	    if (input.isKeyDown(p.getButton())) {
		attract = 0.3f;
		p.setImg(new Image("res/images/magnet_active.png"));
		// TODO: Particles!
	    } else {
		p.setImg(new Image("res/images/magnet_inactive.png"));
		// TODO: Particles!
	    }

	    // NEW POSITION
	    newObstacles.addAll(p.update(delta, gc.getWidth() / 2, gc.getHeight() / 2, attract));

	    // COLLISION
	    for (int j = i + 1; j < this.players.size(); j++) {
		Player pc = this.players.get(j);
		if (p.getShape().intersects(pc.getShape())) {
		    p.collision(pc);
		    pc.collision(p);
		}

	    }

	    for (int j = 0; j < this.entities.size(); j++) {
		Entity ec = this.entities.get(j);

		if (p.getShape().intersects(ec.getShape())) {
		    p.collision(ec);
		    ec.collision(p);
		}
	    }

	    if (!p.getShape().intersects(this.ring.getShape())) {
		p.collision(this.ring);
		this.ring.collision(p);
	    }

	    if (p.getShape().intersects(this.core.getShape())) {
		p.collision(this.core);
		this.core.collision(p);
	    }
	}

	// OTHER ENTITES
	for (int i = 0; i < this.entities.size(); i++) {
	    Entity e = this.entities.get(i);

	    // NEW POSITION
	    newObstacles = e.update(delta, gc.getWidth() / 2, gc.getHeight() / 2, 0);

	    if (newObstacles != null) {
		for (Entity newE : newObstacles) {
		    this.entities.add(newE);
		}
	    }

	    // COLLISION
	    for (int j = i + 1; j < this.entities.size(); j++) {
		Entity ec = this.entities.get(j);

		if (e.getShape().intersects(ec.getShape())) {
		    e.collision(ec);
		    ec.collision(e);
		}
	    }

	    if (!e.getShape().intersects(this.ring.getShape())) {
		e.collision(ring);
		ring.collision(e);
	    }
	    if (e.getShape().intersects(this.core.getShape())) {
		e.collision(core);
		core.collision(e);
	    }
	}

	// KILL DEAD PHYSICAL ENTITIES
	for (int i = 0; i < this.players.size(); i++) {
	    if (this.players.get(i).getHp() <= 0 && this.players.get(i).getHp() != -100) {
		this.players.remove(i);
	    }
	}
	for (int i = 0; i < this.entities.size(); i++) {
	    if (this.entities.get(i) instanceof PhysicalEntity) {
		if (((PhysicalEntity) this.entities.get(i)).getHp() <= 0
			&& ((PhysicalEntity) this.entities.get(i)).getHp() != -100) {
		    this.entities.remove(i);
		}
	    }
	}
	for (int i = 0; i < this.core.getObstacleSpawner().size(); i++) {
	    if (this.core.getObstacleSpawner().get(i).getHp() <= 0
		    && this.core.getObstacleSpawner().get(i).getHp() != -100) {
		this.core.getObstacleSpawner().remove(i);
	    }
	}

	// TODO: particles(?)

	// ADD ALL NEW STUFF TO ENTITIES
	entities.addAll(newObstacles);
    }

}
