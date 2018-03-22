package com.company;

public class Main {

    public static void main(String[] args) {
        GameBoard board = new GameBoard();
        Mover mover = new Mover();
        mover.move(board.getBoard(), 1, 1);
        board.printBoard();
        System.out.println();
        mover.move(board.getBoard(), 0, 0);
        board.printBoard();
        //
    }
}
