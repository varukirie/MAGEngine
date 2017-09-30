package magengine.launcher;

import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

import application.Main;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.canvas.GraphicsContext;
import magengine.bullet.Bullet;
import magengine.bullet.DefaultBullet;
import magengine.element.BaseElement;
import magengine.element.DurationManage;
import magengine.element.Initializable;
import magengine.util.DI;
import magengine.util.ElementUtils;

public class Launcher extends BaseElement implements Initializable ,DurationManage {

	private BulletEvent bulletEvent;
	private DoubleProperty directionProperty =new SimpleDoubleProperty(0);
	private long interval = 400;
	private long startTime=0;
	private long lastLaunchTime = 0;
	private Class<?> bulletType = DefaultBullet.class;
	private double bulletSpeed = 0.4;
	private long duration=10000;
	private ModifyEvent modifyEvent=null;


	public Launcher(double x, double y) {
		super(x, y);
	}
/**
 * 
 * @param x
 * @param y
 * @param direction	发射方向
 * @param interval	发射间隔
 * @param duration	存活时间
 */
	public Launcher(double x, double y, double direction, long interval,long duration) {
		super(x, y);
		this.directionProperty.set(direction);
		this.interval = interval;
		this.duration=duration;
	}
	

	@Override
	public void paint(GraphicsContext gc) {
		if(Main.DEBUG) gc.fillText("launcher", getX(), getY());
	}

	public void launch() {
		try {
			((ElementUtils) (DI.di().get("mEU"))).addEventBullet(UUID.randomUUID().toString(), this.bulletEvent,
					(Bullet) bulletType.getConstructor(double.class, double.class, double.class, double.class)
							.newInstance(getX(), getY(), Math.cos(getDirection()) * bulletSpeed,
									Math.sin(getDirection()) * bulletSpeed));
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			System.out.println("错误的子弹类型");
			e.printStackTrace();
		}
	}

	@Override
	public void modify() {
		super.modify();
		if(modifyEvent!=null) modifyEvent.modify();
	}
	public void setBulletEvent(BulletEvent bulletEvent) {
		this.bulletEvent = bulletEvent;
	}

	@Override
	public void initWhenAdd() {
		this.lastLaunchTime = System.currentTimeMillis();
	}

	public long getLastLaunch() {
		return lastLaunchTime;
	}

	public void setLastLaunch(long lastLaunch) {
		this.lastLaunchTime = lastLaunch;
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

	public ModifyEvent getModifyEvent() {
		return modifyEvent;
	}

	public void setModifyEvent(ModifyEvent modifyEvent) {
		this.modifyEvent = modifyEvent;
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

}