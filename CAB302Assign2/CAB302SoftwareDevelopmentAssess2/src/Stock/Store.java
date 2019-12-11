/*
 * This file forms part of the SuperMart Project
 * Assignment Two - CAB302 2018
 *
 */

package Stock;

import java.util.*;

import Delivery.*;
import CSV.*;

/**
 * This class represents an object for the store itself.
 * Adopts the Singleton pattern to avoid multiple 
 * instantiation of the same object. Contains at least
 * the following properties:
 * (Capital, Inventory, Name)
 *
 * @author Kwun Hyo Lee & Jonathan Wai
 * @version 1.0
 */
public class Store {
	
	private static double capital;
	private static Stock inventory;
	private static String name;
	
	private static LinkedHashMap<String, Item> itemsList = null;
	
	private static Store firstInstance = null;
	
	private static boolean itemsListFlag = false;
	private static boolean manifestFlag = false;
		
	
		
	/**
	 * Private Constructor for store to prevent accidental calls
	 *  
	 * @param name String to be used as the Name of Store
	 */
	private Store(String name) {
		Store.inventory = new Stock();
		Store.name = name;		
	} //Store
	
	
	
	/**
	 * Overloaded Constructor to allow setting the Starting Capital in addition to the Name of the store
	 * 
	 * @param name Name of the store
	 * @param capital Value of the Starting Capital of the store
	 */
	private Store(String name, double capital) {
		Store.capital = capital;
		Store.inventory = new Stock();
		Store.name = name;
	} //Store
	
	
	
	/**
	 * Gets the current instance of the Store Object
	 * 
	 * @return Returns current store instance. If no instance has been initialized previously, returns null.
	 */
	public static Store getStoreInstance() {		
		return firstInstance;
	} //getStoreInstance
	
	
	
	/**
	  * Overloaded method to get the current instance of the Store Object
	  * If no instance exists, creates a new instance using the given parameters
	  *
	  * @param store Name Name of the Store
	  * @param starting Capital Starting capital of the Store
	  * 
	  * @return current store instance, if no store instance exists, initialises a new instance using the given parameters and returns that.
	  */
	public static Store getStoreInstance(String storeName, Double startingCapital) {
		if (firstInstance == null) {
			firstInstance = new Store(storeName, startingCapital);			
		}
		
		return firstInstance;
	} //getStoreInstance
	
	
	
	/**
	 * Sets the current instance of store to null
	 */
	public static void destroyInstance() {
		firstInstance = null;
	} //destroyInstance
	
	
	
	/**
	 * Sets the name of the Store
	 * @param name String to be used as name of store
	 */
	public static void setName(String name) {
		Store.name = name;		
	} //setName
	
	
	
	/**
	 * Returns the name of the store as a string
	 * @return Name of store
	 */	
	public static String getName() {
		return name;		
	} //getName
	
	
	
	/**
	 * Sets the capital
	 * @param capital
	 */
	public static void setCapital(double capital) {
		Store.capital = capital;		
	} //setCapital
	
	
	
	/**
	 * Adds amount to current store capital
	 * @param amount Value to be added, negative numbers will remove capital instead.
	 * @throws StockException if a value to be added is negative, and there is not enough capital.
	 */
	public static void addCapital(double amount) throws StockException {
		if (amount < 0) {
			removeCapital(Math.abs(amount));
		}
		else {
			capital += amount;
		}
	} //addCapital
	
	
	
	/**
	 * Removes amount from current store capital
	 * @param amount Value to be subtracted, negative numbers will increase capital instead.
	 * @throws StockException Throws exception if value to be subtracted is greater than the current amount of capital.
	 */
	public static void removeCapital(double amount) throws StockException {
		if (amount > capital) {
			throw new StockException("Not enough Capital");
		}
		else {
			capital -= amount;
		}
	} //removeCapital
	
	
	
	/**
	 * Returns the current value of Capital 
	 * @return capital
	 */
	public static double getCapital() {
		return capital;		
	} //getCapital

	
	
	/**
	 * Returns a Stock object representing the current inventory of the store
	 * @return Stock object representing current store inventory
	 */
	public static Stock getInventory() {
		return inventory;		
	} //getInventory
	
	
	
	/**
	 * Returns a List of items which have been passed to the program from the Item Properties
	 * @return A LinkedHashMap to be accessed using ItemName as key, returning the corresponding Item object
	 */
	public static LinkedHashMap<String, Item> getItemsList() {
		return itemsList;
	} //LinkedHashMap
	
	
	
