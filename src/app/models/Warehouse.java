package app.models;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sun.java2d.pipe.SpanShapeRenderer;

import java.util.ArrayList;

/**
 * The Warehouse class is responsible for keeping track of the shipments that
 * are located at the warehouse. Warehouses also keep track of whether they
 * are currently accepting shipments or not.
 */
public class Warehouse {
	private IntegerProperty warehouseID = new SimpleIntegerProperty();
	private StringProperty warehouseName = new SimpleStringProperty();
	private BooleanProperty airMode = new SimpleBooleanProperty();
	private BooleanProperty railMode = new SimpleBooleanProperty();
	private BooleanProperty truckMode = new SimpleBooleanProperty();
	private BooleanProperty shipMode = new SimpleBooleanProperty();
	private BooleanProperty receiving = new SimpleBooleanProperty();
	private ArrayList<Shipment> shipments = new ArrayList<>();
	public ObservableList<Shipment> shipmentsList = FXCollections.observableArrayList(shipments);

	/**
	 * The Warehouse constructor creates a warehouse and assigns it the ID
	 * provided as an argument. Note that a client of this method would need
	 * to ensure the ID provided is unique.
	 * @param whID The ID assigned to the new warehouse
	 */
	public Warehouse(int whID) {
		setWarehouseID(whID);
	}

	/**
	 * The Warehouse constructor creates a warehouse and assigns it the ID
	 * provided as an argument. Note that a client of this method would need
	 * to ensure the ID provided is unique.
	 * @param whID The ID assigned to the new warehouse
	 * @param air Whether the warehouse handles air shipments
	 * @param rail Whether the warehouse handles rail shipments
	 * @param truck Whether the warehouse handles truck shipments
	 * @param ship Whether the warehouse handles ship shipments
	 * @param wName The name of the warehouse
	 * @param receive Whether the warehouse is receiving shipments
	 */
	public Warehouse(int whID, boolean air, boolean rail, boolean truck, boolean ship, String wName, boolean receive) {
		setWarehouseID(whID);
		setAirMode(air);
		setRailMode(rail);
		setTruckMode(truck);
		setShipMode(ship);
		setWarehouseName(wName);
		setReceiving(receive);
	}

	/**
	 * The getWarehouseID method returns the ID of the warehouse.
	 * @return The ID of the warehouse
	 */
	public int getWarehouseID() {
		return warehouseID.get();
	}

	/**
	 * The getWarehouseName method returns the name of the warehouse
	 * @return The name of the warehouse
	 */
	public String getWarehouseName() {
		return warehouseName.get();
	}

	public boolean getAirMode() { return airMode.get(); }
	public boolean getRailMode() { return railMode.get(); }
	public boolean getTruckMode() { return truckMode.get(); }
	public boolean getShipMode() { return shipMode.get(); }
	public boolean getReceiving() { return receiving.get(); }

	/**
	 * The setWarehouseName method sets the warehouse name.
	 * 
	 */
	public void setWarehouseName(String name) {
		warehouseName.set(name);
	}

	public void setWarehouseID(int id) { warehouseID.set(id); }
	public void setAirMode(boolean mode) { airMode.set(mode); }
	public void setRailMode(boolean mode) { railMode.set(mode); }
	public void setTruckMode(boolean mode) { truckMode.set(mode); }
	public void setShipMode(boolean mode) { shipMode.set(mode); }
	public void setReceiving(boolean receive) { receiving.set(receive); }

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
		if (receiving.get()) {
			recordShipment(shipment);
			shipmentAdded = true;
		}
		return shipmentAdded;
	}

//	/**
//	 * The enableFreightReceipt method sets the warehouse to receive freight.
//	 */
//	public void enableFreightReceipt() {
//		receiving.set(true);
//	}
//
//	/**
//	 * The disableFreightReceipt method sets the warehouse to stop receiving
//	 * freight.
//	 */
//	public void disableFreighReceipt() {
//		receiving.set(false);
//	}

	/**
	 * The isFreightEnabled method checks whether the warehouse is currently
	 * receiving freight or not.
	 * @return True if the warehouse is receiving freight and false if not
	 */
	public boolean isFreightEnabled() {
		return receiving.get();
	}

	/**
	 * The getNameByID method returns the name of the warehouse when an ID is provided.
	 * @param whID The ID of the warehouse
	 * @return The name of the warehouse
	 */
	public String getNameByID(int whID) {
		return warehouseName.get();
	}

	/**
	 * The getShipments method returns the shipments currently located at the
	 * warehouse.
	 * @return The shipments located at the warehouse as an array list
	 */
	public ArrayList<Shipment> getShipments() {
		return shipments;
	}

	public ObservableList<Shipment> getShipmentsList() {
		return shipmentsList;
	}
}