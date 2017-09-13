package indi.megaastronic.launcher;

import java.util.concurrent.ScheduledExecutorService;

import indi.megaastronic.bullet.Bullet;
@FunctionalInterface
public interface BulletEvent {

	public void event(ScheduledExecutorService ses,Bullet bullet);
}
