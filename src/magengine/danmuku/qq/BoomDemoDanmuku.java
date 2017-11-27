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
import magengine.game.LogicExecutor;
import magengine.helper.OvalHelper;
import magengine.launcher.ArcLauncherGroup;
import magengine.launcher.Launcher;
import magengine.launcher.OvalLauncherGroup;
import magengine.util.ElementUtils;

public class BoomDemoDanmuku extends ADanmuku{
	/*
	 * 实现子弹中可以爆开新的子弹
	 * 可以作用于小怪
	 */
	public static final long DURATION = 25000;
	ElementUtils mEU = getmEU();
	QuickDanmuku quick = QuickDanmuku.getQuickDanmuku();
	BaseElement be = getSourceElement();//弹幕绑定的对象
	Random r = new Random();
	private Stop[] stops = new Stop[]{
			new Stop(0, Color.BLACK),
			new Stop(1, Color.BLUE)
	};
	@Override
	public void executeDanmuku() {
		// TODO Auto-generated method stub
		arcBulletLauncher();
	}
	
	Supplier<Double> angleDeltaSupplier = ()->{
		return sin(LogicExecutor.gameTime()/2400.0)*8;//2400 8
	};
	
	public void arcBulletLauncher(){
			final int launcherCount = 6;
			for(int i=1; i<=launcherCount; i++){
				final double launcherAngle = Math.PI/6*i;
				Launcher launcher = new Launcher(300, 200);
				launcher.getxProperty().bind(getSourceElement().getxProperty());
				launcher.getyProperty().bind(getSourceElement().getyProperty());
				launcher.setInterval(4000);
				launcher.setDirection(launcherAngle);
				launcher.setDuration(DURATION);
				launcher.setBulletType(CircleBullet.class);
				launcher.setBulletConfig(b->{
					((CircleBullet)b).setR(10);
					((CircleBullet)b).setColorSupplier(PresetColor.getByStops(stops));
				});
				launcher.setBulletEvent((executor, bullet)->{
					executor.schedule(()->{
						for(int j=1; j<=launcherCount; j++){
							Launcher L1 = new Launcher(bullet.getX(), bullet.getY());
							L1.getxProperty().bind(bullet.getxProperty());
							L1.getyProperty().bind(bullet.getyProperty());
							L1.setInterval(4000);
							L1.setDirection(4000);
							L1.setDirection(launcherAngle*j);
							mEU.add("L1_"+r.nextInt(), L1);
						}
					}, 1000);
				});
				mEU.add("textLauncher"+r.nextInt(), launcher);
		}
	}
	
}

