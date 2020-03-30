package app.models;

import java.io.FileReader;
import java.io.IOException;
import app.controllers.NavigationController;
import app.controllers.ShipTrackerController;
import app.controllers.ShipmentsReportController;
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

    public static WarehouseManager warehouseMgr = new WarehouseManager();

    /**
     * The main method launches the GUI
     * @param args TBD
     */
    public static void main(String[] args) throws IOException, ParseException {
        loadWarehouses("src/resources/warehouses.json");
        loadShipments("src/resources/shipments.json");
        System.out.println("Warehouses (main): " + warehouseMgr.getWarehouses().size());
        launch(args);
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

        ShipmentsReportController.setMainController(shipTrackerController);

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

    /**
     * The loadWarehouses method is a private method that loads all warehouses into the system
     * before the program is launched.
     * @param fileName The name of the file
     */
    private static void loadWarehouses(String fileName) throws
            IOException, ParseException {
        JSONParser parser = new JSONParser();
        FileReader file = new FileReader(fileName);
        JSONObject warehousesObject = (JSONObject) parser.parse(file);
        JSONArray warehousesArray = (JSONArray) warehousesObject.get("warehouses");

        for ( Object warehouse : warehousesArray ) {
            JSONObject warehouseObject = (JSONObject) warehouse;

            int wId = Integer.parseInt(warehouseObject.get("warehouse_id").toString());
            boolean air = (boolean) warehouseObject.get("air");
            boolean rail = (boolean) warehouseObject.get("rail");
            boolean truck = (boolean) warehouseObject.get("truck");
            boolean ship = (boolean) warehouseObject.get("ship");
            String wName = warehouseObject.get("name").toString();
            boolean wReceiving = (boolean) warehouseObject.get("receiving");
            warehouseMgr.addWarehouse(wId, air, rail, truck, ship, wName, wReceiving);
        }
    }

    /**
     * The loadShipments method is a private method that loads all shipments into the system
     * before the program is launched.
     * @param fileName The name of the file
     */
    private static void loadShipments(String fileName) throws
            IOException, ParseException {
        JSONParser parser = new JSONParser();
        FileReader file = new FileReader(fileName);
        JSONObject shipmentsObject = (JSONObject) parser.parse(file);
        JSONArray shipmentsArray = (JSONArray) shipmentsObject.get("shipments");

        for ( Object shipment : shipmentsArray ) {
            JSONObject shipmentObject = (JSONObject) shipment;

            int wID = Integer.parseInt(shipmentObject.get("warehouse_id").toString());
            String sMode = (String) shipmentObject.get("shipment_method");
            String sID = (String) shipmentObject.get("shipment_id");
            float sWeight = Float.parseFloat(shipmentObject.get("weight").toString());
            long rDate = (long) shipmentObject.get("receipt_date");

            // Create the shipment
            Shipment newShipment = new Shipment(sID, sMode, sWeight, wID, rDate);

            // Add the shipment to the Warehouse's records
            for (Warehouse wh : warehouseMgr.getWarehouses()) {
                if (wh.getWarehouseID() == wID) {
                    wh.recordShipment(newShipment);
                }
            }
        }
    }
}