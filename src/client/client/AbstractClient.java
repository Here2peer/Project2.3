package client.client;

import client.Connection;
import client.Settings;
import model.AbstractPlayer;

import java.util.ArrayList;

/**
 * Created by Rick Huizing on 4-4-2018.
 */
public interface AbstractClient {

    boolean isLoggedIn();

    /**
     * registers a player
     * @param player the player to be registered
     */
    void registerPLayer(AbstractPlayer player);

    AbstractPlayer getPlayer();
    /**
     * logs you in with the playername in  {@link Settings}
     *
     * @return true if login succesful
     */
    boolean login();

    /**
     * @return an arrayList with games the game server is hosting
     */
    ArrayList<String> getGameList();

    /**
     * @return a list with online players
     */
    ArrayList<String> getPlayerList();

    /**
     * subscribes you for a specific game mode, calling this method will lead to the start of a game.
     *
     * @param gameName name of the game to be played, as returned by getGameList();
     * @return true if subscription is successful
     */
    boolean subscribe(String gameName);

    /**
     * forfeits current game
     */
    void forfeit();

    void waitForMatch();

    void enterMatch(String gameMessage);

    public void endMatch();

    public boolean localPlayerTurn();

    public String getNextMessage() throws InterruptedException;

    public Connection getConnection();
}
