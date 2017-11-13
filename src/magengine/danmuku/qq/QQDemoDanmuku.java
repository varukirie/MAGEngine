package magengine.danmuku.qq;

import magengine.danmuku.ADanmuku;
import magengine.launcher.BurstLauncher;
import magengine.launcher.Launcher;
import magengine.util.ElementUtils;

import java.util.concurrent.TimeUnit;

public class QQDemoDanmuku extends ADanmuku {
	public static final long DURATION = 10000;
	@Override
	public void executeDanmuku() {
		ElementUtils mEU= super.getmEU();
		long delay = 2000;
		Launcher launcher = new BurstLauncher(300, 100, Math.PI/2, 3000, 10000);
		launcher.setBulletEvent((executor, bullet) -> {
			executor.schedule(()->{
				bullet.setVelocityX(140);
				bullet.setVelocityY(0);
			}, delay, TimeUnit.MILLISECONDS);
			executor.schedule(()->{
				bullet.setVelocityX(0);
				bullet.setVelocityY(100);
			}, delay+2000, TimeUnit.MILLISECONDS);
		});
		mEU.add("testLauncher", launcher);
		
	}

}
