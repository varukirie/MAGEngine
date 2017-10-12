package magengine.bullet;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * 画菱形子弹 继承多边形
 * 
 * 
 */
public class CircleBullet extends PolygonBullet {
	
		Color color = Color.LIGHTGOLDENRODYELLOW ;

	public CircleBullet(double x, double y, double vx, double vy, double ax, double ay) {
		super(x, y, vx, vy, ax, ay);
		// TODO Auto-generated constructor stub
	}

	public CircleBullet(double x, double y, double vx, double vy, double[] ACoodinate) {
		super(x, y, vx, vy, ACoodinate);
		// TODO Auto-generated constructor stub
	}

	public CircleBullet(double x, double y, double[] VCoodinate, double ax, double ay) {
		super(x, y, VCoodinate, ax, ay);
		// TODO Auto-generated constructor stub
	}

	public CircleBullet(double x, double y, double[] VCoodinate) {
		super(x, y, VCoodinate);
		// TODO Auto-generated constructor stub
	}

	public CircleBullet(double x, double y, double vx, double vy) {
		super(x, y, vx, vy);
		// TODO Auto-generated constructor stub
	}

	public CircleBullet(double x, double y) {
		super(x, y); // 中心坐标
		// TODO Auto-generated constructor stub
	}
	
	public Color getBulletColor() {
		return color;
	}
	
	public void setBulletColor(Color color){
		this.color = color;
	}

	
		public static final double[][] origin = new double[][] {
		{ 
			0,-0.38268343236509,-0.70710678118655,-0.92387953251129,
			-1,-0.92387953251129,-0.70710678118655,-0.38268343236509,
			0,0.38268343236509,0.70710678118655,0.92387953251129,
			1,0.92387953251129,0.70710678118655,0.38268343236509,0
			
		},
		{
			-1,-0.92387953251129,-0.70710678118655,-0.38268343236509,
			0,0.38268343236509,0.70710678118655,0.92387953251129,
			1,0.92387953251129,0.70710678118655,0.38268343236509,
			0,-0.38268343236509,-0.70710678118655,-0.92387953251129,-1
			
			
		}
	};


@Override
protected double[][] getOrigin() {
	return origin;
}
	public void paint(GraphicsContext gc) {
		gc.setFill(color);
		super.paint(gc);
	}

}
