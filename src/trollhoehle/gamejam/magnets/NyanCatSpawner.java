package trollhoehle.gamejam.magnets;

import org.newdawn.slick.SlickException;

public class NyanCatSpawner extends ObstacleSpawner {
    public NyanCatSpawner(float posX, float posY, float startSpeed, float timeBetweenSpawns, float timeToLive)
	    throws SlickException {
	super(posX, posY, startSpeed, timeBetweenSpawns, timeToLive);
    }
}
