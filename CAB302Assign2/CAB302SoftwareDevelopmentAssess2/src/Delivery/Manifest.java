/*
 * This file forms part of the SuperMart Project
 * Assignment Two - CAB302 2018
 *
 */

package Delivery;

import java.util.ArrayList;

/**
 * This class represents a collection of trucks in 
 * ArrayList form of Truck type. 
 *
 * @author Jonathan Wai & Kwun Hyo Lee
 * @version 1.0
 */
public class Manifest {
	
	private ArrayList<Truck> manifest;
	
	/**
	  * Constructs a Manifest object, representing a collection of Trucks.
	  * Instantiates an ArrayList of Trucks.
	  * 
	  */
	public Manifest() {
		manifest = new ArrayList<Truck>();		
	}

	/**
	  * Adds a Truck to the Manifest.
	  * 
	  * @param truck The Truck to add to the manifest
	  */
	public void addTruckToManifest(Truck truck) {
		manifest.add(truck);		
	}
	
	/**
	  * Removes a Truck from the Manifest.
	  * 
	  * @param truck The Truck to remove from the manifest
	  */
	public void removeTruckFromManifest(Truck truck) throws DeliveryException {
		if(manifest.isEmpty()) {
			throw new DeliveryException("There are no existing trucks to remove from the Manifest");
		}
		manifest.remove(truck);
	}
	
	/**
	  * Gets the Manifest's ArrayList of Trucks
	  * 
	  * @return The Manifest's ArrayList<Truck>
	  */
	public ArrayList<Truck> getManifest() {
		return manifest;
	}
	
	/**
	  * Returns a Truck of either Truck type given an input temperature and 
	  * adds the Truck to the manifest. 
	  * If the temperature is not a Double.NaN value, the function returns
	  * a RefrigeratedTruck, else, an OrdinaryTruck. 
	  * 
	  * @return A Truck of Truck type given a temperature.
	  */
	public Truck createNewTruck(Double temperature) {
		Truck truck;
		if (!Double.isNaN(temperature)) {
			truck = new RefrigeratedTruck();
		}
		else {
			truck = new OrdinaryTruck();
		}
				
		addTruckToManifest(truck);
		
		return truck;
	}
	
	/**
	  * Returns the Manifest's Truck in the last position of the ArrayList.
	  * 
	  * @return The last Truck in the Manifest's ArrayList
	  */
	public Truck getLastTruck() throws DeliveryException {
		if(manifest.isEmpty()) {
			throw new DeliveryException("The Manifest is empty.");
		}
		return manifest.get(manifest.size() - 1);		
	}
	
}
