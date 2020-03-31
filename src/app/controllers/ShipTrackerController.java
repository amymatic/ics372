package app.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;


/**
 * Main controller class for the entire layout application
 */
public class ShipTrackerController {
    @FXML private StackPane pageHolder;

    public void setPage(Node node) {
        pageHolder.getChildren().setAll(node);
    }

    @FXML protected void handleImportShipmentsClick(ActionEvent event) {
        NavigationController.loadPage("/app/views/importShipments.fxml");
    }

    @FXML
    protected void handleAddWarehouseClick(ActionEvent event) {
        NavigationController.loadPage("/app/views/addWarehouse.fxml");
    }
    @FXML
    protected void handleManageWarehouseClick(ActionEvent event) {
        NavigationController.loadPage("/app/views/manageWarehouse.fxml");
    }
    @FXML
    protected void handleAddShipmentClick(ActionEvent event) {
        NavigationController.loadPage("/app/views/addShipment.fxml");
    }
    @FXML
    protected void handleManageShipmentClick(ActionEvent event) {
        NavigationController.loadPage("/app/views/manageShipment.fxml");
    }
    @FXML
    protected void handleWarehouseReportClick(ActionEvent event) {
        NavigationController.loadPage("/app/views/warehouseReport.fxml");
    }
    @FXML
    protected void handleShipmentReportClick(ActionEvent event) {
        NavigationController.loadPage("/app/views/shipmentReport.fxml");
    }

}