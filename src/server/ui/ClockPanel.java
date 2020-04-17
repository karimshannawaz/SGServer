package server.ui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.UIManager;

//Floreta Krasniqi

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
		
		JPanel panel = new JPanel();
		panel.setBackground(UIManager.getColor("Button.highlight"));
		panel.setBounds(166, 178, 613, 283);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Employee ID:");
		lblNewLabel.setBounds(90, 52, 145, 46);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
		JLabel lblNewLabel_1 = new JLabel("Password:");
		lblNewLabel_1.setBounds(119, 103, 103, 46);
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
		textField = new JTextField();
		textField.setBounds(276, 58, 234, 40);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(275, 108, 234, 40);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JButton clockInBtn = new JButton("CLOCK IN");
		clockInBtn.setBackground(UIManager.getColor("Button.background"));
		clockInBtn.setBounds(80, 181, 220, 40);
		panel.add(clockInBtn);
		
		JButton clockOutBtn = new JButton("CLOCK OUT");
		clockOutBtn.setBounds(319, 181, 220, 40);
		panel.add(clockOutBtn);
		clockOutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clockOut();//call function to handle clocking out if user selects button
			}
		});
		clockInBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clockIn();//call function to handle clocking in if user selects button
			}
		});
	}

		//function for clocking in
		protected void clockIn() {
		
		}
		
		//function for clocking out
		protected void clockOut() {
			
		}
}