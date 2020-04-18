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
 * @author Karimshan
 *
 */

public class InfoPanel extends JPanel {

	private static final long serialVersionUID = -7728688801223383898L;

	public JLabel infoPLbl;
	public JLabel activeTables;
	public JLabel currentRevenue;
	public JLabel currentTips;
	public JLabel mostPopMI;
	public JLabel newRewardMembers;
	public JLabel employeesClockedIn;
	public JLabel totalHours;

	/**
	 * Create the panel.
	 */
	public InfoPanel() {
		super();
		setBounds(233, 0, 962, 710);
		setLayout(null);
		
		//prints the date
		infoPLbl = new JLabel("<dynamic Date> - Seven Guys General Information");
		infoPLbl.setFont(new Font("SimSun", Font.PLAIN, 35));
		infoPLbl.setBounds(32, 13, 898, 59);
		add(infoPLbl);
		
		JLabel lblNewLabel_1 = new JLabel("Current Active Tables:");
		lblNewLabel_1.setFont(new Font("Yu Gothic UI", Font.PLAIN, 24));
		lblNewLabel_1.setBounds(49, 100, 255, 42);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Current Revenue:");
		lblNewLabel_1_1.setFont(new Font("Yu Gothic UI", Font.PLAIN, 24));
		lblNewLabel_1_1.setBounds(49, 164, 196, 42);
		add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Current Tips Total:");
		lblNewLabel_1_2.setFont(new Font("Yu Gothic UI", Font.PLAIN, 24));
		lblNewLabel_1_2.setBounds(49, 233, 210, 42);
		add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Most Popular Menu Item:");
		lblNewLabel_1_3.setFont(new Font("Yu Gothic UI", Font.PLAIN, 24));
		lblNewLabel_1_3.setBounds(49, 302, 296, 42);
		add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("New Reward Members:");
		lblNewLabel_1_4.setFont(new Font("Yu Gothic UI", Font.PLAIN, 24));
		lblNewLabel_1_4.setBounds(49, 376, 262, 42);
		add(lblNewLabel_1_4);
		
		JLabel lblNewLabel_1_5 = new JLabel("Employees Clocked In:");
		lblNewLabel_1_5.setFont(new Font("Yu Gothic UI", Font.PLAIN, 24));
		lblNewLabel_1_5.setBounds(49, 450, 262, 42);
		add(lblNewLabel_1_5);
		
		JLabel lblNewLabel_1_6 = new JLabel("Total Hours Worked Today:");
		lblNewLabel_1_6.setFont(new Font("Yu Gothic UI", Font.PLAIN, 24));
		lblNewLabel_1_6.setBounds(49, 515, 306, 42);
		add(lblNewLabel_1_6);
		
		JButton btnNewButton = new JButton("Generate Report");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generateReport(false);
			}
		});
		btnNewButton.setFont(new Font("Yu Gothic", Font.PLAIN, 23));
		btnNewButton.setBounds(73, 603, 243, 69);
		add(btnNewButton);
		
		activeTables = new JLabel("<dynamic>");
		activeTables.setFont(new Font("Yu Gothic UI", Font.PLAIN, 24));
		activeTables.setBounds(367, 100, 255, 42);
		add(activeTables);
		
		currentRevenue = new JLabel("Current Revenue:");
		currentRevenue.setFont(new Font("Yu Gothic UI", Font.PLAIN, 24));
		currentRevenue.setBounds(367, 164, 196, 42);
		add(currentRevenue);
		
		currentTips = new JLabel("Current Revenue:");
		currentTips.setFont(new Font("Yu Gothic UI", Font.PLAIN, 24));
		currentTips.setBounds(367, 233, 196, 42);
		add(currentTips);
		
		mostPopMI = new JLabel("Current Revenue:");
		mostPopMI.setFont(new Font("Yu Gothic UI", Font.PLAIN, 24));
		mostPopMI.setBounds(367, 302, 196, 42);
		add(mostPopMI);
		
		newRewardMembers = new JLabel("Current Revenue:");
		newRewardMembers.setFont(new Font("Yu Gothic UI", Font.PLAIN, 24));
		newRewardMembers.setBounds(367, 376, 196, 42);
		add(newRewardMembers);
		
		employeesClockedIn = new JLabel("Current Revenue:");
		employeesClockedIn.setFont(new Font("Yu Gothic UI", Font.PLAIN, 24));
		employeesClockedIn.setBounds(367, 450, 196, 42);
		add(employeesClockedIn);
		
		totalHours = new JLabel("Current Revenue:");
		totalHours.setFont(new Font("Yu Gothic UI", Font.PLAIN, 24));
		totalHours.setBounds(367, 515, 196, 42);
		add(totalHours);

		
	}

	/**
	 * Generates a report to save to a file. Either during the day
	 * or at the end of the day
	 * @param endOfDay
	 */
	protected void generateReport(boolean endOfDay) {
		
	}

	/**
	 * Updates all of the labels on this panel every second.
	 */
	public void updateLabels() {
		Date fullDate = new Date();
		String format = DateFormat.getInstance().format(fullDate);
		String[] tok = format.split(" ");
		String date = tok[0];
		String time = tok[1];
		String meridiem = tok[2];
		String[] timeToks = time.split(":");
		int hour = Integer.parseInt(timeToks[0]);
		int mins = Integer.parseInt(timeToks[1]);
		infoPLbl.setText(format+ " - Seven Guys General Information");
		activeTables.setText(""+Reports.activeTables);
		currentRevenue.setText(DecimalFormat.getCurrencyInstance().format((Reports.totalRevenue)));
		currentTips.setText(DecimalFormat.getCurrencyInstance().format((Reports.totalTips)));
		mostPopMI.setText(""+Reports.getMostPopularMenuItem());
		newRewardMembers.setText(""+Reports.newRewardMembers);
		employeesClockedIn.setText(""+Reports.employeesClockedIn);
		totalHours.setText(""+Reports.totalHoursWorked);
	}
	
}
