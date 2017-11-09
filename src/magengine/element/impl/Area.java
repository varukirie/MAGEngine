package magengine.element.impl;

import java.util.function.Consumer;

import magengine.bullet.APolygonBullet;
import magengine.element.PolygonCollision;

public abstract class Area extends APolygonBullet{

	public Area(double x, double y) {
		super(x, y);
	}

	private Consumer<PolygonCollision> onCollisionEvent = null;
	
	public Consumer<PolygonCollision> getOnCollisionEvent() {
		return onCollisionEvent;
	}

	public void setOnCollisionEvent(Consumer<PolygonCollision> onCollisionEvent) {
		this.onCollisionEvent = onCollisionEvent;
	}

	@Override
	public void onCollision(PolygonCollision m) {
		if(onCollisionEvent!=null){
			onCollisionEvent.accept(m);
		}
	}
	
	
}
