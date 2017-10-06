package magengine.danmuku;

import java.util.function.Consumer;

import magengine.element.BaseElement;
import magengine.launcher.ArcLauncherGroup;
import magengine.launcher.Launcher;

public class TriArcDanmuku extends ADanmuku {


	@Override
	public void executeDanmuku() {
		Consumer<Launcher> config = (launcher)->{
			launcher.getxProperty().bind(sourceElement.getxProperty());
			launcher.getyProperty().bind(sourceElement.getyProperty());
		};
		new ArcLauncherGroup(sourceElement.getX(), sourceElement.getY(), Math.PI/2, Math.PI/3, 6).setLauncherConfig(config).delayExecute(700);
		ArcLauncherGroup alg = new ArcLauncherGroup(sourceElement.getX(), sourceElement.getY(), Math.PI/2, Math.PI/3, 6);
		alg.setLauncherConfig((launcher)->{
			config.accept(launcher);
			launcher.setBulletSpeed(180);
		}).delayExecute(850);

		new ArcLauncherGroup(sourceElement.getX(), sourceElement.getY(), Math.PI/2+Math.PI/5, Math.PI/3, 6).setLauncherConfig(config).delayExecute(1300);
		new ArcLauncherGroup(sourceElement.getX(), sourceElement.getY(), Math.PI/2-Math.PI/5, Math.PI/3, 6).setLauncherConfig(config).delayExecute(1900);
	}

}
