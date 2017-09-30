package magengine.chapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javafx.application.Platform;
import javafx.scene.effect.Bloom;
import magengine.bullet.ArrowBullet;
import magengine.bullet.DefaultBullet;
import magengine.bullet.StarBullet;
import magengine.chapter.util.AChapter;
import magengine.chapter.util.ChapterLoader;
import magengine.chapter.util.QuickDanmuku;
import magengine.chapter.util.SeqDanmuku;
import magengine.danmuku.ADanmuku;
import magengine.danmuku.StarDanmuku;
import magengine.danmuku.TriArcDanmuku;
import magengine.element.impl.Player;
import magengine.enemy.AEnemy;
import magengine.enemy.DefaultEnemy;
import magengine.helper.Helper;
import magengine.helper.PloygonalLineHelper;
import magengine.helper.RelativeAnchorHelper;
import magengine.launcher.BulletEvent;
import magengine.launcher.Launcher;
import magengine.launcher.OvalLauncherGroup;
import magengine.paint.MyCanvas;
import magengine.util.DI;
import magengine.util.ElementUtils;
import magengine.util.MyCanvasSwitcher;

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
		
		AEnemy em = new DefaultEnemy(midX+100, midY+100,-0.2,-0.3);
		mEU.add("dme", em);
		Helper helper1 = new RelativeAnchorHelper(Player.getPlayer(), -50, 0);
		mEU.add("testHelper1", helper1);
		Helper helper2 = new RelativeAnchorHelper(Player.getPlayer(), 50, 0);
		mEU.add("testHelper2", helper2);
//		ADanmuku danmuku = new StarDanmuku();
		ADanmuku danmuku = new TriArcDanmuku(em);
		danmuku.executeDanmuku();
		
		sES.schedule(() -> {
			ChapterLoader.loadChapter(new ChapterDemo());
		}, 14000, TimeUnit.MILLISECONDS);
		
		
	


	}

}
