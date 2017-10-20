package magengine.bullet;

import java.util.function.Function;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.Paint;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import magengine.util.Transform;

/**
 * 画菱形子弹
 * 继承多边形
 * 
 * 
 */
public class HexagonBullet extends APolygonBullet {
	
	
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
	
	public static enum PresetColor{
		redOpacity((cirb)->{
			return new RadialGradient(0,
		            .1,
		            cirb.getX(),
		            cirb.getY(),
		            cirb.getR(),
		            false,
		            CycleMethod.NO_CYCLE,
		            new Stop(0, Color.rgb(255, 99, 71,0.4)),
		            new Stop(1, Color.rgb(245, 245, 245, 0.9)));
		}),
		blueOpacity((cirb)->{
			return new RadialGradient(0,
		            .1,
		            cirb.getX(),
		            cirb.getY(),
		            cirb.getR(),
		            false,
		            CycleMethod.NO_CYCLE,
		            new Stop(0, Color.rgb(67, 110, 238,0.4)),
		            new Stop(1, Color.rgb(245, 245, 245, 0.7)));
		}),
		blueOpacityWhite((cirb)->{
			return new RadialGradient(0,
		            .1,
		            cirb.getX(),
		            cirb.getY(),
		            cirb.getR(),
		            false,
		            CycleMethod.NO_CYCLE,
		            new Stop(0, Color.BLUE),
		            new Stop(0.5, Color.rgb(245, 245, 245, 0.0)),
		            new Stop(1, Color.WHITESMOKE));
		}),
	   pinkOpacity((cirb)->{
			return new RadialGradient(0,
		            .1,
		            cirb.getX(),
		            cirb.getY(),
		            cirb.getR(),
		            false,
		            CycleMethod.NO_CYCLE,
		            new Stop(0, Color.PINK),
		            new Stop(0, Color.rgb(241, 158, 194,0.0)),
		            new Stop(1, Color.rgb(242, 156, 177, 0.9))
		            );
		}),
		;
		private PresetColor(Function<HexagonBullet,Paint> colorSP){
			this.colorSupplier=colorSP;
		}
		
		private Function<HexagonBullet,Paint> colorSupplier ;
		public Function<HexagonBullet,Paint> get(){
			return this.colorSupplier;
		}
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
	private Function<HexagonBullet,Paint> colorSupplier = (cirb)->{
		return Color.WHITESMOKE;
	};

	public void setColorSupplier(Function<HexagonBullet, Paint> function) {
		this.colorSupplier = function;
	}
	
	public Function<HexagonBullet, Paint> getColorSupplier() {
		return colorSupplier;
	}

}
