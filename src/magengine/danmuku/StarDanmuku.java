package magengine.danmuku;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import magengine.bullet.impl.DefaultBullet;
import magengine.bullet.impl.StarBullet;
import magengine.chapter.util.QuickDanmuku;
import magengine.chapter.util.SeqDanmuku;
import magengine.game.GameSession;
import magengine.game.LogicExecutor;
import magengine.helper.PolygonalLineHelper;
import magengine.launcher.BulletEvent;
import magengine.launcher.Launcher;
import magengine.launcher.OvalLauncherGroup;
import magengine.util.ElementUtils;

public class StarDanmuku extends ADanmuku {

public static final long DURATION = 8000;
	@Override
	public void executeDanmuku() {
		ElementUtils mEU=getmEU();
		SeqDanmuku seq = new SeqDanmuku(mEU);
		QuickDanmuku quick = new QuickDanmuku(mEU);
		Random r = GameSession.rand();

		long targetTime = 6000;
		BulletEvent be = (sesx,b)->{
			LogicExecutor.getLogicExecutor().schedule(() -> {
				quick.runBullet(b);
				quick.VToByDirection(b, r.nextDouble()*Math.PI*2);
			}, 6000, TimeUnit.MILLISECONDS);
		};
		
		double helperSpeed = 200;
		long interval = 50;
		long startMill=1000;
		double[][] ploygon1 = new double[][] { { 157, 275, 392, 157 }, { 256, 74, 256, 256 } };
		double[][] ploygon2 = new double[][] { { 393, 275, 157, 393 }, { 135, 318, 135, 135 } };
		PolygonalLineHelper helper = new PolygonalLineHelper(ploygon1, helperSpeed);
		Launcher launcher = new Launcher(69, 236, Math.PI / 2, interval, 10000);
		launcher.setBulletSpeed(0.12);
		launcher.setBulletType(StarBullet.class);
		
		launcher.setBulletEvent((sesx,b)->{
			LogicExecutor.getLogicExecutor().schedule(() -> {
				quick.runBullet(b);
//				quick.VToByDirection(b, Math.PI/2);
				quick.VToByDirection(b, r.nextDouble()*Math.PI*2);
			}, 5000+launcher.getStartTime()-LogicExecutor.gameTime(), TimeUnit.MILLISECONDS);
		});
		launcher.getxProperty().bind(helper.getxProperty());
		launcher.getyProperty().bind(helper.getyProperty());

		LogicExecutor.getLogicExecutor().schedule(() -> {
			mEU.add(r.nextLong() + "", helper);
			launcher.setDuration(helper.getDuration());
			mEU.add(r.nextLong() + "", launcher);
		}, startMill, TimeUnit.MILLISECONDS);

		PolygonalLineHelper helper2 = new PolygonalLineHelper(ploygon2, helperSpeed);
		Launcher lc2 = new Launcher(69, 236, Math.PI / 2, interval, 10000);
		lc2.setBulletSpeed(0.12);
		lc2.setBulletType(StarBullet.class);
		lc2.setBulletEvent(be);
		lc2.getxProperty().bind(helper2.getxProperty());
		lc2.getyProperty().bind(helper2.getyProperty());
		LogicExecutor.getLogicExecutor().schedule(() -> {
			mEU.add(r.nextLong() + "", helper2);
			lc2.setDuration(helper2.getDuration());
			mEU.add(r.nextLong() + "", lc2);
		}, startMill, TimeUnit.MILLISECONDS);

		
		OvalLauncherGroup oG = new OvalLauncherGroup(275, 195.5);
		oG.setLauncherConfig((launcherx) -> {
			launcherx.setBulletType(DefaultBullet.class);
			launcherx.setBulletEvent((sESx, bullet) -> {
				LogicExecutor.getLogicExecutor().schedule(() -> {
					quick.stopBullet(bullet);
				}, 1800, TimeUnit.MILLISECONDS);
				LogicExecutor.getLogicExecutor().schedule(() -> {
					quick.runBullet(bullet);
					bullet.setVelocityX(-bullet.getVelocityX());
					bullet.setVelocityY(-bullet.getVelocityY());
				}, 5000, TimeUnit.MILLISECONDS);
			});
		});
		oG.delayExecute(1500);
	}

}
