package magengine.launcher;

import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

import magengine.chapter.util.QuickDanmuku;
import magengine.element.BaseElement;
import magengine.game.LogicExecutor;
import magengine.util.DI;
import magengine.util.ElementUtils;

public class BurstLauncher extends Launcher {

	private int burstAmount = 7;
	private double burstRange = 200;
	private long burstDuration = 500;

	public BurstLauncher(double x, double y) {
		super(x, y);
	}

	public BurstLauncher(double x, double y, double direction, long interval, long duration) {
		super(x, y, direction, interval, duration);
	}

	
	@Override
	public void launch() {
		double eachRange = burstRange / (burstAmount - 1);
		for (int i = 0; i < burstAmount; i++) {
			double burstBulletSpeed = 1.0*i * (eachRange / (burstDuration / 1000.0))+1;
			try {
				BaseElement elem = (BaseElement) bulletType
						.getConstructor(double.class, double.class, double.class, double.class).newInstance(getX(),
								getY(), Math.cos(getDirection()) * burstBulletSpeed,
								Math.sin(getDirection()) * burstBulletSpeed);
				if (bulletConfig != null)
					bulletConfig.accept(elem);
				((ElementUtils) (DI.di().get("mEU"))).addEventBullet(UUID.randomUUID().toString(),  this.getBulletEvent(),
						elem);
				LogicExecutor.getLogicExecutor().schedule(() -> {
					QuickDanmuku.getQuickDanmuku().setSpeed(elem, bulletSpeed);
				}, burstDuration);
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException e) {
				System.out.println("错误的子弹类型");
				e.printStackTrace();
			}
		}
	}

	public int getBurstAmount() {
		return burstAmount;
	}

	public void setBurstAmount(int burstAmount) {
		this.burstAmount = burstAmount;
	}

	public double getBurstRange() {
		return burstRange;
	}

	public void setBurstRange(double burstRange) {
		this.burstRange = burstRange;
	}

	public long getBurstDuration() {
		return burstDuration;
	}

	public void setBurstDuration(long burstDuration) {
		this.burstDuration = burstDuration;
	}

	
}
