package indi.megaastronic.chapter.util;

import java.util.concurrent.ScheduledExecutorService;

import indi.megaastronic.paint.MyCanvas;
import indi.megaastronic.util.ElementUtils;

public abstract class AChapter {
	public abstract void design(ScheduledExecutorService sES,MyCanvas staticCanvas,ElementUtils mEU);
}
