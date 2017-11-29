package magengine.bullet.impl;

import java.util.function.Function;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import magengine.bullet.APolygonBullet;
import magengine.bullet.RadiusSupplier;
import magengine.util.Transform;

/**
 * 画六边形子弹
 * 继承多边形
 * 增加颜色绘制
 * 
 */
public class HexagonBullet extends APolygonBullet implements RadiusSupplier{
	
	
	private double r = 10;

	private double[][] martix ;
	public void setR(double r) {
		this.r = r;
		martix[0][0]=r;
		martix[1][1]=r;
	}
	
	public double getR() {
		return r;
	}
	
	
	public HexagonBullet(double x, double y, double vx, double vy, double ax, double ay) {
		super(x, y, vx, vy, ax, ay);
		// TODO Auto-generated constructor stub
	}

	public HexagonBullet(double x, double y, double vx, double vy, double[] ACoodinate) {
		super(x, y, vx, vy, ACoodinate);
		// TODO Auto-generated constructor stub
	}

	public HexagonBullet(double x, double y, double[] VCoodinate, double ax, double ay) {
		super(x, y, VCoodinate, ax, ay);
		// TODO Auto-generated constructor stub
	}

	public HexagonBullet(double x, double y, double[] VCoodinate) {
		super(x, y, VCoodinate);
		// TODO Auto-generated constructor stub
	}


	public HexagonBullet(double x, double y, double vx, double vy) {
		super(x, y, vx, vy);
		// TODO Auto-generated constructor stub
	}

	
	
	

	public HexagonBullet(double x, double y) {
		super(x, y); //中心坐标
		// TODO Auto-generated constructor stub
	}


	public static final double[][] origin=new double[][]{
		{
			-1,-1,0, 1, 1, 0,  -1
		},
		{
			-1, 1,2, 1, -1, -2,-1
		}
	};
	
	
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
		gc.setFill(colorSupplier.apply(this));
	//   gc.setFill(Color.LIGHTGOLDENRODYELLOW);
		super.paint(gc);
	}
	private Function<RadiusSupplier,Paint> colorSupplier = (cirb)->{
		return Color.WHITESMOKE;
	};

	public void setColorSupplier(Function<RadiusSupplier, Paint> colorSupplier) {
		this.colorSupplier = colorSupplier;
	}
	
	public Function<RadiusSupplier, Paint> getColorSupplier() {
		return colorSupplier;
	}
}
