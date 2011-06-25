package trollhoehle.gamejam.magnets;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;

/**
 * Stores values which (almost) every object needs.
 * 
 * @author Cakemix
 * 
 */
public abstract class GlobalSettings {
    static private float globalSpeed = 0.15f;
    static private int healthPerPlayer = 5;
    static private Management management;
    static private Audio nyanLoop;
    static private Audio pa_health;
    static private Audio pa_speedup;
    static private Audio pa_slowdown;
    static private Audio ping;
    static private Audio pa_invincible;
    static private Audio explosion;

    public static void setManagement(Management pManagement) {
	management = pManagement;
    }

    public static void initSound() throws FileNotFoundException, IOException {
	nyanLoop = AudioLoader.getAudio("WAV", new FileInputStream("res/sound/nyanlooped.wav"));
	pa_health = AudioLoader.getAudio("WAV", new FileInputStream("res/sound/health.wav"));
	pa_invincible = AudioLoader.getAudio("WAV", new FileInputStream("res/sound/invincible.wav"));
	pa_slowdown = AudioLoader.getAudio("WAV", new FileInputStream("res/sound/slowdown.wav"));
	pa_speedup = AudioLoader.getAudio("WAV", new FileInputStream("res/sound/speedup.wav"));
	explosion = AudioLoader.getAudio("WAV", new FileInputStream("res/sound/explosion.wav"));
	ping = AudioLoader.getAudio("WAV", new FileInputStream("res/sound/ping.wav"));
    }

    public static float getGlobalSpeed() {
	return globalSpeed;
    }

    public static int getHealthPerPlayer() {
	return healthPerPlayer;
    }

    public static void setGlobalSpeed(float globalSpeed) {
	GlobalSettings.globalSpeed = globalSpeed;
	management.changeCurrentSpeed(globalSpeed);
    }

    public static void setHealthPerPlayer(int healthPerPlayer) {
	GlobalSettings.healthPerPlayer = healthPerPlayer;
    }

    public static Audio getNyanLoop() {
	return nyanLoop;
    }

    public static Audio getPa_health() {
	return pa_health;
    }

    public static Audio getPa_speedup() {
	return pa_speedup;
    }

    public static Audio getPa_slowdown() {
	return pa_slowdown;
    }

    public static Audio getPing() {
	return ping;
    }

    public static Audio getPa_invincible() {
	return pa_invincible;
    }

    public static Audio getExplosion() {
	return explosion;
    }

}
