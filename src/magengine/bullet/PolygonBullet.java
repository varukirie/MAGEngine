package magengine.bullet;

import com.badlogic.gdx.math.Polygon;

import javafx.scene.canvas.GraphicsContext;
import magengine.element.PolygonCollision;
import magengine.util.Transform;

public abstract class PolygonBullet extends Bullet implements PolygonCollision{

	public PolygonBullet(double x, double y, double vx, double vy, double ax, double ay) {
		super(x, y, vx, vy, ax, ay);
		// TODO Auto-generated constructor stub
	}

	public PolygonBullet(double x, double y, double vx, double vy, double[] ACoodinate) {
		super(x, y, vx, vy, ACoodinate);
		// TODO Auto-generated constructor stub
	}

	public PolygonBullet(double x, double y, double vx, double vy) {
		super(x, y, vx, vy);
		// TODO Auto-generated constructor stub
	}

	public PolygonBullet(double x, double y, double[] VCoodinate, double ax, double ay) {
		super(x, y, VCoodinate, ax, ay);
		// TODO Auto-generated constructor stub
	}

	public PolygonBullet(double x, double y, double[] VCoodinate) {
		super(x, y, VCoodinate);
		// TODO Auto-generated constructor stub
	}

	public PolygonBullet(double x, double y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	protected abstract double[][] getOrigin();
	protected float[] vertices=new float[getOrigin()[0].length*2];
	protected Polygon polygon = new Polygon(vertices);
	
	
	protected double[][] transformVAndDelta(double[][] origin){
		double s = Math.sqrt(velocityX*velocityX+velocityY*velocityY);
		Transform t= new Transform(new double[][] {
			{-velocityY/s , velocityX/s }, 
			{-velocityX/s,-velocityY/s } });
		double[][] ans= t.transform(origin);
		t.delta(ans, getX(), getY());
		return ans;
	}
	
	protected void toVertices(double[][] ans,float[] target){
		for(int i=0;i<ans[0].length;i++){
			target[i*2]=(float) ans[0][i];
			target[i*2+1]=(float) ans[1][i];
		}
	}
	@Override
	public String getTeam() {
		return "bullet";
	}
	@Override
	public Polygon getPolygon() {
		return polygon;
	}
	@Override
	public void paint(GraphicsContext gc){
		double[][] ans=transformVAndDelta(getOrigin());
		gc.fillPolygon(ans[0],ans[1], getOrigin()[0].length);
		toVertices(ans, vertices);
	}
	
	
}
