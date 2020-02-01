public class Shipment {

    private String warehouseId;
    private String shipmentMethod;
    private String shipmentId;
    private double shipmentWeight;
    private long receiptDate;
    //private String metaData; //?unsure about this at moment

    public Shipment(String warehouseId, String shipmentMethod ,String shipmentId, double shipmentWeight, long receiptDate) {
        this.warehouseId = warehouseId;
        this.shipmentMethod = shipmentMethod;
        this.shipmentId = shipmentId;
        this.shipmentWeight = shipmentWeight;
        this.receiptDate = receiptDate;
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getShipmentMethod() {
        return shipmentMethod;
    }

    public void setShipmentMethod(String shipmentMethod) {
        this.shipmentMethod = shipmentMethod;
    }

    public double getShipmentWeight() {
        return shipmentWeight;
    }

    public void setShipmentWeight(double shipmentWeight) {
        this.shipmentWeight = shipmentWeight;
    }

    public long getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(long receiptDate) {
        this.receiptDate = receiptDate;
    }

    public String getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(String shipmentId) {
        this.shipmentId = shipmentId;
    }

    //Although toString() is provided by default, this here in case we want to customize display format
    @Override
    public String toString() {
        return "Shipment{" +
                "warehouseId='" + warehouseId + '\'' +
                ", shipmentMethod='" + shipmentMethod + '\'' +
                ", shipmentId='" + shipmentId + '\'' +
                ", shipmentWeight=" + shipmentWeight +
                ", receiptDate=" + receiptDate +
                '}';
    }

}
