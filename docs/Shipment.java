public class Shipment(){
	
	String shipmentId; 
	String shipmentMethod;	
	String currentWarehouseId;
	float shipmentWeight;
	long receivedAt;
	
	//No argument constructor not provided
	public Shipment(String shipId, String shipMthd, float weight){	
		shipmentId = shipID;
		shipmentMethod = shipMthd;
		shipmentWeight = weight;
	}
	
	public Shipment(String shipId, String shipMthd, String warehouse, float weight, long received){
	
		shipmentId = shipID;
		shipmentMethod = shipMthd;
		currentWarehouseId = 
		shipmentWeight = weight;
		receivedAt = received;		
	}

	//Basic getters and setters
	//No setters for shipmentID, shipmentMethod and warehouseID
	//Must be known to create shipment object
	public void setWarehouseId(String warehouse){
		currentWarehouseId = warehouse;
	}
	
	public void setReceiptTime(long time){
		receivedAt = time;
	}
	
	public String getShipmentId(){
		return shipmentId;
	}	
	
	public String getShipmentMthd(){
		return shipmentMethod;
	}
	public String getWarehouseId(){
		return currentWarehouseId;
	}
	public float getWeight(){
		return shipmentWeight;	
	}
	public long dated(){
		return receivedAt;
	}
}

