package order.net.class101.server1;

import java.io.IOException;

import order.net.class101.server1.cart.CartServiceImpl;
import order.net.class101.server1.order.OrderServiceImpl;

public class OrderTest {
	public static void main(String[] args) throws NumberFormatException, IOException {
		OrderClient o = new OrderClient(new OrderServiceImpl(), new CartServiceImpl());
		o.purchase();
	}
}
