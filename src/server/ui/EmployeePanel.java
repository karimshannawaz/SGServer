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

import server.menu.Inventory;
import server.user.User;
import server.user.UserLoader;
import server.utils.JFrameUtils;

import javax.swing.JComboBox;

//Floreta Krasniqi
// and Karimshan
public class EmployeePanel extends JPanel {

	private static final long serialVersionUID = -4184136749870861339L;

	private JTextField employeeName;

	private JTable table;
	private DefaultTableModel model;
	private JTextField employeeID;
	private JTextField employeePass;
	
	private JComboBox roleComboBox;

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

		// add employee list to rows
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
		
		employeePass = new JTextField();
		employeePass.setFont(new Font("Tahoma", Font.PLAIN, 16));
		employeePass.setBounds(631, 243, 262, 27);
		add(employeePass);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPassword.setBounds(535, 241, 82, 30);
		add(lblPassword);
		
		roleComboBox = new JComboBox(new String[] 
			{ "waitstaff", "kitchen", "manager" });
		roleComboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
		roleComboBox.setBounds(631, 195, 262, 30);
		add(roleComboBox);
		
		// Sets current selected index to 0
		table.setRowSelectionInterval(0, 0);

	}

	protected void addEmployee() {
		String id = this.employeeID.getText();
		String name = this.employeeName.getText();
		String role = (String) this.roleComboBox.getSelectedItem();
		String pwd = this.employeePass.getText();
		if(id.equals("") || id.equals(null) || id.contains(" ")
		|| name.equals("") || name.equals(null)
		|| pwd.equals("") || pwd.equals(null) || pwd.contains(" ")) {
			JFrameUtils.showMessage("Employee Editor", "Error: Invalid employee ID, Name or Password entered. Please try again.");
			return;
		}
		if(UserLoader.containsUser(id, true)) {
			JFrameUtils.showMessage("Employee Editor", "Error: This employee already exists.");
			return;
		}
		
		User employee = new User();
		employee.createEmployee(id, name, role, pwd);
		UserLoader.saveUser(employee, true);
		
		// Adds new employee to list.
		model.addRow(new Object[] { employee.getId(), employee.getName(), 
			employee.getRole(), employee.getPassword() });
		
		// Sets current selected index to newest one
		// and populates ingredient and quantity.
		employeeID.setText(""+model.getValueAt(table.getRowCount() - 1, 0));
		employeeName.setText(""+model.getValueAt(table.getRowCount() - 1, 1));
		employeePass.setText(""+model.getValueAt(table.getRowCount() - 1, 2));
		JFrameUtils.showMessage("Employee Editor", "Successfully added employee: "+name+" with ID: "+id);
	}

	protected void removeEmployee() {
		int row = table.getSelectedRow();
		String id = (String) this.model.getValueAt(table.getSelectedRow(), 0);
		boolean delete = JFrameUtils.confirmDialog("Employee Editor", "Are you sure you would like to remove employee with ID: "+id+"?\n"
				+ "WARNING: this action cannot be undone.");
		if(delete) {
			model.removeRow(row);
			// Sets current selected index to 0
			table.setRowSelectionInterval(0, 0);
			if(!UserLoader.deleteUser(id, true)) {
				JFrameUtils.showMessage("Employee Editor", "Unable to remove employee with ID "+id+". Please try again.");
			}
			else {
				JFrameUtils.showMessage("Employee Editor", "Successfully deleted/remove employee with ID "+id+".\nIf they were logged in, they will now be logged out.");
			}
		}
	}
}
