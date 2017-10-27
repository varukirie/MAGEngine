package magengine.danmuku.yt;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import application.Main;
import magengine.bullet.PresetColor;
import magengine.bullet.impl.ArrowBullet;
import magengine.bullet.impl.CircleBullet;
import magengine.bullet.impl.DefaultBullet;
import magengine.chapter.util.QuickDanmuku;
import magengine.chapter.util.SeqDanmuku;
import magengine.danmuku.ADanmuku;
import magengine.game.LogicExecutor;
import magengine.helper.OvalHelper;
import magengine.launcher.ArcLauncherGroup;
import magengine.launcher.BurstLauncher;
import magengine.launcher.Launcher;
import magengine.launcher.OvalLauncherGroup;
import magengine.util.ElementUtils;

public class RotateDanmuku extends ADanmuku {
	LogicExecutor ses = LogicExecutor.getLogicExecutor();
	private int count = 0;
	private int midX = 250;
	private int midY = 150;
	private ElementUtils mEU = getmEU();
	private Random r = new Random();
	private QuickDanmuku quick = QuickDanmuku.getQuickDanmuku();
	
	@Override
	public void executeDanmuku() {
		
		int lcount = 7;
		for (int i = 0; i < lcount; i++) {
			rotateDSnipe(midX, midY, 700, 2 * Math.PI / lcount * i,1);
		}
		
		for (int i = 0; i < lcount; i++) {
			rotateDSnipe(midX, midY, 9750,2 * Math.PI / lcount * i,-1);
		}
		


		int num = 11;
		for(int i=0;i<num+1;i++) {
			 ArcLauncherGroup(midX,midY, i*Math.PI/6, Math.PI/3, 5,3,CircleBullet.class,700);
		}
		
		for(int i=0;i<num+6;i++) {
			 ArcLauncherGroup(midX,midY, i*Math.PI/3, Math.PI/3, 5,3,CircleBullet.class,4500);
		}
		
		for(int i=0;i<num+1;i++) {
			 ArcLauncherGroup(midX,midY, i*Math.PI/4, Math.PI/3, 5,3,CircleBullet.class,9500);
		}
			

			 ArcLauncherGroup(midX,midY,Math.PI/2, Math.PI/3, 5,3,CircleBullet.class,15000);
			 ArcLauncherGroup(midX,midY,Math.PI/6, Math.PI/3, 5,3,CircleBullet.class,19500);
			 ArcLauncherGroup(midX,midY,Math.PI, Math.PI/2, 5,3,CircleBullet.class,21000);
			 
			 for(int i=0;i<num+1;i++) {
				 ArcLauncherGroup(midX,midY, i*Math.PI/4, Math.PI/3, 5,3,CircleBullet.class,27500);
			}
				
		
	}
	

	public void rotateDSnipe(double midX, double midY, long startTime,double angle,int RL){
		Random r=new Random();
		double R = 30;
		double speed = 40;
		int helperCount = count++;
		long duration=8000;
		
		OvalHelper helper = new OvalHelper(midX, midY, R, speed, angle);
		
		Launcher launcher = new Launcher(100, 100, 0.5*Math.PI, 220, duration);
		
		launcher.getDirectionProperty().bind(helper.getDirectionProperty());
		launcher.getxProperty().bind(helper.getxProperty());
		launcher.getyProperty().bind(helper.getyProperty());
		
		
		launcher.setBulletEvent((sEX, bullet) -> {
			sEX.schedule(() -> {
				bullet.setVelocityX(bullet.getVelocityX()*0.01);
				bullet.setVelocityY(bullet.getVelocityY()*0.01);
			}, 1000, TimeUnit.MILLISECONDS);
			
			sEX.schedule(() -> {
				bullet.setVelocityX(bullet.getVelocityX()*180);
				bullet.setVelocityY(bullet.getVelocityY()*180);
				quick.VRotate(bullet, RL*Math.PI);
				
			}, 3500, TimeUnit.MILLISECONDS);
			
			sEX.schedule(() -> {
				bullet.setVelocityX(bullet.getVelocityX()*0.01);
				bullet.setVelocityY(bullet.getVelocityY()*0.01);
				quick.VRotate(bullet, RL*Math.PI/3);
				
			}, 4700, TimeUnit.MILLISECONDS);
			
			
			sEX.schedule(() -> {
				bullet.setVelocityX(bullet.getVelocityX()*100);
				bullet.setVelocityY(bullet.getVelocityY()*100);
				quick.VRotate(bullet,RL*Math.PI);
				
			}, 7200, TimeUnit.MILLISECONDS);
			
			
			sEX.schedule(() -> {
				bullet.setVelocityX(bullet.getVelocityX()*0.01);
				bullet.setVelocityY(bullet.getVelocityY()*0.01);
				quick.VRotate(bullet, RL*Math.PI/3);
				
			}, 8100, TimeUnit.MILLISECONDS);
			
			sEX.schedule(() -> {
				bullet.setVelocityX(bullet.getVelocityX()*100);
				bullet.setVelocityY(bullet.getVelocityY()*100);
				quick.VRotate(bullet,RL*Math.PI/3);
				
			}, 9800, TimeUnit.MILLISECONDS);
			
			
			
			
		});
		
		LogicExecutor.getLogicExecutor().schedule(() -> {
			mEU.add("helper"+helperCount, helper);
		}, startTime, TimeUnit.MILLISECONDS);
		
		LogicExecutor.getLogicExecutor().schedule(() -> {
			mEU.add("launcher"+count++, launcher);
		}, startTime+1, TimeUnit.MILLISECONDS);
		
		LogicExecutor.getLogicExecutor().schedule(() -> {
			mEU.removeBoth("helper" + helperCount);
		}, startTime+duration+2, TimeUnit.MILLISECONDS);
	}
	
	/**
	 * 
	 * @param midX
	 * @param midY
	 * @param direction 方向
	 * @param angle 弧角度
	 * @param lineAmount 发射组列数
	 * @param columnAmount 发射组行数
	 * @param bulletType 子弹类型
	 */
	private void ArcLauncherGroup(double midX, double midY,double direction, double angle, int lineAmount,int columnAmount,Class<?> bulletType ,long  launcherTime) {
		for (int i = 0; i < columnAmount; i++) {

			ArcLauncherGroup l = new ArcLauncherGroup(midX,midY, direction, angle, lineAmount);
		 
			l.setLauncherConfig((launcher) -> {
			
				launcher.setBulletType(bulletType);
				
			launcher.setBulletEvent((sESx, bullet) -> {
				
					((CircleBullet)bullet).setR(7);
					((CircleBullet)bullet).setColorSupplier(PresetColor.redOpacity.get());
					
					sESx.schedule(() -> {
						quick.runBullet(bullet);
						}, 10000, TimeUnit.MILLISECONDS);
					
					
			});
		});
			l.delayExecute(launcherTime+i*200);
		}
		
	}
	
	
}
