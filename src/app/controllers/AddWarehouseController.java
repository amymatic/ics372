package app.controllers;

import app.helpers.AlertHelper;
import app.models.ShipTracker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

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
    protected void handleAddWarehouseButtonClick(ActionEvent event) {
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

        ShipTracker.warehouseMgr.addWarehouse(whID, air, rail, truck, ship, whName, receiving);

        alerter.showAlert(Alert.AlertType.CONFIRMATION, addWarehousePane.getScene().getWindow(),
                "Success", "Warehouse created");
    }
}
