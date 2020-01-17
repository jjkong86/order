package order.net.class101.server1.order;

import java.util.Map;

import order.net.class101.server1.exception.SoldOutException;
import order.net.class101.server1.model.Goods;
import order.net.class101.server1.model.Order;

public class OrderServiceImpl implements OrderService {

	@Override
	public void order(Map<String, Goods> map, Order orderList) {
		chkDeliveryCharge(orderList);
		chkOrderAmount(map, orderList);
		printAndOrderClear(orderList);
	}

	@Override
	public void chkDeliveryCharge(Order orderList) {
		if (orderList.getTotalPrice() < 50000) {
			orderList.setDeliveryCharge(5000);
		}
	}

	@Override
	public void chkOrderAmount(Map<String, Goods> map, Order orderList) {
		for (Goods orderGoods : orderList.getGoods()) {
			if (orderGoods.isUnlimited())
				continue;

			chkStock(map.get(orderGoods.getId()), orderGoods); // 동기화
		}
	}

	@Override
	public synchronized void chkStock(Goods goods, Goods orderGoods) {
		int stock = goods.getStock() - orderGoods.getStock();
		if (stock < 0) {
			throw new SoldOutException("[id : " + orderGoods.getId() +", stock : "+goods.getStock()+", amount : "+orderGoods.getStock()+ "] 재고가 부족합니다.");
		}
		goods.setStock(stock);
	}

	@Override
	public void printAndOrderClear(Order orderList) {
		System.out.println("주문내역 : ");
		System.out.println(orderList.toString());
		System.out.print("상품번호 : ");

		orderList = new Order();
	}
}
