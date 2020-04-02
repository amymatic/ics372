package app.models;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import app.jsonsimple.ParseException;

class WarehouseManagerTest {
	
	static WarehouseManager wm;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
		wm = new WarehouseManager();
	}

	@Test
	void testCreateExistingShipmentsFromJSON() {
		
		wm.addWarehouse(new Warehouse(12513, true, true, true, true, "Warehouse", true));
		
		try {
			wm.createExistingShipmentsFromJSON("src/resources/example.json");
			Warehouse warehouse = wm.getWarehouseByName("Warehouse");
			assertEquals(12513, warehouse.getShipments().get(0).getWarehouseID());
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Exception Caught");
		}
		
	}

	@Test
	void testWriteAllShipmentsToJSON() {
		
		Warehouse warehouse = new Warehouse(12345, true, true, true, true, "Warehouse", true);
		
		warehouse.addIncomingShipment(new Shipment("6575ffh", "Air", (float)100.8));
		wm.addWarehouse(warehouse);
		
		wm.writeAllShipmentsToJSON();
		
		assertEquals(true, new File("src/resources/shipments.json").exists());
		
	}
	
	@Test
	void testWriteWarehousesToJSON() {
		
		wm.addWarehouse(new Warehouse(12513, true, true, true, true, "Warehouse", true));
		wm.writeWarehousesToJSON(wm.getWarehouses());
		
		assertEquals(true, new File("src/resources/warehouses.json").exists());
		
	}


}
