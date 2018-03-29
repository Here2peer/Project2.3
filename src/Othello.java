import javax.imageio.plugins.jpeg.JPEGHuffmanTable;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Othello extends JPanel implements Runnable {
    //setup board array (2d)
    CellState [][] board;

    //UI
    JPanel mainPanel;



    public void othello(){
    }

    @Override
    public void run() {
        Board board = new Board();
        board.playfield();
        board.visable();
        setBoard();
        newGame();

    }

    public enum CellState {
        EMPTY,
        WHITE,
        BLACK
    }

    public boolean isEmpty(int x, int y){
        return board[x][y] == CellState.EMPTY;
    }

    public boolean isWhite(int x, int y){
        return board[x][y] == CellState.WHITE;
    }

    public boolean isBlack(int x, int y){
        return board[x][y] == CellState.BLACK;
    }

    //set new board with size 8*8
    void setBoard(){
        this.board = new CellState[8][8];
    }

    //initiate new game (clears board)
    void newGame(){
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                this.board[x][y] = CellState.EMPTY;
            }
        }
    }
}
