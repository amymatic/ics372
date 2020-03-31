package app.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ShipmentTest {

	static Shipment shipment;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
		shipment = new Shipment("12345j", "air", 1000, 12345, 12345);
		
	}

	@Test
	void testGetShipmentID() {
		assertEquals("12345j", shipment.getShipmentID());
	}

	@Test
	void testGetShipmentMode() {
		assertEquals("air", shipment.getShipmentMode());
	}

	@Test
	void testGetShipmentWeight() {
		assertEquals(1000, shipment.getShipmentWeight());
	}

	@Test
	void testGetWarehouseID() {
		assertEquals(12345, shipment.getWarehouseID());
	}

	@Test
	void testGetReadableReceivedAt() {
		assertEquals("Jan 1, 1970 12:00", shipment.getReadableReceivedAt());
	}

	@Test
	void testGetReceivedAt() {
		assertEquals(12345, shipment.getReceivedAt());
	}

	

}
