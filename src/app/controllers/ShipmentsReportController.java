package app.controllers;

import app.models.ShipTracker;
import app.models.Shipment;
import app.models.Warehouse;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import java.util.ArrayList;

public class ShipmentsReportController {
    private static ObservableList<String> warehouses = FXCollections.observableArrayList();
    private ObservableList<Shipment> shipments = FXCollections.observableArrayList();

    @FXML
    public ChoiceBox<String> warehouseChoiceBox;
    @FXML
    public TableView<Shipment> shipmentTable;

    public void initialize() {
        loadWarehouses();
    }

    @FXML
    protected void handleShipmentReportButtonClick(ActionEvent event) {
        displayShipments();
    }

    @FXML
    public void loadWarehouses() {
        warehouses.removeAll(warehouses);
        ArrayList<String> warehouseNames = new ArrayList<>();
        warehouseNames.add("All Warehouses");
        for ( Warehouse wh : ShipTracker.warehouseMgr.getWarehouses()) {
            warehouseNames.add(wh.getWarehouseName());
        }
        warehouses.addAll(warehouseNames);
        warehouseChoiceBox.setItems(warehouses);
    }

    @FXML
    public void displayShipments() {
        shipments.removeAll(shipments);
        ArrayList<Shipment> shipmentList = new ArrayList<Shipment>();
        String selection = warehouseChoiceBox.getSelectionModel().getSelectedItem();
        if (selection.equals("All Warehouses")) {
            for ( Warehouse wh : ShipTracker.warehouseMgr.getWarehouses()) {
                shipmentList.addAll(wh.getShipments());
            }
        } else {
            Warehouse selectedWH = ShipTracker.warehouseMgr.getWarehouseByName(selection);
            shipmentList.addAll(selectedWH.getShipments());
        }
        shipments.addAll(shipmentList);
        shipmentTable.setItems(shipments);
    }
}