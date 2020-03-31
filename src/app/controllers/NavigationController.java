package app.controllers;

import javafx.fxml.FXMLLoader;
import java.io.IOException;

/**
 * Utility class for controlling navigation between pages of the application.
 *
 * All methods on the navigator are static to facilitate
 * simple access from anywhere in the application.
 */
public class NavigationController {

    /**
     * Convenience constants for fxml layouts managed by the navigator.
     */
    public static final String MAIN = "/app/views/shiptracker.fxml";
    public static final String IMPORT_SHIPMENTS = "/app/views/importShipments.fxml";
    public static final String ADD_WAREHOUSE = "/app/views/addWarehouse.fxml";
    public static final String MANAGE_WAREHOUSE = "/app/views/manageWarehouse.fxml";
    public static final String ADD_SHIPMENT = "/app/views/addShipment.fxml";
    public static final String MANAGE_SHIPMENT = "/app/views/manageShipment.fxml";
    public static final String WAREHOUSE_REPORT = "/app/views/warehouseReport.fxml";
    public static final String SHIPMENT_REPORT = "/app/views/shipmentReport.fxml";

    /** The main application layout controller. */
    private static ShipTrackerController shipTrackerController;

    /**
     * Stores the main controller for later use in navigation tasks.
     *
     * @param controller the main application layout controller.
     */
    public static void setMainController(ShipTrackerController controller) {
        NavigationController.shipTrackerController = controller;
    }

    /**
     * Loads the page specified by the fxml file into the
     * holder pane of the main application layout.
     *
     * Previously loaded page for the same fxml file are not cached.
     * The fxml is loaded anew and a new vista node hierarchy generated
     * every time this method is invoked.
     *
     * A more sophisticated load function could potentially add some
     * enhancements or optimizations, for example:
     *   cache FXMLLoaders
     *   cache loaded vista nodes, so they can be recalled or reused
     *   allow a user to specify vista node reuse or new creation
     *   allow back and forward history like a browser
     *
     * @param fxml the fxml file to be loaded.
     */
    public static void loadPage(String fxml) {
        try {
            shipTrackerController.setPage(
                    FXMLLoader.load(NavigationController.class.getResource(fxml))
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}