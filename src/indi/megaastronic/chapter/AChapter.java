package indi.megaastronic.chapter;

import java.util.Timer;
import java.util.concurrent.ScheduledExecutorService;

import indi.megaastronic.paint.MyCanvas;
import indi.megaastronic.util.ElementUtils;

public abstract class AChapter {
	public abstract void design(ScheduledExecutorService sES,MyCanvas staticCanvas,ElementUtils moveableEU);
}
