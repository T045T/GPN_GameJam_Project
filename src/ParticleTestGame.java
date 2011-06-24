
import org.newdawn.slick.AppGameContainer;
import tololo.particle.*;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.particles.ParticleSystem;
 
public class ParticleTestGame extends BasicGame{
	
	Player player;
	Player player2;
 
    Image plane = null;
    Image playerDos = null;
    Image land = null;
    Image ring = null;
    float x = 400;
    float y = 300;
    float scale = 1;
    int width;
    int height;
    private ParticleSystem system;
 
    public ParticleTestGame(int width, int height)
    {
        super("Slick2D Path2Glory - ParticleTestGame");
        this.width = width;
        this.height = height;
    }
 
    public void init(GameContainer gc)
			throws SlickException {
    	Image image = new Image("res/images/particleTest.png");
    	system = new ParticleSystem(image);
    	
		player = new Player("foo", 100, 60, (float)Math.PI/2, 0.03f);
		player2 = new Player("bar", 300, 50, (float) Math.PI/2, 0.01f);

    	system.addEmitter(new MagnetoParticleEmitter(player, player2));
		
        plane = new Image("res/images/magnet_inactive.png");
        playerDos = new Image("res/images/magnet_inactive.png");
        land = new Image("res/images/core.png");
        ring = new Image("res/images/testRing.png");
    }

    public void update(GameContainer gc, int delta)
			throws SlickException
    {
        Input input = gc.getInput();
 
        if(input.isKeyDown(Input.KEY_A))
        {
            plane.rotate(-0.2f * delta);
        }
 
        if(input.isKeyDown(Input.KEY_D))
        {
            plane.rotate(0.2f * delta);
        }
 
        if(input.isKeyDown(Input.KEY_W))
        {
            float hip = 0.4f * delta;
 
            float rotation = plane.getRotation();
 
            x+= hip * Math.sin(Math.toRadians(rotation));
            y-= hip * Math.cos(Math.toRadians(rotation));
        }
 
        if(input.isKeyDown(Input.KEY_2))
        {
            scale += (scale >= 5.0f) ? 0 : 0.1f;
            plane.setCenterOfRotation(plane.getWidth()/2.0f*scale, plane.getHeight()/2.0f*scale);
        }
        if(input.isKeyDown(Input.KEY_1))
        {
            scale -= (scale <= 1.0f) ? 0 : 0.1f;
            plane.setCenterOfRotation(plane.getWidth()/2.0f*scale, plane.getHeight()/2.0f*scale);
        }
        
        player.calculateNewPos(delta*0.01f, x/2, y/2);
        player2.calculateNewPos(delta*0.01f, x/2, y/2);
        
        system.update(delta);
    }
 
    public void render(GameContainer gc, Graphics g)
			throws SlickException
    {
        land.draw(300, 300);
 
        plane.draw(player.getCenterX(), player.getCenterY(), scale);
        playerDos.draw(player2.getCenterX(), player2.getCenterY(), scale);
        ring.draw(0, 0, (float) this.height/ring.getHeight());
        system.render();
    }
 
    public static void main(String[] args)
			throws SlickException
    {
    	int width = 800;
    	int height = 600;
         AppGameContainer app =
			new AppGameContainer( new ParticleTestGame(width, height) );
 
         app.setDisplayMode(width, height, false);
         app.start();
    }
}
