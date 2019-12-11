package CSV;

import java.io.File;
import java.io.FileWriter;

import java.util.ArrayList;
import java.util.Scanner;

public class ParseCsv {
	
	/**
	 * Opens a csv file from the given filepath and returns the contents in an Arraylist
	 * @param filePath Absolute file path of file
	 * @return ArrayList containing Arraylist of strings representing rows of the input csv file
	 * @throws CSVFormatException if the input filepath does not have the .csv extension
	 */
	public static ArrayList<ArrayList<String>> openCsvFromPath(String filePath) throws CSVFormatException {
		File csvFile = new File(filePath);
		
		if (!filePath.substring(filePath.lastIndexOf(".") + 1).equals("csv")) {
			throw new CSVFormatException("File is not in .csv format");
		}
		
		ArrayList<ArrayList<String>> csvOutput = readCsvFile(csvFile);
		
		return csvOutput;
	}//openCsvFromPath
	
	
	
	/**
	 * Takes input file object and reads it line by line, converting it into ArrayList splitting based on defined delimiter
	 * @param csvFile input CSV file from openCsvFromPath
	 * @return Output data formatted into Arraylist
	 */
	public static ArrayList<ArrayList<String>> readCsvFile(File csvFile) {
		Scanner inputStream;
		ArrayList<ArrayList<String>> csvOutput = new ArrayList<ArrayList<String>>();
		
		final String DELIMITER = ",";
		
		try {
			 inputStream = new Scanner(csvFile);			
			
			while (inputStream.hasNextLine()) {
				String inputRow = inputStream.nextLine();
				String[] inputCells = inputRow.split(DELIMITER);
				
				ArrayList<String> csvRow = new ArrayList<String>();
				
				for (String s : inputCells) {
					csvRow.add(s);
				}
				
				csvOutput.add(csvRow);			
				
			}	
			
		} //try
		catch(Exception e) {			
			System.out.println(e.getMessage());			
		}//try catch
		
		return csvOutput;		
	}//readCsvFile
	
	
	
	
	/**
	 * Takes input Csv string and writes it to a file at the given filePath
	 * @param filePath Input filepath of the CSV file
	 * @param csvData Input data as a string
	 */
	public static void outputToCsvFile(String filePath, String csvData) {
		File csvFile = new File(filePath);
		try {
			FileWriter fileWriter = new FileWriter(csvFile);
			fileWriter.write(csvData);	
			fileWriter.close();
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}		
	}
	
}
