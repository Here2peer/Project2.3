package view;

import model.Model;
import model.ModelTicTacToe;
import controller.*;

import javax.swing.JButton; //imports JButton library
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

public class GridView extends AbstractView {

        final Color color1 = Color.BLACK;
        final Color color2 = Color.ORANGE;
        final Color color3 = Color.pink;

        private JButton button;

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

        int tictac = 3;
        int orthello = 8;

        public GridView(Controller controller, ModelTicTacToe model){
            super(controller, model);
            gridGen(tictac);
            grid=new JButton[width][heigth]; //Groote van grid maken
            //setStart();
            initateFrame(tictac);
        }

        private void initateFrame(int gametype) {
                int width = multi.length;
                int heigth = multi.length;

                setLayout(new GridLayout(gametype, gametype));
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
                                model.move(finalY, finalX);
                                colorFrames();
                                gameOver();
                            }
                        });
                        add(grid[y][x]); //adds button to grid
                    }
                }
            }

        private void colorFrames() {
            multi = model.getGridView();

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

        private void gridGen(int gametype){
            //2D Array met waarden van vakjes erin
            this.multi = new int[gametype][gametype];
            this.width = multi.length;
            this.heigth = multi.length;
        }

        private void gameOver() {
            if (!model.getGameState()) {
                for (int x = 0; x < 3; x++) {
                    for (int y = 0; y < 3; y++) {
                        grid[y][x].setEnabled(false);
                    }
                }
            }
        }

        /*private void resetGame(){
            button = new JButton();
            button.setBounds(60,10,50,50);
            add(button);
            button.addActionListener(this);
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==button) {
                    model.newGame();
                }
            }
        }*/

        public int[][] getGridView(){
            return this.multi;
        }

    @Override
    public void updateView() {
        repaint();
    }
}

