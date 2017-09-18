package indi.megaastronic.chapter;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import indi.megaastronic.chapter.util.AChapter;
import indi.megaastronic.chapter.util.ChapterLoader;
import indi.megaastronic.chapter.util.QuickDanmuku;
import indi.megaastronic.chapter.util.SeqDanmuku;
import indi.megaastronic.helper.OvalHelper;
import indi.megaastronic.helper.PloygonalLineHelper;
import indi.megaastronic.launcher.ArcLauncherGroup;
import indi.megaastronic.launcher.Launcher;
import indi.megaastronic.launcher.OvalLauncherGroup;
import indi.megaastronic.paint.MyCanvas;
import indi.megaastronic.util.DI;
import indi.megaastronic.util.ElementUtils;
import indi.megaastronic.util.MyCanvasSwitcher;
import javafx.application.Platform;
import javafx.scene.effect.Bloom;
import indi.megaastronic.bullet.DefaultBullet;
import indi.megaastronic.bullet.StarBullet;

public class TestChapter extends AChapter {

	private int i;
	int midX = MyCanvas.CANVAS_WIDTH/2;
	int midY = 200;

	@Override
	public void design(ScheduledExecutorService sES, MyCanvas staticCanvas, ElementUtils mEU) {

		Platform.runLater(()->{
			((MyCanvasSwitcher)DI.di().get("switcher")).configCanvas(DefaultBullet.class, (canvas)->{
				canvas.setEffect(new Bloom());
			});
			staticCanvas.getGraphicsContext2D().fillRect(0, 0, MyCanvas.CANVAS_WIDTH, MyCanvas.CANVAS_HEIGHT);
		});
		
		SeqDanmuku seq = new SeqDanmuku(sES, mEU);
		QuickDanmuku quick = new QuickDanmuku(mEU);
		Random r = new Random();
		
		long intervalS=700;
		PloygonalLineHelper helper =  new PloygonalLineHelper(new double[][]{
			{157,275,392,157},{256,74,256,256}
		}, 2.5);
		Launcher lc = new Launcher(69, 236, Math.PI/2, 100, 10000);
		lc.setBulletSpeed(0.001);
		lc.setBulletType(StarBullet.class);
		helper.getxProperty().bindBidirectional(lc.getxProperty());
		helper.getyProperty().bindBidirectional(lc.getyProperty());
		mEU.add(r.nextLong()+"",helper);
		lc.setDuration(helper.getDuration());
		mEU.add(r.nextLong()+"", lc);
		
		PloygonalLineHelper helper2 =  new PloygonalLineHelper(new double[][]{
			{393,275,157,393},{135,318,135,135}
		}, 2.5);
		Launcher lc2 = new Launcher(69, 236, Math.PI/2, 100, 10000);
		lc2.setBulletSpeed(0.001);
		lc2.setBulletType(StarBullet.class);
		helper2.getxProperty().bindBidirectional(lc2.getxProperty());
		helper2.getyProperty().bindBidirectional(lc2.getyProperty());
		mEU.add(r.nextLong()+"",helper2);
		lc2.setDuration(helper2.getDuration());
		mEU.add(r.nextLong()+"", lc2);
		
	}

}
