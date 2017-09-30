package magengine.launcher;

import java.util.UUID;

public class ArcLauncherGroup extends ALauncherGroup {

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
	private int amount = 10;
	private double midX = 200;
	private double midY = 200;

	
	public ArcLauncherGroup(double midX, double midY) {
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
	public ArcLauncherGroup(double midX, double midY,double direction, double angle, int amount) {
		super();
		this.direction = direction;
		this.angle = angle;
		this.amount = amount;
		this.midX = midX;
		this.midY = midY;
	}



	@Override
	public void execute() {
		for (int i = 0; i < amount; i++) {
			Launcher launcher = new Launcher(midX, midY, (direction - angle / 2) + i * (angle / (amount - 1)),interval, duration);
			configLauncher(launcher);
			mEU.add(UUID.randomUUID().toString(), launcher);
		}
	}
	
}
