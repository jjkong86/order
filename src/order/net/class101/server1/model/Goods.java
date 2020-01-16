package order.net.class101.server1.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Goods {
	private String id;
	private GoodsType type;
	private String name;
	private int price;
	private int stock;
	private boolean unlimited;

	@Override
	public String toString() {
		return id + " " + type.getType() + " " + name + " " + price + " " + stock;
	}
}
