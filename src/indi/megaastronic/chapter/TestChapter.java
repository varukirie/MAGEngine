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
		quick.slash(midX, midY, 0.5);
		Random r = new Random();
		Launcher lc = new Launcher(69, 236, Math.PI/2, 200, 10000);
		lc.setVelocityX(0.7);
		lc.setVelocityY(0.7);
		quick.VTo(lc, 98, 149);
		mEU.add(r.nextLong()+"", lc);
	}

}
