package magengine.chapter;

import javafx.application.Platform;
import javafx.scene.effect.Bloom;
import magengine.bullet.ArrowBullet;
import magengine.bullet.Bullet;
import magengine.bullet.DefaultBullet;
import magengine.bullet.PlayerBullet;
import magengine.bullet.StarBullet;
import magengine.chapter.util.AChapter;
import magengine.chapter.util.QuickDanmuku;
import magengine.element.BaseElement;
import magengine.enemy.DefaultEnemy;
import magengine.enemy.Enemy1;
import magengine.launcher.Launcher;
import magengine.paint.MyCanvas;
import magengine.util.DI;
import magengine.util.ElementUtils;
import magengine.util.MyCanvasSwitcher;
import java.util.concurrent.*;
public class TestChapter1 extends AChapter {
	int midX = MyCanvas.CANVAS_WIDTH / 2;
	int midY = 200;

	@Override
	public void design(ScheduledExecutorService sES, MyCanvas staticCanvas, ElementUtils mEU) {

		Platform.runLater(() -> {
			((MyCanvasSwitcher) DI.di().get("switcher")).configCanvas(DefaultBullet.class, (canvas) -> {
				canvas.setEffect(new Bloom());
			});
			((MyCanvasSwitcher) DI.di().get("switcher")).configCanvas(StarBullet.class, (canvas) -> {
				canvas.setEffect(new Bloom());
			});
			((MyCanvasSwitcher) DI.di().get("switcher")).configCanvas(ArrowBullet.class, (canvas) -> {
				canvas.setEffect(new Bloom());
			});
			((MyCanvasSwitcher) DI.di().get("switcher")).configCanvas(PlayerBullet.class, (canvas) -> {
				canvas.setEffect(new Bloom());
			});
			staticCanvas.getGraphicsContext2D().fillRect(0, 0, MyCanvas.CANVAS_WIDTH, MyCanvas.CANVAS_HEIGHT);
		});
		double starlength= 100;
		double x = 275,y = 195;
		long delay = 1500 ,interval = 200;
		long runningtime = 1500;
		QuickDanmuku quick = QuickDanmuku.getQuickDanmuku();
		DefaultEnemy enemy = new DefaultEnemy(0+x,starlength*triFunc(18,2)+y);//敌人初始化位置有问题！！！
		Launcher launcher = new Launcher(300, 200, Math.PI / 2, 100, delay+4*(runningtime+interval));
		launcher.setBulletSpeed(0.00001);
		launcher.setInterval(10);
		launcher.setBulletType(StarBullet.class);
		mEU.add("enemy", enemy);
		mEU.add("testlauncher", launcher);
		enemy.getxProperty().bindBidirectional(launcher.getxProperty());
		enemy.getyProperty().bindBidirectional(launcher.getyProperty());
		launcher.setBulletEvent((executor,bullet)->{
			executor.schedule(()->{
				bullet.setVelocityY(50);
			}, delay+4*(runningtime+interval), TimeUnit.MILLISECONDS);
		});
		quick.moveTo(enemy, runningtime, -starlength*triFunc(36,2)+x,-starlength*triFunc(36,2)*triFunc(18,3)+starlength*triFunc(18,2)+y );
		sES.schedule(() -> {
			quick.moveTo(enemy, runningtime, starlength+starlength*triFunc(18,1)+x, 0+y);
		}, delay, TimeUnit.MILLISECONDS);
		sES.schedule(() -> {
			quick.moveTo(enemy, runningtime, -starlength-starlength*triFunc(18,1)+x, 0+y);
		}, delay+(runningtime+interval), TimeUnit.MILLISECONDS);
		sES.schedule(() -> {
			quick.moveTo(enemy, runningtime, starlength*triFunc(36,2)+x, -starlength*triFunc(36,2)*triFunc(18,3)+starlength*triFunc(18,2)+y);
		}, delay+2*(runningtime+interval), TimeUnit.MILLISECONDS);
		sES.schedule(() -> {
			quick.moveTo(enemy, runningtime, 0+x, starlength*triFunc(18,2)+y);
		}, delay+3*(runningtime+interval), TimeUnit.MILLISECONDS);
	
//		long delay = 1000;
//		double bulletspeed=0.5;
//		Launcher launcher = new Launcher(300, 200, Math.PI/2, 10, 10000);
//		launcher.setBulletType(StarBullet.class);
//		launcher.setBulletEvent((executor, bullet) -> {
//			executor.schedule(()->{
//				randomDireSwitch(bullet,bulletspeed);
//				executor.schedule(()->{
//					randomDireSwitch(bullet,bulletspeed);
//					executor.schedule(()->{
//						randomDireSwitch(bullet,bulletspeed);
//						executor.schedule(()->{
//							randomDireSwitch(bullet,bulletspeed);
//							quick.stopBullet(bullet);
//						}, delay, TimeUnit.MILLISECONDS);
//					}, delay, TimeUnit.MILLISECONDS);
//				}, delay, TimeUnit.MILLISECONDS);
//			}, delay, TimeUnit.MILLISECONDS);
//			
//			
//		});
//		mEU.add("testLauncher", launcher);
//		
//		
//		
//	
	}
//	public void starRun(BaseElement enemy,double starlength,double x,double y){
//		QuickDanmuku quick = QuickDanmuku.getQuickDanmuku();
//		quick.moveTo(enemy, 1500, -starlength*triFunc(36,2)+x,-starlength*triFunc(36,2)*triFunc(18,3)+starlength*triFunc(18,2)+y );
//		sES.schedule(() -> {
//			quick.moveTo(enemy, 1500, starlength+starlength*triFunc(18,1)+x, 0+y);
//		}, 1700, TimeUnit.MILLISECONDS);
//		sES.schedule(() -> {
//			quick.moveTo(enemy, 1500, -starlength-starlength*triFunc(18,1)+x, 0+y);
//		}, 3500, TimeUnit.MILLISECONDS);
//		sES.schedule(() -> {
//			quick.moveTo(enemy, 1500, starlength*triFunc(36,2)+x, -starlength*triFunc(36,2)*triFunc(18,3)+starlength*triFunc(18,2)+y);
//		}, 5200, TimeUnit.MILLISECONDS);
//		sES.schedule(() -> {
//			quick.moveTo(enemy, 1500, 0+x, starlength*triFunc(18,2)+y);
//		}, 6900, TimeUnit.MILLISECONDS);
//	}
	public double triFunc(double a,int type){
		if(type==1)
			return Math.sin(a*Math.PI/180);
		else if(type==2)
			return Math.cos(a*Math.PI/180);
		else
			return 1/Math.tan(a*Math.PI/180);
	}
//	
//	public void randomDireSwitch(BaseElement bullet,double speed){
//		double flag=Math.random();
//		if(flag>0.666){bullet.setVelocityX(speed);bullet.setVelocityY(0);}
//		else if(flag>0.333){bullet.setVelocityX(0-speed);bullet.setVelocityY(0);}
//		else {bullet.setVelocityX(0);bullet.setVelocityY(speed);}
//	}
}