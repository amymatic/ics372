package app.controllers;

import app.helpers.AlertHelper;
import app.jsonsimple.JSONArray;
import app.jsonsimple.JSONObject;
import app.jsonsimple.JSONParser;
import app.jsonsimple.ParseException;
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
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class AddShipmentController {
    private static ObservableList<String> shippingModes = FXCollections.observableArrayList();
    private ObservableList<String> airWarehouses = FXCollections.observableArrayList();
    private ObservableList<String> railWarehouses = FXCollections.observableArrayList();
    private ObservableList<String> truckWarehouses = FXCollections.observableArrayList();
    private ObservableList<String> shipWarehouses = FXCollections.observableArrayList();

    @FXML
    public ChoiceBox<String> shippingModeChoiceBox;
    @FXML
    public ChoiceBox<String> warehouseChoiceBox;
    @FXML
    private StackPane addShipmentPane;
    @FXML
    private TextField shipmentIDField;
    @FXML
    private TextField shipmentWeightField;

    public void initialize() {
        loadShippingModes();
        preloadWarehouses();
    }

    @FXML
    protected void handleAddShipmentButtonClick(ActionEvent event)throws IOException, ParseException {
        if(shipmentIDField.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, addShipmentPane.getScene().getWindow(),
                    "Form Error!", "Please enter the warehouse ID");
            return;
        }
        if(shipmentWeightField.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, addShipmentPane.getScene().getWindow(),
                    "Form Error!", "Please enter the warehouse name");
            return;
        }
        if(shippingModeChoiceBox.getSelectionModel().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, addShipmentPane.getScene().getWindow(),
                    "Form Error!", "Please select the shipping mode");
            return;
        }
        if(warehouseChoiceBox.getSelectionModel().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, addShipmentPane.getScene().getWindow(),
                    "Form Error!", "Please select a warehouse");
            return;
        }

        String shID = shipmentIDField.getText();
        float shWeight = Float.parseFloat(shipmentWeightField.getText());
        String mode = shippingModeChoiceBox.getValue().toLowerCase();
        String whName = warehouseChoiceBox.getValue();
        int whID = ShipTracker.warehouseMgr.getWarehouseByName(whName).getWarehouseID();
        long received = System.currentTimeMillis();

        Shipment newShipment = new Shipment(shID, mode, shWeight, whID, received);
        ShipTracker.warehouseMgr.getWarehouseByName(whName).addIncomingShipment(newShipment);

        addShipmentToDataStore(newShipment);

        AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, addShipmentPane.getScene().getWindow(),
                "Success", "Shipment created");
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

    private void addShipmentToDataStore(Shipment shipment) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        FileReader file = new FileReader("src/resources/shipments.json");
        JSONObject shipmentsObject = (JSONObject) parser.parse(file);
        JSONArray shipmentsArray = (JSONArray) shipmentsObject.get("shipments");
        JSONObject shipmentObject = new JSONObject();
        shipmentObject.put("shipment_id", shipment.getShipmentID());
        shipmentObject.put("weight", shipment.getShipmentWeight());
        shipmentObject.put("warehouse_id", shipment.getWarehouseID());
        shipmentObject.put("shipment_method", shipment.getShipmentMode());
        shipmentObject.put("receipt_date", shipment.getReceivedAt());

        shipmentsArray.add(shipmentObject);
        shipmentsObject.put("shipments", shipmentsArray);

        //Write JSON file
        try (FileWriter updatedFile = new FileWriter("src/resources/shipments.json")) {
            updatedFile.write(shipmentsObject.toJSONString());
            updatedFile.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}