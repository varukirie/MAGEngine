package magengine.danmuku;

import java.util.function.Consumer;

import magengine.launcher.ArcLauncherGroup;
import magengine.launcher.Launcher;

public class TriArcDanmuku extends ADanmuku {

	public static final long DURATION= 3000;

	@Override
	public void executeDanmuku() {
		Consumer<Launcher> config = (launcher)->{
			launcher.bindToXY(sourceElement);
			launcher.bindToWantBeRemoved(sourceElement);
		};
		new ArcLauncherGroup(sourceElement.getX(), sourceElement.getY(), Math.PI/2, Math.PI/3, 3).setLauncherConfig(config).delayExecute(700);
		ArcLauncherGroup alg = new ArcLauncherGroup(sourceElement.getX(), sourceElement.getY(), Math.PI/2, Math.PI/3, 6);
		alg.setLauncherConfig((launcher)->{
			config.accept(launcher);
			launcher.setBulletSpeed(180);
			launcher.bindToXY(sourceElement);
			launcher.bindToWantBeRemoved(sourceElement);
		}).delayExecute(850);

		new ArcLauncherGroup(sourceElement.getX(), sourceElement.getY(), Math.PI/2+Math.PI/5, Math.PI/3, 5).setLauncherConfig(config).delayExecute(1300);
		new ArcLauncherGroup(sourceElement.getX(), sourceElement.getY(), Math.PI/2-Math.PI/5, Math.PI/3, 6).setLauncherConfig(config).delayExecute(1900);
	}

}
