package magengine.element;

import com.badlogic.gdx.math.Polygon;

import magengine.util.CollisionTeam;

public interface PolygonCollision {
	Polygon getPolygon();
	CollisionTeam getTeam();
	default void onCollision(){
		
	}
}
