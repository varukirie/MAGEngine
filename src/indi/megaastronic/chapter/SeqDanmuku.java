package indi.megaastronic.chapter;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import indi.megaastronic.element.impl.Helper;
import indi.megaastronic.element.impl.OvalHelper;
import indi.megaastronic.util.ElementUtils;

public class SeqDanmuku {
	private ScheduledExecutorService sES;
	private ElementUtils mEU;
	private DanmukuUtils du;
	private int callCount=0;
	Map<String,Helper> helperMap = new HashMap<>();
	public SeqDanmuku(ScheduledExecutorService sES, ElementUtils mEU) {
		this.sES = sES;
		this.mEU = mEU;
		du=new DanmukuUtils(mEU);
	}
	Helper tHelper;
	public void rotate(double midX,double midY,long startTime) {
		
		int i;
		double helperLenght = 70;
		int count=100;
		int currentHelperCount=callCount++;
		
		Random r = new Random();
		sES.schedule(()->{
			tHelper = new OvalHelper(midX,midY, helperLenght, 20);
			
			mEU.add("tHelper"+currentHelperCount, tHelper);
		}, startTime, TimeUnit.MILLISECONDS);
	
		for(i=1;i<=count;i++){
			sES.schedule(()->{
				mEU.add("snipe"+r.nextInt(), du.snipe((midX+tHelper.getX())/2, (midY+ tHelper.getY())/2, tHelper.getX(), tHelper.getY(),0.1));
			}, startTime+i*100, TimeUnit.MILLISECONDS);
		}
		sES.schedule(()->{
			mEU.removeBoth("tHelper"+currentHelperCount);
		}, startTime+count*100+1, TimeUnit.MILLISECONDS);
	}
	
	public void rotateSlash(double midX,double midY,long startTime){
		int i;
		double helperLenght = 70;
		int count=100;
		Helper tHelper = new OvalHelper(midX,midY, helperLenght, 20);
		int currentHelperCount=callCount++;
		mEU.add("tHelper"+currentHelperCount, tHelper);
		Random r = new Random();
		for(i=1;i<=count;i++){
			sES.schedule(()->{
				du.slashTransformAcc(tHelper.getX(),
						 tHelper.getY(), 0.1, new double[][] { { 1, 0 }, { 1, 1
						 } });
			}, startTime+i*100, TimeUnit.MILLISECONDS);
		}
		sES.schedule(()->{
			mEU.removeBoth("tHelper"+currentHelperCount);
		}, startTime+count*100+1, TimeUnit.MILLISECONDS);
		
	}
}
