package order.net.class101.server1.cart;

import order.net.class101.server1.model.Goods;
import order.net.class101.server1.model.Order;

public interface CartService {
	void putOrder(Goods selectGoods, int count, Order orderList);
	boolean cartValidation(Goods selectGoods, Order orderList);
	boolean exsistGoods(Goods selectGoods);
	boolean exsistClass(Goods selectGoods, Order orderList);
	boolean chkStockLessThanAmount(Goods selectGoods, int count);
}
