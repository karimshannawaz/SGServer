package server.ui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

//Floreta Krasniqi

public class TimelogPanel extends JPanel {
	
	private static final long serialVersionUID = 1644025221735811591L;
	
	private JTable table;

	
	/**
	 * Create the panel.
	 */
	public TimelogPanel() {
		super();
		setLayout(null);
		setBounds(233, 0, 962, 710);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(198, 68, 572, 575);
		add(scrollPane);
		
		//create table
		table = new JTable();
		table.setModel(new DefaultTableModel(
		new String[] {
				"ID", "Name", "Time In", "Time Out", "Total Time"
			}, 0
		));
		scrollPane.setViewportView(table);
		
	}
}
