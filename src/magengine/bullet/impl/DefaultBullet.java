package magengine.bullet.impl;

import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.Bloom;
import javafx.scene.paint.Color;
import magengine.bullet.APolygonBullet;
import magengine.element.InitBeforeLoadChapter;
import magengine.paint.MyCanvasSwitcher;
import magengine.util.DI;

public class DefaultBullet extends APolygonBullet implements InitBeforeLoadChapter {

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
	
	public DefaultBullet(){
		super(0, 0);
	}

	static final double[][] origin = new double[][] { { 0, 0 - 3, 0 - 6, 0, 0 + 6, 0 + 3 },
			{ 0 + 11, 0, 0 + 2, 0 - 10, 0 + 2, 0 } };

	@Override
	protected double[][] getOrigin() {
		return origin;
	}

	@Override
	public void paint(GraphicsContext gc) {
		gc.setFill(Color.WHITESMOKE);
		super.paint(gc);
	}

	@Override
	public void initWhenChapterLoad() {
		Platform.runLater(()->{
			((MyCanvasSwitcher) DI.di().get("switcher")).configCanvas(DefaultBullet.class, (canvas) -> {
				canvas.setEffect(new Bloom());
			});
		});
	}
	
}