	/**
	 * Helper function to check if an item has been initialised in the item properties.
	 * @param itemName Name of the item to check
	 * @return returns Item object with the given itemName 
	 * @throws StockException Throws error if no item with the given name exists in itemList
	 */
	public static Item getItem(String itemName) throws StockException {
		if (itemsList.containsKey(itemName)) {
			return itemsList.get(itemName);
		}
		else {
			throw new StockException(itemName + " does not exist in itemsList\r\nPlease add item property to CSV and reload Item properties");
		}
	} //getItem
		
	
	
	/**
	 * Opens csv file from given file path and creates an object for each item with it's specified properties.
	 * If an item already exists in itemsList, it updates the properties of the existing item instead of creating a new Item object.
	 * Adds all objects to the store inventory.
	 * @param filePath Absolute path to CSV file containing item properties
	 * @throws CSVFormatException Throws exception if the number of rows is more or less than the number of defined columns.
	 */
	public static void openAndParseItemsList(String filePath) throws CSVFormatException {
		
		if(!filePath.endsWith(".csv")) {
			throw new CSVFormatException("File is not in .csv format");
		}
		
		ArrayList<ArrayList<String>> csvFile = ParseCsv.openCsvFromPath(filePath);
		
		if (Store.itemsList == null) {
			Store.itemsList = new LinkedHashMap<String, Item>();
		}
				
		final Integer ITEM_NAME_COL = 0;
		final Integer ITEM_COST_COL = 1;
		final Integer ITEM_PRICE_COL = 2;
		final Integer ITEM_REORDER_POINT_COL = 3;
		final Integer ITEM_REORDER_AMOUNT_COL = 4;
		final Integer ITEM_TEMPERATURE_COL = 5;
		final Integer MAX_CSV_COLS = 5;			
		
		for (ArrayList<String> itemRow : csvFile) {
			try {	
				String itemName = itemRow.get(ITEM_NAME_COL);
				Double itemCost = Double.parseDouble(itemRow.get(ITEM_COST_COL));
				Double itemPrice = Double.parseDouble(itemRow.get(ITEM_PRICE_COL));
				Integer itemReorderPoint = Integer.parseInt(itemRow.get(ITEM_REORDER_POINT_COL));
				Integer itemReorderAmount = Integer.parseInt(itemRow.get(ITEM_REORDER_AMOUNT_COL));
				Double itemTemperature = Double.NaN;		
			
				if (ITEM_TEMPERATURE_COL < itemRow.size()) {
					itemTemperature = Double.parseDouble(itemRow.get(ITEM_TEMPERATURE_COL));					
				}
				
				//If item exists, update properties
				if (Store.itemsList.containsKey(itemName)) {
					Item item = getItem(itemName);
					item.setManufacturingCost(itemCost);
					item.setSellPrice(itemPrice);
					item.setReorderPoint(itemReorderPoint);
					item.setReorderAmount(itemReorderAmount);
					item.setTemperature(itemTemperature);
				}
				else { //Else create new item object and add to list
					Item item = new Item(itemName, itemCost, itemPrice, itemReorderPoint, itemReorderAmount, itemTemperature);
					Store.itemsList.put(itemName, item);
				}	
			} //try		
			catch (Exception e) {
				if (e instanceof IndexOutOfBoundsException || itemRow.size() >= MAX_CSV_COLS) {
					throw new CSVFormatException("Incorrect CSV Format");
				}
				else {
					System.out.println(e.getClass() + " " + e.getMessage());;
				}
			} //catch
		} //for (ArrayList<String> itemRow : csvFile)
		
		//Initialise inventory using itemsList
		for (Item item : Store.itemsList.values()) {
			Store.inventory.addStock(item, 0);			
		}	
		itemsListFlag = true;
	} //parseItemsList
	
	
	
