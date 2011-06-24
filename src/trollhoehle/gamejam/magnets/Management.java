package trollhoehle.gamejam.magnets;



import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Management extends BasicGame {

	public Management() {
		super("Magnets - How do they work?");
	}
	
    /**
     * @param args
     * @throws SlickException 
     */
    public static void main(String[] args) throws SlickException {
    	AppGameContainer app =
			new AppGameContainer( new Management() );

		app.setDisplayMode(720, 720, false);
		app.start();

    }

	@Override
	public void render(GameContainer arg0, Graphics arg1) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(GameContainer arg0) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException {
		// TODO Auto-generated method stub
		
	}

}
