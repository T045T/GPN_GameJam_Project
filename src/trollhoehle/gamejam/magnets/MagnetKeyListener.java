package trollhoehle.gamejam.magnets;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.particles.ParticleSystem;
import org.newdawn.slick.particles.ParticleEmitter;


public class MagnetKeyListener implements KeyListener {
    private ArrayList<Player> players;
    private ParticleSystem system;
    private Entity core;
    int counter = 0;

    int pressedButton = 0;

    public MagnetKeyListener(ArrayList<Player> players, ParticleSystem system, Entity core) {
	this.players = players;
	this.system = system;
	this.core = core;
	this.counter = 0;
	this.pressedButton = 0;
    }

    public void inputEnded() {
	// TODO Auto-generated method stub

    }

    public void inputStarted() {
	// TODO Auto-generated method stub

    }

    public boolean isAcceptingInput() {
	// TODO Auto-generated method stub
	return true;
    }

    public void setInput(Input arg0) {
	// TODO Auto-generated method stub

    }

    public void keyPressed(int arg0, char arg1) {
	// TODO Auto-generated method stub
	boolean found = false;
	for (Player player : this.players) {
	    if (player.getButton() == arg0) {
		found = true;
	    }
	}

	if (!found) {
	    if (this.pressedButton == arg0) {
		if (counter > 0) {
		    counter = 0;

		    try {
		    	Player tempPlayer = new Player(200, 200, new Circle(200, 200, 20), new Image(
				"res/images/magnet_inactive.png"), 0.25f, "Trollspieler " + arg1, arg0, 0.15f);
		    	
				system.addEmitter(new MagnetoParticleEmitter(tempPlayer, core));
				players.add(tempPlayer);
		    } catch (SlickException e) {

		    }
		} else {
		    this.pressedButton = arg0;
		    counter++;
		}
	    } else {
		this.pressedButton = arg0;
		counter++;
	    }
	}
    }

    public void keyReleased(int arg0, char arg1) {
	// TODO Auto-generated method stub

    }

}