	/**
	 * Opens Manifest from given csv filepath and creates a Manifest object.
	 * Creates trucks and adds items to cargo in truck based on what is listed in the given csv file.
	 * Passes the created manifest object to ProcessManifest function (as part of GUI program flow)
	 * @param filePath
	 * @return
	 * @throws StockException
	 * @throws CSVFormatException
	 * @throws DeliveryException
	 */
	public static Manifest openAndParseManifest(String filePath) throws StockException, CSVFormatException, DeliveryException {
		if (!itemsListFlag) {
			throw new StockException("itemsList not initialized, please load item properties first");
		}
		
		ArrayList<ArrayList<String>> csvFile = ParseCsv.openCsvFromPath(filePath);
		
		Manifest manifest = new Manifest();
		Truck truck = null;
		
		final Integer ITEM_NAME_COL = 0;
		final Integer ITEM_QTY_COL = 1;
		final Integer EXPECTED_COLS = 2;
		
		for (ArrayList<String> row : csvFile) {	
			if (row.size() > EXPECTED_COLS) {
				throw new CSVFormatException("Invalid CSV format for Manifest, please check input file");
			}
			
			String itemName = row.get(ITEM_NAME_COL);
			Integer itemQuantity = null;
			if (ITEM_QTY_COL < row.size()) {
				 itemQuantity = Integer.parseInt(row.get(ITEM_QTY_COL));
			}			
			
			//System.out.println(itemName);
			//System.out.println(itemQuantity);
			
			if (row.size() == 1) {
				if (itemName.contains("Refrigerated")) {
					truck = new RefrigeratedTruck();
				}
				else if (itemName.contains("Ordinary")) {
					truck = new OrdinaryTruck();
				}
				else {
					//Error
					throw new DeliveryException("Truck type does not exist: " + itemName);
				}				
				manifest.addTruckToManifest(truck);
			}		
			else if (row.size() == 2)  {
				truck.addCargo(getItem(itemName), itemQuantity);
			}
			
		} //for (ArrayList<String> row : csvFile)
		
		//Process Manifest (For GUI load manifest)
		processManifest(manifest);
		
		return manifest;
		
	} //openAndParseManifest
	
	
	
	/**
	 * Takes manifest object and iterates through, adding stock from each truck to store inventory and removing the cost of the truck from store capital
	 * @param manifest Input manifest object
	 * @throws DeliveryException 
	 * @throws StockException
	 */
	public static void processManifest(Manifest manifest) throws DeliveryException, StockException {		
		Iterator<Truck> truckIterator = manifest.getManifest().iterator();
		
		while (truckIterator.hasNext()) {
			Truck curTruck = truckIterator.next();
			
			getInventory().addTwoStocks(curTruck.getCargo());			
			
			removeCapital(curTruck.calculateCost());			
		}
		manifestFlag = true;
	} //processManifest
	
	
	
	/**
	 * Generates a Manifest object from the current store inventory based on restock levels and amount
	 * @return Manifest object
	 * @throws StockException
	 * @throws DeliveryException
	 */
	public static Manifest generateManifest() throws StockException, DeliveryException {
		if (!itemsListFlag) {
			throw new StockException("itemsList not initialized, please load item properties first");
		}
		
		Manifest manifest = new Manifest();
		Stock restockItems = new Stock();
		
		//Checks if an item needs to be restocked
		for(Item item : inventory.getStock().keySet()) {
			if (inventory.getQuantity(item) <= item.getReorderPoint()) {
				restockItems.addStock(item, item.getReorderAmount());
			}			
		}
		
		//Sorts the list of items which need to be restocked by lowest temperature, for truck cost optimisation.
		restockItems.sortStockByTemperature();		
			
		while (!restockItems.getStock().keySet().isEmpty()) {
			Iterator<Item> restockIterator = restockItems.getStock().keySet().iterator();
			
			while (restockIterator.hasNext()) {
				Item nextItem = restockIterator.next(); 
				
				Truck truck = (manifest.getManifest().size() > 0 ? manifest.getLastTruck() : manifest.createNewTruck(nextItem.getTemperature()));
				
				while (restockItems.getQuantity(nextItem) > 0) {
					//If the current truck is full, create a new truck based on the storage temperature of the current item
					if (truck.getRemainingCapacity() <= 0) {
						truck = manifest.createNewTruck(nextItem.getTemperature());						
					}
					
					//Fills truck with as much capacity as possible
					if (restockItems.getQuantity(nextItem) < truck.getRemainingCapacity()) {
						truck.addCargo(nextItem, restockItems.getQuantity(nextItem));
						restockItems.removeStock(nextItem, restockItems.getQuantity(nextItem));
					}
					else {
						restockItems.removeStock(nextItem, truck.getRemainingCapacity());
						truck.addCargo(nextItem, truck.getRemainingCapacity());										
					}
				} //while (restockItems.getQuantity(nextItem) > 0)
				
				restockIterator.remove();
				
			} //while (restockIterator.hasNext())				
		} //while (!restockItems.getStock().keySet().isEmpty())
		
		return manifest;
	} //generateManifest
	
	
		
