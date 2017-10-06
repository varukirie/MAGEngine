package magengine.util;

import java.util.Arrays;

import com.badlogic.gdx.math.Polygon;

import magengine.element.PolygonCollision;

public class CollisionUtil {

	public static boolean PolygonDetect(PolygonCollision elem1,PolygonCollision elem2){
		if(CollisionTeam.shouldCollision(elem1.getTeam(), elem2.getTeam())){
//			System.out.println("shouldCollsion"+elem1.getTeam()+" | "+elem2.getTeam());
			return isOverlap(elem1.getPolygon(),elem2.getPolygon());
		}else{
			return false;
		}
	}
	private static boolean isOverlap(Polygon polygon1, Polygon polygon2){
//		System.out.println(Arrays.toString(polygon1.getVertices()));
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
	
	public static void toVertices(double[][] in,float[] target){
		for(int i=0;i<in[0].length;i++){
			target[i*2]=(float) in[0][i];
			target[i*2+1]=(float) in[1][i];
		}
	}

}
