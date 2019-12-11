/*
 * This file forms part of the SuperMart Project
 * Assignment Two - CAB302 2018
 *
 */

package Delivery;

import Stock.Item;
import Stock.Stock;
import Stock.StockException;

/**
 * This class represents an abstract class for the two
 * truck types (RefrigeratedTruck and OrdinaryTruck).
 * 
 * @author Jonathan Wai & Kwun Hyo Lee
 * @version 1.0
 */
public abstract class Truck {
	
	private Stock cargo;
	private int maxCapacity;
	private String truckType;
	
	protected Truck(String truckType, int maxCapacity) {
		this.cargo = new Stock();
		this.truckType = truckType;
		this.maxCapacity = maxCapacity;
	}
	
	/**
	  * Gets the maximum capacity of a Truck.
	  * 
	  * @return An Integer representing the maximum capacity of the Truck 
	  */
	public int getMaxCapacity() {
		return maxCapacity;		
	}
	
	/**
	  * Sets the maximum capacity of a Truck to an Integer.
	  * 
	  * @param maxCapacity The max capacity of the Truck
	  */
	public void setMaxCapacity(int maxCapacity) {
		this.maxCapacity = maxCapacity; 
	}
	
	/**
	  * Gets the Truck type, either RefrigeratedTruck or OrdinaryTruck.
	  * 
	  * @return A String of the given Truck type
	  */
	public String getTruckType() {
		return truckType;
	}
	
	/**
	  * Sets the Truck type to a String
	  * 
	  * @param truckType The type of Truck
	  */
	public void setTruckType(String truckType) {
		this.truckType = truckType;
	}
	
	/**
	  * Gets the cargo of a Truck.
	  * 
	  * @return A Stock representing the cargo of a Truck
	  */
	public Stock getCargo() {
		return cargo;
	}
	
	
	/**
	  * Gets the current sum of items in a Truck.
	  * 
	  * @return An Integer representing the current sum of quantities of Items 
	  * in the Truck 
	  */
	public int getCurrentCargo() {
		int total = 0;
		
		for (Item item : getCargo().getStock().keySet()) {
			total += cargo.getQuantity(item);			
		}
		
		//System.out.println("total = " + total);
		return total;
	}
	
	/**
	  * Gets the remaining capacity left in the Truck.
	  * 
	  * @return An Integer representing the available capacity of the Truck 
	  */
	public int getRemainingCapacity() {
		return maxCapacity - getCurrentCargo();	
	}
		
	/**
	  * Adds a quantity of Items to the corresponding Item in the Truck
	  * Stock. 
	  * 
	  * @param item Name of the Item
	  * @param quantity Quantity of the Item
	  */
	public void addCargo(Item item, int quantity) throws StockException {	
		if (getCurrentCargo() + quantity > maxCapacity) {
			throw new StockException("Not enough capacity remaining to add stock");
		}
		
		cargo.addStock(item, quantity);	
	}
	
	/**
	  * Removes a quantity of Items from the corresponding Item in the Truck
	  * Stock. 
	  * 
	  * @param item Name of the Item
	  * @param quantity Quantity of the Item
	  */
	public void removeCargo(Item item, int quantity) throws StockException {
		cargo.removeStock(item, quantity);
	}
	
	/**
	  * Calculates the cost of the Truck and its cargo.
	  * 
	  * @return The calculated cost of the Truck and its cargo
	  */ 
	public abstract double calculateCost() throws DeliveryException;
}
