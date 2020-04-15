package server.ui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;

//Floreta Krasniqi

public class TimelogPanel extends JPanel {
	
	private static final long serialVersionUID = 1644025221735811591L;
	
	private JTable table;

	
	/**
	 * Create the panel.
	 */
	public TimelogPanel() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Employee Name");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblNewLabel.setBounds(222, 37, 132, 20);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Time In");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(402, 39, 75, 17);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Time Out");
		lblNewLabel_2.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(521, 37, 80, 20);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Total Time");
		lblNewLabel_3.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(650, 39, 103, 17);
		add(lblNewLabel_3);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(206, 68, 572, 575);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
	}
}
