package server.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import server.Global;
import server.Reports;
import server.user.User;

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
		currentRevenue.setBounds(367, 164, 373, 42);
		add(currentRevenue);
		
		currentTips = new JLabel("Current Revenue:");
		currentTips.setFont(new Font("Yu Gothic UI", Font.PLAIN, 24));
		currentTips.setBounds(367, 233, 373, 42);
		add(currentTips);
		
		mostPopMI = new JLabel("Current Revenue:");
		mostPopMI.setFont(new Font("Yu Gothic UI", Font.PLAIN, 24));
		mostPopMI.setBounds(367, 302, 405, 42);
		add(mostPopMI);
		
		newRewardMembers = new JLabel("Current Revenue:");
		newRewardMembers.setFont(new Font("Yu Gothic UI", Font.PLAIN, 24));
		newRewardMembers.setBounds(367, 376, 405, 42);
		add(newRewardMembers);
		
		employeesClockedIn = new JLabel("Current Revenue:");
		employeesClockedIn.setFont(new Font("Yu Gothic UI", Font.PLAIN, 24));
		employeesClockedIn.setBounds(367, 450, 373, 42);
		add(employeesClockedIn);
		
		totalHours = new JLabel("Current Revenue:");
		totalHours.setFont(new Font("Yu Gothic UI", Font.PLAIN, 24));
		totalHours.setBounds(367, 515, 339, 42);
		add(totalHours);
		
		JButton shutdown = new JButton("Safely Shutdown");
		shutdown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				shutdown();
			}
		});
		shutdown.setFont(new Font("Yu Gothic", Font.PLAIN, 23));
		shutdown.setBounds(646, 603, 243, 69);
		add(shutdown);

		
	}

	/**
	 * Safely shuts down the server and generates a report.
	 */
	protected void shutdown() {
		generateReport(true);
		for(User u : Global.getUsers()) {
			if(u != null) {
				u.getSession().getChannel().close();
			}
		}
		System.out.println("Safely exited all users and generated a report.");
		System.exit(1);
	}

	/**
	 * Generates a report to save to a file. Either during the day
	 * or at the end of the day
	 * @param endOfDay
	 */
	protected void generateReport(boolean endOfDay) {
		try {
			DateFormat d = new SimpleDateFormat("MMMM dd, yyyy");
			File f = new File("data/reports/"+d.format(new Date())+".txt");
			BufferedWriter writer = new BufferedWriter(new FileWriter(f, true));
			writer.write("[" + new Date() + "]: Total Active Tables: "+Reports.totalActiveTables);
			writer.write("[" + new Date() + "]: Total Revenue: "+Reports.totalRevenue);
			writer.write("[" + new Date() + "]: Total Tips: "+Reports.totalTips);
			writer.write("[" + new Date() + "]: Most Popular Menu Item: "+Reports.mostPopularItemName);
			writer.write("[" + new Date() + "]: New Reward Members: "+Reports.newRewardMembers);
			writer.write("[" + new Date() + "]: Total Employees Clocked In: "+Reports.totalEmployeesClockedIn);
			writer.write("[" + new Date() + "]: Total Hours Worked Today: "+Reports.totalHoursWorked);
			writer.newLine();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Updates all of the labels on this panel every second.
	 */
	public void updateLabels() {
		Date fullDate = new Date();
		String format = DateFormat.getInstance().format(fullDate);
		infoPLbl.setText(format+ " - Seven Guys General Information");
		activeTables.setText(""+Reports.activeTables);
		currentRevenue.setText(DecimalFormat.getCurrencyInstance().format((Reports.totalRevenue)));
		currentTips.setText(DecimalFormat.getCurrencyInstance().format((Reports.totalTips)));
		mostPopMI.setText(""+Reports.mostPopularItemName);
		newRewardMembers.setText(""+Reports.newRewardMembers);
		employeesClockedIn.setText(""+Reports.employeesClockedIn);
		totalHours.setText(""+Reports.totalHoursWorked);
	}
	
}
