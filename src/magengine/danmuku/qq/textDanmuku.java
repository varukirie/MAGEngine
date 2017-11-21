package magengine.danmuku.qq;

import java.util.concurrent.TimeUnit;

import javax.crypto.AEADBadTagException;
import javax.print.attribute.SetOfIntegerSyntax;

import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;

import javafx.scene.paint.Color;
import javafx.scene.paint.Stop;
import magengine.bullet.PresetColor;
import magengine.bullet.impl.CircleBullet;
import magengine.bullet.impl.FllowerBullet;
import magengine.bullet.impl.HexagonBullet;
import magengine.bullet.impl.LongHexagonBullet;
import magengine.bullet.impl.PlayerBullet;
import magengine.chapter.util.QuickDanmuku;
import magengine.danmuku.ADanmuku;
import magengine.helper.OvalHelper;
import magengine.launcher.ArcLauncherGroup;
import magengine.launcher.Launcher;
import magengine.launcher.OvalLauncherGroup;
import magengine.util.ElementUtils;

public class textDanmuku extends ADanmuku{
	public static final long DURATION = 8000;
	private int midX = 300;
	private int midY = 200;
	private int speed = 60;
	private int delayTime = 200;
	ElementUtils mEU = getmEU();
	
	@Override
	public void executeDanmuku() {
		// TODO Auto-generated method stub
		Stop[] YellowAndGreenstops = new Stop[]{new Stop(0, Color.YELLOW), new Stop(1, Color.GREEN)};
		Stop[] RedAndOrangestops = new Stop[]{new Stop(1, Color.RED), new Stop(0, Color.ORANGE)};
		Stop[] BlueAndWhitestops = new Stop[]{new Stop(1, Color.BLUE), new Stop(0, Color.WHITE)};
		
		followBulletLauncher(midX+200, midY-50, 2*Math.PI/3, speed, DURATION, BlueAndWhitestops);
		aroundFollowCircleBulletLauncher(midX, midY, Math.PI/2, speed, DURATION, RedAndOrangestops);
		followLongBulletLauncher(midX, midY, Math.PI/2, DURATION, YellowAndGreenstops);
	}
	
	public void followBulletLauncher(int midX, int midY, double d, int bulletSpeed, long duration2, Stop[] stops){
		Launcher followLauncher = new Launcher(midX, midY, d,800, duration2);
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
		QuickDanmuku quick = QuickDanmuku.getQuickDanmuku();
		OvalLauncherGroup oLG = new OvalLauncherGroup(midX, midY, 20);
		oLG.setDuration(DURATION);
		oLG.setLauncherConfig((aroundFollowLauncher)->{
			aroundFollowLauncher.setBulletType(CircleBullet.class);
			aroundFollowLauncher.setBulletEvent((executor, bullet)->{
				executor.schedule(()->{
					((CircleBullet)bullet).setR(10);
					((CircleBullet)bullet).setColorSupplier(PresetColor.getByStops(stops));
					quick.VToByDirection(bullet, quick.getPlayerDirectionAngle(bullet));
				}, 5000 ,TimeUnit.MILLISECONDS);
			});//setBulletEvent
		});//setLauncherConfig
		
		oLG.delayExecute(500);
	}//public void 
	
	public void followLongBulletLauncher(int midX, int midY, double direction, long duration, Stop[] stops){
		QuickDanmuku quick = QuickDanmuku.getQuickDanmuku();
		OvalLauncherGroup oLGForLongBullet = new OvalLauncherGroup(midX+200, midY-50);
		oLGForLongBullet.setLauncherConfig((longBulletLauncher)->{
			longBulletLauncher.setBulletType(LongHexagonBullet.class);
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
