package magengine.bullet.impl;

import java.util.function.Function;
import java.util.function.Supplier;

import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.Bloom;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.Paint;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import magengine.bullet.APolygonBullet;
import magengine.bullet.RadiusSupplier;
import magengine.element.InitBeforeLoadChapter;
import magengine.paint.MyCanvasSwitcher;
import magengine.util.DI;
import magengine.util.Transform;

public class CircleBullet extends APolygonBullet implements RadiusSupplier,InitBeforeLoadChapter{
	
	private Function<RadiusSupplier,Paint> colorSupplier = (cirb)->{
		return Color.WHITESMOKE;
	};
	private double r = 1;
	private double[][] martix ;
	
	public void setR(double r) {
		this.r = r;
		martix[0][0]=r;
		martix[1][1]=r;
	}
	/**
	 * 取得缩放后的半径
	 * @return
	 */
	@Override
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
	
	public CircleBullet(double x, double y, double vx, double vy, double ax, double ay) {
		super(x, y, vx, vy, ax, ay);
	}

	public CircleBullet(double x, double y, double vx, double vy, double[] ACoodinate) {
		super(x, y, vx, vy, ACoodinate);
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
	}
	
	public CircleBullet(){
		super(0, 0);
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
		if(martix==null){
			martix=new double[][]{{1,0},{0,1}};
		}
		double[][] ans = new double[2][origin[0].length];
		for(int i=0;i<origin[0].length;i++){
			double[] res=Transform.transform(martix,origin[0][i],origin[1][i]);
			ans[0][i]=res[0];
			ans[1][i]=res[1];
		}
		return ans;
	}

	public void paint(GraphicsContext gc) {
		super.handleCollision();
		gc.setFill(colorSupplier.apply(this));
		gc.fillOval(getX()-getR(), getY()-getR(), getR()*2, getR()*2);
		if(super.getNeedPaintOutline()){
			gc.setStroke(super.getOutlineColor());
			gc.strokeOval(getX()-getR(), getY()-getR(), getR()*2, getR()*2);
		}
			
	}

	public void setColorSupplier(Function<RadiusSupplier, Paint> colorSupplier) {
		this.colorSupplier = colorSupplier;
	}
	
	public Function<RadiusSupplier, Paint> getColorSupplier() {
		return colorSupplier;
	}
	@Override
	public void initWhenChapterLoad() {
		Platform.runLater(()->{
			((MyCanvasSwitcher) DI.di().get("switcher")).configCanvas(CircleBullet.class, (canvas) -> {
				canvas.setEffect(new Bloom());
			});
		});		
	}
}
