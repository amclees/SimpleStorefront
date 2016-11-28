package simplestorefront.models;

import java.util.LinkedList;
import java.util.List;

public class Cart {
	private List<StoreItem> items;
	
	public Cart() {
		this.items = new LinkedList<StoreItem>();
	}
	
	//Not passed by reference
	public List<StoreItem> getItems() {
		return new LinkedList<StoreItem>(items);
	}
	
	//Not bean ops:
	public void addItem(StoreItem item) {
		this.items.add(item);
	}
	
	public void addItems(StoreItem... items) {
		for(StoreItem item : items) {
			this.addItem(item);
		}
	}
	
	public void removeItem(int id) {
		for(StoreItem item : this.items) {
			if(item.getId() == id) {
				this.items.remove(item);
				return;
			}
		}
	}
	
	public void removeItem(StoreItem item) {
		this.removeItem(item.getId());
	}
	
	public void clear() {
		this.items = new LinkedList<StoreItem>();
	}
	
}
