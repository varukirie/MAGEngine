package magengine.launcher.yt;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import magengine.bullet.impl.CircleBullet;
import magengine.bullet.impl.DefaultBullet;
import magengine.chapter.util.QuickDanmuku;
import magengine.launcher.ALauncherGroup;
import magengine.launcher.ArcLauncherGroup;

public class Arc3LauncherGroup extends ALauncherGroup {
/**
 * 发射组行数
 */
	private int columnAmount = 3;
	/**
	 * direction为负角表示 单位弧度
	 */
	/**
	 * 发射组列数
	 */
	private int lineAmount =5;
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
	private Class<?> bulletType = DefaultBullet.class;

	
	
	public Arc3LauncherGroup(double midX, double midY) {
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
 * @param columnAmount 发射组行数
 * @param lineAmount 发射组列数
 */
	public Arc3LauncherGroup(double midX, double midY,double direction, double angle, int lineAmount,int columnAmount,Class<?> bulletType ) {
		super();
		this.direction = direction;
		this.angle = angle;
		this.lineAmount = lineAmount;
		this.midX = midX;
		this.midY = midY;
		this.columnAmount = columnAmount;
		this.bulletType = bulletType;
	}


/**
 * 
 * @param midX
 * @param midY 
 * @param direction  方向
 * @param lineAmount 发射组列数
 * @param columnAmount 发射组行数
 */
	public Arc3LauncherGroup(double midX, double midY,double direction, int lineAmount, int columnAmount) {
		
		super();
		this.direction = direction;
		this.lineAmount =  lineAmount;
		this.midX = midX;
		this.midY = midY;
		this.columnAmount = columnAmount;
		
}
/**
 * 
 * @param midX
 * @param midY
 * @param direction 方向
 */

	public Arc3LauncherGroup(double midX, double midY,double direction) {
		super();
		this.direction = direction;
		this.midX = midX;
		this.midY = midY;
	}



/**
 * 
 * @param midX
 * @param midY 
 * @param direction 方向
 * @param angle 负角
 * @param lineAmount 发射组列数
 */

	

	public Arc3LauncherGroup(double midX, double midY,double direction, double angle, int lineAmount) {
		super();
		this.direction = direction;
		this.angle = angle;
		this.lineAmount = lineAmount;
		this.midX = midX;
		this.midY = midY;
		
	}

/**
 * 
 * @param midX 中心X坐标
 * @param midY  中心Y坐标
 * @param direction 方向
 * @param angle  负角
 * @param lineAmount 发射组列数
 * @param columnAmount 发涉组行数
 */
	public Arc3LauncherGroup(double midX, double midY,double direction, double angle, int lineAmount,int columnAmount) {
		super();
		this.direction = direction;
		this.angle = angle;
		this.lineAmount = lineAmount;
		this.midX = midX;
		this.midY = midY;
		this.columnAmount = columnAmount;
		
	}


	public Arc3LauncherGroup(double midX, double midY, Class<?> bulletType) {
		super();
		this.midX = midX;
		this.midY = midY;
		this.bulletType = bulletType;
	
}


	@Override
	public void execute() {
		 QuickDanmuku quick =QuickDanmuku.getQuickDanmuku();
		for (int i = 0; i < columnAmount; i++) {

			ArcLauncherGroup l = new ArcLauncherGroup(midX,midY, direction, angle, lineAmount);
		 
			l.setLauncherConfig((launcher) -> {
			
				launcher.setBulletType(bulletType);
				
			launcher.setBulletEvent((sESx, bullet) -> {
					sESx.schedule(() -> {
						quick.runBullet(bullet);
						}, 10000, TimeUnit.MILLISECONDS);
			});
		});
			l.delayExecute(500+i*500);
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


	public int getcolumnAmount() {
		return columnAmount;
	}


	public void setAmount(int columnAmount) {
		this.columnAmount = columnAmount;
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
