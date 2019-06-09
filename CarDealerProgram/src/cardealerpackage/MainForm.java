package cardealerpackage;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class MainForm implements ActionListener {
	
	JFrame mainFrame, addFrame, viewFrame;
	JButton addBtn, viewBtn, sellBtn;

	/*
	 * Constructor for the MainForm class
	 */
	public MainForm(JFrame mainFrame, JFrame addFrame, JFrame viewFrame) {
		this.mainFrame = mainFrame;
		this.addFrame = addFrame;
		this.viewFrame = viewFrame;
		createPanel();
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}//end constructor
/*
 * The createPanel method creates the UI for the main form
 */
	private void createPanel() {
		//ints 
		final int sizeX = 400;
		final int sizeY = 200;
		
		BorderLayout bLayout = new BorderLayout();
		FlowLayout flow = new FlowLayout();
		
		JPanel panel1 = new JPanel(bLayout);
		JPanel panel2 = new JPanel(flow);
		
		ImageIcon img = new ImageIcon(this.getClass().getResource("/logo.png"));
		JLabel imgLabel = new JLabel(img);
		imgLabel.setBounds(10,10, img.getIconWidth(), img.getIconHeight());
		
		addBtn = new JButton("Add Vehicle");
		viewBtn = new JButton("View Stock");
		sellBtn = new JButton("Sell Vehicle");
		
		panel1.add(imgLabel);
		
		panel2.add(addBtn);
		panel2.add(viewBtn);
		
		mainFrame.setLayout(new BorderLayout());
		mainFrame.setResizable(true);
		mainFrame.add(panel1, BorderLayout.CENTER);
		mainFrame.add(panel2,BorderLayout.PAGE_END);
		mainFrame.setSize(sizeX,sizeY);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
		
		addBtn.addActionListener(this);
		viewBtn.addActionListener(this);
	}//end createPanel method

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == addBtn) {
			mainFrame.setVisible(false);
			addFrame.setVisible(true);
		}//end if
		
		if(e.getSource() == viewBtn) {
			viewFrame.setVisible(true);
			mainFrame.setVisible(false);
		}//end if
	}//end actionPerformed
}//end class
