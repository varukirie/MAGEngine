package indi.megaastronic.chapter;

import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import indi.megaastronic.chapter.util.AChapter;
import indi.megaastronic.chapter.util.ChapterLoader;
import indi.megaastronic.chapter.util.QuickDanmuku;
import indi.megaastronic.chapter.util.SeqDanmuku;
import indi.megaastronic.helper.PloygonalLineHelper;
import indi.megaastronic.launcher.BulletEvent;
import indi.megaastronic.launcher.Launcher;
import indi.megaastronic.launcher.OvalLauncherGroup;
import indi.megaastronic.paint.MyCanvas;
import indi.megaastronic.util.DI;
import indi.megaastronic.util.ElementUtils;
import indi.megaastronic.util.MyCanvasSwitcher;
import javafx.application.Platform;
import javafx.scene.effect.Bloom;
import indi.megaastronic.bullet.ArrowBullet;
import indi.megaastronic.bullet.DefaultBullet;
import indi.megaastronic.bullet.StarBullet;

public class TestChapter extends AChapter {

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
			staticCanvas.getGraphicsContext2D().fillRect(0, 0, MyCanvas.CANVAS_WIDTH, MyCanvas.CANVAS_HEIGHT);
		});

		SeqDanmuku seq = new SeqDanmuku(sES, mEU);
		QuickDanmuku quick = new QuickDanmuku(mEU);
		Random r = new Random();

		BulletEvent be = (sesx,b)->{
			sesx.schedule(() -> {
				quick.runBullet(b);
				quick.VToByDirection(b, r.nextDouble()*Math.PI*2);
			}, 6000, TimeUnit.MILLISECONDS);
		};
		
		double helperSpeed = 1;
		long interval = 50;
		long startMill=1000;
		double[][] ploygon1 = new double[][] { { 157, 275, 392, 157 }, { 256, 74, 256, 256 } };
		double[][] ploygon2 = new double[][] { { 393, 275, 157, 393 }, { 135, 318, 135, 135 } };
		PloygonalLineHelper helper = new PloygonalLineHelper(ploygon1, helperSpeed);
		Launcher lc = new Launcher(69, 236, Math.PI / 2, interval, 10000);
		lc.setBulletSpeed(0.0006);
		lc.setBulletType(StarBullet.class);
		lc.setBulletEvent(be);
		helper.getxProperty().bindBidirectional(lc.getxProperty());
		helper.getyProperty().bindBidirectional(lc.getyProperty());

		sES.schedule(() -> {
			mEU.add(r.nextLong() + "", helper);
			lc.setDuration(helper.getDuration());
			mEU.add(r.nextLong() + "", lc);
		}, startMill, TimeUnit.MILLISECONDS);

		PloygonalLineHelper helper2 = new PloygonalLineHelper(ploygon2, helperSpeed);
		Launcher lc2 = new Launcher(69, 236, Math.PI / 2, interval, 10000);
		lc2.setBulletSpeed(0.0006);
		lc2.setBulletType(StarBullet.class);
		lc2.setBulletEvent(be);
		helper2.getxProperty().bindBidirectional(lc2.getxProperty());
		helper2.getyProperty().bindBidirectional(lc2.getyProperty());
		sES.schedule(() -> {
			mEU.add(r.nextLong() + "", helper2);
			lc2.setDuration(helper2.getDuration());
			mEU.add(r.nextLong() + "", lc2);
		}, startMill, TimeUnit.MILLISECONDS);

		
		OvalLauncherGroup oG = new OvalLauncherGroup(275, 195.5);
		oG.setLauncherConfig((launcher) -> {
			launcher.setBulletType(DefaultBullet.class);
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
		oG.delayExecute(1500);
		sES.schedule(() -> {
			ChapterLoader.loadChapter(new ChapterDemo());
		}, 14000, TimeUnit.MILLISECONDS);
		
		
//		for(int i=1;i<=100;i++){
//			oG.delayExecute(1500+i*50);
//		}
//		
		// for(int i=1;i<=10;i++){
		// sES.schedule(() -> {
		// Launcher l2 = new Launcher(midX, midY, Math.PI, 100, 10000);
		// l2.setVelocityX(1);
		// l2.setVelocityY(1);
		// mEU.add(r.nextLong()+"", l2);
		// }, 1000*i, TimeUnit.MILLISECONDS);
		// }

	}

}
