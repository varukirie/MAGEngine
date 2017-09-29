package magengine.util;

import java.util.function.BiPredicate;
import com.badlogic.gdx.math.Polygon;

import magengine.element.PolygonCollision;

public class CollisionUtil {

	public static boolean PolygonDetect(PolygonCollision elem1,PolygonCollision elem2){
		if(shouldDetect.test(elem1, elem2)){
			return isOverlap(elem1.getPolygon(),elem2.getPolygon());
		}else{
			return false;
		}
	}
	private static boolean isOverlap(Polygon polygon1, Polygon polygon2){
        for(int i=0; i<polygon2.getVertices().length; i+=2){
            if( polygon1.contains(polygon2.getVertices()[i], polygon2.getVertices()[i+1]) ){
                return true;
            }
        }
        for(int i=0; i<polygon1.getVertices().length; i+=2){
            if( polygon2.contains(polygon1.getVertices()[i], polygon1.getVertices()[i+1]) ){
                return true;
            }
        }
        return false;
    }
	
	private static BiPredicate<PolygonCollision, PolygonCollision> shouldDetect=(elem1,elem2)->{
		return !elem1.getTeam().equals(elem2.getTeam());
	};
}
