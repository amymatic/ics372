package app.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import app.models.*;

import java.util.ArrayList;

/**
 * Main controller class for the entire layout application
 */
public class ShipTrackerController {
    @FXML private StackPane pageHolder;

    public void setPage(Node node) {
        pageHolder.getChildren().setAll(node);
    }

    @FXML private GridPane shipTrackerMainPane;
    @FXML private Hyperlink importShipmentsLink;
    @FXML private StackPane importShipmentsPane;
    @FXML private Hyperlink addWarehouseLink;
    @FXML private StackPane addWarehousePane;
    @FXML private Hyperlink manageWarehouseLink;
    @FXML private StackPane manageWarehousePane;
    @FXML private Hyperlink addShipmentLink;
    @FXML private StackPane addShipmentPane;
    @FXML private Hyperlink manageShipmentLink;
    @FXML private StackPane manageShipmentPane;
    @FXML private Hyperlink warehouseReportLink;
    @FXML private StackPane warehouseReportPane;
    @FXML private Hyperlink shipmentReportLink;
    @FXML private StackPane shipmentReportPane;

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