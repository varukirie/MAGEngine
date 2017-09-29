package magengine.danmuku;

import java.util.function.Consumer;

import magengine.element.BaseElement;
import magengine.launcher.ArcLauncherGroup;
import magengine.launcher.Launcher;

public class TriArcDanmuku extends ADanmuku {

	private BaseElement midElem = null;
	public TriArcDanmuku(BaseElement midElem) {
		super();
		this.midElem=midElem;
	}
	
	@Override
	public void executeDanmuku() {
		Consumer<Launcher> config = (launcher)->{
			launcher.getxProperty().bindBidirectional(midElem.getxProperty());
			launcher.getyProperty().bindBidirectional(midElem.getyProperty());
		};
		new ArcLauncherGroup(midElem.getX(), midElem.getY(), Math.PI/2, Math.PI/3, 6).setLauncherConfig(config).delayExecute(700);
		ArcLauncherGroup alg = new ArcLauncherGroup(midElem.getX(), midElem.getY(), Math.PI/2, Math.PI/3, 6);
		alg.setLauncherConfig((launcher)->{
			config.accept(launcher);
			launcher.setBulletSpeed(0.9);
		}).delayExecute(850);

		new ArcLauncherGroup(midElem.getX(), midElem.getY(), Math.PI/2+Math.PI/5, Math.PI/3, 6).setLauncherConfig(config).delayExecute(1300);
		new ArcLauncherGroup(midElem.getX(), midElem.getY(), Math.PI/2-Math.PI/5, Math.PI/3, 6).setLauncherConfig(config).delayExecute(1900);
	}

}
