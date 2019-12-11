package JUnitTestCases;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Delivery.DeliveryException;
import Delivery.OrdinaryTruck;
import Delivery.RefrigeratedTruck;
import Stock.Item;
import Stock.Stock;
import Stock.StockException;

class TruckTest {

	RefrigeratedTruck rTruckTest;
	OrdinaryTruck oTruckTest;
	
	double valueNaN = Double.NaN;
	Item rice = new Item("rice", 2.0, 3.0, 225, 300, valueNaN);
	Item ice = new Item("ice", 2.0, 3.0, 225, 300, -10.0);
	
	@Test
	void testTruckInstantiation() {
		rTruckTest = new RefrigeratedTruck();
		oTruckTest = new OrdinaryTruck();

		assertEquals(rTruckTest != null && oTruckTest != null, true);
	}
	
	@Test
	void testGetMaxCapacity() {
		rTruckTest = new RefrigeratedTruck();
		oTruckTest = new OrdinaryTruck();

		assertEquals(rTruckTest.getMaxCapacity() == 800 
				&& oTruckTest.getMaxCapacity() == 1000, true);
		
	}
	
	@Test
	void testSetMaxCapacity() {
		rTruckTest = new RefrigeratedTruck();
		oTruckTest = new OrdinaryTruck();
		
		rTruckTest.setMaxCapacity(1000);
		oTruckTest.setMaxCapacity(800);

		assertEquals(rTruckTest.getMaxCapacity() == 1000 
				&& oTruckTest.getMaxCapacity() == 800, true);
		
	}
	
	@Test
	void testGetTruckType() {
		rTruckTest = new RefrigeratedTruck();
		oTruckTest = new OrdinaryTruck();

		assertEquals(rTruckTest.getTruckType() == "Refrigerated"
				&& oTruckTest.getTruckType() == "Ordinary", true);
		
	}
	
	@Test
	void testSetTruckType() {
		rTruckTest = new RefrigeratedTruck();
		oTruckTest = new OrdinaryTruck();

		rTruckTest.setTruckType("Ordinary");
		oTruckTest.setTruckType("Refrigerated");
		
		assertEquals(rTruckTest.getTruckType() == "Ordinary"
				&& oTruckTest.getTruckType() == "Refrigerated", true);
		
	}
	
	@Test
	void testOrdinaryInstantiation() {
		oTruckTest = new OrdinaryTruck();

		assertEquals(oTruckTest != null && oTruckTest.getMaxCapacity() == 1000 
				&& oTruckTest.getTruckType() == "Ordinary", true);
		
	}
	
	@Test
	void testGetCargo() {
		Stock testStock = new Stock();
		
		rTruckTest = new RefrigeratedTruck();
		oTruckTest = new OrdinaryTruck();
		
		
		assertEquals(rTruckTest.getCargo().getStock().equals(testStock.getStock()) 
				&& oTruckTest.getCargo().getStock().equals(testStock.getStock()), true);
		
	}
	
	@Test
	void testAddCargo() {		
		rTruckTest = new RefrigeratedTruck();
		
		try {
			rTruckTest.addCargo(rice, 100);
		} catch (StockException e) {
			System.out.println(e.getMessage());
		}
		
		assertEquals(rTruckTest.getCargo().getQuantity(rice) == 100, true);
		
	}
	
	@Test
	void testRemoveCargo() {		
		rTruckTest = new RefrigeratedTruck();
		
		try {
			rTruckTest.addCargo(rice, 100);
			rTruckTest.removeCargo(rice, 50);
		} catch (StockException e) {
			System.out.println(e.getMessage());
		}
		
		assertEquals(rTruckTest.getCargo().getQuantity(rice) == 50, true);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	@Test
	void testAddCargoException() {		
		rTruckTest = new RefrigeratedTruck();
		
		assertThrows(StockException.class, ()->{
			rTruckTest.addCargo(rice, 1000);
            });
	}

	@Test
	void testRemoveCargoException() {		
		rTruckTest = new RefrigeratedTruck();
		
		assertThrows(StockException.class, ()->{
			rTruckTest.addCargo(rice, 100);
			rTruckTest.removeCargo(rice, 200);
            });
		
	}
	
	@Test
	void testGetCurrentCargo() {
		Stock testStock = new Stock();
		
		rTruckTest = new RefrigeratedTruck();
		oTruckTest = new OrdinaryTruck();
		try {
			oTruckTest.addCargo(rice, 100);
		} catch (StockException e) {
			System.out.println(e.getMessage());
		}
		
		assertEquals(rTruckTest.getCurrentCargo() == 0
				&& oTruckTest.getCurrentCargo() == 100, true);
		
	}
	
	@Test
	void testGetRemainingCapacity() {
		rTruckTest = new RefrigeratedTruck();
		
		try {
			rTruckTest.addCargo(rice, 200);
		} catch (StockException e1) {
			System.out.println(e1.getMessage());
		}
		
		assertEquals(rTruckTest.getRemainingCapacity() == 600, true);
		
	}
	
	@Test
	void testInitialRefrigeratedCalculateCosts() {
		rTruckTest = new RefrigeratedTruck();

		assertThrows(DeliveryException.class, ()->{
			rTruckTest.calculateCost();
            });
		
	}
	
	@Test
	void testInitialOrdinaryCalculateCosts() {
		oTruckTest = new OrdinaryTruck();
		
		assertEquals(oTruckTest.calculateCost(), 750);
		
	}
	
	
	@Test
	void testRefrigeratedCalculateCosts() throws DeliveryException {
		rTruckTest = new RefrigeratedTruck();
		double cost = 900 + (200 * Math.pow(0.7, -10 / 5));
		double newCost;
				
		try {
			rTruckTest.addCargo(ice, 20);
			newCost = rTruckTest.calculateCost();
		} catch (StockException e) {
			System.out.println(e.getMessage());
			newCost = 0;
		}

		assertEquals(newCost, cost);
		
	}
	
	@Test
	void testOrdinaryCalculateCosts() {
		oTruckTest = new OrdinaryTruck();
		double cost = 750 + (0.25 * 100);
		
		try {
			oTruckTest.addCargo(rice, 100);
		} catch (StockException e) {
			System.out.println(e.getMessage());
		}
		
		assertEquals(oTruckTest.calculateCost(), cost);
		
	}
	
	
	
	
}
