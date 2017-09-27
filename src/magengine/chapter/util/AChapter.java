package magengine.chapter.util;

import java.util.concurrent.ScheduledExecutorService;

import magengine.paint.MyCanvas;
import magengine.util.ElementUtils;

public abstract class AChapter {
	public abstract void design(ScheduledExecutorService sES,MyCanvas staticCanvas,ElementUtils mEU);
}
