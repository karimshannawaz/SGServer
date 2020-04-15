package server.ui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

//Floreta Krasniqi
/*
 * I need to add column names (employee name, role) to 
 * the table I've created, but Eclipse crashes every time
 * I try. Come back to this.
 */

public class EmployeePanel extends JPanel {
	
	private JTable table;
	private JTextField employeeName;
	private JTextField employeeRole;

	/**
	 * Create the panel.
	 */
	public EmployeePanel() {
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 25, 269, 259);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel nameLabel = new JLabel("Employee Name:");
		nameLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		nameLabel.setBounds(308, 94, 100, 16);
		add(nameLabel);
		
		JLabel roleLabel = new JLabel("Role:");
		roleLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		roleLabel.setBounds(308, 122, 61, 16);
		add(roleLabel);
		
		employeeName = new JTextField();
		employeeName.setBounds(403, 88, 130, 26);
		add(employeeName);
		employeeName.setColumns(10);
		
		employeeRole = new JTextField();
		employeeRole.setBounds(403, 116, 130, 26);
		add(employeeRole);
		employeeRole.setColumns(10);
		
		JButton AddEmplBtn = new JButton("Add Employee");
		AddEmplBtn.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		AddEmplBtn.setBounds(297, 150, 111, 29);
		add(AddEmplBtn);
		
		JButton RemoveEmplBtn = new JButton("Remove Employee");
		RemoveEmplBtn.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		RemoveEmplBtn.setBounds(403, 150, 130, 29);
		add(RemoveEmplBtn);

	}
}
