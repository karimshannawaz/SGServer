package server.ui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

//Floreta

public class ClockPanel extends JPanel {
	
	static final long serialVersionUID = 8778351450140155093L;
	
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Create the panel.
	 */
	public ClockPanel() {
		
		super();
		setBounds(233, 0, 962, 710);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Employee ID:");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblNewLabel.setBounds(278, 229, 145, 46);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password:");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(278, 287, 103, 46);
		add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(435, 233, 234, 40);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(435, 287, 234, 40);
		add(textField_1);
		
		JButton clockInBtn = new JButton("CLOCK IN");
		clockInBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clockIn();
			}
		});
		clockInBtn.setBounds(236, 360, 220, 40);
		add(clockInBtn);
		
		JButton clockOutBtn = new JButton("CLOCK OUT");
		clockOutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clockOut();
			}
		});
		clockOutBtn.setBounds(483, 360, 220, 40);
		add(clockOutBtn);
	}

		//function for clocking in
		protected void clockIn() {
		
		}
		
		//function for clocking out
		protected void clockOut() {
			
		}
}
