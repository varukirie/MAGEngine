package magengine.launcher;

import java.lang.reflect.InvocationTargetException;
import java.util.UUID;
import java.util.function.Consumer;

import application.Main;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import magengine.bullet.Bullet;
import magengine.bullet.impl.DefaultBullet;
import magengine.element.BaseElement;
import magengine.element.DurationManage;
import magengine.element.Initializable;
import magengine.game.LogicExecutor;
import magengine.util.DI;
import magengine.util.ElementUtils;

public class Launcher extends BaseElement implements Initializable, DurationManage {

	protected BulletEvent bulletEvent;
	protected DoubleProperty directionProperty = new SimpleDoubleProperty(0);
	protected long interval = 400;
	protected long startTime = 0;
	protected long lastLaunchTime = 0;
	protected Class<?> bulletType = DefaultBullet.class;
	protected double bulletSpeed = 80;
	protected long duration = 10000;
	protected Consumer<BaseElement> bulletConfig = null;

	public Launcher(double x, double y) {
		super(x, y);
	}

	public Launcher(double x, double y, double velocityX, double velocityY, double ax, double ay) {
		super(x, y, velocityX, velocityY, ax, ay);
	}

	public Launcher(double x, double y, double velocityX, double velocityY) {
		super(x, y, velocityX, velocityY);
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @param direction
	 *            发射方向
	 * @param interval
	 *            发射间隔
	 * @param DURATION
	 *            存活时间
	 */
	public Launcher(double x, double y, double direction, long interval, long duration) {
		super(x, y);
		this.directionProperty.set(direction);
		this.interval = interval;
		this.duration = duration;
	}

	@Override
	public void paint(GraphicsContext gc) {
		if (Main.DEBUG)
			gc.setFill(Color.WHITESMOKE);
			gc.fillText("launcher", getX(), getY());
	}

	public void launch() {
		LogicExecutor.getLogicExecutor().schedule(()->{
			try {
				BaseElement elem = (BaseElement) bulletType
						.getConstructor(double.class, double.class, double.class, double.class).newInstance(getX(), getY(),
								Math.cos(getDirection()) * bulletSpeed, Math.sin(getDirection()) * bulletSpeed);
				if(bulletConfig!=null)
					bulletConfig.accept(elem);
				((ElementUtils) (DI.di().get("mEU"))).addEventBullet(UUID.randomUUID().toString(), this.bulletEvent, elem);
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
					| NoSuchMethodException | SecurityException e) {
				System.out.println("错误的子弹类型");
				e.printStackTrace();
			}
		}, 0);
	}


	public void setBulletEvent(BulletEvent bulletEvent) {
		this.bulletEvent = bulletEvent;
	}

	@Override
	public void initWhenAdd() {
		this.lastLaunchTime = LogicExecutor.gameTime();
	}

	public double getDirection() {
		return directionProperty.get();
	}

	public void setDirection(double direction) {
		this.directionProperty.set(direction);
	}

	public long getInterval() {
		return interval;
	}

	public void setInterval(long interval) {
		this.interval = interval;
	}

	public Class<?> getBulletType() {
		return bulletType;
	}

	public void setBulletType(Class<?> bulletType) {
		this.bulletType = bulletType;
	}

	public double getBulletSpeed() {
		return bulletSpeed;
	}

	public void setBulletSpeed(double bulletSpeed) {
		this.bulletSpeed = bulletSpeed;
	}

	public BulletEvent getBulletEvent() {
		return bulletEvent;
	}

	public long getStartTime() {
		return startTime;
	}

	public long getLastLaunchTime() {
		return lastLaunchTime;
	}

	public void setLastLaunchTime(long lastLaunchTime) {
		this.lastLaunchTime = lastLaunchTime;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}


	public DoubleProperty getDirectionProperty() {
		return directionProperty;
	}

	public void setDirectionProperty(DoubleProperty directionProperty) {
		this.directionProperty = directionProperty;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public Consumer<BaseElement> getBulletConfig() {
		return bulletConfig;
	}

	public void setBulletConfig(Consumer<BaseElement> bulletConfig) {
		this.bulletConfig = bulletConfig;
	}

}
