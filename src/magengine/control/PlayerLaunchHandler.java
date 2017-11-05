package magengine.control;

import magengine.bullet.impl.DefaultBullet;
import magengine.bullet.impl.PlayerBullet;
import magengine.element.impl.Player;
import magengine.game.GameSession;
import magengine.game.MoveHandler;
import magengine.util.DI;
import magengine.util.ElementUtils;

public class PlayerLaunchHandler implements Runnable {

	private  ElementUtils elementUtils = (ElementUtils) DI.di().get("mEU");
	private Player player = Player.getPlayer1();
	private long count=0;
	@Override
	public void run() {
		MoveHandler mh= (MoveHandler) DI.di().get("mh");
		while(mh.keepRun){
			if(Player.getPlayer1().isShooting==true){
				playerShooting();
			}
			if(GameSession.getGameSession().mulplay&&Player.getPlayer2().isShooting==true){
				player2Shooting();
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	private  void playerShooting() {
		shooting(player.getX(), player.getY());
	}
	private  void player2Shooting() {
		shooting(Player.getPlayer2().getX(), Player.getPlayer2().getY());
	}
	
	private void shooting(double x,double y){
		{
			DefaultBullet bullet = new PlayerBullet(x-10, y);
			bullet.setVelocityY(-600);
			bullet.setVelocityX(0);
			elementUtils.add("playerBullet" + count++, bullet);
		}
		
		DefaultBullet bullet = new PlayerBullet(x+10, y);
		bullet.setVelocityY(-600);
		bullet.setVelocityX(0);
		elementUtils.add("playerBullet" + count++, bullet);
	}
}
