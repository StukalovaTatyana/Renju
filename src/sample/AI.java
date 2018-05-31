package sample;

import java.awt.*;
import java.util.ArrayList;

public class AI {

    public int my;
    private int enemy;
    private Mover mover = new Mover();
    private ArrayList<SuperPosition> myPos;
    private ArrayList<SuperPosition> enemyPos;
    private Controller controller;

    public AI(int my, int enemy) {
        this.my = my;
        this.enemy = enemy;

    }

    private int[] analizePoint(int x, int y, int player) {
        int count = 1;
        int pointx = x;
        int pointy = y;
        int[] arr = new int[4];
        while (pointx + 1 >= 0 && pointx + 1 <= 15 && pointy + 1 >= 0 && pointy + 1 <= 15 &&
                Mover.board[pointx + 1][pointy + 1] != 0 && Mover.board[pointx + 1][pointy + 1] != player) {
            pointx++;
            pointy++;
            count++;
        }
        pointx = x;
        pointy = y;
        while (pointx - 1 >= 0 && pointx - 1 <= 15 && pointy - 1 >= 0 && pointy - 1 <= 15 &&
                Mover.board[pointx - 1][pointy - 1] != 0 && Mover.board[pointx - 1][pointy - 1] != player) {
            pointx--;
            pointy--;
            count++;
        }
        System.out.println("D1 " + count);
        arr[0] = count;

        // диагональ 2
        count = 1;
        pointx = x;
        pointy = y;
        while (pointx - 1 >= 0 && pointx - 1 <= 15 && pointy + 1 >= 0 && pointy + 1 <= 15 &&
                Mover.board[pointx - 1][pointy + 1] != 0 && Mover.board[pointx - 1][pointy + 1] != player) {
            pointx--;
            pointy++;
            count++;
        }
        pointx = x;
        pointy = y;
        while (pointx + 1 >= 0 && pointx + 1 <= 15 && pointy - 1 >= 0 && pointy - 1 <= 15 &&
                Mover.board[pointx + 1][pointy - 1] != 0 && Mover.board[pointx + 1][pointy - 1] != player) {
            pointx++;
            pointy--;
            count++;
        }
        System.out.println("D2 " + count);
        arr[1] = count;

        // вертикаль
        count = 1;
        pointx = x;
        pointy = y;
        while (pointx >= 0 && pointx <= 15 && pointy + 1 >= 0 && pointy + 1 <= 15 &&
                Mover.board[pointx][pointy + 1] != 0 && Mover.board[pointx][pointy + 1] != player) {
            pointy++;
            count++;
        }
        pointy = y;
        while (pointx >= 0 && pointx <= 15 && pointy - 1 >= 0 && pointy - 1 <= 15 &&
                Mover.board[pointx][pointy - 1] != 0 && Mover.board[pointx][pointy - 1] != player) {
            pointy--;
            count++;
        }
        System.out.println("V " + count);
        arr[2] = count;

        // горизонталь
        count = 1;
        pointx = x;
        pointy = y;
        while (pointx + 1 >= 0 && pointx + 1 <= 15 && pointy >= 0 && pointy <= 15 &&
                Mover.board[pointx + 1][pointy] != 0 && Mover.board[pointx + 1][pointy] != player) {
            pointx++;
            count++;
        }
        pointx = x;
        while (pointx - 1 >= 0 && pointx - 1 <= 15 && pointy >= 0 && pointy <= 15 &&
                Mover.board[pointx - 1][pointy] != 0 && Mover.board[pointx - 1][pointy] != player) {
            pointx--;
            count++;
        }
        System.out.println("G " + count);
        arr[3] = count;
        System.out.println("********************************");

        return arr;
    }

    public void analizeBoard() {
        this.myPos = new ArrayList<>();
        this.enemyPos = new ArrayList<>();
        for (int i = 0; i < Mover.board.length; i++) {
            for (int j = 0; j < Mover.board[i].length; j++) {
                if (Mover.board[i][j] == my) {
                    int[] arr = analizePoint(i, j, enemy);
                    myPos.add(new SuperPosition(
                            new Point(i, j), arr[0], arr[1], arr[2], arr[3])
                    );
                } else if (Mover.board[i][j] == enemy) {
                    int[] arr = analizePoint(i, j, my);
                    enemyPos.add(new SuperPosition(
                            new Point(i, j), arr[0], arr[1], arr[2], arr[3])
                    );
                }
            }
        }
    }

    public ArrayList<SuperPosition> getPosWithNumber(int player, int number) {
        ArrayList<SuperPosition> r = new ArrayList<>();
        if (player == enemy) {
            for (SuperPosition position : enemyPos) {
                if (position.Dleft == number || position.Dright == number || position.V == number || position.G == number) {
                    r.add(position);
                }
            }
        } else if (player == my) {
            for (SuperPosition position : myPos) {
                if (position.Dleft == number || position.Dright == number || position.V == number || position.G == number) {
                    r.add(position);
                }
            }
        }
        return r;
    }

