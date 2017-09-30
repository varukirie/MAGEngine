package magengine.danmuku;

import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import magengine.bullet.DefaultBullet;
import magengine.bullet.StarBullet;
import magengine.chapter.util.QuickDanmuku;
import magengine.chapter.util.SeqDanmuku;
import magengine.helper.PloygonalLineHelper;
import magengine.launcher.BulletEvent;
import magengine.launcher.Launcher;
import magengine.launcher.OvalLauncherGroup;
import magengine.util.ElementUtils;

public class StarDanmuku extends ADanmuku {

	@Override
	public void executeDanmuku() {
		ElementUtils mEU=getmEU();
		ScheduledExecutorService sES=getsES();
		SeqDanmuku seq = new SeqDanmuku(sES, mEU);
		QuickDanmuku quick = new QuickDanmuku(mEU);
		Random r = new Random();

		long targetTime = 6000;
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
		
		lc.setBulletEvent((sesx,b)->{
			sesx.schedule(() -> {
				quick.runBullet(b);
//				quick.VToByDirection(b, Math.PI/2);
				quick.VToByDirection(b, r.nextDouble()*Math.PI*2);
			}, 5000+lc.getStartTime()-System.currentTimeMillis(), TimeUnit.MILLISECONDS);
		});
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
	}

}
