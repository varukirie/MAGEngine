package indi.megaastronic.bullet;

import javafx.scene.canvas.GraphicsContext;

public class HiddenBullet extends Bullet {
	
	public HiddenBullet(double x, double y, double vx, double vy, double ax, double ay) {
		super(x, y, vx, vy, ax, ay);
	}

	public HiddenBullet(double x, double y, double vx, double vy, double[] ACoodinate) {
		super(x, y, vx, vy, ACoodinate);
	}

	public HiddenBullet(double x, double y, double vx, double vy) {
		super(x, y, vx, vy);
	}

	public HiddenBullet(double x, double y, double[] VCoodinate, double ax, double ay) {
		super(x, y, VCoodinate, ax, ay);
	}

	public HiddenBullet(double x, double y, double[] VCoodinate) {
		super(x, y, VCoodinate);
	}

	public HiddenBullet(double x, double y) {
		super(x, y);
	}

	@Override
	public void paint(GraphicsContext gc) {
	}

}
