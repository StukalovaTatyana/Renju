package com.company;

public class GameBoard {

    private int[][] board;

    public GameBoard() {
        board = new int[15][15];
    }

    public void printBoard() {
        for (int[] i : board) {
            for (int j : i) {
                System.out.print(j);
            }
            System.out.println();
        }
    }

    public int[][] getBoard() {
        return board;
    }
}
