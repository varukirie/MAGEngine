package magengine.chapter.util;

import java.util.concurrent.ScheduledExecutorService;

import magengine.game.LogicExecutor;
import magengine.paint.MyCanvas;
import magengine.util.ElementUtils;

public abstract class AChapter {
	public abstract void design(LogicExecutor executor,MyCanvas staticCanvas,ElementUtils mEU);
}
