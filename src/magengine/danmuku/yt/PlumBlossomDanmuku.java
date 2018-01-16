package magengine.danmuku.yt;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Stop;
import magengine.bullet.PresetColor;
import magengine.bullet.impl.ArrowBullet;
import magengine.bullet.impl.CircleBullet;
import magengine.bullet.impl.DiamondBullet;
import magengine.bullet.impl.FllowerBullet;
import magengine.bullet.impl.HexagonBullet;
import magengine.bullet.impl.HiddenBullet;
import magengine.bullet.impl.LongHexagonBullet;
import magengine.bullet.impl.PetalBullet;
import magengine.bullet.impl.SquareBullet;
import magengine.bullet.impl.StarBullet;
import magengine.chapter.util.QuickDanmuku;
import magengine.chapter.util.SeqDanmuku;
import magengine.danmuku.ADanmuku;
import magengine.element.BaseElement;
import magengine.game.GameSession;
import magengine.game.LogicExecutor;
import magengine.helper.OvalHelper;
import magengine.launcher.ArcLauncherGroup;
import magengine.launcher.Launcher;
import magengine.launcher.OvalLauncherGroup;
import magengine.launcher.yt.LineLauncherGroup;
import magengine.util.ElementUtils;

public class PlumBlossomDanmuku extends ADanmuku {
	

	private QuickDanmuku quick = QuickDanmuku.getQuickDanmuku();
	public static final long DURATION = 35000;
	

	
	Stop[] stops  = {
			new Stop(0.56, Color.WHITE),
			new Stop(1.0,Color.rgb(0 ,191, 255,0.75)),
			new Stop(0.75, Color.rgb(135, 206, 250,0.2)),
		};
	
