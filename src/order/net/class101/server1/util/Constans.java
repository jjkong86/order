package order.net.class101.server1.util;

import lombok.Getter;

@Getter
public enum Constans {
	NotExsistGoodsId("상품번호가 존재하지 않습니다."),
	StocklessThanQuantity("재고보다 많은 수량을 구입할 수 없습니다."),
	NotDupleClass("class type은 한번에 하나만 주문할 수 있습니다."),
	OrderAndQuit("입력(o[order]: 주문, q[quit]: 종료) : "),
	ThanksForOrder("고객님의 주문 감사합니다.");
	
	private String message;

	Constans(String message) {
		this.message = message;
	}
}
