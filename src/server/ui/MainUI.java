package server.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;

import server.Global;
import server.core.CoresManager;
import server.utils.STime;

/**
 * 
 * @author Karimshan
 *
 */
public class MainUI extends JFrame {

	private static final long serialVersionUID = 4680811856634328038L;

	private JPanel utilityPanel;

	public InfoPanel infoPanel;
	public ClockPanel clockPanel;
	public CompensatePanel compensatePanel;
	public MenuPanel menuPanel;
	public InventoryPanel inventoryPanel;
	public TimelogPanel timelogPanel;
	public DiscountPanel discountPanel;
	public EmployeePanel employeePanel;
	public TablesPanel tablesPanel;
	public KitchenPanel kitchenPanel;

	public static long onlineTime;

	/**
	 * Create the frame.
	 */
	public MainUI() {
		
		/*
		for(int i = 0; i < 10; i++) {
			User u = new User();
			int rand = Constants.generateNumber(1000, 30279193);
			u.createEmployee("rand"+rand+"", "Name "+rand, "waitstaff", "rand"+rand);
			UserLoader.saveUser(u, true);
		}
		System.out.println("Created 10 random employees");
		*/

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setBounds(100, 100, 1215, 761);

		// Create Frame to hold Manager Tasks
		utilityPanel = new JPanel();
		utilityPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(utilityPanel);
		utilityPanel.setLayout(null);
		
		// Info Panel
		this.infoPanel = new InfoPanel();
		infoPanel.setVisible(false);
		getContentPane().add(infoPanel);

		// Clock Panel
		this.clockPanel = new ClockPanel();
		clockPanel.setVisible(false);
		getContentPane().add(clockPanel);
		
		// Kitchen Panel
		this.kitchenPanel = new KitchenPanel();
		kitchenPanel.setVisible(false);
		getContentPane().add(kitchenPanel);
		
		// Compensate Panel
		this.compensatePanel = new CompensatePanel();
		compensatePanel.setVisible(false);
		getContentPane().add(compensatePanel);
		
		// Menu Panel
		this.menuPanel = new MenuPanel();
		menuPanel.setVisible(false);
		getContentPane().add(menuPanel);
		
		// Inventory Panel
		this.inventoryPanel = new InventoryPanel();
		inventoryPanel.setVisible(false);
		getContentPane().add(inventoryPanel);
		
		// Timelog Panel
		this.timelogPanel = new TimelogPanel();
		timelogPanel.setVisible(false);
		getContentPane().add(timelogPanel);
		
		// Discount Panel
		this.discountPanel = new DiscountPanel();
		discountPanel.setVisible(false);
		getContentPane().add(discountPanel);
		
		// Employee Panel
		this.employeePanel = new EmployeePanel();
		employeePanel.setVisible(false);
		getContentPane().add(employeePanel);
		
		// Tables Panel
		this.tablesPanel = new TablesPanel();
		tablesPanel.setVisible(false);
		getContentPane().add(tablesPanel);


		String[] panelNames = { 
				"Information", "Clock In/Out", "Kitchen Orders", 
				"Compensate", "Tables", "Inventory", "Menu", "Time Log",
				"Discounts", "Employees" };

		JToggleButton[] panelBtns = new JToggleButton[panelNames.length];

		for (int index = 0; index < panelBtns.length; index++) {
			panelBtns[index] = new JToggleButton(panelNames[index]);
			panelBtns[index].setBounds(0, (71 * index) - 3, 232, 74);
			panelBtns[index].setFont(new Font("Tahoma", Font.PLAIN, 30));
			utilityPanel.add(panelBtns[index]);
		}
		
		panelBtns[0].setSelected(true);
		infoPanel.setVisible(true);
		

		for (int index = 0; index < panelBtns.length; index++) {
			JToggleButton btn = panelBtns[index];
			btn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if (btn.isSelected()) {
						for (int i = 0; i < panelBtns.length; i++) {
							if (btn.getText().equals(panelBtns[i].getText())) {
								continue;
							}
							panelBtns[i].setSelected(false);
						}
						
						//make all panels initially not visible
						infoPanel.setVisible(false);
						clockPanel.setVisible(false);
						kitchenPanel.setVisible(false);
						compensatePanel.setVisible(false);
						menuPanel.setVisible(false);
						inventoryPanel.setVisible(false);
						timelogPanel.setVisible(false);
						discountPanel.setVisible(false);
						employeePanel.setVisible(false);
						tablesPanel.setVisible(false);
						
						//switch statement to make user selected page visible 
						switch (btn.getText()) {
						case "Information":
							infoPanel.setVisible(true);
							break;
						case "Clock In/Out":
							clockPanel.setVisible(true);
							break;
						case "Kitchen Orders":
							kitchenPanel.setVisible(true);
							break;
						case "Compensate":
							compensatePanel.setVisible(true);
							break;
						case "Tables":
							tablesPanel.setVisible(true);
							break;
						case "Inventory":
							inventoryPanel.setVisible(true);
							break;
						case "Menu":
							menuPanel.setVisible(true);
							break;
						case "Time Log":
							timelogPanel.setVisible(true);
							break;
						case "Discounts":
							discountPanel.setVisible(true);
							break;
						case "Employees":
							employeePanel.setVisible(true);
							break;
						}
					} else {
						infoPanel.setVisible(false);
						clockPanel.setVisible(false);
						kitchenPanel.setVisible(false);
						compensatePanel.setVisible(false);
						inventoryPanel.setVisible(false);
						menuPanel.setVisible(false);
						timelogPanel.setVisible(false);
						discountPanel.setVisible(false);
						employeePanel.setVisible(false);
						tablesPanel.setVisible(false);
						btn.setSelected(false);
					}
				}

			});
		}

		CoresManager.slowExecutor.scheduleWithFixedDelay(new Runnable() {
			@Override
			public void run() {
				try {
					onlineTime += 1000;
					setTitle("Seven Guys - Restaurant System has been online for: "
							+ (STime.formatCountdown(STime.getCurrent() + onlineTime)));
					infoPanel.updateLabels();
//					System.out.println("Users: "+Arrays.toString(Global.getUsers().toArray()));
				} catch (Throwable e) {
					e.printStackTrace();
				}
			}
		}, 0, 1, TimeUnit.SECONDS);
		
		/**
		 * Sets up our executor to update the time in the employee timelog
		 * every minute, as well as clocking in/out..
		 */
		CoresManager.slowExecutor.scheduleWithFixedDelay(new Runnable() {
			@Override
			public void run() {
				try {
					Date fullDate = new Date();
					String format = DateFormat.getInstance().format(fullDate);
					clockPanel.dateLbl.setText("The date/time is currently: " +format);
					timelogPanel.dateLbl.setText("Current time: "+format);
				} catch (Throwable e) {
					e.printStackTrace();
				}
			}
		}, 0, 1, TimeUnit.MINUTES);
		
	}
}
