package com.company;

public class Mover {

    /*
    Добавить конструктор с начальными настройкками игры (выбор режима, игрок который ходит первым)
     mode:
     * 1 - игрок против игрока
     * 2 - игрок против ИИ
     * 3 - ИИ против ИИ

     player:
     * 1 игрок
     * 2 игрок
     * 3 комп
     * 4 комп
     */
    private int mode;
    private int player = 1; // какой игрок или комп ходит
    private boolean isAI = false; // включен ИИ в игре или нет!

    public void move(int[][] board, int x, int y) {
        // Если клетка свободна
        if (board[x][y] == 0) {
            // ход в зависимости от текущего игрока
            switch (player){
                case 1:
                    board[x][y] = 1;
                    break;
                case 2:
                    board[x][y] = 2;
            }
            switchPlayer(); // смена хода игрока
        } else {
            System.out.println("Данная клетка уже занята");
        }
    }

    public int getPlayer() {
        return player;
    }

    public void switchPlayer() {
        switch (player){
            case 1:
                player = 2;
                break;
            case 2:
                player = 1;
                break;
        }
    }

    public boolean isAI() {
        return isAI;
    }

    public void setAI(boolean AI) {
        isAI = AI;
    }
}
