package order.net.class101.server1.testCase;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import org.junit.Test;

import order.net.class101.server1.exception.SoldOutException;
import order.net.class101.server1.model.Goods;
import order.net.class101.server1.model.Order;
import order.net.class101.server1.order.OrderServiceImpl;
import order.net.class101.server1.util.Utils;

public class MultiThreadStockTest {

	@Test(expected = SoldOutException.class)
	public void 주문_완료_재고_검증() throws InterruptedException, ExecutionException {
		int num = 11;
		ExecutorService e = Executors.newFixedThreadPool(num);
		Map<String, Goods> map = Utils.buildGoodsList();
		String goodsId = "91008";
		Order orderList = new Order();
		orderList.getGoods().add(Utils.getNewInstance(map.get(goodsId), 1));
		AtomicBoolean success = new AtomicBoolean(true);
		for (int i = 0; i < num; i++) {
			e.execute(() -> {
				try {
					new OrderServiceImpl().chkOrderAmount(map, orderList);
				} catch (Exception e1) {
					e1.printStackTrace();
					success.set(false);
				}
			});
		}

		e.awaitTermination(3, TimeUnit.SECONDS);

		if (!success.get()) {
			throw new SoldOutException("실패");
		}

	}
}
