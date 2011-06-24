package trollhoehle.gamejam.magnets;

import trollhoehle.gamejam.magnets.Player;

import java.util.ArrayList;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;

public class Management extends BasicGame {

    /**
     * All Entities but the Players and the ring
     */
    private ArrayList<Entity> entities;
    private Ring ring;
    private ArrayList<Player> players;

    public Management() {
	super("Fucking magnets - How do they work?");
	this.entities = new ArrayList<Entity>();
	this.players = new ArrayList<Player>();
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

    public void render(GameContainer gc, Graphics arg1) throws SlickException {
	for (Entity e : this.entities) {
	    e.getImg().draw(e.getX(), e.getY(), (float) ((float) e.getImg().getWidth()/(float) e.getShape().getWidth()));
	}


	ring.getImg().draw(0, 0, (float) ((float) gc.getHeight() / (float) ring.getImg().getHeight()));
	this.ring.getImg().setCenterOfRotation(gc.getWidth()/2, gc.getHeight()/2);


	for (Player p : this.players) {
	    p.getImg().draw(p.getX(), p.getY());
	}

    }

    @Override
    public void init(GameContainer gc) throws SlickException {

	this.ring = new Ring(gc.getWidth() / 2, gc.getHeight() / 2, gc.getWidth() / 2);

	gc.getInput().addKeyListener(new MagnetKeyListener(this.players));

	this.entities.add(new Core(gc.getWidth(), gc.getHeight()));
    }

    @Override
    public void update(GameContainer gc, int delta) throws SlickException {
	Input input = gc.getInput();

	this.ring.getImg().rotate(0.05f * delta);

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
	    p.update(delta, gc.getWidth() / 2, gc.getHeight() / 2, attract);

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
	}

	// OTHER ENTITES
	for (int i = 0; i < this.entities.size(); i++) {
	    Entity e = this.entities.get(i);

	    // NEW POSITION
	    e.update(delta, gc.getWidth() / 2, gc.getHeight() / 2, 0);

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
	}

	// KILL DEAD PHYSICAL ENTITIES
	for (int i = 0; i < this.players.size(); i++) {
	    if (this.players.get(i).getHp() <= 0) {
		this.players.remove(i);
	    }
	}
	for (int i = 0; i < this.entities.size(); i++) {
	    if (this.entities.get(i) instanceof PhysicalEntity) {
		if (((PhysicalEntity) this.entities.get(i)).getHp() <= 0) {
		    this.entities.remove(i);
		}
	    }
	}

	// TODO: particles(?)
    }

}
