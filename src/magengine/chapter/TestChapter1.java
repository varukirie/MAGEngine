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
import magengine.element.impl.Player;
import magengine.enemy.DefaultEnemy;
import magengine.enemy.Enemy1;
import magengine.game.LogicExecutor;
import magengine.launcher.Launcher;
import magengine.paint.MyCanvas;
import magengine.paint.MyCanvasSwitcher;
import magengine.util.DI;
import magengine.util.ElementUtils;

import java.util.concurrent.*;
public class TestChapter1 extends AChapter {
	int midX = MyCanvas.CANVAS_WIDTH / 2;
	int midY = 200;

	@Override
	public void design(LogicExecutor sES, MyCanvas staticCanvas, ElementUtils mEU) {

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
		Player p = Player.getPlayer();
		QuickDanmuku quick = QuickDanmuku.getQuickDanmuku();
		DefaultEnemy enemy = new DefaultEnemy(0+x,starlength*triFunc(18,2)+y);
		Launcher launcher = new Launcher(0+x, starlength*triFunc(18,2)+y, Math.PI / 2, 100, delay+5*(runningtime+interval));
		launcher.setBulletSpeed(1);
		launcher.setInterval(10);
		launcher.setBulletType(StarBullet.class);
		LogicExecutor.getLogicExecutor().schedule(()->{
			mEU.add("enemy", enemy);
			mEU.add("testlauncher", launcher);
		}, 1000, TimeUnit.MILLISECONDS);
		launcher.getxProperty().bind(enemy.getxProperty());
		launcher.getyProperty().bind(enemy.getyProperty());
		launcher.setBulletEvent((executor,bullet)->{
			executor.schedule(()->{
				quick.runBullet(bullet);
				quick.VTo(bullet, p.getX() , p.getY());
			}, delay+5*(runningtime+interval), TimeUnit.MILLISECONDS);
		});
		LogicExecutor.getLogicExecutor().schedule(()->{
			quick.moveTo(enemy, runningtime, -starlength*triFunc(36,2)+x,-starlength*triFunc(36,2)*triFunc(18,3)+starlength*triFunc(18,2)+y );
		},delay,TimeUnit.MILLISECONDS);
		LogicExecutor.getLogicExecutor().schedule(() -> {
			quick.moveTo(enemy, runningtime, starlength+starlength*triFunc(18,1)+x, 0+y);
		}, delay+(runningtime+interval), TimeUnit.MILLISECONDS);
		LogicExecutor.getLogicExecutor().schedule(() -> {
			quick.moveTo(enemy, runningtime, -starlength-starlength*triFunc(18,1)+x, 0+y);
		}, delay+2*(runningtime+interval), TimeUnit.MILLISECONDS);
		LogicExecutor.getLogicExecutor().schedule(() -> {
			quick.moveTo(enemy, runningtime, starlength*triFunc(36,2)+x, -starlength*triFunc(36,2)*triFunc(18,3)+starlength*triFunc(18,2)+y);
		}, delay+3*(runningtime+interval), TimeUnit.MILLISECONDS);
		LogicExecutor.getLogicExecutor().schedule(() -> {
			quick.moveTo(enemy, runningtime, 0+x, starlength*triFunc(18,2)+y);
		}, delay+4*(runningtime+interval), TimeUnit.MILLISECONDS);
	
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
//		LogicExecutor.getLogicExecutor().schedule(() -> {
//			quick.moveTo(enemy, 1500, starlength+starlength*triFunc(18,1)+x, 0+y);
//		}, 1700, TimeUnit.MILLISECONDS);
//		LogicExecutor.getLogicExecutor().schedule(() -> {
//			quick.moveTo(enemy, 1500, -starlength-starlength*triFunc(18,1)+x, 0+y);
//		}, 3500, TimeUnit.MILLISECONDS);
//		LogicExecutor.getLogicExecutor().schedule(() -> {
//			quick.moveTo(enemy, 1500, starlength*triFunc(36,2)+x, -starlength*triFunc(36,2)*triFunc(18,3)+starlength*triFunc(18,2)+y);
//		}, 5200, TimeUnit.MILLISECONDS);
//		LogicExecutor.getLogicExecutor().schedule(() -> {
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
