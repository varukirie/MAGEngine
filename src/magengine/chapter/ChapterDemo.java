package magengine.chapter;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import javafx.application.Platform;
import javafx.scene.effect.Bloom;
import magengine.bullet.impl.ArrowBullet;
import magengine.bullet.impl.DefaultBullet;
import magengine.bullet.impl.StarBullet;
import magengine.chapter.util.AChapter;
import magengine.chapter.util.ChapterLoader;
import magengine.chapter.util.QuickDanmuku;
import magengine.chapter.util.SeqDanmuku;
import magengine.game.LogicExecutor;
import magengine.launcher.ArcLauncherGroup;
import magengine.launcher.OvalLauncherGroup;
import magengine.paint.MyCanvas;
import magengine.paint.MyCanvasSwitcher;
import magengine.util.C;
import magengine.util.DI;
import magengine.util.ElementUtils;

public class ChapterDemo extends AChapter {

	private int i;
	int midX = MyCanvas.CANVAS_WIDTH/2;
	int midY = 200;

	@Override
	public void design(LogicExecutor sES, MyCanvas staticCanvas, ElementUtils mEU) {
		Platform.runLater(()->{
			((MyCanvasSwitcher)DI.di().get("switcher")).setEffectByClass(DefaultBullet.class, new Bloom());
			((MyCanvasSwitcher)DI.di().get("switcher")).setEffectByClass(ArrowBullet.class, new Bloom());
			((MyCanvasSwitcher)DI.di().get("switcher")).setEffectByClass(StarBullet.class, new Bloom());
			
			staticCanvas.getGraphicsContext2D().fillRect(0, 0, MyCanvas.CANVAS_WIDTH, MyCanvas.CANVAS_HEIGHT);
		});
		SeqDanmuku seq = new SeqDanmuku(mEU);
		QuickDanmuku quick = new QuickDanmuku(mEU);
		Random r = new Random(C.SEED);

//		Launcher launcherx = new Launcher(midX-100, midY-100, Math.PI/2,100, 1000);
//		launcherx.setBulletSpeed(0.001);
//		launcherx.setBulletEvent((sesx,bullet)->{
//			LogicExecutor.getLogicExecutor().schedule(()->{
//				bullet.setAccY(0.2);
//			}, 2000, TimeUnit.MILLISECONDS);
//		});
//		launcherx.setVelocityY(0.5);
//		mEU.add(UUID.randomUUID().toString(), launcherx);
//		
//		launcherx = new Launcher(midX+100, midY-100, Math.PI/2,100, 1000);
//		launcherx.setBulletSpeed(0.001);
//		launcherx.setBulletEvent((sesx,bullet)->{
//			LogicExecutor.getLogicExecutor().schedule(()->{
//				bullet.setAccY(0.2);
//			}, 2000, TimeUnit.MILLISECONDS);
//		});
//		launcherx.setVelocityY(0.5);
//		mEU.add(UUID.randomUUID().toString(), launcherx);
		

		
		
		
		new ArcLauncherGroup(midX, midY, Math.PI/2, Math.PI/3, 6).delayExecute(700);
		ArcLauncherGroup alg = new ArcLauncherGroup(midX, midY, Math.PI/2, Math.PI/3, 6);
		alg.setLauncherConfig((launcher)->{
			launcher.setBulletSpeed(1.5);
		});
		alg.delayExecute(850);

		new ArcLauncherGroup(midX, midY, Math.PI/2+Math.PI/7, Math.PI/3, 6).delayExecute(1300);
		new ArcLauncherGroup(midX, midY, Math.PI/2-Math.PI/7, Math.PI/3, 6).delayExecute(1900);

		OvalLauncherGroup oG = new OvalLauncherGroup(midX, midY);
		oG.setLauncherConfig((launcher) -> {
			launcher.setBulletType(ArrowBullet.class);
			launcher.setBulletEvent((sESx, bullet) -> {
				LogicExecutor.getLogicExecutor().schedule(() -> {
					quick.stopBullet(bullet);
				}, 1000, TimeUnit.MILLISECONDS);
				LogicExecutor.getLogicExecutor().schedule(() -> {
					quick.runBullet(bullet);
					bullet.setVelocityY(1.0);
					bullet.setVelocityX(0);
				}, 1500, TimeUnit.MILLISECONDS);
			});
		});
		oG.delayExecute(4000);

		
		OvalLauncherGroup oG2 = new OvalLauncherGroup(midX, midY);
		oG2.setLauncherConfig((launcher) -> {
			launcher.setBulletEvent((sESx, bullet) -> {
				LogicExecutor.getLogicExecutor().schedule(() -> {
					quick.stopBullet(bullet);
				}, 1700, TimeUnit.MILLISECONDS);
				LogicExecutor.getLogicExecutor().schedule(() -> {
					quick.runBullet(bullet);
					quick.VTransform(bullet,new double[][]{
						{0,-1},{1,0}
					});
				}, 2000, TimeUnit.MILLISECONDS);
			});
		});
		
		oG2.delayExecute(100);
		OvalLauncherGroup oG3 = new OvalLauncherGroup(midX, midY);
		oG3.setLauncherConfig((launcher) -> {
			
			launcher.setBulletEvent((sESx, bullet) -> {
				LogicExecutor.getLogicExecutor().schedule(() -> {
					quick.stopBullet(bullet);
				}, 1700, TimeUnit.MILLISECONDS);
				LogicExecutor.getLogicExecutor().schedule(() -> {
					quick.runBullet(bullet);
					quick.VTransform(bullet,new double[][]{
						{0,1},{-1,0}
					});
				}, 2000, TimeUnit.MILLISECONDS);
			});
		});
		oG3.delayExecute(1000);
		
		OvalLauncherGroup oG4 = new OvalLauncherGroup(midX, midY);
		oG4.setLauncherConfig((launcher) -> {
			launcher.setBulletType(magengine.bullet.impl.StarBullet.class);
			launcher.setBulletEvent((sESx, bullet) -> {
				LogicExecutor.getLogicExecutor().schedule(() -> {
					quick.stopBullet(bullet);
				}, 1700, TimeUnit.MILLISECONDS);
				LogicExecutor.getLogicExecutor().schedule(() -> {
					quick.runBullet(bullet);
					quick.VTransform(bullet,new double[][]{
						{0,1},{1,0}
					});
				}, 2000, TimeUnit.MILLISECONDS);
			});
		});
		oG4.delayExecute(1500);
		
		OvalLauncherGroup oG5 = new OvalLauncherGroup(midX, midY);
		oG5.setLauncherConfig((launcher) -> {
			launcher.setBulletEvent((sESx, bullet) -> {
				LogicExecutor.getLogicExecutor().schedule(() -> {
					quick.stopBullet(bullet);
				}, 1000, TimeUnit.MILLISECONDS);
				LogicExecutor.getLogicExecutor().schedule(() -> {
					quick.runBullet(bullet);
					bullet.setVelocityX(-bullet.getVelocityX());
					bullet.setVelocityY(-bullet.getVelocityY());
				}, 1500, TimeUnit.MILLISECONDS);
			});
		});
		oG5.delayExecute(1500);
		
		int lcount = 12;
		for (int i = 0; i < lcount; i++) {
			seq.rotateDSnipe(midX, midY, 7000, 2 * Math.PI / lcount * i);
		}
		long interval = 100;
		long startTime2 = 17000;
		for (int i = 1; i <= 1; i++) {
			int x = midX;
			int y = midY;
			seq.rotate(x, y, interval * i + startTime2, Math.PI * 2 / 3 + i);
			seq.rotate(x, y, interval * i + startTime2, Math.PI * 4 / 3 + i);
			seq.rotate(x, y, interval * i + startTime2, Math.PI * 6 / 3 + i);
		}

		for (int i = 1; i <= 2; i++) {
			int x = midX;
			int y = midY;
			seq.rotate(x, y, interval * i + startTime2 + 7000, Math.PI * 2 / 3 + i, StarBullet.class, false);
			seq.rotate(x, y, interval * i + startTime2 + 7000, Math.PI * 4 / 3 + i, StarBullet.class, false);
			seq.rotate(x, y, interval * i + startTime2 + 7000, Math.PI * 6 / 3 + i, StarBullet.class, false);
		}
		
		LogicExecutor.getLogicExecutor().schedule(() -> {
			ChapterLoader.loadChapter(new ChapterDemo());
		}, 35000, TimeUnit.MILLISECONDS);
		
	}

}
