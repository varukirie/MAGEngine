package magengine.danmuku.yt;

import java.util.Random;
import javafx.scene.paint.Color;
import javafx.scene.paint.Stop;
import magengine.bullet.PresetColor;
import magengine.bullet.impl.CircleBullet;
import magengine.bullet.impl.HexagonBullet;
import magengine.bullet.impl.LongHexagonBullet;
import magengine.chapter.util.QuickDanmuku;
import magengine.danmuku.ADanmuku;
import magengine.element.BaseElement;
import magengine.game.GameSession;
import magengine.launcher.OvalLauncherGroup;

public class autumnSkyDanmuku extends ADanmuku {

	@Override
	public void executeDanmuku() {
		Stop[] stops1= {
				new Stop(0.8, Color.DEEPPINK),
				new Stop(0.1,Color.rgb(255,130, 171)),
				new Stop(0.6, Color.rgb(245, 245, 245,0.4)),
				
			};
		Stop[] stops2  = {
				new Stop(0.8, Color.DEEPSKYBLUE),
				new Stop(0.8,Color.rgb(0 ,191, 255)),
				new Stop(0.2, Color.rgb(245, 245, 245,0.2)),
			};
		
		
			setOvalLauncher(stops1,1,0);
			setOvalLauncher(stops2,-1,0);
		
			setOvalLauncher(stops1,1,320);
			setOvalLauncher(stops2,-1,320);
		
			setOvalLauncher(stops1,-1,720);
			setOvalLauncher(stops2, 1,720);
		
		
		
		
		
		
		 setLongHexagonLauncher(1,2700);
		 setLongHexagonLauncher(1,2700);

	}
	
	
	
	private void setOvalLauncher(Stop[] stops,int rRL,int delayTime){
		
		Random r = GameSession.rand;
		QuickDanmuku quick = QuickDanmuku.getQuickDanmuku();
		OvalLauncherGroup olg = new OvalLauncherGroup(300, 100, 42);
		
		olg.setLauncherConfig((l)->{
			l.setBulletType(HexagonBullet.class);
			l.setBulletSpeed(210);
			
			l.setBulletConfig(b->{
				((HexagonBullet)b).setColorSupplier(PresetColor.redOpacity.getByStops(stops));
				((HexagonBullet)b).setR(5.0);
			});
			
			l.setBulletEvent((s,b)->{
				s.schedule(()->{
				quick.stopBullet(b);
				quick.VRotate(b, rRL*4*Math.PI/3);
				}, 720+delayTime);
				
				
				s.schedule(()->{
					quick.runBullet(b);
					quick.VRotate(b, 2*rRL*Math.PI/3);
					}, 950+delayTime);
				
              
				
				s.schedule(()->{
					quick.VRotate(b, rRL*Math.PI/2);
					
					}, 1210+delayTime);
		
			
			});
			
			quick.bindToXY(l, getSourceElement());
		});
		olg.execute();
		

	}

	
	private void setLongHexagonLauncher(int rRL,int delayTime) {
		
		Random r = GameSession.rand;
		QuickDanmuku quick = QuickDanmuku.getQuickDanmuku();
		OvalLauncherGroup olg= new OvalLauncherGroup(300, 100, 42);
		
		olg.setLauncherConfig((l)->{
			l.setBulletType(LongHexagonBullet.class);
			l.setBulletSpeed(250);
		
			l.setBulletConfig(b->{
				((LongHexagonBullet)b).setNeedPaintOutline(true);
				
				((LongHexagonBullet)b).setOutlineColor(Color.rgb(238, 0, 0,0.5));
				
				((LongHexagonBullet)b).setR(5.0);
			});
			
			l.setBulletEvent((s,b)->{
				s.schedule(()->{
				quick.stopBullet(b);
				}, 720+delayTime);
				
				
			
               s.schedule(()->{
   				quick.runBullet(b);
   				quick.VRotate(b, rRL*2*Math.PI/3);
   				}, 2750+delayTime);
   				
			
			});
			
			quick.bindToXY(l, getSourceElement());
		});
		olg.delayExecute(2000);
		
		
	}

}
