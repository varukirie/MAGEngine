package magengine.danmuku;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import application.Main;
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
	private static final long duration = 15000;
	
	
	@Override
	public void executeDanmuku() {
		setRandomRotateLauncher(getSourceElement(), true, duration, 0);
		setRandomRotateLauncher(getSourceElement(), true, duration, Math.PI/3*2);
		setRandomRotateLauncher(getSourceElement(), true, duration, Math.PI/3*4);
		OvalLauncherGroup olg = new OvalLauncherGroup(sourceElement.getX(), sourceElement.getY(), 16);
		olg.setLauncherConfig((l)->{
			l.setBulletSpeed(200);
		});
		olg.delayExecute(0);
		olg.delayExecute(duration/4);
		olg.delayExecute(duration/4*2);
		olg.delayExecute(duration/4*3);
		olg.delayExecute(duration/4*4);
	}

	private void setRandomRotateLauncher(BaseElement target,boolean positive,long duration,double startAngle){
		OvalHelper helper = new OvalHelper(target.getX(),target.getY() , 1,rotateSpeed , startAngle, duration);
		helper.setPositive(positive);
		mEU.add("circle "+r.nextInt(), helper);
		Launcher l = new Launcher(target.getX(), target.getY(), 0, interval, duration);
		l.setBulletSpeed(bulletSpeed);
		quick.bindToXY(l, target);
		l.getDirectionProperty().bind(helper.getDirectionProperty());
		l.setBulletConfig((b)->{
			quick.VRotateRandom(b, Math.PI/7);
		});
		mEU.add("launcher "+r.nextInt(), l);
	}
}
