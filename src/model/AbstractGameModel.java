package model;

public abstract class AbstractGameModel {
    boolean turn = true;
    boolean gamestate = true;

    private int[][] multi;

    int width;
    int heigth;

    public abstract boolean WinState();

    public abstract boolean drawState();

    private void gridGen(int gametype){
        //2D Array met waarden van vakjes erin
        this.multi = new int[gametype][gametype];
        this.width = multi.length;
        this.heigth = multi.length;
    }

    public abstract  void move(int y, int x);

    public int[][] getGridView(){
        return this.multi;
    }

    public void setGridView(int[][] grid){
        this.multi = grid;
    }

    public boolean getGameState(){
        return gamestate;
    }

    public void setGameState(boolean state){
        this.gamestate = state;
    }

    public boolean getTurn(){
        return turn;
    }

    public void setTurn(boolean i){
        this.turn = i;
    }

}
