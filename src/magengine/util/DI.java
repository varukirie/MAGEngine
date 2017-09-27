package magengine.util;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/**
 * 通过该类能获得的对象有：<br>
 * <table>
 * <tr><td>key</td><td>value</td><td>Type</td></tr>
 * <tr><td>mEU</td><td>moveableElementUtils</td><td>ElementUtils</td></tr>
 * <tr><td>mh</td><td>moveHandler</td><td>MoveHandler</td></tr>
 * <tr><td>sES</td><td>sExecutorService</td><td>ScheduledExecutorService</td></tr>
 * <tr><td>staticCanvas</td><td>staticCanvas</td><td>MyCanvas</td></tr>
 * <tr><td>switcher</td><td>switcher</td><td>MyCanvasSwitcher</td></tr>
 * <tr><td>animationTimer</td><td>animationTimer</td><td>AnimationTimer</td></tr>
 * </table>
 * @author Astronic
 *
 */
public class DI {
	private static Map<String,Object> di=new HashMap<>();
	public static Map<String, Object> di() {
		return di;
	}
}
