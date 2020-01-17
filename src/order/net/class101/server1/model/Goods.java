package order.net.class101.server1.model;

public class Goods {
	private String id;
	private GoodsType type;
	private String name;
	private int price;
	private int stock;
	private boolean unlimited;

	public Goods() {
	}

	public Goods(String id, GoodsType type, String name, int price, int stock, boolean unlimited) {
		this.id = id;
		this.type = type;
		this.name = name;
		this.price = price;
		this.stock = stock;
		this.unlimited = unlimited;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public GoodsType getType() {
		return type;
	}

	public void setType(GoodsType type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public boolean isUnlimited() {
		return unlimited;
	}

	public void setUnlimited(boolean unlimited) {
		this.unlimited = unlimited;
	}

	@Override
	public String toString() {
		return id + " " + type.getType() + " " + name + " " + price + " " + stock;
	}

	public static class Builder implements Buildable<Goods> {
		private String id;
		private GoodsType type;
		private String name;
		private int price;
		private int stock;
		private boolean unlimited;

		public Builder() {
		}

		@Override
		public Goods build() {
			return new Goods(id, type, name, price, stock, unlimited);
		}

		public Builder id(String id) {
			this.id = id;
			return this;
		}

		public Builder type(GoodsType type) {
			this.type = type;
			return this;
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder price(int price) {
			this.price = price;
			return this;
		}

		public Builder stock(int stock) {
			this.stock = stock;
			return this;
		}

		public Builder unlimited(boolean unlimited) {
			this.unlimited = unlimited;
			return this;
		}
	}
}
