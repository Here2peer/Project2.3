package model;

import view.AbstractView;

public class ModelTicTacToe {

    boolean CIRCLE;
    boolean CROSS;
    boolean EMPTY;

    private int[][] multi;

    int width;
    int heigth;

    int tictac = 3;
    int orthello = 8;

    public ModelTicTacToe(){
        gridGen(tictac);
    }

    private boolean WinState() {

        for (int q = 0; q < 3; q++) {

            if (multi[q][0] == multi[q][1] && multi[q][1] == multi[q][2]) {
                if (multi[q][0] != 0) {
                    System.out.println("lijn 36");
                    return true;
                }
            } //Checkt alle horizontale

            if (multi[0][q] == multi[1][q] && multi[1][q] == multi[2][q]) {
                if (multi[0][q] != 0) {
                    System.out.println("lijn 41");
                    return true;
                }
            } //Checkt alle verticale
        }

        if (multi[1][1] != 0) {
            if (multi[0][0] == multi[1][1] && multi[1][1] == multi[2][2]) {
                System.out.println("Lijn 49");
                return true;
            } else if (multi[0][2] == multi[1][1] && multi[1][1] == multi[2][0]) {
                System.out.println("Lijn 55");
                return true;
            }  //Checkt alle schuine

        }
        return false;
    }

    private boolean DrawState() {
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

    public int[][] getGridView(){
        return this.multi;
    }


}
