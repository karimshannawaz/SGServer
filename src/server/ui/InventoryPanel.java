package server.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import server.menu.Inventory;
import server.utils.JFrameUtils;

/**
 * 
 * @author Asha and Karimshan
 *
 */
public class InventoryPanel extends JPanel {

	private static final long serialVersionUID = -7728688801223513408L;

	private JTextField ItextField;
	private JTextField QtextField;

	private JTextField ing;
	private JTextField qty;

	private JTable table;
	private DefaultTableModel model;


	/**
	 * Create the panel.
	 */
	public InventoryPanel() {
		super();
		setBounds(233, 0, 962, 710);
		setLayout(null);

		// Create our scroll pane
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

		model = (DefaultTableModel) table.getModel();

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
				ing.setText(""+model.getValueAt(table.getSelectedRow(), 0));
				qty.setText(""+model.getValueAt(table.getSelectedRow(), 1));
			}


		});

		JLabel lblNewLabel = new JLabel("Ingredient:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(535, 215, 79, 30);
		add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Quantity:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(535, 256, 70, 30);
		add(lblNewLabel_1);

		JButton updateItemBtn = new JButton("Update Inventory");
		updateItemBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				updateInventory();
			}
		});
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




		JButton btnNewButton_1 = new JButton("Add Ingredient");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addIngredient();
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnNewButton_1.setBounds(745, 334, 189, 44);
		add(btnNewButton_1);

		ing = new JTextField();
		ing.setFont(new Font("Tahoma", Font.PLAIN, 16));
		ing.setBounds(631, 219, 262, 27);
		add(ing);
		ing.setColumns(10);

		qty = new JTextField();
		qty.setFont(new Font("Tahoma", Font.PLAIN, 16));
		qty.setColumns(10);
		qty.setBounds(631, 260, 262, 27);
		add(qty);

		// Sets current selected index to 0
		// and populates ingredient and quantity.
		table.setRowSelectionInterval(0, 0);
		ing.setText(""+model.getValueAt(0, 0));
		qty.setText(""+model.getValueAt(0, 1));

	}


	protected void addIngredient() {
		if(ing.getText().equals("") || ing.getText().equals(null) || ing.getText().contains(" ")) {
			JFrameUtils.showMessage("Inventory Editor", "Error: Invalid ingredient entered. Please try again.");
			return;
		}
		if(Inventory.instance.containsKey(ing.getText())) {
			JFrameUtils.showMessage("Inventory Editor", "Error: This ingredient already exists.");
			return;
		}
		int qtyAsInt;
		try {
			qtyAsInt = Integer.parseInt(qty.getText());
		} catch(NumberFormatException e) {
			JFrameUtils.showMessage("Inventory Editor", "Error: Invalid quantity entered. Please try again.");
			return;
		}
		if(qty.getText().equals("") || qty.getText().equals(null) || qty.getText().contains(" ")
				|| qtyAsInt < 0 || qtyAsInt > 5000000) {
			JFrameUtils.showMessage("Inventory Editor", "Error: Invalid quantity entered. Please try again.");
			return;
		}
		
		Inventory.instance.put(ing.getText(), qtyAsInt);
		// adds new ingredient to list.
		model.addRow(new Object[] { ing.getText(), qtyAsInt });
		// Sets current selected index to newest one
		// and populates ingredient and quantity.
		ing.setText(""+model.getValueAt(table.getRowCount() - 1, 0));
		qty.setText(""+model.getValueAt(table.getRowCount() - 1, 1));
		JFrameUtils.showMessage("Inventory Editor", "Successfully added ingredient "+ing.getText()+" with qty: "+qtyAsInt);
	}


	protected void updateInventory() {
		if(ing.getText().equals("") || ing.getText().equals(null) || ing.getText().contains(" ")) {
			JFrameUtils.showMessage("Inventory Editor", "Error: Invalid ingredient entered. Please try again.");
			return;
		}
		if(!Inventory.instance.containsKey(ing.getText())) {
			JFrameUtils.showMessage("Inventory Editor", "Error: This ingredient does not exist in the table.");
			return;
		}
		int qtyAsInt;
		try {
			qtyAsInt = Integer.parseInt(qty.getText());
		} catch(NumberFormatException e) {
			JFrameUtils.showMessage("Inventory Editor", "Error: Invalid quantity entered. Please try again.");
			return;
		}
		if(qty.getText().equals("") || qty.getText().equals(null) || qty.getText().contains(" ")
				|| qtyAsInt < 0 || qtyAsInt > 5000000) {
			JFrameUtils.showMessage("Inventory Editor", "Error: Invalid quantity entered. Please try again.");
			return;
		}
		
		int index = 0;
		for(String name : Inventory.instance.keySet()) {
			if(name.equalsIgnoreCase(ing.getText()))
				break;
			index++;
		}
		
		// Changes the quantity in the table
		model.setValueAt(qtyAsInt, index, 1);
		Inventory.instance.put(ing.getText(), qtyAsInt);
		// Sets current selected index to newest one
		// and populates ingredient and quantity.
		ing.setText(""+Inventory.instance.keySet().toArray()[index]);
		qty.setText(""+Inventory.instance.values().toArray()[index]);
		JFrameUtils.showMessage("Inventory Editor", "Successfully UPDATED ingredient "+ing.getText()+" with qty: "+qtyAsInt);
	}


}
