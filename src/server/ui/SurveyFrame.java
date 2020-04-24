package server.ui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

//floreta
// and Shan
public class SurveyFrame extends JFrame {

	private static final long serialVersionUID = -2124017323274436141L;
	
	public JTable table;
	
	public JPanel contentPanel;
	
	public static String[] q1A = { "Bad", "Okay", "Excellent" };
	public static String[] q2A = { "Yes", "No" };
	public static String[] q3A = { "Not Likely", "Very Likely" };
	public static String[] q4A = { "Dissatisfied", "Neutral", "Very Satisfied" };
	
	public static int[] q1 = new int[3];
	public static int[] q2 = new int[2];
	public static int[] q3 = new int[2];
	public static int[] q4 = new int[3];

	private JLabel q1bad;

	private JLabel q1okay;

	private JLabel q1excellent;

	private JLabel q2yes;

	private JLabel q2no;

	private JLabel q3notLikely;

	private JLabel q3veryLikely;

	private JLabel q4dissatisfied;

	private JLabel q4neutral;

	private JLabel q4satisfied;

	/**
	 * Create the panel.
	 */
	public SurveyFrame() {
		setTitle("Surveys");
		contentPanel = new JPanel();
		setBounds(233, 0, 962, 710);
		contentPanel.setBounds(233, 0, 962, 710);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setContentPane(contentPanel);

		/*
		//scroll pane for time log
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(36, 135, 896, 539);
		contentPanel.add(scrollPane);

		//create table
		table = new JTable();
		table.setModel(new DefaultTableModel(
				new String[] {
					"Question 1", "Question 2", "Question 3", "Question 4"
				}, 0
				));
		scrollPane.setViewportView(table);
		*/

		JLabel mainLbl = new JLabel("Survey Results");
		mainLbl.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		mainLbl.setBounds(425, 28, 210, 58);
		contentPanel.add(mainLbl);
		
		JLabel question1 = new JLabel("How was your experience today?");
		question1.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		question1.setBounds(114, 113, 354, 38);
		contentPanel.add(question1);
		
		JLabel question2 = new JLabel("Did our staff meet your needs?");
		question2.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		question2.setBounds(114, 184, 329, 35);
		contentPanel.add(question2);
		
		JLabel question3 = new JLabel("How likely are you to return?");
		question3.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		question3.setBounds(114, 249, 329, 28);
		add(question3);
		
		JLabel question4 = new JLabel("How satisfied were you with our kiosk system?");
		question4.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		question4.setBounds(114, 311, 450, 38);
		contentPanel.add(question4);
		
		q1bad = new JLabel("Bad");
		q1bad.setBackground(Color.WHITE);
		q1bad.setBounds(634, 123, 71, 23);
		contentPanel.add(q1bad);
		
		q1okay = new JLabel("Okay");
		q1okay.setBackground(Color.WHITE);
		q1okay.setBounds(717, 123, 71, 23);
		contentPanel.add(q1okay);
		
		q1excellent = new JLabel("Excellent");
		q1excellent.setBackground(Color.WHITE);
		q1excellent.setBounds(800, 123, 89, 23);
		add(q1excellent);
		
		q2yes = new JLabel("Yes");
		q2yes.setBackground(Color.WHITE);
		q2yes.setBounds(636, 192, 69, 23);
		contentPanel.add(q2yes);
		
		q2no = new JLabel("No");
		q2no.setBackground(Color.WHITE);
		q2no.setBounds(719, 192, 69, 23);
		add(q2no);
		
		q3notLikely = new JLabel("Not Likely");
		q3notLikely.setBackground(Color.WHITE);
		q3notLikely.setBounds(634, 254, 106, 23);
		contentPanel.add(q3notLikely);
		
		q3veryLikely = new JLabel("Very Likely");
		q3veryLikely.setBackground(Color.WHITE);
		q3veryLikely.setBounds(746, 254, 106, 23);
		add(q3veryLikely);
		
		q4dissatisfied = new JLabel("Dissatisfied");
		q4dissatisfied.setBackground(Color.WHITE);
		q4dissatisfied.setBounds(634, 321, 117, 23);
		contentPanel.add(q4dissatisfied);
		
		q4neutral = new JLabel("Neutral");
		q4neutral.setBackground(Color.WHITE);
		q4neutral.setBounds(763, 321, 83, 23);
		add(q4neutral);
		
		q4satisfied = new JLabel("Very Satisfied");
		q4satisfied.setBounds(858, 321, 117, 23);
		q4satisfied.setBackground(Color.WHITE);
		contentPanel.add(q4satisfied);
		
		JPanel p1 = new JPanel();
		p1.setBackground(Color.WHITE);
		p1.setBounds(49, 109, 946, 46);
		contentPanel.add(p1);
		
		JPanel p2 = new JPanel();
		p2.setBackground(Color.WHITE);
		p2.setBounds(49, 177, 946, 48);
		contentPanel.add(p2);
		
		JPanel p3 = new JPanel();
		p3.setBackground(Color.WHITE);
		p3.setBounds(49, 239, 946, 48);
		contentPanel.add(p3);
		
		JPanel p4 = new JPanel();
		p4.setBackground(Color.WHITE);
		p4.setBounds(49, 309, 946, 48);
		contentPanel.add(p4);
		
		pack();
	}

	/**
	 * Processes survey answers
	 * @param q1
	 * @param q2
	 * @param q3
	 * @param q4
	 */
	public void processAnswers(String q1, String q2, String q3, String q4) {
		int q1I = getIndex(0, q1);
		int q2I = getIndex(1, q2);
		int q3I = getIndex(2, q3);
		int q4I = getIndex(3, q4);
		SurveyFrame.q1[q1I] += 1;
		SurveyFrame.q2[q2I] += 1;
		SurveyFrame.q3[q3I] += 1;
		SurveyFrame.q4[q4I] += 1;
		refreshLabels();
	}
	
	/**
	 * Returns the index of the question
	 * @param i
	 * @param q12
	 * @return
	 */
	private static int getIndex(int questionIndex, String q) {
		int index = 0;
		if(questionIndex == 0 || questionIndex == 3) {
			for(int i = 0; i < q1A.length; i++) {
				if((q.equalsIgnoreCase(q1A[i]) && questionIndex == 0) 
					|| (q.equalsIgnoreCase(q4A[i]) && questionIndex == 3)) {
					return index;
				}
				index++;
			}
		}
		else {
			for(int i = 0; i < q2A.length; i++) {
				if((q.equalsIgnoreCase(q2A[i]) && questionIndex == 1) 
					|| (q.equalsIgnoreCase(q3A[i]) && questionIndex == 2)) {
					return index;
				}
				index++;
			}
		}
		return -1;
	}

	/**
	 * Refreshes the survey result labels.
	 */
	public void refreshLabels() {
		q1bad.setText("Bad: "+q1[0]);
		q1okay.setText("Okay: "+q1[1]);
		q1excellent.setText("Excellent: "+q1[2]);
		
		q2yes.setText("Yes: "+q2[0]);
		q2no.setText("No: "+q2[1]);
		
		q3notLikely.setText("Not Likely: "+q3[0]);
		q3veryLikely.setText("Very Likely: "+q3[1]);
		
		q4dissatisfied.setText("Dissatisfied: "+q4[0]);
		q4neutral.setText("Neutral: "+q4[1]);
		q4satisfied.setText("Very Satisfied: "+q4[2]);
	}
}
