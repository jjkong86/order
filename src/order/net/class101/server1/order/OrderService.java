package order.net.class101.server1.order;

import java.util.Map;

import order.net.class101.server1.model.Goods;
import order.net.class101.server1.model.Order;

public interface OrderService {
	public void order(Map<String, Goods> map, Order orderList);

	public void chkDeliveryCharge(Order orderList);

	public void chkOrderAmount(Map<String, Goods> map, Order orderList);

	public void printAndOrderClear(Order orderList);

	public int chkStock(Map<String, Goods> map, Goods goods, Goods orderGoods);
}
