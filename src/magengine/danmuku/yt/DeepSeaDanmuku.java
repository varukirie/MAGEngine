package magengine.danmuku.yt;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import application.Main;
import javafx.scene.paint.Color;
import javafx.scene.paint.Stop;
import magengine.bullet.PresetColor;
import magengine.bullet.impl.ArrowBullet;
import magengine.bullet.impl.CircleBullet;
import magengine.bullet.impl.DefaultBullet;
import magengine.bullet.impl.HexagonBullet;
import magengine.bullet.impl.PetalBullet;
import magengine.chapter.util.QuickDanmuku;
import magengine.chapter.util.SeqDanmuku;
import magengine.danmuku.ADanmuku;
import magengine.game.GameSession;
import magengine.game.LogicExecutor;
import magengine.helper.OvalHelper;
import magengine.launcher.ArcLauncherGroup;
import magengine.launcher.BurstLauncher;
import magengine.launcher.Launcher;
import magengine.launcher.OvalLauncherGroup;
import magengine.util.ElementUtils;

public class DeepSeaDanmuku extends ADanmuku {
	public static final long DURATION =25000;
	LogicExecutor ses = LogicExecutor.getLogicExecutor();
	private int count = 0;
	private int midX = 250;
	private int midY = 150;
	private ElementUtils mEU = getmEU();
	private QuickDanmuku quick = QuickDanmuku.getQuickDanmuku();
	
	@Override
	public void executeDanmuku() {
		
		int lcount = 5;
		for (int i = 0; i < lcount; i++) {
			rotateDSnipe(midX, midY, 700, 2 * Math.PI / lcount * i,1);
		}
		
		for (int i = 0; i < lcount; i++) {
			rotateDSnipe(midX, midY, 9750,2 * Math.PI / lcount * i,-1);
		}
		
		Stop[] stops= {
				new Stop(0.8, Color.WHITE),
				new Stop(1.0,Color.rgb(255 ,105 ,180)),
				new Stop(0.6, Color.rgb(245, 245, 245,0.8)),
			};
		
		Stop[] blueStops= {
				new Stop(0.8, Color.WHITE),
				new Stop(1.0,Color.rgb(0 ,0,255)),
				new Stop(0.6, Color.rgb(255, 255, 255,0.8)),
			};
		
		Stop[] deepBlueStops= {
			
				new Stop(0, Color.rgb(30,144 ,255,0.0)),
				new Stop(0.2, Color.rgb(30 ,144 ,255, 0.0)), 
				new Stop(0.4, Color.rgb(0 ,0,255, 0.7)), 
				new Stop(0.75, Color.rgb(245, 245, 245, 1)),
				new Stop(1, Color.rgb(245, 245, 245, 1)),
				
			};
		
		Stop[] deepPurpleStops= {
				
				new Stop(0, Color.rgb(186 ,85 ,211,0.0)),
				new Stop(0.2, Color.rgb(186 ,85 ,211, 0.0)), 
				new Stop(0.4, Color.rgb(153 ,50 ,204,1)), 
				new Stop(0.75, Color.rgb(245, 245, 245, 1)),
				new Stop(1, Color.rgb(245, 245, 245, 1)),
				
			};
		
		
		
		Launcher launcher = new Launcher(sourceElement.getX(), sourceElement.getY(),Math.PI/2,1200,12000);
		launcher.setBulletType(CircleBullet.class);
		launcher.setBulletSpeed(81);
		
		launcher.setBulletConfig(b->{
			((CircleBullet)b).setColorSupplier(PresetColor.blueOpacity.getByStops(deepPurpleStops));
			((CircleBullet)b).setR(21.0);
			quick.VToByDirection(b, quick.getPlayerDirectionAngle(b));
		});
		
		    mEU.add("launcher", launcher);
		    
		    
		
        int num =3;
		for(int k =0;k<num;k++){
		BubbleLauncherGroup(0,300+k*100,720,210,blueStops,12);
		}
		 BubbleLauncherGroup(0,300+250,720,210,blueStops,12);
		 BubbleLauncherGroup(0,300+400,720,210,blueStops,12);
		
		 for(int k =0;k<num;k++){
			BubbleLauncherGroup(0,320+k*150,2100,12000,deepBlueStops,27);
			}


		
	}
	

