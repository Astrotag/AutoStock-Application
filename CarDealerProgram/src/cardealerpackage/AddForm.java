package cardealerpackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import datahandler.DataHandler;

public class AddForm implements ActionListener, KeyListener {
	
	JLabel lblMake, lblModel, lblMileage, lblEngineSize, lblMotStatus, lblPreviousOwners;
	JFrame mainFrame, addFrame;
	JButton saveBtn, clearBtn, backBtn;
	JTextField txtMake, txtModel, txtMileage, txtEngineSize, txtPreviousOwners;
	JRadioButton motYes, motNo;
	String fileName = "CarStock.csv";
	
	public AddForm(JFrame addFrame, JFrame mainFrame) {
		this.mainFrame = mainFrame;
		this.addFrame = addFrame;
		
		addFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createPanel();
	}
	
	/*
	 * The method to create the addForm panel which holds the text fields, labels and buttons associated with it to create a new vehicle to be added to the CSV file. 
	 */
	private void createPanel() {
		
		final int sizeX = 600;
		final int sizeY = 300;
		
		//create layouts
		GridLayout gLout = new GridLayout(7,4);
		FlowLayout flow = new FlowLayout();
		//create panels and assign their layouts
		JPanel panel1 = new JPanel(gLout);
		JPanel panel2 = new JPanel(flow);
		
		//create labels and give them their text
		lblMake = new JLabel("Make : ");
		lblModel = new JLabel("Model : ");
		lblMileage = new JLabel("Mileage : ");
		lblEngineSize = new JLabel("Engine Size : ");
		lblMotStatus = new JLabel("MOT Status : ");
		lblPreviousOwners = new JLabel("Previous Owners : ");
		
		//create and label buttons
		saveBtn = new JButton("Save");
		clearBtn = new JButton("Clear");
		backBtn = new JButton("Back");
		
		//create textfields
		txtMake = new JTextField(40);
		txtModel = new JTextField(40);
		txtMileage = new JTextField(40);
		txtEngineSize = new JTextField(40);
		txtPreviousOwners = new JTextField(40);
		
		//Allows enter to be press to try and save the data that is currently stored in the field in focus
		txtMake.setFocusable(true);
		txtMake.addKeyListener(this);
		txtModel.addKeyListener(this);
		txtMileage.addKeyListener(this);
		txtEngineSize.addKeyListener(this);
		txtPreviousOwners.addKeyListener(this);

		//Create and group radio buttons to be used for MOT status
		motYes = new JRadioButton("MOT");
		motYes.setSelected(true);
		motYes.addActionListener(this);
		motYes.addKeyListener(this);
		motNo = new JRadioButton("No MOT");
		motNo.addActionListener(this);
		motNo.addKeyListener(this);
		ButtonGroup group = new ButtonGroup();
		group.add(motYes);
		group.add(motNo);
		JPanel radioPanel = new JPanel(new GridLayout(1,2));
		radioPanel.add(motYes);
		radioPanel.add(motNo);
		
		//adding labels and text fields to the panel
		panel1.add(lblMake);
		panel1.add(txtMake);
		panel1.add(lblModel);
		panel1.add(txtModel);
		panel1.add(lblMileage);
		panel1.add(txtMileage);
		panel1.add(lblEngineSize);
		panel1.add(txtEngineSize);
		panel1.add(lblPreviousOwners);
		panel1.add(txtPreviousOwners);
		panel1.add(lblMotStatus);
		panel1.add(radioPanel);
//		panel1.addKeyListener(this);
		
		//adding the buttons to the panel
		panel2.add(backBtn);
		panel2.add(clearBtn);
		panel2.add(saveBtn);
		
		addFrame.setLayout(new BorderLayout());
		addFrame.setResizable(false);
		addFrame.add(panel1);
		addFrame.add(panel2, BorderLayout.PAGE_END);
		addFrame.setSize(sizeX, sizeY);
		addFrame.setLocationRelativeTo(null);
		addFrame.setVisible(false);
		
		clearBtn.addActionListener(this);
		saveBtn.addActionListener(this);
		backBtn.addActionListener(this);
		
		lblMake.setForeground(Color.black);
		lblModel.setForeground(Color.black);
		lblMileage.setForeground(Color.black);
		lblEngineSize.setForeground(Color.black);
		lblPreviousOwners.setForeground(Color.black);
		lblMotStatus.setForeground(Color.black);
	}//end createPanel
	
