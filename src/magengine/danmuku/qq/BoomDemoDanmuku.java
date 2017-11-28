package magengine.danmuku.qq;

import static java.lang.Math.PI;
import static java.lang.Math.sin;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.crypto.AEADBadTagException;
import javax.print.attribute.SetOfIntegerSyntax;

import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;

import io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue.Supplier;
import javafx.scene.paint.Color;
import javafx.scene.paint.Stop;
import magengine.bullet.PresetColor;
import magengine.bullet.impl.ArrowBullet;
import magengine.bullet.impl.CircleBullet;
import magengine.bullet.impl.DiamondBullet;
import magengine.bullet.impl.FllowerBullet;
import magengine.bullet.impl.HexagonBullet;
import magengine.bullet.impl.LongHexagonBullet;
import magengine.bullet.impl.PlayerBullet;
import magengine.chapter.util.QuickDanmuku;
import magengine.danmuku.ADanmuku;
import magengine.element.BaseElement;
import magengine.game.GameSession;
import magengine.game.LogicExecutor;
import magengine.helper.OvalHelper;
import magengine.launcher.ArcLauncherGroup;
import magengine.launcher.Launcher;
import magengine.launcher.OvalLauncherGroup;
import magengine.util.ElementUtils;

public class BoomDemoDanmuku extends ADanmuku {
	/*
	 * 实现子弹中可以爆开新的子弹 可以作用于小怪
	 */
	public static final long DURATION = 25000;
	ElementUtils mEU = getmEU();
	QuickDanmuku quick = QuickDanmuku.getQuickDanmuku();
	BaseElement be = getSourceElement();// 弹幕绑定的对象
	Random r = GameSession.rand();
	private Stop[] stops = new Stop[] { new Stop(0, Color.WHITESMOKE), new Stop(1, Color.BLUE) };

	@Override
	public void executeDanmuku() {
		arcBulletLauncher();
	}

	Supplier<Double> angleDeltaSupplier = () -> {
		return sin(LogicExecutor.gameTime() / 2400.0) * 8;// 2400 8
	};

	public void arcBulletLauncher() {
		// final int launcherCount = 6;
		// for(int i=1; i<=launcherCount; i++){
		// final double launcherAngle = Math.PI/6*i;
		// Launcher launcher = new Launcher(300, 200);
		// launcher.getxProperty().bind(getSourceElement().getxProperty());
		// launcher.getyProperty().bind(getSourceElement().getyProperty());
		// launcher.setInterval(4000);
		// launcher.setDirection(launcherAngle);
		// launcher.setDuration(DURATION);
		// launcher.setBulletType(CircleBullet.class);
		// launcher.setBulletConfig(b->{
		// ((CircleBullet)b).setR(10);
		// ((CircleBullet)b).setColorSupplier(PresetColor.getByStops(stops));
		// });
		//
		// mEU.add("textLauncher"+r.nextInt(), launcher);
		// }
		ArcLauncherGroup alg = new ArcLauncherGroup(getSourceElement().getX(), getSourceElement().getY(), Math.PI / 2*3,
				Math.PI / 3, 3);
		alg.setLauncherConfig(l -> {
			l.bindToXY(getSourceElement());
			l.setInterval(5000);
			l.setDuration(DURATION);
			l.setBulletType(CircleBullet.class);
			l.bindToWantBeRemoved(sourceElement);
			l.setBulletConfig(b -> {
				((CircleBullet) b).setR(10);
				((CircleBullet) b).setColorSupplier(PresetColor.getByStops(stops));
			});
			l.setBulletEvent((executor, bullet) -> {
				executor.schedule(() -> {
					OvalLauncherGroup olg = new OvalLauncherGroup(bullet.getX(), bullet.getY(), 16, Math.PI / 2);
					olg.setLauncherConfig((subl) -> {
						subl.setBulletSpeed(105);
						subl.setBulletType(LongHexagonBullet.class);
						subl.setBulletConfig(b->{
							if(b instanceof LongHexagonBullet){
								((LongHexagonBullet) b).setR(5);
							}
						});
						subl.bindToXY(bullet);
					});
					
					olg.execute();
					bullet.setWantBeRemoved(true);
				}, 1500);
			});

		});
		alg.execute();

	}

}
