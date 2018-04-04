package model;

import view.AbstractView;

public class ModelTicTacToe {

    private boolean turn = true;
    private boolean gamestate = true;

    private int[][] multi;

    int width;
    int heigth;

    int tictac = 3;
    int orthello = 8;

    public ModelTicTacToe(){
        gridGen(tictac);
    }

    public boolean WinState() {

        for (int q = 0; q < 3; q++) {

            if (multi[q][0] == multi[q][1] && multi[q][1] == multi[q][2]) {
                if (multi[q][0] != 0) {
                    return true;
                }
            } //Checkt alle horizontale

            if (multi[0][q] == multi[1][q] && multi[1][q] == multi[2][q]) {
                if (multi[0][q] != 0) {
                    return true;
                }
            } //Checkt alle verticale
        }

        if (multi[1][1] != 0) {
            if (multi[0][0] == multi[1][1] && multi[1][1] == multi[2][2]) {
                return true;
            } else return multi[0][2] == multi[1][1] && multi[1][1] == multi[2][0];

        }
        return false;
    }

    public boolean drawState() {
        for(int xx = 0; xx < 3; xx++){
            for(int yy = 0; yy < 3; yy++) {
                if(multi[yy][xx] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private void gridGen(int gametype){
        //2D Array met waarden van vakjes erin
        this.multi = new int[gametype][gametype];
        this.width = multi.length;
        this.heigth = multi.length;
    }

    public void move(int y, int x){
        if(this.multi[y][x] == 0){

            if(this.turn) {
                this.multi[y][x] = 1;
            }else{
                this.multi[y][x] = 2;
            }

            if(WinState()) {
                if(turn) {
                    this.gamestate = false;
                    System.out.println("Black won.");
                }else{
                    this.gamestate = false;
                    System.out.println("Orange won.");
                }
            } else if(drawState()){
                this.gamestate = false;
                System.out.println("Draw!");
            }
            this.turn = !this.turn;
        }else{
            System.out.println("Impossible!");
        }
    }

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
