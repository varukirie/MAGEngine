package magengine.launcher;

import java.util.concurrent.ScheduledExecutorService;

import magengine.bullet.Bullet;
import magengine.element.BaseElement;
@FunctionalInterface
public interface BulletEvent {

	public void event(ScheduledExecutorService ses,BaseElement bullet);
}
