package magengine.danmuku;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import application.Main;
import javafx.scene.paint.Color;
import magengine.bullet.PresetColor;
import magengine.bullet.impl.HexagonBullet;
import magengine.chapter.util.QuickDanmuku;
import magengine.element.BaseElement;
import magengine.game.LogicExecutor;
import magengine.helper.OvalHelper;
import magengine.helper.RelativeAnchorHelper;
import magengine.launcher.Launcher;
import magengine.util.ElementUtils;

import static application.Main.*;

public class ReisenNonSpellCardDanmuku extends ADanmuku{

	private static final long speedChangeTime = 100;
	private static final double startBulletSpeed = 800;
	private static final double changedBulletSpeed= 85;
	private static final double anchorR = 70;
	private static final long interval = (long) (230*(1.0/Main.level.getFactor()));
	private static final double rotateSpeed = 9;
	private static final long duration = 10000;
	private ElementUtils mEU = getmEU();
	private Random r = new Random();
	private QuickDanmuku quick = QuickDanmuku.getQuickDanmuku();
	@Override
	public void executeDanmuku() {
		
		BaseElement enemy = getSourceElement();
		anchor(enemy, anchorR, anchorR,true);
		anchor(enemy, -anchorR, anchorR,false);
		anchor(enemy, anchorR, -anchorR,true);
		anchor(enemy, -anchorR, -anchorR,false);
	}
	
	private void anchor(BaseElement enemy,double deltaX,double deltaY,boolean positive){
		RelativeAnchorHelper helper = new RelativeAnchorHelper(enemy, deltaX, deltaY);
		quick.bindToWantBeRemoved(helper, getSourceElement());
		helper.setDuration(duration);
		mEU.add("AnchorHelper "+r.nextInt(), helper);
		setRandomRotateLauncher(helper, positive, duration, 0);
		setRandomRotateLauncher(helper, positive, duration, Math.PI/2);
		setRandomRotateLauncher(helper, positive, duration, Math.PI);
		setRandomRotateLauncher(helper, positive, duration, Math.PI/2*3);
	}
	
	private void setRandomRotateLauncher(BaseElement target,boolean positive,long duration,double startAngle){
		OvalHelper helper = new OvalHelper(target.getX(),target.getY() , 1,rotateSpeed , startAngle, duration);
		helper.setPositive(positive);
		quick.bindToWantBeRemoved(helper, getSourceElement());
		mEU.add("circle "+r.nextInt(), helper);
		Launcher l = new Launcher(target.getX(), target.getY(), 0, interval, duration);
		l.setBulletSpeed(startBulletSpeed);
		l.setBulletType(HexagonBullet.class);
		quick.bindToWantBeRemoved(l, getSourceElement());
		l.setBulletConfig((b)->{
			((HexagonBullet)b).setR(5);
			((HexagonBullet)b).setColorSupplier((x)->{return Color.rgb(245, 245, 245,0.8);});
		});
		l.setBulletEvent((sesx,b)->{
			LogicExecutor.getLogicExecutor().schedule(() -> {
				quick.setSpeed(b, changedBulletSpeed);
			}, speedChangeTime, TimeUnit.MILLISECONDS);
		});
		quick.bindToXY(l, target);
		l.getDirectionProperty().bind(helper.getDirectionProperty());
		mEU.add("launcher "+r.nextInt(), l);
	}
}
