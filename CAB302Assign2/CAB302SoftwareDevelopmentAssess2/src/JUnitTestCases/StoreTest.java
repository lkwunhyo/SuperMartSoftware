package JUnitTestCases;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedHashMap;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import Stock.Item;
import Stock.Stock;
import Stock.StockException;
import Stock.Store;

class StoreTest {
	
	Store testStore;
	Store newSingletonStore;
	double valueNaN = Double.NaN;
	Item rice = new Item("rice", 2.0, 3.0, 225, 300, valueNaN);
	Item ice = new Item("ice", 2.0, 3.0, 225, 300, -10.0);
	
	@Test
	void instantiateStore() {
		if(Store.getStoreInstance() != null) {
			Store.destroyInstance();
		}
		
		Store testStore = Store.getStoreInstance("testStore", 100000.0);
		
		assertEquals(!testStore.equals(null), true);
		
	}
	
	@Test
	void testSingleton() {
		
		if(Store.getStoreInstance() != null) {
			Store.destroyInstance();
		}
		
		Store testStore = Store.getStoreInstance("Test Store 1", 100000.0);
		Store newSingletonStore = Store.getStoreInstance("thisStoreShouldNotExist", 200.0);
		
		assertEquals(testStore, newSingletonStore);		
		
	}
	
	@Test
	void testGetCapital() {
		if(Store.getStoreInstance() != null) {
			Store.destroyInstance();
		}
		
		testStore = Store.getStoreInstance("testStore", 100000.0);

		assertEquals(Store.getCapital(), 100000.0);
		
	}
	
	@Test
	void testSetCapital() {
		if(Store.getStoreInstance() != null) {
			Store.destroyInstance();
		}
		
		testStore = Store.getStoreInstance("testStore",  1000000.0);
		Store.setCapital(20.0);
		
		assertEquals(Store.getCapital(), 20.0);
		
	}
	
	@Test
	void testAddCapital() {
		if(Store.getStoreInstance() != null) {
			Store.destroyInstance();
		}
		
		testStore = Store.getStoreInstance("testStore", 100000.0);
		Store.addCapital(100000.0);
		
		assertEquals(Store.getCapital(), 200000.0);
	}
	
	@Test
	void testRemoveCapital() {
		if(Store.getStoreInstance() != null) {
			Store.destroyInstance();
		}
		
		testStore = Store.getStoreInstance("testStore", 100000.0);
		Store.removeCapital(100000.0);
		
		assertEquals(Store.getCapital(), 0.0);
	}
	
	@Test
	void testGetName() {
		if(Store.getStoreInstance() != null) {
			Store.destroyInstance();
		}
		
		testStore = Store.getStoreInstance("testStore", 100000.0);
		
		assertEquals(Store.getName(), "testStore");
	}
	
	@Test
	void testSetName() {
		if(Store.getStoreInstance() != null) {
			Store.destroyInstance();
		}
		
		testStore = Store.getStoreInstance("testStore", 100000.0);
		Store.setName("newTestStore");
		
		assertEquals(Store.getName(), "newTestStore");
	}
	
	@Test
	void testGetInitialInventory() {
		if(Store.getStoreInstance() != null) {
			Store.destroyInstance();
		}
		
		testStore = Store.getStoreInstance("testStore",  100000.0);
				
		assertEquals(Store.getInventory().getStock().equals(new LinkedHashMap<Item, Integer>()), true);
	}
	
	@Test		////////////////////////////////////////////////
	void testGetItemException(){
		if(Store.getStoreInstance() != null) {
			Store.destroyInstance();
		}
		
		testStore = Store.getStoreInstance("testStore",  100000.0);
		
		assertThrows(StockException.class, () -> {
			Store.getItem("cookies");
            });
		
	}
	
	@Test		////////////////////////////////////////////////
	void testGetItem() throws StockException {
		if(Store.getStoreInstance() != null) {
			Store.destroyInstance();
		}
		
		testStore = Store.getStoreInstance("testStore",  100000.0);
		
		try {
			Store.getItemsList().put(rice.getName(), rice);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			
		}
		
		assertEquals(Store.getItem(rice.getName()), rice);
	}
		

}
