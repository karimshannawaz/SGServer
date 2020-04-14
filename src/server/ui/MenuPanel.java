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
import server.utils.JFrameUtils;

/**
 * Represents the menu panel which lets managers see the current menu, edit, add or remove
 * menu items and update it in real time for the customer kiosks.
 * SERVER SIDED
 * 
 * @author Karimshan
 *
 */
public class MenuPanel extends JPanel {

	private static final long serialVersionUID = -7728688801223513408L;

	public JTextArea currMenu;
	public StringBuilder menuAsTxt;

	public JButton deleteBtn;

	public JToggleButton[] mItemBtns;
	
	private JTextField name;
	private JTextField price;
	private JTextField desc;
	private JTextField calories;
	private JTextField allergens;
	private JTextField type;
	private JTextField menuType;
	private JTextField ingredients;
	
	int currIndex = 0;

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

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(215, 58, 735, 314);
		add(scrollPane);

		currMenu = new JTextArea();
		currMenu.setEditable(false);
		scrollPane.setViewportView(currMenu);
		currMenu.setFont(new Font("Tahoma", Font.PLAIN, 19));

		deleteBtn = new JButton("Delete <dynamic>");
		deleteBtn.setSelected(false);
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteMenuItem(currIndex);
			}
		});
		deleteBtn.setFont(new Font("Tahoma", Font.PLAIN, 19));
		deleteBtn.setBounds(655, 7, 295, 44);
		add(deleteBtn);

		JLabel lblNewLabel_2 = new JLabel("Add/Update Menu Item:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2.setBounds(453, 380, 218, 32);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Name:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(325, 424, 62, 32);
		add(lblNewLabel_3);
		
		name = new JTextField();
		name.setFont(new Font("Tahoma", Font.PLAIN, 15));
		name.setBounds(386, 425, 201, 31);
		add(name);
		name.setColumns(10);
		
		JLabel lblNewLabel_3_1 = new JLabel("Price:");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_3_1.setBounds(609, 424, 62, 32);
		add(lblNewLabel_3_1);
		
		price = new JTextField();
		price.setFont(new Font("Tahoma", Font.PLAIN, 15));
		price.setColumns(10);
		price.setBounds(669, 425, 137, 31);
		add(price);
		
		desc = new JTextField();
		desc.setFont(new Font("Tahoma", Font.PLAIN, 15));
		desc.setColumns(10);
		desc.setBounds(422, 468, 506, 31);
		add(desc);
		
		JLabel descLbl = new JLabel("Description:");
		descLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		descLbl.setBounds(325, 465, 129, 32);
		add(descLbl);
		
		JLabel calLbl = new JLabel("Calories:");
		calLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		calLbl.setBounds(325, 507, 129, 32);
		add(calLbl);
		
		calories = new JTextField();
		calories.setFont(new Font("Tahoma", Font.PLAIN, 15));
		calories.setColumns(10);
		calories.setBounds(403, 507, 114, 31);
		add(calories);
		
		allergens = new JTextField();
		allergens.setFont(new Font("Tahoma", Font.PLAIN, 15));
		allergens.setColumns(10);
		allergens.setBounds(609, 507, 270, 31);
		add(allergens);
		
		JLabel allergenLbl = new JLabel("Allergens:");
		allergenLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		allergenLbl.setBounds(529, 507, 88, 32);
		add(allergenLbl);
		
		JLabel typeLbl = new JLabel("Type:");
		typeLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		typeLbl.setBounds(325, 545, 62, 32);
		add(typeLbl);
		
		type = new JTextField();
		
		type.setFont(new Font("Tahoma", Font.PLAIN, 15));
		type.setColumns(10);
		type.setBounds(386, 545, 118, 31);
		add(type);
		
		JLabel menuTypeLbl = new JLabel("Menu Type:");
		menuTypeLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		menuTypeLbl.setBounds(516, 545, 101, 32);
		add(menuTypeLbl);
		
		menuType = new JTextField();
		menuType.setFont(new Font("Tahoma", Font.PLAIN, 15));
		menuType.setColumns(10);
		menuType.setBounds(619, 545, 143, 31);
		add(menuType);
		
		JLabel ingLbl = new JLabel("Ingredients:");
		ingLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		ingLbl.setBounds(325, 584, 101, 32);
		add(ingLbl);
		
		ingredients = new JTextField();
		ingredients.setFont(new Font("Tahoma", Font.PLAIN, 15));
		ingredients.setColumns(10);
		ingredients.setBounds(422, 584, 506, 31);
		add(ingredients);
		
		JButton btnNewButton_1 = new JButton("Add to Menu");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addToMenu();
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnNewButton_1.setBounds(637, 640, 180, 44);
		add(btnNewButton_1);

		currIndex = 0;
		deleteBtn.setVisible(true);
		deleteBtn.setText("Delete "+mItemBtns[0].getText());
		
		JButton updateItemBtn = new JButton("Update Item");
		updateItemBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateItem(currIndex);
			}
		});
		updateItemBtn.setFont(new Font("Tahoma", Font.PLAIN, 19));
		updateItemBtn.setBounds(347, 640, 180, 44);
		add(updateItemBtn);
		
		refreshMenuText(0);
		mItemBtns[0].setSelected(true);
		refreshDefaultFields(true);
	}

	private void refreshDefaultFields(boolean currInd) {
		MItem item = Menu.instance.get(currIndex);
		name.setText(currInd ? item.name : "");
		price.setText(currInd ? ""+item.price : "4.99");
		desc.setText(currInd ? item.description : "Try our newest creation today and let us know what you think!");
		calories.setText(currInd ? ""+item.calories : "500");
		allergens.setText(currInd ? item.allergens : "none");
		type.setText(currInd ? item.type : "default");
		menuType.setText(currInd ? item.menuType : "entree");
		ingredients.setText(currInd ? item.ingredients : "beef_patty:1,lettuce:3,tomato:1");
	}

	protected void updateItem(int index) {
		if(name.getText().equals("") || name.getText().equals(null) || 
				ingredients.getText().equals("") || ingredients.getText().equals(null)) {
			JFrameUtils.showMessage("Menu Editor", "Invalid name or ingredients entered, please try again.");
			return;
		}
		
		if(!(name.getText().equalsIgnoreCase(Menu.instance.get(index).name))) {
			JFrameUtils.showMessage("Menu Editor", "This item does not exist in the menu. Please re-enter the name.");
			return;
		}
		
		if(price.getText().equals(""))
			price.setText("4.99");
		if(desc.getText().equals(""))
			desc.setText("Try our newest creation today and let us know what you think!");
		if(type.getText().equals(""))
			type.setText("default");
		if(menuType.getText().equals(""))
			menuType.setText("entree");
		if(allergens.getText().equals(""))
			allergens.setText("none");
		if(calories.getText().equals(""))
			calories.setText("500");
		
		
		Menu.instance.get(index).name = name.getText();
		Menu.instance.get(index).price = Double.parseDouble(price.getText());
		Menu.instance.get(index).description = desc.getText();
		Menu.instance.get(index).type = type.getText();
		Menu.instance.get(index).menuType = menuType.getText();
		Menu.instance.get(index).allergens = allergens.getText();
		Menu.instance.get(index).calories = Integer.parseInt(calories.getText());
		Menu.instance.get(index).ingredients = ingredients.getText();
		
		refreshMenuItemButtons();
		mItemBtns[index].setSelected(true);
		
		refreshMenuText(index);
		currIndex = index;
		deleteBtn.setVisible(true);
		deleteBtn.setText("Delete "+mItemBtns[index].getText());
		
		JFrameUtils.showMessage("Menu Editor", "Successfully UPDATED item: "+name.getText()+" at index: "+index);
	}

	protected void addToMenu() {
		if(name.getText().equals("") || name.getText().equals(null) || 
				ingredients.getText().equals("") || ingredients.getText().equals(null)) {
			JFrameUtils.showMessage("Menu Editor", "Invalid name or ingredients entered, please try again.");
			return;
		}
		for(MItem item : Menu.instance) {
			if(item.name.equalsIgnoreCase(name.getText())) {
				JFrameUtils.showMessage("Menu Editor", "This item already exists in the menu.");
				return;
			}
		}
		/*
		Object[] validTypes = { "vegan", "vegetarian", "default" };
		String[] validMenuTypes = { "entree", "dessert", "drink", "side" };
		String[] validAllergens = { "none", "milk", "eggs", "fish", 
			"crustacean shellfish", "tree nuts", "peanuts", "wheat", "soybean" };
		*/
		if(price.getText().equals(""))
			price.setText("4.99");
		if(desc.getText().equals(""))
			desc.setText("Try our newest creation today and let us know what you think!");
		if(type.getText().equals(""))
			type.setText("default");
		if(menuType.getText().equals(""))
			menuType.setText("entree");
		if(allergens.getText().equals(""))
			allergens.setText("none");
		if(calories.getText().equals(""))
			calories.setText("500");
		
		MItem newMI = new MItem();
		newMI.name = name.getText();
		newMI.price = Double.parseDouble(price.getText());
		newMI.description = desc.getText();
		newMI.type = type.getText();
		newMI.menuType = menuType.getText();
		newMI.allergens = allergens.getText();
		newMI.calories = Integer.parseInt(calories.getText());
		newMI.ingredients = ingredients.getText();
		Menu.add(newMI);
		
		name.setText("");
		int index = Menu.instance.size() - 1;
		refreshMenuItemButtons();
		mItemBtns[currIndex + 1].setSelected(false);
		mItemBtns[index].setSelected(true);
		
		refreshMenuText(index);
		currIndex = index;
		deleteBtn.setVisible(true);
		deleteBtn.setText("Delete "+mItemBtns[index].getText());
		
		JFrameUtils.showMessage("Menu Editor", "Successfully added item: "+newMI.name+" at index: "+index);
	}

	private void refreshMenuItemButtons() {
		if(mItemBtns != null) {
			for(int i = mItemBtns.length - 1; i >= 0; i--) {
				this.remove(mItemBtns[i]);
				this.repaint();
			}
		}
		mItemBtns = new JToggleButton[Menu.instance.size()];

		System.out.println("Index is: "+mItemBtns.length);
		for (int index = 0; index < mItemBtns.length; index++) {
			mItemBtns[index] = new JToggleButton(Menu.instance.get(index).name);
			mItemBtns[index].setBounds(0, (35 * index) + 58, 208, 32);
			mItemBtns[index].setFont(new Font("Tahoma", Font.BOLD, 15));
			add(mItemBtns[index]);
		}
		
		for (int index = 0; index < mItemBtns.length; index++) {
			JToggleButton btn = mItemBtns[index];
			final int tempIndex = index;
			btn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if (btn.isSelected()) {
						refreshMenuText(tempIndex);
						currIndex = tempIndex;
						deleteBtn.setVisible(true);
						deleteBtn.setText("Delete "+btn.getText());
						for (int i = 0; i < mItemBtns.length; i++) {
							if (btn.getText().equals(mItemBtns[i].getText())) {
								continue;
							}
							mItemBtns[i].setSelected(false);
						}
						refreshDefaultFields(true);
					} else {
						refreshDefaultFields(false);
						currIndex = -1;
						deleteBtn.setVisible(false);
						btn.setSelected(false);
						currMenu.setText("");
					}
				}

			});
		}
	}

	private void refreshMenuText(int index) {
		menuAsTxt = new StringBuilder();
		MItem item = Menu.instance.get(index);

		menuAsTxt.append("\n");
		menuAsTxt.append("Name: "+item.name+"\n");
		menuAsTxt.append("Price: $"+item.price+"\n");
		menuAsTxt.append("Description: "+item.description+"\n");
		menuAsTxt.append("Calories: "+item.calories+"\n");
		menuAsTxt.append("Allergens: "+item.allergens+"\n");
		menuAsTxt.append("Type: "+item.type+"\n");
		menuAsTxt.append("Menu Type: "+item.menuType+"\n");
		menuAsTxt.append("Ingredients (name:qty): "+item.ingredients+"\n");

		currMenu.setText(menuAsTxt.toString());
	}
	
	private void deleteMenuItem(int index) {
		boolean option = JFrameUtils.confirmDialog("Menu Editor", "Do you really want to delete \""+
			mItemBtns[index].getText()+"\" from the menu?");
		String oldItem = mItemBtns[index].getText();
		if(option) {
			Menu.remove(index);
			
			name.setText("");
			refreshMenuItemButtons();
			mItemBtns[0].setSelected(true);
			
			refreshMenuText(0);
			currIndex = 0;
			deleteBtn.setVisible(true);
			deleteBtn.setText("Delete "+mItemBtns[0].getText());
			
			JFrameUtils.showMessage("Menu Editor", "Successfully deleted item: "+oldItem+" at index: "+index);
		}
	}
}