	/**
	 * Converts manifest object to String for output to file
	 * @param manifest
	 * @return string output of manifest
	 */
	private static String convertManifestTCsvToString(Manifest manifest) {
		final String DELIMITER = ",";
		final String NEW_LINE = "\r\n";
		final String TRUCK_PREFIX = ">";
		
		StringBuilder stringBuilder = new StringBuilder();
		
		Iterator<Truck> manifestIterator = manifest.getManifest().iterator();
		
		while (manifestIterator.hasNext()) {
			Truck curTruck = manifestIterator.next();
					
			stringBuilder.append(TRUCK_PREFIX + curTruck.getTruckType());
			stringBuilder.append(NEW_LINE);
			
			Iterator<Item> cargoIterator = curTruck.getCargo().getStock().keySet().iterator();
			
			while (cargoIterator.hasNext()) {
				Item curItem = cargoIterator.next();
				
				stringBuilder.append(curItem.getName());
				stringBuilder.append(DELIMITER);
				stringBuilder.append(curTruck.getCargo().getQuantity(curItem));
				stringBuilder.append(NEW_LINE);				
				
			} //while (cargoIterator.hasNext())	
		} //while (manifestIterator.hasNext())
		
		return stringBuilder.toString();		
	} //convertManifestTCsvToString
	
	
	
	/**
	 * Generates a new manifest and outputs it to a file at the given filepath location
	 * @param filePath
	 * @throws StockException
	 * @throws DeliveryException
	 */
	public static void outputManifestToFile(String filePath) throws StockException, DeliveryException {
		Manifest manifest = generateManifest();
		String csvString = convertManifestTCsvToString(manifest);
		ParseCsv.outputToCsvFile(filePath, csvString);		
	} //outputManifestToFile
	
	
	
	/**
	 * Opens Sales Log from given csv filepath and creates a SalesLog object.
	 * @param filePath
	 * @return
	 * @throws StockException
	 * @throws CSVFormatException
	 * @throws DeliveryException 
	 */
	public static Stock openAndParseSalesLog(String filePath) throws StockException, CSVFormatException, DeliveryException {
		if (!itemsListFlag) {
			throw new StockException("itemsList not initialized, please load item properties first");
		} else if (!manifestFlag) {
			throw new DeliveryException("Please generate and run manifest before processing a new sales Log");
		}
		
		ArrayList<ArrayList<String>> csvFile = ParseCsv.openCsvFromPath(filePath);
		
		Stock salesLog = new Stock();
		 
		final Integer ITEM_NAME_COL = 0;
		final Integer ITEM_QTY_COL = 1;
		final Integer EXPECTED_ROW_SIZE = 2;
		
		for (ArrayList<String> row : csvFile) {		
			if (row.size() != EXPECTED_ROW_SIZE)  {
				throw new CSVFormatException("Sales Log formatted incorrectly");
			}
			else {
				String itemName = row.get(ITEM_NAME_COL);
				Integer itemQuantity = Integer.parseInt(row.get(ITEM_QTY_COL));
						
				//System.out.println(itemName);
				//System.out.println(itemQuantity);							
			
				salesLog.addStock(getItem(itemName), itemQuantity);
			}
		} //for (ArrayList<String> row : csvFile)
		
		//Move program flow to backend for GUI buttons
		processSalesLog(salesLog);
		
		return salesLog;		
	} //openAndParseSalesLog
	
	
	
	/**
	 * Takes input sales log and processes it.
	 * Subtracts stock from inventory and adds corresponding amount to capital.
	 * @param salesLog
	 * @throws StockException Throws exception if there is not enough stock remaining in store inventory
	 */
	public static void processSalesLog(Stock salesLog) throws StockException {
	
		for(Item saleItem : salesLog.getStock().keySet()) {
			addCapital(saleItem.getSellPrice() * salesLog.getQuantity(saleItem));
			inventory.removeStock(saleItem, salesLog.getQuantity(saleItem));
		}				
		
		manifestFlag = false;
	} //processSalesLog
	
} //Store class
