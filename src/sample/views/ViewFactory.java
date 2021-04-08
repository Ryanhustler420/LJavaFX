package sample.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sample.controllers.AbstractController;
import sample.controllers.EmailDetailsView;
import sample.controllers.MainView;
import sample.controllers.ModelAccess;

import java.net.URL;

public class ViewFactory {

    public static ViewFactory singleton = new ViewFactory();
    private static boolean mainViewInitialized = false;

    private final String DEFAULT_STYLE_SHEET = "styles.css";
    private final String EMAIL_DETAILS_FXML = "layouts/email_details_view.fxml";
    private final String MAIN_FXML = "layouts/main_stage_view.fxml";

    private ModelAccess modelAccess = new ModelAccess();

    private MainView mainViewController;
    private EmailDetailsView emailDetailsController;

    public Scene getMainScene() throws Exception {
        if (!mainViewInitialized) {
            mainViewInitialized = true;
            mainViewController = new MainView(modelAccess);
            return initializeScene(MAIN_FXML, mainViewController);
        } else throw new Exception("Illegal action invocation preformed, main scene already initialized");
    }

    public Scene getMessageBoxScene() {
        emailDetailsController = new EmailDetailsView(modelAccess);
        return initializeScene(EMAIL_DETAILS_FXML, emailDetailsController);
    }

    private Scene initializeScene(String fxmlPath, AbstractController abstractController) {
        FXMLLoader loader;
        Parent parent;
        Scene scene;
        try {
            loader = new FXMLLoader(getClass().getResource(fxmlPath));
            loader.setController(abstractController);
            parent = loader.load();
        } catch (Exception e) {
            return null;
        }

        scene = new Scene(parent);
        scene.getStylesheets().add(getClass().getResource(DEFAULT_STYLE_SHEET).toExternalForm());
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
