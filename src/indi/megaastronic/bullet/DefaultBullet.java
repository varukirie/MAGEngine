package indi.megaastronic.bullet;

import com.badlogic.gdx.math.Polygon;

import indi.megaastronic.util.Transform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.paint.Color;

public class DefaultBullet extends PolygonBullet {

	public DefaultBullet(double x, double y, double vx, double vy, double ax, double ay) {
		super(x, y, vx, vy, ax, ay);
		// TODO Auto-generated constructor stub
	}

	public DefaultBullet(double x, double y, double vx, double vy, double[] ACoodinate) {
		super(x, y, vx, vy, ACoodinate);
		// TODO Auto-generated constructor stub
	}

	public DefaultBullet(double x, double y, double vx, double vy) {
		super(x, y, vx, vy);
		// TODO Auto-generated constructor stub
	}

	public DefaultBullet(double x, double y, double[] VCoodinate, double ax, double ay) {
		super(x, y, VCoodinate, ax, ay);
		// TODO Auto-generated constructor stub
	}

	public DefaultBullet(double x, double y, double[] VCoodinate) {
		super(x, y, VCoodinate);
		// TODO Auto-generated constructor stub
	}

	public DefaultBullet(double x, double y) {
		super(x, y);
	}



	static final double[][] origin=new double[][]{
		{
			0,0-3,0-6,0,0+6,0+3
		},
		{
			0+11,0,0+2,0-10,0+2,0
		}
	};


	@Override
	protected double[][] getOrigin() {
		return origin;
	}
	@Override
	public void paint(GraphicsContext gc) {
//		gc.setFill(Color.WHITE);
//		gc.setEffect(new Bloom(0.999));
		super.paint(gc);
//		gc.setEffect(null);
	}




}
