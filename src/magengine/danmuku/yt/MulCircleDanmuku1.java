package magengine.danmuku.yt;

import static java.lang.Math.PI;

import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import magengine.bullet.ArrowBullet;
import magengine.bullet.Bullet;
import magengine.bullet.CircleBullet;
import magengine.bullet.DefaultBullet;
import magengine.bullet.DiamondBullet;
import magengine.bullet.EllipseBullet;
import magengine.bullet.StarBullet;
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
import magengine.paint.MyCanvas;
import magengine.util.ElementUtils;

public class MulCircleDanmuku1 extends ADanmuku  {

@Override
public void executeDanmuku() {

	
	
	ElementUtils mEU = getmEU();
	ScheduledExecutorService ses = getsES();
	QuickDanmuku quick = QuickDanmuku.getQuickDanmuku();
	int midX = 300 ;
	int midY = 250;

	
	

     double[][] midArray = new double[][] { {300,300,300,170.0961894,429.9038106,170.0961894,429.9038106,300},{250,100,400,175,175,335,335,250}};
	
	double[] rArray = new double[] {  50,30, 30, 30, 30,30,30,210};// 旋转半径
	double[] rotateSpeedArray = new double[] { 30, 10, 10, 10, 10,10,10,70};
	int bulletCount = 25 ;
	
	
	
	
	
	for (int j = 0; j < midArray[0].length; j++) {
		int i = j;
		LogicExecutor.getLogicExecutor().schedule(() -> {
			OvalLauncherGroup olg = new OvalLauncherGroup(midArray[0][i], midArray[1][i]);
			
			olg.setAmount(bulletCount);
			olg.setDuration(100);
			olg.setLauncherConfig((launcher) -> {
				launcher.setBulletSpeed(rArray[i]);
				launcher.setBulletEvent((sesx, b) -> {
					LogicExecutor.getLogicExecutor().schedule(() -> {
						b.setWantBeRemoved(true);
					}, 600, TimeUnit.MILLISECONDS);
				});
			});
			olg.execute();
		//	Helper midHelper = new Helper(midArray[0][i], midArray[1][i]);
			
			for (int k = 0; k < bulletCount+10 ; k++) {
				new ArcLauncherGroup(midX, midY, Math.PI/2+Math.PI/7, Math.PI/3, 6).delayExecute(1000+i*2);
				new ArcLauncherGroup(midX, midY, Math.PI/2-Math.PI/7, Math.PI/3, 6).delayExecute(1000+i*2);
				new ArcLauncherGroup(midX, midY, Math.PI/2-Math.PI/7, Math.PI/3, 6).delayExecute(1000+i*2);
		   
			}
		
			LogicExecutor.getLogicExecutor().schedule(() -> {
			 //进行绑定事件，新建中心helper
				Helper midHelper = new Helper(midArray[0][i], midArray[1][i]);		
				
				
			for (int k = 0; k < bulletCount ; k++) {
				
				
//					
					Bullet bullet = new DefaultBullet(midArray[0][i], midArray[1][i]);
					OvalHelper helper = new OvalHelper(midArray[0][i], midArray[1][i], rArray[i],
							rotateSpeedArray[i], Math.PI * 2 / bulletCount * k);
		
					helper.getOvalMidXProperty().bind(midHelper.getxProperty());
					helper.getOvalMidYProperty().bind(midHelper.getyProperty());
					
					//子弹随着helper一起运动
				
					bullet.getxProperty().bind(helper.getxProperty());
					bullet.getyProperty().bind(helper.getyProperty());
					
					
                    bullet.setVelocityX(1.0);
                    
                    
                    
                    
                    
                   
                    new ArcLauncherGroup(midArray[0][i], midArray[1][i], Math.PI/2+Math.PI/7, Math.PI/3, 6).delayExecute(150*i);
                	new ArcLauncherGroup(midArray[0][i], midArray[1][i], Math.PI/2-Math.PI/7, Math.PI/3, 8).delayExecute(600*i);


                    
					bullet.setLambdaModify((x) -> {
						quick.VToByDirection(x, helper.getDirection() - Math.PI / 2);
					});
					
				
					
					Random r = new Random();			
					mEU.add("bullet" + r.nextInt(), bullet);
					mEU.add("helper" + r.nextInt(), helper);
					mEU.add("midHelper" + i * i * 10007, midHelper);
			
					
			
				}
			
			
			
			
		
		
			}, 800, TimeUnit.MILLISECONDS);
			
			
		
	
			
		}, 3000, TimeUnit.MILLISECONDS);

	}
	


	new ArcLauncherGroup(midX, midY, Math.PI/2+Math.PI/7, Math.PI/3, 6).delayExecute(10000);
	new ArcLauncherGroup(midX, midY, Math.PI/2-Math.PI/7, Math.PI/3, 6).delayExecute(25000);
	new ArcLauncherGroup(300,250, Math.PI/2, Math.PI/3, 15).delayExecute(8000);

	
	
	
	
	
	
	/*
	
	//int midX = MyCanvas.CANVAS_WIDTH/2;
	//int midY = 200;
	int midX = 300 ;
	int midY = 250;
    long startTime = 7000;
	Random r=new Random();
	double helperLenght = 70;
	//int currentHelperCount = callCount++;
	
	long duration=8000;
	// int callCount = 0;
	

	int lcount = 12;
	
	
	OvalLauncherGroup oG5 = new OvalLauncherGroup(midX, midY);
	oG5.setLauncherConfig((launcher) -> {
		launcher.setBulletEvent((sESx, bullet) -> {
			LogicExecutor.getLogicExecutor().schedule(() -> {
				quick.stopBullet(bullet);
			}, 1000, TimeUnit.MILLISECONDS);
			LogicExecutor.getLogicExecutor().schedule(() -> {
				quick.runBullet(bullet);
				bullet.setVelocityX(-bullet.getVelocityX());
				bullet.setVelocityY(-bullet.getVelocityY());
			}, 1500, TimeUnit.MILLISECONDS);
		});
	});
	
	
	oG5.delayExecute(1500);
	
	
	

	for (int i = 0; i < lcount; i++) {
		
		int currentHelperCount = callCount++;
		
		double helperDelta = 2 * Math.PI / lcount * i;
	
	OvalHelper tHelper = new OvalHelper(midX, midY, helperLenght, 40,helperDelta);
	tHelper.setPositive(true);
	Launcher launcher = new Launcher(100, 100, 1/2.0*Math.PI, 400, duration);
	
	launcher.getDirectionProperty().bindBidirectional(tHelper.getDirectionProperty());
	launcher.getxProperty().bindBidirectional(tHelper.getxProperty());
	launcher.getyProperty().bindBidirectional(tHelper.getyProperty());
	launcher.setBulletEvent((ScheduledExecutorService timer, Bullet bullet) -> {
		timer.schedule(() -> {
			bullet.setVelocityX(bullet.getVelocityX()*0.01);
			bullet.setVelocityY(bullet.getVelocityY()*0.01);
		}, 1000, TimeUnit.MILLISECONDS);
		timer.schedule(() -> {
			bullet.setVelocityX(bullet.getVelocityX()*120);
			bullet.setVelocityY(bullet.getVelocityY()*120);
			quick.snipe(bullet,tHelper.getX(),tHelper.getY());
//			quick.snipePlayer(bullet);
		}, 3000, TimeUnit.MILLISECONDS);
		
	});
	

	
	LogicExecutor.getLogicExecutor().schedule(() -> {
		mEU.add("tHelper" + currentHelperCount, tHelper);
	}, startTime, TimeUnit.MILLISECONDS);
	LogicExecutor.getLogicExecutor().schedule(() -> {
		mEU.add("launcher"+callCount++, launcher);
	}, startTime+1, TimeUnit.MILLISECONDS);
	LogicExecutor.getLogicExecutor().schedule(() -> {
		mEU.removeBoth("tHelper" + currentHelperCount);
	}, startTime+duration+2, TimeUnit.MILLISECONDS);
	
	
	
	}
	*/



	
}

}