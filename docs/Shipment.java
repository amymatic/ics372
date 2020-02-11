
/**  Creates shipments to later be stored wthin a warehouse
* @version 2.0
* @since 1.0
*/
public class Shipment{

	private String shipment_id;
	private String shipment_method;
	private String warehouse_id;
	private float weight;
	private long receipt_date;

	//No-argument constructor is not provided
	/** Creates a shipment with a specific shipment Id, method and weight
	* @param shipment_id Unique id assigned to each shipment
	* @param shipment_method type of shipping method associated with each shipment
	* @param weight = weight of shipment
	*/
	public Shipment(String shipId, String shipMthd, float weighs){
		shipment_id = shipId;
		shipment_method = shipMthd;
		weight = weighs;
	}

	/** Creates a shipment that has already been assigned/exists in a warehouse
	* @param shipment_id Unique id assigned to each shipment
	* @param shipment_method type of shipping method associated with each shipment
	* @param warehouse_id current warehouse the shipment is located
	* @param weight = weight of shipment
	* @param receipt_date = the date in which the shipment was received
	*/
	public Shipment(String shipId, String shipMthd, String warehouse, float weighs, long received){

		shipment_id = shipId;
		shipment_method = shipMthd;
		warehouse_id = warehouse;
		weight = weighs;
		receipt_date = received;
	}

	/** A shipment's id, shipping method, and weight must be known at construction
	* thus, no setters for shipment_id, shipment_method, and weight are provided
	*/

	/** Sets the shipment's warehouse_id
	* @param warehouse_id An id number associated with a specific warehouse
	*/

	public void setWarehouseId(String warehouse){
		warehouse_id = warehouse;
	}
	/** set time shipment is/was received
	* @param receipt_date the date in which a shipment is received by a warehouse
	*/
	public void setReceipt(long received){
	receipt_date = received;
	}


}
