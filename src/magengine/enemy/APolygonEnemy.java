package magengine.enemy;

import com.badlogic.gdx.math.Polygon;

import application.Main;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import magengine.bullet.impl.PlayerBullet;
import magengine.element.PolygonCollision;
import magengine.game.GameSession;
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
	
	private volatile double[][] afterDelta=new double[2][3];
	@Override
	public void modify() {
		super.modify();
		double[][] ans = new double[2][getOrigin()[0].length];
		for(int i=0;i<getOrigin()[0].length;i++){
			ans[0][i]=getOrigin()[0][i];
			ans[1][i]=getOrigin()[1][i];
		}
		Transform.delta(ans, getX(), getY());
		afterDelta=ans;
		CollisionUtil.toVertices(ans, vertices);
		polygon.setVertices(vertices);
//		System.out.println("vertices "+Arrays.toString(vertices));
//		System.out.println("polygon's vertices"+Arrays.toString(getPolygon().getVertices()));
	}
	@Override
	public void paint(GraphicsContext gc) {
		if(Main.DEBUG_COLLISION){
			gc.setStroke(Color.RED);
			gc.strokePolygon(afterDelta[0], afterDelta[1],afterDelta[0].length);
		}
	}
	@Override
	public void onCollision(PolygonCollision m) {
		if(m.getTeam().equals(CollisionTeam.PLAYER_BULLET)){
			if(this.addAndGetHP(-1)<=0){
				this.setWantBeRemoved(true);
			}
			this.setNeedPaintBloodBar(GameSession.gc, GameSession.emBb);
		}
	
	}
}
