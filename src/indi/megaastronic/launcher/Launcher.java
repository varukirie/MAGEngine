package indi.megaastronic.launcher;

import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

import application.Main;
import indi.megaastronic.bullet.Bullet;
import indi.megaastronic.bullet.DefaultBullet;
import indi.megaastronic.element.BaseElement;
import indi.megaastronic.element.Initializable;
import indi.megaastronic.util.DI;
import indi.megaastronic.util.ElementUtils;
import javafx.scene.canvas.GraphicsContext;

public class Launcher extends BaseElement implements Initializable {

	private BulletEvent bulletEvent;
	private double direction = 0;
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

	public Launcher(double x, double y, double direction, long interval,long duration) {
		super(x, y);
		this.direction = direction;
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
							.newInstance(getX(), getY(), Math.cos(direction) * bulletSpeed,
									Math.sin(direction) * bulletSpeed));
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
		this.startTime=System.currentTimeMillis();
	}

	public long getLastLaunch() {
		return lastLaunchTime;
	}

	public void setLastLaunch(long lastLaunch) {
		this.lastLaunchTime = lastLaunch;
	}

	public double getDirection() {
		return direction;
	}

	public void setDirection(double direction) {
		this.direction = direction;
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
	
	
}
