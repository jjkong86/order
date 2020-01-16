package order.net.class101.server1.testCase;

import java.util.Map;

import order.net.class101.server1.model.Goods;
import order.net.class101.server1.model.Order;
import order.net.class101.server1.order.OrderServiceImpl;
import order.net.class101.server1.util.Utils;

public class OrderTestRun implements Runnable {
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName());
		Map<String, Goods> map = Utils.buildGoodsList();
		String goodsId = "91008";
		Order orderList = new Order();
		orderList.getGoods().add(Utils.getNewInstance(map.get(goodsId), 3));
		new OrderServiceImpl().chkOrderAmount(map, orderList);
		System.out.println("상품 수량 : " + map.get(goodsId).getStock());
	}
}
