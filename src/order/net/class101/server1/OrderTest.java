package order.net.class101.server1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringJoiner;

import order.net.class101.server1.exception.SoldOutException;
import order.net.class101.server1.model.Goods;
import order.net.class101.server1.model.Order;
import order.net.class101.server1.util.Utils;

public class OrderTest {
	static final String INPUT_FILE_NAME = "productList";

	public static void main(String[] args) throws FileNotFoundException, IOException {
		Map<String, Goods> map = new HashMap<>();
		try (BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE_NAME))) {
			while (br.ready()) {
				String[] split = Utils.readLineSplit(br.readLine());
				if (split.length < 1)
					continue;
				Goods goods = Utils.getInstance(split);
				map.put(split[0], goods);
			}
		}

		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			String input;
			String[] quit = new String[] { "q", "quit" };
			String[] order = new String[] { "o", "order" };
			System.out.print("입력(o[order]: 주문, q[quit]: 종료) : ");
			List<Goods> list = new ArrayList<>();
			Order orderList = new Order();
			while (inputChk(order, br.readLine())) {
				print(map);
				System.out.print("상품번호 : ");
				while (!inputChk(quit, (input = br.readLine()))) {
					if (" ".equals(input)) {
						System.out.println("주문내역 : ");
						if (orderList.getTotalPrice() < 50000) {
							orderList.setTotalPrice(5000);
						}
						for (Goods g : orderList.getGoods()) {
							Goods temp = map.get(g.getId());
							temp.setStock(temp.getStock() - g.getStock());
						}

						System.out.println(orderList.toString());
						orderList = new Order();
						System.out.print("상품번호 : ");
						break;
					}

					Goods temp = map.get(input.trim());
					if (temp != null) {
						if (orderList.isGoodsType() && temp.getType().equals("클래스")) {
							System.out.println("class type은 한번에 하나만 결제할 수 있습니다.");
						} else if (temp.getStock() < 1) {
							new SoldOutException("재고가 부족합니다.");
						} else {
							System.out.print("수량  : ");
							int count = Integer.parseInt(br.readLine());
							orderList.getGoods().add(Utils.getNewInstance(map.get(input)));
							if (temp.getType().equals("클래스")) {
								orderList.setGoodsType(true);
							}

							if (count > 0) {
								orderList.setTotalPrice(temp.getPrice() * count);
							}
						}

					} else {
						System.out.println("상품 번호가 존재하지 않습니다.");
					}
					System.out.print("상품번호 : ");
				}
				System.out.print("입력(o[order]: 주문, q[quit]: 종료) : ");
			}

			try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
				bw.write("고객님의 주문 감사합니다.");
			}
		}
		System.out.println();
	}

	public static boolean inputChk(String[] array, String input) {
		for (String str : array) {
			if (str.equals(input))
				return true;
		}
		return false;
	}

	private static void print(Map<String, Goods> map) {
		StringJoiner sj = new StringJoiner("\n");
		sj.add("상품번호                              상품명                                     판매가격                 재고수");
		for (Entry<String, Goods> key : map.entrySet()) {
			sj.add(key.getValue().toString());
		}

		System.out.println("\n" + sj.toString());
	}
}
