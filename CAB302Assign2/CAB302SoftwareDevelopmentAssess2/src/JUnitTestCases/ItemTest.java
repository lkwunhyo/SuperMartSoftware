package JUnitTestCases;

import static org.junit.jupiter.api.Assertions.*;

import Stock.StockException;
import Stock.Item;

import org.junit.jupiter.api.Test;

class ItemTest {
	
	Item rice;
	Item beans;
	Item ice;
	double valueNaN = Double.NaN;

	// Testing the instantiation of a new Item
	@Test
	void ItemInstantiation() {
		rice = new Item("rice", 2.0, 3.0, 225, 300, 0.0);

	}
	
	// Testing for multiple instantiation
	@Test
	void MultipleInstantiation() {	
		rice = new Item("rice", 2.0, 3.0, 225, 300, 0.0);
		beans = new Item("beans", 1.0, 5.0, 225, 400, 0.0);
		
		assertNotEquals(rice, beans);

	}
	
	@Test
	void GetNameTest() {		
		rice = new Item("rice", 2.0, 3.0, 225, 300, 0.0);
		
		assertEquals(rice.getName(), "rice");

	}
	
	@Test
	void GetManufactureCostsTest() {		
		rice = new Item("rice", 2.0, 3.0, 225, 300, 0.0);
		
		assertEquals(rice.getManufacturingCost(), 2.0);
	}
	
	@Test
	void GetPriceTest() {		
		rice = new Item("rice", 2.0, 3.0, 225, 300, 0.0);
		
		assertEquals(rice.getSellPrice(), 3.0);
	}
	
	@Test
	void GetRePointTest() {		
		rice = new Item("rice", 2.0, 3.0, 225, 300, 0.0);
		
		assertEquals(rice.getReorderPoint(), 225);
	}
	
	@Test
	void GetReAmountTest() {		
		rice = new Item("rice", 2.0, 3.0, 225, 300, 0.0);
		
		assertEquals(rice.getReorderAmount(), 300);
	}
	
	@Test
	void GetTemperatureTest() throws StockException {	
		ice = new Item("ice", 2.0, 5.0, 225, 325, -10.0);
		
		assertEquals(ice.getTemperature(), -10.0);

	}
	
	@Test
	void GetNoTemperatureTest() {	
		ice = new Item("ice", 2.0, 3.0, 225, 300, valueNaN);
		
		assertEquals(ice.getTemperature(), valueNaN);
		
	}

}
