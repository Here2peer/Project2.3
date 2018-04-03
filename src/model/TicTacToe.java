package model;

import view.AbstractView;

public class TicTacToe {

    boolean CIRCLE;
    boolean CROSS;
    boolean EMPTY;

    private int[][] multi;


    /*



     */

    public TicTacToe(AbstractView griedView){
        System.out.println(WinState());
    }

    private boolean WinState() {

        int[][] multi = new int[][]{
                {1, 0, 0},
                {1, 0, 0},
                {1, 0, 0}
        };

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
        

        return false
    }


}
