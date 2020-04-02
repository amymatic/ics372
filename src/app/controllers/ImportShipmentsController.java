package app.controllers;

import app.helpers.AlertHelper;
import app.jsonsimple.ParseException;
import app.models.ShipTracker;
import app.models.Shipment;
import app.models.Warehouse;
import app.models.WarehouseManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class ImportShipmentsController {
    public static final String RESET_TEXT = "(no file selected)";
    @FXML
    private StackPane importShipmentsPane;
    @FXML
    private Label selectedJSONFileLabel;
    @FXML
    private Label selectedXMLFileLabel;

    @FXML
    protected void handleSearchJSONFilesButtonClick(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select JSON File to Import");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Importable Files", "*.json"));
        File selectedFile = fileChooser.showOpenDialog(importShipmentsPane.getScene().getWindow());
        if (selectedFile != null) {
            selectedJSONFileLabel.setText(selectedFile.toString());
        }
    }

    @FXML
    protected void handleSearchXMLFilesButtonClick(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select XML File to Import");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Importable Files", "*.xml"));
        File selectedFile = fileChooser.showOpenDialog(importShipmentsPane.getScene().getWindow());
        if (selectedFile != null) {
            selectedXMLFileLabel.setText(selectedFile.toString());
        }
    }

    @FXML
    protected void handleImportJSONShipmentsButtonClick(ActionEvent event) throws IOException, ParseException {
        if(selectedJSONFileLabel.getText().equals(RESET_TEXT)) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, importShipmentsPane.getScene().getWindow(),
                    "Form Error!", "Please select a file");
            return;
        }

        try {
            ArrayList<String> invalidShipments = ShipTracker.warehouseMgr.createExistingShipmentsFromJSON(
                    selectedJSONFileLabel.getText());
            selectedJSONFileLabel.setText(RESET_TEXT);
            if (invalidShipments.isEmpty()) {
                AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, importShipmentsPane.getScene().getWindow(),
                        "Success", "Shipments imported");
            } else {
                AlertHelper.showAlert(Alert.AlertType.INFORMATION, importShipmentsPane.getScene().getWindow(),
                        "Note", "These shipments were not imported because the warehouse does not exist: "
                                + invalidShipments.toString());
            }
        } catch (Exception e) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, importShipmentsPane.getScene().getWindow(),
                    "Failure", "Shipments not imported");
        }
    }

    @FXML
    protected void handleImportXMLShipmentsButtonClick(ActionEvent event) throws IOException, ParseException, JAXBException {
        if(selectedXMLFileLabel.getText().equals(RESET_TEXT)) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, importShipmentsPane.getScene().getWindow(),
                    "Form Error!", "Please select a file");
            return;
        }

        JAXBContext jaxbContext = JAXBContext.newInstance(WarehouseManager.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        try {
            WarehouseManager whManager = (WarehouseManager) unmarshaller.unmarshal(new File(selectedXMLFileLabel.getText()));
            ArrayList<Warehouse> newWarehouses = getNewWarehouses(whManager);
            if (newWarehouses.isEmpty()) {
                addShipmentsFromXML(whManager);
                AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, importShipmentsPane.getScene().getWindow(),
                        "Success", "Shipments imported");
            } else {
                AlertHelper.showAlert(Alert.AlertType.ERROR, importShipmentsPane.getScene().getWindow(),
                        "Failure", "One or more warehouses do not exist");
            }
        } catch (Exception e) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, importShipmentsPane.getScene().getWindow(),
                        "Failure", "Shipments not imported");
        }
    }

    private ArrayList<Warehouse> getNewWarehouses(WarehouseManager whManager) {
        ArrayList<Warehouse> newWarehouses = new ArrayList<Warehouse>();
        for ( Warehouse newWH : whManager.getWarehouses() ) {
            boolean exists = false;
            for ( Warehouse wh : ShipTracker.warehouseMgr.getWarehouses() ) {
                if (newWH.getWarehouseID() == wh.getWarehouseID()) { exists = true; }
            }
            if (!exists) { newWarehouses.add(newWH); }
        }
        return newWarehouses;
    }

    private void addShipmentsFromXML(WarehouseManager whManager) throws IOException, ParseException {
        for ( Warehouse wh : whManager.getWarehouses() ) {
            for ( Shipment sh : wh.getShipments() ) {
                String sID = sh.getShipmentID();
                String sMode = sh.getShipmentMode().toLowerCase();
                float sWeight = sh.getShipmentWeight();
//                if (sh.getWeightUnit().toLowerCase().equals("kg")) {
//                    sWeight = convertKgsToLbs(sWeight);
//                }
                int wID = wh.getWarehouseID();
                long rDate = sh.getReceivedAt();
                Shipment shipment = new Shipment(sID, sMode, sWeight, wID, rDate);
                ShipTracker.warehouseMgr.getWarehouseByID(wID).recordShipment(shipment);
                ShipTracker.warehouseMgr.addShipmentToDataStore(shipment);
            }
        }
    }

    private float convertKgsToLbs(float weight) {
        DecimalFormat df = new DecimalFormat("0.00");
//        weight *= 2.20462;
//        df.format(weight * 2.20462);
        return (Float.parseFloat(df.format(weight * 2.20462)));
    }
}