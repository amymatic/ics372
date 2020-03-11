import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class ShipTracker extends Application {
   /* @Override
    public void start(Stage homeStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("shiptracker.fxml")));
        homeStage.setTitle("ShipTracker");
        homeStage.setWidth(800);
        homeStage.setHeight(500);
        homeStage.setScene(new Scene(root, 625, 400));
        homeStage.show();
    }*/

    @Override
    public void start(Stage stage) throws Exception{
        stage.setTitle("ShipTracker");

        stage.setScene(
                createScene(
                        loadMainPane()
                )
        );

        stage.show();
    }

    /**
     * Loads the main fxml layout.
     * Sets up the page switching NavigationController.
     * Loads the first page into the fxml layout.
     *
     * @return the loaded pane.
     * @throws IOException if the pane could not be loaded.
     */
    private Pane loadMainPane() throws IOException {
        FXMLLoader loader = new FXMLLoader();

        Pane mainPane = (Pane) loader.load(
                getClass().getResourceAsStream(
                        NavigationController.MAIN
                )
        );

        ShipTrackerController shipTrackerController = loader.getController();

        NavigationController.setMainController(shipTrackerController);
        NavigationController.loadPage(NavigationController.IMPORT_SHIPMENTS);

        return mainPane;
    }

    /**
     * Creates the main application scene.
     *
     * @param mainPane the main application layout.
     *
     * @return the created scene.
     */
    private Scene createScene(Pane mainPane) {
        Scene scene = new Scene(
                mainPane
        );

        scene.getStylesheets().setAll(
                getClass().getResource("shiptracker.css").toExternalForm()
        );

        return scene;
    }

    public static void main(String[] args) {
        launch(args);
    }
}