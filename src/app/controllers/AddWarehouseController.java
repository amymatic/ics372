package app.controllers;

import app.helpers.AlertHelper;
import app.jsonsimple.JSONArray;
import app.jsonsimple.JSONObject;
import app.jsonsimple.JSONParser;
import app.jsonsimple.ParseException;
import app.models.ShipTracker;
import app.models.Warehouse;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class AddWarehouseController {
    private AlertHelper alerter;

    @FXML
    private StackPane addWarehousePane;
    @FXML
    private TextField warehouseIDField;
    @FXML
    private TextField warehouseNameField;
    @FXML
    private RadioButton airModeRadioButton;
    @FXML
    private RadioButton railModeRadioButton;
    @FXML
    private RadioButton truckModeRadioButton;
    @FXML
    private RadioButton shipModeRadioButton;
    @FXML
    private RadioButton receivingRadioButton;

    @FXML
    protected void handleAddWarehouseButtonClick(ActionEvent event) throws IOException, ParseException {
        if(warehouseIDField.getText().isEmpty()) {
            alerter.showAlert(Alert.AlertType.ERROR, addWarehousePane.getScene().getWindow(),
                    "Form Error!", "Please enter the warehouse ID");
            return;
        }
        if(warehouseNameField.getText().isEmpty()) {
            alerter.showAlert(Alert.AlertType.ERROR, addWarehousePane.getScene().getWindow(),
                    "Form Error!", "Please enter the warehouse name");
            return;
        }
        if(!warehouseIDField.getText().isEmpty()) {
            try {
                Integer.parseInt(warehouseIDField.getText());
            } catch (NumberFormatException ex) {
                alerter.showAlert(Alert.AlertType.ERROR, addWarehousePane.getScene().getWindow(),
                        "Form Error!", "Please enter the warehouse ID in integers");
                return;
            }
        }

        int whID = Integer.parseInt(warehouseIDField.getText());
        String whName = warehouseNameField.getText();
        boolean air = airModeRadioButton.isSelected();
        boolean rail = railModeRadioButton.isSelected();
        boolean truck = truckModeRadioButton.isSelected();
        boolean ship = shipModeRadioButton.isSelected();
        boolean receiving = receivingRadioButton.isSelected();

        Warehouse newWarehouse = new Warehouse(whID, air, rail, truck, ship, whName, receiving);
        ShipTracker.warehouseMgr.addWarehouse(newWarehouse);
        addWarehouseToDataStore(newWarehouse);

        alerter.showAlert(Alert.AlertType.CONFIRMATION, addWarehousePane.getScene().getWindow(),
                "Success", "Warehouse created");
    }

    private void addWarehouseToDataStore(Warehouse warehouse) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        FileReader file = new FileReader("src/resources/warehouses.json");
        JSONObject warehousesObject = (JSONObject) parser.parse(file);
        JSONArray warehousesArray = (JSONArray) warehousesObject.get("warehouses");
        JSONObject warehouseObject = new JSONObject();
        warehouseObject.put("warehouse_id", warehouse.getWarehouseID());
        warehouseObject.put("name", warehouse.getWarehouseName());
        warehouseObject.put("air", warehouse.getAirMode());
        warehouseObject.put("rail", warehouse.getRailMode());
        warehouseObject.put("truck", warehouse.getTruckMode());
        warehouseObject.put("ship", warehouse.getShipMode());
        warehouseObject.put("receiving", warehouse.getReceiving());
        warehousesArray.add(warehouseObject);
        warehousesObject.put("warehouses", warehousesArray);

        //Write JSON file
        try (FileWriter updatedFile = new FileWriter("src/resources/warehouses.json")) {
            updatedFile.write(warehousesObject.toJSONString());
            updatedFile.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
