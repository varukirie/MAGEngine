package magengine.danmuku;

import java.util.Random;
import java.util.function.Consumer;


import javafx.application.Platform;
import javafx.scene.effect.Bloom;
import magengine.bullet.PresetColor;
import magengine.bullet.impl.CircleBullet;
import magengine.bullet.impl.HexagonBullet;
import magengine.chapter.util.QuickDanmuku;
import magengine.element.BaseElement;
import magengine.element.impl.Player;
import magengine.game.GameSession;
import magengine.launcher.ArcLauncherGroup;
import magengine.launcher.BurstLauncher;
import magengine.launcher.OvalLauncherGroup;
import magengine.paint.MyCanvasSwitcher;
import magengine.util.DI;

public class UtsuhoNonSpellCard1 extends ADanmuku {

	public static long DURATION = 7000;
	
	@Override
	public void executeDanmuku() {
		Random r = GameSession.rand;
		QuickDanmuku quick = QuickDanmuku.getQuickDanmuku();
		Platform.runLater(()->{
			((MyCanvasSwitcher) DI.di().get("switcher")).configCanvas(CircleBullet.class, (canvas) -> {
				canvas.setEffect(new Bloom());
			});
		});
		Consumer<BaseElement> circleBulletConfig = (bullet) -> {
//			((CircleBullet) bullet).setColorSupplier(PresetColor
//					.getRandomColorOpacityIn(new Color[] { Color.ORANGE, Color.WHITE, Color.AQUA,Color.CADETBLUE,Color.LIGHTCORAL,Color.MISTYROSE }));
			((CircleBullet) bullet).setR(22);
		};
		ArcLauncherGroup alg= new ArcLauncherGroup(300, 200, Math.PI/2, Math.PI/3,7);
		alg.setLauncherConfig((l)->{
			l.setBulletSpeed(190);
			((BurstLauncher)l).setBurstAmount(16);
			((BurstLauncher)l).setBurstRange(600);
			((BurstLauncher)l).setBurstDuration(1700);
			l.setBulletType(CircleBullet.class);
			l.setBulletConfig(circleBulletConfig);
			l.setBulletEvent((sesx,b)->{
			});
		});
		alg.setLauncherType(BurstLauncher.class);
		
		OvalLauncherGroup olg = new OvalLauncherGroup(300, 100, 48);
		olg.setLauncherConfig((l)->{
			l.setBulletType(CircleBullet.class);
			l.setBulletSpeed(120);
			l.setBulletEvent((s,b)->{
				s.schedule(()->{
					QuickDanmuku.getQuickDanmuku().VRotateRandom(b, Math.PI/7);
//					quick.VToByDirection(b, quick.getDirectionAngle(b.getX(), b.getY(), Player.getPlayer().getX(), Player.getPlayer().getY()));
				}, 1500);
			});
			l.setBulletConfig(b->{
				((CircleBullet)b).setColorSupplier(PresetColor.getOpacityByColor(javafx.scene.paint.Color.RED));
				((CircleBullet)b).setR(15);
			});
			quick.bindToXY(l, getSourceElement());
		});
		olg.execute();

		alg.delayExecuteCheckAndWith(3600,sourceElement,()->{
			alg.setMidX(getSourceElement().getX());
			alg.setMidY(getSourceElement().getY());
			alg.setDirection(quick.getPlayerDirectionAngle(getSourceElement()));
		});
	}

}
