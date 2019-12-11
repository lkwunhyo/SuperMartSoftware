package JUnitTestCases;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Executable;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import Stock.Item;
import Stock.Stock;
import Stock.StockException;


class StockTest {
	
	// Instantiating variables used for the test cases
	Stock stockTest;
	Stock newStockTest;
	Stock assertStock;
	double valueNaN = Double.NaN;
	Item rice = new Item("rice", 2.0, 3.0, 225, 300, valueNaN);
	Item beans = new Item("beans", 1.0, 2.0, 400, 200, valueNaN);
	Item ice = new Item("ice", 2.0, 3.0, 225, 300, -10.0);

	// Testing Stock object instantiation
	@Test
	void StockInstantiation() {
		stockTest = new Stock();
		
		assertEquals(stockTest.getStock().isEmpty(), true);
		
	}
	
	@Test
	void MultipleInstantiation() {
		stockTest = new Stock();
		newStockTest = new Stock();
		
		assertNotEquals(stockTest, newStockTest);
		
	}
	
	// Testing quantity getter
	@Test
	void GetQuantityTest() {
		stockTest = new Stock();
		stockTest.addStock(rice, 3);
		
		assertEquals(stockTest.getQuantity(rice), 3);
		
	}
	
	// Testing the quantity of non-existing item
	@Test
	void GetNoQuantityTest() {
		stockTest = new Stock();
		
		assertEquals(stockTest.getQuantity(rice), 0);
		
	}
	
	// Testing setter for quantity
	@Test
	void AddStockTest() {
		stockTest = new Stock();
		stockTest.addStock(rice, 3);
		
		assertEquals(stockTest.getQuantity(rice), 3);
		
	}
	
	// Testing another setter for quantity
	@Test
	void RemoveStockTest() throws StockException{
		stockTest = new Stock();
		stockTest.addStock(rice, 3);
		stockTest.removeStock(rice, 3);
		
		assertEquals(stockTest.getQuantity(rice), 0);
		
	}
	
	@Test
	void AddTwoStockTest() {
		stockTest = new Stock();
		newStockTest = new Stock();
		assertStock = new Stock();
		
		stockTest.addStock(rice, 2);
		stockTest.addStock(beans, 3);
		
		newStockTest.addStock(rice, 2);
		newStockTest.addStock(beans, 2);
		
		stockTest.addTwoStocks(newStockTest);
		
		assertStock.addStock(rice,  4);
		assertStock.addStock(beans,  5);
		
		assertEquals(stockTest.getStock().equals(assertStock.getStock()), true);
		
	}
	
	// Testing another setter for quantity
	@Test
	void RemoveTwoStockTest() throws StockException {
		stockTest = new Stock();
		newStockTest = new Stock();
		assertStock = new Stock();
		
		stockTest.addStock(rice, 2);
		stockTest.addStock(beans, 3);
		
		newStockTest.addStock(rice, 2);
		newStockTest.addStock(beans, 1);
		
		stockTest.removeTwoStocks(newStockTest);
		
		assertStock.addStock(rice,  0);
		assertStock.addStock(beans,  2);
		
		assertEquals(stockTest.getStock(), assertStock.getStock());
		
	}
	
	// Testing StockException for removing too much of a quantity
	@Test 
	void RemoveStockExceptionTest() {
		
		stockTest = new Stock();
		stockTest.addStock(rice, 3);
		
		assertThrows(StockException.class, ()->{
			stockTest.removeStock(rice,  5);
            });
	}
	

	
	

}
