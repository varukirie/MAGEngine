package magengine.danmuku;

import static java.lang.Math.*;

import java.util.Random;
import java.util.function.Supplier;

import javafx.scene.paint.Color;
import magengine.bullet.impl.HexagonBullet;
import magengine.chapter.util.QuickDanmuku;
import magengine.element.BaseElement;
import magengine.game.GameSession;
import magengine.game.LogicExecutor;
import magengine.launcher.Launcher;
import magengine.util.ElementUtils;

public class WaveParticleDanmuku extends ADanmuku {

	public static final long DURATION = 24000;
	@Override
	public void executeDanmuku() {
		final int lchCount = 12;
		
		BaseElement se = getSourceElement();
		QuickDanmuku quick = QuickDanmuku.getQuickDanmuku();
		ElementUtils mEU = getmEU();
		Random r = GameSession.rand();
		
		Supplier<Double> angleDeltaSupplier = ()->{
			return sin(LogicExecutor.gameTime()/3500.0)*16;
		};
		
		for(int i=0;i<lchCount;i++){
			final double angle = 2*PI/lchCount*i;
			Launcher lch = new Launcher(se.getX(), se.getY());
			quick.bindToXY(lch, se);
			quick.bindToWantBeRemoved(lch, se);
			lch.setDuration(DURATION);
			lch.setInterval(120);
			lch.setBulletSpeed(120);
			lch.setBulletType(HexagonBullet.class);
			lch.setBulletConfig((b)->{
				((HexagonBullet)b).setR(5);
				((HexagonBullet)b).setColorSupplier((x)->{return Color.rgb(245, 245, 245,0.8);});
			});
			lch.setLambdaModify((l)->{
				if(l instanceof Launcher){
					double angleDelta = angleDeltaSupplier.get();
					((Launcher) l).setDirection(angle+angleDelta);
				}
			});
			mEU.add("WP-lch"+r.nextInt(), lch);
		}
	}

}
