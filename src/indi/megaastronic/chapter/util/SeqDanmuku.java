package indi.megaastronic.chapter.util;

import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import indi.megaastronic.element.impl.Bullet;
import indi.megaastronic.element.impl.FBullet;
import indi.megaastronic.element.impl.MissileBullet;
import indi.megaastronic.element.impl.Player;
import indi.megaastronic.helper.Helper;
import indi.megaastronic.helper.OvalHelper;
import indi.megaastronic.util.ElementUtils;

public class SeqDanmuku {
	private ScheduledExecutorService sES;
	private ElementUtils mEU;
	private QuickDanmuku quick;
	private int callCount=0;
	public SeqDanmuku(ScheduledExecutorService sES, ElementUtils mEU) {
		this.sES = sES;
		this.mEU = mEU;
		quick=new QuickDanmuku(mEU);
	}
	public void rotate(double midX,double midY,long startTime) {
		rotate(midX, midY, startTime, 0);
	}
		
	public void rotate(double midX,double midY,long startTime,double helperDelta){
		rotate(midX, midY, startTime, 0,Bullet.class);
	}
	public void rotate(double midX,double midY,long startTime,double helperDelta,Class cls) {
		
		int i;
		double helperLenght = 70;
		int count=70;
		int currentHelperCount=callCount++;
		long interval=100;
		Helper tHelper= new OvalHelper(midX,midY, helperLenght, 20,helperDelta);
		Random r = new Random();
		sES.schedule(()->{
			mEU.add("tHelper"+currentHelperCount, tHelper);
		}, startTime, TimeUnit.MILLISECONDS);
	
		for(i=1;i<=count;i++){
			sES.schedule(()->{
						quick.snipe((midX+tHelper.getX())/2, (midY+ tHelper.getY())/2, 
									tHelper.getX(), tHelper.getY(),0.4,cls);
			}, startTime+i*interval, TimeUnit.MILLISECONDS);
		}
		sES.schedule(()->{
			mEU.removeBoth("tHelper"+currentHelperCount);
		}, startTime+count*interval+1, TimeUnit.MILLISECONDS);
	}
	
	public void rotateSlash(double midX,double midY,long startTime){
		int i;
		double helperLenght = 70;
		int count=100;
		Helper tHelper = new OvalHelper(midX,midY, helperLenght, 20);
		int currentHelperCount=callCount++;
		
		sES.schedule(()->{
			mEU.add("tHelper"+currentHelperCount, tHelper);
		}, startTime, TimeUnit.MILLISECONDS);
		for(i=1;i<=count;i++){
			sES.schedule(()->{
				quick.slashTransformAcc(tHelper.getX(),
						 tHelper.getY(), 0.1, new double[][] { { 1, 0 }, { 1, 1
						 } });
			}, startTime+i*100, TimeUnit.MILLISECONDS);
		}
		sES.schedule(()->{
			mEU.removeBoth("tHelper"+currentHelperCount);
		}, startTime+count*100+1, TimeUnit.MILLISECONDS);
	}
	
	public void lineSnipe(double startX,double startY,double vx,double vy,long startTime){
		int i;
		int count=100;
		Helper tHelper = new Helper(startX, startY, vx, vy);
		int currentHelperCount=callCount++;
		sES.schedule(()->{
			//让Helper动起来
			mEU.add("tHelper"+currentHelperCount, tHelper);
		}, startTime, TimeUnit.MILLISECONDS);
		for(i=1;i<=count;i++){
			sES.schedule(()->{
				if(!tHelper.getDeleted()){
					quick.snipe(tHelper.getX(), tHelper.getY(), Player.getPlayer().getX(), Player.getPlayer().getY(), 1,Bullet.class);
				}
			}, startTime+i*100, TimeUnit.MILLISECONDS);
		}
		sES.schedule(()->{
			mEU.removeBoth("tHelper"+currentHelperCount);
		}, startTime+count*100+1, TimeUnit.MILLISECONDS);
	}
}
