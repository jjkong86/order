package order.net.class101.server1.model;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Order {
	private List<Goods> goods = new ArrayList<>();
	private boolean classInner;
	private long totalPrice;
	private int deliveryCharge;

	public List<Goods> getGoods() {
		return goods;
	}

	public void setGoods(List<Goods> goods) {
		this.goods = goods;
	}

	public boolean isClassInner() {
		return classInner;
	}

	public void setClassInner(boolean classInner) {
		this.classInner = classInner;
	}

	public long getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(long totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getDeliveryCharge() {
		return deliveryCharge;
	}

	public void setDeliveryCharge(int deliveryCharge) {
		this.deliveryCharge = deliveryCharge;
	}

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
