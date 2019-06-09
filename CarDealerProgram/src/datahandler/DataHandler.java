package datahandler;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import cardealerpackage.Vehicle;

public class DataHandler {
	private static Path dirPath = Paths.get("C:/AutoStock");
	private static Path filePath = Paths.get("CarStock.csv");
	static Path dir = checkFiles(dirPath, filePath);
	
/*
 * The save file method will take input from the AddForm that is input by the user, and that is valid. It then adds the information to the .csv file.
 */
	public static void saveCSVFileData(String txtMake, String txtModel, String txtMileage, String txtEngineSize, 
			String txtPreviousOwners, String motStatus, String fileName){
//		Path dirPath = Paths.get("C:/AutoStore");
//		Path filePath = Paths.get(fileName);
//		Path dir, file;
		
		
		
//		File csvFile = new File(fileName);
		PrintWriter printWriter = null;
		StringBuilder sb = new StringBuilder();
		
		
		try {
//			if(Files.notExists(dirPath.resolve(filePath))) {
//				checkFiles(dirPath, filePath);
//				System.out.println("New file has been made : " + filePath);			
//				}//end if
			//Files.newBufferedWriter(dir)
			printWriter = new PrintWriter(Files.newBufferedWriter(dir, StandardOpenOption.APPEND));
			
			sb.append(txtMake + ",");
			sb.append(txtModel + ",");
			sb.append(txtMileage + ",");
			sb.append(txtEngineSize + ",");
			sb.append(motStatus + ",");
			sb.append(txtPreviousOwners + "\n");
			

			//printWriter = new PrintWriter(new FileOutputStream(fileName, true));
			
			printWriter.append(sb.toString());
		
//			buffWriter = new BufferedWriter(buffWriter);
//			buffWriter.write(sb.toString());
			System.out.println(sb.toString());
			//System.out.println("New vehicle added : " + txtMake + " " + txtModel);
		}//end try
		
		catch(IOException ioex) {
			JOptionPane.showMessageDialog(null, ioex);
		}//end catch
		
		catch(NullPointerException e) {
			JOptionPane.showMessageDialog(null, e);
		}//end catch
		finally {
			if(printWriter != null) {
				printWriter.flush();
				printWriter.close();
			}//end finally
		}//end catch	
	}//end saveData method
	
/*
 * A method that returns a path that gives the directory for the .csv file named "CarStock.csv". 
 */
	private static Path checkFiles(Path dirPath, Path filePath) {
		Path absPath = dirPath.resolve(filePath);
		
		try {
			if(Files.notExists(dirPath)) { 
				Files.createDirectories(dirPath);
				//System.out.println("Directory created : " + dirPath);
			}//end if
			
			if (Files.notExists(absPath)) {
				Files.createFile(absPath);
				//System.out.println("New file has been made @ path : " + absPath);
			}//end if
		}//end try
		catch(IOException e) {
			System.out.println(e);
			return null;
		}//end catch
		
		return absPath;
	}//end checkfiles method
/*
 * The readCSVFile method returns an ArrayList of vehicles that is read from the .csv file. 
 */
	public static ArrayList<Vehicle> readCSVFile(String fileName) throws IOException {
		ArrayList<Vehicle> vehicles = new ArrayList<>();
		Path pathToFile = checkFiles(dirPath, filePath);
		try (BufferedReader br = Files.newBufferedReader(pathToFile)){
			String line = br.readLine();
			while (line != null) {
				String[] attributes = line.split(",");
				
				Vehicle vehicle = createVehicle(attributes);
				
				vehicles.add(vehicle);
				line = br.readLine();
			}//end while
		}//end try
		catch(IOException e) {
			System.out.println(e);
		}//end catch
		return vehicles;
	}//end readCSVFile Method
/*
 * A return for the vehicle class to be created as an object
 */
	private static Vehicle createVehicle(String[] data) {
		
		String carMake = data[0];
		String carModel = data[1];
		int carMileage = Integer.parseInt(data[2]);
		double engineSize = Double.parseDouble(data[3]);
		String motStatus = data[4];
		int previousOwners = Integer.parseInt(data[5]);
		
		return new Vehicle(carMake, carModel, carMileage, engineSize, previousOwners, motStatus);
	}//end createVehicle method

/*
 * The delete file method uses the delete function of the Files class that deletes the file.
 */
	public static void deleteFile() {		
		try {
			Files.delete(dir);
		}//end try
		catch (IOException e) {
			e.printStackTrace();
		}//end catch IOException
	}//end deleteFile method
/*
 * The recreateFile method recreates the previous file to be updated after a vehicle has been "sold"
 */
	public static void reCreateFile(ArrayList<Vehicle> vehicles) {
		StringBuilder sb = new StringBuilder();
		PrintWriter printWriter;
		
		try {
			printWriter = new PrintWriter(Files.newBufferedWriter(dir));
			
			for (Vehicle vehicle : vehicles) {
				sb.append(vehicle.getCarMake() + ",");
				sb.append(vehicle.getCarModel() + ",");
				sb.append(vehicle.getCarMileage() + ",");
				sb.append(vehicle.getEngineSize() + ",");
				sb.append(vehicle.getMotStatus() + ",");
				sb.append(vehicle.getPreviousOwners() + "\n");
			}//end for loop
			
			printWriter.write(sb.toString());
			printWriter.flush();
			printWriter.close();
		}//end try
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}//end catch for FileException
		catch (IOException e) {
			e.printStackTrace();
		}//end catch for IOException
		catch (NullPointerException e) {
			e.printStackTrace();
		}//end catch NullPointerException
	}//end reCreateFile method
}//end class






