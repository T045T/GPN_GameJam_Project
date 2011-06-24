import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class SlickBasicGame extends BasicGame {

	Player player;

	Image plane = null;
	Image land = null;
	Image ring = null;
	float x = 400;
	float y = 300;
	float scale = 1;
	private int width;
	private int height;

	public SlickBasicGame(int width, int height) {
		super("Slick2D Path2Glory - SlickBasicGame");
		this.width = width;
		this.height = height;

	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		player = new Player("foo", 100, 60, (float) Math.PI, 10);

		plane = new Image("res/test.png");
		land = new Image("res/trollface.png");
		ring = new Image("res/images/testRing.png");
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		Input input = gc.getInput();

		if (input.isKeyDown(Input.KEY_A)) {
			plane.rotate(-0.2f * delta);
		}

		if (input.isKeyDown(Input.KEY_D)) {
			plane.rotate(0.2f * delta);
		}

		if (input.isKeyDown(Input.KEY_W)) {
			float hip = 0.4f * delta;

			float rotation = plane.getRotation();

			x += hip * Math.sin(Math.toRadians(rotation));
			y -= hip * Math.cos(Math.toRadians(rotation));
		}

		if (input.isKeyDown(Input.KEY_2)) {
			scale += (scale >= 5.0f) ? 0 : 0.1f;
			plane.setCenterOfRotation(plane.getWidth() / 2.0f * scale, plane.getHeight() / 2.0f * scale);
		}
		if (input.isKeyDown(Input.KEY_1)) {
			scale -= (scale <= 1.0f) ? 0 : 0.1f;
			plane.setCenterOfRotation(plane.getWidth() / 2.0f * scale, plane.getHeight() / 2.0f * scale);
		}

		//player.calculateNewPos(delta);
	}

	public void render(GameContainer gc, Graphics g)
			throws SlickException
    {
        land.draw(0, 0);
        ring.draw(0, 0, (float) 0.5);
        plane.draw(player.getCenterX(), player.getCenterY(), scale);
 
    }

	public static void main(String[] args) throws SlickException {
		int width = 800;
		int height = 600;
		AppGameContainer app = new AppGameContainer(new SlickBasicGame(width, height));

		app.setDisplayMode(width, height, false);
		app.start();
	}
}