/*
 * This file forms part of the SuperMart Project
 * Assignment Two - CAB302 2018
 *
 */

package Delivery;

/**
 * This class represents any exception thrown by any
 * Delivery classes
 *
 * @author Jonathan Wai &amp; Kwun Hyo Lee
 * @version 1.0
 */
public class DeliveryException extends Exception {
	
	public DeliveryException() {
		super();
	}
	
	/**
	  * Constructs a new DeliveryException object with a helpful message
	  * describing the problem.
	  * @param message error message to return to user
	  */
	public DeliveryException(String message) {
		super(message);
	}
}
