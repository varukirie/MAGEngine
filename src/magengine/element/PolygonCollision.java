package magengine.element;

import com.badlogic.gdx.math.Polygon;

import magengine.bullet.CollisionTeam;

public interface PolygonCollision {
	Polygon getPolygon();
	CollisionTeam getTeam();

}
