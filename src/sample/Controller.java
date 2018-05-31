package sample;

import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorpane;

    @FXML
    public GridPane gridpane;

    public Mover mover;

    public AI ai = new AI(2, 1);

    @FXML
    void initialize() {

        mover = new Mover();

        gridpane.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
            int y = (int) (event.getX() / 50);
            int x = (int) (event.getY() / 50);
            if (mover.canPoint(x, y) == true) {
                mover.point(x, y);
                addImage(Mover.player, x, y);
                mover.winGame(x, y);

                mover.changePlayer();
                Point p = ai.moveAI();
                addImage(ai.my, p.x, p.y);
                for (int i = 0; i < Mover.board.length; i++) {
                    for (int j = 0; j < Mover.board[i].length; j++) {
                        System.out.print(Mover.board[i][j] +" ");
                    }
                    System.out.println();
                }
            } else JOptionPane.showMessageDialog(null, "Ход невозможен");
        });
    }

    public void addImage(int player, int x, int y) {
        if (player == 1) {
            try {
                FileInputStream f = new FileInputStream("C:\\Users\\alexey\\Desktop\\АГУ\\GitHub\\Renju\\Image\\30ch.png");
                ImageView imageView = new ImageView();
                imageView.setImage(new Image(f));
                gridpane.add(imageView, y, x);
                GridPane.setHalignment(imageView, HPos.CENTER);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            try {
                FileInputStream f = new FileInputStream("C:\\Users\\alexey\\Desktop\\АГУ\\GitHub\\Renju\\Image\\30b.png");
                ImageView imageView = new ImageView();
                imageView.setImage(new Image(f));
                gridpane.add(imageView, y, x);
                GridPane.setHalignment(imageView, HPos.CENTER);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
