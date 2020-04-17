package server.ui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

//Floreta Krasniqi

public class TimelogPanel extends JPanel {
	
	private static final long serialVersionUID = 1644025221735811591L;
	
	private JTable table;
	public JLabel dateLbl;
	private JLabel lblNewLabel;

	
	/**
	 * Create the panel.
	 */
	public TimelogPanel() {
		super();
		setLayout(null);
		setBounds(233, 0, 962, 710);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(201, 135, 572, 539);
		add(scrollPane);
		
		lblNewLabel = new JLabel("Employee Timelog");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		lblNewLabel.setBounds(355, 31, 277, 41);
		add(lblNewLabel);
		
		//create table
		table = new JTable();
		table.setModel(new DefaultTableModel(
		new String[] {
				"ID", "Name", "Time In", "Time Out", "Total Time"
			}, 0
		));
		scrollPane.setViewportView(table);
		
		//prints current date
		dateLbl = new JLabel("Current Time: <dynamic date>");
		dateLbl.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		dateLbl.setBounds(374, 84, 228, 35);
		add(dateLbl);
		Date fullDate = new Date();
		String format = DateFormat.getInstance().format(fullDate);
		String[] tok = format.split(" ");
		String date = tok[0];
		String time = tok[1];
		String meridiem = tok[2];
		String[] timeToks = time.split(":");
		int hour = Integer.parseInt(timeToks[0]);
		int mins = Integer.parseInt(timeToks[1]);
		dateLbl.setText("Current Time: " +format);
		
	}
}
