package magengine.danmuku.gs;

import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import application.Main;
import magengine.bullet.Bullet;
import magengine.bullet.impl.ArrowBullet;
import magengine.bullet.impl.CircleBullet;
import magengine.bullet.impl.DefaultBullet;
import magengine.bullet.impl.DiamondBullet;
import magengine.bullet.impl.EllipseBullet;
import magengine.bullet.impl.StarBullet;
import magengine.chapter.util.QuickDanmuku;
import magengine.chapter.util.SeqDanmuku;
import magengine.danmuku.ADanmuku;
import magengine.element.BaseElement;
import magengine.element.impl.Player;
import magengine.enemy.Enemy1;
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
import static java.lang.Math.PI;

public class Testing extends ADanmuku {
final Player p = Player.getPlayer();
@Override
public void executeDanmuku(){
	
	ElementUtils mEU = getmEU();
	QuickDanmuku quick = QuickDanmuku.getQuickDanmuku();
	
	
	
	final double starlength= 100;//默认边长
//	double x = 275,y = 195;
//	long delay = 1500 ,interval = 200;
//	long runningtime = 1500;
//	int lineDensity = 100;
//	int bulletSpeed = 3;
	double[][] starPoint = {{0,starlength*triFunc(18,2)},
			{-starlength*triFunc(36,2),-starlength*triFunc(36,2)*triFunc(18,3)+starlength*triFunc(18,2)},
			{starlength+starlength*triFunc(18,1), 0},
			{-starlength-starlength*triFunc(18,1), 0},
			{starlength*triFunc(36,2), -starlength*triFunc(36,2)*triFunc(18,3)+starlength*triFunc(18,2)},
			{ 0, starlength*triFunc(18,2)}};
	
	
	
	
//	Launcher launcher = new Launcher(starPoint[0][0], starPoint[0][1], Math.PI / 2, 100, delay+5*(runningtime+interval));
//	launcher.setBulletSpeed(bulletSpeed*0.1);
//	launcher.setInterval(lineDensity);
//	launcher.setBulletType(DiamondBullet.class);
	
//	Helper helper = new Helper(starPoint[0][0],starPoint[0][1]);
	
	
//	launcher.getxProperty().bind(helper.getxProperty());
//	launcher.getyProperty().bind(helper.getyProperty());
	
//	launcher.setBulletEvent((executor,bullet)->{
//		executor.schedule(()->{
//			quick.runBullet(bullet);
//			quick.VTo(bullet, p.getX() , p.getY());
//		}, delay+5*(runningtime+interval), TimeUnit.MILLISECONDS);
//	});
//	LogicExecutor.getLogicExecutor().schedule(()->{
//		quick.moveTo(helper, runningtime, starPoint[1][0],starPoint[1][1] );
//	},delay,TimeUnit.MILLISECONDS);
//	LogicExecutor.getLogicExecutor().schedule(() -> {
//		quick.moveTo(helper, runningtime, starPoint[2][0], starPoint[2][1]);
//	}, delay+(runningtime+interval), TimeUnit.MILLISECONDS);
//	LogicExecutor.getLogicExecutor().schedule(() -> {
//		quick.moveTo(helper, runningtime, starPoint[3][0], starPoint[3][1]);
//	}, delay+2*(runningtime+interval), TimeUnit.MILLISECONDS);
//	LogicExecutor.getLogicExecutor().schedule(() -> {
//		quick.moveTo(helper, runningtime, starPoint[4][0], starPoint[4][1]);
//	}, delay+3*(runningtime+interval), TimeUnit.MILLISECONDS);
//	LogicExecutor.getLogicExecutor().schedule(() -> {
//		quick.moveTo(helper, runningtime, starPoint[5][0], starPoint[5][1]);
//	}, delay+4*(runningtime+interval), TimeUnit.MILLISECONDS);
	starRunning(starPoint,1500,3,10,quick,mEU,1000,200,300,200,1);
	
	LogicExecutor.getLogicExecutor().schedule(()->{
		starRunning(starPoint,1500,3,10,quick,mEU,1000,200,150,350,0.5);
	},9000,TimeUnit.MILLISECONDS);
	LogicExecutor.getLogicExecutor().schedule(()->{
		starRunning(starPoint,1500,3,10,quick,mEU,1000,200,450,350,0.5);
	},19000,TimeUnit.MILLISECONDS);
	
	
	
}
public void starRunning(double[][] starPoint,long delay,int bulletSpeed,int lineDensity,QuickDanmuku quick,ElementUtils mEU,long runningtime,long interval,double x,double y,double size){
	Helper helper = new Helper((starPoint[0][0]*size+x),(starPoint[0][1]*size+y));
	Launcher launcher = new Launcher((starPoint[0][0]*size+x), (starPoint[0][1]*size+y), Math.PI / 2, 100, delay+5*(runningtime+interval));
	launcher.getxProperty().bind(helper.getxProperty());
	launcher.getyProperty().bind(helper.getyProperty());
	launcher.setBulletSpeed(bulletSpeed*0.1);
	launcher.setInterval(lineDensity);
	launcher.setBulletType(DiamondBullet.class);
	
	
	
	
	LogicExecutor.getLogicExecutor().schedule(()->{
		mEU.add("testlauncher", launcher);
	}, 1000, TimeUnit.MILLISECONDS);
	
	launcher.setBulletEvent((executor,bullet)->{
		executor.schedule(()->{
			quick.runBullet(bullet);
			quick.VTo(bullet, p.getX() , p.getY());
		}, delay+5*(runningtime+interval), TimeUnit.MILLISECONDS);
	});
	
	
	
	
	LogicExecutor.getLogicExecutor().schedule(()->{
		quick.moveTo(helper, runningtime, (starPoint[1][0]*size+x),(starPoint[1][1]*size+y) );
	},delay,TimeUnit.MILLISECONDS);
	LogicExecutor.getLogicExecutor().schedule(() -> {
		quick.moveTo(helper, runningtime, (starPoint[2][0]*size+x), (starPoint[2][1]*size+y));
	}, delay+(runningtime+interval), TimeUnit.MILLISECONDS);
	LogicExecutor.getLogicExecutor().schedule(() -> {
		quick.moveTo(helper, runningtime, (starPoint[3][0]*size+x), (starPoint[3][1]*size+y));
	}, delay+2*(runningtime+interval), TimeUnit.MILLISECONDS);
	LogicExecutor.getLogicExecutor().schedule(() -> {
		quick.moveTo(helper, runningtime, (starPoint[4][0]*size+x), (starPoint[4][1]*size+y));
	}, delay+3*(runningtime+interval), TimeUnit.MILLISECONDS);
	LogicExecutor.getLogicExecutor().schedule(() -> {
		quick.moveTo(helper, runningtime, (starPoint[5][0]*size+x), (starPoint[5][1]*size+y));
	}, delay+4*(runningtime+interval), TimeUnit.MILLISECONDS);
}
public double triFunc(double a,int type){
	if(type==1)
		return Math.sin(a*Math.PI/180);
	else if(type==2)
		return Math.cos(a*Math.PI/180);
	else
		return 1/Math.tan(a*Math.PI/180);
}
}
