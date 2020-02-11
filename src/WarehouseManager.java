import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.*;

// The WarehouseManager class is responsible for keeping track of warehouses.
// The default no-arg constructor is used to create a WarehouseManager.
public class WarehouseManager {

    private ArrayList<Warehouse> warehouses = new ArrayList<Warehouse>();

    // Clients will need to handle validation to ensure the warehouses in their
    // WarehouseManager are unique. This method will add any warehouse to the
    // warehouse array, even if the warehouseId is not unique. 
    public void addWarehouse(int warehouseId) {
        Warehouse warehouse = new Warehouse(warehouseId);
        warehouses.add(warehouse);
    }

    public ArrayList<Warehouse> getWarehouses() {
        return warehouses;
    }

    // This method accepts and parses a json file and creates Shipment objects
    // that represent where Shipments are currently located and when they were
    // received.
    // Note that this method does not attempt to validate whether a warehouse
    // is accepting shipments because it is meant to be used by the client only
    // to record where shipments are that have already been accepted.
    public void createExistingShipmentsFromJSON(String inputFile) throws
        FileNotFoundException, IOException, ParseException {

            FileReader file = new FileReader(inputFile);
            JSONParser parser = new JSONParser();
            JSONObject shipmentsObject = (JSONObject) parser.parse(file);
            JSONArray shipmentsArray = (JSONArray) shipmentsObject.get("warehouse_contents");

            for (Object object : shipmentsArray) {
                JSONObject jsonShipment = (JSONObject) object;

                int wID = Integer.parseInt(jsonShipment.get("warehouse_id").toString());
                String sMode = (String) jsonShipment.get("shipment_method");
                String sID = (String) jsonShipment.get("shipment_id");
                float sWeight = Float.parseFloat(jsonShipment.get("weight").toString());
                long rDate = (long) jsonShipment.get("receipt_date");

                // Create the shipment
                Shipment shipment = new Shipment(sID, sMode, sWeight, wID, rDate);

                // Add the shipment to the Warehouse's records
                for ( Warehouse wh : warehouses ) {
                    if (wh.getWarehouseID() == wID) {
                        wh.addShipment(shipment);
                        System.out.println("Shipments at WH " + wID + " : " +
                            wh.getShipments().size());
                    }
                }
            }
    }

    // This method should either produce a file that ends up being created
    // within the directory somewhere (in other words it doesn't need to return
    // a file object), or it can return a file object
    public void writeAllShipmentsToJSON() {
        JSONObject warehouseContents = new JSONObject();
        JSONArray shipmentsArray = new JSONArray();
        for ( Warehouse wh : warehouses ) {
            for ( Shipment sh : wh.getShipments() ) {
                JSONObject shipment = new JSONObject();
                shipment.put("warehouse_id", sh.getWarehouseID());
                shipment.put("shipment_method", sh.getShipmentMode());
                shipment.put("shipment_id", sh.getShipmentID());
                shipment.put("weight", sh.getShipmentWeight());
                shipment.put("receipt_date", sh.getReceivedAt());
                shipmentsArray.add(shipment);
            }
        }
        warehouseContents.put("warehouse_contents", shipmentsArray);

        //Write JSON file
        try (FileWriter file = new FileWriter("warehouse_contents.json")) {
 
            file.write(warehouseContents.toJSONString());
            file.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}