	public void rotateDSnipe(double midX, double midY, long startTime,double angle,int RL){
		double R = 30;
		double speed = 40;
		int helperCount = count++;
		long duration=8000;
		
		OvalHelper helper = new OvalHelper(midX, midY, R, speed, angle);
		
		Launcher launcher = new Launcher(100, 100, 0.5*Math.PI, 220, duration);
		
		launcher.getDirectionProperty().bind(helper.getDirectionProperty());
		launcher.getxProperty().bind(helper.getxProperty());
		launcher.getyProperty().bind(helper.getyProperty());
		launcher.setBulletType(PetalBullet.class);
		
		Stop[] stops  = {
				new Stop(0.56, Color.WHITE),
				new Stop(1.0,Color.rgb(0 ,191, 255,0.75)),
				new Stop(0.75, Color.rgb(135, 206, 250,0.5)),
			};
		
		
		launcher.setBulletEvent((sEX, bullet) -> {
			((PetalBullet)bullet).setR(4.2);

			((PetalBullet)bullet).setColorSupplier(PresetColor.blueOpacity.getByStops(stops));
			
		
			sEX.schedule(() -> {
				bullet.setVelocityX(bullet.getVelocityX()*0.01);
				bullet.setVelocityY(bullet.getVelocityY()*0.01);
			}, 1000, TimeUnit.MILLISECONDS);
			
			sEX.schedule(() -> {
				bullet.setVelocityX(bullet.getVelocityX()*180);
				bullet.setVelocityY(bullet.getVelocityY()*180);
				quick.VRotate(bullet, RL*Math.PI);
				
			}, 3500, TimeUnit.MILLISECONDS);
			
			sEX.schedule(() -> {
				bullet.setVelocityX(bullet.getVelocityX()*0.01);
				bullet.setVelocityY(bullet.getVelocityY()*0.01);
				quick.VRotate(bullet, RL*Math.PI/3);
				
			}, 4700, TimeUnit.MILLISECONDS);
			
			
			sEX.schedule(() -> {
				bullet.setVelocityX(bullet.getVelocityX()*100);
				bullet.setVelocityY(bullet.getVelocityY()*100);
				quick.VRotate(bullet,RL*Math.PI);
				
			}, 7200, TimeUnit.MILLISECONDS);
			
			
			sEX.schedule(() -> {
				bullet.setVelocityX(bullet.getVelocityX()*0.01);
				bullet.setVelocityY(bullet.getVelocityY()*0.01);
				quick.VRotate(bullet, RL*Math.PI/3);
				
			}, 8100, TimeUnit.MILLISECONDS);
			
			sEX.schedule(() -> {
				bullet.setVelocityX(bullet.getVelocityX()*100);
				bullet.setVelocityY(bullet.getVelocityY()*100);
				quick.VRotate(bullet,RL*Math.PI/3);
				
			}, 9800, TimeUnit.MILLISECONDS);
			
			
			
			
		});
		
		
		LogicExecutor.getLogicExecutor().schedule(() -> {
			mEU.add("helper"+helperCount, helper);
		}, startTime, TimeUnit.MILLISECONDS);
		
		LogicExecutor.getLogicExecutor().schedule(() -> {
			mEU.add("launcher"+count++, launcher);
		}, startTime+1, TimeUnit.MILLISECONDS);
		
		LogicExecutor.getLogicExecutor().schedule(() -> {
			mEU.removeBoth("helper" + helperCount);
		}, startTime+duration+2, TimeUnit.MILLISECONDS);
	}
	

	private void BubbleLauncherGroup(int midX,int midY,int duration,int delayTime,Stop[] stops,int r) {
		
		LogicExecutor.getLogicExecutor().schedule(()->{
        	Launcher launcher = new Launcher(midX,midY,0,duration,12000);
			launcher.setBulletType(CircleBullet.class);
			launcher.setBulletSpeed(75);
			
			launcher.setBulletConfig(b->{
				((CircleBullet)b).setColorSupplier(PresetColor.blueOpacity.getByStops(stops));
				((CircleBullet)b).setR(r);
			});
			
  		    mEU.add("launcher"+midY, launcher);
		},delayTime+duration);
		
	}
	
	
}
