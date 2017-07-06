package indi.megaastronic.chapter;

import java.util.Timer;

import indi.megaastronic.paint.MyCanvas;
import indi.megaastronic.util.ElementUtils;

public abstract class AChapter {
	public abstract void design(Timer timer,MyCanvas staticCanvas,ElementUtils moveableEU);
}
