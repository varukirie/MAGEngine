package indi.megaastronic.control;


import indi.megaastronic.object.Ball;
import indi.megaastronic.object.Player;
import indi.megaastronic.util.ObjectUtils;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
/**
 * 此类负责绑定玩家操作事件
 * @author Astronic
 *
 */
public class PlayerKBControlHandler {
	//速度V
	public static final double BALL_V=2;
	public static final double PLAYER_V=1;
	private Player player = null;
	private ObjectUtils ou = null;
	private double mouseX=0;
	private double mouseY=0;
	public double getMouseX() {
		return mouseX;
	}
	public double getMouseY() {
		return mouseY;
	}
	public PlayerKBControlHandler(ObjectUtils ou,Player player) {
		this.ou=ou;
		this.player=player;
	}
	
	public void bindEvent(Scene scene){
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				//System.out.println("tri");
				pressHandle(e);
			}
		});
		scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				releaseHandle(e);
			}
		});
		scene.setOnMouseMoved(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent e) {
				mouseX=e.getX();
				mouseY=e.getY();
				//System.out.println("mouse x="+mouseX+" mouseY="+mouseY);
			}
		});
		
	}
	
	//顺序是上、右、下、左
	private boolean[] pressed = new boolean[]{false,false,false,false};
	private int ballCount = 0;
	@SuppressWarnings("incomplete-switch")
	public void pressHandle(KeyEvent e) {
		
		switch (e.getCode()) {
		case W:
			player.setVelocityY(-PLAYER_V);
			pressed[0]=true;
			break;
		case D:
			player.setVelocityX(PLAYER_V);
			pressed[1]=true;
			break;
		case S:
			player.setVelocityY(PLAYER_V);
			pressed[2]=true;
			break;
		case A:
			player.setVelocityX(-PLAYER_V);
			pressed[3]=true;
			break;
		case SPACE:
			Ball ball = new Ball(player.getX(), player.getY());
			double dx=mouseX-player.getX();
			double dy=mouseY-player.getY();
			double s=Math.sqrt(dx*dx+dy*dy);
			ball.setVelocityX(dx*BALL_V/s);
			ball.setVelocityY(dy*BALL_V/s);
			ou.addWantMoveAndPaint("ball"+ballCount++, ball);
			
			break;
		}
	}
	@SuppressWarnings("incomplete-switch")
	public void releaseHandle(KeyEvent e){
		switch (e.getCode()) {
		case W:
			if(pressed[2]==false)
				player.setVelocityY(0);
			else 
				player.setVelocityY(PLAYER_V);
			pressed[0]=false;
			break;
		case D:
			if(pressed[3]!=true)
				player.setVelocityX(0);
			else
				player.setVelocityX(-PLAYER_V);
			
			pressed[1]=false;
			break;
		case S:
			if(pressed[0]!=true)
				player.setVelocityY(0);
			else
				player.setVelocityY(-PLAYER_V);
				
			pressed[2]=false;
			break;
		case A:
			if(pressed[1]!=true)
				player.setVelocityX(0);
			else
				player.setVelocityX(PLAYER_V);
			pressed[3]=false;
			break;
		}
	}
}
