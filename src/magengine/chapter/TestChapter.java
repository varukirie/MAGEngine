package magengine.chapter;

import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import javafx.application.Platform;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.BoxBlur;
import javafx.scene.paint.Color;
import magengine.bullet.APolygonBullet;
import magengine.bullet.Bullet;
import magengine.bullet.PresetColor;
import magengine.bullet.impl.ArrowBullet;
import magengine.bullet.impl.CircleBullet;
import magengine.bullet.impl.DefaultBullet;
import magengine.bullet.impl.HexagonBullet;
import magengine.bullet.impl.PlayerBullet;
import magengine.bullet.impl.StarBullet;
import magengine.chapter.util.AChapter;
import magengine.chapter.util.QuickDanmuku;
import magengine.danmuku.ReisenNonSpellCardDanmuku;
import magengine.danmuku.RingDanmuku;
import magengine.danmuku.RunAwayNuclearDanmuku;
import magengine.danmuku.StarDanmuku;
import magengine.danmuku.TriArcDanmuku;
import magengine.danmuku.UtsuhoNonSpellCard1;
import magengine.danmuku.gs.Testing;
import magengine.danmuku.yt.DirectPlayerDanmuku;
import magengine.danmuku.yt.MulCircleDanmuku2;
import magengine.danmuku.yt.RotateDanmuku;
import magengine.danmuku.yt.autumnSkyDanmuku;
import magengine.danmuku.yt.demo1;
import magengine.element.BaseElement;
import magengine.element.impl.Area;
import magengine.element.impl.BombCircleArea;
import magengine.element.impl.CircleArea;
import magengine.element.impl.InvertCircleArea;
import magengine.element.impl.Player;
import magengine.enemy.ALoopDanmukuEnemy;
import magengine.enemy.DefaultEnemy;
import magengine.enemy.Enemy1;
import magengine.game.LogicExecutor;
import magengine.helper.Helper;
import magengine.helper.OvalHelper;
import magengine.launcher.ArcLauncherGroup;
import magengine.launcher.BurstLauncher;
import magengine.launcher.Launcher;
import magengine.launcher.OvalLauncherGroup;
import magengine.paint.MyCanvas;
import magengine.paint.MyCanvasSwitcher;
import magengine.ui.SceneManager;
import magengine.util.CollisionTeam;
import magengine.util.DI;
import magengine.util.ElementUtils;

import static java.lang.Math.*;

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
			((MyCanvasSwitcher) DI.di().get("switcher")).configCanvas(HexagonBullet.class, (canvas) -> {
				canvas.setEffect(new Bloom());
			});
			((MyCanvasSwitcher) DI.di().get("switcher")).configCanvas(BombCircleArea.class, (canvas) -> {
				canvas.setEffect(new BoxBlur(2.5, 2.5, 1));
//				canvas.setEffect(new Bloom(1));
			});
			staticCanvas.getGraphicsContext2D().fillRect(0, 0, MyCanvas.CANVAS_WIDTH, MyCanvas.CANVAS_HEIGHT);
		});
		QuickDanmuku quick = QuickDanmuku.getQuickDanmuku();
		Random r = new Random();
		
		LogicExecutor executor = LogicExecutor.getLogicExecutor();
		ALoopDanmukuEnemy boss = new Enemy1(300, 100);
		boss.setHP(500);
		boss.setDanmukuStartDelay(1);
		// boss.addDanmuku(new RotateDanmuku(), DirectPlayerDanmuku.DURATION);
		boss.addDanmuku(new UtsuhoNonSpellCard1(), UtsuhoNonSpellCard1.DURATION);
		boss.addDanmuku(new ReisenNonSpellCardDanmuku(), ReisenNonSpellCardDanmuku.DURATION + 1000);// 1秒间隔
		boss.addDanmuku(new RunAwayNuclearDanmuku(), RunAwayNuclearDanmuku.DURATION + 2000);// 2秒间隔
		// boss.setOnRemoveEvent((bs)->{
		// new OvalLauncherGroup(bs.getX(), bs.getY(), 32).execute();
		// });
		mEU.add("enemy", boss);

		for (int i = 0; i < 100; i++) {
			long sum = 6000 + 1000 + 6000 + 1000;
			executor.schedule(() -> {
				quick.moveTo(boss, 6000, 100, 120);
			}, i * sum);
			executor.schedule(() -> {
				quick.moveTo(boss, 6000, 500, 100);
			}, i * sum + 6000 + 1000);
		}

		
