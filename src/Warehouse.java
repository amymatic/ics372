import java.util.ArrayList;

public class Warehouse {
	
	private String warehouseId;
	private boolean receiving;
	private ArrayList<Shipment> shipmentList;
	
	public Warehouse (String id, boolean receipt) {
		warehouseId = id;
		this.receiving = receipt;
		shipmentList = new ArrayList<Shipment>();
	}
	
	//Adds shipment to warehouse if receipt is enabled
	//returns true if sucessful else false
	public boolean addShipment (Shipment shipment) {
		if (receiving) {
			shipmentList.add(shipment);
			return true;
		}
		else {
			return false;
		}
	}
	
	//enables receipt
	public void enableReceiving () {
		receiving = true;
		
	}
	
	//ends receipt
	public void endReceiving() {
		receiving = false;
	}
	
	//returns warehouse id
	public String getWarehouseId() {
		return warehouseId;
	}
	
	//returns list of shipments in warehouse
	public ArrayList<Shipment> getShipments(){
		return shipmentList;
	}
	
	//checks receipt
	public boolean isReceiving() {
		return receiving;
	}

}
