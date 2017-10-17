package magengine.launcher.yt;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import magengine.chapter.util.QuickDanmuku;
import magengine.launcher.ALauncherGroup;
import magengine.launcher.Launcher;

public class RayLauncher extends ALauncherGroup {

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
	private int amount = 7;
	private double midX = 200;
	private double midY = 200;
	
	
	
	public RayLauncher(double midX, double midY) {
		super();
		this.midX = midX;
		this.midY = midY;
	}
	

/**
 * 
 * @param midX 		坐标
 * @param midY		坐标
 * @param direction 方向 （负角
 * @param angle		扇形角度
 * @param amount	发射器数量
 */
	public RayLauncher(double midX, double midY,double direction, double angle, int amount) {
		super();
		this.direction = direction;
		this.angle = angle;
		this.amount = amount;
		this.midX = midX;
		this.midY = midY;
	}



	public RayLauncher(double midX, double midY, int amount) {
		this.amount =amount;
		this.midX = midX;
		this.midY = midY;
	}


	@Override
	public void execute() {
		 QuickDanmuku quick =QuickDanmuku.getQuickDanmuku();
		
		for (int i = 1; i <=amount; i++) {
			if(i<=amount/2){
				Launcher launcher = new Launcher(midX+i*10, midY+i*(10/(Math.tan(Math.PI/6))), Math.PI/3,0, duration);
				launcher.setBulletSpeed(150.0);
				configLauncher(launcher);
				mEU.add(UUID.randomUUID().toString(), launcher);
			}
		
				
			else {
				Launcher launcher = new Launcher(midX+i*10, midY+300+(7-i)*(10/(Math.tan(Math.PI/6))),2*Math.PI/3,0, duration);
				quick.runBullet(launcher);
				configLauncher(launcher);
				launcher.setBulletSpeed(250.0);
				mEU.add(UUID.randomUUID().toString(), launcher);
				
			}
			
		
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


	public int getAmount() {
		return amount;
	}


	public void setAmount(int amount) {
		this.amount = amount;
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
	
}
