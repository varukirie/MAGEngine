package magengine.bullet.impl;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import magengine.bullet.APolygonBullet;

public class StarBullet extends APolygonBullet {

	public StarBullet(double x, double y, double vx, double vy, double ax, double ay) {
		super(x, y, vx, vy, ax, ay);
		// TODO Auto-generated constructor stub
	}

	public StarBullet(double x, double y, double vx, double vy, double[] ACoodinate) {
		super(x, y, vx, vy, ACoodinate);
		// TODO Auto-generated constructor stub
	}

	public StarBullet(double x, double y, double[] VCoodinate, double ax, double ay) {
		super(x, y, VCoodinate, ax, ay);
		// TODO Auto-generated constructor stub
	}

	public StarBullet(double x, double y, double[] VCoodinate) {
		super(x, y, VCoodinate);
		// TODO Auto-generated constructor stub
	}

	public StarBullet(double x, double y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	public StarBullet(double x, double y, double vx, double vy) {
		super(x, y, vx, vy);
		// TODO Auto-generated constructor stub
	}

	public static final double[][] origin = new double[][]{
		{0,-2,-9,-4,-6,0,6,4,9,3},{-9,-3,-3,1,8,4,8,1,-3,-3}};
	
	@Override
	protected double[][] getOrigin() {
		return origin;
	}
	
	@Override
	public void paint(GraphicsContext gc) {
		gc.setFill(Color.LIGHTGOLDENRODYELLOW);
		super.paint(gc);
	}

}
