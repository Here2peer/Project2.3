package model;

import java.util.ArrayList;
import java.util.Arrays;

public class ReversiModel extends GameModel {
    public ReversiModel() {
        this.gameName = "Reversi";
        this.boardDimensions = 8;
        this.board = new int[this.boardDimensions][this.boardDimensions];
        this.playerTurn = true;
        this.initializeBoard();
    }



    private void initializeBoard() {
        this.setSquare(3,3, 2);
        this.setSquare(3,4,1);
        this.setSquare(4,3,1);
        this.setSquare(4,4,2);

        printBoardState(this.board);

        this.board = showValidMoves(this.board, this.playerTurn);
    }

    private void printBoardState(int[][] board) {
        for(int i = 0; i < this.getDimensions(); i++) {
            String toPrint = "";
            for(int j = 0; j < this.getDimensions(); j++) {
                if(!(toPrint.isEmpty())) {
                    toPrint += ", ";
                }
                toPrint += board[j][i];
            }
            System.out.println(toPrint);
        }
    }


    public int[][] showValidMoves(int[][] tempBoard, boolean pt) {
        int value;
        if(pt) {
            value = 1;
        } else {
            value = 2;
        }
        for(int x = 0; x < this.boardDimensions; x++) {
            for(int y = 0; y < this.boardDimensions; y++) {
                if(tempBoard[x][y] == value) {
                    for(int x1 = -1; x1 <= 1; x1++) {
                        for(int y1 = -1; y1 <= 1; y1++) {
                            int[] move = findValidMoveFrom(x, y, tempBoard, value, x1, y1);
                            if(!(move == null)) {
                                System.out.println("Found move " + move[0] + ", " + move[1] + " for player " + value);
                                tempBoard[move[0]][move[1]] = 3;
                            }
                        }
                    }
                }
            }
        }
        return tempBoard;
    }

    private int[] findValidMoveFrom(int col, int row, int[][] board, int ownValue, int xDelta, int yDelta) {
        int[] move = new int[2];
        int colPos = col + xDelta;
        int rowPos = row + yDelta;
        int otherValue;
        if(ownValue == 1) {
            otherValue = 2;
        } else {
            otherValue = 1;
        }
        if(isOutOfBounds(colPos, rowPos)) {
            return null;
        }
        if(board[colPos][rowPos] != otherValue) {
            return null;
        }

        while(true) {
            colPos += xDelta;
            rowPos += yDelta;
            if(isOutOfBounds(colPos, rowPos)) {
                return null;
            }
            if(board[colPos][rowPos] == 0) {
                move[0] = colPos;
                move[1] = rowPos;
                return move;
            }
            if(board[colPos][rowPos] != otherValue) {
                return null;
            }
        }
    }

    /** Currently not in use, "shit's on fire yo" (it's not working) */
    public int[][] validFields(int[][] tempBoard, boolean pt) {

        System.out.println("Attempting to calculate valid moves...");
        for(int x = 0; x < this.boardDimensions; x++) {
            for(int y = 0; y < this.boardDimensions; y++) {
                if(tempBoard[x][y] == 3) {
                    tempBoard[x][y] = 0;
                }
                if(tempBoard[x][y] == 0) {
                    if(validMove(x, y, pt, tempBoard)) {
                        tempBoard[x][y] = 3;
                    }

                    //TODO: check adjacent fields for rows, columns or diagonals to take.
                }
            }
        }

        return tempBoard;
    }



    /** Currently not in use, "shit's on fire yo" (it's not working) */
    private boolean validMove(int col, int row, boolean pt, int[][] board) {
        int value;
        boolean retBool=false;
        if(pt) {
            value = 2;
        } else {
            value = 1;
        }
        for(int x = -1; x <= 1; x++) {
            for(int y = -1; y <= 1; y++) {
                boolean searchComplete = false;
                int colPos = col + x;
                int rowPos = row + y;

                if(isOutOfBounds(colPos, rowPos)) {
                    continue;
                }
                if(!(board[colPos][rowPos] == value)) {
                    continue;
                }
                while(!searchComplete) {
                    System.out.println("Attempting to find valid move originating from [" + col + "][" + row + "], currently at" + colPos + ", " + rowPos);
                    colPos += x;
                    rowPos += y;

                    if(isOutOfBounds(colPos, rowPos)) {
                        searchComplete = true;
                    } else if(board[colPos][rowPos] == value){
                        searchComplete = true;
                        retBool = true;
                        System.out.println("Found valid move! [" + col + "][" + row + "]");
                    } else if(board[colPos][rowPos] == 0) {
                        searchComplete = true;
                    }
                }
            }
        }
        return retBool;
    }

    private boolean isOutOfBounds(int x, int y) {
        return (x < 0 || x > 7 || y < 0 || y > 7);
    }

    public void attemptMove(int x, int y) {
        if (this.board[y][x] != 3) {
            System.out.println("[ERROR] Invalid move attempted!");
        } else {
            doMove(x, y);
        }
    }

    private void doMove(int x, int y) {
        int value;
        if(playerTurn) {
            value = 1;
        } else {
            value = 2;
        }
        setSquare(x, y, value);
        for(int i = -1; i <= 1; i++) {
            for(int j = -1; j <= 1; j++) {
                updateAdjacent(x, y, i, j, value, 0);
            }
        }
        playerTurn = !playerTurn;
        this.board = showValidMoves(this.board, playerTurn);
        notifyViews();
    }

    private boolean updateAdjacent(int col, int row, int xDelta, int yDelta, int value, int counter) {
        if(isOutOfBounds(col, row)) {
            return false;
        }
        if(board[col][row] == value) {
            return true;
        }
        if(counter > 7) {
            return false;
        }
        if(updateAdjacent(col + xDelta, row + yDelta, xDelta, yDelta, value, ++counter)) {
            System.out.println("Updating (" + col + ", " + row + ") from " + board[row][col] + " to " + value);
            board[row][col] = value;
            return true;
        }
        return false;
    }

    private void setSquare(int x, int y, int val) {
        board[y][x] = val;
    }

}
