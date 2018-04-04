package ConnectionHandler;

import model.AbstractPlayer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Rick Huizing on 3-4-2018.
 */
public class ServerConnection implements AbstractServerConnection {
    private BlockingQueue<String> inBoundMessageQueue = new LinkedBlockingQueue<String>();
    private CommandHandler commandHandler;

    private ArrayList<String> gameList;
    private ArrayList<String> playerList;

    private boolean loggedIn = false;
    private boolean waitingForGame = false;
    private boolean inGame = false;
    private boolean yourTurn = false;
    private String gameType = "notInGame";
    private String opponentName = "";

    private boolean player1IsHuman = true;
    private AbstractPlayer playerOne;

    private LinkedList<String> moves = new LinkedList<>();

    Client client;

    ServerConnection(){
        //Client client = new Client(inBoundMessageQueue);
        client = new Client(inBoundMessageQueue);
        commandHandler = new CommandHandler(inBoundMessageQueue, client);
        moves.addLast("MOVE d, 2");
        if(login()){
            if(subscribe(getGameList().get(0))){
                System.out.println("waitForGame");
                waitForGame();
            }
        }
    }

    private void waitForGame() {
        try {
            String gameMessage = inBoundMessageQueue.take();
            System.out.println(gameMessage);
            if(gameMessage.startsWith("SVR GAME MATCH")){
                waitingForGame = false;
                inGame(gameMessage);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void inGame(String gameMessage) {
        inGame = true;
        Matcher matcher = Pattern.compile("\"([^\"]*)\"").matcher(gameMessage);
        if(matcher.find()){
            String firstPlayerToMove = matcher.group();
            if(matcher.find()){
                gameType = matcher.group();
                if(matcher.find()){
                    opponentName = matcher.group();
                }
            }
            if(!firstPlayerToMove.equals(opponentName)){
                System.out.println("you start the game");
            }else{
                System.out.println("you do not start the game");
            }
        }

        while(inGame) {
            if (yourTurn) {
                makeMove();
                yourTurn = false;
            } else {
                waitForEvent();
            }
        }
    }

    private void makeMove() {
        if(player1IsHuman){
            /*try {
                    client.executeCommand(moves.pop());
                    System.out.println(inBoundMessageQueue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
            return;//wait for user input
        }else{
            playerOne.makeMove();
        }
    }

    private void waitForEvent(){
        try {
        String message = inBoundMessageQueue.take(); //wait for opponent
        System.out.println(":"+message);
        if(message.contains("YOURTURN")){
            System.out.println("It's your turn");
            yourTurn = true;
        }
        else if(message.contains("MOVE")){
            processMove(message);
        }
        else if(message.contains("WIN")){
            inGame = false;
            System.out.println("YOU WON THE GAME!!");
        }
        else if(message.contains("DRAW")){
            inGame = false;
            System.out.println("Draw!");
        }
        else if(message.contains("LOSS")){
            inGame = false;
            System.err.println("something went wrong, we lost the game");
        }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void processMove(String message){
        Matcher matcher = Pattern.compile("\"([^\"]*)\"").matcher(message);
        matcher.find();
        System.out.println( matcher.group());//player
        matcher.find();
        System.out.println(matcher.group());//details
        matcher.find();
        System.out.println( matcher.group());//move
        //todo: process move
    }

    public boolean isLoggedIn(){
        return loggedIn;
    }

    public boolean login() {
        return (loggedIn = commandHandler.login(Settings.PLAYER_NAME));
    }

    public ArrayList<String> getGameList(){
        gameList = commandHandler.getGameList();
        return gameList;
    }

    public ArrayList<String> getPlayerList(){
        playerList = commandHandler.getPlayerList();
        return playerList;
    }
    public boolean subscribe(String gameName){
        waitingForGame = commandHandler.subscribe(gameName);
        return waitingForGame;
    }

    public void forfeit(){
        commandHandler.forfeit();
    }


    public void registerPLayer(AbstractPlayer player) {
            this.playerOne = player;
    }

    public boolean isPlayer1IsHuman() {
        return player1IsHuman;
    }

    public void setPlayer1IsHuman(boolean player1IsHuman) {
        this.player1IsHuman = player1IsHuman;
    }

    public static void main(String[] args){
        ServerConnection serverConnection = new ServerConnection();
    }
}
