package magengine.element.impl;

import magengine.bullet.impl.CircleBullet;
import magengine.element.PolygonCollision;
import magengine.util.CollisionTeam;
import magengine.util.Transform;

public class CircleArea extends Area {
	private double r = 1;
	private double[][] martix ;
	
	
	public CircleArea(double x, double y, double r) {
		super(x, y);
		setR(r);
	}
	public void setR(double r) {
		this.r = r;
		martix[0][0]=r;
		martix[1][1]=r;
	}
	/**
	 * 取得缩放后的半径
	 * @return
	 */
	public double getR() {
		return r*super.getScale();
	}
	/**
	 * 取得缩放后前的半径
	 * @return
	 */
	public double getRealR() {
		return r;
	}
	
	@Override
	public CollisionTeam getTeam() {
		return CollisionTeam.NO_TEAM;
	}



	public static final double[][] origin = new double[][] { { 0, -0.38268343236509, -0.70710678118655,
			-0.92387953251129, -1, -0.92387953251129, -0.70710678118655, -0.38268343236509, 0, 0.38268343236509,
			0.70710678118655, 0.92387953251129, 1, 0.92387953251129, 0.70710678118655, 0.38268343236509, 0

			},
			{ -1, -0.92387953251129, -0.70710678118655, -0.38268343236509, 0, 0.38268343236509, 0.70710678118655,
					0.92387953251129, 1, 0.92387953251129, 0.70710678118655, 0.38268343236509, 0, -0.38268343236509,
					-0.70710678118655, -0.92387953251129, -1

			} };

	@Override
	protected double[][] getOrigin() {
		if (martix == null) {
			martix = new double[][] { { 1, 0 }, { 0, 1 } };
		}
		double[][] ans = new double[2][origin[0].length];
		for (int i = 0; i < origin[0].length; i++) {
			double[] res = Transform.transform(martix, origin[0][i], origin[1][i]);
			ans[0][i] = res[0];
			ans[1][i] = res[1];
		}
		return ans;
	}
}
