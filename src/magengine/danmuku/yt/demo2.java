package magengine.danmuku.yt;

import static java.lang.Math.PI;

import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import com.badlogic.gdx.math.Ellipse;

import application.Main;
import magengine.bullet.Bullet;
import magengine.bullet.PresetColor;
import magengine.bullet.impl.ArrowBullet;
import magengine.bullet.impl.CircleBullet;
import magengine.bullet.impl.DefaultBullet;
import magengine.bullet.impl.DiamondBullet;
import magengine.bullet.impl.EllipseBullet;
import magengine.bullet.impl.HexagonBullet;
import magengine.bullet.impl.SquareBullet;
import magengine.bullet.impl.StarBullet;
import magengine.chapter.util.QuickDanmuku;
import magengine.chapter.util.SeqDanmuku;
import magengine.danmuku.ADanmuku;
import magengine.element.BaseElement;
import magengine.game.LogicExecutor;
import magengine.helper.Helper;
import magengine.helper.OvalHelper;
import magengine.helper.PolygonalLineHelper;
import magengine.launcher.ALauncherGroup;
import magengine.launcher.ArcLauncherGroup;
import magengine.launcher.BulletEvent;
import magengine.launcher.Launcher;
import magengine.launcher.OvalLauncherGroup;
import magengine.launcher.yt.Arc3LauncherGroup;
import magengine.launcher.yt.LineLauncherGroup;
import magengine.paint.MyCanvas;
import magengine.util.ElementUtils;

public class demo2 extends ADanmuku  {


	LogicExecutor ses = LogicExecutor.getLogicExecutor();
	private int midX = 250;
	private int midY =300;
	private double bulletSpeed=100;
	private ElementUtils mEU = getmEU();
	private Random r = new Random();
	private QuickDanmuku quick = QuickDanmuku.getQuickDanmuku();
	private static final long interval = (long) (50*(1.0/Main.level.getFactor()));
	private static final double rotateSpeed = 10;
	private static final long duration = 15000;
	

	
	
public void executeDanmuku() {
	
	//setRandomRotateLauncher(getSourceElement(), true, duration, 0);
	//setRandomRotateLauncher(getSourceElement(), true, duration, Math.PI/3*2);
	//setRandomRotateLauncher(getSourceElement(), true, duration, Math.PI/3*4);
	
	/*setRovateOvalLauncher(250,300,10,100,-1);
	setRovateOvalLauncher(250,300,10,100, 1);
	setRovateOvalLauncher(250,300,10,100,-1);
	setRovateOvalLauncher(250,300,10,100, 1);
	setRovateOvalLauncher(250,300,10,100,-1);
	setRovateOvalLauncher(250,300,10,100, 1);
	
	setRovateOvalLauncher(250,300,10,700, 1);
	setRovateOvalLauncher(250,300,10,700,-1);
	setRovateOvalLauncher(250,300,10,700, 1);
	setRovateOvalLauncher(250,300,10,700,-1);
	setRovateOvalLauncher(250,300,10,700, 1);*/
	
	setRovateOvalLauncher(250,300,10,700,-1);
	

	

}

		
private void setRandomRotateLauncher(BaseElement target,boolean positive,long duration,double startAngle){
	OvalHelper helper = new OvalHelper(250,300 , 1,rotateSpeed , startAngle, duration);
	helper.setPositive(positive);
	mEU.add("circle "+r.nextInt(), helper);
	Launcher l = new Launcher(250, 300, 0, interval, duration);
	l.setBulletSpeed(bulletSpeed);
	l.setBulletType(CircleBullet.class);
	l.getDirectionProperty().bind(helper.getDirectionProperty());
	l.setBulletConfig((b)->{
		((CircleBullet)b).setR(10);
		((CircleBullet)b).setColorSupplier(PresetColor.redOpacity.get());
		quick.VRotateRandom(b, Math.PI/7);
	});
	mEU.add("launcher "+r.nextInt(), l);
}
		 
private void setRovateOvalLauncher(double midX,double midY,int amount,int startTime,int rovate) {
	
OvalLauncherGroup oG = new OvalLauncherGroup(midX, midY,amount);
oG.setLauncherConfig((launcher) -> {
	launcher.setBulletType(CircleBullet.class);
	launcher.setBulletConfig((b)->{
		((CircleBullet)b).setR(10);
	    ((CircleBullet)b).setColorSupplier(PresetColor.blueOpacity.get());
		quick.VRotateRandom(b, rovate*Math.PI/7);
	});
	launcher.setBulletEvent((sESx, bullet) -> {
		sESx.schedule(() -> {
			quick.stopBullet(bullet);
		}, startTime, TimeUnit.MILLISECONDS);
		sESx.schedule(() -> {
			quick.runBullet(bullet);
			quick.VRotate(bullet,rovate*Math.PI/2);
		}, startTime+500, TimeUnit.MILLISECONDS);
		
		sESx.schedule(() -> {
			quick.stopBullet(bullet);
		}, startTime+1000, TimeUnit.MILLISECONDS);
		sESx.schedule(() -> {
			quick.runBullet(bullet);
			quick.VRotate(bullet,rovate*Math.PI/2);
		}, startTime+1500, TimeUnit.MILLISECONDS);
		
		sESx.schedule(() -> {
			quick.stopBullet(bullet);
		}, startTime+2500, TimeUnit.MILLISECONDS);
		sESx.schedule(() -> {
			quick.setSpeed(bullet, 250);
			quick.VRotate(bullet,rovate*Math.PI/2);
		}, startTime+3000, TimeUnit.MILLISECONDS);
		

	});
});
oG.delayExecute(500);

}
		

}

