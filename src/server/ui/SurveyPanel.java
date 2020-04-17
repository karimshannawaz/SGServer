package server.ui;

import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.JLabel;

//Floreta Krasniqi

public class SurveyPanel extends JPanel {

	private static final long serialVersionUID = -2124017323274436141L;

	/**
	 * Create the panel.
	 */
	public SurveyPanel() {
		super();
		setBounds(233, 0, 962, 710);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Surveys will show up here");
		lblNewLabel.setBounds(296, 163, 341, 34);
		add(lblNewLabel);

	}

}
