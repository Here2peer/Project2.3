package model;

abstract public class GameModel extends AbstractModel {
    String gameName;
    int boardDimensions;
    int[][] board;
    boolean playerTurn;

    public int getDimensions() {
        return this.boardDimensions;
    }

    public int[][] getBoard() {
        return this.board;
    }

    public String getTitle() {
        return this.gameName;
    }

    public abstract void attemptMove(int x, int y);

}
