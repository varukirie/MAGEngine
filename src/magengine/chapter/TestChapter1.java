package magengine.chapter;

import java.util.concurrent.ScheduledExecutorService;

import javafx.application.Platform;
import javafx.scene.effect.Bloom;
import magengine.bullet.ArrowBullet;
import magengine.bullet.Bullet;
import magengine.bullet.DefaultBullet;
import magengine.bullet.PlayerBullet;
import magengine.bullet.StarBullet;
import magengine.chapter.util.AChapter;
import magengine.chapter.util.QuickDanmuku;
import magengine.launcher.Launcher;
import magengine.paint.MyCanvas;
import magengine.util.DI;
import magengine.util.ElementUtils;
import magengine.util.MyCanvasSwitcher;
import java.util.concurrent.*;
public class TestChapter1 extends AChapter {
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
		/*long delay = 900;
		double bulletspeed=0.5;
		QuickDanmuku quick =QuickDanmuku.getQuickDanmuku();
		Launcher launcher = new Launcher(300, 200, Math.PI/2, 10, 5000);
		launcher.setBulletType(StarBullet.class);
		launcher.setBulletEvent((executor, bullet) -> {
			executor.schedule(()->{
				randomDireSwitch(bullet,bulletspeed);
				executor.schedule(()->{
					randomDireSwitch(bullet,bulletspeed);
					executor.schedule(()->{
						randomDireSwitch(bullet,bulletspeed);
						executor.schedule(()->{
							randomDireSwitch(bullet,bulletspeed);
							quick.stopBullet(bullet);
						}, delay, TimeUnit.MILLISECONDS);
					}, delay, TimeUnit.MILLISECONDS);
				}, delay, TimeUnit.MILLISECONDS);
			}, delay, TimeUnit.MILLISECONDS);
			
			
		});
		mEU.add("testLauncher", launcher);
		
		
		
	*/
	}
	/*
	public void randomDireSwitch(Bullet bullet,double speed){
		double flag=Math.random();
		if(flag>0.666){bullet.setVelocityX(speed);bullet.setVelocityY(0);}
		else if(flag>0.333){bullet.setVelocityX(0-speed);bullet.setVelocityY(0);}
		else {bullet.setVelocityX(0);bullet.setVelocityY(0.7);}
	}*/
}
