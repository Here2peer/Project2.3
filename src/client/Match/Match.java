package client.Match;

import client.client.Client;
import model.AbstractPlayer;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Rick Huizing on 4-4-2018.
 */
public class Match extends ObservableMatch {
    private final Client client;
    private final String firstPlayerToMove;
    private boolean inMatch = true;

    private String gameType = "notInGame";
    private String opponentName = "";

    private AbstractPlayer player;

    public Match(String startingPlayer, String opponentName, String gameType, Client client) {
        this.firstPlayerToMove = startingPlayer;
        this.opponentName = opponentName;
        this.gameType = gameType;
        this.client = client;
        this.player = client.getPlayer();
        start();
    }

    /**
     * game starts either by a YOURTURN message or a move by your opponent
     */
    void start() {
        player.firstToMove(!firstPlayerToMove.equals(opponentName));

        while (inMatch){
            if (yourTurn) {
                //makeMove();         //todo: delete this line and uncomment below lines
                player.makeMove();//todo: implement player
                try {
                    client.getConnection().sendMessage(player.getMove());
                    client.getNextMessage();             //dispose of OK message
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                setYourTurn(false);
            } else {
                waitForEvent();
            }
        }
    }

    private void waitForEvent(){
        try {
            String message = client.getNextMessage();   //wait on server
            //System.out.println(":"+message);
            if(message.contains("YOURTURN")){
                System.out.println("It's your turn");
                setYourTurn(true);
            }
            else if(message.contains("MOVE")){
                processMove(message);
            }
            else if(message.contains("WIN")){
                inMatch = false;
                System.out.println("YOU WON THE GAME!!");
            }
            else if(message.contains("DRAW")){
                inMatch = false;
                System.out.println("Draw!");
            }
            else if(message.contains("LOSS")){
                inMatch = false;
                System.out.println("something went wrong, we lost the game");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void processMove(String message){
        Matcher matcher = Pattern.compile("\"([^\"]*)\"").matcher(message);
        matcher.find();
        String player = matcher.group();//player
        matcher.find();
        String move = matcher.group();//move
        matcher.find();
        String details = matcher.group();//move
        System.out.println(player+" placed tile on "+move+" server's reaction: "+details);
        //todo: process move
    }

    public boolean localPlayerTurn() {
        return yourTurn;
    }
}