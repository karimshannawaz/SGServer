package server.ui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.Map;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import server.menu.Inventory;

import javax.swing.JScrollPane;
import javax.swing.JTable;

//Floreta Krasniqi

public class CompensatePanel extends JPanel {
	
	private static final long serialVersionUID = -7997986343727651881L;
	
	private JTextField textField;
	private JTable table;
	private JTable table_1;
	private JTable orderTable;

	/**
	 * Create the panel.
	 */
	public CompensatePanel() {
		super();
		setBackground(UIManager.getColor("Button.highlight"));
		setBounds(233, 0, 962, 710);
		setLayout(null);
		
		JPanel startPanel = new JPanel();
		startPanel.setBounds(6, 6, 310, 698);
		startPanel.setBackground(UIManager.getColor("Button.background"));
		add(startPanel);
		startPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("TABLE NUMBER:");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblNewLabel.setBounds(41, 262, 117, 47);
		startPanel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		textField.setBounds(177, 264, 91, 40);
		startPanel.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Continue");
		btnNewButton.setBounds(97, 330, 117, 29);
		startPanel.add(btnNewButton);
		
		JPanel selectionPanel = new JPanel();
		selectionPanel.setBounds(328, 6, 628, 698);
		add(selectionPanel);
		selectionPanel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("SELECT AN ITEM TO COMPENSATE:");
		lblNewLabel_1.setBounds(60, 37, 501, 48);
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		selectionPanel.add(lblNewLabel_1);
		
		JButton btnNewButton_1 = new JButton("REMOVE FROM BILL");
		btnNewButton_1.setBounds(215, 610, 196, 29);
		selectionPanel.add(btnNewButton_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(60, 108, 501, 458);
		selectionPanel.add(scrollPane);
		
		/*//create table
		orderTable = new JTable();
		orderTable.setModel(new DefaultTableModel(
		new String[] {
				"Item", "Price"
			}, 0
		));
		
		// Sets the table header and row font, as well as adjusts the row height.
		table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 20));
		table.setFont(new Font("Tahoma", Font.PLAIN, 20));
		table.setRowHeight(40);
		
		scrollPane.setViewportView(orderTable);
		*/
	}
}
