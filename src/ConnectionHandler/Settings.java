package ConnectionHandler;

/**
 * Created by Rick Huizing on 3-4-2018.
 */
public class Settings {
    static int PORTNUMBER = 7789;
    //static final String SERVER_IP = "145.33.225.170";
    static String SERVER_IP = "localhost";

    public static String PLAYER_NAME = "jaantje"; //Name may change on login!

    public static void setPlayerName(String playerName) {
        Settings.PLAYER_NAME = playerName;
    }
}
