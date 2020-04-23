package server.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import server.Global;
import server.menu.MItem;
import server.menu.Order;
import server.menu.OrderQueue;
import server.user.User;
import server.utils.JFrameUtils;

//Floreta Krasniqi

/*
 * 
 * Page for manager to compensate meals
 * 
 * Karimshan
 * 
 */

public class CompensatePanel extends JPanel {
	
	private static final long serialVersionUID = -7997986343727651881L;
	
	private JTextField textField;
	private JTable table;
	public JTextArea textArea;
	public DefaultTableModel model;
	
	public double total = 0.0;
	public int tableID = -1;
	public int qty = 0;
	public List<MItem> temp = new ArrayList<MItem>();
	public List<MItem> compensatingItems = new ArrayList<MItem>();

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
		startPanel.setBounds(17, 17, 252, 676);
		startPanel.setBackground(UIManager.getColor("Button.background"));
		add(startPanel);
		startPanel.setLayout(null);
		
		//table number prompt
		JLabel lblNewLabel = new JLabel("TABLE:");
		lblNewLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblNewLabel.setBounds(22, 261, 85, 47);
		startPanel.add(lblNewLabel);
		
		//text field for table number
		textField = new JTextField();
		textField.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		textField.setBounds(119, 264, 75, 40);
		startPanel.add(textField);
		textField.setColumns(10);
		
