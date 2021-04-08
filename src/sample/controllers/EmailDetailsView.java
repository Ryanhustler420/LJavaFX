package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.web.WebView;
import sample.middleware.Singleton;

import java.net.URL;
import java.util.ResourceBundle;

public class EmailDetailsView implements Initializable {

    private Singleton singleton;

    @FXML
    private WebView webview;

    @FXML
    private Label subjectLabel;

    @FXML
    private Label senderLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        singleton = Singleton.getInstance();
        System.out.println("Loaded Extra Dialog");

        subjectLabel.setText("Subject: " + singleton.getMessage().getSubject());
        senderLabel.setText("Sender: " + singleton.getMessage().getSender());
        webview.getEngine().loadContent(singleton.getMessage().getBody());

    }

}
