package model;

public class ModelTicTacToe {

    private boolean turn = true;
    private boolean gamestate = true;
    private int[][] multi;
    int tictacGridLength = 3;

    /*
    (constructor) ModelTicTacToe
    creates class ModelTicTacToe

    gridGen(int) -> Create grid (int = width and heigth)
     */
    public ModelTicTacToe(){
        gridGen(tictacGridLength);
    }

    /*
    WinState()
    Checks if someone has won yet

    @return boolean -> true if someone has won
                    -> false if there is no winner yet
     */
    public boolean getWinState() {

        for (int q = 0; q < 3; q++) {
            if (multi[q][0] == multi[q][1] && multi[q][1] == multi[q][2]) { //Horizontal
                if (multi[q][0] != 0) {
                    return true;
                }
            }
            if (multi[0][q] == multi[1][q] && multi[1][q] == multi[2][q]) { //Vertical
                if (multi[0][q] != 0) {
                    return true;
                }
            }
        }

        if (multi[1][1] != 0) {
            if (multi[0][0] == multi[1][1] && multi[1][1] == multi[2][2]) {
                return true;
            } else {
                return (multi[0][2] == multi[1][1] && multi[1][1] == multi[2][0]);
            }
        }   return false;
    }

    /*
    drawState()
    Checks if game board has been filled

    @return boolean true -> board has NOT been fully fulled
                    false -> board has been fully filled
     */
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
        this.multi = new int[gametype][gametype]; //2D Array met waarden van vakjes erin
    }

    /*
    move(int y, int x)
    sets a new move on the board (function will automatically check whose turn it is)

    int y -> y value of position on board
    int x -> x value of position on board
     */
    public void setMove(int y, int x){
        if(this.multi[y][x] == 0){ //Checks if turn is valid
            if(this.turn) {
                this.multi[y][x] = 1;
            } else {
                this.multi[y][x] = 2;
            }

            if(getWinState()) { //Checks if game reached end state (full board)
                this.gamestate = false;
            } else if(drawState()){
                this.gamestate = false;
            }

            this.turn = !this.turn; //reverses turn

        } else {
            System.out.println("Impossible move!");
        }
    }

    /*
    newGame()
    Starts a new game

    setGridView(new int[x][x])  -> resets the grid
    setTurn(true)               -> resets turn to player 1
    setGameState(true)          -> sets gamestate to playable again
     */
    public void newGame() {
        setGridView(new int[3][3]);
        setTurn(true);
        setGameState(true);
    }

    /*
    getGridView()
    returns the current grid

    @return this.multi (current grid)
     */
    public int[][] getGridView(){
        return this.multi;
    }

    /*
    getGridView()
    sets the grid

    @param int[][] grid (the new grid)
     */
    public void setGridView(int[][] grid){
        this.multi = grid;
    }

    /*
    getGameState()
    returns the gamestate

    if game is over, return FALSE
    if game is still going, return TRUE

    @return boolean gamestate
     */
    public boolean getGameState(){
        return gamestate;
    }

    /*
    setGameState()
    sets the game state

    @param boolean state = new state
     */
    public void setGameState(boolean state){
        this.gamestate = state;
    }

    /*
    getTurn()
    return whose turn it is

    if it's player1's turn, return true
    if it's player2's turn, return false

    @return boolean turn
     */
    public boolean getTurn(){
        return turn;
    }

    /*
    setTurn()
    set whose turn it is

    @param boolean i = new turn
     */
    public void setTurn(boolean i){
        this.turn = i;
    }
}
