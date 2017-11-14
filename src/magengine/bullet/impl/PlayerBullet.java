package magengine.bullet.impl;

import javafx.application.Platform;
import javafx.scene.effect.Bloom;
import magengine.element.PolygonCollision;
import magengine.enemy.AEnemy;
import magengine.paint.MyCanvasSwitcher;
import magengine.util.CollisionTeam;
import magengine.util.DI;

public class PlayerBullet extends StarBullet {

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
		}
	}
}
