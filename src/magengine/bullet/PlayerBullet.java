package magengine.bullet;

import magengine.util.CollisionTeam;

public class PlayerBullet extends DefaultBullet {

	public PlayerBullet(double x, double y) {
		super(x, y);
	}

	@Override
	public CollisionTeam getTeam() {
		return CollisionTeam.PLAYER_BULLET;
	}
}
