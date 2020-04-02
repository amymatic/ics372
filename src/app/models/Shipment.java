package app.models;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import app.helpers.WeightHelper;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleLongProperty;

import javax.xml.bind.annotation.*;

/**
 * The Shipment class keeps track of the ID, weight, location, and other
 * metadata about a shipment.
 */
public class Shipment {
    private StringProperty shipmentID = new SimpleStringProperty();
    private StringProperty shipmentMode = new SimpleStringProperty();
    private FloatProperty shipmentWeight = new SimpleFloatProperty();
    private IntegerProperty currentWarehouseID = new SimpleIntegerProperty();
    private StringProperty currentWarehouseName = new SimpleStringProperty();
    private LongProperty receivedAt = new SimpleLongProperty();
    private StringProperty readableReceivedAt = new SimpleStringProperty();
    private StringProperty weightUnit = new SimpleStringProperty();
    private WeightHelper weightHelper = new WeightHelper();

    public Shipment() {}
    /**
     * This Shipment constructor sets all the attributes of a shipment.
     * @param shipID The ID of the shipment
     * @param mode The shipping method of the shipment, such as 'air' or 'rail'
     * @param weight The weight of the shipment
     * @param whID The ID of the warehouse housing the shipment
     * @param time The time the shipment was received, in ms since Jan 1 1970
     */
    public Shipment(String shipID, String mode, float weight, int whID, long time) {
        setShipmentID(shipID);
        setShipmentMode(mode);
        setShipmentWeight(weight);
        setWarehouseID(whID);
        setReceiptTime(time);
        setReadableReceiptTime(time);
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
        setShipmentID(shipID);
        setShipmentMode(mode);
        setShipmentWeight(weight);
    }

    /**
     * The getShipmentID method returns the value of the property.
     * @return The ID of the shipment, as a String
     */
    @XmlAttribute(name = "id")
    public final String getShipmentID() {
        return shipmentID.get();
    }

    /**
     * The getShipmentMode method returns the value of the shipping mode.
     * @return The way the shipment is being sent, as a String
     */
    @XmlAttribute(name = "type")
    public final String getShipmentMode() {
        return shipmentMode.get();
    }

    /**
     * The getShipmentWeight method returns the weight of the shipment.
     * @return The shipment weight, as a float
     */
    @XmlElement(name = "Weight")
    public final float getShipmentWeight() {
        return shipmentWeight.get();
    }

    public final String getWeightUnit() { return weightUnit.get(); }

    /**
     * The getWarehouseID method returns the ID of the warehouse housing the
     * shipment.
     * @return The warehouse ID where the shipment is located, as an int
     */
    public final int getWarehouseID() {
        return currentWarehouseID.get();
    }

    public final String getCurrentWarehouseName() { return currentWarehouseName.get(); }
    public final String getReadableReceivedAt() { return readableReceivedAt.get(); }

    /**
     * The getReceivedAt method returns the timestamp the shipment was
     * received at the warehouse.
     * @return The shipment receipt timestamp, in ms since the UNIX Epoch
     */
    @XmlElement(name = "ReceiptDate")
    public final long getReceivedAt() {
        return receivedAt.get();
    }

    public final void setShipmentID(String id) {
        shipmentID.set(id);
    }

    public final void setShipmentMode(String mode) {
        shipmentMode.set(mode);
    }

    /**
     * The setShipmentWeight method assigns the weight of a shipment.
     * @param weight The weight of the shipment
     */
    public final void setShipmentWeight(float weight) {
        shipmentWeight.set(weight);
    }

    /**
     * The setWarehouseID method assigns the shipment to a warehouse.
     * @param whID The ID of the warehouse accepting the shipment
     */
    public final void setWarehouseID(int whID) {
        currentWarehouseID.set(whID);
        setWarehouseName(whID);
    }

    private final void setWarehouseName(int whID) {
        for ( Warehouse wh: ShipTracker.warehouseMgr.getWarehouses()) {
            if (whID == wh.getWarehouseID()) {
                currentWarehouseName.set(wh.getWarehouseName());
            }
        }
    }

    /**
     * The setReceiptTime method records the timestamp the shipment is accepted
     * at the warehouse
     */
    public final void setReceiptTime(long time) {
        receivedAt.set(time);
        setReadableReceiptTime(time);
    }

    private final void setReadableReceiptTime(long time) {
        Date date = new Date(time);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy hh:mm", Locale.ENGLISH);
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        readableReceivedAt.set(dateFormat.format(date));
    }
}