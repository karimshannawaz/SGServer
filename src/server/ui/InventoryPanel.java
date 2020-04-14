package server.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

import server.menu.Inventory;
import server.menu.MItem;
import server.menu.Menu;
import server.utils.JFrameUtils;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

//Asha

public class InventoryPanel extends JPanel {

	private static final long serialVersionUID = -7728688801223513408L;
	private JTextField ItextField;
	private JTextField QtextField;
	private JTable table;


	/**
	 * Create the panel.
	 */
	public InventoryPanel() {
		super();
		setBounds(233, 0, 962, 710);
		setLayout(null);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 24, 436, 476);
		add(scrollPane);
		
		//create table
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new String[] {
				"Ingredients", "Quantity"
			}, 0
		));
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		
		//add inventory list to rows
		for (Map.Entry<?,?> entry : Inventory.instance.entrySet()) {
	        model.addRow(new Object[] { entry.getKey(), entry.getValue() });
	    }
		scrollPane.setViewportView(table);
		
		
		
		JLabel lblNewLabel = new JLabel("Ingredient");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(535, 215, 76, 30);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Quantity");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(535, 256, 70, 30);
		add(lblNewLabel_1);
		
		JButton updateItemBtn = new JButton("Update Inventory");
		updateItemBtn.setFont(new Font("Tahoma", Font.PLAIN, 19));
		updateItemBtn.setBounds(535, 334, 200, 44);
		add(updateItemBtn);
		
		Object[] row = new Object[2];
		JButton addBtn = new JButton("Add Ingredient");
		addBtn.setFont(new Font("Tahoma", Font.PLAIN, 19));
		addBtn.setBounds(745, 334, 189, 44);
		add(addBtn);
		
		ItextField = new JTextField();
		ItextField.setBounds(631, 219, 262, 27);
		add(ItextField);
		ItextField.setColumns(10);
		
		QtextField = new JTextField();
		QtextField.setColumns(10);
		QtextField.setBounds(631, 260, 262, 27);
		add(QtextField);

		
		
	}
}
