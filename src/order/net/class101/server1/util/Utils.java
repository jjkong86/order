package order.net.class101.server1.util;

import order.net.class101.server1.model.Goods;

public class Utils {
	public static Goods getInstance(String[] split) {
		return Goods.builder().id(split[0]).type(split[1]).name(split[2])
				.price(Integer.parseInt(split[3])).stock(Integer.parseInt(split[4])).build();
	}
	
	public static Goods getNewInstance(Goods g) {
		return Goods.builder().id(g.getId()).name(g.getName()).price(g.getPrice()).stock(g.getStock()).build();
	}
	
	public static String[] readLineSplit(String readLine) {
		readLine = readLine.startsWith("[") && readLine.endsWith("]")
				? readLine.substring(1, readLine.length() - 1)
				: readLine;
		return readLine.split("]\\[");
	}
}
