package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

public class EmailDetailsView extends AbstractController implements Initializable {

    public EmailDetailsView(ModelAccess access) {
        super(access);
    }

    @FXML
    private WebView webview;

    @FXML
    private Label subjectLabel;

    @FXML
    private Label senderLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        subjectLabel.setText("Subject: " + getModelAccess().getSelectedMessage().getSubject());
        senderLabel.setText("Sender: " + getModelAccess().getSelectedMessage().getSender());
        webview.getEngine().loadContent(getModelAccess().getSelectedMessage().getBody());
    }

}
