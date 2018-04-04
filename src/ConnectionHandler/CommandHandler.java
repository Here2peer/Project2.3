package ConnectionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by Rick Huizing on 3-4-2018.
 * provides methods to use the protocol dictated by the server
 */
public class CommandHandler {
    private BlockingQueue<String> inBoundMessageQueue;
    private Connection connection;

    public CommandHandler(BlockingQueue<String> inBoundMessageQueue, Connection connection) {
        this.inBoundMessageQueue = inBoundMessageQueue;
        this.connection = connection;
    }

    /**
     * subscribe to a game type.
     * @param gameType type of game, as returned by getGameList.
     * @return true if subscription succesful.
     */
    public boolean subscribe(String gameType) {
        String returnMessage = "";
        try {
            connection.sendMessage("subscribe " + gameType);
            returnMessage = inBoundMessageQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
            //todo: go into backup procedure(clear responses send command again)
        }

        if (returnMessage.equals("OK")) {
            System.out.println("subscribed for " + gameType);
            return true;
        } else if (returnMessage.startsWith("ERR")) {
            System.err.println("Error message: " + returnMessage);
        } else {
            System.err.println(returnMessage);
        }
        return false;
    }

    /**
     * disconnect connection from server.
     * @return true if command successful.
     */
    boolean exit() {
        try {
            connection.sendMessage("exit");
            System.out.println("exit: " + inBoundMessageQueue.poll(250, TimeUnit.MILLISECONDS));
            return true;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * returns a list of online 'challengable' players.
     * @return ArrayList with playernames.
     */
    public ArrayList<String> getPlayerList() {
        ArrayList<String> gameList = new ArrayList<>(3);
        String returnMessage;
        try {
            connection.sendMessage("get playerlist");
            returnMessage = inBoundMessageQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return gameList;
        }
        if (returnMessage.startsWith("OK")) {
            try {
                returnMessage = inBoundMessageQueue.take();

            } catch (InterruptedException e) {
                e.printStackTrace();
                return gameList;
            }
            if (returnMessage.startsWith("SVR PLAYERLIST")) {
                return arrayFromResponse(returnMessage, 15);
            }
        }
        return gameList;
    }

    /**
     * @return list of games playable on server at this moment.
     * returns and empty array on failure.
     */
    public ArrayList<String> getGameList() {
        ArrayList<String> gameList = new ArrayList<>(3);
        String returnMessage;
        try {
            connection.sendMessage("get gamelist");
            returnMessage = inBoundMessageQueue.take();
            //System.out.println(returnMessage);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return gameList;
        }
        if (returnMessage.contains("OK")) {
            try {
                returnMessage = inBoundMessageQueue.take();
                //System.out.println(returnMessage);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return gameList;
            }
            if (returnMessage.contains("SVR GAMELIST")) {
                //System.out.println("arrayfromresponse");
                return arrayFromResponse(returnMessage, 13);
            }
        }
        System.err.println("gamelist failed");
        return gameList;
    }

    /**
     * creates an array from a server's message containing an array.
     * @param response message from server.
     * @param startOfList index of the start of the array in the response message.
     * @return list of the received information.
     */
    private ArrayList<String> arrayFromResponse(String response, int startOfList) {
        response = response.substring(startOfList);
        response = response.replace("[", "");
        response = response.replace("]", "");
        response = response.replace("\"", "");
        return new ArrayList<String>(Arrays.asList(response.split(",")));
    }

    /**
     * logs in player with provided playername.
     * adds an x to the end of the playername recursively if the playername already is online.
     * @param playerName your username.
     * @return true on success, false on failure.
     */
    public boolean login(String playerName) {
        try {
            connection.sendMessage("login " + playerName);
            Settings.setPlayerName(playerName);
        } catch (InterruptedException e) {
            e.printStackTrace();
            //todo: go into backup procedure(clear responses send command again)
        }
        LinkedList<String> returnMessages = readInboundMessageQueue();

        for (String returnOutput : returnMessages) {
            if (returnOutput != null) {
                if (returnOutput.equals("OK")) {
                    System.out.println("logged in as " + Settings.PLAYER_NAME);
                    return true;
                } else if (returnOutput.startsWith("ERR Duplicate")) {
                    return login(playerName + "x");
                } else if (returnOutput.startsWith("ERR")) {
                    System.err.println("Error message: " + returnOutput);
                } else {
                    System.err.println(returnOutput);
                }
            } else {
                System.out.println(returnMessages);
                return false;
            }
        }
        return false;
    }

    /**
     * specialised message queue read method because for some reason 2 login responses are sometimes parsed as one sentence.
     * it emmpties the inBoundMessageQueue
     * @return contents of inBoundMessageQueue
     */
    LinkedList<String> readInboundMessageQueue() {
        String output = "s";
        LinkedList<String> outputs = new LinkedList<String>();

        while (output != null) {
            try {
                output = inBoundMessageQueue.poll(100, TimeUnit.MILLISECONDS);
                if (output != null) {
                    outputs.add(output);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                output = null;
            }
        }
        return outputs;
    }

    /**
     * forfeits the current match
     */
    public void forfeit() {
        String returnMessage = "";
        try {
            connection.sendMessage("forfeit");
            returnMessage = inBoundMessageQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
            //todo: go into backup procedure(clear responses send command again)
        }

        if (returnMessage.equals("OK")) {
            System.out.println("forfeited the game");
        } else if (returnMessage.startsWith("ERR")) {
            System.err.println("Error message: " + returnMessage);
        } else {
            System.err.println(returnMessage);
        }
    }
}