package server.ui;

import javax.swing.JPanel;
import javax.swing.JButton;

public class MenuPanel extends JPanel {

	private static final long serialVersionUID = -7728688801223513408L;

	/**
	 * Create the panel.
	 */
	public MenuPanel() {
		super();
		setBounds(233, 0, 962, 710);
		setLayout(null);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(0, 0, 97, 25);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(0, 685, 97, 25);
		add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("New button");
		btnNewButton_1_1.setBounds(865, 685, 97, 25);
		add(btnNewButton_1_1);
		
		JButton btnNewButton_1_2 = new JButton("New button");
		btnNewButton_1_2.setBounds(842, 0, 97, 25);
		add(btnNewButton_1_2);
		
	}
}
