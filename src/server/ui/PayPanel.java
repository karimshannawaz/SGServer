package server.ui;

import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.JLabel;

//Floreta Krasniqi

public class PayPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public PayPanel() {
		super();
		setBounds(233, 0, 962, 710);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("sorry to disappoint u. there is nothing here yet :-(");
		lblNewLabel.setBounds(296, 163, 341, 34);
		add(lblNewLabel);

	}

}
