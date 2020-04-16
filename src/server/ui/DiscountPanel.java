package server.ui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JRadioButton;

//Floreta Krasniqi

public class DiscountPanel extends JPanel {

	private static final long serialVersionUID = -4618108778866433627L;

	/**
	 * Create the panel.
	 */
	
	//panel to display active specials/discounts for the restaurant
	//allows user to disable the specials
	public DiscountPanel() {
		
		super();
		setBounds(233, 0, 962, 710);
		
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Day");
		lblNewLabel.setBackground(Color.LIGHT_GRAY);
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		lblNewLabel.setBounds(79, 95, 68, 30);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Description");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		lblNewLabel_1.setBounds(422, 95, 137, 30);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Seven Guys Weekly Specials");
		lblNewLabel_2.setFont(new Font("Lucida Grande", Font.BOLD, 30));
		lblNewLabel_2.setBounds(285, 28, 457, 30);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Status");
		lblNewLabel_3.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		lblNewLabel_3.setBounds(757, 102, 83, 16);
		add(lblNewLabel_3);
		
		JLabel lblNewLabel_7 = new JLabel("Time");
		lblNewLabel_7.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		lblNewLabel_7.setBounds(209, 100, 93, 20);
		add(lblNewLabel_7);
		
		JPanel MondayPanel = new JPanel();
		MondayPanel.setBackground(Color.WHITE);
		MondayPanel.setBounds(57, 151, 859, 88);
		add(MondayPanel);
		MondayPanel.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("Monday");
		lblNewLabel_4.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblNewLabel_4.setBounds(20, 36, 61, 16);
		MondayPanel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Kids (12 and under) eat free");
		lblNewLabel_5.setBounds(364, 23, 229, 16);
		MondayPanel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("with the purchase of an adult entree");
		lblNewLabel_6.setBounds(364, 37, 240, 15);
		MondayPanel.add(lblNewLabel_6);
		
		JLabel lblNewLabel_8 = new JLabel("4 PM - 11:59 PM CST");
		lblNewLabel_8.setBounds(152, 38, 132, 15);
		MondayPanel.add(lblNewLabel_8);
		
		//radio button for monday special. pressing button will disable the special
		JRadioButton MondayRadioBtn = new JRadioButton("OFF");
		MondayRadioBtn.setBounds(702, 33, 115, 23);
		MondayPanel.add(MondayRadioBtn);
		
		JPanel SundayPanel = new JPanel();
		SundayPanel.setBackground(Color.WHITE);
		SundayPanel.setBounds(57, 260, 859, 88);
		add(SundayPanel);
		SundayPanel.setLayout(null);
		
		JLabel lblNewLabel_4_1 = new JLabel("Sunday");
		lblNewLabel_4_1.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblNewLabel_4_1.setBounds(19, 35, 61, 16);
		SundayPanel.add(lblNewLabel_4_1);
		
		JLabel lblNewLabel_8_1 = new JLabel("4 PM - 11:59 PM CST");
		lblNewLabel_8_1.setBounds(149, 36, 132, 15);
		SundayPanel.add(lblNewLabel_8_1);
		
		JLabel lblNewLabel_6_1 = new JLabel("Beverages free with any purchase");
		lblNewLabel_6_1.setBounds(362, 36, 240, 15);
		SundayPanel.add(lblNewLabel_6_1);
		
		//radio button for sunday special. pressing button will disable the special
		JRadioButton SundayRadioBtn = new JRadioButton("OFF");
		SundayRadioBtn.setBounds(699, 32, 115, 23);
		SundayPanel.add(SundayRadioBtn);

	}
}