    public int getNumber(SuperPosition superPosition, int number) {
        if (superPosition.Dleft == number) return 0;
        if (superPosition.Dright == number) return 1;
        if (superPosition.V == number) return 2;
        if (superPosition.G == number) return 3;
        return -1;
    }

    public Point findAndMove(int player, ArrayList<SuperPosition> listPos, int number) {
        for (SuperPosition pos : listPos) {
            int num = getNumber(pos, number);
            if (num == 0) {
                int pointx = pos.coord.x;
                int pointy = pos.coord.y;
                while (pointx + 1 >= 0 && pointx + 1 <= 15 && pointy + 1 >= 0 && pointy + 1 <= 15 &&
                        Mover.board[pointx + 1][pointy + 1] != 0 && Mover.board[pointx + 1][pointy + 1] != player) {
                    pointx++;
                    pointy++;
                }
                if (pointx + 1 >= 0 && pointx + 1 <= 15 && pointy + 1 >= 0 && pointy + 1 <= 15 && Mover.board[pointx + 1][pointy + 1] != player) {
                    mover.point(pointx + 1, pointy + 1);
                    //controller.addImage(my, pointx + 1, pointy + 1);
                    mover.winGame(pointx + 1, pointy + 1);
                    return new Point(pointx+1, pointy+1);
                } else {
                    pointx = pos.coord.x;
                    pointy = pos.coord.y;
                    while (pointx - 1 >= 0 && pointx - 1 <= 15 && pointy - 1 >= 0 && pointy - 1 <= 15 &&
                            Mover.board[pointx - 1][pointy - 1] != 0 && Mover.board[pointx - 1][pointy - 1] != player) {
                        pointx--;
                        pointy--;
                    }
                    if (pointx - 1 >= 0 && pointx - 1 <= 15 && pointy - 1 >= 0 && pointy - 1 <= 15 && Mover.board[pointx - 1][pointy - 1] != player) {
                        mover.point(pointx - 1, pointy - 1);
                        //controller.addImage(my, pointx - 1, pointy - 1);
                        mover.winGame(pointx - 1, pointy - 1);
                        return new Point(pointx-1, pointy-1);
                    }
                }
            } else if (num == 1) {
                int pointx = pos.coord.x;
                int pointy = pos.coord.y;
                while (pointx - 1 >= 0 && pointx - 1 <= 15 && pointy + 1 >= 0 && pointy + 1 <= 15 &&
                        Mover.board[pointx - 1][pointy + 1] != 0 && Mover.board[pointx - 1][pointy + 1] != player) {
                    pointx--;
                    pointy++;
                }
                if (pointx - 1 >= 0 && pointx - 1 <= 15 && pointy + 1 >= 0 && pointy + 1 <= 15 && Mover.board[pointx - 1][pointy + 1] != player) {
                    mover.point(pointx - 1, pointy + 1);
                    //controller.addImage(my, pointx - 1, pointy + 1);
                    mover.winGame(pointx - 1, pointy + 1);
                    return new Point(pointx-1, pointy+1);
                } else {
                    pointx = pos.coord.x;
                    pointy = pos.coord.y;
                    while (pointx + 1 >= 0 && pointx + 1 <= 15 && pointy - 1 >= 0 && pointy - 1 <= 15 &&
                            Mover.board[pointx + 1][pointy - 1] != 0 && Mover.board[pointx + 1][pointy - 1] != player) {
                        pointx++;
                        pointy--;
                    }
                    if (pointx + 1 >= 0 && pointx + 1 <= 15 && pointy - 1 >= 0 && pointy - 1 <= 15 && Mover.board[pointx + 1][pointy - 1] != player) {
                        mover.point(pointx + 1, pointy - 1);
                        //controller.addImage(my, pointx + 1, pointy - 1);
                        mover.winGame(pointx + 1, pointy - 1);
                        return new Point(pointx+1, pointy-1);
                    }
                }
            } else if (num == 2) {
                int pointx = pos.coord.x;
                int pointy = pos.coord.y;
                while (pointx >= 0 && pointx <= 15 && pointy + 1 >= 0 && pointy + 1 <= 15 &&
                        Mover.board[pointx][pointy + 1] != 0 && Mover.board[pointx][pointy + 1] != player) {
                    pointy++;
                }
                if (pointx >= 0 && pointx <= 15 && pointy + 1 >= 0 && pointy + 1 <= 15 && Mover.board[pointx][pointy + 1] != player) {
                    mover.point(pointx, pointy + 1);
                    //controller.addImage(my, pointx, pointy + 1);
                    mover.winGame(pointx, pointy + 1);
                    return new Point(pointx, pointy+1);
                } else {
                    pointx = pos.coord.x;
                    pointy = pos.coord.y;
                    while (pointx >= 0 && pointx <= 15 && pointy - 1 >= 0 && pointy - 1 <= 15 &&
                            Mover.board[pointx][pointy - 1] != 0 && Mover.board[pointx][pointy - 1] != player) {
                        pointy--;
                    }
                    if (pointx >= 0 && pointx <= 15 && pointy - 1 >= 0 && pointy - 1 <= 15 && Mover.board[pointx][pointy - 1] != player) {
                        mover.point(pointx, pointy - 1);
                        //controller.addImage(my, pointx, pointy - 1);
                        mover.winGame(pointx, pointy - 1);
                        return new Point(pointx, pointy-1);
                    }
                }
            } else if (num == 3) {
                int pointx = pos.coord.x;
                int pointy = pos.coord.y;
                while (pointx + 1 >= 0 && pointx + 1 <= 15 && pointy >= 0 && pointy <= 15 &&
                        Mover.board[pointx + 1][pointy] != 0 && Mover.board[pointx + 1][pointy] != player) {
                    pointx++;
                }
                if (pointx + 1 >= 0 && pointx + 1 <= 15 && pointy >= 0 && pointy <= 15 && Mover.board[pointx + 1][pointy] != player) {
                    mover.point(pointx + 1, pointy);
                    //controller.addImage(my, pointx + 1, pointy);
                    mover.winGame(pointx + 1, pointy);
                    return new Point(pointx+1, pointy);
                } else {
                    pointx = pos.coord.x;
                    pointy = pos.coord.y;
                    while (pointx - 1 >= 0 && pointx - 1 <= 15 && pointy >= 0 && pointy <= 15 &&
                            Mover.board[pointx - 1][pointy] != 0 && Mover.board[pointx - 1][pointy] != player) {
                        pointx--;
                    }
                    if (pointx - 1 >= 0 && pointx - 1 <= 15 && pointy >= 0 && pointy <= 15 && Mover.board[pointx - 1][pointy] != player) {
                        mover.point(pointx - 1, pointy);
                        //controller.addImage(my, pointx - 1, pointy);
                        mover.winGame(pointx - 1, pointy);
                        return new Point(pointx-1, pointy);
                    }
                }
            }
        }
        return null;
    }

