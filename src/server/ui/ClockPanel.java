package server.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import server.Reports;
import server.Server;
import server.user.TimeLog;
import server.user.User;
import server.user.UserLoader;
import server.utils.JFrameUtils;

//Floreta Krasniqi

/*
 * Employee clock in/out page
 * 
 */

public class ClockPanel extends JPanel {

	static final long serialVersionUID = 8778351450140155093L;

	private JTextField employeeID;
	public JLabel dateLbl;


	/**
	 * Create the panel.
	 */
	public ClockPanel() {

		super();
		setBounds(233, 0, 962, 710);
		setLayout(null);

		//main panel
		JPanel panel = new JPanel();
		panel.setBackground(UIManager.getColor("Button.highlight"));
		panel.setBounds(166, 178, 613, 283);
		add(panel);
		panel.setLayout(null);

		//id prompt
		JLabel lblNewLabel = new JLabel("Employee ID:");
		lblNewLabel.setBounds(122, 77, 145, 46);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));

		//id text field for user input
		employeeID = new JTextField();
		employeeID.setFont(new Font("Dialog", Font.PLAIN, 20));
		employeeID.setBounds(279, 83, 234, 40);
		panel.add(employeeID);
		employeeID.setColumns(10);

		//button to clock in
		JButton clockInBtn = new JButton("CLOCK IN");
		clockInBtn.setBackground(UIManager.getColor("Button.background"));
		clockInBtn.setBounds(80, 181, 220, 40);
		panel.add(clockInBtn);

		//button to clock out
		JButton clockOutBtn = new JButton("CLOCK OUT");
		clockOutBtn.setBounds(319, 181, 220, 40);
		panel.add(clockOutBtn);

		//prints current date
		dateLbl = new JLabel("The date is currently: <dynamic date>");
		dateLbl.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		dateLbl.setBounds(201, 62, 549, 48);
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
		dateLbl.setText("The date is currently: " +format);


		//if clock out button is clicked
		clockOutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clockOut();//call function to handle clocking out if user selects button
			}
		});

		//if clock in button is clicked
		clockInBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clockIn();//call function to handle clocking in if user selects button
			}
		});


	}

	//function for clocking in
	protected void clockIn() {
		String id = this.employeeID.getText();
		User user = UserLoader.loadUser(id, true);
		if(user != null) {
			String name = user.getName();
			if(!TimeLog.clockIn(id, name)) {
				return;
			}
			Reports.employeesClockedIn++;
			Reports.totalEmployeesClockedIn++;
			Server.ui.infoPanel.updateLabels();
			JFrameUtils.showMessage("Clock In", "Welcome back! You have successfully clocked in.");
			return;
		}
		else {
			JFrameUtils.showMessage("Clock In", "Error: Invalid ID - This employee does not exist.");
			return;
		}

	}

	// Function for clocking out
	protected void clockOut() {
		String id = this.employeeID.getText();
		User user = UserLoader.loadUser(id, true);
		if(user != null) {
			String name = user.getName();
			if(!TimeLog.clockOut(id, name)) {
				return;
			}
			Reports.employeesClockedIn--;
			Server.ui.infoPanel.updateLabels();
			JFrameUtils.showMessage("Clock Out", "You have successfully clocked out. See you next time!");
			return;
		}
		else {
			JFrameUtils.showMessage("Clock Out", "Error: Invalid ID - This employee does not exist.");
			return;
		}

	}
}
