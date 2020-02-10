

//This is a shipment class that can be used to create
//and store shipments objects within a warehouse
public class Shipment(){

	private String shipmentId;
	private String shipmentMethod;
	private String currentWarehouseId;
	private float shipmentWeight;
	private long receivedAt;

	//No-argument constructor not provided
	public Shipment(String shipId, String shipMthd, float weight){
		shipmentId = shipID;
		shipmentMethod = shipMthd;
		shipmentWeight = weight;
	}

	//This constructor is for shipments that already exist within a warehosue
	public Shipment(String shipId, String shipMthd, String warehouse, float weight, long received){

		shipmentId = shipID;
		shipmentMethod = shipMthd;
		currentWarehouseId =
		shipmentWeight = weight;
		receivedAt = received;
	}

	//No setters for shipmentID, shipmentMethod and weight
	//must be known at construction

	//set warehouseID once a warehouse has been assigned
	public void setWarehouseId(String warehouse){
		currentWarehouseId = warehouse;
	}
	//set time shipment is/was received
	public void setReceiptTime(long time){
		receivedAt = time;
	}

}
