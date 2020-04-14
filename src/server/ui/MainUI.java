package server.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;

import server.core.CoresManager;
import server.menu.MItem;
import server.menu.Menu;
import server.utils.Constants;
import server.utils.STime;

public class MainUI extends JFrame {

	private static final long serialVersionUID = 4680811856634328038L;

	private JPanel utilityPanel;

	public MenuPanel menuPanel;
	public InventoryPanel inventoryPanel;

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
					setTitle("Seven Guys - Restaurant System has been online for: "
							+ (STime.formatCountdown(STime.getCurrent() + onlineTime)));
				} catch (Throwable e) {
					e.printStackTrace();
				}
			}
		}, 0, 1, TimeUnit.SECONDS);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setBounds(100, 100, 1215, 761);

		// Create Frame to hold Manager Tasks
		utilityPanel = new JPanel();
		utilityPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(utilityPanel);
		utilityPanel.setLayout(null);

		// Menu Panel
		this.menuPanel = new MenuPanel();
		menuPanel.setVisible(false);
		add(menuPanel);
		
		// Inventory Panel
		this.inventoryPanel = new InventoryPanel();
		inventoryPanel.setVisible(false);
		add(inventoryPanel);

		String[] panelNames = { "Clock In/Out", "Order", "Pay", "Compensate", "Tables", "Inventory", "Menu", "Time Log",
				"Discounts", "Employees" };

		JToggleButton[] panelBtns = new JToggleButton[panelNames.length];

		for (int index = 0; index < panelBtns.length; index++) {
			panelBtns[index] = new JToggleButton(panelNames[index]);
			panelBtns[index].setBounds(0, (71 * index) - 3, 232, 74);
			panelBtns[index].setFont(new Font("Tahoma", Font.PLAIN, 30));
			utilityPanel.add(panelBtns[index]);
		}

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
						menuPanel.setVisible(false);
						inventoryPanel.setVisible(false);
						switch (btn.getText()) {
						case "Clock In/Out":
							break;
						case "Order":
							break;
						case "Pay":
							break;
						case "Compensate":
							break;
						case "Tables":
							break;
						case "Inventory":
							inventoryPanel.setVisible(true);
							break;
						case "Menu":
							menuPanel.setVisible(true);
							break;
						case "Time Log":
							break;
						case "Discounts":
							break;
						case "Employees":
							break;
						}
					} else {
						inventoryPanel.setVisible(false);
						menuPanel.setVisible(false);
						btn.setSelected(false);
					}
				}

			});
		}

	}
}
