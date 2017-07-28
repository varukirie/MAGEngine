package indi.megaastronic.chapter;

import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import indi.megaastronic.element.impl.Helper;
import indi.megaastronic.element.impl.OvalHelper;
import indi.megaastronic.paint.MyCanvas;
import indi.megaastronic.util.ElementUtils;

public class TestChapter extends AChapter {
	
	private int i;
	int midX=270;
	int midY=200;
	@Override
	public void design(ScheduledExecutorService sES, MyCanvas staticCanvas, ElementUtils mEU) {
		SeqDanmuku seq = new SeqDanmuku(sES, mEU);
		seq.rotate(midX+30, midY,15000);
		seq.rotate(midX, midY,1000);
		//seq.rotateSlash(midX, midY, 1000);
		//seq.rotateSlash(midX-100, midY+100, 15000);
		
	}

}
