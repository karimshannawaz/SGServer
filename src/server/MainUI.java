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

public class MainUI extends JFrame {

	private static final long serialVersionUID = 4680811856634328038L;
	
	private JPanel contentPane;
	
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
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Add Menu Item");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addMenuItem();
			}
		});
		btnNewButton.setBounds(35, 81, 189, 29);
		contentPane.add(btnNewButton);
	}

	protected void addMenuItem() {
		MItem item = new MItem();
		item.name = "RandomItem"+(Constants.generateNumber(0, 10000000));
		item.price = 4.20;
		item.description = "This is a random description "+(Constants.generateNumber(0, 10000000));
		item.calories = Constants.generateNumber(500, 4000);
		item.allergens = "wheat, gluten, eggs, everything";
		item.type = 0;
		item.ingredients = "fries:1";
		Menu.add(item);
	}

}
