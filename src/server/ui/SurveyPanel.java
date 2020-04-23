package server.ui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;

import javax.swing.JLabel;

//floreta

public class SurveyPanel extends JPanel {

	private static final long serialVersionUID = -2124017323274436141L;
	
	public JTable table;

	/**
	 * Create the panel.
	 */
	public SurveyPanel() {
		super();
		setBounds(233, 0, 962, 710);
		setLayout(null);
		
		//scroll pane for time log
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(36, 135, 896, 539);
				add(scrollPane);
				
				//create table
				table = new JTable();
				table.setModel(new DefaultTableModel(
				new String[] {
						"Table Number", "Question 1", "Question 2", "Question 3", "Question 4"
					}, 0
				));
				scrollPane.setViewportView(table);
				
				JLabel lblNewLabel = new JLabel("Survey Results");
				lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
				lblNewLabel.setBounds(378, 28, 210, 58);
				add(lblNewLabel);
		
		
	}
}
