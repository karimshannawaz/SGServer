package server.menu;


/**
 * Represents a single menu item ingredient
 * 
 * @author Karimshan
 *
 */
public class Ingredient {
	
	private String name;
	private boolean editable;
	private int qty;
	private String substitution;
	
	public Ingredient(String name, int qty, boolean editable) {
		this.setName(name);
		this.setQty(qty);
		this.setEditable(editable);
		this.addSub("n");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public String getSub() {
		return substitution;
	}

	public void addSub(String sub) {
		this.substitution = sub;
	}
	
	@Override
	public String toString() {
		return name+":"+qty+":"+(editable ? "t" : "f")+":"+substitution;
	}

}
