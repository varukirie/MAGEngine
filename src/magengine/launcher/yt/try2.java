package magengine.launcher.yt;

import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import magengine.bullet.DefaultBullet;
import magengine.chapter.util.QuickDanmuku;
import magengine.launcher.ALauncherGroup;
import magengine.launcher.Launcher;

public class try2 extends ALauncherGroup {

	/**
	 * direction为负角表示 单位弧度
	 */
	private double direction = Math.PI / 2;
	/**
	 * 弧角度
	 */
	private double angle = Math.PI/3;
	/**
	 * 填充弹幕数
	 */
	
	private double midX = 200;
	private double midY = 200;
	private double r = 50;
	private Class<?> bulletType = DefaultBullet.class;
	private int launcherAmount = 6 ;
	
	/**
	 * 
	 * @param midX 中心x坐标
	 * @param midY 中心y坐标
	 * @param direction 方向
	 * @param angle 负角
	 * @param launcherAmount 发射器数量
	 * @param bulletType  子弹类型
	 */
	public try2(double midX, double midY, double direction, double angle, int launcherAmount, Class<?> bulletType) {
		super();
		this.midX = midX;
		this.midY = midY;
		this.direction = direction;
		this.angle = angle;
		this.launcherAmount = launcherAmount;
		this.setBulletType(bulletType);
	}
	

/**
 * 
 * @param midX 		坐标
 * @param midY		坐标
 * @param direction 方向 （负角
 * @param angle		扇形角度
 * @param launcherAmount 发射器数目
 */
	public try2(double midX, double midY,double direction, double angle, int launcherAmount) {
		super();
		this.direction = direction;
		this.angle = angle;
		this.launcherAmount = launcherAmount;
		this.midX = midX;
		this.midY = midY;
	}



	@Override
	/**
	 * 设置发射器数量(即发射组列数)
	 */
	public void execute() {
		  for(int i=0;i<launcherAmount;i++){
			  Launcher launcher = new Launcher(midX, midY, (direction - angle / 2) + i * (angle / (launcherAmount - 1)),interval, duration);
			
			configLauncher(launcher);
			mEU.add(UUID.randomUUID().toString(), launcher);
		
		}
			
	      
		
			
		
		
	}


	public double getDirection() {
		return direction;
	}


	public void setDirection(double direction) {
		this.direction = direction;
	}


	public double getAngle() {
		return angle;
	}


	public void setAngle(double angle) {
		this.angle = angle;
	}


	public int getlauncherAmount() {
		return launcherAmount;
	}


	public void setlauncherAmount(int launcherAmount) {
		this.launcherAmount = launcherAmount;
	}


	public double getMidX() {
		return midX;
	}


	public void setMidX(double midX) {
		this.midX = midX;
	}


	public double getMidY() {
		return midY;
	}


	public void setMidY(double midY) {
		this.midY = midY;
	}


	public Class<?> getBulletType() {
		return bulletType;
	}


	public void setBulletType(Class<?> bulletType) {
		this.bulletType = bulletType;
	}

	
}
