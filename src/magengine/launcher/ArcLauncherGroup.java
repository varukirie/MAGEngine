package magengine.launcher;

import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

public class ArcLauncherGroup extends ALauncherGroup {

	/**
	 * direction为负角表示 单位弧度
	 */
	private double direction = Math.PI / 2;
	/**
	 * 弧角度
	 */
	private double angle = Math.PI / 3;
	/**
	 * 填充弹幕数
	 */
	private int amount = 10;
	private double midX = 200;
	private double midY = 200;
	private Class<? extends Launcher> launcherType = Launcher.class;

	public ArcLauncherGroup(double midX, double midY) {
		super();
		this.midX = midX;
		this.midY = midY;
	}

	/**
	 * 
	 * @param midX
	 *            坐标
	 * @param midY
	 *            坐标
	 * @param direction
	 *            方向 （负角
	 * @param angle
	 *            扇形角度
	 * @param amount
	 *            发射器数量
	 */
	public ArcLauncherGroup(double midX, double midY, double direction, double angle, int amount) {
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
			Launcher launcher;
			try {
				launcher = launcherType.getConstructor(double.class, double.class, double.class, long.class, long.class)
						.newInstance(midX, midY, (direction - angle / 2) + i * (angle / (amount - 1)), interval,
								duration);
				configLauncher(launcher);
				mEU.add(UUID.randomUUID().toString(), launcher);
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
				System.out.println("ArcLauncherGroup:错误的launcherType");
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

	public void setLauncherType(Class<? extends Launcher> launcherType) {
		this.launcherType = launcherType;
	}
	public Class<? extends Launcher> getLauncherType() {
		return launcherType;
	}
}