    // враг для нас, мы для врага
    // getPosWithNumber(my, 4); для поиска в нашем массиве
    public Point moveAI() {
        analizeBoard();
        // ищем наши четверки
        ArrayList<SuperPosition> listPos = getPosWithNumber(my, 4);
        if (listPos.size() != 0) { // если они есть, то ходим и по возможности побеждаем
            findAndMove(enemy, listPos, 4);
        }
        listPos = getPosWithNumber(enemy, 4);
        if (listPos.size() != 0) {
            Point a = findAndMove(my, listPos, 4);
            if (a != null) {
                mover.changePlayer();
                return a;
            }
        }
        listPos = getPosWithNumber(enemy, 3);
        if (listPos.size() != 0) {
            Point a = findAndMove(my, listPos, 3);
            if (a != null) {
                mover.changePlayer();
                return a;
            }
        }
        listPos = getPosWithNumber(my, 3);
        if (listPos.size() != 0) {
            Point a = findAndMove(enemy, listPos, 3);
            if (a != null) {
                mover.changePlayer();
                return a;
            }
        }
        listPos = getPosWithNumber(my, 2);
        if (listPos.size() != 0) {
            Point a = findAndMove(enemy, listPos, 2);
            if (a != null) {
                mover.changePlayer();
                return a;
            }
        }
        listPos = getPosWithNumber(my, 1);
        if (listPos.size() != 0) {
            Point a = findAndMove(enemy, listPos, 1);
            if (a != null) {
                mover.changePlayer();
                return a;
            }
        }
        int a = 7 + (int) (Math.random() * 10);
        int b = 7 + (int) (Math.random() * 10);
        while (mover.canPoint(a, b) == false) {
            a = 7 + (int) (Math.random() * 10);
            b = 7 + (int) (Math.random() * 10);
        }
        mover.point(a, b);
        mover.changePlayer();
        return new Point(a, b);

    }

}

class SuperPosition {
    public Point coord;
    public int Dleft;
    public int Dright;
    public int V;
    public int G;

    public SuperPosition(Point coord, int dleft, int dright, int v, int g) {
        this.coord = coord;
        Dleft = dleft;
        Dright = dright;
        V = v;
        G = g;
    }
}
