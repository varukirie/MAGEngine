package magengine.danmuku;

import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import magengine.bullet.Bullet;
import magengine.bullet.DefaultBullet;
import magengine.chapter.util.QuickDanmuku;
import magengine.helper.Helper;
import magengine.helper.OvalHelper;
import magengine.launcher.ArcLauncherGroup;
import magengine.launcher.Launcher;
import magengine.launcher.OvalLauncherGroup;
import magengine.util.ElementUtils;

import static java.lang.Math.*;

public class RingDanmuku extends ADanmuku {

	@Override
	public void executeDanmuku() {
		ElementUtils mEU = getmEU();
		ScheduledExecutorService ses = getsES();
		QuickDanmuku quick = QuickDanmuku.getQuickDanmuku();
		
		
		//Ring
		double[][] midArray = new double[][] { { 300, 150, 470, 125, 300 }, { 150, 150, 150, 150, 150 } };
		double[] rArray = new double[] { 100, 70, 50, 100, 70 };
		double[] rotateSpeedArray = new double[] { 30, 10, 25, 50, 20 };
		double[] downSpeedArray = new double[] { 50, 60, 20, 60, 50 };
		int bulletCount = 8;
		for (int j = 0; j < midArray[0].length; j++) {
			int i = j;
			ses.schedule(() -> {
				OvalLauncherGroup olg = new OvalLauncherGroup(midArray[0][i], midArray[1][i]);
				olg.setAmount(bulletCount);
				olg.setLauncherConfig((launcher) -> {
					launcher.setBulletSpeed(rArray[i]);
					launcher.setBulletEvent((sesx, b) -> {
						sesx.schedule(() -> {
							b.wantBeRemoved = true;
						}, 999, TimeUnit.MILLISECONDS);
					});
				});
				olg.execute();
				ses.schedule(() -> {
					Helper midHelper = new Helper(midArray[0][i], midArray[1][i]);
					midHelper.setVelocityY(downSpeedArray[i]);
					for (int k = 0; k < bulletCount; k++) {
						Bullet bullet = new DefaultBullet(midArray[0][i], midArray[1][i]);
						OvalHelper helper = new OvalHelper(midArray[0][i], midArray[1][i], rArray[i],
								rotateSpeedArray[i], Math.PI * 2 / bulletCount * k);
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
						mEU.add("midHelper" + i * i * 10007, midHelper);
					}
				}, 1000, TimeUnit.MILLISECONDS);
			}, j * 2000, TimeUnit.MILLISECONDS);
		}
		
		//Snipe
		Consumer<Launcher> config = (Launcher launcher)->{
			launcher.setBulletSpeed(200);
			launcher.setBulletEvent((sesx,bullet)->{
				sesx.schedule(() -> {
					bullet.setVelocityX(70);
					bullet.setVelocityY(0);
					quick.snipePlayer(bullet);
				}, 1000, TimeUnit.MILLISECONDS);
			});
		};
		ArcLauncherGroup alg = new ArcLauncherGroup(getSourceElement().getX(), getSourceElement().getY(), Math.PI/2*3, PI/2, 5);
		alg.setLauncherConfig(config);
		long startTime = 4000;
		ses.schedule(() -> {
			alg.setMidX(getSourceElement().getX());
			alg.setMidY(getSourceElement().getY());
			alg.execute();
		}, startTime , TimeUnit.MILLISECONDS);
		ses.schedule(() -> {
			alg.setMidX(getSourceElement().getX());
			alg.setMidY(getSourceElement().getY());
			alg.execute();
		}, startTime+400 , TimeUnit.MILLISECONDS);
		ses.schedule(() -> {
			alg.setMidX(getSourceElement().getX());
			alg.setMidY(getSourceElement().getY());
			alg.execute();
		}, startTime+800 , TimeUnit.MILLISECONDS);
		
		
	}

}
