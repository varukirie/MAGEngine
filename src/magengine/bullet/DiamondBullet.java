package magengine.bullet;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * 画菱形子弹
 * 继承多边形
 * 
 * 
 */
public class DiamondBullet extends PolygonBullet {
	
	public DiamondBullet(double x, double y, double vx, double vy, double ax, double ay) {
		super(x, y, vx, vy, ax, ay);
		// TODO Auto-generated constructor stub
	}

	public DiamondBullet(double x, double y, double vx, double vy, double[] ACoodinate) {
		super(x, y, vx, vy, ACoodinate);
		// TODO Auto-generated constructor stub
	}

	public DiamondBullet(double x, double y, double[] VCoodinate, double ax, double ay) {
		super(x, y, VCoodinate, ax, ay);
		// TODO Auto-generated constructor stub
	}

	public DiamondBullet(double x, double y, double[] VCoodinate) {
		super(x, y, VCoodinate);
		// TODO Auto-generated constructor stub
	}


	public DiamondBullet(double x, double y, double vx, double vy) {
		super(x, y, vx, vy);
		// TODO Auto-generated constructor stub
	}

	
	
	

	public DiamondBullet(double x, double y) {
		super(x, y); //中心坐标
		// TODO Auto-generated constructor stub
	}


	public static final double[][] origin=new double[][]{
		{
			0,-5,0,5,0,
		},
		{
			-12,0,12,0,-12
		}
	};
	
	
	@Override
	protected double[][] getOrigin() {
		// TODO Auto-generated method stub
		return origin ;
	}
	//
	public void paint(GraphicsContext gc) {
	   gc.setFill(Color.LIGHTGOLDENRODYELLOW);
		super.paint(gc);
	}

}
