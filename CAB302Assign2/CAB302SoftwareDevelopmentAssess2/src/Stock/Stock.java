/*
 *	This file forms part of the SuperMart Project
 *	Assignment Two - CAB302 2018
 *
 */

package Stock;

import java.util.*;
import Stock.StockException;

/**
 * This class represents a collection of items organised 
 * by Items and its quantity. Can be used for 
 * Store Inventory, Stock Orders, Sales Logs, and 
 * Truck Cargo.
 * 
 * @author Kwun Hyo Lee & Jonathan Wai
 * @version 1.0
 */
public class Stock {
	
	private LinkedHashMap<Item, Integer> stock;
	
	/**
	  * Constructs a new Stock object, representing a collection of Items.
	  * Instantiates a LinkedHashMap of Items as the Key and Integer as the Value.
	  * The Stock object represents an Item and its corresponding Quantity.
	  * The Stock object can be used for:
	  * 	Store Inventory, Stock Orders, Sales Logs, Truck Cargos
	  * 
	  * @author Kwun Hyo Lee & Jonathan Wai
	  * @version 1.0
	  */
	public Stock() {
		stock = new LinkedHashMap<Item, Integer>();		
	}
	
	/**
	  * Adds a given quantity to the corresponding Item in the Stock LinkedHashMap. 
	  * If the stock does not contain the specific Item, the Item will be added
	  * to the LinkedHashMap as a new Key and will be provided the input quantity.
	  * 
	  * @param item Name of the Item
	  * @param quantity Quantity of the Item to add
	  */
	public void addStock(Item item, int quantity) {
		if (!stock.containsKey(item)) {
			stock.put(item, 0);
		}
		
		stock.put(item, stock.get(item) + quantity);		
	}
	
	/**
	  * Removes a given quantity from the corresponding Item in the Stock LinkedHashMap. 
	  * If the Item has a lower quantity than the input quantity, a StockException 
	  * will be thrown with the message, "Not enough Stock".
	  * 
	  * @param item Name of the Item
	  * @param quantity Quantity of the Item to remove
	  */
	public void removeStock(Item item, int quantity) throws StockException {
		if (getQuantity(item) < quantity) {
			throw new StockException("Not enough Stock");
		}
		
		stock.put(item, stock.get(item) - quantity);
	}
	
	/**
	  * Adds the quantity of Items in the input Stock (param Stock) to 
	  * the current Stock. 
	  * 
	  * @param newStock Stock to add to the given Stock
	  */
	public void addTwoStocks(Stock newStock) {
		for(Item newItem : newStock.getStock().keySet()) {
			addStock(newItem, newStock.getQuantity(newItem));
		}		
	}
	
	/**
	  * Removes the quantity of Items in the input Stock (param Stock) from 
	  * the current Stock. 
	  * 
	  * @param newStock Stock to remove from the given Stock
	  */
	public void removeTwoStocks(Stock newStock) throws StockException {
		for(Item newItem : newStock.getStock().keySet()) {
			for(Item item : stock.keySet()) {
				if (item.getName().equals(newItem.getName())) {
					removeStock(item, newStock.getQuantity(newItem));
				}
			}
		}
	}
	
	/**
	  * Gets the current Stock
	  * 
	  * @return A LinkedHashMap<Item, Integer> of the Stock
	  */
	public LinkedHashMap<Item, Integer> getStock() {
		return stock;
	}
	
	/**
	  * Gets the quantity of the given Item in the Stock. Returns
	  * 0 if the item does not exist.
	  * 
	  * @return Quantity of item in the current Stock
	  */
	public int getQuantity(Item item) { 
		if(stock.containsKey(item)) {
			if (stock.get(item) >= 0) {
				return stock.get(item);
			}
		}
		
		return   0;		
	}
	
	/**
	  * Sorts the current Stock by Item temperature in lowest to highest temperature order.
	  * 
	  * @return A LinkedHashMap<Item, Integer> sorted by temperature in ascending order.
	  */
	public LinkedHashMap<Item, Integer> sortStockByTemperature() {
		LinkedHashMap<Item, Integer> sortedStock = new LinkedHashMap<Item, Integer>();
		
		// Loops through the current stock as long as items exist
		while (!stock.isEmpty()) {
			Iterator<Item> stockIterator = stock.keySet().iterator();		
			
			// A temporary variable to store the lowest temperature item
			Item lowestTemperatureItem = stockIterator.next();
			
			while (stockIterator.hasNext()) {
				Item curItem = stockIterator.next();		
				if (curItem.isLowerTemperatureThan(lowestTemperatureItem)) {
					lowestTemperatureItem = curItem;					
				}				
			}
			
			sortedStock.put(lowestTemperatureItem, stock.get(lowestTemperatureItem));
			stock.remove(lowestTemperatureItem);
			
		}
		
		stock = sortedStock;
		return stock;
	}
	
}
