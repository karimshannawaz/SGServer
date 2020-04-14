package server.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

import server.menu.MItem;
import server.menu.Menu;

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

		for (int index = 0; index < mItemBtns.length; index++) {
			JToggleButton btn = mItemBtns[index];
			final int tempIndex = index;
			btn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if (btn.isSelected()) {
						for (int i = 0; i < mItemBtns.length; i++) {
							if (btn.getText().equals(mItemBtns[i].getText())) {
								continue;
							}
							mItemBtns[i].setSelected(false);
						}
						refreshMenuText(tempIndex);
					} else {
						btn.setSelected(false);
						currMenu.setText("");
					}
				}

			});
		}

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(215, 58, 735, 314);
		add(scrollPane);

		currMenu = new JTextArea();
		currMenu.setEditable(false);
		scrollPane.setViewportView(currMenu);
		currMenu.setFont(new Font("Tahoma", Font.PLAIN, 19));

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

		refreshMenuText(0);
		mItemBtns[0].setSelected(true);

	}

	private void refreshMenuItemButtons() {
		mItemBtns = new JToggleButton[Menu.instance.size()];

		for (int index = 0; index < mItemBtns.length; index++) {
			mItemBtns[index] = new JToggleButton(Menu.instance.get(index).name);
			mItemBtns[index].setBounds(0, (35 * index) + 58, 208, 32);
			mItemBtns[index].setFont(new Font("Tahoma", Font.BOLD, 15));
			add(mItemBtns[index]);
		}
	}

	private void refreshMenuText(int index) {
		menuAsTxt = new StringBuilder();
		MItem item = Menu.instance.get(index);

		menuAsTxt.append("Name: " + item.name + "\n");
		menuAsTxt.append("Price: $" + item.price + "\n");
		menuAsTxt.append("Description: " + item.description + "\n");
		menuAsTxt.append("Calories: " + item.calories + "\n");
		menuAsTxt.append("Allergens: " + item.allergens + "\n");
		menuAsTxt.append("Type: " + item.type + " ("
				+ (item.type == 2 ? "Vegan" : item.type == 1 ? "Vegetarian" : "Default") + ")\n");
		menuAsTxt.append("Menu Type: " + item.menuType + "\n");
		menuAsTxt.append("Ingredients (name:qty): " + item.ingredients + "\n");

		menuAsTxt.append("\n");

		currMenu.setText(menuAsTxt.toString());
	}
}
