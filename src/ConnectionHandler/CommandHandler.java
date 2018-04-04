package ConnectionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by Rick Huizing on 3-4-2018.
 */
public class CommandHandler {
    private BlockingQueue<String> inBoundMessageQueue;
    private Client client;

    CommandHandler(BlockingQueue<String> inBoundMessageQueue, Client client) {
        this.inBoundMessageQueue = inBoundMessageQueue;
        this.client = client;
    }


    boolean subscribe(String gameType) {
        String returnMessage = "";
        try {
            client.executeCommand("subscribe " + gameType);
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

    boolean exit() {
        try {
            client.executeCommand("exit");
            return true;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

    ArrayList<String> getPlayerList() {
        ArrayList<String> gameList = new ArrayList<>(3);
        String returnMessage;
        try {
            client.executeCommand("get playerlist");
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

    ArrayList<String> getGameList() {
        ArrayList<String> gameList = new ArrayList<>(3);
        String returnMessage;
        try {
            client.executeCommand("get gamelist");
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
        System.out.println("gamelist failed");
        return gameList;
    }

    private ArrayList<String> arrayFromResponse(String response, int startOfList) {
        response = response.substring(startOfList);
        response = response.replace("[", "");
        response = response.replace("]", "");
        response = response.replace("\"", "");
        return new ArrayList<String>(Arrays.asList(response.split(",")));
    }

    boolean login(String playerName) {
        try {
            client.executeCommand("login " + playerName);
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

    void forfeit() {
        String returnMessage = "";
        try {
            client.executeCommand("forfeit");
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