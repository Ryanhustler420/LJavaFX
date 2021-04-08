package sample;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import sample.views.ViewFactory;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        ViewFactory viewFactory = new ViewFactory();
        primaryStage.setTitle("Raisehand Networks");
        primaryStage.getIcons().add(new Image("/sample/views/icons/icon_large.png"));
        primaryStage.setScene(viewFactory.getMainScene(1600, 720));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
