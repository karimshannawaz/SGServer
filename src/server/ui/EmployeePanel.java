package server.ui;//

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import server.user.User;
import server.user.UserLoader;

//Floreta Krasniqi
/*
 * I need to add column names (employee name, role) to 
 * the table I've created, but Eclipse crashes every time
 * I try. Come back to this.
 */
// and Karimshan
public class EmployeePanel extends JPanel {

	private static final long serialVersionUID = -4184136749870861339L;

	private JTextField employeeName;
	private JTextField employeeRole;

	private JTable table;
	private DefaultTableModel model;
	private JTextField employeeID;
	private JTextField textField;

	//
	/**
	 * Create the panel.
	 */
	public EmployeePanel() {
		super();
		setLayout(null);
		setBounds(233, 0, 962, 710);

		// Create our scroll pane
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 24, 494, 659);
		add(scrollPane);

		//create table
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new String[] {
				"ID", "Name", "Role", "Password"
			}, 0
		));

		model = (DefaultTableModel) table.getModel();

		// add inventory list to rows
		for(User employee : UserLoader.getAllEmployees()) {
			model.addRow(new Object[] { employee.getId(), employee.getName(), 
				employee.getRole(), employee.getPassword() });
		}

		// Sets the table header and row font, as well as adjusts the row height.
		table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 20));
		table.setFont(new Font("Tahoma", Font.PLAIN, 20));
		table.setRowHeight(30);


		scrollPane.setViewportView(table);

		JLabel nameLabel = new JLabel("Name:");
		nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		nameLabel.setBounds(535, 147, 121, 30);
		add(nameLabel);

		JLabel roleLabel = new JLabel("Role:");
		roleLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		roleLabel.setBounds(535, 190, 70, 30);
		add(roleLabel);

		employeeName = new JTextField();
		employeeName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		employeeName.setBounds(631, 149, 262, 27);
		add(employeeName);

		employeeRole = new JTextField();
		employeeRole.setFont(new Font("Tahoma", Font.PLAIN, 16));
		employeeRole.setBounds(631, 192, 262, 27);
		add(employeeRole);

		JButton AddEmplBtn = new JButton("Add Employee");
		AddEmplBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addEmployee();
			}
		});
		AddEmplBtn.setFont(new Font("Tahoma", Font.PLAIN, 19));
		AddEmplBtn.setBounds(535, 334, 200, 44);
		add(AddEmplBtn);

		JButton RemoveEmplBtn = new JButton("Remove Employee");
		RemoveEmplBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				removeEmployee();
			}
		});
		RemoveEmplBtn.setFont(new Font("Tahoma", Font.PLAIN, 19));
		RemoveEmplBtn.setBounds(745, 334, 189, 44);

		add(RemoveEmplBtn);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblId.setBounds(535, 106, 70, 30);
		add(lblId);
		
		employeeID = new JTextField();
		employeeID.setFont(new Font("Tahoma", Font.PLAIN, 16));
		employeeID.setBounds(631, 107, 262, 27);
		add(employeeID);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField.setBounds(631, 243, 262, 27);
		add(textField);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPassword.setBounds(535, 241, 82, 30);
		add(lblPassword);

	}

	protected void addEmployee() {
		// TODO Auto-generated method stub
		
	}

	protected void removeEmployee() {
		// TODO Auto-generated method stub
		
	}
}
