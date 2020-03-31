package app.controllers;

import app.helpers.AlertHelper;
import app.jsonsimple.ParseException;
import app.models.ShipTracker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import java.io.File;
import java.io.IOException;

public class ImportShipmentsController {
    @FXML
    private StackPane importShipmentsPane;
    @FXML
    private Label selectedFilesLabel;

    @FXML
    protected void handleSearchFilesButtonClick(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select File to Import");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Importable Files", "*.json"));
        File selectedFile = fileChooser.showOpenDialog(importShipmentsPane.getScene().getWindow());
        if (selectedFile != null) {
            selectedFilesLabel.setText(selectedFile.toString());
        }
    }

    @FXML
    protected void handleImportShipmentsButtonClick(ActionEvent event) throws IOException, ParseException {
        ShipTracker.warehouseMgr.createExistingShipmentsFromJSON(selectedFilesLabel.getText());
        selectedFilesLabel.setText("(no file selected)");

        AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, importShipmentsPane.getScene().getWindow(),
                "Success", "Shipments imported");
    }
}