package order.net.class101.server1.cart;

import order.net.class101.server1.model.Goods;
import order.net.class101.server1.model.GoodsType;
import order.net.class101.server1.model.Order;
import order.net.class101.server1.util.Constans;
import order.net.class101.server1.util.Utils;

public class CartServiceImpl implements CartService {

	@Override
	public void putOrder(Goods selectGoods, int count, Order orderList) {
		if (!chkStockLessThanAmount(selectGoods, count))
			return;

		orderList.getGoods().add(Utils.getNewInstance(selectGoods, count));
		if (selectGoods.getType() == GoodsType.CLASS) {
			orderList.setClassInner(true);
		}
		if (count > 0) {
			orderList.setTotalPrice(selectGoods.getPrice() * count);
		}
	}

	@Override
	public boolean exsistGoods(Goods selectGoods) {
		if (selectGoods == null) {
			System.out.println(Constans.NotExsistGoodsId.getMessage());
			return false;
		}
		return true;
	}

	@Override
	public boolean exsistClass(Goods selectGoods, Order orderList) {
		if (orderList.isClassInner() && selectGoods.getType() == GoodsType.CLASS) {
			System.out.println(Constans.NotDupleClass.getMessage());
			return true;
		}
		return false;
	}

	@Override
	public boolean chkStockLessThanAmount(Goods selectGoods, int count) {
		if (selectGoods.getStock() < count) {
			System.out.println(Constans.StocklessThanQuantity.getMessage());
			return false;
		}
		return true;
	}

	@Override
	public boolean cartValidation(Goods selectGoods, Order orderList) {
		if (exsistGoods(selectGoods) && !exsistClass(selectGoods, orderList)) {
			return true;
		}
		return false;
	}

}
