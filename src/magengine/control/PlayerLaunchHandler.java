package magengine.control;

import magengine.bullet.impl.DefaultBullet;
import magengine.bullet.impl.PlayerBullet;
import magengine.element.impl.Player;
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
			if(PlayerControlHandler.getExistedPlayerControlHandler().playerLauncher==true){
				playerShooting();
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	private  void playerShooting() {
		{
			DefaultBullet bullet = new PlayerBullet(player.getX()-10, player.getY());
			bullet.setVelocityY(-600);
			bullet.setVelocityX(0);
			elementUtils.add("playerBullet" + count++, bullet);
		}
		
		DefaultBullet bullet = new PlayerBullet(player.getX()+10, player.getY());
		bullet.setVelocityY(-600);
		bullet.setVelocityX(0);
		elementUtils.add("playerBullet" + count++, bullet);
	}
}
