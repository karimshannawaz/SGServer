package server.ui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import server.user.TimeLog;

//Floreta Krasniqi

/*
 * page for manager to view employee time log
 * 
 */


public class TimelogPanel extends JPanel {
	
	private static final long serialVersionUID = 1644025221735811591L;
	
	public JTable table;
	public JLabel dateLbl;
	private JLabel lblNewLabel;
	
	/**
	 * Create the panel.
	 */
	public TimelogPanel() {
		super();
		setLayout(null);
		setBounds(233, 0, 962, 710);
		
		lblNewLabel = new JLabel("Employee Time Log");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		lblNewLabel.setBounds(344, 31, 316, 41);
		add(lblNewLabel);
		
		//scroll pane for time log
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(201, 135, 572, 539);
		add(scrollPane);
		
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
		dateLbl.setBounds(366, 84, 245, 35);
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
	
	/**
	 * Updates the table with the newest time logs.
	 * @param id
	 * @param clockIn
	 */
	public void updateTable(String id, boolean clockIn) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		int index = 0;
		TimeLog log = null;
		for(TimeLog l : TimeLog.logs) {
			if(l.getId().equals(id)) {
				log = l;
				break;
			}
			index++;
		}
		if(clockIn) {
			model.setValueAt(log.getPunchIns().get(log.getPunchIns().size() - 1), index, 2);
			model.setValueAt("", index, 3);
			model.setValueAt("", index, 4);
		}
		else {
			model.setValueAt(log.getPunchIns().get(log.getPunchIns().size() - 1), index, 2);
			model.setValueAt(log.getPunchOuts().get(log.getPunchOuts().size() - 1), index, 3);
			model.setValueAt(log.getTotalWorkedHours(), index, 4);
		}
	}
}
