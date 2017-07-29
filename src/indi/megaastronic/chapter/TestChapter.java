package indi.megaastronic.chapter;

import java.util.concurrent.ScheduledExecutorService;
import indi.megaastronic.paint.MyCanvas;
import indi.megaastronic.util.ElementUtils;

public class TestChapter extends AChapter {
	
	private int i;
	int midX=270;
	int midY=200;
	@Override
	public void design(ScheduledExecutorService sES, MyCanvas staticCanvas, ElementUtils mEU) {
		SeqDanmuku seq = new SeqDanmuku(sES, mEU);
		QuickDanmuku quick = new QuickDanmuku(mEU);
		seq.rotate(midX, midY,2000,Math.PI*2/3);
		seq.rotate(midX, midY,2000,Math.PI*4/3);
		seq.rotate(midX, midY,2000,Math.PI*6/3);
		
		seq.rotateSlash(midX, midY, 5000);
		seq.rotateSlash(midX-100, midY+100, 15000);
		
		seq.lineSnipe(midX, midY-100,0.5, 0, 1000);
		seq.lineSnipe(midX, midY-100,-0.5, 0, 1000);
	}

}
