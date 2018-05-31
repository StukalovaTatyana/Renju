package sample;

import javax.swing.*;

public class Mover {

    public static int[][] board = new int[16][16];
    public static int player = 1;
    public static int enemy;

    public void changePlayer() {
        if (player == 1) {
            player = 2;
            enemy = 1;
        } else {
            player = 1;
            enemy = 2;
        }
    }

    public void point(int x, int y) {
        board[x][y] = player;
    }

    public boolean canPoint(int x, int y) {
        if (board[x][y] == 0)
            return true;
        else
            return false;
    }

    public void winGame(int x, int y) {
        // диагональ 1
        int count = 1;
        int pointx = x;
        int pointy = y;
        while (pointx + 1 >= 0 && pointx + 1 <= 15 && pointy + 1 >= 0 && pointy + 1 <= 15 && board[pointx + 1][pointy + 1] != 0 && board[pointx + 1][pointy + 1] != enemy) {
            pointx++;
            pointy++;
            count++;
        }
        pointx = x;
        pointy = y;
        while (pointx - 1 >= 0 && pointx - 1 <= 15 && pointy - 1 >= 0 && pointy - 1 <= 15 && board[pointx - 1][pointy - 1] != 0 && board[pointx - 1][pointy - 1] != enemy) {
            pointx--;
            pointy--;
            count++;
        }
        System.out.println("D1 " + count);
        if (count >= 5) {

            JOptionPane.showMessageDialog(null, "Победа " + player + " игрока");
            System.exit(0);
        }
        // диагональ 2
        count = 1;
        pointx = x;
        pointy = y;
        while (pointx - 1 >= 0 && pointx - 1 <= 15 && pointy + 1 >= 0 && pointy + 1 <= 15 && board[pointx - 1][pointy + 1] != 0 && board[pointx - 1][pointy + 1] != enemy) {
            pointx--;
            pointy++;
            count++;
        }
        pointx = x;
        pointy = y;
        while (pointx + 1 >= 0 && pointx + 1 <= 15 && pointy - 1 >= 0 && pointy - 1 <= 15 && board[pointx + 1][pointy - 1] != 0 && board[pointx + 1][pointy - 1] != enemy) {
            pointx++;
            pointy--;
            count++;
        }
        System.out.println("D2 " + count);
        if (count >= 5) {
            JOptionPane.showMessageDialog(null, "Победа " + player + " игрока");
            System.exit(0);
        }
        // вертикаль
        count = 1;
        pointx = x;
        pointy = y;
        while (pointx >= 0 && pointx <= 15 && pointy + 1 >= 0 && pointy + 1 <= 15 && board[pointx][pointy + 1] != 0 && board[pointx][pointy + 1] != enemy) {
            pointy++;
            count++;
        }
        pointy = y;
        while (pointx >= 0 && pointx <= 15 && pointy - 1 >= 0 && pointy - 1 <= 15 && board[pointx][pointy - 1] != 0 && board[pointx][pointy - 1] != enemy) {
            pointy--;
            count++;
        }
        System.out.println("V " + count);
        if (count >= 5) {
            JOptionPane.showMessageDialog(null, "Победа " + player + " игрока");
            System.exit(0);
        }
        // горизонталь
        count = 1;
        pointx = x;
        pointy = y;
        while (pointx + 1 >= 0 && pointx + 1 <= 15 && pointy >= 0 && pointy <= 15 && board[pointx + 1][pointy] != 0 && board[pointx + 1][pointy] != enemy) {
            pointx++;
            count++;
        }
        pointx = x;
        while (pointx - 1 >= 0 && pointx - 1 <= 15 && pointy >= 0 && pointy <= 15 && board[pointx - 1][pointy] != 0 && board[pointx - 1][pointy] != enemy) {
            pointx--;
            count++;
        }
        System.out.println("G " + count);
        if (count >= 5) {
            JOptionPane.showMessageDialog(null, "Победа " + player + " игрока");
            System.exit(0);
        }
        System.out.println("********************************");
    }

}
