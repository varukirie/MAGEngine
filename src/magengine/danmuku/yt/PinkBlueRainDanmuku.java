package magengine.danmuku.yt;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import javafx.scene.paint.Color;
import javafx.scene.paint.Stop;
import magengine.bullet.PresetColor;
import magengine.bullet.impl.CircleBullet;
import magengine.bullet.impl.HexagonBullet;
import magengine.bullet.impl.LongHexagonBullet;
import magengine.chapter.util.QuickDanmuku;
import magengine.danmuku.ADanmuku;
import magengine.element.BaseElement;
import magengine.game.LogicExecutor;
import magengine.helper.OvalHelper;
import magengine.launcher.Launcher;
import magengine.launcher.OvalLauncherGroup;
import magengine.util.ElementUtils;

public class PinkBlueRainDanmuku extends ADanmuku {

	public static final long DURATION = 35000;

	private QuickDanmuku quick = QuickDanmuku.getQuickDanmuku();
	
	@Override
	public void executeDanmuku() {
		Stop[] stops1= {
				new Stop(0.8, Color.WHITE),
				new Stop(1.0,Color.rgb(255 ,105 ,180)),
				new Stop(0.6, Color.rgb(245, 245, 245,0.8)),
				
			};
		Stop[] stops2  = {
				new Stop(0.56, Color.WHITE),
				new Stop(1.0,Color.rgb(0 ,191, 255,0.75)),
				new Stop(0.75, Color.rgb(135, 206, 250,0.2)),
			};
		
		
		
			setOvalLauncher(stops1,1,0);
			setOvalLauncher(stops2,-1,0);
		
			setOvalLauncher(stops1,1,720);
			setOvalLauncher(stops2,-1,720);
		
			setOvalLauncher(stops1,-1,3200);
			setOvalLauncher(stops2, 1,3200);
			
			setOvalLauncher(stops1,-1,3750);
			setOvalLauncher(stops2, 1,3750);
		
	
			
			
			setOvalLauncher(stops1,-1,0);
			setOvalLauncher(stops2,1,0);
		
			setOvalLauncher(stops1,-1,720);
			setOvalLauncher(stops2,1,720);
		
			setOvalLauncher(stops1,1,3200);
			setOvalLauncher(stops2,-1,3200);
			

			
			

			
			setOvalLauncher(stops1,1,5200);
			setOvalLauncher(stops2,-1,5200);
		
			setOvalLauncher(stops1,1,7200);
			setOvalLauncher(stops2,-1,7200);
		
			setOvalLauncher(stops1,-1,81000);
			setOvalLauncher(stops2, 1,81000);
			
			

		
		for(int i=0;i<5;i++) {
			RandomRovateLauncher(stops2,i*520,9,17200);
			RandomRovateLauncher(stops1,i*520,9,17200);
		}
	
		for(int i=0;i<5;i++) {
			RandomRovateLauncher(stops2,i*520,9,21000);
			RandomRovateLauncher(stops1,i*520,9,21000);
		}
		for(int i=0;i<5;i++) {
			RandomRovateLauncher(stops2,i*520,9,25000);
			RandomRovateLauncher(stops1,i*520,9,25000);
		}
	
	
		
	 
		 
		 for(int j=0;j<5;j++){
			 setLongHexagonLauncher(320+j*4250,9, Color.rgb(255, 255, 0,0.5));
		
		 }
		 
		 
		 
		 for(int k=0;k<2;k++){
			 
			 setLongHexagonLauncher(2700+k*4200,15, Color.rgb(238, 0, 0,0.5));
		
		 }
		 
	}
	
	
	
	private void setOvalLauncher(Stop[] stops,int rRL,int delayTime){
		
		OvalLauncherGroup olg = new OvalLauncherGroup(sourceElement.getX(), sourceElement.getY(), 18);
		
		olg.setLauncherConfig((l)->{
			l.setBulletType(HexagonBullet.class);
			l.setBulletSpeed(81);
			
			l.setBulletConfig(b->{
				((HexagonBullet)b).setColorSupplier(PresetColor.redOpacity.getByStops(stops));
				((HexagonBullet)b).setR(5.0);
			});
			
			l.setBulletEvent((s,b)->{
				s.schedule(()->{
				quick.stopBullet(b);
				quick.VRotate(b, rRL*4*Math.PI/3);
				}, 52+delayTime);
				
				
				s.schedule(()->{
					quick.runBullet(b);
					quick.VRotate(b, 2*rRL*Math.PI/3);
					}, 520+delayTime);
				
              
				
				s.schedule(()->{
					quick.VRotate(b, rRL*Math.PI/2);
					
					}, 970+delayTime);
		
			
			});
			
			quick.bindToXY(l, getSourceElement());
		});
		olg.delayExecute(52+2*delayTime);
		

	}

	
	private void setLongHexagonLauncher(int delayTime,int num,Color color) {
		
		OvalLauncherGroup olg= new OvalLauncherGroup(sourceElement.getX(), sourceElement.getY(), num);
		
		olg.setLauncherConfig((l)->{
			l.setBulletType(LongHexagonBullet.class);
			l.setBulletSpeed(152.0);
		
			l.setBulletConfig(b->{
				((LongHexagonBullet)b).setNeedPaintOutline(true);
				
				((LongHexagonBullet)b).setOutlineColor(color);
				
				((LongHexagonBullet)b).setR(5.2);
			});
			
	
			
			quick.bindToXY(l, getSourceElement());
		});
		olg.delayExecute(1500+delayTime);
		
		
	}
	
	private void RandomRovateLauncher(Stop[] stops,int delayTime,int num ,int executeTime){
	
		OvalLauncherGroup olg = new OvalLauncherGroup(sourceElement.getX(), sourceElement.getY(), num);
		olg.setLauncherConfig((l)->{
			l.setBulletType(HexagonBullet.class);
			l.setBulletSpeed(110.0);
			quick.bindToWantBeRemoved(l, getSourceElement());
			l.setBulletConfig(b->{
				((HexagonBullet)b).setColorSupplier(PresetColor.redOpacity.getByStops(stops));
				((HexagonBullet)b).setR(5.0);
			});
			
			l.setBulletEvent((s,b)->{
				quick.VRotateRandom(b,2*Math.PI/3);
		       LogicExecutor.getLogicExecutor().schedule(() -> {
					quick.stopBullet(b);
					}, delayTime);
					
		       
		       LogicExecutor.getLogicExecutor().schedule(() -> {
		    	   quick.runBullet(b);
					quick.VRotateRandom(b,2*Math.PI/3);
					}, 6+delayTime);
					
		       
			});
			
			quick.bindToXY(l, getSourceElement());
		});
		olg.delayExecute(executeTime+delayTime);
		
	
		
	}
	
	

}
