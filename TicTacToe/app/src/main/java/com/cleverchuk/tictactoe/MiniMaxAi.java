package com.cleverchuk.tictactoe;

/**
 * Created by root on 1/10/18.
 * Domain ${DOMAIN}
 */

class MiniMaxAi {
    private char player, opponent;
    private final static char empty = ' ';
    public final static int playerHeuristicValue = 10, opponentHeuristicValue = -10;

    MiniMaxAi(char player, char opponent) {
        this.player = player;
        this.opponent = opponent;
    }

    char getPlayer() {
        return player;
    }

    char getOpponent() {
        return opponent;
    }

    public int heuristics(char[][] board) {
        for (int row = 0; row < 3; row++) {
            //check row winner
            if (board[row][0] == board[row][1] && board[row][0] == board[row][2]) {
                if (board[row][0] == this.player)
                    return playerHeuristicValue;
                else if (board[row][0] == this.opponent)
                    return opponentHeuristicValue;
            }
        }

        for (int col = 0; col < 3; col++) {
            //check column winner
            if (board[0][col] == board[1][col] && board[0][col] == board[2][col]) {
                if (board[0][col] == this.player)
                    return playerHeuristicValue;
                else if (board[0][col] == this.opponent)
                    return opponentHeuristicValue;
            }
        }

        //check diagonal winner
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2]
                || board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            if (board[1][1] == this.player)
                return playerHeuristicValue;
            else if (board[1][1] == this.opponent)
                return opponentHeuristicValue;
        }

        //Check for draw
        if (!isMoveLeft(board))
            return 0;
        else
            return -1;
    }

    private int miniMax(char[][] board, int depth, boolean isMaximizer) {
        int heuristicValue = heuristics(board);

        if (heuristicValue == playerHeuristicValue)
            return heuristicValue;

        else if (heuristicValue == opponentHeuristicValue)
            return heuristicValue;

        if (!isMoveLeft(board))
            return 0;

        if (isMaximizer) { //maximizer move
            int bestMoveValue = -1000;
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    if (board[row][col] == empty) {
                        board[row][col] = this.player; // make move
                        // recursive call
                        bestMoveValue = Math.max(bestMoveValue, miniMax(board, ++depth, false));
                        board[row][col] = empty; // undo move
                    }
                }
            }
            return bestMoveValue;

        } else {// minimizer move
            int bestMoveValue = 1000;
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    if (board[row][col] == empty) {
                        board[row][col] = this.opponent; // make a move
                        // recursive call
                        bestMoveValue = Math.min(bestMoveValue, miniMax(board, ++depth, true));
                        board[row][col] = empty; // undo move
                    }
                }
            }
            return bestMoveValue;
        }
    }

    int[] findBestMove(char[][] board) {
        int[] move = {-1, -1};
        int bestMoveValue = -1000;
        int currentMoveValue;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == empty) {
                    board[row][col] = this.player; // make move
                    currentMoveValue = miniMax(board, 0, false); // find best move
                    board[row][col] = empty; // undo move

                    if (currentMoveValue > bestMoveValue) {
                        bestMoveValue = currentMoveValue;
                        move[0] = row;
                        move[1] = col;
                    }
                }
            }
        }
        return move;
    }

    private boolean isMoveLeft(char[][] board) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == empty)
                    return true;
            }
        }
        return false;
    }
}
