package app.controllers;

import app.jsonsimple.JSONArray;
import app.jsonsimple.JSONObject;
import app.jsonsimple.JSONParser;
import app.jsonsimple.ParseException;
import app.models.Warehouse;
import app.models.WarehouseManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ImportShipmentController {

    @FXML
    protected void handleImportShipmentReportButtonClick(ActionEvent event) throws IOException, ParseException {
        importFile();
    }

    /**
     * The setupWarehouses method is a private method that is run prior to
     * reading and storing the shipments, to ensure that the necessary
     * warehouses exist. Note that this client of WarehouseManager is
     * validating to ensure each warehouse ID in its inventory is unique.
     * @param fileName The name of the file
     * @throws FileNotFoundException If the file is not found
     * @throws IOException If the input or output encounters a problem
     * @throws ParseException If the file cannot be parsed
     */
    private void setupWarehouses(String fileName , WarehouseManager warehouseMgr) throws
            FileNotFoundException, IOException, ParseException {
        JSONParser parser = new JSONParser();
        FileReader file = new FileReader(fileName);
        JSONObject shipmentsObject = (JSONObject) parser.parse(file);
        JSONArray shipmentsArray =
                (JSONArray) shipmentsObject.get("warehouse_contents");

        for ( Object shipment : shipmentsArray ) {
            JSONObject shipmentObject = (JSONObject) shipment;
            boolean warehouseExists = false;
            String warehouseIdString =
                    shipmentObject.get("warehouse_id").toString();
            int warehouseId = Integer.parseInt(warehouseIdString);
            for ( Warehouse wh : warehouseMgr.getWarehouses() ) {
                if (wh.getWarehouseID() == warehouseId) {
                    warehouseExists = true;
                }
            }
            if (!warehouseExists) {
                warehouseMgr.addWarehouse(warehouseId);
            }
        }
    }

    private void importFile() throws IOException, ParseException {

        String fileName ="src/resources/";

        ListView<String> listView = new ListView<>();

        //select  example.json file from resources
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File(fileName));
        File selectedFile = fc.showOpenDialog(null);

        listView.getItems().add(selectedFile.getName());
        fileName += selectedFile.getName();
        System.out.println(fileName);

        //todo: if file is xml, convert to json
        /*
        fileName = convertXMLtoJSON()
         */

        WarehouseManager warehouseMgr = WarehouseManager.getInstance();

        // Exports all the warehouse contents to JSON file for demonstration
        setupWarehouses(fileName, warehouseMgr);
        warehouseMgr.createExistingShipmentsFromJSON(fileName);
        warehouseMgr.writeAllShipmentsToJSON();

    }

    private String convertXMLtoJSON(String file){

         /* todo IF FILE IS XML, CONVERT TO JSON

        //convert xml to json
        String data="";
        File myObj = new File(fileName);
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            data += myReader.nextLine();
        }
        myReader.close();
        //convert to json
        JSONObject obj = XML.toJSONObject(data);

        //write to json
        FileWriter myWriter = new FileWriter("src/resources/import.json");
        myWriter.write(obj.toString());
        myWriter.close();

        */


        return file;
    }


}
