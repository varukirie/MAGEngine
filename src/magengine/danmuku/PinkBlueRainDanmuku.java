package magengine.danmuku;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import javafx.scene.paint.Color;
import javafx.scene.paint.Stop;
import magengine.bullet.PresetColor;
import magengine.bullet.impl.CircleBullet;
import magengine.bullet.impl.HexagonBullet;
import magengine.bullet.impl.LongHexagonBullet;
import magengine.chapter.util.QuickDanmuku;
import magengine.element.BaseElement;
import magengine.game.LogicExecutor;
import magengine.helper.OvalHelper;
import magengine.launcher.Launcher;
import magengine.launcher.OvalLauncherGroup;
import magengine.util.ElementUtils;

public class PinkBlueRainDanmuku extends ADanmuku {

	public static final long DURATION = 18000;

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
		
		
		
			setOvalLauncher(stops1,1,25);
			setOvalLauncher(stops2,-1,25);
		
			setOvalLauncher(stops1,1,720);
			setOvalLauncher(stops2,-1,720);
		
			setOvalLauncher(stops1,-1,1200);
			setOvalLauncher(stops2, 1,1200);
			
			setOvalLauncher(stops1,-1,1800);
			setOvalLauncher(stops2, 1,1800);

			


		
		for(int i=0;i<5;i++) {
			RandomRovateLauncher(stops2,i*520,21,9000);
			RandomRovateLauncher(stops1,i*520,18,9000);
		}
		
		for(int i=0;i<5;i++) {
			RandomRovateLauncher(stops2,i*420,15,13000);
			RandomRovateLauncher(stops1,i*420,18,13000);
		}
		
		

		 for(int j=0;j<2;j++){
			 setLongHexagonLauncher(320+j*4250,9, Color.rgb(255, 255, 0,0.5),5.2);
		
		 }
		 setLongHexagonLauncher(2700,15, Color.rgb(238, 0, 0,0.5),5.2);
		 setLongHexagonLauncher(4250+2100,21, Color.rgb(238, 0, 0,0.5),2.5);

		 
	}
	
		
	private void setOvalLauncher(Stop[] stops,int rRL,int delayTime){
		
		OvalLauncherGroup olg = new OvalLauncherGroup(sourceElement.getX(), sourceElement.getY(), 18);
		
		olg.setLauncherConfig((l)->{
			l.setBulletType(HexagonBullet.class);
			l.setBulletSpeed(120);
			
			
			l.setBulletConfig(b->{
				((HexagonBullet)b).setColorSupplier(PresetColor.getByStops(stops));
				((HexagonBullet)b).setR(5.0);
			});
			
			l.setBulletEvent((s,b)->{
				s.schedule(()->{
				quick.stopBullet(b);
				quick.VRotate(b, rRL*4*Math.PI/3);
				}, delayTime-250);
				
				
				s.schedule(()->{
					quick.runBullet(b);
					
					}, 120+delayTime);
	
			});


			quick.bindToWantBeRemoved(l, getSourceElement());
			quick.bindToXY(l, getSourceElement());
			
		});
		
		
		olg.delayExecute(52+2*delayTime);
		

	}

	
	private void setLongHexagonLauncher(int delayTime,int num,Color color,double R) {
		
		OvalLauncherGroup olg= new OvalLauncherGroup(sourceElement.getX(), sourceElement.getY(), num);
		
		olg.setLauncherConfig((l)->{
			l.setBulletType(LongHexagonBullet.class);
			l.setBulletSpeed(112.0);
		
			l.setBulletConfig(b->{
				((LongHexagonBullet)b).setNeedPaintOutline(true);
				
				((LongHexagonBullet)b).setOutlineColor(color);
				
				((LongHexagonBullet)b).setR(R);
			});
			
	

			quick.bindToWantBeRemoved(l, getSourceElement());
			quick.bindToXY(l, getSourceElement());
			
		});
		olg.delayExecute(1500+delayTime);
		
		
	}
	
	private void RandomRovateLauncher(Stop[] stops,int delayTime,int num ,int executeTime){
	
		OvalLauncherGroup olg = new OvalLauncherGroup(sourceElement.getX(), sourceElement.getY(), num);
		olg.setLauncherConfig((l)->{
			l.setBulletType(HexagonBullet.class);
			l.setBulletSpeed(132.0);
			quick.bindToWantBeRemoved(l, getSourceElement());
			l.setBulletConfig(b->{
				((HexagonBullet)b).setColorSupplier(PresetColor.getByStops(stops));
				((HexagonBullet)b).setR(5.0);
			});
			
			l.setBulletEvent((s,b)->{
				quick.VRotateRandom(b,2*Math.PI);
		       LogicExecutor.getLogicExecutor().schedule(() -> {
					quick.stopBullet(b);
					}, delayTime);
	       
		       LogicExecutor.getLogicExecutor().schedule(() -> {
		    	   quick.runBullet(b);
					}, 6+delayTime);
					
		       
			});
			

			quick.bindToWantBeRemoved(l, getSourceElement());
			quick.bindToXY(l, getSourceElement());
		});
		olg.delayExecute(executeTime+delayTime);

	}	
	

}
