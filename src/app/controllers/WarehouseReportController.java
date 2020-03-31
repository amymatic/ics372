package app.controllers;

import app.models.ShipTracker;
import app.models.Warehouse;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import java.util.ArrayList;

public class WarehouseReportController {
    private static ObservableList<Warehouse> warehouses = FXCollections.observableArrayList();

    @FXML
    public TableView<Warehouse> warehouseTable;

    @FXML
    protected void handleWarehouseReportButtonClick(ActionEvent event) {
        displayWarehouses();
    }

    @FXML
    public void displayWarehouses() {
        warehouses.removeAll(warehouses);
        ArrayList<Warehouse> warehouseList = new ArrayList<Warehouse>();
        warehouseList.addAll(ShipTracker.warehouseMgr.getWarehouses());
        warehouses.addAll(warehouseList);
        warehouseTable.setItems(warehouses);
    }
}