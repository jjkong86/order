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
		print(map);

		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
			String input;
			String[] quit = new String[] { "q", "quit" };
			String[] order = new String[] { "o", "order" };
			System.out.println("입력(o[order]: 주문, q[quit]: 종료) : ");
			List<Goods> list = new ArrayList<>();
			Order orderList = new Order();
			if (inputChk(order, br.readLine())) {
				System.out.println("상품번호 : ");
				while (!inputChk(quit, (input = br.readLine()))) {

					if (" ".equals(input)) {
						System.out.println("주문내역 : ");
						if (orderList.getTotalPrice() < 50000) {
							orderList.setTotalPrice(orderList.getTotalPrice() + 5000);
						}
						System.out.println(orderList.toString());
						orderList = new Order();
						continue;
					}
					Goods temp = map.get(input);
					if (temp != null) {
						if (orderList.isGoodsType() && temp.getType().equals("class")) {
							System.out.println("class type은 한번에 하나만 결제할 수 있습니다.");
							continue;
						}
						
						System.out.println("수량  : ");
						int count = Integer.parseInt(br.readLine());
						System.out.println("상품번호 : ");
						orderList.getGoods().add(map.get(input));
						if (temp.getType().equals("class")) {
							orderList.setGoodsType(true);
						}
						if (count > 0) {
							orderList.setTotalPrice(temp.getPrice() * count);
						}
					} else {
						System.out.println("상품 번호가 존재하지 않습니다.");
					}
				}
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
