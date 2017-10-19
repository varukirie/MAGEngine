package magengine.chapter;

import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


import javafx.application.Platform;
import javafx.scene.effect.Bloom;
import magengine.bullet.ArrowBullet;
import magengine.bullet.Bullet;
import magengine.bullet.CircleBullet;
import magengine.bullet.DefaultBullet;
import magengine.bullet.StarBullet;
import magengine.chapter.util.AChapter;
import magengine.chapter.util.QuickDanmuku;
import magengine.danmuku.ReisenNonSpellCardDanmuku;
import magengine.danmuku.RingDanmuku;
import magengine.danmuku.RunAwayNuclearDanmuku;
import magengine.danmuku.StarDanmuku;
import magengine.danmuku.TriArcDanmuku;
import magengine.danmuku.gs.Testing;
import magengine.danmuku.yt.MulCircleDanmuku1;
import magengine.danmuku.yt.MulCircleDanmuku2;
import magengine.danmuku.yt.demo1;
import magengine.enemy.DefaultEnemy;
import magengine.enemy.Enemy1;
import magengine.game.LogicExecutor;
import magengine.helper.Helper;
import magengine.helper.OvalHelper;
import magengine.launcher.Launcher;
import magengine.launcher.OvalLauncherGroup;
import magengine.paint.MyCanvas;
import magengine.paint.MyCanvasSwitcher;
import magengine.ui.SceneManager;
import magengine.util.DI;
import magengine.util.ElementUtils;
import magengine.bullet.PlayerBullet;
public class TestChapter extends AChapter {
	int midX = MyCanvas.CANVAS_WIDTH / 2;
	int midY = 200;

	@Override
	public void design(LogicExecutor exec, MyCanvas staticCanvas, ElementUtils mEU) {

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
		QuickDanmuku quick = QuickDanmuku.getQuickDanmuku();
		Random r=new Random();
		
		Enemy1 enemy = new Enemy1(300, 100);
		enemy.setHP(30);
		enemy.addDanmuku(new Testing().setDelay(1000));
//		enemy.addDanmuku(new RunAwayNuclearDanmuku().setDelay(1000));
//		enemy.addDanmuku(new demo1().setDelay(1000));
//		enemy.setVelocityX(-50);
//		enemy.addDanmuku(new ReisenNonSpellCardDanmuku().setDelay(1000));
//		enemy.addDanmuku(new TriArcDanmuku().setDelay(3000));
//		enemy.addDanmuku(new TriArcDanmuku().setDelay(6500));
		mEU.add("enemy", enemy);
		LogicExecutor executor= LogicExecutor.getLogicExecutor();
		
		quick.moveTo(enemy, 1500, 100, 100);
		executor.schedule(() -> {
			quick.moveTo(enemy, 4000, 275, 500);
		}, 1700);
		executor.schedule(() -> {
			quick.moveTo(enemy, 4000, 500, 100);
		}, 6000);
		executor.schedule(() -> {
			quick.moveTo(enemy, 3000, 275, 195.5);
		}, 11000);
		
//		quick.moveTo(enemy, 1500, 100, 100);
//		LogicExecutor.getLogicExecutor().schedule(() -> {
//			quick.moveTo(enemy, 4000, 275, 500);
//		}, 1700, TimeUnit.MILLISECONDS);
//		LogicExecutor.getLogicExecutor().schedule(() -> {
//			quick.moveTo(enemy, 4000, 500, 100);
//		}, 6000, TimeUnit.MILLISECONDS);
//		LogicExecutor.getLogicExecutor().schedule(() -> {
//			quick.moveTo(enemy, 3000, 275, 195.5);
//		}, 11000, TimeUnit.MILLISECONDS);
		//		Launcher launcher  = new Launcher(1, midY, 0, 400, 4000);
//		launcher.setBulletType(Launcher.class);
//		launcher.setVelocityX(120);
//		launcher.setBulletSpeed(0);
//		launcher.setBulletConfig((launcherN)->{
//			Launcher ln = (Launcher) launcherN;
//			ln.setDuration(quick.getSyncDelayAfterLaunch(launcher, 10000));
//			ln.setDirection(Math.PI/2);
//			ln.setBulletSpeed(100);
//			ln.setInterval(1000000);
//			ln.setVelocityX(10);
//		});
//		launcher.setBulletEvent((sesx,launcherN)->{
//			Launcher ln = (Launcher) launcherN;
//			LogicExecutor.getLogicExecutor().schedule(() -> {
//				ln.setInterval(1000);
//			}, quick.getSyncDelayAfterLaunch(launcher, 5000), TimeUnit.MILLISECONDS);
//		});
//		mEU.add("l", launcher);
		
		
		
		
		
//		new RingDanmuku().setDelay(1000).delayExecute();

		
		
//		Helper midHelper  = new Helper(midX, midY);
//		midHelper.setVelocityY(30);
//		int bulletCount = 32;
//		for (int i = 0; i < bulletCount; i++) {
//			Bullet bullet = new DefaultBullet(midX, midY);
//			OvalHelper helper = new OvalHelper(midX, midY, 100, 30, Math.PI * 2 / bulletCount * i);
//			helper.getOvalMidXProperty().bind(midHelper.getxProperty());
//			helper.getOvalMidYProperty().bind(midHelper.getyProperty());
//			bullet.getxProperty().bind(helper.getxProperty());
//			bullet.getyProperty().bind(helper.getyProperty());
//			bullet.setVelocityX(1);
//			bullet.setLambdaModify((x) -> {
//				quick.VToByDirection(x, helper.getDirection() + Math.PI / 2);
//			});
//			Random r = new Random();
//			mEU.add("bullet" + r.nextInt(), bullet);
//			mEU.add("helper" + r.nextInt(), helper);
//			mEU.add("midHelper", midHelper);
//		}
//		for(int i=1;i<=1000;i++){
//			new StarDanmuku().setDelay(10*i).delayExecute();
//		}
		
	}
}
