/*
 * This file forms part of the SuperMart Project
 * Assignment Two - CAB302 2018
 *
 */

package GUI;


import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Iterator;
import java.util.LinkedHashMap;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Stock.*;




/**
 * This class represents the Graphical User Interface of the main Frame.
 * Displays the Store Capital, and has buttons providing the functionality 
 * to:
 * 		Load Inventory, Load Item Properties, Load Manifest, Load Sales Logs, 
 * 		and Export Manifests
 * 
 * @author Kwun Hyo Lee & Jonathan Wai
 * @version 1.0 
 */
public class StoreGUI extends JFrame implements ActionListener {
	
	public static final int WINDOW_STARTING_WIDTH = 800;
	public static final int WINDOW_STARTING_HEIGHT = 500;
	
	Store store;
	LinkedHashMap<String, Item> itemProperties;
		
	JPanel controlPanel;
	JPanel capitalPanel;
	JPanel btnPanel;
	JPanel paddingPanel;
	JPanel tablePanel;
	
	JLabel capitalLabel;
	
	JButton invButton;
	JButton loadProp;
	JButton loadManif;
	JButton loadSales;
	JButton exportManif;
	
	JTable inventoryTable;
	JScrollPane scrollPane;
	
	/**
	 * Constructs a new GUI object which inherits from the JFrame class.
	 * Represents the GUI to be used for the given input storeObj. Sets
	 * the storeObj parameter as its store variable for accessing the 
	 * store properties. 
	 * 
	 * @param storeObj Store Object to be used with the GUI
	 */
	public StoreGUI(Store storeObj) {
		super();
		store = storeObj;
		itemProperties = new LinkedHashMap<String, Item>();
		
		createWindow();	
		updateCapital();
	}	
	
	/**
	 * Instantiates GUI
	 * Populates a JFrame window and with our GUI components
	 * Creates and sets layout for our Labels, Buttons, and Table
	 */
	public void createWindow() {		
		// Create and set up the windows.
			// Main window.
		this.setTitle("SuperMart Program");
		setDefaultLookAndFeelDecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(WINDOW_STARTING_WIDTH, WINDOW_STARTING_HEIGHT));
		setLocation(new Point(200, 100));
		
		// Initialise components.
		GridLayout mainGrid = new GridLayout(2, 1);
		controlPanel = new JPanel(mainGrid);
		
		//Grid for buttons
		GridLayout btnLayout = new GridLayout(1, 4, 10, 1);
		btnPanel = new JPanel(btnLayout);
		//Grid to position buttons and to scale height
		GridLayout btnHeightLayout = new GridLayout(3, 1);
		paddingPanel = new JPanel(btnHeightLayout);
		
		GridLayout capitalLayout = new GridLayout(2, 1);
		capitalPanel = new JPanel(capitalLayout);

		capitalLabel = new JLabel();
		
		// Instantiate JTable and make visible
			// TEST RUN
		Object[] columnNames = {"Item", "Quantity", "Cost", "Selling Price", "Reorder Point", "Reorder Amount", "Temperature"};
		Object[][] data = {}; //inventoryTableRows
		
		inventoryTable = new JTable(new DefaultTableModel(data, columnNames));
		//inventoryTable = new JTable(data, columnNames);
		inventoryTable.setPreferredScrollableViewportSize(new Dimension(500, 300));
		inventoryTable.setFillsViewportHeight(true);
		scrollPane = new JScrollPane(inventoryTable);
			
		loadProp = createButton("<html>&nbsp;Load Item" + "<br>" + "Properties");
		loadManif = createButton("Load Manifests");
		loadSales = createButton("Load Sales Log");
		exportManif = createButton("Export Manifest");
		
		// Set up components.
		capitalLabel.setFont(capitalLabel.getFont().deriveFont(30.0f));
		capitalLabel.setHorizontalAlignment(JLabel.CENTER);
		
		btnPanel.add(loadProp);
		btnPanel.add(loadManif);
		btnPanel.add(loadSales);
		btnPanel.add(exportManif);

		capitalPanel.add(capitalLabel);
		paddingPanel.add(btnPanel);
		capitalPanel.add(paddingPanel);
		controlPanel.add(capitalPanel);	
		controlPanel.add(scrollPane);
	
