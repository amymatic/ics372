package app.models;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class WarehouseTest {
	
	static Warehouse warehouse;
	static Warehouse closedWarehouse;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
		warehouse = new Warehouse(12345, true, true, true, true, "Warehouse", true);
		closedWarehouse = new Warehouse(12345, true, true, true, true, "Warehouse", false);
		
	}

	@Test
	void testgetWarehouseID() {
		
		assertEquals(12345, warehouse.getWarehouseID());
		
	}
	
	@Test
	void testgetWarehouseName() {
		
		assertEquals("Warehouse", warehouse.getWarehouseName());
		
	}
	
	@Test
	void testgetAirMode() {
		
		assertEquals(true, warehouse.getAirMode());
		
	}
	
	@Test
	void testgetRailMode() {
		
		assertEquals(true, warehouse.getRailMode());
		
	}
	
	@Test
	void testgetShipMode() {
		
		assertEquals(true, warehouse.getShipMode());
		
	}
	
	@Test
	void testgetTruckMode() {
		
		assertEquals(true, warehouse.getTruckMode());
		
	}
	
	@Test
	void testgetReceiving() {
		
		assertEquals(true, warehouse.getReceiving());
		
	}
	
	@Test
	void testIsFreightEnabled() {
		
		assertEquals(true, warehouse.isFreightEnabled());
		
	}
	
	@Test
	void testAddIncomingShipment() {
		
		warehouse.addIncomingShipment(new Shipment("6575ffh", "Air", (float)100.8));
		ArrayList<Shipment> shipments = warehouse.getShipments();
		assertEquals("6575ffh", shipments.get(0).getShipmentID());
		
	}
	
	@Test
	void testShouldNotAddShipmentWhenReceivingIsDisabled() {
		
		closedWarehouse.addIncomingShipment(new Shipment("6575ffh", "Air", (float)100.8));
		ArrayList<Shipment> shipments = closedWarehouse.getShipments();
		assertEquals(0, shipments.size());
		
	}

	@Test
	void testGetShipmentsList() {
		warehouse.addIncomingShipment(new Shipment("6575ffh", "Air", (float)100.8));
		ArrayList<Shipment> shipments = warehouse.getShipments();
		
	}

}
