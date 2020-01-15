package order.net.class101.server1.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Order {
	private List<Goods> goods = new ArrayList<>();
	private boolean goodsType;
	private int totalPrice;
}
