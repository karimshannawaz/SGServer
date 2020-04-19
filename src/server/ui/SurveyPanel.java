package server.ui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

//Floreta Krasniqi and Asha

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
		
		//JLabel lblNewLabel = new JLabel("Surveys will show up here");
		//lblNewLabel.setBounds(296, 163, 341, 34);
		//add(lblNewLabel);
		
		// Create our scroll pane
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 962, 710);
		add(scrollPane);
		
		table = new JTable();
		table.setRowSelectionAllowed(false);
		table.setFillsViewportHeight(true);
		table.setFont(new Font("Tahoma", Font.PLAIN, 30));
		table.setRowHeight(60);
		
		scrollPane.setViewportView(table);
		
		/**
		 * Sets the model for this table.
		 */
		table.setModel(new DefaultTableModel(
				new Object[][] {

				},
				new String[] {
					"Table", "Survey Results"
				}
				) {
			
			private static final long serialVersionUID = 1378760572757475870L;

			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
					Integer.class, String.class
			};

			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});

		// Sets the width of the columns.
		table.getColumnModel().getColumn(0).setPreferredWidth(30);
		table.getColumnModel().getColumn(1).setPreferredWidth(430);
		//table.getColumnModel().getColumn(2).setPreferredWidth(30);

		// Centers the text in the columns.
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		table.setDefaultRenderer(String.class, centerRenderer);
		table.setDefaultRenderer(Integer.class, centerRenderer);
		
		table.addMouseListener(new MouseListener() {
			@Override public void mouseClicked(MouseEvent arg0) { }
			@Override public void mouseEntered(MouseEvent arg0) { }
			@Override public void mouseExited(MouseEvent arg0) { }
			@Override public void mousePressed(MouseEvent arg0) { }

			@Override
			public void mouseReleased(MouseEvent arg0) {
				int row = table.getSelectedRow();
				int column = table.getSelectedColumn();
				if(column == 1) {
					int tableID = (int) table.getModel().getValueAt(row, 0) - 1;
					//viewSurveyDetails(tableID, row);
				}
			}
		});

	}
	/*
	 * displays survey answers
	 */
	public void viewSurveyDetails(int tableID, int row) {
		
	}

}
