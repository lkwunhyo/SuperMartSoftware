/*
 * This file forms part of the SuperMart Project
 * Assignment Two - CAB302 2018
 *
 */

package Delivery;

/**
 * This class represents an ordinary cargo truck 
 * containing at least:
 * (Cost of truck, Cargo Capacity, Cargo Stock)
 * Extends the Truck Class.
 *
 * @author Jonathan Wai & Kwun Hyo Lee
 * @version 1.0
 */
public class OrdinaryTruck extends Truck {
	
	private static final int MAX_CAPACITY = 1000;
	private static final String TRUCK_TYPE = "Ordinary";
	
	/**
	  * Constructs an OrdinaryTruck object and sets its maximum capacity and 
	  * Truck type according to the type of Truck.
	  * 
	  */
	public OrdinaryTruck() {
		super(TRUCK_TYPE, MAX_CAPACITY);
		//setMaxCapacity(MAX_CAPACITY);
		//setTruckType(TRUCK_TYPE);	
	}
		
	/**
	  * Calculated the cost of the OrdinaryTruck and its cargo to a Double
	  * value.
	  * 
	  * @return The cost of the OrdinaryTruck and its cargo
	  */
	public double calculateCost() {
		return 750 + (0.25 * getCurrentCargo());
	}
		
}
