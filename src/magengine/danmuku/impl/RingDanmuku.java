package magengine.danmuku.impl;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import magengine.bullet.Bullet;
import magengine.bullet.impl.DefaultBullet;
import magengine.bullet.impl.DiamondBullet;
import magengine.chapter.util.QuickDanmuku;
import magengine.danmuku.ADanmuku;
import magengine.game.GameSession;
import magengine.game.LogicExecutor;
import magengine.helper.Helper;
import magengine.helper.OvalHelper;
import magengine.launcher.ArcLauncherGroup;
import magengine.launcher.Launcher;
import magengine.launcher.OvalLauncherGroup;
import magengine.util.ElementUtils;

import static java.lang.Math.*;

public class RingDanmuku extends ADanmuku {

	
	public static final long DURATION = 20000;
	@Override
	public void executeDanmuku() {
		ElementUtils mEU = getmEU();
		QuickDanmuku quick = QuickDanmuku.getQuickDanmuku();
		Random r = GameSession.rand();
		
		//Ring
		double[][] midArray = new double[][] { { 300, 150, 470, 125, 300 }, { 150, 150, 150, 150, 150 } };
		double[] rArray = new double[] { 100, 70, 50, 100, 70 };
		double[] rotateSpeedArray = new double[] { 30, 60, 75, 50, 65 };
		double[] downSpeedArray = new double[] { 110, 120, 80, 90, 200 };
		int bulletCount = 8;
		for (int j = 0; j < midArray[0].length; j++) {
			int i = j;
			LogicExecutor.getLogicExecutor().schedule(() -> {
				OvalLauncherGroup olg = new OvalLauncherGroup(midArray[0][i], midArray[1][i]);
				olg.setAmount(bulletCount);
				olg.setLauncherConfig((launcher) -> {
					launcher.setBulletType(DiamondBullet.class);
					launcher.bindToWantBeRemoved(sourceElement);
					launcher.setBulletSpeed(rArray[i]);
					launcher.setBulletEvent((sesx, b) -> {
						LogicExecutor.getLogicExecutor().schedule(() -> {
							b.setWantBeRemoved(true);
						}, 999, TimeUnit.MILLISECONDS);
					});
				});
				olg.execute();
				LogicExecutor.getLogicExecutor().schedule(() -> {
					if(!sourceElement.getDeleted()){
						Helper midHelper = new Helper(midArray[0][i], midArray[1][i]);
						midHelper.setVelocityY(downSpeedArray[i]);
						mEU.add("midHelper" +r.nextInt(), midHelper);
						for (int k = 0; k < bulletCount; k++) {
							Bullet bullet = new DiamondBullet(midArray[0][i], midArray[1][i]);
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
							
							mEU.add("bullet" + r.nextInt(), bullet);
							mEU.add("helper" + r.nextInt(), helper);
						}
					}
				}, 1000, TimeUnit.MILLISECONDS);
			}, j * 2000, TimeUnit.MILLISECONDS);
		}
		
		//Snipe
		Consumer<Launcher> config = (Launcher launcher)->{
			launcher.bindToWantBeRemoved(sourceElement);
			launcher.bindToXY(sourceElement);
			launcher.setBulletSpeed(200);
			launcher.setBulletEvent((sesx,bullet)->{
				LogicExecutor.getLogicExecutor().schedule(() -> {
					bullet.setVelocityX(120);
					bullet.setVelocityY(0);
					quick.snipePlayer(bullet);
				}, 1000, TimeUnit.MILLISECONDS);
			});
		};
		ArcLauncherGroup alg = new ArcLauncherGroup(getSourceElement().getX(), getSourceElement().getY(), Math.PI/2*3, PI/2, 5);
		alg.setLauncherConfig(config);
		long startTime = 1000;
		
		for(int k=0;k<4;k++){
			for(int i=0;i<5;i++){
				LogicExecutor.getLogicExecutor().schedule(() -> {
					alg.setMidX(getSourceElement().getX());
					alg.setMidY(getSourceElement().getY());
					alg.execute();
				}, startTime+i*400+k*4000 , TimeUnit.MILLISECONDS);
			}
		}
		
	}

}
