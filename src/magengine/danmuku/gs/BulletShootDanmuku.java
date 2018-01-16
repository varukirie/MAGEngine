package magengine.danmuku.gs;


import java.util.concurrent.TimeUnit;

import magengine.bullet.PresetColor;
import magengine.bullet.impl.CircleBullet;
import magengine.chapter.util.QuickDanmuku;
import magengine.danmuku.ADanmuku;
import magengine.element.impl.Player;
import magengine.game.LogicExecutor;
import magengine.launcher.ArcLauncherGroup;
import magengine.launcher.Launcher;
import magengine.launcher.OvalLauncherGroup;
import magengine.util.ElementUtils;

public class BulletShootDanmuku extends ADanmuku{
	final Player p = Player.getPlayer1();
	int bulletSpeed = 1;
	int starlength = 5;
	ElementUtils mEU = getmEU();
	QuickDanmuku quick = QuickDanmuku.getQuickDanmuku();
	@Override
	public void executeDanmuku() {
		// TODO Auto-generated method stub
		OvalLauncherGroup olg = new OvalLauncherGroup(300,200,6);
		OvalLauncherGroup olgl = new OvalLauncherGroup(300,200,6);
		ArcLauncherGroup alg = new ArcLauncherGroup(300, 200, Math.PI/2,Math.PI/2, 6);
//		OvalHelper helper = new OvalHelper(300, 200, 50, 60);
//		olgl.setLauncherConfig((launcher) -> {
//			launcher.setBulletType(Launcher.class);
//			launcher.setBulletConfig((launcherN)->{
//				 Launcher ln = (Launcher) launcherN;
//				 
//				 ln.setDirection(Math.PI/2);
//				 ln.setBulletSpeed(50);
//				 ln.setInterval(50);
//				 });
//			launcher.setBulletEvent((sesx,launcherN)->{
//				 Launcher ln = (Launcher) launcherN;
//				 LogicExecutor.getLogicExecutor().schedule(() -> {
//				 ln.setInterval(100);
//				 }, 800,
//				 TimeUnit.MILLISECONDS);
//				 
//				 });
//			
//			launcher.setBulletSpeed(200);
//			launcher.setInterval(50);
//		});
		
		
		
		olg.setLauncherConfig((launcher) -> {
			launcher.setBulletType(CircleBullet.class);
			launcher.setBulletConfig((l)->{
				((CircleBullet)l).setR(20);
				((CircleBullet)l).setColorSupplier(PresetColor.blueOpacity.get());
			});
			launcher.setBulletEvent((sesx,bullet)->{
				LogicExecutor.getLogicExecutor().schedule(()->{
					quick.stopBullet(bullet);
					Launcher l = new Launcher(bullet.getX(),bullet.getY());
					l.setBulletSpeed(200);
					l.setBulletEvent((executor,b)->{
						executor.schedule(()->{
							quick.runBullet(b);
						}, 2000, TimeUnit.MILLISECONDS);
					});
				}, 800, TimeUnit.MILLISECONDS);
			});
			launcher.setBulletSpeed(200);
			launcher.setInterval(50);
		});
		
		
		
		
//		alg.setLauncherConfig((launcher) -> {
//			launcher.setBulletType(StarBullet.class);
//			launcher.getDirectionProperty().bind(helper.getDirectionProperty());
//			launcher.setBulletSpeed(200);
//			launcher.setInterval(30);
//		});
		olg.execute();
		mEU.add("testlauncher", olg);
//		alg.execute();
//		mEU.add("testlauncher", alg);
		
	}

}
