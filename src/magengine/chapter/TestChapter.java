package magengine.chapter;

import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javafx.application.Platform;
import javafx.scene.effect.Bloom;
import magengine.bullet.ArrowBullet;
import magengine.bullet.Bullet;
import magengine.bullet.DefaultBullet;
import magengine.bullet.StarBullet;
import magengine.chapter.util.AChapter;
import magengine.chapter.util.ChapterLoader;
import magengine.chapter.util.QuickDanmuku;
import magengine.danmuku.ADanmuku;
import magengine.danmuku.StarDanmuku;
import magengine.danmuku.TriArcDanmuku;
import magengine.enemy.DefaultEnemy;
import magengine.helper.Helper;
import magengine.helper.MoveToHelper;
import magengine.helper.OvalHelper;
import magengine.helper.PloygonalLineHelper;
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
		QuickDanmuku quick = QuickDanmuku.getQuickDanmuku();
		
		
//		DefaultEnemy enemy = new DefaultEnemy(500, 300);
//		ADanmuku danmuku = new TriArcDanmuku();
//		
//		enemy.addDanmuku(danmuku);
//		
//		mEU.add("enemy", enemy);
//		quick.moveTo(enemy, 1000, 100, 400);
//		sES.schedule(() -> {
//			quick.moveTo(enemy, 1000,50, 50);
//		},2000, TimeUnit.MILLISECONDS);
//		sES.schedule(() -> {
//			ChapterLoader.loadChapter(new TestChapter());
//		},4000, TimeUnit.MILLISECONDS);
		

		for(int i=1;i<=1000;i++){
			new StarDanmuku().setDelay(700*i).delayExecute();
		}
	}
}