	/*
	 * This method is used to perform actions with the buttons on the form.
	 */
	public void actionPerformed(ActionEvent e) {
		String motStatus = null;
		
		if (motYes.isSelected()) {
			motStatus = "MOT";	
		}
		
		else if (motNo.isSelected()) {
			motStatus = "No MOT";
		}
		
		if (e.getSource() == backBtn) {
			setTextColor();
			mainFrame.setVisible(true);
			addFrame.setVisible(false);
		}
		
		else if (e.getSource() == clearBtn) {
			setTextColor();
			clear();
		}
		
		if (e.getSource() == saveBtn) {
			if (validate()) {
				DataHandler.saveCSVFileData(txtMake.getText(), 
						txtModel.getText(), txtMileage.getText(), txtEngineSize.getText(), txtPreviousOwners.getText(), motStatus, fileName);
			}//end if inner
		}//end if outer
	}//end actionPerformed

	/*
	 * A method called when the clear button is pressed. This resets all fields to empty and the MOT radio button.
	 */
	private void clear() {
		txtMake.setText("");
		txtModel.setText("");
		txtMileage.setText("");
		txtEngineSize.setText("");
		txtPreviousOwners.setText("");
		motYes.setSelected(true);
	}//

	/*
	 * A method that when called will set the labels for the text fields to black. This would be used if the text failed validation before and the labels have been turned red
	 */
	private void setTextColor() {
		lblMake.setForeground(Color.black);
		lblModel.setForeground(Color.black);
		lblMileage.setForeground(Color.black);
		lblEngineSize.setForeground(Color.black);
		lblPreviousOwners.setForeground(Color.black);
		lblMotStatus.setForeground(Color.black);
	}//end setColor Method
	
	/*
	 * A method that validates the input details by the users to make sure they make the specified arrangements. This will check that the mileage entered is an integer value for example. Should 
	 * a field fail it will turn the labels of the failed fields red. 
	 */
	public boolean validate() {
		boolean bool = true;
		int checkMileage = 0;
		double checkEngine = 0.0;
		int checkPreviousOwners = 0;

		
		if(txtMake.getText().equals("")) {
			lblMake.setForeground(Color.red);
			bool = false;
		}//end if txtMake
		
		if (txtModel.getText().equals("")) {
			lblModel.setForeground(Color.red);
			bool = false;
		}//end if txtModel
		
		try {
			checkMileage = Integer.parseInt(txtMileage.getText());
		}
		catch (NumberFormatException e){
			lblMileage.setForeground(Color.red);
			JOptionPane.showMessageDialog(addFrame, "Mileage should be a number", "Warning", JOptionPane.WARNING_MESSAGE);
			bool = false;
		}//end catch Mileage
		
		if (txtMileage.getText().equals("") || checkMileage <= 0) {
			lblMileage.setForeground(Color.red);
			bool = false;
		}//end if txtMileage
		
		try {
			checkEngine = Double.parseDouble(txtEngineSize.getText());
		}
		catch (NumberFormatException e){
			lblEngineSize.setForeground(Color.red);
			JOptionPane.showMessageDialog(addFrame, "Engine size should be a decimal number", "Warning", JOptionPane.WARNING_MESSAGE);
			bool = false;
		}//end catch EngineSize
		
		if (txtEngineSize.getText().equals("") || checkEngine <= 0.3) {
			lblEngineSize.setForeground(Color.red);
			bool = false;
		}//end if txtEngineSize
		
//		if (txtEngineSize.toString().length() > 2) {
//			lblEngineSize.setForeground(Color.red);
//			JOptionPane.showMessageDialog(addFrame, "Engine size should be only be given to one decimal place", "Warning", JOptionPane.WARNING_MESSAGE);
//			bool = false;
//		}//end if
		
		try {
			checkPreviousOwners = Integer.parseInt(txtPreviousOwners.getText());
		}//end try checkPreviousOwners
		catch (NumberFormatException e){
			lblPreviousOwners.setForeground(Color.red);
			JOptionPane.showMessageDialog(addFrame, "Previous owners must be 0 or above", "Warning", JOptionPane.WARNING_MESSAGE);
			bool = false;	 
		}//end catch PreviousOwners
		
		if (txtPreviousOwners.getText().equals("") || checkPreviousOwners < 0) {
			lblPreviousOwners.setForeground(Color.red);
			bool = false;
		}//end if txtPreviousOwners
		
		else {
			setTextColor();
			return bool;
		}//end else
		
		return bool;
	}//end validate method

	@Override
	public void keyPressed(KeyEvent e) {
		String motStatus = null;
		
		if (motYes.isSelected()) {
			motStatus = "MOT";	
		}//end if
		
		else if (motNo.isSelected()) {
			motStatus = "No MOT";
		}//end else if
		
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (validate()) {
				DataHandler.saveCSVFileData(txtMake.getText(), 
						txtModel.getText(), txtMileage.getText(), txtEngineSize.getText(), txtPreviousOwners.getText(), motStatus, fileName);
			}//end if inner
		}//end keyEvent if
	}//end keyPressed

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}//end AddForm class
