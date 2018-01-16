package magengine.launcher;

import magengine.element.BaseElement;
import magengine.game.LogicExecutor;
@FunctionalInterface
public interface BulletEvent {

	public void event(LogicExecutor ses,BaseElement bullet);
}
