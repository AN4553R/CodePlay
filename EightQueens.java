// For part a, you could  think about it as choosing the 8 squares out of the 64 available squares (Binomial Coefficient)
// so it would be nCr(64, 8)= 4426165368.

package com.company;

import java.io.*;
import java.util.*;


public class Main {
    public interface TaskSolver {
        void solve(int totalQueens, int currentPlacedQueens);
    }

    public static class TaskA implements TaskSolver {
        public void solve(int n, int k) {
            long C[] = new long[k + 1];
            C[0] = 1;
            for (int i = 1; i <= n; i++) {
                for (int j = Math.min(i, k); j > 0; j--)
                    C[j] = C[j] + C[j - 1];
            }
            System.out.printf("Task A placement: %d \n", C[k]);
        }
    }

    public static class TaskB implements TaskSolver {
        private int result = 0;
        private int[][] board = new int[8][8];

        public void solve(int space, int queens) {
            // init the board with zeros
            for (int[] row : board)
                Arrays.fill(row, 0);

            helper(space, queens);
            System.out.printf("Task B placement: %d \n", result);
        }

        void helper(int n, int row) {
            if (row == n) {
                result++;
                return;
            }
            for (int j = 0; j < n; j++) {
                board[row][j] = 1;
                helper(n, row + 1);
                board[row][j] = 0;
            }
            return;

        }
    }

    public static class TaskC implements TaskSolver {
        private int result = 0;
        private int[][] board = new int[8][8];

        public void solve(int space, int queens) {
            // init the board with zeros
            for (int[] row : board)
                Arrays.fill(row, 0);

            helper(space, queens);
            System.out.printf("Task C placement: %d \n", result);
        }

        void helper(int n, int row) {
            if (row == n) {
                result++;
                return;
            }
            for (int j = 0; j < n; j++) {
                if (isPossible(n, row, j)) {
                    board[row][j] = 1;
                    helper(n, row + 1);
                    board[row][j] = 0;
                }
            }
            return;

        }

        boolean isPossible(int n, int row, int col) {
            // Same Column
            for (int i = row - 1; i >= 0; i--) {
                if (board[i][col] == 1) {
                    return false;
                }
            }
            return true;
        }
    }

    public static class allConditionsPlacement implements TaskSolver {
        private int result = 0;
        private int[][] board = new int[8][8];

        public void solve(int space, int queens) {
            // init the board with zeros
            for (int[] row : board)
                Arrays.fill(row, 0);

            helper(space, queens);
            System.out.printf("Altogether placement: %d \n", result);
        }

        void helper(int n, int row) {
            if (row == n) {
                result++;
                return;
            }
            for (int j = 0; j < n; j++) {
                if (isPossible(n, row, j)) {
                    board[row][j] = 1;
                    helper(n, row + 1);
                    board[row][j] = 0;
                }
            }
            return;

        }

        boolean isPossible(int n, int row, int col) {
            // Same Column
            for (int i = row - 1; i >= 0; i--) {
                if (board[i][col] == 1) {
                    return false;
                }
            }
            //Upper Left Diagonal
            for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
                if (board[i][j] == 1) {
                    return false;
                }
            }
            // Upper Right Diagonal
            for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
                if (board[i][j] == 1) {
                    return false;
                }
            }
            return true;
        }
    }


    public static void main(String[] args) {
        int squaresNumber = 64, totalQueens = 8;
        TaskSolver one = new TaskA();
        one.solve(squaresNumber, totalQueens);
        TaskSolver two = new TaskB();
        two.solve(totalQueens, 0);
        TaskSolver three = new TaskC();
        three.solve(totalQueens, 0);
        TaskSolver altogether = new allConditionsPlacement();
        altogether.solve(totalQueens, 0);
    }
}
