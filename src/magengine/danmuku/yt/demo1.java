package magengine.danmuku.yt;

import static java.lang.Math.PI;

import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import application.Main;
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
import magengine.launcher.yt.RayLauncher;
import magengine.launcher.yt.Arc3;
import magengine.launcher.yt.LineLauncherGroup;
import magengine.paint.MyCanvas;
import magengine.util.ElementUtils;

public class demo1 extends ADanmuku  {


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

		
		/**
		 * 从左到右一排扫射
		 */
	
		 LineLauncher(1, 250, 0, 400, 8000);
      
		/**
		 * 弧形发射组
		 */
		new ArcLauncherGroup(midX+100, midY, Math.PI/2+Math.PI/7, Math.PI/3, 6).delayExecute(1500);
		new ArcLauncherGroup(midX+100, midY, Math.PI/2+Math.PI/7, Math.PI/3, 6).delayExecute(2500);
		new ArcLauncherGroup(midX+100, midY, Math.PI/2-Math.PI/7, Math.PI/3, 6).delayExecute(3500);
		new ArcLauncherGroup(midX+100, midY, Math.PI/2-Math.PI/7, Math.PI/3, 6).delayExecute(4500);
		
		/**
		 * 射线组
		 * 
		 */
		new RayLauncher(0,0,20).delayExecute(100);;
		new RayLauncher(midX-150,midY,20).delayExecute(100);
		new RayLauncher(1,midY-100,20).delayExecute(100);;

		
		new RayLauncher(0,0,20).delayExecute(1000);;
		new RayLauncher(midX+350,midY-200,100).delayExecute(50000);
		new RayLauncher(1,midY-100,20).delayExecute(15000);
		
		
		/**
		 * 折角四周散开
		 * 
		 * 
		 */
		
		
		new LineLauncherGroup(0).delayExecute(19500);
		new LineLauncherGroup(Math.PI/2).delayExecute(19500);
		new LineLauncherGroup(2*Math.PI/2).delayExecute(19500);
		new LineLauncherGroup(3*Math.PI/2).delayExecute(19500);
		
		new LineLauncherGroup(Math.PI/4).delayExecute(20500);
		new LineLauncherGroup(3*Math.PI/4).delayExecute(20500);
		new LineLauncherGroup(5*Math.PI/4).delayExecute(20500);
		new LineLauncherGroup(7*Math.PI/4).delayExecute(20500);
		
		new LineLauncherGroup(0).delayExecute(21500);
		new LineLauncherGroup(Math.PI/2).delayExecute(22500);
		new LineLauncherGroup(2*Math.PI/2).delayExecute(22500);
		new LineLauncherGroup(3*Math.PI/2).delayExecute(22500);
		
		new LineLauncherGroup(Math.PI/4).delayExecute(23500);
		new LineLauncherGroup(3*Math.PI/4).delayExecute(23500);
		new LineLauncherGroup(5*Math.PI/4).delayExecute(23500);
		new LineLauncherGroup(7*Math.PI/4).delayExecute(23500);
		
		
		new LineLauncherGroup(0).delayExecute(24500);
		new LineLauncherGroup(Math.PI/2).delayExecute(24500);
		new LineLauncherGroup(2*Math.PI/2).delayExecute(24500);
		new LineLauncherGroup(3*Math.PI/2).delayExecute(24500);
		
		
		new LineLauncherGroup(Math.PI/4).delayExecute(25500);
		new LineLauncherGroup(3*Math.PI/4).delayExecute(25500);
		new LineLauncherGroup(5*Math.PI/4).delayExecute(25500);
		new LineLauncherGroup(7*Math.PI/4).delayExecute(25500);
		
		
		/**
		 * 
		 * 旋转发射组
		 * 
		 * 
		 */
		
	
		setRandomRotateLauncher(getSourceElement(), true, duration, 0);
		setRandomRotateLauncher(getSourceElement(), true, duration, Math.PI/3*2);
		setRandomRotateLauncher(getSourceElement(), true, duration, Math.PI/3*4);
	
		
		new Arc3(200,150,CircleBullet.class).delayExecute(2500);
		new Arc3(200,150, Math.PI/2).delayExecute(2500);
		
