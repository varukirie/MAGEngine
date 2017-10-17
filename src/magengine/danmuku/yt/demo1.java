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
import magengine.helper.Helper;
import magengine.helper.OvalHelper;
import magengine.helper.PolygonalLineHelper;
import magengine.launcher.ALauncherGroup;
import magengine.launcher.ArcLauncherGroup;
import magengine.launcher.BulletEvent;
import magengine.launcher.Launcher;
import magengine.launcher.OvalLauncherGroup;
import magengine.launcher.yt.RayLauncher;
import magengine.launcher.yt.LineLauncherGroup;
import magengine.paint.MyCanvas;
import magengine.util.ElementUtils;

public class demo1 extends ADanmuku  {


	ScheduledExecutorService ses = getsES();
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
	
	
}

		
		public void callOLG(long delay){
			OvalLauncherGroup olg = new OvalLauncherGroup(sourceElement.getX(), sourceElement.getY(), 16);
			olg.setLauncherConfig((l)->{
				l.setBulletType(CircleBullet.class);
				l.setBulletSpeed(200);
				l.setBulletConfig((b)->{
					((CircleBullet)b).setR(20);
					((CircleBullet)b).setColorSupplier(CircleBullet.PresetColor.blueOpacity.get());
				});
			});
			olg.delayExecute(delay);
		}
		
		private void setRandomRotateLauncher(BaseElement target,boolean positive,long duration,double startAngle){
			OvalHelper helper = new OvalHelper(target.getX(),target.getY() , 1,rotateSpeed , startAngle, duration);
			helper.setPositive(positive);
			mEU.add("circle "+r.nextInt(), helper);
			Launcher l = new Launcher(target.getX()-300, target.getY()-200, 0, interval+100, duration);
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

