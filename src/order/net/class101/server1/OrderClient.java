package order.net.class101.server1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringJoiner;

import order.net.class101.server1.cart.CartService;
import order.net.class101.server1.exception.SoldOutException;
import order.net.class101.server1.model.Goods;
import order.net.class101.server1.model.Order;
import order.net.class101.server1.order.OrderService;
import order.net.class101.server1.util.Constans;
import order.net.class101.server1.util.Utils;

public class OrderClient {

	OrderService orderService;
	CartService cartService;
	Map<String, Goods> goodsMap;
	Order orderList;

	OrderClient(OrderService orderService, CartService cartService) {
		this.orderService = orderService;
		this.cartService = cartService;
		goodsMap = Utils.buildGoodsList();
		orderList = new Order();
	}

	public void purchase() throws NumberFormatException, IOException {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			String input;
			System.out.print(Constans.OrderAndQuit.getMessage());

			while (Utils.orderInputChk(br.readLine())) {
				Utils.goodsMapPrint(goodsMap);

				System.out.print("상품번호 : ");
				while (!Utils.quitInputChk((input = br.readLine()))) {
					if (" ".equals(input)) {
						orderService.order(goodsMap, orderList); // 상품 주문
						break;
					}

					Goods selectGoods = goodsMap.get(input.trim());
					if (cartService.cartValidation(selectGoods, orderList)) {
						System.out.print("수량  : ");
						int count = Integer.parseInt(br.readLine().trim());
						if (cartService.chkStockLessThanAmount(selectGoods, count)) {
							cartService.putOrder(selectGoods, count, orderList); // 장바구니에 상품 담기
						}
					}

					System.out.print("상품번호 : ");
				}

				orderList = new Order();
				System.out.print(Constans.OrderAndQuit.getMessage());
			}

			try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
				bw.write(Constans.ThanksForOrder.getMessage());
			}

		} catch (SoldOutException e) {
			e.printStackTrace();
		}
	}
}
