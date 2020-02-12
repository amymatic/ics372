package project1;

import java.io.*;
import java.util.*;
import jsonsimple.*;

/**
 * The Warehouse class is responsible for keeping track of the shipments that
 * are located at the warehouse. Warehouses also keep track of whether they
 * are currently accepting shipments or not.
 */
public class Warehouse {
	private int warehouseID;
	private boolean receiving;
	private ArrayList<Shipment> shipments = new ArrayList<Shipment>();

	/**
	 * The Warehouse constructor creates a warehouse and assigns it the ID
	 * provided as an argument. Note that a client of this method would need
	 * to ensure the ID provided is unique.
	 * @param whID The ID assigned to the new warehouse
	 */
	public Warehouse(int whID) {
		warehouseID = whID;
	}

	/**
	 * The getWarehouseID method returns the ID of the warehouse.
	 * @return The ID of the warehouse
	 */
	public int getWarehouseID() {
		return warehouseID;
	}

	/**
	 * The recordShipment method stores the shipment provided to the
	 * warehouse's list of shipments. Note that this method does not validate
	 * whether the warehouse is accepting shipments. It is intended to be used
	 * to record the location of shipments already accepted by the warehouse.
	 * @param shipment The existing shipment to be recorded at the warehouse
	 */
	public void recordShipment(Shipment shipment) {
		shipments.add(shipment);
	}

	/**
	 * The addIncomingShipment method adds a new shipment to the warehouse
	 * as long as the warehouse is currently accepting shipments.
	 * @param shipment The new shipment to be added to the warehouse
	 * @return True if the shipment was added; false if it was not
	 */
	public boolean addIncomingShipment(Shipment shipment) {
		boolean shipmentAdded = false;
		if (receiving) {
			recordShipment(shipment);
			shipmentAdded = true;
		}
		return shipmentAdded;
	}

	/**
	 * The enableFreightReceipt method sets the warehouse to receive freight.
	 */
	public void enableFreightReceipt() {
		receiving = true;
	}

	/**
	 * The disableFreightReceipt method sets the warehouse to stop receiving
	 * freight.
	 */
	public void disableFreighReceipt() {
		receiving = false;
	}

	/**
	 * The isFreightEnabled method checks whether the warehouse is currently
	 * receiving freight or not.
	 * @return True if the warehouse is receiving freight and false if not
	 */
	public boolean isFreightEnabled() {
		return receiving;
	}

	/**
	 * The getShipments method returns the shipments currently located at the
	 * warehouse.
	 * @return The shipments located at the warehouse as an array list
	 */
	public ArrayList<Shipment> getShipments() {
		return shipments;
	}
}