package cardealerpackage;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import datahandler.DataHandler;

public class ViewForm implements ActionListener {
	
	JFrame viewFrame, mainFrame;
	String fileName = "CarStock.csv";
	String introText = "Click read to view the contents of : " + fileName + "/n";
	JTable table;
	DefaultTableModel model = new DefaultTableModel();
	JButton sellBtn, closeBtn;
	JScrollPane scroll;
	ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
	
/*
 * Constructor for the ViewForm 
 */
	public ViewForm(JFrame viewFrame, JFrame mainFrame) {
		this.viewFrame = viewFrame;
		this.mainFrame = mainFrame;
		
		viewFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createPanel();
	}//end ViewForm constructor
	
/*
 * The create panel method is called when the viewForm is made. This creates and places all buttons, panels, table etc in the Frame.
 */
	private void createPanel() {
		BorderLayout borderLayout = new BorderLayout();
		JPanel upperP = new JPanel(borderLayout);
		FlowLayout flowLayout = new FlowLayout();
		JPanel lowerP = new JPanel(flowLayout);
		String tableHeaders[] = {"Make", "Model", "Mileage", "Engine Size", "MOT", "Previous Owners"};
		
		table = new JTable();
		table.setPreferredScrollableViewportSize(new Dimension(500,700));
		table.setFillsViewportHeight(true);
		upperP.add(table);		
		model.setColumnIdentifiers(tableHeaders);
		table.setModel(model);
		table.setVisible(true);
		
		JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		upperP.add(scrollPane);

		sellBtn = new JButton("Sell Car");
		sellBtn.addActionListener(this);
		lowerP.add(sellBtn);
		
		closeBtn = new JButton("Close");
		closeBtn.addActionListener(this);
		lowerP.add(closeBtn);
		
		viewFrame.setResizable(true);
		viewFrame.add(upperP);
		viewFrame.add(lowerP, BorderLayout.PAGE_END);
		viewFrame.setSize(600, 300);
		viewFrame.setLocationRelativeTo(null);
		viewFrame.setVisible(false);
		
		try {
			vehicles = DataHandler.readCSVFile(fileName);
		}//end try
		catch (IOException e) {
			e.printStackTrace();
		}//end catch
		populateTable(vehicles, model);	
	}//end createPanel Method
	
/*
 * The sellVehicle method shows a JOptionPane to select a vehicle to "sell". This removes it from the .csv file and the table (after a program restart)
 */
	public static ArrayList<Vehicle> sellVehicle(String fileName) throws ArrayIndexOutOfBoundsException, IOException {
//		File csvFile = new File(fileName);
		ArrayList<Vehicle> vehicles = DataHandler.readCSVFile(fileName);
		Object[] data;
		
		data = vehicles.toArray();
		
		Object control = JOptionPane.showInputDialog(null, "Which vehicle would you like to sell?", "Sell Vehicle", JOptionPane.QUESTION_MESSAGE, null, data, data[0]);
		vehicles.remove(control);

		DataHandler.deleteFile();				
		return vehicles;
	}//end sellVehicle method

/*
 * The populateTable method populates the JTable on the ViewForm this takes the objects in the Vehicle Array List and prints them per row of the table
 */
	public static void populateTable(ArrayList<Vehicle> vehicles, DefaultTableModel model) {
		Object data[] = new Object[6];

//		for (int i = 0; i < model.getRowCount(); i++) {
//			model.removeRow(i);
//		}//end for
		
		for (int i = 0; i < vehicles.size(); i++) {
			data[0] = vehicles.get(i).getCarMake();
			data[1] = vehicles.get(i).getCarModel();
			data[2] = vehicles.get(i).getCarMileage();
			data[3] = vehicles.get(i).getEngineSize();
			data[4] = vehicles.get(i).getMotStatus();
			data[5] = vehicles.get(i).getPreviousOwners();
			model.addRow(data);
		}//end for loop
	}//end populateTable Method
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String fileName = "CarStock.csv";
		if (e.getSource() == closeBtn) {
			mainFrame.setVisible(true);
			viewFrame.setVisible(false);
			//populateTable(vehicles, model);
		}//end closeBtn if
		
		if (e.getSource() == sellBtn) {
			try {
				DataHandler.reCreateFile(sellVehicle(fileName));
				//populateTable(vehicles, model);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			catch (ArrayIndexOutOfBoundsException e1) {
				JOptionPane.showMessageDialog(viewFrame, "There are no vehicles remaining", "Warning", JOptionPane.WARNING_MESSAGE);
			}//end catch array out of bounds
		}//
	}//end ActionPerformed
}//end class
