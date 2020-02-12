package project1;

import java.io.*;
import java.util.*;
import jsonsimple.*;

/**
 * The WarehouseManager class is responsible for keeping track of a group of
 * warehouses. It reads and stores existing shipments from JSON files, and
 * writes warehouse contents to JSON.
 */
public class WarehouseManager {
    
    private ArrayList<Warehouse> warehouseList = new ArrayList<Warehouse>();

    public WarehouseManager() {
    }

    /**
     * The addWarehouse method creates a new Warehouse object and adds it to
     * the WarehouseManager array list. Clients will need to handle validation
     * to ensure the warehouses in their WarehouseManager are unique. This
     * method will add any warehouse, even if the warehouseId is not unique.
     * @param warehouseID The ID that the new warehouse will be assigned
     */
    public void addWarehouse(int warehouseID) {
        Warehouse warehouse = new Warehouse(warehouseID);
        warehouseList.add(warehouse);
    }

    /**
     * The getWarehouses method returns the WarehouseManager's list of
     * warehouses.
     * @return The list of warehouses
     */
    public ArrayList<Warehouse> getWarehouses() {
        return warehouseList;
    }

    /**
     * The createExistingShipmentsnFromJSON method parses a json file and creates records
     * for every shipment in the file, reading and storing the metadata for each shipment,
     * including where is is located and when it was received at that warehouse.
     * It then adds a corresponding record of the shipment for its warehouse.
     * @param inputFile The file containing the JSON shipment array
     * @throws FileNotFoundException If the file is not found
     * @throws IOException If the input or output encounters a problem
     * @throws ParseException If the file cannot be parsed
     */
    public void createExistingShipmentsFromJSON(String inputFile) throws
        FileNotFoundException, IOException, ParseException {
            FileReader file = new FileReader(inputFile);
            JSONParser parser = new JSONParser();
            JSONObject shipmentsObject = (JSONObject) parser.parse(file);
            JSONArray shipmentsArray = (JSONArray)
                shipmentsObject.get("warehouse_contents");

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
                for ( Warehouse wh : warehouseList ) {
                    if (wh.getWarehouseID() == wID) {
                        wh.recordShipment(shipment);
                    }
                }
            }
    }

    /**
     * The writeAllShipmentsToJSON method calls the writeShipmentsToJSON method
     * with the entire list of warehouses maintained by the WarehouseManager.
     */
    public void writeAllShipmentsToJSON() {
        writeShipmentsToJSON(warehouseList);
    }

    /**
     * The writeShipmentsToJSON method accepts an array list of warehouses and
     * writes the shipments of each warehouse to a JSON file located in the
     * target directory of the project.
     * @param warehouses An array list of warehouses
     */
    public void writeShipmentsToJSON(ArrayList<Warehouse> warehouses) {
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
        try (FileWriter file =
            new FileWriter("../../../target/warehouse_contents.json")) {
            file.write(warehouseContents.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
