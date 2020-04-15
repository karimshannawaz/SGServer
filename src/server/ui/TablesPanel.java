package server.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import server.Reports;
import server.menu.Inventory;
import server.utils.JFrameUtils;

/**
 * 
 * @author Asha
 *
 */
public class TablesPanel extends JPanel {

	private static final long serialVersionUID = -7728688801223383898L;

	private JTable table;
	private DefaultTableModel model;

	/**
	 * Create the panel.
	 */
	public TablesPanel() {
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
				"Table", "Refill", "Help", "Order"
				}, 0
			));

		model = (DefaultTableModel) table.getModel();

				// add inventory list to rows
			//	for (Map.Entry<?,?> entry : Inventory.instance.entrySet()) {
			//		model.addRow(new Object[] { entry.getKey(), entry.getValue() });
			//	}

				// Sets the table header and row font, as well as adjusts the row height.
		table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 30));
		table.setFont(new Font("Tahoma", Font.PLAIN, 30));
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
				//ing.setText(""+model.getValueAt(table.getSelectedRow(), 0));
				//qty.setText(""+model.getValueAt(table.getSelectedRow(), 1));
			}

		});
		
	}

	//functions

	
}