		//continue button--clicking should populate the table with a list of the customer's ordered items
		JButton continueBtn = new JButton("Continue");
		continueBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				populateTable();
			}
		});
		continueBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		continueBtn.setBounds(119, 336, 114, 69);
		startPanel.add(continueBtn);
		
		JButton resetBtn = new JButton("Reset");
		resetBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearTable();
			}
		});
		resetBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		resetBtn.setBounds(12, 336, 100, 69);
		startPanel.add(resetBtn);
		
		//panel for user to select item to compensate
		JPanel selectionPanel = new JPanel();
		selectionPanel.setBounds(268, 17, 677, 676);
		add(selectionPanel);
		selectionPanel.setLayout(null);
		
		//user instructions
		JLabel lblNewLabel_1 = new JLabel("SELECT AN ITEM TO COMPENSATE:");
		lblNewLabel_1.setBounds(89, 37, 501, 48);
		lblNewLabel_1.setFont(new Font("Dialog", Font.PLAIN, 27));
		selectionPanel.add(lblNewLabel_1);
		
		//remove from bill button---clicking should change the cost of the item selected to $0.00 on customer's bill
		JButton btnNewButton_1 = new JButton("<html>Add to<br>Remove Queue</html>");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addToRemoveQueue();
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_1.setBounds(92, 579, 154, 62);
		selectionPanel.add(btnNewButton_1);
		
		//scroll pane for order list
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 108, 343, 458);
		selectionPanel.add(scrollPane);
		
		//create table for order list
		table = new JTable();
		table.setModel(new DefaultTableModel(
		new String[] {
				"Item", "Price"
			}, 0
		));
		scrollPane.setViewportView(table);
		
		table.addMouseListener(new MouseListener() {
			@Override public void mouseClicked(MouseEvent arg0) { }
			@Override public void mouseEntered(MouseEvent arg0) { }
			@Override public void mouseExited(MouseEvent arg0) { }
			@Override public void mousePressed(MouseEvent arg0) { }

			@Override
			public void mouseReleased(MouseEvent arg0) {
				int row = table.getSelectedRow();
				MItem item = temp.get(row);
				if(item.qty > 1) {
					String op = (String) JFrameUtils.inputDialog("Choose Quantity:", 
						"This item's quantity is greater than 1, please choose the amount\n"
						+ "that you would like to compensate:");
					if(op == null)
						return;
					int quantityChosen;
					try {
						quantityChosen = Integer.parseInt(op);
					} catch(NumberFormatException e) {
						return;
					}
					if(quantityChosen > item.qty || quantityChosen < 1)
						return;
					qty = quantityChosen;
				}
				else {
					qty = 1;
				}
				System.out.println("qty is "+qty);
			}
		});
		
		table.setFont(new Font("Tahoma", Font.PLAIN, 20));
		table.setRowHeight(30);
		table.getColumnModel().getColumn(0).setPreferredWidth(290);
		
		table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 18));
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		table.setDefaultRenderer(String.class, centerRenderer);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(384, 108, 281, 458);
		selectionPanel.add(scrollPane_1);
		
		textArea = new JTextArea();
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setText("Compensating: ");
		scrollPane_1.setViewportView(textArea);
		
		JButton btnNewButton_1_1 = new JButton("<html>Send to<br>Customer</html>");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendDiscountToCustomer();
			}
		});
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_1_1.setBounds(436, 579, 154, 62);
		selectionPanel.add(btnNewButton_1_1);
		
		model = (DefaultTableModel) table.getModel();
		
		refreshTxtArea();
		
	}

	/**
	 * Adds the selected index to the remove queue.
	 */
	protected void addToRemoveQueue() {
		if(tableID < 0) {
			JFrameUtils.showMessage("Compensation", "Invalid compensation table.");
			refreshTxtArea();
			return;
		}
		int index = table.getSelectedRow();
		int oldQty = temp.get(index).qty;
		MItem newItem = new MItem();
		newItem.name = temp.get(index).name;
		newItem.price = temp.get(index).price;
		newItem.qty = qty;
		compensatingItems.add(newItem);
		if(qty == oldQty) {
			temp.remove(index);
			model.removeRow(index);
		}
		else {
			int newQty = (temp.get(index).qty) - qty;
			temp.get(index).qty = newQty;
			model.setValueAt("x"+temp.get(index).qty+" "+temp.get(index).name, index, 0);
			if(qty > newQty)
				qty = newQty;
		}
		refreshTxtArea();
	}

	/**
	 * Sends the discounted items to the customer's kiosk.
	 */
	protected void sendDiscountToCustomer() {
		if(tableID < 1 || total == 0) {
			JFrameUtils.showMessage("Compensation", "Invalid compensation or amount.");
			return;
		}
		boolean containsOrder = false;
		for(Order o : OrderQueue.unpaidOrders) {
			if(o.getTableID() == tableID) {
				containsOrder = true;
				break;
			}
		}
		if(!containsOrder) {
			tableID = -1;
			total = 0;
			temp.clear();
			compensatingItems.clear();
			model.setRowCount(0);
			refreshTxtArea();
			JFrameUtils.showMessage("Compensation", "Error: this table has already paid or no longer has an order.");
			return;
		}
		boolean choice = JFrameUtils.confirmDialog("Compensation", 
			"Are you sure you want to send table "+(tableID + 1)+" a "+(decimalF(total))+" compensation?"
			+ "\nThis action cannot be undone and you will not be able to send further compensations.");
		if(!choice) {
			return;
		}
		for(User u : Global.getUsers()) {
			if(u != null) {
				if(u.getTableID() == tableID) {
					u.getSession().sendClientPacket("manager_compensation", ""+total);
					break;
				}
			}
		}
		tableID = -1;
		total = 0;
		temp.clear();
		compensatingItems.clear();
		model.setRowCount(0);
		JFrameUtils.showMessage("Compensation", "Compensation sent successfully.");
		refreshTxtArea();
	}

	/**
	 * Clears the compensation table.
	 */
	protected void clearTable() {
		if(tableID < 0)
			return;
		boolean choice = JFrameUtils.confirmDialog("Compensation", 
			"Are you sure you want to reset your current progress for table "+(tableID + 1)+"?"
			+ "\nThis action cannot be undone.");
		if(!choice) {
			return;
		}
		tableID = -1;
		total = 0;
		temp.clear();
		compensatingItems.clear();
		model.setRowCount(0);
		refreshTxtArea();
	}

	/**
	 * Populates the table with the information given from the order.
	 */
	protected void populateTable() {
		tableID = -1;
		total = 0;
		temp.clear();
		compensatingItems.clear();
		model.setRowCount(0);
		refreshTxtArea();
		int num;
		try {
			num = Integer.parseInt(textField.getText());
		} catch(NumberFormatException e) {
			return;
		}
		if(num > 20 || num < 1) {
			return;
		}
		
		// Decrement by 1 to account for table IDs
		num--;
		
		Order order = null;
		boolean orderWithIDExists = false;
		for(Order o : OrderQueue.unpaidOrders) {
			if(o.getTableID() == num) {
				orderWithIDExists = true;
				order = o;
				break;
			}
		}
		
		if(!orderWithIDExists) {
			return;
		}
		
		tableID = num;
		
		for(MItem i : order.items) {
			temp.add(i);
			model.addRow(new Object[] { "x"+i.qty+" "+i.name, decimalF(i.price) });
		}
	}
	
	public String decimalF(double num) {
		return DecimalFormat.getCurrencyInstance().format(num);
	}
	
	/**
	 * Refreshes the compensation text area.
	 */
	public void refreshTxtArea() {
		StringBuilder s = new StringBuilder();
		s.append("Compensating Items:\n\n");

		total = 0;
		for(MItem i : compensatingItems) {
			s.append("x"+i.qty+" "+i.name+" - "+
				(decimalF(i.price * i.qty))+"\n");
			total += (i.price * i.qty);
		}
		s.append("\n\n\nTotal: "+decimalF(total));
		textArea.setText(s.toString());
	}
	
}
