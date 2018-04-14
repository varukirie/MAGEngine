package magengine.element.impl;

import java.util.function.Consumer;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import magengine.application.Main;
import magengine.bullet.APolygonBullet;
import magengine.element.PolygonCollision;
import magengine.util.Transform;

public abstract class Area extends APolygonBullet{

	public Area(double x, double y) {
		super(x, y);
	}

	private Consumer<PolygonCollision> onCollisionEvent = null;
	private Consumer<GraphicsContext> onPaintEvent = null;
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
	@Override
	protected double[][] transformVAndScaleAndDelta(double[][] origin){
		//do not transformV
		double[][] ans= Transform.martixInTransform(scaleMartix, origin);
		Transform.delta(ans, getX(), getY());
		return ans;
	}
	
	@Override
	public void paint(GraphicsContext gc) {
		double[][] ans=handleCollision();
		if(onPaintEvent!=null){
			onPaintEvent.accept(gc);
		}
		if(Main.DEBUG_COLLISION_AREA){
			gc.setFill(Color.rgb(230, 50, 50, 0.3));
			gc.fillPolygon(ans[0],ans[1], getOrigin()[0].length);
		}
	}

	public Consumer<GraphicsContext> getOnPaintEvent() {
		return onPaintEvent;
	}

	public void setOnPaintEvent(Consumer<GraphicsContext> onPaintEvent) {
		this.onPaintEvent = onPaintEvent;
	}
	
	
	
	
}
