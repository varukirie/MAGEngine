package magengine.danmuku.qq;

import java.util.concurrent.TimeUnit;

import javafx.scene.paint.Color;
import javafx.scene.paint.Stop;
import magengine.bullet.PresetColor;
import magengine.bullet.impl.CircleBullet;
import magengine.bullet.impl.LongHexagonBullet;
import magengine.chapter.util.QuickDanmuku;
import magengine.danmuku.ADanmuku;
import magengine.launcher.Launcher;
import magengine.launcher.OvalLauncherGroup;
import magengine.util.ElementUtils;

public class TrackDemoDanmuku extends ADanmuku{
	public static final long DURATION = 8000;
	private int midX = 300;
	private int midY = 200;
	private int speed = 60;
	private int delayTime = 200;
	ElementUtils mEU = getmEU();
	
	@Override
	public void executeDanmuku() {
		// TODO Auto-generated method stub
		Stop[] YellowAndGreenstops = new Stop[]{new Stop(1, Color.YELLOW), new Stop(0, Color.GREEN)};
		Stop[] RedAndOrangestops = new Stop[]{new Stop(1, Color.RED), new Stop(0, Color.ORANGE)};
		Stop[] BlueAndWhitestops = new Stop[]{new Stop(1, Color.BLUE), new Stop(0, Color.WHITE)};
		
		followBulletLauncher(midX+200, midY-50, 2*Math.PI/3, speed+40, DURATION, YellowAndGreenstops);
		aroundFollowCircleBulletLauncher(midX, midY, Math.PI/2, speed, DURATION, BlueAndWhitestops);
		followLongBulletLauncher(midX, midY, Math.PI/2, DURATION, YellowAndGreenstops);
	}
	
	public void followBulletLauncher(int midX, int midY, double d, int bulletSpeed, long duration2, Stop[] stops){
		/*
		 * 跟踪子弹：从boss中心发射
		 */
		Launcher followLauncher = new Launcher(midX, midY, d, 600, duration2);
		followLauncher.getxProperty().bind(getSourceElement().getxProperty());
		followLauncher.getyProperty().bind(getSourceElement().getyProperty());
		followLauncher.setBulletSpeed(bulletSpeed);
		followLauncher.setBulletType(CircleBullet.class);
		followLauncher.setBulletConfig(b->{
			//设置子弹的初始配置
			((CircleBullet)b).setR(15.0);
			((CircleBullet)b).setColorSupplier(PresetColor.getByStops(stops));
		});
		QuickDanmuku quick = QuickDanmuku.getQuickDanmuku();
		followLauncher.setBulletEvent((executor, bullet)->{
			executor.schedule(()->{
				quick.VToByDirection(bullet, quick.getPlayerDirectionAngle(bullet));
			}, 0, TimeUnit.MILLISECONDS);
			
			executor.schedule(()->{
				quick.VToByDirection(bullet, quick.getPlayerDirectionAngle(bullet));
			}, 1000, TimeUnit.MILLISECONDS);
		});
		mEU.add("followLauncher", followLauncher);
	}
	
	public void aroundFollowCircleBulletLauncher(int midX, int midY, double d, int bulletSpeed, long duration2, Stop[] stops){
		/*
		 * 环形跟踪子弹，5秒之后显现
		 */
		QuickDanmuku quick = QuickDanmuku.getQuickDanmuku();
		OvalLauncherGroup oLG = new OvalLauncherGroup(midX, midY, 20);
		oLG.setDuration(DURATION);
		oLG.setLauncherConfig((aroundFollowLauncher)->{
//			aroundFollowLauncher.bindToXY(getSourceElement());
			aroundFollowLauncher.setBulletType(CircleBullet.class);
			aroundFollowLauncher.setBulletConfig(b->{
				((CircleBullet)b).setR(8);
			});
			aroundFollowLauncher.setBulletEvent((executor, bullet)->{
				executor.schedule(()->{
					((CircleBullet)bullet).setR(15);
					((CircleBullet)bullet).setColorSupplier(PresetColor.getByStops(stops));
//					quick.VToByDirection(bullet, quick.getPlayerDirectionAngle(bullet));
					quick.stopBullet(bullet);
				}, 4000 ,TimeUnit.MILLISECONDS);
				executor.schedule(()->{
//					((CircleBullet)bullet).setR(15);
//					((CircleBullet)bullet).setColorSupplier(PresetColor.getByStops(stops));
					quick.runBullet(bullet);
					quick.VToByDirection(bullet, quick.getPlayerDirectionAngle(bullet));
				}, 5000 ,TimeUnit.MILLISECONDS);
			});//setBulletEvent
		});//setLauncherConfig
		
		oLG.delayExecute(500);
	}//public void 
	
	public void followLongBulletLauncher(int midX, int midY, double direction, long duration, Stop[] stops){
		/*
		 * 条形环状子弹
		 */
		QuickDanmuku quick = QuickDanmuku.getQuickDanmuku();
		OvalLauncherGroup oLGForLongBullet = new OvalLauncherGroup(midX, midY);
		oLGForLongBullet.setAmount(20);
		oLGForLongBullet.setLauncherConfig((longBulletLauncher)->{
			longBulletLauncher.bindToXY(getSourceElement());
			longBulletLauncher.setBulletType(LongHexagonBullet.class);
			longBulletLauncher.setInterval(300);
			longBulletLauncher.setDuration(900);
			longBulletLauncher.setBulletConfig(b->{
				((LongHexagonBullet)b).setR(3);
			});
			longBulletLauncher.setBulletEvent((sESx, bullet)->{
				sESx.schedule(()->{
					quick.setSpeed(bullet, 500);
				}, 1000, TimeUnit.MILLISECONDS);
			});
		});
		oLGForLongBullet.delayExecute(500);
	}
}

