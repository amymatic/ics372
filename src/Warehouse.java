import java.util.ArrayList;

public class Warehouse {
	
	private String warehouseId;
	private boolean receipt;
	private ArrayList<Shipment> shipmentList;
	
	public Warehouse (String id, boolean receipt) {
		warehouseId = id;
		this.receipt = receipt;
		shipmentList = new ArrayList<Shipment>();
	}
	
	//Adds shipment to warehouse if receipt is enabled
	//returns true if sucessful else false
	public boolean addShipment (Shipment shipment) {
		if (receipt) {
			shipmentList.add(shipment);
			return true;
		}
		else {
			return false;
		}
	}
	
	//enables receipt
	public void enableReceipt () {
		receipt = true;
		
	}
	
	//ends receipt
	public void endReceipt() {
		receipt = false;
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
	public boolean getreceipt() {
		return receipt;
	}

}
