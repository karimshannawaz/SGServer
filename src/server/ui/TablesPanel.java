package server.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import server.menu.Order;
import server.user.Payments;
import server.user.Requests;
import server.utils.JFrameUtils;

/**
 * Holds info for the manager to see including
 * marking orders as complete, taking cash payments and 
 * fulfilling help/refill requests.
 * 
 * @author Karimshan
 * 
 */
public class TablesPanel extends JPanel {

	private static final long serialVersionUID = -7728688801223383898L;

	public JTable table;
	
	public boolean[] requiresRequest = new boolean[20];
	public boolean[] requiresOrder = new boolean[20];
	public boolean[] requiresPayment = new boolean[20];

	/**
	 * Create the panel.
	 */
	public TablesPanel() {
		super();
		setBounds(233, 0, 962, 710);
		setLayout(null);

		// Create our scroll pane
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 962, 710);
		add(scrollPane);

		table = createTable();
		table.setRowSelectionAllowed(false);
		table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 18));
		table.setFont(new Font("Tahoma", Font.PLAIN, 18));
		table.setRowHeight(34);

		table.addMouseListener(new MouseListener() {

			@Override public void mouseClicked(MouseEvent arg0) { }

			@Override public void mouseEntered(MouseEvent arg0) { }

			@Override public void mouseExited(MouseEvent arg0) { }

			@Override public void mousePressed(MouseEvent arg0) { }

			@Override
			public void mouseReleased(MouseEvent arg0) {
				int row = table.getSelectedRow();
				int col = table.getSelectedColumn();
				if(col == 0)
					return;
				if(arg0.isPopupTrigger())
					return;
				if(!(table.getModel().getValueAt(row, col) instanceof String))
					return;
				String val = (String) table.getModel().getValueAt(row, col);
				if(val.equals("X"))
					return;
				else {

					// Refill
					if(col == 1) {
						if(!requiresRequest[row])
							return;
						boolean choice = JFrameUtils.confirmDialog("Refill On The Way", 
							"Are you sure you want to let table "+(row + 1)+" know that you're on your way\n"
							+ "with the refill that they requested?"
							+ " This action cannot be undone.");
						if(!choice) {
							return;
						}
						table.getModel().setValueAt("X", row, col);
						Requests.completeRequest(row, true);
					}
					// Help
					else if(col == 2) {
						if(!requiresRequest[row])
							return;
						boolean choice = JFrameUtils.confirmDialog("Help On The Way", 
							"Are you sure you want to let table "+(row + 1)+" know that you're on your way\n"
							+ "to help them with their request?"
							+ " This action cannot be undone.");
						if(!choice) {
							return;
						}
						table.getModel().setValueAt("X", row, col);
						Requests.completeRequest(row, false);
					}
					// Confirming table got order
					else if(col == 3) {
						if(!requiresOrder[row])
							return;
						boolean choice = JFrameUtils.confirmDialog("Order Completion Confirmation", 
							"Are you sure you want to mark this order for table "+(row + 1)+" as delivered?"
								+ "\nThis action cannot be undone.");
						if(!choice) {
							return;
						}
						table.getModel().setValueAt("X", row, col);
						Order.waiterDroppedFoodOff(null, row);
					}
					// Confirming customer paid with cash.
					else if(col == 4) {
						if(!requiresPayment[row])
							return;
						boolean choice = JFrameUtils.confirmDialog("Cash Payment Confirmation", 
							"Are you sure you want to confirm table "+(row + 1)+"'s cash payment?"
							+ "\nThis action cannot be undone.");
						if(!choice) {
							return;
						}
						table.getModel().setValueAt("X", row, col);
						Payments.completePayment(row);
					}
				}
			}

		});

		scrollPane.setViewportView(table);
	}

	public JTable createTable() {

		Object[][] rows = new Object[20][5];

		for(int i = 0; i < 20; i++) {
			rows[i][0] = new Integer(i + 1);
			rows[i][1] = "X";
			rows[i][2] = "X";
			rows[i][3] = "X";
			rows[i][4] = "X";
		}

		Object[] cols = new Object[]{
				"Table Number", "Refill", "Help", "Order", "Payment"
		};

		JTable t = new JTable(rows, cols) {

			private static final long serialVersionUID = 1L;

			@Override
			public Class<?> getColumnClass(int column) {
				int col = convertColumnIndexToModel(column);
				if(col == 0) 
					return Integer.class;
				else if(col >= 1)
					return String.class;
				return super.getColumnClass(column);
			}

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		t.setDefaultRenderer(Integer.class, centerRenderer);

		t.setDefaultRenderer(String.class, new DefaultTableCellRenderer() {

			private static final long serialVersionUID = 1L;

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, 
					boolean isSelected, boolean hasFocus, int row, int column) {
				super.setHorizontalAlignment( JLabel.CENTER );
				JLabel c = (JLabel) super.getTableCellRendererComponent(table, value, 
						isSelected, hasFocus, row, column);
				Color darkgreen = new Color(0, 153, 0);
				c.setBackground(c.getText().equals("O") ? darkgreen : Color.RED);
				return c;
			}
		});

		return t;
	}

}
