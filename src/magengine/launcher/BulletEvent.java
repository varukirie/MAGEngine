package magengine.launcher;

import java.util.concurrent.ScheduledExecutorService;

import magengine.bullet.Bullet;
import magengine.element.BaseElement;
import magengine.game.LogicExecutor;
@FunctionalInterface
public interface BulletEvent {

	public void event(LogicExecutor ses,BaseElement bullet);
}
