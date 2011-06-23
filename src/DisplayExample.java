import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.Display;

public class DisplayExample {
    public void start() {
	try {
	    Display.setDisplayMode(new DisplayMode(800, 600));
	    Display.create();
	} catch (LWJGLException e) {
	    e.printStackTrace();
	    System.exit(0);
	}

	// init openGL here

	while (!Display.isCloseRequested()) {
	    // render openGL stuff here
	    Display.update();
	}

	Display.destroy();
    }
}