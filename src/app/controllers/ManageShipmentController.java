package app.controllers;

import app.helpers.AlertHelper;
import app.models.ShipTracker;
import app.models.Shipment;
import app.models.Warehouse;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;

public class ManageShipmentController {
    private static ObservableList<Shipment> shipments = FXCollections.observableArrayList();
    private static ObservableList<String> shippingModes = FXCollections.observableArrayList();
    private ObservableList<String> airWarehouses = FXCollections.observableArrayList();
    private ObservableList<String> railWarehouses = FXCollections.observableArrayList();
    private ObservableList<String> truckWarehouses = FXCollections.observableArrayList();
    private ObservableList<String> shipWarehouses = FXCollections.observableArrayList();
    private Shipment shipment;

    @FXML
    public ChoiceBox<String> shippingModeChoiceBox;
    @FXML
    public ChoiceBox<String> warehouseChoiceBox;
    @FXML
    private StackPane manageShipmentPane;
    @FXML
    private TextField selectedShipmentIDField;
    @FXML
    private TextField shipmentIDField;
    @FXML
    private TextField shipmentWeightField;

    public void initialize() {
        preloadShipments();
        preloadWarehouses();
        loadShippingModes();
    }

    @FXML
    protected void handleSearchShipmentsButtonClick(ActionEvent event) {
        if (selectedShipmentIDField.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, manageShipmentPane.getScene().getWindow(),
                    "Form Error!", "Please enter the shipment ID");
            return;
        }
        String enteredShipmentID  = selectedShipmentIDField.getText();
        boolean shipmentFound = false;
        for (Shipment sh : shipments) {
            if (enteredShipmentID.equals(sh.getShipmentID())) {
                shipmentFound = true;
                shipment = sh;
                loadShipmentDetails(sh);
            }
        }
        if (!shipmentFound) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, manageShipmentPane.getScene().getWindow(),
                    "Form Error!", "Shipment not found");
        }
    }

    @FXML
    protected void handleUpdateShipmentButtonClick(ActionEvent event) {
        shipment.setShipmentMode(shippingModeChoiceBox.getValue().toLowerCase());
        String warehouseName = warehouseChoiceBox.getValue();
        int warehouseID = ShipTracker.warehouseMgr.getWarehouseByName(warehouseName).getWarehouseID();
        shipment.setWarehouseID(warehouseID);

        updateShipmentDataStore();
        AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, manageShipmentPane.getScene().getWindow(),
                "Success", "Shipment Updated");
        ShipTracker.warehouseMgr.writeAllShipmentsToJSON();
    }

    @FXML
    public void loadShippingModes() {
        shippingModes.removeAll(shippingModes);
        ArrayList<String> shipModes = new ArrayList<>();
        shipModes.add("Air");
        shipModes.add("Rail");
        shipModes.add("Truck");
        shipModes.add("Ship");
        shippingModes.addAll(shipModes);
        shippingModeChoiceBox.setItems(shippingModes);
        shippingModeChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue obsVal, Object t, Object t1) {
                switch (t1.toString()) {
                    case "Air":
                        warehouseChoiceBox.setItems(airWarehouses);
                        break;
                    case "Rail":
                        warehouseChoiceBox.setItems(railWarehouses);
                        break;
                    case "Truck":
                        warehouseChoiceBox.setItems(truckWarehouses);
                        break;
                    case "Ship":
                        warehouseChoiceBox.setItems(shipWarehouses);
                        break;
                }
            }
        });
    }

    private void preloadShipments() {
        for ( Warehouse wh : ShipTracker.warehouseMgr.getWarehouses() ) {
            for ( Shipment sh : wh.getShipments() ) {
                shipments.add(sh);
            }
        }
    }

    private void preloadWarehouses() {
        for ( Warehouse wh : ShipTracker.warehouseMgr.getWarehouses() ) {
            if (wh.isFreightEnabled()) {
                if (wh.getAirMode()) { airWarehouses.add(wh.getWarehouseName()); }
                if (wh.getRailMode()) { railWarehouses.add(wh.getWarehouseName()); }
                if (wh.getTruckMode()) { truckWarehouses.add(wh.getWarehouseName()); }
                if (wh.getShipMode()) { shipWarehouses.add(wh.getWarehouseName()); }
            }
        }
    }

    private void loadShipmentDetails(Shipment shipment) {
        for ( Shipment sh : shipments ) {
            if (sh.getShipmentID().equals(shipment.getShipmentID())) {
                shipmentIDField.textProperty().setValue(sh.getShipmentID());
                shipmentWeightField.textProperty().setValue(Float.toString(sh.getShipmentWeight()));
                shippingModeChoiceBox.getSelectionModel().select(convertModeToReadable(sh.getShipmentMode()));
                warehouseChoiceBox.getSelectionModel().select(sh.getCurrentWarehouseName());
            }
        }
    }

    private void updateShipmentDataStore() {
        ShipTracker.warehouseMgr.writeAllShipmentsToJSON();
    }

    private String convertModeToReadable(String mode) {
        String convertedMode = "";
        switch (mode.toString())  {
            case "air": convertedMode = "Air"; break;
            case "rail": convertedMode = "Rail"; break;
            case "truck": convertedMode = "Truck"; break;
            case "ship": convertedMode = "Ship"; break;
        }
        return convertedMode;
    }
}