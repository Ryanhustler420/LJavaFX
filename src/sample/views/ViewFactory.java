package sample.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;

public class ViewFactory {

    public Scene getMainScene(double widht, double height) {
        Pane pane;

        try {
            pane = FXMLLoader.load(getClass().getResource("layouts/main_stage_view.fxml"));
        } catch (Exception e) {
            pane = null;
            e.printStackTrace();
        }

        Scene scene = new Scene(pane, widht, height);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        return scene;
    }

    public Scene getMainScene() {
        Pane pane;

        try {
            pane = FXMLLoader.load(getClass().getResource("layouts/main_stage_view.fxml"));
        } catch (Exception e) {
            pane = null;
            e.printStackTrace();
        }

        Scene scene = new Scene(pane);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        return scene;
    }

    public Scene getMessageBoxScene() {
        Pane pane;

        try {
            pane = FXMLLoader.load(getClass().getResource("layouts/email_details_view.fxml"));
        } catch (Exception e) {
            pane = null;
            e.printStackTrace();
        }

        Scene scene = new Scene(pane);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        return scene;
    }

    public Scene getMessageBoxScene(double width, double height) {
        Pane pane;

        try {
            pane = FXMLLoader.load(getClass().getResource("layouts/email_details_view.fxml"));
        } catch (Exception e) {
            pane = null;
            e.printStackTrace();
        }

        Scene scene = new Scene(pane, width, height);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        return scene;
    }

    public Node resolveIcon(String treeItemName) {
        String s = treeItemName.toLowerCase();
        ImageView returnIcon;
        try {
            if (s.contains("inbox")) {
                returnIcon = new ImageView(new Image(getClass().getResourceAsStream("icons/block_empty.png")));
            } else if (s.contains("sent")) {
                returnIcon = new ImageView(new Image(getClass().getResourceAsStream("icons/block_fill.png")));
            } else if (s.contains("spam")) {
                returnIcon = new ImageView(new Image(getClass().getResourceAsStream("icons/block_empty.png")));
            } else if (s.contains("@")) {
                returnIcon = new ImageView(new Image(getClass().getResourceAsStream("icons/block_fill.png")));
            } else {
                returnIcon = new ImageView(new Image(getClass().getResourceAsStream("icons/block_empty.png")));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Invalid image location!");
            returnIcon = new ImageView();
        }

        returnIcon.setFitHeight(16);
        returnIcon.setFitWidth(16);

        return returnIcon;
    }

}
