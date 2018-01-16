package magengine.danmuku.yt;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import javafx.scene.paint.Color;
import javafx.scene.paint.Stop;
import magengine.bullet.PresetColor;
import magengine.bullet.impl.CircleBullet;
import magengine.bullet.impl.FllowerBullet;
import magengine.chapter.util.QuickDanmuku;
import magengine.danmuku.ADanmuku;
import magengine.game.GameSession;
import magengine.game.LogicExecutor;
import magengine.helper.OvalHelper;
import magengine.launcher.Launcher;
import magengine.util.ElementUtils;

public class FllowerArrayDanmuku extends ADanmuku {
	LogicExecutor ses = LogicExecutor.getLogicExecutor();
	private int callCount = 0;
	private int midX = 250;
	private int midY = 150;
	private ElementUtils mEU = getmEU();
	public static final long DURATION = 30000;
	private QuickDanmuku quick = QuickDanmuku.getQuickDanmuku();
	private Random r = GameSession.rand();
	
	
	private Stop[] greenStops= {
			
			new Stop(0, Color.rgb(50 ,205 ,50,0.0)),
			new Stop(0.2, Color.rgb(50 ,205, 50, 0.0)), 
			new Stop(0.4, Color.rgb(0 ,255,0, 0.7)), 
			new Stop(0.75, Color.rgb(245, 245, 245, 1)),
			new Stop(1, Color.rgb(245, 245, 245, 1)),
			
		};
	
	private Stop[] redStops= {
			
			new Stop(0, Color.rgb(255 ,48 ,48,0.0)),
			new Stop(0.2, Color.rgb(255, 48, 48, 0.0)), 
			new Stop(0.4, Color.rgb(255 ,0 ,0,1)), 
			new Stop(0.75, Color.rgb(245, 245, 245, 1)),
			new Stop(1, Color.rgb(245, 245, 245, 1)),
			
		};

  private  Stop[] stops1 ={
		  new Stop(1.0,Color.rgb( 255,255,0,0.8)),
			
  };

	@Override
	public void executeDanmuku() {
		
		int lcount = 12;
		for (int i = 0; i < lcount; i++) {
			rotateDSnipe(midX, midY, 700, 2 * Math.PI / lcount * i,stops1);
		}
		

		RandomCircleLauncher( midX, midY,  Math.PI/2, 320, 17200,250,redStops);
	}
	
	
	
