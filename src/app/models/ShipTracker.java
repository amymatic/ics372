package app.models;

import java.io.FileReader;
import java.io.IOException;
import app.controllers.NavigationController;
import app.controllers.ShipTrackerController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import app.jsonsimple.*;

/**
 * The ShipTracker class demonstrates the functionality of a simple shipping
 * management software.
 */
public class ShipTracker extends Application {

    WarehouseManager warehouseMgr = new WarehouseManager();

    /**
     * The main method launches the GUI
     * @param args TBD
     */
    public static void main(String[] args) {
        //loadWarehouses(warehouses.xml);
        //loadShipments(shipments.xml);
        launch(args);
    }

    /**
     * NOTE: THis is not currently used as it was for Assignment 1 - TBD if still useful.
     * The setupWarehouses method is a private method that is run prior to
     * reading and storing the shipments, to ensure that the necessary
     * warehouses exist. Note that this client of WarehouseManager is
     * validating to ensure each warehouse ID in its inventory is unique.
     * @param fileName The name of the file
     */
    private void setupWarehouses(String fileName) throws
            IOException, ParseException {
        JSONParser parser = new JSONParser();
        FileReader file = new FileReader(fileName);
        JSONObject shipmentsObject = (JSONObject) parser.parse(file);
        JSONArray shipmentsArray =
                (JSONArray) shipmentsObject.get("example");

        for ( Object shipment : shipmentsArray ) {
            JSONObject shipmentObject = (JSONObject) shipment;
            boolean warehouseExists = false;
            String warehouseIdString =
                    shipmentObject.get("warehouse_id").toString();
            int warehouseId = Integer.parseInt(warehouseIdString);
            for ( Warehouse wh : warehouseMgr.getWarehouses() ) {
                if (wh.getWarehouseID() == warehouseId) {
                    warehouseExists = true;
                    break;
                }
            }
            if (!warehouseExists) {
                warehouseMgr.addWarehouse(warehouseId);
            }
        }
    }
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
     * @return the loaded pane
     * @throws IOException if the pane could not be loaded
     */
    private Pane loadMainPane() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setClassLoader(getClass().getClassLoader());
        loader.setLocation(getClass().getClassLoader().getResource(NavigationController.MAIN));

        Pane mainPane = (Pane) loader.load(
                getClass().getClassLoader().getResourceAsStream(
                        "shiptracker.fxml"
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
     * @param mainPane the main application layout
     * @return the created scene
     */
    private Scene createScene(Pane mainPane) {
        Scene scene = new Scene(
                mainPane
        );

        // Aspirational - we can add styling later if we get to it...
        /*scene.getStylesheets().setAll(
                getClass().getResource("/resources/shiptracker.css").toExternalForm()
        );*/

        return scene;
    }

    //private void loadWarehouses()


}