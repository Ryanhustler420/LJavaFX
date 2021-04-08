package sample.controllers;

import sample.models.beans.EmailMessageBean;
import sample.models.SampleData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import sample.middleware.Singleton;
import sample.views.ViewFactory;

import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;

public class MainView implements Initializable {

    private SampleData sampleData = new SampleData();
    private TreeItem<String> root = new TreeItem<>();
    private MenuItem showDetails = new MenuItem("Show Details");
    private Singleton singleton;

    @FXML
    private Button newButton;

    @FXML
    private WebView messageRenderer;

    @FXML
    private TreeView<String> emailFolderTreeView;

    @FXML
    private TableView<EmailMessageBean> emailTableView;

    @FXML
    private TableColumn<EmailMessageBean, String> subjectCol;

    @FXML
    private TableColumn<EmailMessageBean, String> senderCol;

    @FXML
    private TableColumn<EmailMessageBean, String> sizeCol;

    @FXML
    void newButtonAction(ActionEvent event) {
        System.out.println("New Button");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ViewFactory viewFactory = new ViewFactory();
        singleton = Singleton.getInstance();

        subjectCol.setCellValueFactory(new PropertyValueFactory<>("Subject"));
        senderCol.setCellValueFactory(new PropertyValueFactory<>("Sender"));
        sizeCol.setCellValueFactory(new PropertyValueFactory<>("Size"));

        sizeCol.setComparator(new Comparator<String>() {
            Integer int1, int2;

            @Override
            public int compare(String o1, String o2) {
                int1 = EmailMessageBean.getFormattedValues().get(o1);
                int2 = EmailMessageBean.getFormattedValues().get(o2);
                return int1.compareTo(int2);
            }
        });

        emailFolderTreeView.setRoot(root);
        root.setValue("raisehand.io@gmail.com");
        root.setGraphic(viewFactory.resolveIcon("raisehand.io@gmail.com"));

        TreeItem<String> Inbox = new TreeItem<>("Inbox", viewFactory.resolveIcon("Inbox"));
        TreeItem<String> Sent = new TreeItem<>("Sent", viewFactory.resolveIcon("Sent"));
        TreeItem<String> SubSentDirect = new TreeItem<>("Direct");
        TreeItem<String> SubSentDrop = new TreeItem<>("Drop");
        Sent.getChildren().addAll(SubSentDirect, SubSentDrop);
        TreeItem<String> Spam = new TreeItem<>("Spam", viewFactory.resolveIcon("Spam"));
        TreeItem<String> Trash = new TreeItem<>("Trash", viewFactory.resolveIcon("Trash"));

        root.getChildren().addAll(Inbox, Sent, Spam, Trash);
        root.setExpanded(true);

        emailTableView.setContextMenu(new ContextMenu(showDetails));
        emailFolderTreeView.setOnMouseClicked(event -> {
            TreeItem<String> item = emailFolderTreeView.getSelectionModel().getSelectedItem();
            if (item == null) return;
            emailTableView.setItems(sampleData.emailFolders.get(item.getValue()));
        });

        emailTableView.setOnMouseClicked(event -> {
            EmailMessageBean messageBean = emailTableView.getSelectionModel().getSelectedItem();
            if (messageBean == null) return;
            messageRenderer.getEngine().loadContent(messageBean.getBody());
            singleton.setMessage(messageBean);
        });

        showDetails.setOnAction(event -> {
            Stage stage = new Stage();
            Scene messageBoxScene = viewFactory.getMessageBoxScene(1024, 600);

            stage.getIcons().add(new Image("/sample/views/icons/icon_large.png"));
            stage.setTitle("Message Box");
            stage.setScene(messageBoxScene);
            stage.showAndWait();
        });

    }

}
