package indi.megaastronic.util;

import java.util.HashMap;
import java.util.Map;

public class DI {
	private static Map<String,Object> di=new HashMap<>();
	public static Map<String, Object> di() {
		return di;
	}
}
