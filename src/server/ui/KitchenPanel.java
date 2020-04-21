
package server.ui;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import server.Server;
import server.menu.MItem;
import server.menu.Order;
import server.menu.OrderQueue;
import server.utils.JFrameUtils;

/**
 * Holds the information for the kitchen start page
 * SERVER
 * 
 * @author Karimshan
 *
 */
public class KitchenPanel extends JPanel {

	private static final long serialVersionUID = -8112480994553957L;

	public JTable table;

	public KitchenPanel() {
		super();
		setBounds(233, 0, 962, 710);
		setLayout(null);

		// Create our scroll pane
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 962, 710);
		add(scrollPane);

		table = new JTable();
		table.setRowSelectionAllowed(false);
		table.setFillsViewportHeight(true);
		table.setFont(new Font("Tahoma", Font.PLAIN, 20));
		table.setRowHeight(40);
		
		scrollPane.setViewportView(table);
		
		/**
		 * Sets the model for this table.
		 */
		table.setModel(new DefaultTableModel(
				new Object[][] {

				},
				new String[] {
					"Table", "Order", "Mark Complete"
				}
				) {
			
			private static final long serialVersionUID = 1378760572757475870L;

			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
					Integer.class, String.class, Boolean.class
			};

			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});

		// Sets the width of the columns.
		table.getColumnModel().getColumn(0).setPreferredWidth(30);
		table.getColumnModel().getColumn(1).setPreferredWidth(400);
		table.getColumnModel().getColumn(2).setPreferredWidth(30);

		// Centers the text in the columns.
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		table.setDefaultRenderer(String.class, centerRenderer);
		table.setDefaultRenderer(Integer.class, centerRenderer);
		
		table.addMouseListener(new MouseListener() {
			@Override public void mouseClicked(MouseEvent arg0) { }
			@Override public void mouseEntered(MouseEvent arg0) { }
			@Override public void mouseExited(MouseEvent arg0) { }
			@Override public void mousePressed(MouseEvent arg0) { }

			@Override
			public void mouseReleased(MouseEvent arg0) {
				int row = table.getSelectedRow();
				int column = table.getSelectedColumn();
				if(column == 1) {
					int tableID = (int) table.getModel().getValueAt(row, 0) - 1;
					viewOrderDetails(tableID, row);
				}
			}
		});

		// Adds a listener to see when the table has been changed (Clicked)
		table.getModel().addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				// TODO Auto-generated method stub
				int row = e.getFirstRow();
				int column = e.getColumn();
				if (column == 2) {
					DefaultTableModel tab = (DefaultTableModel) table.getModel();
					//send information to the waiter that table order is done with table number
					boolean checked = (boolean) tab.getValueAt(row, 2);
					int tableNum = ((int) tab.getValueAt(row, 0)) - 1;
					if(checked) {
						boolean choice = JFrameUtils.confirmDialog("Order Completion Confirmation", 
							"Are you sure you want to mark this order for table "+(tableNum + 1)+" as fulfilled?"
									+ "\nThis action cannot be undone and the wait staff will be notified.");
						if(!choice) {
							tab.setValueAt(Boolean.FALSE, row, column);
							return;
						}
						tab.setValueAt(Boolean.FALSE, row, column);
						Order.kitchenRequestWaitStaff(tableNum);
					}
				}

			}
		});
	}
	
	/**
	 * Displays the order details for this order.
	 * 
	 * @param row
	 */
	public void viewOrderDetails(int tableID, int row) {
		JFrame frame = new JFrame();
		frame.setBounds(Server.ui.getBounds());
		frame.setBounds(frame.getX() + 260, frame.getY() + 40, 0, 0);
		frame.setSize(500, 560);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setTitle("Order details for table: "+(tableID + 1));
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 500, 560);
		panel.setLayout(null);
		frame.add(panel);
		
		// Create our scroll pane
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 500, 560);
		panel.add(scrollPane);
		
		JTextArea orderSummary = new JTextArea();
		orderSummary.setFont(new Font("Tahoma", Font.PLAIN, 14));
		orderSummary.setEditable(false);
		orderSummary.setLineWrap(true);
		orderSummary.setText(getOrderToString(row));
		scrollPane.setViewportView(orderSummary);
	}

	/**
	 * Adds a new order to the table.
	 * @param tableID 
	 */
	public void addToTable(int tableID) {
		((DefaultTableModel) table.getModel()).addRow(
			new Object[] {
				new Integer((tableID + 1)),
				new String("Tap to view order details"),
				new Boolean(false)
			}
		);
	}

	public String getOrderToString(int tableIndex) {
		StringBuilder s = new StringBuilder();
		//s.append("Order:\n\n");

		for(MItem i : OrderQueue.unfulfilledOrders.get(tableIndex).items) {
			s.append("x"+i.qty+" "+i.name+"\n");

			// Order Menu Item
			String[] newIngTok = i.ingredients.split(",");

			for(int index = 0; index < newIngTok.length; index++) {
				String[] newIng = newIngTok[index].split(":");
				// ingredients
				s.append("    - x"+newIng[1]+" "+newIng[0]+"\n");
			}
			if(!(i.specialReqs.equalsIgnoreCase("none")) 
					&& !i.specialReqs.equals("") && !i.specialReqs.equals(null)) {
				s.append("    - "+i.specialReqs+"\n");
			}
		}
		return s.toString();
	}

}