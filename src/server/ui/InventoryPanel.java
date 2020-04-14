package server.ui;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.Highlighter;

import server.menu.Inventory;

//Asha

public class InventoryPanel extends JPanel {

	private static final long serialVersionUID = -7728688801223513408L;
	
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;


	/**
	 * Create the panel.
	 */
	public InventoryPanel() {
		super();
		setBounds(233, 0, 962, 710);
		setLayout(null);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 24, 494, 659);
		add(scrollPane);
		
		//create table
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new String[] {
				"Ingredient", "Quantity"
			}, 0
		));
		
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		
		// add inventory list to rows
		for (Map.Entry<?,?> entry : Inventory.instance.entrySet()) {
	        model.addRow(new Object[] { entry.getKey(), entry.getValue() });
	    }
		
		// Sets the table header and row font, as well as adjusts the row height.
		table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 20));
		table.setFont(new Font("Tahoma", Font.PLAIN, 20));
		table.setRowHeight(30);
		
		
		scrollPane.setViewportView(table);
		
		table.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				System.out.println("Current index is: "+table.getSelectedRow());
			}

			
		});
		
		JLabel lblNewLabel = new JLabel("Ingredient:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(535, 215, 76, 30);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Quantity:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(535, 256, 70, 30);
		add(lblNewLabel_1);
		
		JButton updateItemBtn = new JButton("Update Inventory");
		updateItemBtn.setFont(new Font("Tahoma", Font.PLAIN, 19));
		updateItemBtn.setBounds(535, 334, 200, 44);
		add(updateItemBtn);
		
		JButton btnNewButton_1 = new JButton("Add Ingredient");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnNewButton_1.setBounds(745, 334, 189, 44);
		add(btnNewButton_1);
		
		textField = new JTextField();
		textField.setBounds(631, 219, 262, 27);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(631, 260, 262, 27);
		add(textField_1);

		
	}
}
