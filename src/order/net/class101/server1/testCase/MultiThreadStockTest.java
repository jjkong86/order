package order.net.class101.server1.testCase;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Test;

import order.net.class101.server1.exception.SoldOutException;
import order.net.class101.server1.model.Goods;
import order.net.class101.server1.model.Order;
import order.net.class101.server1.order.OrderServiceImpl;
import order.net.class101.server1.util.Utils;

public class MultiThreadStockTest {

	@Test(expected = SoldOutException.class)
	public void 주문_완료_재고_검증() throws InterruptedException {
		int num = 11;
		ExecutorService e = Executors.newFixedThreadPool(num);
		Map<String, Goods> map = Utils.buildGoodsList();
		String goodsId = "91008";
		Order orderList = new Order();
		orderList.getGoods().add(Utils.getNewInstance(map.get(goodsId), 1));
		try {
			for (int i = 0; i < num; i++) {
				Future<?> f = e.submit(() -> {
					new OrderServiceImpl().chkOrderAmount(map, orderList);
				});
				f.get();
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			throw new SoldOutException(e1.getMessage());
		}
	}
}
