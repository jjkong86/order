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
public class Goods implements Cloneable{
	private String id;
	private String type;
	private String name;
	private int price;
	private int stock;

	@Override
	public String toString() {
		return id + " " + type + " " + name + " " + price + " " + stock;
	}
}
