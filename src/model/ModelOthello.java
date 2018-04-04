package model;
import view.*;

import view.AbstractView;

import javax.swing.*;

public class ModelOthello {

    private ModelOthelloBot modelOthelloBot;

    boolean turn = true;
    boolean gamestate = true;

    private int[][] multi;

    int width;
    int heigth;

    int tictac = 3;
    int orthello = 8;


    public ModelOthello(ModelOthelloBot model) {
        this.modelOthelloBot=model;
        gridGen(8);
        checkPossibleMoves();
    }

    private void checkPossibleMoves() {
        int[][] tempGrid = multi;

        for (int y = 0; y < tempGrid.length; y++) {
            for (int x = 0; x < tempGrid.length; x++) {  //Alle vakjes bij langs

                if(tempGrid[y][x] == 1 || tempGrid[y][x] == 2) { //Alleen niet-lege vakjes
                    System.out.println("Niet-leeg vakje gevonden! [" + y + "," + x + "]");

                    for(int yy = y-1; yy <= y+1; yy++) {
                        for(int xx = x-1; xx <= x+1; xx++) { //Alle omliggende vakjes
                            if(xx >= 0 && xx < 8 && yy >= 0 && yy < 8){
                                if(tempGrid[yy][xx] == 0) {
                                    tempGrid[yy][xx] = 3;
                                }
                            }
                        }
                    }
                }
            }
        }
        multi = tempGrid;
    }

    public int[][] getGridView(){
        return this.multi;
    }

    public void setGridView(int[][] grid){
        this.multi = grid;
    }

    private void gridGen(int gametype){
        //2D Array met waarden van vakjes erin
        this.multi = new int[gametype][gametype];
        this.width = multi.length;
        this.heigth = multi.length;
    }

}
