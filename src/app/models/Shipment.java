package app.models;

import java.time.Instant;

/**
 * The Shipment class keeps track of the ID, weight, location, and other
 * metadata about a shipment.
 */
public class Shipment {
    private String shipmentID;
    private String shipmentMode;
    private float shipmentWeight;
    private int currentWarehouseID;
    private long receivedAt;

    /**
     * This Shipment constructor sets all the attributes of a shipment.
     * @param shipID The ID of the shipment
     * @param mode The shipping method of the shipment, such as 'air' or 'rail'
     * @param weight The weight of the shipment
     * @param whID The ID of the warehouse housing the shipment
     * @param time The time the shipment was received, in ms since Jan 1 1970
     */
    public Shipment(String shipID, String mode, float weight, int whID, long time) {
        shipmentID = shipID;
        shipmentMode = mode;
        shipmentWeight = weight;
        currentWarehouseID = whID;
        receivedAt = time;
    }

    /**
     * This Shipment constructor sets the shipment ID, method, and weight, but
     * not the warehouse ID or receipt timestamp. It is intended to be used by
     * clients that are staging shipments prior to any attempt to send it to a
     * warehouse.
     * @param shipID The ID of the shipment
     * @param mode The shipping method of the shipment, such as 'air' or 'rail'
     * @param weight The weight of the shipment
     */
    public Shipment(String shipID, String mode, float weight) {
        shipmentID = shipID;
        shipmentMode = mode;
        shipmentWeight = weight;
    }

    /**
     * The getShipmentID method returns the ID of the shipment.
     * @return The ID of the shipment, as a String
     */
    public String getShipmentID() {
        return shipmentID;
    }

    /**
     * The getShipmentMode method returns the shipping mode.
     * @return The way the shipment is being sent, as a String
     */
    public String getShipmentMode() {
        return shipmentMode;
    }

    /**
     * The getShipmentWeight method returns the weight of the shipment.
     * @return The shipment weight, as a float
     */
    public float getShipmentWeight() {
        return shipmentWeight;
    }

    /**
     * The getWarehouseID method returns the ID of the warehouse housing the
     * shipment.
     * @return The warehouse ID where the shipment is located, as an int
     */
    public int getWarehouseID() {
        return currentWarehouseID;
    }

    /**
     * The getReceivedAt method returns the timestamp the shipment was
     * received at the warehouse.
     * @return The shipment receipt timestamp, in ms since the UNIX Epoch
     */
    public long getReceivedAt() {
        return receivedAt;
    }

    /**
     * The setWarehouseID method assigns the shipment to a warehouse.
     * @param whID The ID of the warehouse accepting the shipment
     */
    public void setWarehouseID(int whID) {
        currentWarehouseID = whID;
    }

    /**
     * The setReceiptTime method records the timestamp the shipment is accepted
     * at the warehouse
     */
    public void setReceiptTime() {
        receivedAt = Instant.now().toEpochMilli();
    }
}