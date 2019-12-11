/*
 * This file forms part of the SuperMart Project
 * Assignment Two - CAB302 2018
 *
 */

package Stock;

/**
 * This class represents any exception thrown by any
 * Stock classes
 *
 * @author Jonathan Wai & Kwun Hyo Lee
 * @version 1.0
 */
public class StockException extends Exception{

	public StockException() {
		super();
	}

	/**
	  * Constructs a new StockException object with a helpful message 
	  * describing the problem. 
	  * 
	  */
	public StockException(String message) {
		super(message);
	}
	
	
}
