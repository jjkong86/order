package order.net.class101.server1.model;

public enum GoodsType {
	CLASS("클래스"), KIT("키트");
	private String type;

	GoodsType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
}
