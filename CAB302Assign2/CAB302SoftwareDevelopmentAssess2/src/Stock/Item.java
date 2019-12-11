/*
 *	This file forms part of the SuperMart Project
 *	Assignment Two - CAB302 2018
 *
 */

package Stock;

/**
 * This class represents any item and its given properties, possessing at least:
 * (Name, Manufacturing Costs, Sell Price, Reorder Point, Reorder Amount, 
 *  Temperature)
 * Note that not all items are temperature controlled, and so they will not 
 * need a temperature.
 *
 * @author Kwun Hyo Lee & Jonathan Wai
 * @version 1.0
 */
public class Item {
	
	private String name;
	private double manufacturingCost;
	private double sellPrice;
	private int reorderPoint;
	private int reorderAmount;
	private double temperature;
	
	/**
	  * Constructs a new Item object containing at least the following properties:
	  * (Name, Manufacturing Cost, Sell Price, Reorder Point, Reorder Amount
	  *  Temperature)
	  *  Note that not all items are temperature controlled and may not need a 
	  *  temperature
	  *
	  * @param name Name of the Item
	  * @param manufacturingCost Manufacturing Costs of the Item
	  * @param sellPrice Selling Price of the Item
	  * @param reorderPoint Minimum Reorder Point of the Item
	  * @param reorderAmount Minimum Reorder Amount of the Item
	  * @param temperature Required Temperature of the Item
	  */
	public Item(String name, double manufacturingCost, double sellPrice, int reorderPoint, int reorderAmount, double temperature) {		
		this.name = name;
		this.manufacturingCost = manufacturingCost;
		this.sellPrice = sellPrice;
		this.reorderPoint = reorderPoint;
		this.reorderAmount = reorderAmount;
		this.temperature = (Double.isNaN(temperature) ? Double.NaN : temperature);
	}
			
	/**
	  * Sets a String as the Item's name
	  *
	  * @param name Name of the Item
	  */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	  * Sets a Double to the Item's manufacturing costs
	  *
	  * @param manufacturingCost Double value of the manufacturing cost of the Item
	  */
	public void setManufacturingCost(double manufacturingCost) {
		this.manufacturingCost = manufacturingCost;
	}
	
	/**
	  * Sets a Double to the Item's selling price
	  *
	  * @param sellPrice Double value of the selling price of the Item
	  *
	  */
	public void setSellPrice(double sellPrice) {
		this.sellPrice = sellPrice;
	}
	
	/**
	  * Sets the Item's reorder Point to an Integer.
	  * Should be equal to or greater than 0.
	  *
	  * @param reorderPoint Reordering point of the Item
	  */
	public void setReorderPoint(int reorderPoint) {
		this.reorderPoint = reorderPoint;
	}
	
	/**
	  * Sets the Item's minimum reordering amount to a Double.
	  * Should be equal to or greater than 0.
	  *
	  * @param reorderAmount Reordering amount of the Item
	  */
	public void setReorderAmount(int reorderAmount) {
		this.reorderAmount = reorderAmount;
	}
	
	/**
	  * Sets a Double to the Item's Temperature
	  *
	  * @param temperature Temperature of a temperature-controlled Item
	  */
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}
	
	/**
	  * Gets the Item's name
	  *
	  * @return Name of the Item
	  */
	public String getName() {
		return name;
	}
	
	/**
	  * Gets the Item's manufacturing cost
	  *
	  * @return Manufacturing cost of the Item
	  */
	public double getManufacturingCost() {
		return manufacturingCost;
	}
	
	/**
	  * Gets the Item's selling price
	  *
	  * @return Selling price of the Item
	  */
	public double getSellPrice() {
		return sellPrice;
	}
	
	/**
	  * Gets the Item's minimum reordering point
	  *
	  * @return Reordering point of the Item
	  */
	public int getReorderPoint() {
		return reorderPoint;
	}
	
	/**
	  * Gets the Item's minimum reordering amount
	  *
	  * @return Reordering amount of the Item
	  */
	public int getReorderAmount() {
		return reorderAmount;
	}
	
	/**
	  * Gets the Item's temperature. 
	  * 
	  * @return Temperature of the Item
	  */
	public double getTemperature() {
		return temperature;
	}
	
	/**
	  * Calculates whether the given Item has a lower temperature requirement than
	  * the input Item (param Item).
	  * If the input Item does not have a temperature, the function always returns
	  * true.
	  *
	  * @return Returns true if the given Item's temperature is lower than the input
	  * Item's temperature
	  */
	public boolean isLowerTemperatureThan(Item item) {
		// Checks if the input item's temperature exists
		if (!Double.isNaN(item.getTemperature())) {	
			// Checks if the current item's temperature exists
			if (!Double.isNaN(this.temperature)) {	// Returns true if the current item's temperature is lower
				return this.temperature < item.getTemperature();
			}
			else {
				return false;
			}
		}
		else if (!Double.isNaN(this.temperature)) {		// If the current item has a temperature, 
			return true;								// the function returns true
		}
		
		return false;
	}

}
