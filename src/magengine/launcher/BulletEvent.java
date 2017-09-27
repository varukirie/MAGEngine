package magengine.launcher;

import java.util.concurrent.ScheduledExecutorService;

import magengine.bullet.Bullet;
@FunctionalInterface
public interface BulletEvent {

	public void event(ScheduledExecutorService ses,Bullet bullet);
}
