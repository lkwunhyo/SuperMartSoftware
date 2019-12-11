/*
 * This file forms part of the SuperMart Project
 * Assignment Two - CAB302 2018
 *
 */

package Delivery;

import Stock.Item;

/**
 * This class represents a refrigerated cargo truck 
 * containing at least:
 * (Cost of truck, Cargo Capacity, Cargo Stock, Temperature)
 * Extends the Truck Class.
 *
 * @author Jonathan Wai & Kwun Hyo Lee
 * @version 1.0
 */
public class RefrigeratedTruck extends Truck {
	
	private static final int MAX_CAPACITY = 800;
	private static final String TRUCK_TYPE = "Refrigerated";
			
	/**
	  * Constructs a new Truck object and sets its maximum capacity and 
	  * Truck type according to the type of Truck.
	  *
	  */
	public RefrigeratedTruck() {
		super(TRUCK_TYPE, MAX_CAPACITY);
		//setMaxCapacity(MAX_CAPACITY);
		//setTruckType(TRUCK_TYPE);		
	}
	
	/**
	  * Calculates the cost of the RefrigeratedTruck and its cargo to a 
	  * Double value. 
	  * Throws a DeliveryException if all the Items in the cargo is 
	  * non-temperature-controlled.
	  *
	  * @return The cost of the RefrigeratedTruck and its cargo
	  */
	//deliveryException - if all items are non refrigerated, throw exception
	public double calculateCost() throws DeliveryException {
		return 900 + (200 * Math.pow(0.7, getTemperature() / 5));
	}

	/**
	  * Calculates the temperature of the RefrigeratedTruck by setting
	  * the lowest temperature-controlled Item's temperature as the 
	  * temperature.
	  *
	  * @return The temperature of the RefrigeratedTruck
	  */
	
	//throw exception
	public double getTemperature() throws DeliveryException {
		final double MAX_TEMPERATURE = 10;
		double temperature = MAX_TEMPERATURE + 1; 
		
		for (Item item : getCargo().getStock().keySet()) {			
			if(item.getTemperature() < temperature) {
				temperature = item.getTemperature();
			}
		}
		
		if (temperature > MAX_TEMPERATURE) {
			throw new DeliveryException();
		}
		
		return temperature;
	}
		
}
