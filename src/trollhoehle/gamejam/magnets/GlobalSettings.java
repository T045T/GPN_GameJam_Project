package trollhoehle.gamejam.magnets;

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

    public static void setManagement(Management pManagement) {
	management = pManagement;
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

}
