package order.net.class101.server1.testCase;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import order.net.class101.server1.model.Goods;
import order.net.class101.server1.model.Order;
import order.net.class101.server1.order.OrderServiceImpl;
import order.net.class101.server1.util.Utils;

public class MultiThreadStockTest {

	@Test
	public void 주문_완료_재고_검증() throws InterruptedException {
		int num = 5;
		ExecutorService e = Executors.newFixedThreadPool(num);
		System.out.println(Thread.currentThread().getName());
		Map<String, Goods> map = Utils.buildGoodsList();
		String goodsId = "91008";
		Order orderList = new Order();
		orderList.getGoods().add(Utils.getNewInstance(map.get(goodsId), 3));
		for (int i = 0; i < num; i++) {
			e.execute(() -> {
				new OrderServiceImpl().chkOrderAmount(map, orderList);
			});
		}
		e.awaitTermination(3, TimeUnit.SECONDS);
		e.shutdown();
	}
}
