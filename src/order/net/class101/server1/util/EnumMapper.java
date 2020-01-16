package order.net.class101.server1.util;

import java.util.HashMap;
import java.util.Map;

import order.net.class101.server1.model.GoodsType;

public class EnumMapper {

	private EnumMapper() {
	}

	public static Map<String, GoodsType> toEnumValues() {
		return LazyHoder.toEnumValues();
	}

	private static class LazyHoder {
		private final static Map<String, GoodsType> map = new HashMap<>();

		private static Map<String, GoodsType> toEnumValues() {
			if (map.size() > 0) {
				return map;
			}

			for (GoodsType g : GoodsType.values()) {
				map.put(g.getType(), g);
			}
			return map;
		}
	}
}
