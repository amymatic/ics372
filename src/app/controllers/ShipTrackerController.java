package app.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;


/**
 * Main controller class for the entire layout application
 */
public class ShipTrackerController {
    @FXML
    private StackPane pageHolder;

    public void setPage(Node node) {
        pageHolder.getChildren().setAll(node);
    }

    @FXML
    private GridPane shipTrackerMainPane;

    @FXML
    private Hyperlink importShipmentsLink;

    @FXML
    private StackPane importShipmentsPane;

    @FXML
    private Hyperlink addWarehouseLink;

    @FXML
    private StackPane addWarehousePane;

    @FXML
    private Hyperlink manageWarehouseLink;

    @FXML
    private StackPane manageWarehousePane;

    @FXML
    private Hyperlink addShipmentLink;

    @FXML
    private StackPane addShipmentPane;

    @FXML
    private Hyperlink manageShipmentLink;

    @FXML
    private StackPane manageShipmentPane;

    @FXML
    private Hyperlink warehouseReportLink;

    @FXML
    private StackPane warehouseReportPane;

    @FXML
    private Hyperlink shipmentReportLink;

    @FXML
    private StackPane shipmentReportPane;

    @FXML
    protected void handleImportShipmentsClick(ActionEvent event) throws JSONException, IOException {
        String fileName ="src/resources/xmlFiles/";//to be determined by user selection
        String data="";

        //todo upload/select file from our project or anyhwere
        NavigationController.loadPage(NavigationController.IMPORT_SHIPMENTS);
        ListView<String> listView = new ListView<>();

        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File(fileName));
        File selectedFile = fc.showOpenDialog(null);

        listView.getItems().add(selectedFile.getName());
        fileName += selectedFile.getName();

        //todo convert xml to json
        File myObj = new File(fileName);
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            data += myReader.nextLine();
        }
        myReader.close();
        //convert to json
        JSONObject obj = XML.toJSONObject(data);

        //todo write to json
        FileWriter myWriter = new FileWriter("src/resources/import.json");
        myWriter.write(obj.toString());
        myWriter.close();
    }

    @FXML
    protected void handleAddWarehouseClick(ActionEvent event) {
        NavigationController.loadPage(NavigationController.ADD_WAREHOUSE);
    }

    @FXML
    protected void handleManageWarehouseClick(ActionEvent event) {
        NavigationController.loadPage(NavigationController.MANAGE_WAREHOUSE);
    }

    @FXML
    protected void handleAddShipmentClick(ActionEvent event) {
        NavigationController.loadPage(NavigationController.ADD_SHIPMENT);
    }

    @FXML
    protected void handleManageShipmentClick(ActionEvent event) {
        NavigationController.loadPage(NavigationController.MANAGE_SHIPMENT);
    }

    @FXML
    protected void handleWarehouseReportClick(ActionEvent event) {
        NavigationController.loadPage(NavigationController.WAREHOUSE_REPORT);
    }

    @FXML
    protected void handleShipmentReportClick(ActionEvent event) {
        NavigationController.loadPage(NavigationController.SHIPMENT_REPORT);
    }
}