package server.ui;//

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;

//Floreta Krasniqi
/*
 * I need to add column names (employee name, role) to 
 * the table I've created, but Eclipse crashes every time
 * I try. Come back to this.
 */

public class EmployeePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4184136749870861339L;

	private JTable table;
	private JTextField employeeName;
	private JTextField employeeRole;

	//
	/**
	 * Create the panel.
	 */
	public EmployeePanel() {
		super();
		setLayout(null);
		setBounds(233, 0, 962, 710);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 25, 534, 670);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel nameLabel = new JLabel("Employee Name:");
		nameLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		nameLabel.setBounds(620, 222, 125, 26);
		add(nameLabel);
		
		JLabel roleLabel = new JLabel("Role:");
		roleLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		roleLabel.setBounds(620, 265, 116, 16);
		add(roleLabel);
		
		employeeName = new JTextField();
		employeeName.setBounds(746, 223, 169, 26);
		add(employeeName);
		employeeName.setColumns(10);
		
		employeeRole = new JTextField();
		employeeRole.setBounds(748, 261, 167, 26);
		add(employeeRole);
		employeeRole.setColumns(10);
		
		JButton AddEmplBtn = new JButton("Add Employee");
AddEmplBtn.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		AddEmplBtn.setBounds(297, 150, 111, 29);

		AddEmplBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//add employee
			}
		});
		AddEmplBtn.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		AddEmplBtn.setBounds(620, 300, 142, 26);
		add(AddEmplBtn);
		
		JButton RemoveEmplBtn = new JButton("Remove Employee");

		RemoveEmplBtn.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		RemoveEmplBtn.setBounds(403, 150, 130, 29);

		RemoveEmplBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//remove employee
			}
		});
		RemoveEmplBtn.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		RemoveEmplBtn.setBounds(761, 299, 154, 29);

		add(RemoveEmplBtn);

	}
}
