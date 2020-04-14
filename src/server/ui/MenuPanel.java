package server.ui;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import server.menu.MItem;
import server.menu.Menu;
import server.utils.Constants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuPanel extends JPanel {

	private static final long serialVersionUID = -7728688801223513408L;
	
	public JTextArea currMenu;
	public StringBuilder menuAsTxt = new StringBuilder();

	/**
	 * Create the panel.
	 */
	public MenuPanel() {
		super();
		setBounds(233, 0, 962, 710);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Current Menu:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblNewLabel.setBounds(388, 13, 192, 32);
		add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 62, 962, 280);
		add(scrollPane);
		
		currMenu = new JTextArea();
		currMenu.setEditable(false);
		scrollPane.setViewportView(currMenu);
		
		
	}
}
