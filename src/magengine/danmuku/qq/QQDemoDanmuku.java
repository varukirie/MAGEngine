package magengine.danmuku.qq;

import magengine.bullet.impl.StarBullet;
import magengine.chapter.util.QuickDanmuku;
import magengine.danmuku.ADanmuku;
import magengine.launcher.Launcher;
import magengine.launcher.OvalLauncherGroup;
import magengine.util.ElementUtils;

import java.util.concurrent.TimeUnit;

public class QQDemoDanmuku extends ADanmuku {
	public static final long DURATION = 10000;
	@Override
	public void executeDanmuku() {
		
		ElementUtils mEU= super.getmEU();
		int delay = 3000;
		Launcher startLauncher = new Launcher(300, 200, Math.PI / 2, 20, delay);
		startLauncher.setBulletType(StarBullet.class);
		startLauncher.setBulletEvent((executor, bullet)->{
			executor.schedule(()->{
				bullet.setVelocityX(Math.random()*300*Math.pow(-1, (int)(Math.random()*10)));
				bullet.setVelocityY(Math.random()*300*Math.pow(-1, (int)(Math.random()*10)));
			}, 0, TimeUnit.MILLISECONDS);
		});
		mEU.add("startLauncher", startLauncher);
		//Բ�ε�Ļ
		QuickDanmuku quick = QuickDanmuku.getQuickDanmuku();
		OvalLauncherGroup oLG = new OvalLauncherGroup(300, 200, 100);
		oLG.setLauncherConfig((ovalLauncher)->{
			ovalLauncher.setBulletEvent((sESx, bullet)->{
				sESx.schedule(()->{
					quick.stopBullet(bullet);
				}, 2000, TimeUnit.MILLISECONDS);
				sESx.schedule(()->{
					quick.runBullet(bullet);
					quick.VTransform(bullet, new double[][]{
						{Math.pow(-1, (int)(Math.random()*10)), 0}, {0, Math.pow(-1, (int)(Math.random()*10)) }
					});
				}, 4000, TimeUnit.MILLISECONDS);
			});
		});
		oLG.delayExecute(500);
		
		OvalLauncherGroup smallOLG = new OvalLauncherGroup(300, 200, 100);
		smallOLG.setLauncherConfig((ovalLauncher)->{
			ovalLauncher.setBulletEvent((sESx, bullet)->{
				sESx.schedule(()->{
					quick.stopBullet(bullet);
				}, 1000, TimeUnit.MILLISECONDS);
				sESx.schedule(()->{
					quick.runBullet(bullet);
					quick.VTransform(bullet, new double[][]{
						{0, Math.pow(-1, (int)(Math.random()*10))}, {Math.pow(-1, (int)(Math.random()*10)),0 }
					});
				}, 2000, TimeUnit.MILLISECONDS);
			});
		});
		smallOLG.delayExecute(500);
		
	}

}
