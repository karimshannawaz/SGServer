package server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import server.core.CoresManager;
import server.menu.MItem;
import server.menu.Menu;
import server.utils.Constants;
import server.utils.STime;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JToggleButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.Rectangle;
import javax.swing.JLayeredPane;
import javax.swing.JTable;

public class MainUI extends JFrame {

	private static final long serialVersionUID = 4680811856634328038L;
	
	private JPanel UtilityPanel;
	
	public static long onlineTime;

	/**
	 * Create the frame.
	 */
	public MainUI() {
		CoresManager.slowExecutor.scheduleWithFixedDelay(new Runnable() {
			@Override
			public void run() {
				try {
					onlineTime += 1000;
					setTitle("Seven Guys - Restaurant System has been online for: "+(STime.formatCountdown(STime.getCurrent() + onlineTime)));
				} catch (Throwable e) {
					e.printStackTrace();
				}
			}
		}, 0, 1, TimeUnit.SECONDS);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setBounds(100, 100, 1172, 761);
		
		//Create Frame to hold Manager Tasks
		UtilityPanel = new JPanel();
		UtilityPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(UtilityPanel);
		UtilityPanel.setLayout(null);
		
		JToggleButton ClockInOut_btn = new JToggleButton("Clock In/Out");
		ClockInOut_btn.setBounds(0, -3, 232, 74);
		UtilityPanel.add(ClockInOut_btn);
		
		JToggleButton Order_btn = new JToggleButton("Order");
		Order_btn.setBounds(0, 68, 232, 74);
		UtilityPanel.add(Order_btn);
		
		JToggleButton Pay_btn = new JToggleButton("Pay");
		Pay_btn.setBounds(0, 139, 232, 74);
		UtilityPanel.add(Pay_btn);
		
		JToggleButton Compensate_btn = new JToggleButton("Compensate");
		Compensate_btn.setBounds(0, 210, 232, 74);
		UtilityPanel.add(Compensate_btn);
		
		JToggleButton Tables_btn = new JToggleButton("Tables");
		Tables_btn.setBounds(0, 281, 232, 74);
		UtilityPanel.add(Tables_btn);
		
		JToggleButton Inventory_btn = new JToggleButton("Inventory");
		Inventory_btn.setBounds(0, 352, 232, 74);
		UtilityPanel.add(Inventory_btn);
		
		JButton AddItem_btn = new JButton("Add Menu Item");
		AddItem_btn.setBounds(0, 423, 232, 74);
		AddItem_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addMenuItem();
			}
		});
		UtilityPanel.add(AddItem_btn);
		
		JToggleButton TimeLog_btn = new JToggleButton("Time Log");
		TimeLog_btn.setBounds(0, 494, 232, 74);
		UtilityPanel.add(TimeLog_btn);
		
		JToggleButton Discounts_btn = new JToggleButton("Discounts");
		Discounts_btn.setBounds(0, 565, 232, 74);
		UtilityPanel.add(Discounts_btn);
		
		JToggleButton Employees_btn = new JToggleButton("Employees");
		Employees_btn.setBounds(0, 636, 232, 74);
		UtilityPanel.add(Employees_btn);
		
		ClockInOut_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (ClockInOut_btn.isSelected())
				{

					Order_btn.setSelected(false);
					Pay_btn.setSelected(false);
					Compensate_btn.setSelected(false);
					Tables_btn.setSelected(false);
					Inventory_btn.setSelected(false);
					AddItem_btn.setSelected(false);
					TimeLog_btn.setSelected(false);
					Discounts_btn.setSelected(false);
					Employees_btn.setSelected(false);
				}
				else
				{
					ClockInOut_btn.setSelected(false);
				}
			}
		});
		Order_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Order_btn.isSelected())
				{
					Pay_btn.setSelected(false);
					Compensate_btn.setSelected(false);
					Tables_btn.setSelected(false);
					Inventory_btn.setSelected(false);
					AddItem_btn.setSelected(false);
					TimeLog_btn.setSelected(false);
					Discounts_btn.setSelected(false);
					Employees_btn.setSelected(false);
					ClockInOut_btn.setSelected(false);
				}
				else
				{
					Order_btn.setSelected(false);
				}
			}
		});
		Pay_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Pay_btn.isSelected())
				{

					Order_btn.setSelected(false);
					Compensate_btn.setSelected(false);
					Tables_btn.setSelected(false);
					Inventory_btn.setSelected(false);
					AddItem_btn.setSelected(false);
					TimeLog_btn.setSelected(false);
					Discounts_btn.setSelected(false);
					Employees_btn.setSelected(false);
					ClockInOut_btn.setSelected(false);
				}
				else
				{
					Pay_btn.setSelected(false);
				}
			}
		});
		Compensate_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Compensate_btn.isSelected())
				{

					Order_btn.setSelected(false);
					Pay_btn.setSelected(false);
					Tables_btn.setSelected(false);
					Inventory_btn.setSelected(false);
					AddItem_btn.setSelected(false);
					TimeLog_btn.setSelected(false);
					Discounts_btn.setSelected(false);
					Employees_btn.setSelected(false);
					ClockInOut_btn.setSelected(false);
				}
				else
				{
					Compensate_btn.setSelected(false);
				}
			}
		});
		Tables_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Tables_btn.isSelected())
				{

					Order_btn.setSelected(false);
					Pay_btn.setSelected(false);
					Compensate_btn.setSelected(false);
					Inventory_btn.setSelected(false);
					AddItem_btn.setSelected(false);
					TimeLog_btn.setSelected(false);
					Discounts_btn.setSelected(false);
					Employees_btn.setSelected(false);
					ClockInOut_btn.setSelected(false);
				}
				else
				{
					Tables_btn.setSelected(false);
				}
			}
		});
		Inventory_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Inventory_btn.isSelected())
				{

					Order_btn.setSelected(false);
					Pay_btn.setSelected(false);
					Compensate_btn.setSelected(false);
					Tables_btn.setSelected(false);
					AddItem_btn.setSelected(false);
					TimeLog_btn.setSelected(false);
					Discounts_btn.setSelected(false);
					Employees_btn.setSelected(false);
					ClockInOut_btn.setSelected(false);
				}
				else
				{
					Inventory_btn.setSelected(false);
				}
			}
		});
		AddItem_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (AddItem_btn.isSelected())
				{
					ClockInOut_btn.setSelected(false);
					Order_btn.setSelected(false);
					Pay_btn.setSelected(false);
					Compensate_btn.setSelected(false);
					Tables_btn.setSelected(false);
					Inventory_btn.setSelected(false);
					TimeLog_btn.setSelected(false);
					Discounts_btn.setSelected(false);
					Employees_btn.setSelected(false);
				}
				else
				{
					AddItem_btn.setSelected(false);
				}
			}
		});
		TimeLog_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (TimeLog_btn.isSelected())
				{

					Order_btn.setSelected(false);
					Pay_btn.setSelected(false);
					Compensate_btn.setSelected(false);
					Tables_btn.setSelected(false);
					Inventory_btn.setSelected(false);
					AddItem_btn.setSelected(false);
					Discounts_btn.setSelected(false);
					Employees_btn.setSelected(false);
					ClockInOut_btn.setSelected(false);
				}
				else
				{
					TimeLog_btn.setSelected(false);
				}
			}
		});
		Discounts_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Discounts_btn.isSelected())
				{

					Order_btn.setSelected(false);
					Pay_btn.setSelected(false);
					Compensate_btn.setSelected(false);
					Tables_btn.setSelected(false);
					Inventory_btn.setSelected(false);
					AddItem_btn.setSelected(false);
					TimeLog_btn.setSelected(false);
					ClockInOut_btn.setSelected(false);
					Employees_btn.setSelected(false);
				}
				else
				{
					Discounts_btn.setSelected(false);
				}
			}
		});
		Employees_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Employees_btn.isSelected())
				{

					Order_btn.setSelected(false);
					Pay_btn.setSelected(false);
					Compensate_btn.setSelected(false);
					Tables_btn.setSelected(false);
					Inventory_btn.setSelected(false);
					AddItem_btn.setSelected(false);
					TimeLog_btn.setSelected(false);
					Discounts_btn.setSelected(false);
					ClockInOut_btn.setSelected(false);
				}
				else
				{
					Employees_btn.setSelected(false);
				}
			}
		});
		
	}

	protected void addMenuItem() {
		MItem item = new MItem();
		item.name = "RandomItem"+(Constants.generateNumber(0, 10000000));
		item.price = 4.20;
		item.description = "This is a random description "+(Constants.generateNumber(0, 10000000));
		item.calories = Constants.generateNumber(500, 4000);
		item.allergens = "wheat, gluten, eggs, everything";
		item.type = 0;
		item.menuType = "entree";
		item.ingredients = "fries:1";
		Menu.add(item);
	}
}
