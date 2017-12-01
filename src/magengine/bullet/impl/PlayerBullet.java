package magengine.bullet.impl;

import magengine.element.PolygonCollision;
import magengine.element.impl.Spark;
import magengine.game.GameSession;
import magengine.util.CollisionTeam;
import magengine.util.DI;
import magengine.util.SoundUtil;

public class PlayerBullet extends ArrowBullet {

	public PlayerBullet(double x, double y) {
		super(x, y);
	}

	@Override
	public CollisionTeam getTeam() {
		return CollisionTeam.PLAYER_BULLET;
	}

	@Override
	public void onCollision(PolygonCollision m) {
		if(m.getTeam().equals(CollisionTeam.ENEMY)){
			setWantBeRemoved(true);
			Spark spark = new Spark(this.getX(), this.getY());
			DI.getmEU().add("spark"+GameSession.rand().nextInt(), spark);
		}
	}
}
