package server.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import server.Global;
import server.Reports;
import server.menu.Inventory;
import server.network.Session;
import server.user.User;
import server.user.UserLoader;
import server.utils.JFrameUtils;

/**
 * 
 * @author Asha and Desere
 *
 */
public class TablesPanel extends JPanel implements TableModelListener{

	private static final long serialVersionUID = -7728688801223383898L;

	private JTable table;
	private DefaultTableModel model;

	/**
	 * Create the panel.
	 */
	public TablesPanel() {
		super();
		setBounds(233, 0, 962, 710);
		setLayout(null);

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
		/*
		for(int i=0; i<Global.tableIds.length; i++)
		{
			if(Global.tableIds[i]!=0)
			{
				model.addRow(new Object[] {new Integer(i+1),new Boolean(false),new Boolean(false),null});
			}
		}
		*/
		
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
		
	}

	@Override
	public void tableChanged(TableModelEvent e) {
		// TODO Auto-generated method stub
		
	}

	//functions

	
}
