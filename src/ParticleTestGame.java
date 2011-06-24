
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
    	
		player = new Player("foo", 200, 200, (float)Math.PI/2, 1f);
		player2 = new Player("bar", 300, 200, (float) Math.PI/2, 0.01f);

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
        float attract = 0;
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
            attract = 0.3f;
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
        
        ring.setCenterOfRotation(width/2,height/2);
        ring.rotate(0.05f * delta);
    
        player.calculateNewPos(delta*0.2f, width/2, height/2,attract);
        player2.calculateNewPos(delta*0.2f, width/2, height/2,attract);
        
        system.update(delta);
    }
 
    public void render(GameContainer gc, Graphics g)
			throws SlickException
    {
        land.draw(300, 300);
 
        plane.draw(player.getMinX() - plane.getWidth() / 2, player.getMinY() - plane.getHeight() /2, scale);
        playerDos.draw(player2.getMinX() - playerDos.getWidth() / 2, player2.getMinY() - playerDos.getHeight() /2, scale);
        ring.draw(0, 0, (float) this.height/ring.getHeight());
        system.render();
    }
 
    public static void main(String[] args)
			throws SlickException
    {
    	int width = 600;
    	int height = 600;
         AppGameContainer app =
			new AppGameContainer( new ParticleTestGame(width, height) );
 
         app.setDisplayMode(width, height, false);
         app.start();
    }
}