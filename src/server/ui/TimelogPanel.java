package server.ui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;

//Floreta Krasniqi

public class TimelogPanel extends JPanel {

	
	/**
	 * Create the panel.
	 */
	public TimelogPanel() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Employee Name");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblNewLabel.setBounds(40, 8, 132, 20);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Time In");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(194, 10, 75, 17);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Time Out");
		lblNewLabel_2.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(294, 8, 80, 20);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Total Time");
		lblNewLabel_3.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(398, 10, 103, 17);
		add(lblNewLabel_3);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 40, 491, 276);
		add(scrollPane);
		
	}
}
