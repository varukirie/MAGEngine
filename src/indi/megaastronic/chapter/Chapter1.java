package indi.megaastronic.chapter;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import application.Main;
import indi.megaastronic.paint.MyCanvas;
import indi.megaastronic.util.ElementUtils;

public class Chapter1 extends AChapter {
	private int i;

	@Override
	public void design(ScheduledExecutorService sES, MyCanvas staticCanvas, ElementUtils moveableEU) {
Random r = new Random();
		
		ChapterUtils cu = new ChapterUtils(moveableEU);
		long startTime=System.currentTimeMillis();
		for(i=1;i<=7000;i++){
			for(int k=1;k<=1;k++){
				double x=400;//(r.nextDouble()*MyCanvas.CANVAS_WIDTH);
				double y=300;//200+(r.nextDouble()*200);
				double v=0.7*r.nextDouble()+0.3;
				sES.schedule(()->{
					
					cu.slashTransform(x, y,v,
							new double[][]{
								{1,0},
								{1,1}
							}
						);
					
				}, i*1000, TimeUnit.MILLISECONDS);
				
				sES.schedule(()->{
					cu.slashTransform(x, y,v*0.5,
							new double[][]{
								{1,0},
								{-1,1}
							}
						);
				}, i*1000+200, TimeUnit.MILLISECONDS);
			}
		}
		
		


		if(Main.DEBUG) System.out.println("chapter load completed!"+(System.currentTimeMillis()-startTime)+"ms");
	
	}

	

}
