package magengine.danmuku.qq;

import java.util.Random;

import javafx.scene.paint.Color;
import javafx.scene.paint.Stop;
import magengine.bullet.PresetColor;
import magengine.bullet.impl.CircleBullet;
import magengine.chapter.util.QuickDanmuku;
import magengine.danmuku.ADanmuku;
import magengine.element.BaseElement;
import magengine.game.GameSession;
import magengine.launcher.ArcLauncherGroup;
import magengine.util.ElementUtils;

public class BoomDanmuku extends ADanmuku {
	/*
	 * 实现子弹中可以爆开新的子弹 可以作用于小怪
	 */
	public static final long DURATION = 10000;
	ElementUtils mEU = getmEU();
	QuickDanmuku quick = QuickDanmuku.getQuickDanmuku();
	BaseElement be = getSourceElement();// 弹幕绑定的对象
	Random r = GameSession.rand();
	private Stop[] stops = new Stop[]{new Stop(0, Color.SNOW), new Stop(1, Color.SKYBLUE)};
	private Stop[] stopst = new Stop[]{new Stop(0, Color.SNOW), new Stop(1, Color.BLUE)};
	
	@Override
	public void executeDanmuku() {
		arcBulletLauncher();
	}
	
	public void arcBulletLauncher(){
		ArcLauncherGroup alg = new ArcLauncherGroup(getSourceElement().getX(), getSourceElement().getY(), 
				-Math.PI / 2 * 3, Math.PI / 3, 5);
		alg.setLauncherConfig(l->{
			l.setInterval(1000);
			l.setDuration(5000);
			l.bindToXY(getSourceElement());
			l.bindToWantBeRemoved(getSourceElement());//与绑定对象共存亡
			l.setBulletType(CircleBullet.class);
			l.setBulletConfig(b->{
				((CircleBullet) b).setR(10);
				((CircleBullet) b).setColorSupplier(PresetColor.getByStops(stops));
			});
			l.setBulletEvent((sESx, bullet)->{
				ArcLauncherGroup salg = new ArcLauncherGroup(bullet.getX(), bullet.getY(), 
						Math.PI/2*3, Math.PI/3, 5);
				salg.setLauncherConfig(sl->{
					sl.bindToXY(bullet);
				});
				sESx.schedule(()->{
					quick.stopBullet(bullet);
					((CircleBullet)bullet).setR(10);
					((CircleBullet)bullet).setColorSupplier(PresetColor.getByStops(stopst));
				}, 7600);
				
				sESx.schedule(()->{
					salg.execute();
					bullet.setWantBeRemoved(true);
				}, 9000);
			});
		});
		alg.execute();
		
	}
	
}
