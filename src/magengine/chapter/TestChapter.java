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
import magengine.chapter.util.QuickDanmuku;
import magengine.danmuku.StarDanmuku;
import magengine.danmuku.TriArcDanmuku;
import magengine.enemy.DefaultEnemy;
import magengine.helper.Helper;
import magengine.helper.OvalHelper;
import magengine.paint.MyCanvas;
import magengine.util.DI;
import magengine.util.ElementUtils;
import magengine.util.MyCanvasSwitcher;
import magengine.bullet.PlayerBullet;
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
			((MyCanvasSwitcher) DI.di().get("switcher")).configCanvas(PlayerBullet.class, (canvas) -> {
				canvas.setEffect(new Bloom());
			});
			staticCanvas.getGraphicsContext2D().fillRect(0, 0, MyCanvas.CANVAS_WIDTH, MyCanvas.CANVAS_HEIGHT);
		});
		QuickDanmuku quick = QuickDanmuku.getQuickDanmuku();
		
		
//		DefaultEnemy enemy = new DefaultEnemy(500, 100);
//		enemy.setHP(30);
//		enemy.setVelocityX(-50);
//		enemy.addDanmuku(new TriArcDanmuku().setDelay(100));
//		enemy.addDanmuku(new TriArcDanmuku().setDelay(3000));
//		enemy.addDanmuku(new TriArcDanmuku().setDelay(6500));
//		mEU.add("enemy", enemy);
//		quick.moveTo(enemy, 1500, 100, 100);
//		sES.schedule(() -> {
//			quick.moveTo(enemy, 4000, 275, 500);
//		}, 1700, TimeUnit.MILLISECONDS);
//		sES.schedule(() -> {
//			quick.moveTo(enemy, 4000, 500, 100);
//		}, 6000, TimeUnit.MILLISECONDS);
//		sES.schedule(() -> {
//			quick.moveTo(enemy, 3000, 275, 195.5);
//		}, 11000, TimeUnit.MILLISECONDS);
		
		Helper midHelper  = new Helper(midX, midY);
		midHelper.setVelocityY(30);
		int bulletCount = 32;
		for (int i = 0; i < bulletCount; i++) {
			Bullet bullet = new DefaultBullet(midX, midY);
			OvalHelper helper = new OvalHelper(midX, midY, 100, 30, Math.PI * 2 / bulletCount * i);
			helper.getOvalMidXProperty().bind(midHelper.getxProperty());
			helper.getOvalMidYProperty().bind(midHelper.getyProperty());
			bullet.getxProperty().bind(helper.getxProperty());
			bullet.getyProperty().bind(helper.getyProperty());
			bullet.setVelocityX(1);
			bullet.setLambdaModify((x) -> {
				quick.VToByDirection(x, helper.getDirection() + Math.PI / 2);
			});
			Random r = new Random();
			mEU.add("bullet" + r.nextInt(), bullet);
			mEU.add("helper" + r.nextInt(), helper);
			mEU.add("midHelper", midHelper);
		}
//		for(int i=1;i<=1000;i++){
//			new StarDanmuku().setDelay(700*i).delayExecute();
//		}
	}
}
