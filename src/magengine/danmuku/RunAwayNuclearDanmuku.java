package magengine.danmuku;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import application.Main;
import javafx.scene.paint.Color;
import magengine.bullet.PresetColor;
import magengine.bullet.impl.CircleBullet;
import magengine.bullet.impl.HexagonBullet;
import magengine.chapter.util.QuickDanmuku;
import magengine.element.BaseElement;
import magengine.helper.OvalHelper;
import magengine.launcher.Launcher;
import magengine.launcher.OvalLauncherGroup;
import magengine.util.ElementUtils;

public class RunAwayNuclearDanmuku extends ADanmuku {
	private double bulletSpeed=100;
	private ElementUtils mEU = getmEU();
	private Random r = new Random();
	private QuickDanmuku quick = QuickDanmuku.getQuickDanmuku();
	private static final long interval = (long) (100*(1.0/Main.level.getFactor()));
	private static final double rotateSpeed = 10;
	public static final long DURATION = 15000;
	
	
	@Override
	public void executeDanmuku() {
		setRandomRotateLauncher(getSourceElement(), true, DURATION, 0);
		setRandomRotateLauncher(getSourceElement(), true, DURATION, Math.PI/3*2);
		setRandomRotateLauncher(getSourceElement(), true, DURATION, Math.PI/3*4);
		callOLG(0);
		callOLG(DURATION/4);
		callOLG(DURATION/4*2);
		callOLG(DURATION/4*3);
		callOLG(DURATION/4*4);
	}
	public void callOLG(long delay){
		OvalLauncherGroup olg = new OvalLauncherGroup(sourceElement.getX(), sourceElement.getY(), 16);
		olg.setLauncherConfig((l)->{
			l.setBulletType(CircleBullet.class);
			l.setBulletSpeed(200);
			l.setBulletConfig((b)->{
				((CircleBullet)b).setR(20);
				((CircleBullet)b).setColorSupplier(PresetColor.whiteOpacity.get());
			});
			quick.bindToWantBeRemoved(l, getSourceElement());
		});
		olg.delayExecute(delay);
	}
	private void setRandomRotateLauncher(BaseElement target,boolean positive,long duration,double startAngle){
		OvalHelper helper = new OvalHelper(target.getX(),target.getY() , 1,rotateSpeed , startAngle, duration);
		helper.setPositive(positive);
		quick.bindToWantBeRemoved(helper, getSourceElement());
		mEU.add("circle "+r.nextInt(), helper);
		Launcher l = new Launcher(target.getX(), target.getY(), 0, interval, duration);
		l.setBulletSpeed(bulletSpeed);
		l.setBulletType(CircleBullet.class);
		quick.bindToXY(l, target);
		l.getDirectionProperty().bind(helper.getDirectionProperty());
		l.setBulletConfig((b)->{
			((CircleBullet)b).setR(10);
			((CircleBullet)b).setColorSupplier(PresetColor.redOpacity.get());
//			((HexagonBullet)b).setR(10);
//			((HexagonBullet)b).setColorSupplier(PresetColor.whiteOpacity.get());
			quick.VRotateRandom(b, Math.PI/7);
		});
		quick.bindToWantBeRemoved(l, getSourceElement());
		mEU.add("launcher "+r.nextInt(), l);
	}
}
