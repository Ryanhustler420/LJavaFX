package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import sample.models.beans.EmailAccountBean;
import sample.models.beans.EmailMessageBean;
import sample.views.ViewFactory;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        ViewFactory viewFactory = ViewFactory.singleton;
        primaryStage.setTitle("Raisehand Networks");
        primaryStage.getIcons().add(new Image("/sample/views/icons/icon_large.png"));
        primaryStage.setScene(viewFactory.getMainScene());
        primaryStage.show();

        EmailAccountBean emailAccountBean = new EmailAccountBean("nahi@gmail.com", "@dona.io");

        ObservableList<EmailMessageBean> data = FXCollections.observableArrayList();
        emailAccountBean.addEmailsToData(data);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
