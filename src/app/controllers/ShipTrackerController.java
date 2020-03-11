import java.awt.event.MouseEvent;
import java.io.*;
import java.util.Objects;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Window;

/**
 * Main controller class for the entire layout application
 */
public class ShipTrackerController {
    @FXML
    private StackPane pageHolder;

    public void setPage(Node node) {
        pageHolder.getChildren().setAll(node);
    }

    /*@FXML
    private GridPane shipTrackerMainPane;

    @FXML
    private GridPane shipTrackerStartPane;

    @FXML
    private TextField nameField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button submitButton;

    @FXML
    private Hyperlink importShipmentsLink;

    @FXML
    private Group importShipmentsGroup;

    @FXML
    private GridPane importShipmentsPane;

    @FXML
    private Hyperlink addWarehouseLink;

    @FXML
    private Group addWarehouseGroup;

    @FXML
    private GridPane addWarehousePane;

    @FXML
    private Hyperlink manageWarehouseLink;

    @FXML
    private Group manageWarehouseGroup;

    @FXML
    private GridPane manageWarehousePane;

    @FXML
    private Hyperlink addShipmentLink;

    @FXML
    private Group addShipmentGroup;

    @FXML
    private GridPane addShipmentPane;

    @FXML
    private Hyperlink manageShipmentLink;

    @FXML
    private Group manageShipmentGroup;

    @FXML
    private GridPane manageShipmentPane;

    @FXML
    private Hyperlink warehouseReportLink;

    @FXML
    private Group warehouseReportGroup;

    @FXML
    private GridPane warehouseReportPane;

    @FXML
    private Hyperlink shipmentReportLink;

    @FXML
    private Group shipmentReportGroup;

    @FXML
    private GridPane shipmentReportPane;

    @FXML
    protected void handleImportShipmentsClick(ActionEvent event) throws IOException {
        //Window owner = importShipmentsLink.getScene().getWindow();
        //AlertHelper.showAlert(Alert.AlertType.INFORMATION, owner, "You clicked import shipments",
          //      "More to come");
        //Group newLoadedPane =  FXMLLoader.load(Objects.requireNonNull(getClass().getResource("importShipments.fxml")));
        importShipmentsPane.getChildren().setAll(FXMLLoader.load(getClass().getClassLoader().getResource("importShipments.fxml"));
        shipTrackerMainPane.getChildren().add(importShipments);
    }*/

    @FXML
    protected void handleImportShipmentsClick(ActionEvent event) {
        NavigationController.loadPage(NavigationController.IMPORT_SHIPMENTS);
    }


    /*@FXML
    protected void handleAddWarehouseClick(ActionEvent event) throws IOException {
        //Window owner = addWarehouseLink.getScene().getWindow();
        //AlertHelper.showAlert(Alert.AlertType.INFORMATION, owner, "You clicked add warehouse",
          //      "More to come");
        Group newLoadedPane =  FXMLLoader.load(getClass().getResource("addWarehouse.fxml"));
        addWarehouseGroup.getChildren().add(newLoadedPane);
    }*/

    @FXML
    protected void handleAddWarehouseClick(ActionEvent event) {
        NavigationController.loadPage(NavigationController.ADD_WAREHOUSE);
    }

    /*@FXML
    protected void handleManageWarehouseClick(ActionEvent event) throws IOException {
        //Window owner = manageWarehouseLink.getScene().getWindow();
        //AlertHelper.showAlert(Alert.AlertType.INFORMATION, owner, "You clicked manage warehouse",
          //      "More to come");
        Group newLoadedPane =  FXMLLoader.load(getClass().getResource("manageWarehouse.fxml"));
        manageWarehouseGroup.getChildren().add(newLoadedPane);
    }*/

    @FXML
    protected void handleManageWarehouseClick(ActionEvent event) {
        NavigationController.loadPage(NavigationController.MANAGE_WAREHOUSE);
    }

    /*@FXML
    protected void handleAddShipmentClick(ActionEvent event) throws IOException {
        //Window owner = addShipmentLink.getScene().getWindow();
        //AlertHelper.showAlert(Alert.AlertType.INFORMATION, owner, "You clicked add shipment",
          //      "More to come");
        Group newLoadedPane =  FXMLLoader.load(getClass().getResource("addShipment.fxml"));
        addShipmentGroup.getChildren().add(newLoadedPane);
    }*/

    @FXML
    protected void handleAddShipmentClick(ActionEvent event) {
        NavigationController.loadPage(NavigationController.ADD_SHIPMENT);
    }

    /*@FXML
    protected void handleManageShipmentClick(ActionEvent event) throws IOException {
        //Window owner = manageShipmentLink.getScene().getWindow();
        //AlertHelper.showAlert(Alert.AlertType.INFORMATION, owner, "You clicked manage shipment",
          //      "More to come");
        Group newLoadedPane =  FXMLLoader.load(getClass().getResource("manageShipment.fxml"));
        manageShipmentGroup.getChildren().add(newLoadedPane);
    }*/

    @FXML
    protected void handleManageShipmentClick(ActionEvent event) {
        NavigationController.loadPage(NavigationController.MANAGE_SHIPMENT);
    }

    /*@FXML
    protected void handleWarehouseReportClick(ActionEvent event) throws IOException {
        //Window owner = warehouseReportLink.getScene().getWindow();
        //AlertHelper.showAlert(Alert.AlertType.INFORMATION, owner, "You clicked warehouse report",
          //      "More to come");
        Group newLoadedPane =  FXMLLoader.load(getClass().getResource("warehouseReport.fxml"));
        warehouseReportGroup.getChildren().add(newLoadedPane);
    }*/

    @FXML
    protected void handleWarehouseReportClick(ActionEvent event) {
        NavigationController.loadPage(NavigationController.WAREHOUSE_REPORT);
    }

    /*@FXML
    protected void handleShipmentReportClick(ActionEvent event) throws IOException {
        //Window owner = shipmentReportLink.getScene().getWindow();
        //AlertHelper.showAlert(Alert.AlertType.INFORMATION, owner, "You clicked shipment report",
          //      "More to come");
        Group newLoadedPane =  FXMLLoader.load(getClass().getResource("shipmentReport.fxml"));
        shipmentReportGroup.getChildren().add(newLoadedPane);
    }*/

    @FXML
    protected void handleShipmentReportClick(ActionEvent event) {
        NavigationController.loadPage(NavigationController.SHIPMENT_REPORT);
    }
}