		new Arc3(200,150, Math.PI/2, Math.PI/3, 5).delayExecute(35000);
		new Arc3(200,150, Math.PI/2, Math.PI/3, 5, 6).delayExecute(45000);
		new Arc3(200,150, Math.PI/2, Math.PI/3, 5, 6,CircleBullet.class).delayExecute(50000);
		new Arc3(200,150, Math.PI/2, Math.PI/3, 15, 12,CircleBullet.class).delayExecute(50000);
		
		callOLG(duration/4*10);
		callOLG(duration/4*11);
		callOLG(duration/4*12);

		
		 callOLG(midX,midY,20,30000);
		 callOLG(midX,midY,20,31000);
		 callOLG(midX,midY,20,32000);
				
		 
		 
		
	
}

		
		public void callOLG(long delay){
			OvalLauncherGroup olg = new OvalLauncherGroup(sourceElement.getX(), sourceElement.getY(), 16);
			olg.setLauncherConfig((l)->{
				l.setBulletType(CircleBullet.class);
				l.setBulletSpeed(200);
			
			});
			olg.delayExecute(delay);
		}
		
		
	public void callOLG(double x,double y ,int amount,long delay) {
		
		OvalLauncherGroup olg = new OvalLauncherGroup(x, y,amount);
		olg.setLauncherConfig((launcher) -> {
			launcher.setBulletType(DiamondBullet.class);
			launcher.setBulletEvent((sESx, bullet) -> {
				sESx.schedule(() -> {
					quick.stopBullet(bullet);
				}, 1800, TimeUnit.MILLISECONDS);
				sESx.schedule(() -> {
					quick.runBullet(bullet);
					bullet.setVelocityX(-bullet.getVelocityX());
					bullet.setVelocityY(-bullet.getVelocityY());
				}, 5000, TimeUnit.MILLISECONDS);
			});
		});
		olg.delayExecute(delay);

		
		
	}



		
		private void setRandomRotateLauncher(BaseElement target,boolean positive,long duration,double startAngle){
			OvalHelper helper = new OvalHelper(target.getX(),target.getY() , 1,rotateSpeed , startAngle, duration);
			helper.setPositive(positive);
			mEU.add("circle "+r.nextInt(), helper);
			Launcher l = new Launcher(target.getX()-300, target.getY()-100, 0, interval+100, duration);
			l.setBulletSpeed(bulletSpeed);
			l.setBulletType(CircleBullet.class);
			l.getDirectionProperty().bind(helper.getDirectionProperty());
			l.setBulletConfig((b)->{
				((CircleBullet)b).setR(5);
			});
			mEU.add("launcher "+r.nextInt(), l);
		}
	

		public void LineLauncher(double x, double y, double direction, long interval, long duration){
			Launcher launcher  = new Launcher(x, y, direction, interval, duration);
			launcher.setBulletType(Launcher.class);
			launcher.setVelocityX(120);
			launcher.setBulletSpeed(0);
			launcher.setBulletConfig((launcherN)->{
				Launcher ln = (Launcher) launcherN;
				ln.setDuration(quick.getSyncDelayAfterLaunch(launcher, 10000));
				ln.setDirection(Math.PI/2);
				ln.setBulletSpeed(100);
				ln.setInterval(100000);
				ln.setVelocityX(30);
			});
			launcher.setBulletEvent((sesx,launcherN)->{
				Launcher ln = (Launcher) launcherN;
				ses.schedule(() -> {
					ln.setInterval(1000);
				}, quick.getSyncDelayAfterLaunch(launcher, 50), TimeUnit.MILLISECONDS);
				
				
				
			});
			mEU.add("launcher", launcher);
			
			
		}

		
	
		
		
	
}

