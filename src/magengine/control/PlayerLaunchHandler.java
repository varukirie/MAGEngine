package magengine.control;

import magengine.bullet.Bullet;
import magengine.bullet.impl.DefaultBullet;
import magengine.bullet.impl.PlayerBullet;
import magengine.element.impl.Player;
import magengine.game.GameSession;
import magengine.game.LogicExecutor;
import magengine.game.MoveHandler;
import magengine.util.DI;
import magengine.util.ElementUtils;

public class PlayerLaunchHandler{

	private static long count=0;

	public static  void playerShooting() {
		shooting(Player.getPlayer1().getX(), Player.getPlayer1().getY());
	}
	public static  void player2Shooting() {
		shooting(Player.getPlayer2().getX(), Player.getPlayer2().getY());
	}
	
	public static void shooting(double x,double y){
		ElementUtils mEU = (ElementUtils) DI.di().get("mEU");
		{
			Bullet bullet = new PlayerBullet(x-10, y);
			bullet.setVelocityY(-600);
			bullet.setVelocityX(60);
			mEU.add("playerBullet" + count++, bullet);
		}
		
		Bullet bullet = new PlayerBullet(x+10, y);
		bullet.setVelocityY(-600);
		bullet.setVelocityX(-60);
		mEU.add("playerBullet" + count++, bullet);
	}
	
	public static void shootSchedule(long interval){
		if(Player.getPlayer1().isShooting){
			playerShooting();
		}
		if(GameSession.getGameSession().mulplay&&Player.getPlayer2().isShooting==true){
			player2Shooting();
		}
		LogicExecutor.getLogicExecutor().schedule(()->{
			shootSchedule(interval);
		},interval);
	}
}
