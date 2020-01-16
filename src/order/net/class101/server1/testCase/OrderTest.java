package order.net.class101.server1.testCase;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import order.net.class101.server1.cart.CartService;
import order.net.class101.server1.cart.CartServiceImpl;
import order.net.class101.server1.model.Goods;
import order.net.class101.server1.model.Order;
import order.net.class101.server1.order.OrderService;
import order.net.class101.server1.order.OrderServiceImpl;
import order.net.class101.server1.util.Utils;

@RunWith(JUnit4.class)
public class OrderTest {

	static OrderService orderService;
	static CartService cartService;
	static Map<String, Goods> goodsMap;

	@BeforeClass
	public static void init() {
		orderService = new OrderServiceImpl();
		cartService = new CartServiceImpl();
		goodsMap = Utils.buildGoodsList();
		Utils.goodsMapPrint(goodsMap);
	}

	public void putOrderTest(Goods selectGoods, int count, Order orderList) {
		if (cartService.cartValidation(selectGoods, orderList)) {
			if (cartService.chkStockLessThanAmount(selectGoods, count)) {
				cartService.putOrder(selectGoods, count, orderList); // 장바구니에 상품 담기
			}
		}

	}

	private String getGoodsId(Order orderList, String id) {
		for (Goods g : orderList.getGoods()) {
			if (g.getId().equals(id)) {
				return g.getId();
			}
		}
		return "";
	}

	@Test
	public void 상품_존재_여부_체크() {
		String exsistGoodsId = "2344";
		String notExsistGoodsId = "2345";
		assertThat(true, is(cartService.exsistGoods(goodsMap.get(exsistGoodsId)))); // 상품 존재
		assertThat(false, is(cartService.exsistGoods(goodsMap.get(notExsistGoodsId)))); // 존재하지 않음
	}

	@Test
	public void 클래스_1개이상_체크() {
		Goods goods = goodsMap.get("55527");
		Order orderList = new Order();
		cartService.putOrder(goods, 5, orderList);
		assertThat(true, is(cartService.exsistClass(goods, orderList)));
	}

	@Test
	public void 상품_담기() {
		Goods goods1 = goodsMap.get("55527"); // 클래스
		Goods goods2 = goodsMap.get("2344"); // 클래스
		Goods goods3 = goodsMap.get("39712"); // 키트
		Goods goods4 = goodsMap.get("91008"); // 키트
		Order orderList = new Order();

		putOrderTest(goods1, 5, orderList);
		assertThat(goods1.getId(), is(getGoodsId(orderList, goods1.getId())));

		putOrderTest(goods2, 11, orderList); // 클래스는 중복해서 들어갈 수 없음
		assertThat(goods2.getId(), not(getGoodsId(orderList, goods2.getId())));

		putOrderTest(goods3, 5, orderList);
		assertThat(goods3.getId(), is(getGoodsId(orderList, goods3.getId())));

		putOrderTest(goods4, 3, orderList); // 키트는 중복 주문 가능
		assertThat(goods4.getId(), is(getGoodsId(orderList, goods4.getId())));

		assertThat(3, is(orderList.getGoods().size()));
	}

	@Test
	public void 배송비_가격_검증() {
		Goods goods1 = goodsMap.get("39712");
		Order orderList = new Order();
		putOrderTest(goods1, 2, orderList);
		orderService.order(goodsMap, orderList);
		assertThat((35200L + 5000), is(orderList.getTotalPrice() + orderList.getDeliveryCharge()));
		System.out.println("=================\n");

		Goods goods2 = goodsMap.get("39712");
		orderList = new Order();
		putOrderTest(goods2, 5, orderList);
		orderService.order(goodsMap, orderList);
		assertThat((88000L + 0), is(orderList.getTotalPrice() + orderList.getDeliveryCharge()));
	}
}
