
/*
 * Greig Myles
 * Car Dealer Stock Program
 * ------------------------
 * 
 * This program will take in details about various vehicles and store them to a CSV file. It will also show the vehicles currently "in stock" in a table format. A vehicle can be added and remove from the program
 * to simulate a dealer selling a vehicle. 
 * 
 */

package cardealerpackage;
import javax.swing.JFrame;
import cardealerpackage.AddForm;
import cardealerpackage.ViewForm;
import cardealerpackage.MainForm;

public class DealerDriver {

	public static void main(String[] args) {
		
		//Setting up the frames for the application as well as the default layout. Also called the Main, Add and View form classes to be created.
		JFrame.setDefaultLookAndFeelDecorated(true);
		JFrame mainFrame = new JFrame("AutoStock");
		JFrame addFrame = new JFrame("Add a car to stock");
		JFrame viewFrame = new JFrame("View cars in stock");
		MainForm mainGUI = new MainForm(mainFrame,addFrame,viewFrame);
		AddForm addGUI = new AddForm(addFrame, mainFrame);
		ViewForm viewGUI = new ViewForm(viewFrame, mainFrame);
	}//end main
}//end class
