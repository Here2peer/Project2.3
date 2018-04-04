package Client;

import Client.Match.Match;
import model.AbstractPlayer;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Rick Huizing on 3-4-2018.
 * represents the connection with the server.
 */
public class Client implements AbstractServerConnection{
    private BlockingQueue<String> inBoundMessageQueue = new LinkedBlockingQueue<String>();
    private CommandHandler commandHandler;

    private ArrayList<String> gameList;
    private ArrayList<String> playerList;

    private boolean loggedIn = false;
    private boolean waitingForGame = false;
    private boolean inMatch = false;
    private Match match = null;

    private boolean player1IsHuman = true;
    private AbstractPlayer player = null;

    Connection connection;

    Client(){
        //Connection connection = new Connection(inBoundMessageQueue);
        connection = new Connection(inBoundMessageQueue);
        commandHandler = new CommandHandler(inBoundMessageQueue, connection);
        if(login()){
            if(subscribe(getGameList().get(0))){
                System.out.println("waitForMatch");
                waitForMatch();
            }
        }
    }

    void waitForMatch() {
        try {
            String gameMessage = inBoundMessageQueue.take();
            //System.out.println(gameMessage);
            if(gameMessage.startsWith("SVR GAME MATCH")){
                waitingForGame = false;
                enterMatch(gameMessage);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void enterMatch(String gameMessage) {
        inMatch = true;
        Matcher matcher = Pattern.compile("\"([^\"]*)\"").matcher(gameMessage);
        if (matcher.find()) {
            String firstPlayerToMove = matcher.group();
            if (matcher.find()) {
                String gameType = matcher.group();
                if (matcher.find()) {
                    String opponentName = matcher.group();
                    match = new Match(firstPlayerToMove, opponentName, gameType, this);
                }
            }
        }
    }

    public boolean isLoggedIn(){
        return loggedIn;
    }

    public boolean login() {
        if(inMatch){
            return false;
        }
        return (loggedIn = commandHandler.login(Settings.PLAYER_NAME));
    }

    public ArrayList<String> getGameList(){
        if(inMatch){
            return null;
        }
        gameList = commandHandler.getGameList();
        return gameList;
    }

    public ArrayList<String> getPlayerList(){
        if(inMatch){
            return null;
        }
        playerList = commandHandler.getPlayerList();
        return playerList;
    }
    public boolean subscribe(String gameName){
        if(inMatch || player ==null){
            return false;
        }
        waitingForGame = commandHandler.subscribe(gameName);
        return waitingForGame;
    }

    public void forfeit(){
        if(inMatch){
            commandHandler.forfeit();
        }
        endMatch();
    }

    public void endMatch(){
        if(match!=null){
            match = null;
        }
        inMatch = false;
    }

    public boolean localPlayerTurn(){
        return match.localPlayerTurn();
    }

    public void registerPLayer(AbstractPlayer player) {
            this.player = player;
    }

    public AbstractPlayer getPlayer(){return player;}


    public boolean Player1IsHuman() {
        return player1IsHuman;
    }

    public void setPlayer1IsHuman(boolean player1IsHuman) {
        this.player1IsHuman = player1IsHuman;
    }

    public static void main(String[] args){
        Client client = new Client();
    }

    public String getNextMessage() throws InterruptedException {
            return inBoundMessageQueue.take();
    }

    public Connection getConnection() {
        return connection;
    }
}