package simplestorefront.models;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;

public class Order {

	private int id;
	private String text;

	public Order(int id, List<StoreItem> cart, String orderedBy) {
		this.id = id;
		double totalPrice = 0;
		for (StoreItem item : cart) {
			totalPrice += item.getQuantity() * item.getPrice();
		}
		this.text = "Order by " + orderedBy + "\n";
		DecimalFormat df = new DecimalFormat("#.##");
		df.setRoundingMode(RoundingMode.HALF_UP);
		this.text += "Order Total: $" + df.format(totalPrice) + "\n";
		for (StoreItem item : cart) {
			this.text += item.getQuantity() + " of item " + item.getName() + " each priced at $" + df.format(item.getPrice())
					+ " with a total item cost of $" + df.format(item.getPrice() * item.getQuantity());
			this.text += "\n";
		}
	}

	public Order(int id, String text) {
		this.id = id;
		this.text = text;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
