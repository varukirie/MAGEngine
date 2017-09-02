package indi.megaastronic.chapter;

import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import indi.megaastronic.chapter.util.AChapter;
import indi.megaastronic.chapter.util.ChapterLoader;
import indi.megaastronic.chapter.util.QuickDanmuku;
import indi.megaastronic.chapter.util.SeqDanmuku;
import indi.megaastronic.danmuku.ArcGroup;
import indi.megaastronic.danmuku.OvalGroup;
import indi.megaastronic.paint.MyCanvas;
import indi.megaastronic.util.DI;
import indi.megaastronic.util.ElementUtils;
import indi.megaastronic.util.MyCanvasSwitcher;
import javafx.application.Platform;
import javafx.scene.effect.Bloom;
import indi.megaastronic.bullet.DefaultBullet;

public class TestChapter extends AChapter {

	private int i;
	int midX = MyCanvas.CANVAS_WIDTH/2;
	int midY = 200;

	@Override
	public void design(ScheduledExecutorService sES, MyCanvas staticCanvas, ElementUtils mEU) {
		Platform.runLater(()->{
			((MyCanvasSwitcher)DI.di().get("switcher")).setEffect(DefaultBullet.class, new Bloom());
			staticCanvas.getGraphicsContext2D().fillRect(0, 0, MyCanvas.CANVAS_WIDTH, MyCanvas.CANVAS_HEIGHT);
		});
		SeqDanmuku seq = new SeqDanmuku(sES, mEU);
		QuickDanmuku quick = new QuickDanmuku(mEU);
		Random r = new Random();

		// ArcGroup arcGroup=new ArcGroup(midX,midY);
		// arcGroup.delayExecute(500);
		
		
		new ArcGroup(midX, midY, Math.PI/2, Math.PI/3, 6).delayExecute(700);
		new ArcGroup(midX, midY, Math.PI/2, Math.PI/3, 6).delayExecute(850);
		new ArcGroup(midX, midY, Math.PI/2+Math.PI/7, Math.PI/3, 6).delayExecute(1300);
		new ArcGroup(midX, midY, Math.PI/2-Math.PI/7, Math.PI/3, 6).delayExecute(1900);

		OvalGroup oG = new OvalGroup(midX, midY);
		oG.setLauncherConfig((launcher) -> {
			launcher.setBulletEvent((sESx, bullet) -> {
				sESx.schedule(() -> {
					quick.stopBullet(bullet);
				}, 1000, TimeUnit.MILLISECONDS);
				sESx.schedule(() -> {
					quick.runBullet(bullet);
					bullet.setVelocityY(1.0);
					bullet.setVelocityX(0);
				}, 1500, TimeUnit.MILLISECONDS);
			});
		});
		oG.delayExecute(3000);
		oG.delayExecute(4000);
		oG.delayExecute(5000);
		
		OvalGroup oG2 = new OvalGroup(midX, midY);
		oG2.setLauncherConfig((launcher) -> {
			launcher.setBulletEvent((sESx, bullet) -> {
				sESx.schedule(() -> {
					quick.stopBullet(bullet);
				}, 1700, TimeUnit.MILLISECONDS);
				sESx.schedule(() -> {
					quick.runBullet(bullet);
					quick.bulletVTransform(bullet,new double[][]{
						{0,-1},{1,0}
					});
				}, 2000, TimeUnit.MILLISECONDS);
			});
		});
		
		oG2.delayExecute(100);
		OvalGroup oG3 = new OvalGroup(midX, midY);
		oG3.setLauncherConfig((launcher) -> {
			launcher.setBulletEvent((sESx, bullet) -> {
				sESx.schedule(() -> {
					quick.stopBullet(bullet);
				}, 1700, TimeUnit.MILLISECONDS);
				sESx.schedule(() -> {
					quick.runBullet(bullet);
					quick.bulletVTransform(bullet,new double[][]{
						{0,1},{-1,0}
					});
				}, 2000, TimeUnit.MILLISECONDS);
			});
		});
		oG3.delayExecute(1000);
		
		OvalGroup oG4 = new OvalGroup(midX, midY);
		oG4.setLauncherConfig((launcher) -> {
			launcher.setBulletEvent((sESx, bullet) -> {
				sESx.schedule(() -> {
					quick.stopBullet(bullet);
				}, 1700, TimeUnit.MILLISECONDS);
				sESx.schedule(() -> {
					quick.runBullet(bullet);
					quick.bulletVTransform(bullet,new double[][]{
						{0,1},{1,0}
					});
				}, 2000, TimeUnit.MILLISECONDS);
			});
		});
		oG4.delayExecute(1500);
		
		int lcount = 32;
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
			seq.rotate(x, y, interval * i + startTime2 + 7000, Math.PI * 2 / 3 + i, DefaultBullet.class, false);
			seq.rotate(x, y, interval * i + startTime2 + 7000, Math.PI * 4 / 3 + i, DefaultBullet.class, false);
			seq.rotate(x, y, interval * i + startTime2 + 7000, Math.PI * 6 / 3 + i, DefaultBullet.class, false);
		}
		
		sES.schedule(() -> {
			ChapterLoader.loadChapter(new TestChapter());
		}, 35000, TimeUnit.MILLISECONDS);
		
		// seq.rotate(midX-100, midY,2000,0);
		// seq.rotate(midX-100, midY,2000,Math.PI);

		// seq.rotate(midX-100, midY,2000,Math.PI);
		// seq.rotate(midX+100, midY,2000,0);

		// seq.rotateSlash(midX, midY, 5000);
		// seq.rotateSlash(midX-100, midY+100, 15000);
		//
		// seq.lineSnipe(midX, midY-100,0.5, 0, 1000);
		// seq.lineSnipe(midX, midY-100,-0.5, 0, 1000);
	}

}
