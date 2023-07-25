package Model;

import org.tinylog.Logger;

public class Logic {
    private static final int BOARD_SIZE = 11;
    private int currentPlayer = 1;
    private boolean gameOver = false;
    private final int[][] board = new int[BOARD_SIZE][BOARD_SIZE];
    public boolean onlyBlues;
    public boolean onlyReds;

    public Logic(){
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                if (row % 2 == 0) {
                    if(col % 2 == 0)
                        board[row][col] = 0;
                    else
                        board[row][col] = 1;
                }
                else{
                    if(col % 2 == 0)
                        board[row][col] = 2;
                    else
                        board[row][col] = 0;
                }
            }
        }
    }

    public void coloring(int row, int col){
        if(!gameOver && board[row][col] == 0){
            board[row][col] = currentPlayer;

            if(checkWin() || checkDraw()){
                gameOver = true;
            } else{
                if(currentPlayer == 1) {
                    Logger.info("Blue Coloring: {} {}", row, col);
                    currentPlayer = 2;
                }
                else {
                    Logger.info("Red Coloring: {} {}", row, col);
                    currentPlayer = 1;

                }
            }
        } else if(board[row][col] != 0){
            Logger.warn("Invalid move: {} {}", row, col);
        }
    }
    public boolean checkWin() {
        int row;
        int col;


        for(col = 1; col < this.board[0].length; col += 2) {
            onlyBlues = true;
            for(row = 0; row < this.board.length; ++row) {
                if (this.board[row][col] == 2 || this.board[row][col] == 0) {
                    onlyBlues = false;
                    break;
                }
            }

            if (onlyBlues) {
                return true;
            }
        }

        for(row = 1; row < this.board.length; row += 2) {
            onlyReds = true;
            for(col = 0; col < this.board[0].length; ++col) {
                if (this.board[row][col] == 1 || this.board[row][col] == 0) {
                    onlyReds = false;
                    break;
                }
            }

            if (onlyReds) {
                return true;
            }
        }

        return false;
    }

    public boolean checkDraw() {
        int row;
        int col;
        boolean hasRed;
        boolean hasBlue;

        for(col = 1; col < this.board[0].length; col += 2) {
            hasRed = false;
            for(row = 0; row < this.board.length; ++row) {
                if (this.board[row][col] == 2) {
                    hasRed = true;
                    break;
                }
            }

            if (!hasRed) {
                return false;
            }
        }

        for(row = 1; row < this.board.length; row += 2) {
            hasBlue = false;
            for(col = 0; col < this.board[0].length; ++col) {
                if (this.board[row][col] == 1) {
                    hasBlue = true;
                    break;
                }
            }

            if (!hasBlue) {
                return false;
            }
        }

        return true;
    }

    public int[][] getBoard(){
        return board;
    }

    public boolean getGameOver(){ return gameOver;}


}