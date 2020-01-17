package order.net.class101.server1.testCase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import order.net.class101.server1.exception.SoldOutException;

public class MultiThreadStockTest {

	@Test(expected = SoldOutException.class)
	public void 주문_완료_재고_검증() throws InterruptedException {
		int num = 10;
		ExecutorService e = Executors.newFixedThreadPool(num);
		List<OrderTestRun> list = new ArrayList<>();
		for (int i = 0; i < num; i++) {
			list.add(new OrderTestRun());
		}

		for (OrderTestRun run : list) {
			e.execute(run);
		}
		e.awaitTermination(3, TimeUnit.SECONDS);
		e.shutdown();
	}

}