//		 InvertCircleArea ica = new InvertCircleArea(300, 100, 200);
//		 ica.setOnCollisionEvent((m) -> {
//		 if (m.getTeam().equals(CollisionTeam.ENEMY_BULLET)) {
//		 Bullet b = (Bullet) m;
//		 System.out.println("caught");
//		 if(b.state<1){
//		 quick.VToByDirection(b, PI/2*3);
//		 }
//		 }
//		 });
//		 mEU.add("ica", ica);

		// new RotateDanmuku().delayExecute();

		// ArcLauncherGroup alg= new ArcLauncherGroup(300, 200, Math.PI/3,
		// Math.PI/3,128);
		// alg.setLauncherConfig((l)->{
		// l.setBulletSpeed(100);
		// ((BurstLauncher)l).setBurstAmount(5);
		// ((BurstLauncher)l).setBurstRange(500);
		// ((BurstLauncher)l).setBurstDuration(2000);
		// l.setBulletType(CircleBullet.class);
		// l.setBulletConfig(circleBulletConfig);
		// l.setBulletEvent((sesx,b)->{
		// });
		// });
		// alg.setLauncherType(BurstLauncher.class);
		// alg.execute();

		// quick.moveTo(boss, 1500, 100, 100);
		// executor.schedule(() -> {
		// quick.moveTo(boss, 4000, 275, 500);
		// }, 1700);
		// executor.schedule(() -> {
		// quick.moveTo(boss, 4000, 500, 100);
		// }, 6000);
		// executor.schedule(() -> {
		// quick.moveTo(boss, 3000, 275, 195.5);
		// }, 11000);

		// quick.moveTo(enemy, 1500, 100, 100);
		// LogicExecutor.getLogicExecutor().schedule(() -> {
		// quick.moveTo(enemy, 4000, 275, 500);
		// }, 1700, TimeUnit.MILLISECONDS);
		// LogicExecutor.getLogicExecutor().schedule(() -> {
		// quick.moveTo(enemy, 4000, 500, 100);
		// }, 6000, TimeUnit.MILLISECONDS);
		// LogicExecutor.getLogicExecutor().schedule(() -> {
		// quick.moveTo(enemy, 3000, 275, 195.5);
		// }, 11000, TimeUnit.MILLISECONDS);
		// Launcher launcher = new Launcher(1, midY, 0, 400, 4000);
		// launcher.setBulletType(Launcher.class);
		// launcher.setVelocityX(120);
		// launcher.setBulletSpeed(0);
		// launcher.setBulletConfig((launcherN)->{
		// Launcher ln = (Launcher) launcherN;
		// ln.setDuration(quick.getSyncDelayAfterLaunch(launcher, 10000));
		// ln.setDirection(Math.PI/2);
		// ln.setBulletSpeed(100);
		// ln.setInterval(1000000);
		// ln.setVelocityX(10);
		// });
		// launcher.setBulletEvent((sesx,launcherN)->{
		// Launcher ln = (Launcher) launcherN;
		// LogicExecutor.getLogicExecutor().schedule(() -> {
		// ln.setInterval(1000);
		// }, quick.getSyncDelayAfterLaunch(launcher, 5000),
		// TimeUnit.MILLISECONDS);
		// });
		// mEU.add("l", launcher);

		// new RingDanmuku().setDelay(1000).delayExecute();

		// Helper midHelper = new Helper(midX, midY);
		// midHelper.setVelocityY(30);
		// int bulletCount = 32;
		// for (int i = 0; i < bulletCount; i++) {
		// Bullet bullet = new DefaultBullet(midX, midY);
		// OvalHelper helper = new OvalHelper(midX, midY, 100, 30, Math.PI * 2 /
		// bulletCount * i);
		// helper.getOvalMidXProperty().bind(midHelper.getxProperty());
		// helper.getOvalMidYProperty().bind(midHelper.getyProperty());
		// bullet.getxProperty().bind(helper.getxProperty());
		// bullet.getyProperty().bind(helper.getyProperty());
		// bullet.setVelocityX(1);
		// bullet.setLambdaModify((x) -> {
		// quick.VToByDirection(x, helper.getDirection() + Math.PI / 2);
		// });
		// Random r = new Random();
		// mEU.add("bullet" + r.nextInt(), bullet);
		// mEU.add("helper" + r.nextInt(), helper);
		// mEU.add("midHelper", midHelper);
		// }
//		 for(int i=1;i<=1000;i++){
//		 new StarDanmuku().setDelay(700*i).delayExecute();
//		 }

	}
}
