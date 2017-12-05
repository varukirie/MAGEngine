package magengine.bullet.impl;

import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.Bloom;
import javafx.scene.paint.Color;
import magengine.bullet.APolygonBullet;
import magengine.element.InitBeforeLoadChapter;
import magengine.paint.MyCanvasSwitcher;
import magengine.util.DI;

/**
 * 画菱形子弹
 * 继承多边形
 * 
 * 
 */
public class DiamondBullet extends APolygonBullet implements InitBeforeLoadChapter{
	
	public DiamondBullet(){
		super(0, 0);
	}
	
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

	@Override
	public void initWhenChapterLoad() {
		Platform.runLater(()->{
			((MyCanvasSwitcher) DI.di().get("switcher")).configCanvas(DiamondBullet.class, (canvas) -> {
				canvas.setEffect(new Bloom());
			});
		});
	}

}
