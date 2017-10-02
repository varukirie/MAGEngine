package magengine.chapter;

import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javafx.application.Platform;
import javafx.scene.effect.Bloom;
import magengine.bullet.ArrowBullet;
import magengine.bullet.DefaultBullet;
import magengine.bullet.StarBullet;
import magengine.chapter.util.AChapter;
import magengine.chapter.util.QuickDanmuku;
import magengine.helper.Helper;
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
		
		
		double helperSpeed = 1;
		long interval = 50;
		long startMill=1000;
		Random r= new Random();
		QuickDanmuku quick = QuickDanmuku.getQuickDanmuku();
		double[][] ploygon1 = new double[][] { { 157, 275, 392, 157 }, { 256, 74, 256, 256 } };
		PloygonalLineHelper helper = new PloygonalLineHelper(ploygon1, helperSpeed);
		Launcher launcher = new Launcher(69, 236, Math.PI / 2, interval, 10000);
		launcher.setBulletSpeed(0.0006);
		launcher.setBulletType(StarBullet.class);
		launcher.setBulletEvent((sesx,b)->{
			sesx.schedule(() -> {
				quick.runBullet(b);
				quick.VToByDirection(b, r.nextDouble()*Math.PI*2);
			}, 5000+launcher.getStartTime()-System.currentTimeMillis(), TimeUnit.MILLISECONDS);
		});
		helper.getxProperty().bindBidirectional(launcher.getxProperty());
		helper.getyProperty().bindBidirectional(launcher.getyProperty());
		sES.schedule(() -> {
			mEU.add(r.nextLong() + "", helper);
			launcher.setDuration(helper.getDuration());
			mEU.add(r.nextLong() + "", launcher);
		}, startMill, TimeUnit.MILLISECONDS);
	}
//	executor.schedule(()->{
//	quick.stopBullet(bullet);
//}, 500, TimeUnit.MILLISECONDS);
}