	public void rotateDSnipe(double midX, double midY, long startTime,double angle,Stop[] stops){
		double R = 30;
		double speed = 62;
		int currentHelperCount = callCount++;
		long duration=16700;
	
		
		OvalHelper tHelper = new OvalHelper(midX, midY, R, speed, angle);
		
		Launcher launcher = new Launcher(100, 100, 2*Math.PI/3, 520, duration);
		
		quick.bindToWantBeRemoved(tHelper, getSourceElement());
		launcher.getDirectionProperty().bind(tHelper.getDirectionProperty());
		launcher.getxProperty().bind(tHelper.getxProperty());
		launcher.getyProperty().bind(tHelper.getyProperty());
		
		launcher.setBulletEvent((sEX, bullet) -> {
			
			launcher.setBulletType(FllowerBullet.class);
			launcher.setBulletSpeed(75);
			
			sEX.schedule(() -> {
			launcher.setBulletConfig(b->{
				((FllowerBullet)b).setColorSupplier(PresetColor.getByStops(stops));
				((FllowerBullet)b).setR(2.7);
			});
			
		}, 500, TimeUnit.MILLISECONDS);
			
			
			
			sEX.schedule(() -> {
			

				bullet.setVelocityX(-bullet.getVelocityX()*0.01);
				bullet.setVelocityY(-bullet.getVelocityY()*0.01);
			}, 500, TimeUnit.MILLISECONDS);
			
			sEX.schedule(() -> {
				bullet.setVelocityX(-bullet.getVelocityX()*120);
				bullet.setVelocityY(-bullet.getVelocityY()*120);
				quick.VRotate(bullet, Math.PI+ Math.PI);
				
			}, 900, TimeUnit.MILLISECONDS);
			
			sEX.schedule(() -> {
				bullet.setVelocityX(-bullet.getVelocityX()*0.01);
				bullet.setVelocityY(-bullet.getVelocityY()*0.01);
				quick.VRotate(bullet,Math.PI/5+Math.PI);
				
			}, 1200, TimeUnit.MILLISECONDS);
			
			
			sEX.schedule(() -> {
				bullet.setVelocityX(-bullet.getVelocityX()*100);
				bullet.setVelocityY(-bullet.getVelocityY()*100);
				quick.VRotate(bullet,2*Math.PI);
				
			}, 4500, TimeUnit.MILLISECONDS);
			
			
			sEX.schedule(() -> {
				bullet.setVelocityX(-bullet.getVelocityX()*0.01);
				bullet.setVelocityY(-bullet.getVelocityY()*0.01);
				quick.VRotate(bullet,Math.PI/2);
			}, 6700, TimeUnit.MILLISECONDS);
			
			
			sEX.schedule(() -> {
				bullet.setVelocityX(-bullet.getVelocityX()*150);
				bullet.setVelocityY(-bullet.getVelocityY()*150);
				quick.VRotate(bullet,Math.PI/2);
				
			}, 8700, TimeUnit.MILLISECONDS);
			
			sEX.schedule(() -> {
				bullet.setVelocityX(-bullet.getVelocityX()*0.01);
				bullet.setVelocityY(-bullet.getVelocityY()*0.01);
				quick.VRotate(bullet,Math.PI);
				
			}, 10000, TimeUnit.MILLISECONDS);
			
			sEX.schedule(() -> {
				bullet.setVelocityX(-bullet.getVelocityX()*120);
				bullet.setVelocityY(-bullet.getVelocityY()*120);
				quick.VRotate(bullet,Math.PI/2);
				
			}, 12500, TimeUnit.MILLISECONDS);
			
			
		
			sEX.schedule(() -> {
				bullet.setVelocityX(-bullet.getVelocityX()*0.01);
				bullet.setVelocityY(-bullet.getVelocityY()*0.01);
				quick.VRotate(bullet,Math.PI);
				
			}, 37500, TimeUnit.MILLISECONDS);
		
			sEX.schedule(() -> {
				bullet.setVelocityX(-bullet.getVelocityX()*120);
				bullet.setVelocityY(-bullet.getVelocityY()*120);
				quick.VRotateRandom(bullet,Math.PI);
			}, 26700, TimeUnit.MILLISECONDS);

		});
		
		

	     quick.bindToWantBeRemoved(launcher, getSourceElement());
	     
		LogicExecutor.getLogicExecutor().schedule(() -> {
			mEU.add("tHelper"+currentHelperCount, tHelper);
		}, startTime, TimeUnit.MILLISECONDS);
		
		LogicExecutor.getLogicExecutor().schedule(() -> {
			mEU.add("launcher"+callCount++, launcher);
		}, startTime+1, TimeUnit.MILLISECONDS);
		
		LogicExecutor.getLogicExecutor().schedule(() -> {
			mEU.removeBoth("tHelper" + currentHelperCount);
		}, startTime+duration+2, TimeUnit.MILLISECONDS);
	}
	
	
	public void RandomCircleLauncher(double midX, double midY, double direction, long interval, int duration ,int delayTime,Stop[] stops){
		Launcher launcher = new Launcher(midX, midY, direction, interval+420, duration);
	
		launcher.setBulletType(CircleBullet.class);
		launcher.setBulletSpeed(210.0);
		launcher.setBulletConfig((b)->{
			((CircleBullet)b).setR(25);
			((CircleBullet)b).setColorSupplier(PresetColor.getByStops(stops));
		});
		
		launcher.setBulletEvent((sesx,bullet)->{
			LogicExecutor.getLogicExecutor().schedule(() -> {
			quick.VToByDirection(bullet, quick.getPlayerDirectionAngle(bullet));
			}, 500+delayTime, TimeUnit.MILLISECONDS);
		});
		
		
		quick.bindToWantBeRemoved(launcher, getSourceElement());
		quick.bindToXY(launcher, getSourceElement());
		
		LogicExecutor.getLogicExecutor().schedule(() -> {
			mEU.add("launcher"+delayTime, launcher);
		}, 720+delayTime, TimeUnit.MILLISECONDS);
		
	}

	
}

