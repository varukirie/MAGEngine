package magengine.danmuku;

import javafx.scene.paint.Color;
import magengine.bullet.PresetColor;
import magengine.bullet.impl.CircleBullet;
import magengine.chapter.util.QuickDanmuku;
import magengine.launcher.ArcLauncherGroup;
import magengine.launcher.BurstLauncher;

public class TriBurstDmk extends ADanmuku {

	public static final long DURATION = 8000;
	QuickDanmuku quick = QuickDanmuku.getQuickDanmuku();
	@Override
	public void executeDanmuku() {
		
		ArcLauncherGroup alg = new ArcLauncherGroup(sourceElement.getX(), sourceElement.getY(), Math.PI/2, Math.PI/4, 3);
		alg.setLauncherType(BurstLauncher.class);
		alg.setLauncherConfig((l)->{
			BurstLauncher brl = (BurstLauncher) l;
			brl.bindToXY(sourceElement);
			brl.bindToWantBeRemoved(sourceElement);
			brl.setBulletSpeed(150);
			brl.setBurstRange(150);
			brl.setBurstDuration(1000);
			brl.setBulletType(CircleBullet.class);
			brl.setBulletConfig((b)->{
				CircleBullet cb = (CircleBullet) b;
				cb.setR(15);
				cb.setColorSupplier(PresetColor.getRandomColorOpacityIn(new Color[]{Color.AZURE,Color.RED,Color.YELLOW,Color.GOLD,Color.GREENYELLOW}));
			});
		});
		for(int i=0;i<3;i++){
			alg.delayExecuteCheckAndWith(2000*i, sourceElement, ()->{
				alg.setDirection(quick.getPlayerDirectionAngle(sourceElement));
			});
		}
	}

}
