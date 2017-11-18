package magengine.danmuku.yt;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import application.Main;
import javafx.scene.paint.Color;
import magengine.bullet.PresetColor;
import magengine.bullet.impl.ArrowBullet;
import magengine.bullet.impl.CircleBullet;
import magengine.bullet.impl.DefaultBullet;
import magengine.bullet.impl.DiamondBullet;
import magengine.bullet.impl.HexagonBullet;
import magengine.bullet.impl.SquareBullet;
import magengine.bullet.impl.StarBullet;
import magengine.chapter.util.QuickDanmuku;
import magengine.chapter.util.SeqDanmuku;
import magengine.danmuku.ADanmuku;
import magengine.game.GameSession;
import magengine.game.LogicExecutor;
import magengine.helper.OvalHelper;
import magengine.launcher.BurstLauncher;
import magengine.launcher.Launcher;
import magengine.launcher.OvalLauncherGroup;
import magengine.util.ElementUtils;

public class DirectPlayerDanmuku extends ADanmuku {
	LogicExecutor ses = LogicExecutor.getLogicExecutor();
	private int callCount = 0;
	private int midX = 250;
	private int midY = 150;
	private ElementUtils mEU = getmEU();
	private Random r = GameSession.rand();
	public static final long DURATION = 12100;
	private QuickDanmuku quick = QuickDanmuku.getQuickDanmuku();
	@Override
	public void executeDanmuku() {
		
		int lcount = 12;
		for (int i = 0; i < lcount; i++) {
			rotateDSnipe(midX, midY, 700, 2 * Math.PI / lcount * i);
		}
		
		for (int i = 0; i < lcount; i++) {
			rotateDSnipe(midX, midY, 14000, 2 * Math.PI / lcount * (lcount-i));
		}
		
	
		RandomCircleLauncher( midX, midY,  Math.PI/2, 750, DURATION);

	
	
	}
	
	
	
	public void rotateDSnipe(double midX, double midY, long startTime,double angle){
		Random r=new Random();
		double R = 30;
		double speed = 40;
		int currentHelperCount = callCount++;
		long duration=12100;
		
		OvalHelper tHelper = new OvalHelper(midX, midY, R, speed, angle);
		
		Launcher launcher = new Launcher(100, 100, 0.5*Math.PI, 220, duration);
		
		launcher.getDirectionProperty().bind(tHelper.getDirectionProperty());
		launcher.getxProperty().bind(tHelper.getxProperty());
		launcher.getyProperty().bind(tHelper.getyProperty());
		
		launcher.setBulletEvent((sEX, bullet) -> {
			sEX.schedule(() -> {
			

				bullet.setVelocityX(-bullet.getVelocityX()*0.01);
				bullet.setVelocityY(-bullet.getVelocityY()*0.01);
			}, 500, TimeUnit.MILLISECONDS);
			
			sEX.schedule(() -> {
				bullet.setVelocityX(-bullet.getVelocityX()*180);
				bullet.setVelocityY(-bullet.getVelocityY()*180);
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
				quick.VRotate(bullet,Math.PI+Math.PI);
				
			}, 3200, TimeUnit.MILLISECONDS);
			
			
			sEX.schedule(() -> {
				bullet.setVelocityX(-bullet.getVelocityX()*0.01);
				bullet.setVelocityY(-bullet.getVelocityY()*0.01);
				quick.VRotate(bullet,Math.PI/2+Math.PI);
				
			}, 5200, TimeUnit.MILLISECONDS);
			
			
			sEX.schedule(() -> {
				bullet.setVelocityX(-bullet.getVelocityX()*100);
				bullet.setVelocityY(-bullet.getVelocityY()*100);
				quick.VRotate(bullet,Math.PI);
				
			}, 5700, TimeUnit.MILLISECONDS);
			
			
			sEX.schedule(() -> {
				bullet.setVelocityX(-bullet.getVelocityX()*0.01);
				bullet.setVelocityY(-bullet.getVelocityY()*0.01);
				quick.VRotate(bullet,Math.PI/3);
				
			}, 5200+1200, TimeUnit.MILLISECONDS);
			
			
			
			
			sEX.schedule(() -> {
				bullet.setVelocityX(-bullet.getVelocityX()*100);
				bullet.setVelocityY(-bullet.getVelocityY()*100);
				quick.VRotateRandom(bullet,2*Math.PI);
			}, 9800+5700, TimeUnit.MILLISECONDS);

			
		});
		
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
	
	
	public void RandomCircleLauncher(double midX, double midY, double direction, long interval, long duration){
		Launcher launcher = new Launcher(midX, midY, direction, interval, duration);
	
		launcher.setBulletType(CircleBullet.class);
		launcher.setBulletSpeed(300.0);
		launcher.setBulletConfig((b)->{
			((CircleBullet)b).setR(25);
			((CircleBullet)b).setColorSupplier(PresetColor.redOpacity.get());
		});
		
		launcher.setBulletEvent((sesx,bullet)->{
			LogicExecutor.getLogicExecutor().schedule(() -> {
//				quick.VRotateRandom(bullet, Math.PI/2);
			quick.VToByDirection(bullet, quick.getPlayerDirectionAngle(bullet));
			}, 500, TimeUnit.MILLISECONDS);
		});
		
		LogicExecutor.getLogicExecutor().schedule(() -> {
			mEU.add("launcher", launcher);
		}, 300, TimeUnit.MILLISECONDS);
		
	}

	
}

