package project1;

import java.io.*;
import java.util.*;
import jsonsimple.*;

/**
 * The ShipmentDemo class demonstrates the functionality of a shipping
 * management software.
 */
public class ShipmentDemo {

	WarehouseManager warehouseMgr = new WarehouseManager();
	
	/**
	 * The main method accepts a sequence of JSON files when ShipmentDemo is
	 * called from the command line. For example: 
	 *    java /project1/Shipment example.json example2.json
	 * The JSON files contain records of where shipments are currently located.
	 * The shipments are read from the files and stored into Shipment objects.
	 * @param args JSON File names located in the project resources directory
	 */
	public static void main(String[] args) throws
		FileNotFoundException, IOException, ParseException {

		ShipmentDemo demo = new ShipmentDemo();

		// Processes each file provided as args when ShipmentDemo is run,
		// creating warehouses as needed for the demonstration, and storing
		// the shipments in the warehouses.
		for ( String arg : args ) {
			String fileName = ("../resources/" + arg);
			demo.setupWarehouses(fileName);
			demo.warehouseMgr.createExistingShipmentsFromJSON(fileName);
		}

		// Exports all the warehouse contents to JSON file for demonstration
		demo.warehouseMgr.writeAllShipmentsToJSON();

		// Exports the warehouse contents of only the first warehouse to JSON
		// file for demonstration - uncomment this and comment the call to 
		// writeAllShipmentsToJSON above to see this method in action instead.
		//ArrayList<Warehouse> whList = new ArrayList<Warehouse>();
		//whList.add(demo.warehouseMgr.getWarehouses().get(0));
		//demo.warehouseMgr.writeShipmentsToJSON(whList);
	}

	/**
	 * The setupWarehouses method is a private method that is run prior to
	 * reading and storing the shipments, to ensure that the necessary
	 * warehouses exist. Note that this client of WarehouseManager is
	 * validating to ensure each warehouse ID in its inventory is unique.
	 * @param fileName The name of the file
	 */
	private void setupWarehouses(String fileName) throws
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
}