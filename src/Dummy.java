import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class Dummy {

	public void start() {
		try {
			Display.setDisplayMode(new DisplayMode(800, 600));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}

		while (!Display.isCloseRequested()) {

			Display.update();
		}

		Display.destroy();
	}

	public static void main(String[] argv) {
		Dummy displayExample = new Dummy();
		displayExample.start();
	}
}