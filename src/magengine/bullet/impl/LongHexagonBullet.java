package magengine.bullet.impl;

import java.util.function.Function;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import magengine.bullet.APolygonBullet;
import magengine.util.Transform;

public class LongHexagonBullet extends APolygonBullet {
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
	
	private Function<LongHexagonBullet,Paint> colorSupplier = (cirb)->{
		return Color.WHITESMOKE;
	};
	
	public LongHexagonBullet(double x, double y, double vx, double vy, double ax, double ay) {
		super(x, y, vx, vy, ax, ay);
		// TODO Auto-generated constructor stub
	}

	public LongHexagonBullet(double x, double y, double vx, double vy, double[] ACoodinate) {
		super(x, y, vx, vy, ACoodinate);
		// TODO Auto-generated constructor stub
	}

	public LongHexagonBullet(double x, double y, double[] VCoodinate, double ax, double ay) {
		super(x, y, VCoodinate, ax, ay);
		// TODO Auto-generated constructor stub
	}

	public LongHexagonBullet(double x, double y, double[] VCoodinate) {
		super(x, y, VCoodinate);
		// TODO Auto-generated constructor stub
	}

	public LongHexagonBullet(double x, double y, double vx, double vy) {
		super(x, y, vx, vy);
		// TODO Auto-generated constructor stub
	}

	public LongHexagonBullet(double x, double y) {
		super(x, y); // 中心坐标
		// TODO Auto-generated constructor stub
	}

	
	
	
	public static final double[][] origin = new double[][] {
		{ 
			-1,-1,0,1,1,0,-1
		},
			
		{ 

			-5,5,21,5,-5,-21,-5
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
			
			gc.setFill(Color.WHITE);
			super.paint(gc);
		}

  

}
