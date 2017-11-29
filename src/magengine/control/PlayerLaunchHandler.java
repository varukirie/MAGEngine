package magengine.control;

import magengine.element.impl.Player;
import magengine.game.GameSession;
import magengine.game.LogicExecutor;

public class PlayerLaunchHandler{

	private static PlayerPowerType playerPowerType= PlayerPowerType.MID;
	private static long count=0;
	private static long interval = 150;

	public static  void playerShooting() {
		shooting(Player.getPlayer1().getX(), Player.getPlayer1().getY());
	}
	public static  void player2Shooting() {
		shooting(Player.getPlayer2().getX(), Player.getPlayer2().getY());
	}
	
	public static void shooting(double x,double y){
		playerPowerType.getDoShoot().accept(x, y);
	}
	
	public static void shootSchedule(){
		if(Player.getPlayer1().isShooting){
			playerShooting();
		}
		if(GameSession.getGameSession().mulplay&& Player.getPlayer2().isShooting){
			player2Shooting();
		}
		LogicExecutor.getLogicExecutor().schedule(()->{
			shootSchedule();
		},interval);
	}
}
