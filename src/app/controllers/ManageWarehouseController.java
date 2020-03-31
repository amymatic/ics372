package app.controllers;

import app.helpers.AlertHelper;
import app.models.ShipTracker;
import app.models.Warehouse;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;

public class ManageWarehouseController {
    private static ObservableList<String> warehouses = FXCollections.observableArrayList();
    private ArrayList<Warehouse> warehouseList = ShipTracker.warehouseMgr.getWarehouses();
    private Warehouse warehouse;

    @FXML
    public ChoiceBox<String> warehouseChoiceBox;
    @FXML
    private StackPane manageWarehousePane;
    @FXML
    public TextField warehouseIDField;
    @FXML
    public TextField warehouseNameField;
    @FXML
    public RadioButton airModeRadioButton;
    @FXML
    public RadioButton railModeRadioButton;
    @FXML
    public RadioButton truckModeRadioButton;
    @FXML
    public RadioButton shipModeRadioButton;
    @FXML
    public RadioButton receivingRadioButton;

    public void initialize() {
        loadWarehouses();
    }

    @FXML
    protected void handleUpdateWarehouseButtonClick(ActionEvent event) {
        warehouse.setAirMode(airModeRadioButton.selectedProperty().getValue());
        warehouse.setRailMode(railModeRadioButton.selectedProperty().getValue());
        warehouse.setTruckMode(truckModeRadioButton.selectedProperty().getValue());
        warehouse.setShipMode(shipModeRadioButton.selectedProperty().getValue());
        warehouse.setReceiving(receivingRadioButton.selectedProperty().getValue());

        updateWarehouseDataStore();
        AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, manageWarehousePane.getScene().getWindow(),
                "Success", "Warehouse Updated");
    }

    @FXML
    public void loadWarehouses() {
        warehouses.removeAll(warehouses);
        ArrayList<String> warehouseNames = new ArrayList<>();
        for ( Warehouse wh : ShipTracker.warehouseMgr.getWarehouses()) {
            warehouseNames.add(wh.getWarehouseName());
        }
        warehouses.addAll(warehouseNames);
        warehouseChoiceBox.setItems(warehouses);
        warehouseChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue obsVal, Object t, Object t1) {
                String whName = t1.toString();
                warehouse = ShipTracker.warehouseMgr.getWarehouseByName(whName);
                for ( Warehouse wh : ShipTracker.warehouseMgr.getWarehouses() ) {
                    if (wh.equals(warehouse)) {
                        warehouseIDField.textProperty().setValue(Integer.toString(wh.getWarehouseID()));
                        warehouseNameField.textProperty().setValue(wh.getWarehouseName());
                        airModeRadioButton.selectedProperty().setValue(wh.getAirMode());
                        railModeRadioButton.selectedProperty().setValue(wh.getRailMode());
                        truckModeRadioButton.selectedProperty().setValue(wh.getTruckMode());
                        shipModeRadioButton.selectedProperty().setValue(wh.getShipMode());
                        receivingRadioButton.selectedProperty().setValue(wh.getReceiving());
                    }
                }
            }
        });
    }

    private void updateWarehouseDataStore() {
        ShipTracker.warehouseMgr.writeWarehousesToJSON(warehouseList);
    }
}