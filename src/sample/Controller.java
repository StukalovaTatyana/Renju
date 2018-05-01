package sample;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Line;
import org.w3c.dom.events.Event;

import javax.swing.*;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorpane;

    @FXML
    private GridPane gridpane;

    public Mover mover;

    @FXML
    void initialize() {
        /*for (int i = 0; i < 750; i+=50){
            anchorpane.getChildren().add(new Line(0, i, 750, i));
            anchorpane.getChildren().add(new Line(i, 0, i, 750));
        }*/
        mover = new Mover();
        gridpane.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
            int y = (int) (event.getX() / 50);
            int x = (int) (event.getY() / 50);
            if (mover.canPoint(x, y) == true) {
                mover.point(x, y);
                if (Mover.player == 1) {
                    try {
                        FileInputStream f = new FileInputStream("C:\\Users\\tatya\\Documents\\GitHub\\Renju\\Image\\Black.png");
                        ImageView imageView = new ImageView();
                        imageView.setImage(new Image(f));
                        gridpane.add(imageView, y, x);

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        FileInputStream f = new FileInputStream("C:\\Users\\tatya\\Documents\\GitHub\\Renju\\Image\\Pink.png");
                        ImageView imageView = new ImageView();
                        imageView.setImage(new Image(f));
                        gridpane.add(imageView, y, x);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                mover.winGame(x, y);
                mover.changePlayer();
            } else JOptionPane.showMessageDialog(null, "Ход невозможен");


        });

    }
}
