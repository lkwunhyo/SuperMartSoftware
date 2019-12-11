package JUnitTestCases;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import Delivery.DeliveryException;
import Delivery.Manifest;
import Delivery.RefrigeratedTruck;
import Delivery.Truck;
import Stock.StockException;

class ManifestTest {
	
	Manifest testManifest;
	RefrigeratedTruck rTruckTest;

	@Test
	void testInstantiation() {
		testManifest = new Manifest();
		
		assertEquals(testManifest != null, true);
	}

	@Test
	void testAddTruck() {
		testManifest = new Manifest();
		testManifest.addTruckToManifest(rTruckTest);
		
		assertEquals(testManifest.getManifest().get(0), rTruckTest);
	}
	
	@Test
	void testRemoveTruck() throws DeliveryException {
		testManifest = new Manifest();
		testManifest.addTruckToManifest(rTruckTest);
		testManifest.removeTruckFromManifest(rTruckTest);
		
		assertEquals(testManifest.getManifest().isEmpty(), true);
	}
	
	@Test
	void testRemoveTruckException() {
		testManifest = new Manifest();
		rTruckTest = new RefrigeratedTruck();
		
		assertThrows(DeliveryException.class, ()->{
			testManifest.removeTruckFromManifest(rTruckTest);
            });
	}
	
	@Test
	void testCreateNewTruck() {
		testManifest = new Manifest();
		Truck newTruck = testManifest.createNewTruck(-20.0);
		
		assertEquals(testManifest.getManifest().get(0), newTruck);
	}
	
	@Test
	void testGetLastTruck() throws DeliveryException {
		testManifest = new Manifest();
		testManifest.createNewTruck(-20.0);
		Truck newTruck = testManifest.createNewTruck(Double.NaN);
		
		assertEquals(testManifest.getLastTruck(), newTruck);
	}
	
	@Test
	void testGetLastTruckException() {
		testManifest = new Manifest();
		
		assertThrows(DeliveryException.class, ()->{
			testManifest.getLastTruck();
            });
		
	}
	
	
	
	
	
}
