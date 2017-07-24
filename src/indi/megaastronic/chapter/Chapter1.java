package indi.megaastronic.chapter;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import application.Main;
import indi.megaastronic.paint.MyCanvas;
import indi.megaastronic.util.ElementUtils;

public class Chapter1 extends AChapter {
	private int i;
	@Override
	public void design(Timer timer, MyCanvas staticCanvas, ElementUtils moveableEU) {
		Random r = new Random();
		
		ChapterUtils cu = new ChapterUtils(moveableEU);
		long startTime=System.currentTimeMillis();
		for(i=1;i<=7000;i++){
			for(int k=1;k<=4;k++){
				timer.schedule(new TimerTask() {
					@Override
					public void run() {
						cu.slashTransform((r.nextDouble()*MyCanvas.CANVAS_WIDTH), 200+(r.nextDouble()*200), 2*r.nextDouble()+0.1,
								new double[][]{
									{1,0},
									{1,1}
								}
							);
					}
				}, i*1000);
			}

//			timer.schedule(new TimerTask() {
//				@Override
//				public void run() {
//					cu.slashDown(480, 280, 1);
//				}
//			}, i*50+10*250);
//			timer.schedule(new TimerTask() {
//				@Override
//				public void run() {
//					cu.slashDown(80, 280, 1);
//				}
//			}, (i+10)*150);
		}
		if(Main.DEBUG) System.out.println("chapter load completed!"+(System.currentTimeMillis()-startTime)+"ms");
	}

	

}
