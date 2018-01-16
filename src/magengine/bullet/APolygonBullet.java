package magengine.bullet;

import com.badlogic.gdx.math.Polygon;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import magengine.element.PolygonCollision;
import magengine.element.impl.Player;
import magengine.util.CollisionTeam;
import magengine.util.Transform;

public abstract class APolygonBullet extends Bullet implements PolygonCollision{

	public APolygonBullet(double x, double y, double vx, double vy, double ax, double ay) {
		super(x, y, vx, vy, ax, ay);
		// TODO Auto-generated constructor stub
	}

	public APolygonBullet(double x, double y, double vx, double vy, double[] ACoodinate) {
		super(x, y, vx, vy, ACoodinate);
		// TODO Auto-generated constructor stub
	}

	public APolygonBullet(double x, double y, double vx, double vy) {
		super(x, y, vx, vy);
		// TODO Auto-generated constructor stub
	}

	public APolygonBullet(double x, double y, double[] VCoodinate, double ax, double ay) {
		super(x, y, VCoodinate, ax, ay);
		// TODO Auto-generated constructor stub
	}

	public APolygonBullet(double x, double y, double[] VCoodinate) {
		super(x, y, VCoodinate);
		// TODO Auto-generated constructor stub
	}

	public APolygonBullet(double x, double y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	protected abstract double[][] getOrigin();
	protected volatile float[] vertices=new float[getOrigin()[0].length*2];
	protected volatile Polygon polygon = new Polygon(vertices);
	private double scale = 1.0;
	protected double[][] scaleMartix = new double[][]{{scale,0},{0,scale}} ;
	public void setScale(double scale) {
		this.scale = scale;
		scaleMartix[0][0]=scale;
		scaleMartix[1][1]=scale;
	}
	public double getScale() {
		return scale;
	}
	protected double[][] transformVAndScaleAndDelta(double[][] origin){
		double s = Math.sqrt(velocityX*velocityX+velocityY*velocityY);
		Transform t= new Transform(new double[][] {
			{-velocityY/s , velocityX/s }, 
			{-velocityX/s,-velocityY/s } });
		double[][] ans= Transform.martixInTransform(scaleMartix, t.transform(origin));
		Transform.delta(ans, getX(), getY());
		return ans;
	}
	
	protected void toVertices(double[][] in,float[] target){
		for(int i=0;i<in[0].length;i++){
			target[i*2]=(float) in[0][i];
			target[i*2+1]=(float) in[1][i];
		}
	}
	@Override
	public CollisionTeam getTeam() {
		return CollisionTeam.ENEMY_BULLET;
	}
	@Override
	public Polygon getPolygon() {
		return polygon;
	}
	
	protected double[][] handleCollision(){
		double[][] ans=transformVAndScaleAndDelta(getOrigin());
//		System.out.println(this.getClass().getSimpleName()+"x:"+Arrays.toString(getOrigin()[0]));
//		System.out.println(this.getClass().getSimpleName()+"y:"+Arrays.toString(getOrigin()[1]));
		toVertices(ans, vertices);
//		System.out.println("vertices:"+Arrays.toString(vertices));
		polygon.setVertices(vertices);
		return ans;
	}
	private Paint outlineColor = Color.FLORALWHITE;
	private boolean needPaintOutline=false;
	
	@Override
	public void paint(GraphicsContext gc){
		double[][] ans=handleCollision();
		gc.fillPolygon(ans[0],ans[1], getOrigin()[0].length);
		if(needPaintOutline){
			gc.setStroke(outlineColor);
			gc.strokePolygon(ans[0],ans[1], getOrigin()[0].length);
		}
	}
	@Override
	public void onCollision(PolygonCollision m) {
		if(m instanceof Player){
			this.setWantBeRemoved(true);
		}
	}

	public boolean getNeedPaintOutline() {
		return needPaintOutline;
	}

	public void setNeedPaintOutline(boolean needPaintOutline) {
		this.needPaintOutline = needPaintOutline;
	}

	public Paint getOutlineColor() {
		return outlineColor;
	}

	public void setOutlineColor(Paint outlineColor) {
		this.outlineColor = outlineColor;
	}

	
}
