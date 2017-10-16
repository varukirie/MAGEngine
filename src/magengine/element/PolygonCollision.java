package magengine.element;

import com.badlogic.gdx.math.Polygon;

import magengine.util.CollisionTeam;

public interface PolygonCollision {
	Polygon getPolygon();
	CollisionTeam getTeam();
	/**
	 * 被碰撞时会调用该方法，m是碰撞的另一方
	 * 该方法中的m只做确认对方类型的作用
	 * 不建议干预对方
	 * 一次碰撞双方的onCollision都会被调用
	 * @param m
	 */
	default void onCollision(PolygonCollision m){
		
	}
}
