package order.net.class101.server1.model;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class Order {
	private List<Goods> goods = new ArrayList<>();
	private boolean goodsType;
	private long totalPrice;
	private int deliveryCharge;

	@Override
	public String toString() {
		StringJoiner sj = new StringJoiner("\n");
		sj.add("-----------------------------------------------------");
		for (Goods g : goods) {
			StringBuilder sb = new StringBuilder();
			sb.append(g.getName()).append(" - ").append(g.getStock()).append("개");
			sj.add(sb.toString());
		}
		sj.add("-----------------------------------------------------");
		sj.add("주문금액 : " + totalPrice + "원");
		sj.add("-----------------------------------------------------");
		sj.add("지불금액 : " + (totalPrice + deliveryCharge) + "원");
		sj.add("-----------------------------------------------------");
		return sj.toString();
	}
}
