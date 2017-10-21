package magengine.danmuku.yt;

import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import magengine.bullet.impl.ArrowBullet;
import magengine.bullet.impl.CircleBullet;
import magengine.bullet.impl.DefaultBullet;
import magengine.bullet.impl.DiamondBullet;
import magengine.bullet.impl.EllipseBullet;
import magengine.bullet.impl.StarBullet;
import magengine.chapter.util.QuickDanmuku;
import magengine.chapter.util.SeqDanmuku;
import magengine.danmuku.ADanmuku;
import magengine.game.LogicExecutor;
import magengine.helper.Helper;
import magengine.helper.OvalHelper;
import magengine.launcher.ALauncherGroup;
import magengine.launcher.ArcLauncherGroup;
import magengine.launcher.BulletEvent;
import magengine.launcher.Launcher;
import magengine.launcher.OvalLauncherGroup;
import magengine.util.ElementUtils;

public class MulCircleDanmuku2 extends ADanmuku  {

/**
 * 关于 ScheduledExecutorService
 *ScheduledExecutorService,是基于线程池设计的定时任务类,每个调度任务都会分配到线程池中的一个线程去执行,也就是说,任务是并发执行,互不影响。
 *需要注意,只有当调度任务来的时候,ScheduledExecutorService才会真正启动一个线程,其余时间ScheduledExecutorService都是出于轮询任务的状态。
 */

@Override
public void executeDanmuku() {

	ElementUtils mEU =  getmEU();
	QuickDanmuku quick = new QuickDanmuku(mEU);
	Random r = new Random();
	
	/**
	 * 子弹事件1(be1) ：子弹随机散开
	 */
	/*
	BulletEvent be1 = (sesx, b) -> {
		LogicExecutor.getLogicExecutor().schedule(() -> {
			quick.runBullet(b);
			quick.VToByDirection(b, r.nextDouble() * Math.PI * 2);
		}, 2000, TimeUnit.MILLISECONDS);
	};
	
//	OvalHelper helper1 = new OvalHelper(delay, delay, delay, delay);
	OvalHelper helper1 = new OvalHelper(300, 200, 70, 20, 0);//中心坐标、半径、速度
	OvalHelper helper2 = new OvalHelper(300, 200, 70, 30, 0);
	// OvalHelper  helper = new OvalHelper(300, 200, 70, 20, 0);
		  helper2.setDuration(5000);  
		  //存活时间为5秒 跟launcher时间相同
		 
		  Launcher launcher1 = new Launcher(300, 200, Math.PI / 2, 400, 5000);
		  helper2.getxProperty().bindBidirectional(launcher1.getxProperty());
		  helper2.getyProperty().bindBidirectional(launcher1.getyProperty());
		  launcher1.setBulletEvent((executor, bullet) -> { 
			  quick.stopBullet(bullet);
		  }); 
		  mEU.add("testHelper", helper2);
		  mEU.add("testLauncher", launcher1);


	//PloygonalLineHelper helper1 = new PloygonalLineHelper(ploygon3, helperSpeed);
	
	OvalLauncherGroup oG = new OvalLauncherGroup(275, 200);
	oG.setLauncherConfig((launcher) -> {
		
		launcher.setBulletType(EllipseBullet.class);
		launcher.setBulletSpeed(0.0006);
		launcher.setBulletEvent(be1);
		
	
	
		
		launcher.setBulletEvent((sESx, bullet) -> {
		 
			LogicExecutor.getLogicExecutor().schedule(() -> {
				quick.stopBullet(bullet);
				
			}, 100, TimeUnit.MILLISECONDS);
		
		});
	});//最里面
	oG.delayExecute(1500);*/

	
	OvalLauncherGroup oG1 = new OvalLauncherGroup(275, 200);
	oG1.setLauncherConfig((launcher) -> {
		
	launcher.setBulletType(EllipseBullet.class);
		launcher.setBulletEvent((sESx, bullet) -> {
		 
			LogicExecutor.getLogicExecutor().schedule(() -> {
				quick.stopBullet(bullet);
	
			}, 500, TimeUnit.MILLISECONDS);
			LogicExecutor.getLogicExecutor().schedule(() -> {
				quick.runBullet(bullet);
				
			}, 2000, TimeUnit.MILLISECONDS);
			
		});
	});
	oG1.delayExecute(1000);
	

	

	
	
	OvalLauncherGroup oG2 = new OvalLauncherGroup(275, 200);
	oG1.setLauncherConfig((launcher) -> {
		
	launcher.setBulletType(EllipseBullet.class);
		
		launcher.setBulletEvent((sESx, bullet) -> {
		 
			LogicExecutor.getLogicExecutor().schedule(() -> {
				quick.stopBullet(bullet);
				
			}, 800, TimeUnit.MILLISECONDS);
			
			
		});
		
		launcher.setBulletEvent((sESx, bullet) -> {
			 
			LogicExecutor.getLogicExecutor().schedule(() -> {
				quick.runBullet(bullet);
				
			}, 1800, TimeUnit.MILLISECONDS);
			
			
		});
		
		
	});
	oG2.delayExecute(1500);
	
	OvalLauncherGroup oG3 = new OvalLauncherGroup(275, 200);
	oG3.setAmount(10);
	oG3.setLauncherConfig((launcher) -> {
		
		launcher.setBulletType(EllipseBullet.class);
		launcher.setBulletEvent((sESx, bullet) -> {
			LogicExecutor.getLogicExecutor().schedule(() -> {
				quick.stopBullet(bullet);
			}, 1100, TimeUnit.MILLISECONDS);
		   
			
				LogicExecutor.getLogicExecutor().schedule(() -> {
					quick.runBullet(bullet);
					
				}, 1500, TimeUnit.MILLISECONDS);
				
			
			
		
		});
	});
	oG3.delayExecute(1500);



	
}

}