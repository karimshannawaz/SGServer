package server.ui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import javax.swing.JScrollPane;
import javax.swing.JTable;

//Floreta Krasniqi

/*
 * 
 * Page for manager to compensate meals
 * 
 */

public class CompensatePanel extends JPanel {
	
	private static final long serialVersionUID = -7997986343727651881L;
	
	private JTextField textField;
	private JTable orderTable;

	/**
	 * Create the panel.
	 */
	public CompensatePanel() {
		super();
		setBackground(UIManager.getColor("Button.highlight"));
		setBounds(233, 0, 962, 710);
		setLayout(null);
		
		//left panel for table number entry
		JPanel startPanel = new JPanel();
		startPanel.setBounds(17, 17, 299, 676);
		startPanel.setBackground(UIManager.getColor("Button.background"));
		add(startPanel);
		startPanel.setLayout(null);
		
		//table number prompt
		JLabel lblNewLabel = new JLabel("TABLE NUMBER:");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblNewLabel.setBounds(41, 262, 117, 47);
		startPanel.add(lblNewLabel);
		
		//text field for table number
		textField = new JTextField();
		textField.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		textField.setBounds(177, 264, 91, 40);
		startPanel.add(textField);
		textField.setColumns(10);
		
		//continue button--clicking should populate the table with a list of the customer's ordered items
		JButton btnNewButton = new JButton("Continue");
		btnNewButton.setBounds(97, 330, 117, 29);
		startPanel.add(btnNewButton);
		
		//panel for user to select item to compensate
		JPanel selectionPanel = new JPanel();
		selectionPanel.setBounds(328, 17, 617, 676);
		add(selectionPanel);
		selectionPanel.setLayout(null);
		
		//user instructions
		JLabel lblNewLabel_1 = new JLabel("SELECT AN ITEM TO COMPENSATE:");
		lblNewLabel_1.setBounds(60, 37, 501, 48);
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		selectionPanel.add(lblNewLabel_1);
		
		//remove from bill button---clicking should change the cost of the item selected to $0.00 on customer's bill
		JButton btnNewButton_1 = new JButton("REMOVE FROM BILL");
		btnNewButton_1.setBounds(215, 610, 196, 29);
		selectionPanel.add(btnNewButton_1);
		
		//scroll pane for order list
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(60, 108, 501, 458);
		selectionPanel.add(scrollPane);
		
		//create table for order list
		orderTable = new JTable();
		orderTable.setModel(new DefaultTableModel(
		new String[] {
				"Item", "Price"
			}, 0
		));
		scrollPane.setViewportView(orderTable);
		
	}
}
