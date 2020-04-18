package server.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import server.Global;
import server.utils.JFrameUtils;

/**
 * Holds info for the manager to see including
 * marking orders as complete, taking cash payments and 
 * fulfilling help/refill requests.
 * 
 * @author Karimshan
 * 
 */
public class TablesPanel extends JPanel {

	private static final long serialVersionUID = -7728688801223383898L;

	private static JTable table;

	/**
	 * Create the panel.
	 */
	public TablesPanel() {


		/*
		// Create our scroll pane
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 16, 494, 659);
		add(scrollPane);

		//create table
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {

			},
			new String[] {
				"Table Number", "Refill", "Help", "Order"
			}
		) {
			Class[] columnTypes = new Class[] {
				Object.class, Boolean.class, Boolean.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(166);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(166);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(166);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(3).setPreferredWidth(166);
		table.setBounds(0, 0, 1, 1);
		table.setRowHeight(30);

		DefaultTableModel model = (DefaultTableModel) table.getModel();

		//need to place this to be updated everytime someone logs into the customer side
		for(int i=0; i<Global.tableIds.length; i++)
		{
			if(Global.tableIds[i]!=0)
			{
				model.addRow(new Object[] {new Integer(i+1),new Boolean(false),new Boolean(false),null});
			}
		}


		List<Object> refill = new ArrayList<Object>(20);

		table.getModel().addTableModelListener(new TableModelListener(){

			public void tableChanged(TableModelEvent e) {
				// TODO Auto-generated method stub
				int row = e.getFirstRow();
			    int column = e.getColumn();
			    Object refReq = null;
			    if (column == 1) {
			        TableModel model = (TableModel) e.getSource();
			        Boolean checked1 = (Boolean) model.getValueAt(row, column);
			        if (checked1) {
			        	refReq="Coke, Lemonade";
			        	JFrameUtils.showMessage("Refills", "You have a new refill request: "+refReq+ " "+(row+1));
			        	//read in the customer data about refill
			        	refill.add(row,refReq);
			        } else {
			        	//show the waiter the refill request
			        	//store into object
			        	//refReq = "Coke, Lemonade";
			        	boolean confirm = JFrameUtils.confirmDialog("Table Refill", refill.get(row));
			        	if(!confirm) {
			        		model.setValueAt(Boolean.TRUE, row, column);
			        	}
			        	else
			        	{
			        		refill.add(row,null);
			        	}

			        }
			    }
			    else if(column == 2)
			    {
			    	TableModel model = (TableModel) e.getSource();
			    	String columnName = model.getColumnName(column);
			    	Boolean checked2= (Boolean) model.getValueAt(row, column);
			    	if (checked2) {
			        	JFrameUtils.showMessage("Help", "You have a new help request: at Table "+(row+1));
			        } else {
			        	//show the waiter the refill request
			        	//store into object
			        	//refReq = "Coke, Lemonade";
			        	boolean confirm = JFrameUtils.confirmDialog("Table Help", "Notifying customer... ");
			        	if(!confirm) {
			        		model.setValueAt(Boolean.TRUE, row, column);
			        	}
			        	else
			        	{
			        		//send to customer that waiter is coming
			        	}

			        }
			    }
			}
		});

		// Sets the table header and row font, as well as adjusts the row height.
		table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 30));
		table.setFont(new Font("Tahoma", Font.PLAIN, 30));
		table.setRowHeight(30);

		scrollPane.setViewportView(table);

		table.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {

			}

			@Override
			public void mouseExited(MouseEvent arg0) {

			}

			@Override
			public void mousePressed(MouseEvent arg0) {

			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				System.out.println("Current index is: "+table.getSelectedRow());
				//ing.setText(""+model.getValueAt(table.getSelectedRow(), 0));
				//qty.setText(""+model.getValueAt(table.getSelectedRow(), 1));
			}

		});
		 */
		super();
		setBounds(233, 0, 962, 710);
		setLayout(null);

		// Create our scroll pane
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 962, 710);
		add(scrollPane);


		table = createTable();
		table.setRowSelectionAllowed(false);
		table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 18));
		table.setFont(new Font("Tahoma", Font.PLAIN, 18));
		table.setRowHeight(34);

		table.addMouseListener(new MouseListener() {

			@Override public void mouseClicked(MouseEvent arg0) { }

			@Override public void mouseEntered(MouseEvent arg0) { }

			@Override public void mouseExited(MouseEvent arg0) { }

			@Override public void mousePressed(MouseEvent arg0) { }

			@Override
			public void mouseReleased(MouseEvent arg0) {
				int row = table.getSelectedRow();
				int col = table.getSelectedColumn();
				if(col == 0)
					return;
				if(arg0.isPopupTrigger())
					return;
				if(!(table.getModel().getValueAt(row, col) instanceof String))
					return;
				String val = (String) table.getModel().getValueAt(row, col);
				if(val.equals("X"))
					return;
				else {

					// Refill
					if(col == 1) {
					
					}
					// Help
					else if(col == 2) {
						
					}
					// Confirming table got order
					else if(col == 3) {
						boolean choice = JFrameUtils.confirmDialog("Order Completion Confirmation", 
								"Are you sure you want to mark this order for table "+(row + 1)+" as delivered?"
										+ "\nThis action cannot be undone.");
						if(!choice) {
							return;
						}
						
					}
				}
			}

		});

		scrollPane.setViewportView(table);
	}

	public JTable createTable() {

		Object[][] rows = new Object[20][5];

		for(int i = 0; i < 20; i++) {
			rows[i][0] = new Integer(i + 1);
			rows[i][1] = "X";
			rows[i][2] = "X";
			rows[i][3] = "X";
			rows[i][4] = "X";
		}

		Object[] cols = new Object[]{
				"Table Number", "Refill", "Help", "Order", "Payment"
		};

		JTable t = new JTable(rows, cols) {

			private static final long serialVersionUID = 1L;

			@Override
			public Class<?> getColumnClass(int column) {
				int col = convertColumnIndexToModel(column);
				if(col == 0) 
					return Integer.class;
				else if(col >= 1)
					return String.class;
				return super.getColumnClass(column);
			}

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		t.setDefaultRenderer(Integer.class, centerRenderer);

		t.setDefaultRenderer(String.class, new DefaultTableCellRenderer() {

			private static final long serialVersionUID = 1L;

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, 
					boolean isSelected, boolean hasFocus, int row, int column) {
				super.setHorizontalAlignment( JLabel.CENTER );
				JLabel c = (JLabel) super.getTableCellRendererComponent(table, value, 
						isSelected, hasFocus, row, column);
				Color darkgreen = new Color(0, 153, 0);
				c.setBackground(c.getText().equals("O") ? darkgreen : Color.RED);
				return c;
			}
		});

		return t;
	}




	public static void tableUpdate()
	{
		DefaultTableModel model = (DefaultTableModel) table.getModel();

		//need to place this to be updated everytime someone logs into the customer side
		for(int i=0; i<Global.tableIds.length; i++)
		{
			if(Global.tableIds[i]!=0)
			{
				model.addRow(new Object[] {new Integer(i+1),new Boolean(false),new Boolean(false),null});
			}
		}
	}

	/*
	@Override
	public void tableChanged(TableModelEvent e) {
		// TODO Auto-generated method stub

	}
	 */

	//functions


}
