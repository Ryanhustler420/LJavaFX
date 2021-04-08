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
import sample.models.folder.EmailFolderBean;
import sample.models.table.BoldableRowFactory;
import sample.views.ViewFactory;

import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;

public class MainView extends AbstractController implements Initializable {

    private SampleData sampleData = new SampleData();
    private MenuItem showDetails = new MenuItem("Show Details");

    public MainView(ModelAccess access) {
        super(access);
    }

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

    @FXML
    void changeReadAction(ActionEvent event) {
        EmailMessageBean messageBean = getModelAccess().getSelectedMessage();
        if (messageBean == null) return;
        boolean value = messageBean.isRead();
        messageBean.setRead(!value);
        EmailFolderBean<String> selectedFolder = getModelAccess().getSelectedFolder();
        if (selectedFolder == null) return;
        if (value) selectedFolder.incrementUnreadMessagesCount(1);
        else selectedFolder.decrementUnreadMessagesCount();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        emailTableView.setRowFactory(e -> new BoldableRowFactory());
        ViewFactory viewFactory = ViewFactory.singleton;

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

        EmailFolderBean<String> root = new EmailFolderBean<>("");
        emailFolderTreeView.setRoot(root);
        emailFolderTreeView.setShowRoot(false);
        EmailFolderBean<String> raisehand = new EmailFolderBean<>("raisehand.io@gmail.com");
        root.getChildren().add(raisehand);

        EmailFolderBean<String> Inbox = new EmailFolderBean<>("Inbox", "CompleteInbox");
        EmailFolderBean<String> Sent = new EmailFolderBean<>("Sent", "CompleteSent");
        Sent.getChildren().add(new EmailFolderBean<>("SubSentDraft", "CompleteSubSentDraft"));
        EmailFolderBean<String> Spam = new EmailFolderBean<>("Spam", "CompleteSpam");

        raisehand.getChildren().addAll(Inbox, Sent, Spam);

        Inbox.getData().addAll(SampleData.Inbox);
        Inbox.getData().addAll(SampleData.Sent);
        Inbox.getData().addAll(SampleData.Spam);

        emailTableView.setContextMenu(new ContextMenu(showDetails));

        emailFolderTreeView.setOnMouseClicked(event -> {
            EmailFolderBean<String> item = (EmailFolderBean<String>) emailFolderTreeView.getSelectionModel().getSelectedItem();
            if (item == null && item.isTopElement()) return;
            emailTableView.setItems(item.getData());
            getModelAccess().setSelectedFolder(item);
            // Clear the selected message:
            getModelAccess().setSelectedMessage(null);
        });

        emailTableView.setOnMouseClicked(event -> {
            EmailMessageBean messageBean = emailTableView.getSelectionModel().getSelectedItem();
            if (messageBean == null) return;
            messageRenderer.getEngine().loadContent(messageBean.getBody());
            getModelAccess().setSelectedMessage(messageBean);
        });

        showDetails.setOnAction(event -> {
            Stage stage = new Stage();
            Scene messageBoxScene = viewFactory.getMessageBoxScene();

            stage.getIcons().add(new Image("/sample/views/icons/icon_large.png"));
            stage.setTitle("Message Box");
            stage.setScene(messageBoxScene);
            stage.showAndWait();
        });

    }

}