	@Override
	public void executeDanmuku() {
		
		BaseElement enemy = getSourceElement();
		
		//旋转
		callOLG1(enemy,1000,250,stops,9,9);
		callOLG1(enemy,1500,250,stops,9,9);
		callOLG1(enemy,1200,750,stops,5,15);
		callOLG1(enemy,2100,750,stops,7,12);
		
		
		//长六边形
		callOLG2(enemy,1200,750,stops,3,15);
		callOLG2(enemy,1990,750,stops,3,9);
		callOLG2(enemy,2800,750,stops,3,27);
		callOLG2(enemy,4200,750,stops,3,18);
		callOLG2(enemy,5950,750,stops,2,25);
		
		long endTime = 5700;
		callOLG(enemy,0-200,endTime);
		callOLG(enemy,420-200,endTime+1700);
		
		
		callOLG(enemy,0+endTime,endTime);
		callOLG(enemy,420+endTime,endTime+1700);
		callOLG1(enemy,420+endTime+320,210,stops,5,32);
		
		
		int count = 9;
		int speed=120;
		double midX =enemy.getX();
		double midY = enemy.getY();
		for(int i=0;i<count;i++){
			callOLG0(midX,midY,10000,81,1700,i*Math.PI/count,speed);
			callOLG0(midX,midY,10000,81,1700,-(count-i)*Math.PI/count,speed);
		
		}
	
		
		for(int i=0;i<count;i++){
			callOLG0(midX,midY,10000,1270,1700,i*Math.PI/count,speed);
			callOLG0(midX,midY,10000,1270,1700,-(count-i)*Math.PI/count,speed);
		
		}
	
		
		for(int i=0;i<count;i++){
			callOLG0(midX,midY,10000,450,4200,i*Math.PI/count,speed);
			callOLG0(midX,midY,10000,450,4200,-(count-i)*Math.PI/count,speed);
		
		}
	
		
		for(int i=0;i<count;i++){
			callOLG0(midX,midY,18000,1250,2100+i*200,Math.PI/2,speed);
		
		}
		
		for(int i=0;i<count;i++){
			callOLG0(midX,midY,20000,520,2100+i*250,Math.PI/2,speed);
		
		}
		
		for(int i=0;i<count;i++){
			callOLG0(midX-120,midY,20000,820,2100+i*250,Math.PI/2,speed);
			callOLG0(midX+120,midY,20000,820,2100+i*250,Math.PI/2,speed);
			
		
		}
		
	}
	

	
	public void callOLG0(double midX,double midY,long startTime,long delay,long endTime,double rovateAngle,int speed){
		OvalLauncherGroup olg = new OvalLauncherGroup(midX,midY, 5);
		olg.setLauncherConfig((l)->{
			l.setBulletType(CircleBullet.class);
			l.setBulletSpeed(200);
			l.setBulletConfig((b)->{
				((CircleBullet)b).setR(9);
				((CircleBullet)b).setColorSupplier(PresetColor.whiteOpacity.get());
			});
			
			l.setBulletEvent((sEX, bullet) -> {
				
				sEX.schedule(() -> {
					quick.stopBullet(bullet);
					
					
				}, delay, TimeUnit.MILLISECONDS);
				
				sEX.schedule(() -> {
					bullet.setVelocityX(speed);
					
					quick.VRotate(bullet, rovateAngle);
					
				},endTime+l.getStartTime()-LogicExecutor.gameTime(), TimeUnit.MILLISECONDS);
				
			});
			quick.bindToWantBeRemoved(l, getSourceElement());
		});
		
		olg.delayExecuteCheck(delay+startTime, getSourceElement());
	}
	
	
	
	

	
	public void callOLG(BaseElement target,long delay,long endTime){
		OvalLauncherGroup olg = new OvalLauncherGroup(target.getX(), target.getY(), 9);
		olg.setLauncherConfig((l)->{
			l.setBulletType(CircleBullet.class);
			l.setBulletSpeed(200);
			l.setBulletConfig((b)->{
				((CircleBullet)b).setR(27);
				((CircleBullet)b).setColorSupplier(PresetColor.whiteOpacity.get());
			});
			
			l.setBulletEvent((sEX, bullet) -> {
				
				sEX.schedule(() -> {
					quick.stopBullet(bullet);;
					quick.VRotate(bullet, Math.PI/3);
					
				}, delay, TimeUnit.MILLISECONDS);
				
				sEX.schedule(() -> {
					quick.runBullet(bullet);
					
				},endTime, TimeUnit.MILLISECONDS);
				
			});
			quick.bindToWantBeRemoved(l, getSourceElement());
		});
		olg.delayExecuteCheck(delay, getSourceElement());
	}
	
	
	public void callOLG1(BaseElement target,long delay,long time,Stop[] stops,int sizeR,int num){
		OvalLauncherGroup olg = new OvalLauncherGroup(target.getX(), target.getY(), num);
		olg.setLauncherConfig((l)->{
			l.setBulletType(CircleBullet.class);
			l.setBulletSpeed(200);
			l.setBulletConfig((b)->{
				((CircleBullet)b).setR(sizeR);
				((CircleBullet)b).setColorSupplier(PresetColor.getByStops(stops));
			});
			
			l.setBulletEvent((sEX, bullet) -> {
				
				sEX.schedule(() -> {

					quick.stopBullet(bullet);
					quick.VRotate(bullet, Math.PI/3);
					
				}, time, TimeUnit.MILLISECONDS);
				
				sEX.schedule(() -> {

					quick.runBullet(bullet);
					bullet.setVelocityX(-bullet.getVelocityX());
					bullet.setVelocityY(-bullet.getVelocityY());
					
				},  time+2100, TimeUnit.MILLISECONDS);
				
				
				sEX.schedule(() -> {

					quick.stopBullet(bullet);
			
					
				}, time+2700, TimeUnit.MILLISECONDS);
				
				sEX.schedule(() -> {

					quick.runBullet(bullet);
					bullet.setVelocityX(-bullet.getVelocityX());
					bullet.setVelocityY(-bullet.getVelocityY());
					
				},  time+4200, TimeUnit.MILLISECONDS);
				
				sEX.schedule(() -> {
					
					quick.VTransform(bullet, new double[][]{{0,1},{-1,0}
					
					});
				}, time+5200, TimeUnit.MILLISECONDS);
				
			});
			quick.bindToWantBeRemoved(l, getSourceElement());
		});
		olg.delayExecuteCheck(delay, getSourceElement());
	}
	
	
	
	
	public void callOLG2(BaseElement target,long delay,long time,Stop[] stops,int sizeR,int num){
		OvalLauncherGroup olg = new OvalLauncherGroup(target.getX(), target.getY(), num);
		olg.setLauncherConfig((l)->{
			l.setBulletType(LongHexagonBullet.class);
			l.setBulletSpeed(210);
			l.setBulletConfig((b)->{
				((LongHexagonBullet)b).setR(sizeR);
				((LongHexagonBullet)b).setNeedPaintOutline(true);
				((LongHexagonBullet)b).setOutlineColor(Color.BLUE);
			});
			
			l.setBulletEvent((sEX, bullet) -> {
				
				sEX.schedule(() -> {

					quick.stopBullet(bullet);
					
					
				}, time, TimeUnit.MILLISECONDS);
				
				
				sEX.schedule(() -> {

					quick.runBullet(bullet);
				
					
				}, time+20, TimeUnit.MILLISECONDS);
			});
			quick.bindToWantBeRemoved(l, getSourceElement());
		});
		olg.delayExecuteCheck(delay, getSourceElement());
	}
	
	
	
	

}
