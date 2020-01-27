package order.net.class101.server1.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringJoiner;

import order.net.class101.server1.model.Goods;

public class Utils {
	static final String INPUT_FILE_NAME = "productList";
	static String[] quit = new String[] { "q", "quit" };
	static String[] order = new String[] { "o", "order" };
	static int unlimitedAmount = 99999;
	static final Map<String, Goods> map = new HashMap<>();

	public static boolean orderInputChk(String input) {
		for (String str : order) {
			if (str.equals(input))
				return true;
		}
		return false;
	}

	public static boolean quitInputChk(String input) {
		for (String str : quit) {
			if (str.equals(input))
				return true;
		}
		return false;
	}

	public static Map<String, Goods> buildGoodsList() {
		if (map.size() > 0)
			return map;

		try (BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE_NAME))) {
			while (br.ready()) {
				String[] split = Utils.readLineSplit(br.readLine());
				if (split.length < 1)
					continue;

				Goods goods = Utils.getInstance(split);
				map.put(split[0], goods);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}

	public static String[] readLineSplit(String readLine) {
		readLine = readLine.startsWith("[") && readLine.endsWith("]") ? readLine.substring(1, readLine.length() - 1)
				: readLine;
		return readLine.split("]\\[");
	}

	public static Goods getInstance(String[] split) {
		return new Goods.Builder().id(split[0]).type(EnumMapper.toEnumValues().get(split[1])).name(split[2])
				.price(Integer.parseInt(split[3])).stock(Integer.parseInt(split[4]))
				.unlimited(Integer.parseInt(split[4]) == unlimitedAmount).build();
	}

	public static Goods getNewInstance(Goods g, int count) {
		return new Goods.Builder().id(g.getId()).type(g.getType()).name(g.getName()).price(g.getPrice()).stock(count)
				.unlimited(g.isUnlimited()).build();
	}

	public static void goodsMapPrint(Map<String, Goods> goodsMap) {
		StringJoiner sj = new StringJoiner("\n");
		sj.add("상품번호                              상품명                                     판매가격                 재고수");
		for (Entry<String, Goods> key : goodsMap.entrySet()) {
			sj.add(key.getValue().toString());
		}

		System.out.println("\n" + sj.toString());
	}
}
