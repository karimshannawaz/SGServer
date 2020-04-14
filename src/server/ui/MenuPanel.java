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
import javax.swing.JTextField;
import javax.swing.JToggleButton;

public class MenuPanel extends JPanel {

	private static final long serialVersionUID = -7728688801223513408L;

	public JTextArea currMenu;
	public StringBuilder menuAsTxt;
	private JTextField textField;
	
	public JToggleButton[] mItemBtns;

	/**
	 * Create the panel.
	 */
	public MenuPanel() {
		super();
		setBounds(233, 0, 962, 710);
		setLayout(null);

		JLabel lblNewLabel = new JLabel("Current Menu:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblNewLabel.setBounds(386, 13, 154, 32);
		add(lblNewLabel);

		refreshMenuItemButtons();

		for(int index = 0; index < mItemBtns.length; index++) {
			JToggleButton btn = mItemBtns[index];
			btn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if(btn.isSelected()) {
						for(int i = 0; i < mItemBtns.length; i++) {
							if(btn.getText().equals(mItemBtns[i].getText())) {
								continue;
							}
							mItemBtns[i].setSelected(false);
						}
					}
					else {
						btn.setSelected(false);
					}
				}

			});
		}

		/*
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 100, 962, 280);
		add(scrollPane);

		currMenu = new JTextArea();
		currMenu.setEditable(false);
		scrollPane.setViewportView(currMenu);
		currMenu.setFont(new Font("Tahoma", Font.PLAIN, 19));

		JLabel lblSyntaxIndexName = new JLabel("Syntax: Index, Name, Price, Description, "
				+ "Calories, Allergens, Type, Menu Type, Ingredients");
		lblSyntaxIndexName.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblSyntaxIndexName.setBounds(79, 45, 857, 32);
		add(lblSyntaxIndexName);
		 */

		JButton btnNewButton = new JButton("Remove Item");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnNewButton.setBounds(682, 609, 154, 58);
		add(btnNewButton);

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 19));
		textField.setBounds(689, 544, 137, 52);
		add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Remove an Item from the Menu:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblNewLabel_1.setBounds(626, 492, 293, 58);
		add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Add Menu Item:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(689, 452, 137, 32);
		add(lblNewLabel_2);

		//refreshMenuText();

	}

	private void refreshMenuItemButtons() {
		mItemBtns = new JToggleButton[Menu.instance.size()];

		for(int index = 0; index < mItemBtns.length; index++) {
			mItemBtns[index] = new JToggleButton(Menu.instance.get(index).name);
			mItemBtns[index].setBounds(0, (32 * index) + 58, 166, 32);
			mItemBtns[index].setFont(new Font("Tahoma", Font.PLAIN, 15));
			add(mItemBtns[index]);
		}
	}

	private void refreshMenuText() {
		menuAsTxt = new StringBuilder();
		for(MItem item : Menu.instance.values()) {
			String toShow = item.getIndex()+" - "+item.name+", $"+item.price+", "+
					item.description+", "+item.calories+", "+item.allergens+", "+
					item.type+", "+item.menuType+", "+item.ingredients;
			menuAsTxt.append(toShow).append("\n");
		}
		currMenu.setText(menuAsTxt.toString());
	}
}