		// Add controlPanel to mainWindow.
		getContentPane().add(controlPanel);
		setVisible(true);		
	}
	
	/**
	 * Creates a JButton with the given text.
	 * Adds an ActionListener for additional functionality.
	 * 
	 * @param text The text set on the button
	 * @return JButton object with an ActionListener.
	 */
	JButton createButton(String text) {
		JButton button = new JButton(text);
		button.addActionListener(this);
		return button;
	}
	
	
	/**
	 * Pops up a dialog box for the user to pick a file to open
	 * @return file path of selected file
	 */
	private String getFilePathWithDialog() {
		JFileChooser fileChooser = new JFileChooser(); 
		
		String filePath = null;
		if(fileChooser.showOpenDialog(fileChooser) == JFileChooser.APPROVE_OPTION) {
			/// Exists for the JFileChooser
		}
		
		// Receiving the selected file
		File selectedFile = fileChooser.getSelectedFile();
		
		// Opens a dialog box, warning user if no file is selected
		if(selectedFile != null) {
			filePath = selectedFile.getAbsolutePath();
		}
		else {			
			JOptionPane.showMessageDialog(this, "No file selected");
		}
			
		return filePath;
	}

	
	
	/**
	 * Creates a dialog for the user and Creates a csv file at selected location with user prompted file name
	 * @return filepath of the created file
	 */
	private String createFileWithDialog() {
		
		JFileChooser fileChooser = new JFileChooser(); 
		//setDefaultCloseOperation(JFileChooser.EXIT_ON_CLOSE);
		//fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		String filePath = null;
		File newFile = null;
		
		if(fileChooser.showOpenDialog(fileChooser) == JFileChooser.APPROVE_OPTION) {
			/// Exists for the JFileChooser			
			File selectedFile = fileChooser.getSelectedFile();
			
			if(selectedFile != null) {
				filePath = selectedFile.getAbsolutePath() + ".csv";
			}
			else {			
				JOptionPane.showMessageDialog(this, "No file selected");
			}
			
			newFile = new File(filePath);
		} 
		
		if(newFile != null) {
			try {
				BufferedWriter file = new BufferedWriter(new FileWriter(newFile));
				file.close();
				System.out.println("File written");
			} catch (IOException e) {
				System.out.println("Cannot create file");
			}

		}

		return filePath;
	}
	
	
	/**
	 * Updates the Store Capital JLabel on the main window.
	 * Used whenever the capital is changed, to show the 
	 * correct amount.
	 * 
	 */
	void updateCapital() {
		String capital = NumberFormat.getCurrencyInstance().format(Store.getCapital());
		capitalLabel.setText("Store Capital: " + capital);		
	}
	
	
	/**
	 * Updates the table contents from the store inventory
	 */
	void updateTable() {
		
		DefaultTableModel inventoryTableModel = (DefaultTableModel) inventoryTable.getModel();
		
		//Clears all rows from table
		//System.out.println(inventoryTableModel.getRowCount());
		for (int i = inventoryTableModel.getRowCount() - 1; i >= 0; i--) {
			inventoryTableModel.removeRow(i);
		}
		
		//Iterates through store inventory and creates a new row for each item
		Iterator<Item> stockIterator = Store.getInventory().getStock().keySet().iterator();
		
		while (stockIterator.hasNext()) {
			Item item = stockIterator.next();
			
			String itemName = item.getName();
			Integer itemQuantity = Store.getInventory().getQuantity(item);
			Double itemCost = item.getManufacturingCost();
			Double itemPrice = item.getSellPrice();
			Integer itemReorderPoint = item.getReorderPoint();
			Integer itemReorderAmount = item.getReorderAmount();
			Double itemTemperature = (Double.isNaN(item.getTemperature()) ? null : item.getTemperature());
			
			Object[] tableRow = new Object[] {itemName, itemQuantity, itemCost, itemPrice, itemReorderPoint, itemReorderAmount, itemTemperature};
			
			inventoryTableModel.addRow(tableRow);
		}		
	}
	
	/**
	 * Action handler for all of the buttons.
	 * Calls different methods depending on button pressed
	 * Calls error message dialog on exception
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if(e.getSource() == loadProp) {
				
				String filePath = getFilePathWithDialog();
				if(filePath != null) {
					Store.openAndParseItemsList(filePath);
				}
				else {
					//No filepath
				}		
				
				updateTable();			
				
			} else if(e.getSource() == loadManif) {
				String filePath = getFilePathWithDialog();
				
				if(filePath != null) {					
					Store.openAndParseManifest(filePath);					
				}
				else {
					//No filepath
				}
				
				updateCapital();
				updateTable();		
				
			} else if(e.getSource() == loadSales) {
				String filePath = getFilePathWithDialog();
				System.out.println(filePath);
				if(filePath != null) {
					Store.openAndParseSalesLog(filePath);
					
				}
				else {
					//No file selected, do nothing
				}
				
				updateCapital();
				updateTable();	
				
			} else if (e.getSource() == exportManif) {
				
				String filePath = createFileWithDialog();
				Store.outputManifestToFile(filePath);
				
				updateTable();
			}
		} //try
		catch (Exception exception) {
			JOptionPane.showMessageDialog(this, exception.getMessage());
		}
	}
	
	public static void main(String[] args) {
		final Double STARTING_CAPITAL = 100000.0;
		final String STORE_NAME = "SuperMart";
		
		Store newStore = Store.getStoreInstance(STORE_NAME, STARTING_CAPITAL);
		
		StoreGUI newGUI = new StoreGUI(newStore);
		
		newGUI.updateCapital();		
	}
	
}
