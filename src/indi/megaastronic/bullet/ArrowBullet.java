package indi.megaastronic.bullet;

import com.badlogic.gdx.math.Polygon;

import indi.megaastronic.util.Transform;
import javafx.scene.canvas.GraphicsContext;

/**
 * 另一种子弹
 * @author Astronic
 *
 */
public class ArrowBullet extends PolygonBullet {

	public ArrowBullet(double x, double y) {
		super(x, y);
	}
	public ArrowBullet(double x,double y,double[] VCoodinate){
		super(x, y, VCoodinate[0], VCoodinate[1]);
	}
	public ArrowBullet(double x,double y,double vx, double vy){
		super(x, y,vx, vy);
	}
	
	public ArrowBullet(double x,double y,double vx, double vy,double[] ACoodinate) {
		super(x,y,vx, vy,ACoodinate[0], ACoodinate[1]);
	}
	
	public ArrowBullet(double x,double y,double[] VCoodinate,double ax,double ay) {
		super(x,y,VCoodinate[0], VCoodinate[1],ax,ay);
	}
	
	public ArrowBullet(double x,double y,double vx, double vy,double ax,double ay) {
		super(x,y,vx,vy,ax,ay);
	}
	
	private static final double[][] origin=new double[][]{
		{
			0,-9,0,9
		},
		{
			-13,12,5,12
		}
	};
	


	@Override
	protected double[][] getOrigin() {
		return origin;
	}

}
