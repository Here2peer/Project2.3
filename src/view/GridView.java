package view;

import model.Model;

import javax.swing.JButton; //imports JButton library
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

public class GridView extends AbstractView {

        final Color color1 = Color.BLACK;
        final Color color2 = Color.ORANGE;
        final Color color3 = Color.pink;

        boolean blackTurn = true;

        /*

                0 -> Leeg vakje
                1 -> Zwart vakje
                2 -> Wit vakje
                3 -> Mogelijk vakje

                grid[][] = Alle JButtons
                multi[][] = Waarden van JButtons

                multi[3][5] ->  Y = 3 (Multi begint bij 0, dus is 3e vakje van boven naar onder)
                                X = 5 (Multi begint bij 0, dus is 6e vakje van links naar rechts)

                Afbeelding met alle vakjes: https://ibb.co/k5HhKn

         */

        private JButton[][] grid; //names the grid of buttons


        //2D Array met waarden van vakjes erin
        private int[][] multi;

        private int width;
        private int heigth;

        public GridView(Model model){
            super(model);
            gridGen(3,3);
            grid=new JButton[width][heigth]; //Groote van grid maken
            //setStart();
            initateFrame();
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

        private void initateFrame() {
                int width = multi.length;
                int heigth = multi.length;

                for(int y=0; y<multi.length; y++){
                    for(int x=0; x<multi.length; x++){  //Alle vakjes bij langs

                        grid[y][x]=new JButton(); //Button aanmaken

                        //Button layout
                        grid[y][x].setPreferredSize(new Dimension(60,60));
                        grid[y][x].setFocusPainted(true);
                        grid[y][x].setContentAreaFilled(false);

                        int finalY = y;
                        int finalX = x;

                        grid[y][x].addActionListener(new ActionListener(){
                            public void actionPerformed(ActionEvent e){
                                if(String.valueOf(multi[finalY][finalX]).equals("3")) { //zet wordt goedgekeurd
                                    if(blackTurn) {
                                        changeGrid(1, finalY, finalX);
                                    } else {
                                        changeGrid(2, finalY, finalX);
                                    }
                                    System.out.println("Button pressed[" + finalY + "," +finalX + "]. Value of button is = " + String.valueOf(multi[finalY][finalX]));
                                    nextFrame();
                                    blackTurn=!blackTurn;
                                } else {
                                    System.out.println("Deze move is niet mogelijk!");
                                }
                            }
                        });
                        add(grid[y][x]); //adds button to grid
                    }
                }
                nextFrame();
            }

        private void nextFrame() {
            checkPossibleMoves();
            colorFrames();
        }

        private void colorFrames() {

            for(int y=0; y<multi.length; y++) {
                for (int x = 0; x < multi.length; x++) {  //Alle vakjes bij langs

                    if (String.valueOf(multi[y][x]).equals("1")) { //Als vakje zwart is
                        grid[y][x].setIcon(new ColorIconRound(30, color1));
                    } else if (String.valueOf(multi[y][x]).equals("2")) {  //Als vakje wit is
                        grid[y][x].setIcon(new ColorIconRound(30, color2));
                    } else if (String.valueOf(multi[y][x]).equals("3")) {  //Als vakje wit is
                        grid[y][x].setIcon(new ColorIconRound(10, color3));
                    }

                }
            }
        }

        public void changeGrid(int color, int tempy, int tempx) {
                if(color==0) {
                    grid[tempy][tempx].setIcon(new ColorIconRound(30,Color.WHITE));
                    this.multi[tempy][tempx] = 1;
                } else if (color==1) {
                    grid[tempy][tempx].setIcon(new ColorIconRound(30,Color.BLACK));
                    multi[tempy][tempx] = 1;
                } else if(color==2) {
                    grid[tempy][tempx].setIcon(new ColorIconRound(30,Color.ORANGE));
                    multi[tempy][tempx] = 2;
                } else {
                    System.out.println("Color code incorrect!");
                }
        }

        private void setStart(){
            multi[3][3] = 1;
            multi[4][3] = 2;
            multi[3][4] = 2;
            multi[4][4] = 1;
        }

        private void gridGen(int x, int y){
            //2D Array met waarden van vakjes erin
            this.multi = new int[x][y];
            this.width = multi.length;
            this.heigth = multi.length;
        }
}

