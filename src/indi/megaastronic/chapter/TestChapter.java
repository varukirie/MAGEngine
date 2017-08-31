package indi.megaastronic.chapter;

import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import indi.megaastronic.bullet.ArrowBullet;
import indi.megaastronic.chapter.util.AChapter;
import indi.megaastronic.chapter.util.QuickDanmuku;
import indi.megaastronic.chapter.util.SeqDanmuku;
import indi.megaastronic.launcher.Launcher;
import indi.megaastronic.paint.MyCanvas;
import indi.megaastronic.util.ElementUtils;
import indi.megaastronic.bullet.Bullet;
import indi.megaastronic.bullet.DefaultBullet;

public class TestChapter extends AChapter {
	
	private int i;
	int midX=270;
	int midY=200;
	@Override
	public void design(ScheduledExecutorService sES, MyCanvas staticCanvas, ElementUtils mEU) {
//		staticCanvas.getGraphicsContext2D().fillRect(0, 0, MyCanvas.CANVAS_WIDTH, MyCanvas.CANVAS_HEIGHT);
		SeqDanmuku seq = new SeqDanmuku(sES, mEU);
		QuickDanmuku quick = new QuickDanmuku(mEU);
		Random r= new Random();
		
//		for(int i=1;i<=10;i++){
//			seq.rotateL(midX, midY, 500,i);	
//		}
		long interval = 100;
		long startTime2=1000;
		for(int i=1;i<=1;i++){
			int x=midX;
			int y= midY;
			seq.rotate(x,y,interval*i+startTime2,Math.PI*2/3+i);
			seq.rotate(x,y,interval*i+startTime2,Math.PI*4/3+i);
			seq.rotate(x,y,interval*i+startTime2,Math.PI*6/3+i);
		}
		for(int i=1;i<=2;i++){
			int x=midX;
			int y= midY;
			seq.rotate(x,y,interval*i+startTime2+7000,Math.PI*2/3+i,DefaultBullet.class,false);
			seq.rotate(x,y,interval*i+startTime2+7000,Math.PI*4/3+i,DefaultBullet.class,false);
			seq.rotate(x,y,interval*i+startTime2+7000,Math.PI*6/3+i,DefaultBullet.class,false);
		}

		
		//seq.rotate(midX-100, midY,2000,0);
		//seq.rotate(midX-100, midY,2000,Math.PI);

//		seq.rotate(midX-100, midY,2000,Math.PI);
//		seq.rotate(midX+100, midY,2000,0);

		
//		seq.rotateSlash(midX, midY, 5000);
//		seq.rotateSlash(midX-100, midY+100, 15000);
//		
//		seq.lineSnipe(midX, midY-100,0.5, 0, 1000);
//		seq.lineSnipe(midX, midY-100,-0.5, 0, 1000);
	}

}
