package magengine.enemy;

import com.badlogic.gdx.math.Polygon;

import magengine.element.PolygonCollision;
import magengine.util.CollisionTeam;
import magengine.util.CollisionUtil;
import magengine.util.Transform;

public abstract class APolygonEnemy extends AEnemy implements PolygonCollision{

	public APolygonEnemy(double x, double y, double velocityX, double velocityY, double ax, double ay) {
		super(x, y, velocityX, velocityY, ax, ay);
	}

	public APolygonEnemy(double x, double y, double velocityX, double velocityY) {
		super(x, y, velocityX, velocityY);
	}

	public APolygonEnemy(double x, double y) {
		super(x, y);
	}
	
	@Override
	public CollisionTeam getTeam() {
		return CollisionTeam.ENEMY;
	}

	protected abstract double[][] getOrigin();
	private volatile float[] vertices = new float[getOrigin()[0].length*2];
	private volatile Polygon polygon = new Polygon(vertices);
	
	@Override
	public Polygon getPolygon() {
		return polygon;
	}
	
	@Override
	public void modify() {
		super.modify();
		double[][] ans = new double[2][getOrigin()[0].length];
		for(int i=0;i<getOrigin()[0].length;i++){
			ans[0][i]=getOrigin()[0][i];
			ans[1][i]=getOrigin()[1][i];
		}
		Transform.delta(ans, getX(), getY());
		CollisionUtil.toVertices(ans, vertices);
		polygon.setVertices(vertices);
//		System.out.println("vertices "+Arrays.toString(vertices));
//		System.out.println("polygon's vertices"+Arrays.toString(getPolygon().getVertices()));
	}
	
	@Override
	public void onCollision() {
		if(this.addAndGetHP(-1)<=0){
			this.wantBeRemoved=true;
		}
	
	}
}
